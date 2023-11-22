/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexao.Conexao;
import entity.Clientes;
import entity.ClientesInfo;
import entity.Transacoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ValterNetto
 */
public class Controller {

    public static boolean validacao(String cpf, String senha, String tipoConta) {
        String error = "Erro ao validar cliente: ";
        String sql = "SELECT * FROM clientes WHERE cpf = ? AND senha = ? AND tipo_conta = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ps.setString(3, tipoConta);

            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(error + e.getMessage());
            return false;
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean validarDebito(String cpf, double saldo) {
        String error = "Erro ao validar debito ";
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        PreparedStatement ps = null;
        double tarifa;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double saldoCliente = rs.getDouble("saldo");
                    String tipoConta = rs.getString("tipo_conta");
                    switch (tipoConta) {
                        case "Conta-Salário":
                            tarifa = saldo * 0.05;
                            if (saldoCliente >= saldo + tarifa) {
                                return true;
                            } else {
                                return false;
                            }
                        case "Conta-Corrente":
                            tarifa = 0.01;
                            if (saldoCliente + 100 >= saldo + tarifa) {
                                return true;
                            } else {
                                return false;
                            }
                        case "Conta-Poupança":
                            if (saldoCliente >= saldo) {
                                return true;
                            } else {
                                return false;
                            }
                        default:
                            return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(error + e.getMessage());
        }
        return false;
    }

    public static void debitarCliente(String cpf, Double saldo, String tipo_Conta) {
        String error = "Erro ao debitar do Cliente: ";
        String sqlUpdate = "UPDATE clientes SET saldo = saldo - ? WHERE cpf = ?";
        String sqlSelect = "SELECT * FROM clientes WHERE cpf = ?";
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        double tarifa;
        switch (tipo_Conta) {
            case "Conta-Salário":
                tarifa = 0.05;
                break;
            case "Conta-Corrente":
                tarifa = 0.01;
                break;
            default:
                tarifa = 0;
        }
        System.out.println(tarifa);
        tarifa = saldo * tarifa;
        double debito = tarifa + saldo;
        try {
            if (validarDebito(cpf, saldo)) {
                ps = Conexao.getConexao().prepareStatement(sqlUpdate);
                ps.setDouble(1, debito);
                ps.setString(2, cpf);

                ps.execute();
                ps.close();
                ps2 = Conexao.getConexao().prepareStatement(sqlSelect);
                ps2.setString(1, cpf);
                try (ResultSet rs = ps2.executeQuery()) {
                    if (rs.next()) {
                        double saldoCliente = rs.getDouble("saldo");
                        lancarMovimento(cpf, saldo, saldoCliente, tarifa, "Débito");
                        ps2.execute();
                        ps2.close();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(error + e.getMessage());
        }
    }

    public static void lancarMovimento(String cpf, double valor, double saldo, double tarifa, String movimento) {
        String error = "Erro ao Lançar Transação: ";
        String sql = "INSERT INTO transacoes (cpf_cliente, movimento, valor, tarifa, saldo, data, hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        //Coletando as datas Deverlyn
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String dataFormatada = data.format(formatoData);
        String horaFormatada = hora.format(formatoHora);

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, movimento);
            ps.setDouble(3, valor);
            ps.setDouble(4, tarifa);
            ps.setDouble(5, saldo);
            ps.setString(6, dataFormatada);
            ps.setString(7, horaFormatada);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(error + e.getMessage());
        }

    }

    public static void depositarCliente(String cpf, Double saldo, String tipo_Conta) {
        String error = "Erro ao depositar para o Cliente: ";
        String sqlUpdate = "UPDATE clientes SET saldo = saldo + ? WHERE cpf = ?";
        String sqlSelect = "SELECT * FROM clientes WHERE cpf = ?";
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        double tarifa = 0;

        //System.out.println(tarifa);
        try {
            ps = Conexao.getConexao().prepareStatement(sqlUpdate);
            ps.setDouble(1, saldo);
            ps.setString(2, cpf);

            ps.execute();
            ps.close();
            ps2 = Conexao.getConexao().prepareStatement(sqlSelect);
            ps2.setString(1, cpf);
            try (ResultSet rs = ps2.executeQuery()) {
                if (rs.next()) {
                    double saldoCliente = rs.getDouble("saldo");
                    lancarMovimento(cpf, saldo, saldoCliente, tarifa, "Débito");
                    ps2.execute();
                    ps2.close();
                }

            }
        } catch (SQLException e) {
            System.out.println(error + e.getMessage());
        }
    }

    public static List<Transacoes> pesquisaExtrato(String cpf) {
        String error = "Erro ao Pesquisar Transações: ";
        String sql = "SELECT * FROM transacoes WHERE cpf_cliente = ?";
        PreparedStatement ps = null;

        try {
            List<Transacoes> transacoes = new ArrayList<>();
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String movimento = rs.getString("movimento");
                    Double valor = rs.getDouble("valor");
                    Double tarifa = rs.getDouble("tarifa");
                    String data = rs.getString("data");
                    String hora = rs.getString("hora");
                    Double saldo = rs.getDouble("saldo");
                    
                    Transacoes transacao = new Transacoes(movimento, valor, tarifa, data, hora, saldo);
                    transacoes.add(transacao);
                }
                return transacoes;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(error + e.getMessage());
            return null;
        }
    }
}
