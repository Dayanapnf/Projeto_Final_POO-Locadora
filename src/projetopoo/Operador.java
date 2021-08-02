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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Operador extends Funcionario{
    //Construtores
    public Operador(){
            
    }
    public Operador (String nome,String cpf,String login, String senha){
        super(nome,cpf,login,senha);
        
    }
    
    //Metodos
    
        // fazendo a locação dos produtos
    public void fazerLocacao() throws ParseException {
        
        System.out.println("---------------------------------------------------------------------\n1- Locar Filme");
        System.out.println("2- Locar Cd");
        
        int opcaoProduto = sc.nextInt();
        sc.nextLine();
        if(opcaoProduto == 1 || opcaoProduto == 2){
            
            System.out.println("Digite o cpf do cliente:");
            String cpf = sc.nextLine();
            System.out.println("Digite o código do Produto:");
            String cod = sc.nextLine();
            
            Locacao loc = new Locacao();
            Cliente c = new Cliente();
        
            int operacao = 1;
            //verificando cliente e filme 
            loc.acessoCliente(cpf);
            loc.acessoLocacao(opcaoProduto,cod,operacao);
            
            
            if(loc.getAcessoLocacao() == true && loc.getAcessoCliente() == true ){
                
                String sql = "INSERT INTO locacao(cpf, codigo, dataEntrada, dataDevolucao) VALUES(?,?,?,?)";
        
                Connection con = null;
                PreparedStatement stmt = null;
                
                //incrementando 
                java.util.Date dedata = new java.util.Date();
                java.sql.Date de = new java.sql.Date (dedata.getTime());
                loc.setDataEntrada(de);
                loc.setDataDevolucao(loc.dataDevolucao());
                c.setCpf(cpf);
                loc.setCodigo(cod);
                
                try{
                    //cria uma conexão com o BD
                    con = ConexaoBD.createConnectionToMySQL();
            
                    //cria uma PreparedStatement para executar uma query
                    stmt = con.prepareStatement(sql);
                    
                    //Adiciona os valores que são esperados pela query
                    stmt.setString (1, c.getCpf());
                    stmt.setString (2, loc.getCodigo());
                    stmt.setDate (3, (java.sql.Date) loc.getDataEntrada());
                    stmt.setDate (4, (java.sql.Date) loc.getDataDevolucao());
                    
                    
                    //Executar
                    stmt.execute();
                    
                    loc.statusLocacaoUp(opcaoProduto,cod);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    //fechar as conexoes
                    try{
                        if(stmt != null) stmt.close();
                
                        if(con != null) con.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
            else{
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("**************** <Não é possível alugar o produto!> *****************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }
        
        }
        else{
            System.out.println("---------------------------------------------------------------------");
            System.out.println("************************* <Opção inválida!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
              
    }
    
    // realizando a devolução dos produtos
    public void baixaLocacao(){
        
        System.out.println("---------------------------------------------------------------------\n1- Dar baixa em um Filme");
        System.out.println("2- Dar baixa em um Cd");
        int opcaoBaixa = sc.nextInt();
        sc.nextLine();
        
        
        if(opcaoBaixa == 1 || opcaoBaixa == 2){
            Locacao loc = new Locacao();
        
            System.out.println("Digite o cpf do cliente:");
            String cpf = sc.nextLine();
            System.out.println("Digite o código do Produto:");
            String codigoBaixa = sc.nextLine();
            int operacao = 2;
            loc.acessoCliente(cpf);
            loc.acessoLocacao(opcaoBaixa,codigoBaixa,operacao);
            
            if(loc.getAcessoLocacao() == false && loc.getAcessoCliente() == true){
                
            
                loc.statusLocacaoDelete(opcaoBaixa, codigoBaixa);
        
                String sql = "SELECT dataDevolucao FROM locacao WHERE codigo = ?";
                String sql2 = "DELETE FROM locacao WHERE codigo = ?";
        
        
                Connection con = null;
                PreparedStatement stmt = null;
                PreparedStatement stmt2 = null;
        
                ResultSet rs = null;
        
                loc.setCodigo(codigoBaixa);
        
        
                try{
            
                    Date dataBanco = new Date();
                    con = ConexaoBD.createConnectionToMySQL();
            
                    stmt = con.prepareStatement(sql);
           
            
                    stmt.setString(1, loc.getCodigo());
           
            
                    rs = stmt.executeQuery();
            
                    if(rs.next()){
                        dataBanco = rs.getDate("dataDevolucao");   
                    }
                
                    double valor = loc.calcularDiaria(dataBanco);
                 
                    stmt2 = con.prepareStatement(sql2);
                    stmt2.setString(1, loc.getCodigo());
                    stmt2.executeUpdate();
                    
                    
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("***************** <Operação realizada com sucesso!> *****************");
                    System.out.println("");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("Valor a receber : R$ " + valor);
                    System.out.println("---------------------------------------------------------------------");
           
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{  
                        if(stmt != null) stmt.close();
                
                        if(con != null) con.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("*************** <Não é possível realizar operação!> *****************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }
        }
        else{
            System.out.println("---------------------------------------------------------------------");
            System.out.println("************************* <Opção inválida!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
        
    }
   
    public void atualizarCliente(){
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        
        Locacao loc = new Locacao();
        System.out.println("Digite o cpf do cliente:");
        String cpf = sc.nextLine();
        
        loc.acessoCliente(cpf);
        if( loc.getAcessoCliente() == true){ 
            
            Cliente c = new Cliente();
            
            System.out.println("Digite o endereço do cliente: "         );
            c.setEndereco(sc.nextLine());
            System.out.println("Digite o telefone do cliente: "             );
            c.setTelefone(sc.nextLine());
            try{
                sql = "UPDATE clientes SET endereco =? , telefone =? WHERE cpf = ?";
            
                con = ConexaoBD.createConnectionToMySQL();
                
                stmt = con.prepareStatement(sql);
                stmt.setString(1,c.getEndereco());
                stmt.setString(2, c.getTelefone());
                stmt.setString(3, cpf);
            
                stmt.executeUpdate();
                
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("***************** <Atualização feita com sucesso!> ******************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }catch(Exception e){
                e.printStackTrace();
            }
            
           
        }
        
            
        
    } 
    
    public void excluirProduto(){
        
        
        System.out.println("---------------------------------------------------------------------\n1- Excluir um Filme");
        System.out.println("2- Excluir um Cd");
        int opcaoProduto = sc.nextInt();
        sc.nextLine();
        
        if(opcaoProduto == 1 || opcaoProduto == 2){
            Locacao loc = new Locacao();
            
            System.out.println("Digite o código do Produto:");
            String codigoExcluir = sc.nextLine();
            int operacao = 3;
            loc.acessoLocacao(opcaoProduto,codigoExcluir,operacao);
            
            if(loc.getAcessoLocacao() == true ){
                
                if(opcaoProduto == 2){
                    String sql2 = "DELETE FROM cd WHERE codigo = ?";
        
        
                    Connection con = null;
                    PreparedStatement stmt2 = null;
        
                    ResultSet rs = null;
        
                    loc.setCodigo(codigoExcluir);
        
        
                    try{
                        con = ConexaoBD.createConnectionToMySQL();
                        stmt2 = con.prepareStatement(sql2);
                        stmt2.setString(1, loc.getCodigo());
                        stmt2.executeUpdate();
                    
                    
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("");
                        System.out.println("***************** <Operação realizada com sucesso!> *****************");
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------");
                    
           
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{  
                            if(stmt2 != null) stmt2.close();
                
                            if(con != null) con.close();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                else {
                     String sql2 = "DELETE FROM filmes WHERE codigo = ?";
        
        
                    Connection con = null;
                    PreparedStatement stmt2 = null;
        
                    ResultSet rs = null;
        
                    loc.setCodigo(codigoExcluir);
        
        
                    try{
                        con = ConexaoBD.createConnectionToMySQL();
                        stmt2 = con.prepareStatement(sql2);
                        stmt2.setString(1, loc.getCodigo());
                        stmt2.executeUpdate();
                    
                    
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("");
                        System.out.println("***************** <Operação realizada com sucesso!> *****************");
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------");
                    
           
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{  
                            if(stmt2 != null) stmt2.close();
                
                            if(con != null) con.close();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            else{
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("*************** <Não é possível realizar operação!> *****************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }
        }
        else{
            System.out.println("---------------------------------------------------------------------");
            System.out.println("************************* <Opção inválida!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
        
    }       
        
    
}
