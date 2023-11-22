/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.fei.prototipo.conexao.Conexao;
import entity.ClienteInfo;
import entity.Clientes;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ValterNetto
 */
public class ClienteController {

    public static void cadastrarClientes(Clientes clientes) {
        String error = "Erro ao cadastrar Cliente";
        String sql = "INSERT INTO CLIENTES (cpf, nome, senha, tipo_conta, saldo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, clientes.getCpf());
            ps.setString(2, clientes.getNome());
            ps.setString(3, clientes.getSenha());
            ps.setString(4, clientes.getTipo_conta());
            ps.setDouble(5, clientes.getSaldo());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println(error);
        }
    }

    public static boolean excluirClientes(String cpf) {
        String error = "Erro ao excluir Cliente";
        String sql = "DELETE FROM CLIENTES WHERE cpf = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(error);
            return false;
        }
    }

    public static ClienteInfo pesquisarClientes(String cpf) {
        String error = "Erro ao pesquisar Cliente";
        String sql = "SELECT * FROM CLIENTES WHERE cpf = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double saldo = rs.getDouble("saldo");
                    String tipo_conta = rs.getString("tipo_conta");
                    return new ClienteInfo(saldo, tipo_conta);
                } else {
                    System.out.println("Cliente não encontrado, CPF: " + cpf);
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(error);
            return null;
        }

    }

    public static List<ClienteInfo> pesquisarContasBanco() {
        String error = "Erro ao mostrar contas do banco";
        String sql = "SELECT * FROM CLIENTES";
        PreparedStatement ps = null;

        try {

            List<ClienteInfo> contas = new ArrayList<>();
            ps = Conexao.getConexao().prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String cpf = rs.getString("cpf");
                    String tipo_conta = rs.getString("tipo_conta");
                    double saldo = rs.getDouble("saldo");

                    ClienteInfo conta = new ClienteInfo(cpf, saldo, tipo_conta);
                    contas.add(conta);
                }
            }
            return contas;
        } catch(SQLException e){
            System.out.println(error);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}