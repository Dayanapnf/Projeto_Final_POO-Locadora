package projetopoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;

public class Gerente extends Funcionario {
    
       
    // as listas das funções do programa
    
    //construtores
    public Gerente(){
        
    }
    public Gerente(String nome,String cpf,String login, String senha){
        super(nome,cpf,login,senha);
    }
    
    // metodos
   
    public void createGerente(){
        Gerente g = new Gerente();
        System.out.println("Cadastre seu nome:"     );
        g.setNome(sc.nextLine());
        System.out.println("Cadastre seu cpf:"      );
        g.setCpf(sc.nextLine());
        System.out.println("Cadastre seu login:"    );
        g.setLogin(sc.nextLine());    
        System.out.println("Cadastre sua senha:"    );
        g.setSenha(sc.nextLine());
        
 
        String sql = "INSERT INTO gerentes(nome, cpf, login, senha) VALUES(?,?,?,?)";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //cria uma conexão com o BD
            con = ConexaoBD.createConnectionToMySQL();
            
            //cria uma PreparedStatement para executar uma query
            stmt = con.prepareStatement(sql);
            
            //Adiciona os valores que são esperados pela query
            stmt.setString(1, g.getNome());
            stmt.setString(2, g.getCpf());
            stmt.setString(3, g.getLogin());
            stmt.setString(4, g.getSenha());
            
            //Executar
            stmt.execute();
            
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            System.out.println("***************** <Gerente cadastrado com sucesso!> *****************");
            System.out.println("");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            
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
    public void createOperador(){
        Operador o = new Operador();
        System.out.println("Cadastre seu nome:"     );
        o.setNome(sc.nextLine());
        System.out.println("Cadastre seu cpf:"      );
        o.setCpf(sc.nextLine());
        System.out.println("Cadastre seu login:"    );
        o.setLogin(sc.nextLine());    
        System.out.println("Cadastre sua senha:"    );
        o.setSenha(sc.nextLine());
        
 
        String sql = "INSERT INTO operadores(nome, cpf, login, senha) VALUES(?,?,?,?)";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //cria uma conexão com o BD
            con = ConexaoBD.createConnectionToMySQL();
            
            //cria uma PreparedStatement para executar uma query
            stmt = con.prepareStatement(sql);
            
            //Adiciona os valores que são esperados pela query
            stmt.setString(1, o.getNome());
            stmt.setString(2, o.getCpf());
            stmt.setString(3, o.getLogin());
            stmt.setString(4, o.getSenha());
            
            //Executar
            stmt.execute();
            
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            System.out.println("***************** <Operador cadastrado com sucesso!> ****************");
            System.out.println("");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            
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
    public void createCliente() throws ParseException{
        Cliente c = new Cliente();
        
        System.out.println("Digite o nome do cliente: "             );
        c.setNome(sc.nextLine());     
        System.out.println("Digite o cpf do cliente: "              );
        c.setCpf(sc.nextLine());
        System.out.println("Digite o endereço do cliente: "         );
        c.setEndereco(sc.nextLine());
        System.out.println("Digite o telefone do cliente: "             );
        c.setTelefone(sc.nextLine()); 
        
        
        
        String sql = "INSERT INTO clientes (nome, cpf, endereco, telefone) VALUES(?,?,?,?)";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //cria uma conexão com o BD
            con = ConexaoBD.createConnectionToMySQL();
            
            //cria uma PreparedStatement para executar uma query
            stmt = con.prepareStatement(sql);
            
            //Adiciona os valores que são esperados pela query
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getTelefone());
            
            //Executar
            stmt.execute();
            
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            System.out.println("***************** <Cliente cadastrado com sucesso!> *****************");
            System.out.println("");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("");
            
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
    public void createProduto( ){
        
        System.out.println("---------------------------------------------------------------------\n1- Cadastrar Filme");
        System.out.println("2- Cadastrar Cd");
        int opcaoProduto = sc.nextInt();
        System.out.println("---------------------------------------------------------------------");
        sc.nextLine();
        if(opcaoProduto == 1){
            
            Filmes f =  new Filmes() ;
            
            System.out.println("Digite o código do Filme:"                        );
            f.setCodigo(sc.nextLine()); 
            System.out.println("Digite o Título do Filme:"                        );
            f.setTitulo( sc.nextLine());
            f.setLocado(false);
            System.out.println("Digite o ano do lançamento do filme:"              );
            f.setAnoLancamento(sc.nextInt());  
            sc.nextLine();
            System.out.println("Digite a duração do filme:"                        );
            f.setDuracao(sc.nextLine());
            
            String sql = "INSERT INTO filmes(codigo, titulo, locado, ano, duracao ) VALUES(?,?,?,?,?)";
        
            Connection con = null;
            PreparedStatement stmt = null;
        
            try{
                //cria uma conexão com o BD
                con = ConexaoBD.createConnectionToMySQL();
            
                //cria uma PreparedStatement para executar uma query
                stmt = con.prepareStatement(sql);
            
                //Adiciona os valores que são esperados pela query
                stmt.setString (1, f.getCodigo());
                stmt.setString (2, f.getTitulo());
                stmt.setBoolean(3, f.getLocado());
                stmt.setInt    (4, f.getAnoLancamento());
                stmt.setString (5, f.getDuracao());
                
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("***************** <Produto cadastrado com sucesso!> *****************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
            
                //Executar
                stmt.execute();
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
    
        else if(opcaoProduto == 2){
            
            Cd cds = new Cd() ;
            System.out.println("Digite o código do CD:"                );
            cds.setCodigo(sc.nextLine()); 
            System.out.println("Digite o Título do CD:"                );
            cds.setTitulo(sc.nextLine()); 
            cds.setLocado(false); 
            System.out.println("Digite o nome do autor:"               );
            cds.setAutor(sc.nextLine()); 
            System.out.println("Digite o número de faixas:"            );
            cds.setNumFaixas(sc.nextInt()); 
            
              String sql = "INSERT INTO cd (codigo, titulo, locado, autor, numfaixas) VALUES(?,?,?,?,?)";
        
            Connection con = null;
            PreparedStatement stmt = null;
        
            try{
                //cria uma conexão com o BD
                con = ConexaoBD.createConnectionToMySQL();
            
                //cria uma PreparedStatement para executar uma query
                stmt = con.prepareStatement(sql);
            
                //Adiciona os valores que são esperados pela query
                stmt.setString (1, cds.getCodigo());
                stmt.setString (2, cds.getTitulo());
                stmt.setBoolean(3, cds.getLocado());
                stmt.setString (4, cds.getAutor());
                stmt.setInt    (5, cds.getNumFaixas());
                
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("***************** <Produto cadastrado com sucesso!> *****************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                //Executar
                stmt.execute();
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
            System.out.println("************************* <Opção inválida!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
            
    }
    
    public void listarOperador(){
        
        String sql = "SELECT * FROM operadores";
        
        List<Operador> op = new ArrayList<Operador>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        try{
            con = ConexaoBD.createConnectionToMySQL();
            
            stmt = con.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Operador o = new Operador();
                
                o.setCpf(rs.getString("cpf"));
                o.setNome(rs.getString("nome"));
                o.setLogin(rs.getString("login"));
                o.setSenha(rs.getString("senha"));
                
                
                op.add(o);
            }
            System.out.println("********************* <Operadores cadastrados> **********************");
            for(int i=0 ; i <op.size(); i++){
                System.out.println("Nome:" + op.get(i).getNome()+ "\nCpf:"+ op.get(i).getCpf()
                    +"\n---------------------------------------------------------------------");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null) rs.close();
                
                if(stmt != null) stmt.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
      
    }
    public void listarClientes(){
         
        String sql = "SELECT * FROM clientes";
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        try{
            con = ConexaoBD.createConnectionToMySQL();
            
            stmt = con.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
                
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                
                
                listaClientes.add(c);
            }
            System.out.println("*********************** <Clientes cadastrados> **********************");
            for(int i=0 ; i <listaClientes.size(); i++){
                System.out.println("Nome:" + listaClientes.get(i).getNome()+ "\nCpf:"+ listaClientes.get(i).getCpf()+
                   "\nEndereço:" + listaClientes.get(i).getEndereco() + "\nTelefone:" + listaClientes.get(i).getTelefone()
                    +"\n---------------------------------------------------------------------");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null) rs.close();
                
                if(stmt != null) stmt.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
    }
    public void listarProdutos(){
        
        String sql = "SELECT * FROM filmes";
        
        List<Filmes> listaFilmes = new ArrayList<Filmes>();
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        try{
            con = ConexaoBD.createConnectionToMySQL();
            
            stmt = con.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Filmes f = new Filmes() ;
                
                f.setCodigo(rs.getString("codigo"));
                f.setTitulo(rs.getString("titulo"));
                f.setLocado(rs.getBoolean("locado"));
                f.setAnoLancamento(rs.getInt("ano"));
                f.setDuracao(rs.getString("duracao"));
                
                listaFilmes.add(f);
            }
            System.out.println("*********************** <Filmes cadastrados> ************************");
            for(int i=0 ; i < listaFilmes.size(); i++){
                System.out.println("Código:" + listaFilmes.get(i).getCodigo()+"\nTítulo:" + listaFilmes.get(i).getTitulo() + "\nAno de lançamento:"+ listaFilmes.get(i).getAnoLancamento()+
                   "\nDuração:" + listaFilmes.get(i).getDuracao() + "\nLocado:" + listaFilmes.get(i).getLocado()
                    +"\n---------------------------------------------------------------------");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null) rs.close();
                
                if(stmt != null) stmt.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        String sql2 = "SELECT * FROM cd";
        
        List<Cd> listaCd = new ArrayList<Cd>();
        
        //Connection con = null;
        PreparedStatement stmt2 = null;
        
        ResultSet rs2 = null;
        
        try{
            con = ConexaoBD.createConnectionToMySQL();
            
            stmt2 = con.prepareStatement(sql2);
            
            rs2 = stmt2.executeQuery();
            
            while(rs2.next()){
                Cd cd = new Cd() ;
                
                cd.setCodigo(rs2.getString("codigo"));
                cd.setTitulo(rs2.getString("titulo"));
                cd.setLocado(rs2.getBoolean("locado"));
                cd.setAutor(rs2.getString("autor"));
                cd.setNumFaixas(rs2.getInt("numfaixas"));
                
                listaCd.add(cd);
            }
            System.out.println("************************* <Cds cadastrados> *************************");
            for(int i=0 ; i <listaCd.size(); i++){
                System.out.println("Código:" + listaCd.get(i).getCodigo()+"\nTítulo:" + listaCd.get(i).getTitulo() + "\nAutor:"+ listaCd.get(i).getAutor()+
                   "\nNúmero de faixas:" + listaCd.get(i).getNumFaixas() + "\nLocado:" + listaCd.get(i).getLocado()
                    +"\n---------------------------------------------------------------------");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs2 != null) rs2.close();
                
                if(stmt2 != null) stmt2.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
    }
   
    
    public void procurarOperador(){
        
        System.out.println("Digite o cpf do Operador que deseja procurar:");
        String buscaOperador = sc.nextLine();
        
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
                Operador o = new Operador();
                
                sql = "SELECT * FROM operadores WHERE cpf =?";
                
                con = ConexaoBD.createConnectionToMySQL();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, buscaOperador);
                rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    o.setNome(rs.getString("nome"));
                    o.setCpf(rs.getString("cpf"));
                   
                    
                    if(buscaOperador.equals(o.getCpf())){
                        System.out.println("---------------------------------------------------------------------" + "\nNome:" + o.getNome()+ "\nCpf:"+ o.getCpf()
                    +"\n---------------------------------------------------------------------");
                    }
                    
                }
                if(!buscaOperador.equals(o.getCpf())){
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("********************* <Operador não encontrado!> ********************");
                    System.out.println("---------------------------------------------------------------------"); 
                }
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
}

