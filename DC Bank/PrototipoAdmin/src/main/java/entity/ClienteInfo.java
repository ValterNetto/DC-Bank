/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ValterNetto
 */
public class ClienteInfo {

    private double saldo;
    private String tipo_conta;
    private String cpf; 
    
    public ClienteInfo(String cpf, double saldo, String tipo_conta){
        this.cpf = cpf;
        this.saldo = saldo;
        this.tipo_conta = tipo_conta;
    }
    
    public ClienteInfo(double saldo, String tipo_conta) {
        this.saldo = saldo;
        this.tipo_conta = tipo_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
