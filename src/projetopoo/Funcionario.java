package projetopoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    
    private String login;
    private String senha;
    
    Scanner sc = new Scanner(System.in);
    
    public Funcionario(){
        
    }
    public Funcionario(String nome,String cpf,String login, String senha){
        super(nome,cpf);
        this.login = login;
        this.senha = senha;       
    }
    public void setLogin(String login){
        this.login = login;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }
    
    public void procurarCliente(){
        
        System.out.println("Digite o cpf do cliente que deseja procurar:");
        String buscaCliente = sc.nextLine();
        
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
                Cliente c = new Cliente();
                
                sql = "SELECT * FROM clientes WHERE cpf =?";
                con = ConexaoBD.createConnectionToMySQL();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, buscaCliente);
                rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    
                    if(buscaCliente.equals(c.getCpf())){
                        System.out.println("---------------------------------------------------------------------" + "\nNome:" + c.getNome()+ "\nCpf:"+ c.getCpf()+
                        "\nEndereço:" + c.getEndereco() + "\nTelefone:" + c.getTelefone()
                        +"\n---------------------------------------------------------------------");
                    }
                    
                }
                if(!buscaCliente.equals(c.getCpf())){
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("********************* <Cliente não encontrado!> *********************");
                    System.out.println("---------------------------------------------------------------------"); 
                }
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            } 
    }
    public void procurarProduto() throws Exception{
        
        System.out.println("---------------------------------------------------------------------\n1- Procurar Filme");
        System.out.println("2- Procurar Cd");
        System.out.println("Digite a opção:");
        int escolha = sc.nextInt();
        sc.nextLine();
        
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        if(escolha == 1){
            
            System.out.println("---------------------------------------------------------------------\nDigite o código do produto que deseja procurar:");
            String busca = sc.nextLine();
                
            try{
                Filmes f = new Filmes() ;
                
                sql = "SELECT * FROM filmes WHERE codigo =?";
                con = ConexaoBD.createConnectionToMySQL();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, busca);
                rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    f.setCodigo(rs.getString("codigo"));
                    f.setTitulo(rs.getString("titulo"));
                    f.setLocado(rs.getBoolean("locado"));
                    f.setAnoLancamento(rs.getInt("ano"));
                    f.setDuracao(rs.getString("duracao"));
                    
                    if(busca.equals(f.getCodigo())){
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("*********************** <Produto encontrado!> ***********************");
                        System.out.println("---------------------------------------------------------------------" + "\nCódigo:" + 
                            f.getCodigo()+"\nTítulo:" + f.getTitulo() +
                            "\nAno de lançamento:"+ f.getAnoLancamento()+
                            "\nDuração:" + f.getDuracao() + "\nLocado:" + f.getLocado()
                            +"\n---------------------------------------------------------------------");
                    }
                    
                }
                if(!busca.equals(f.getCodigo())){
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("********************* <Produto não encontrado!> *********************");
                    System.out.println("---------------------------------------------------------------------"); 
                }
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
        else if(escolha == 2){
                   
            System.out.println("---------------------------------------------------------------------\nDigite o código do produto que deseja procurar:");
            String busca = sc.nextLine();  
            
            try{
                        
                sql = "SELECT * FROM cd WHERE codigo = ?";
                con = ConexaoBD.createConnectionToMySQL();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, busca);
                rs = stmt.executeQuery();
                
                Cd cd = new Cd() ;
                
                while(rs.next()){
                            
                    cd.setCodigo(rs.getString("codigo"));
                    cd.setTitulo(rs.getString("titulo"));
                    cd.setLocado(rs.getBoolean("locado"));
                    cd.setAutor(rs.getString("autor"));
                    cd.setNumFaixas(rs.getInt("numfaixas"));
                    if(busca.equals(cd.getCodigo())){
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("*********************** <Produto encontrado!> ***********************");
                        System.out.println("---------------------------------------------------------------------" +"\nCódigo:" + cd.getCodigo()+"\nTítulo:" + cd.getTitulo() + "\nAutor:"+ cd.getAutor()+
                            "\nNúmero de faixas:" + cd.getNumFaixas() + "\nLocado:" + cd.getLocado()
                            +"\n---------------------------------------------------------------------");
                    }
                }
                if(!busca.equals(cd.getCodigo())){
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("********************* <Produto não encontrado!> *********************");
                    System.out.println("---------------------------------------------------------------------"); 
                }
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        else{
            System.out.println("---------------------------------------------------------------------");
            System.out.println("************************* <Opção inválida!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
            
    
        
    }
    
}
