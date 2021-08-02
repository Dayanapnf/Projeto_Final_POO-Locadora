/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Cliente extends Pessoa {
    
    private String endereco;
    private String telefone;
    
    Scanner sc = new Scanner(System.in);
    public Cliente(){
        
    }
    public Cliente(String nome, String cpf, String endereco, String telefone){
        super(nome,cpf);
        this.endereco = endereco;
        this.telefone    = telefone;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getEndereco(){
        return endereco;
    }
    public String getTelefone(){
        return telefone;
    }
    

    
}


