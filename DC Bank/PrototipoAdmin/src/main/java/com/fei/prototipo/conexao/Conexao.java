/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fei.prototipo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ValterNetto
 */
public class Conexao {
    
    private static final String url = "jdbc:postgresql://localhost:5432/DBFei";
    private static final String user = "postgres";
    private static final String password = "1222";
    private static final String error = "Erro na conexão com PostGre";
    
    private static Connection conn;
    
    
    public static Connection getConexao(){
        
        try{
        if (conn == null){
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } else {
            return conn;
        }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println(error + " " + e.getMessage());
            return null;
        }
    }
}
