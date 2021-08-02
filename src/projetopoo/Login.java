/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.util.List;
import java.sql.*;

/**
 *
 * @author Dayana
 */
public class Login extends Funcionario {
   
    private boolean cadastroStatus   ;
    
    public void setCadastroStatus(boolean cadastroStatus){
        this.cadastroStatus = cadastroStatus;
    }
    public boolean getCadastroStatus(){
        return cadastroStatus;
    }
    
    
    public boolean loginTesteG(){
                            
        System.out.println("************************** Faça seu login! **************************");
        System.out.println("Digite seu login:");
        String login = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
    
        String sql = "SELECT login,senha FROM gerentes WHERE login = ? and senha = ?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        this.setCadastroStatus(false);
        try{
            con = ConexaoBD.createConnectionToMySQL();
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            
           ResultSet rs = stmt.executeQuery();
           
           if(rs.next()){
               String loginBanco = rs.getString("login");
               String senhaBanco = rs.getString("senha");
               
               this.setCadastroStatus(true);
           }
           stmt.close(); 
            
        }catch(Exception e){
            System.out.println("************************* <Login inválido!> *************************");
            System.out.println("---------------------------------------------------------------------");
            
        }
        return cadastroStatus;
    } 
    
    public boolean loginTesteO(){
                            
        System.out.println("************************** Faça seu login! **************************");
        System.out.println("Digite seu login:");
        String login = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
    
        String sql = "SELECT login,senha FROM operadores WHERE login = ? and senha = ?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        this.setCadastroStatus(false);
        try{
            con = ConexaoBD.createConnectionToMySQL();
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            
           ResultSet rs = stmt.executeQuery();
           
           if(rs.next()){
               String loginBanco = rs.getString("login");
               String senhaBanco = rs.getString("senha");
               
               this.setCadastroStatus(true);
           }
           stmt.close(); 
            
        }catch(Exception e){
            System.out.println("************************* <Login inválido!> *************************");
            System.out.println("---------------------------------------------------------------------");
            
        }
        return cadastroStatus;
    } 
}

    

