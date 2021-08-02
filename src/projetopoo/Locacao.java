
package projetopoo;

       
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Dayana
 */
public class Locacao extends Produtos{
    
    private Date data ;
    private boolean acessoCliente;
    private boolean acessoLocacao;
    private Date dataDevolucao;
    private Date dataEntrada;
    private Date dataEntrega;
    
    
    Scanner sc = new Scanner(System.in);
    
    //construtores
    public Locacao() {
        
    }
    public Locacao(Date dataEntrada , Date dataDevolucao){
       
        this.dataEntrada = dataEntrada;
        this.dataDevolucao = dataDevolucao;
        
    }

    ///gets sets
    public void setDataEntrega(Date dataEntrega){
        this.dataEntrega = dataEntrega;
    }
    public void setAcessoCliente(boolean acessoCliente){
        this.acessoCliente = acessoCliente;
    }
    public void setAcessoLocacao(boolean acessoLocacao){
        this.acessoLocacao = acessoLocacao;
    }
    public void setData(Date data){
        this.data = data;
    }
    public void setDataDevolucao( Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }
    public void setDataEntrada( Date dataEntrada){
        this.dataEntrada= dataEntrada;
    }
    
    public Date getDataEntrega(){
        return dataEntrega;
    }
    public Date getData(){
        return data;
    }
    public boolean getAcessoCliente(){
        return acessoCliente;
    }
    public boolean getAcessoLocacao(){
        return acessoLocacao;
    }
    public Date getDataDevolucao(){
        return dataDevolucao;
    }
    public Date getDataEntrada(){
        return dataEntrada;
    }
    
    
    //metodos
    
    // calculando data e valor a ser pago na devolução
    public double calcularDiaria(Date dataDevolucao){
        
        Locacao loc = new Locacao();
        
        double total ;
        loc.setDataEntrega(new Date());
        
        long diffEmMil = Math.abs(dataDevolucao.getTime() - loc.getDataEntrega().getTime());
        
        long diff = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);
        //int dias = (int) (diffEmMil /(1000 *60 *60));
        System.out.println(diff);
        //System.out.println(dias);
        int diferenca = (int) diff;
        
        if(diferenca == 0){
           total = 5.50;
        }
        else{
            total = 5.50 + (2.00 * diferenca) ;
        }
        return total;
    }
    public Date dataDevolucao() throws ParseException{
       
        Locacao loc = new Locacao();
      
        data = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, 2 );
        data = cal.getTime();
        loc.setData(data);
        java.sql.Date dt = new java.sql.Date (data.getTime());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Data de devolucao: " + data);
        System.out.println("");
       
        return dt;
        
    }
    
    //verificando cliente,codigo,locação
    public boolean acessoCliente(String cpf ){
     
        String sql = "SELECT * FROM clientes WHERE cpf = ? ";
        Cliente c = new Cliente();
        Connection con = null;
        PreparedStatement stmt = null;
        this.setAcessoCliente(false);
        try{
            con = ConexaoBD.createConnectionToMySQL();
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
           
           if(rs.next()){
               c.setCpf(rs.getString("cpf"));
               this.setAcessoCliente(true);
               
           }
           if(!cpf.equals(c.getCpf())){
                System.out.println("---------------------------------------------------------------------");
                System.out.println("************************ <Cliente inválido!> ************************");
                System.out.println("---------------------------------------------------------------------");
           }
           stmt.close(); 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return acessoCliente;
    }
    public boolean acessoLocacao(int opcaoProduto ,String cod, int operacao){

        
        if(opcaoProduto == 1 ){
            String sql = "SELECT * FROM filmes WHERE codigo = ? ";
            Filmes f = new Filmes() ;
            
            Connection con = null;
            PreparedStatement stmt = null;
            
            try{
                con = ConexaoBD.createConnectionToMySQL();
                stmt = con.prepareStatement(sql);
            
                stmt.setString(1, cod);
            
                ResultSet rs = stmt.executeQuery();
           
                if(rs.next()){
                    f.setLocado(rs.getBoolean("locado"));
                    f.setCodigo(rs.getString("codigo"));
                }
                boolean status = true;
                //System.out.println(cod);
                //System.out.println(f.getLocado());
                //System.out.println(f.getCodigo());
                if(operacao == 1){
                    if(cod.equals(f.getCodigo()) && f.getCodigo() != null){
                        if(status == f.getLocado()){
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("********************** <Filme indisponível!> ************************");
                            System.out.println("---------------------------------------------------------------------");
                            this.setAcessoLocacao(false); 
                        }
                        else
                            this.setAcessoLocacao(true);
                    }
                    else{
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("************************ <Código inválido!> *************************");
                        System.out.println("---------------------------------------------------------------------");
                    }
                }
                // excluir locacao
                else if (operacao == 3){
                    if(cod.equals(f.getCodigo()) && f.getCodigo() != null){
                        if(status == f.getLocado()){
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("******** <Produto em locação, é necessário efetuar baixa!> **********");
                            System.out.println("---------------------------------------------------------------------");
                            this.setAcessoLocacao(false); 
                        }
                        else
                            this.setAcessoLocacao(true);
                    }
                    else{
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("************************ <Código inválido!> *************************");
                        System.out.println("---------------------------------------------------------------------");
                    }
                }
                else{
                    if(cod.equals(f.getCodigo()) && f.getCodigo() != null){
                        if(status != f.getLocado()){
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("********************** <Baixa indisponível!> ************************");
                            System.out.println("---------------------------------------------------------------------");
                            this.setAcessoLocacao(true); 
                        }
                        else
                            this.setAcessoLocacao(false);
                    }
                    else{
                        this.setAcessoLocacao(true);
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("************************ <Código inválido!> *************************");
                        System.out.println("---------------------------------------------------------------------");
                    }
                }
                stmt.close();
                //System.out.println(this.getAcessoLocacao());
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            if (opcaoProduto == 2){
                String sql = "SELECT * FROM cd WHERE codigo = ? ";
                Cd cd = new Cd() ;
            
                Connection con = null;
                PreparedStatement stmt = null;
            
                try{
                    con = ConexaoBD.createConnectionToMySQL();
                    stmt = con.prepareStatement(sql);
            
                    stmt.setString(1, cod);
            
                    ResultSet rs = stmt.executeQuery();
           
                    if(rs.next()){
                        cd.setLocado(rs.getBoolean("locado"));
                        cd.setCodigo(rs.getString("codigo"));
                    }
                    boolean status = true;
                    if(operacao == 1){
                        if(cod.equals(cd.getCodigo()) && cd.getCodigo() != null){
                            if(status == cd.getLocado()){
                                System.out.println("---------------------------------------------------------------------");
                                System.out.println("************************ <Cd indisponível!> *************************");
                                System.out.println("---------------------------------------------------------------------");
                                this.setAcessoLocacao(false); 
                            }
                            else
                                this.setAcessoLocacao(true);
                        }
                        else{
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("************************ <Código inválido!> *************************");
                            System.out.println("---------------------------------------------------------------------");
                        }
                    }
                    //excluir cd
                    else if (operacao == 3){
                        if(cod.equals(cd.getCodigo()) && cd.getCodigo() != null){
                            if(status == cd.getLocado()){
                                System.out.println("---------------------------------------------------------------------");
                                System.out.println("******** <Produto em locação, é necessário efetuar baixa!> **********");
                                System.out.println("---------------------------------------------------------------------");
                                this.setAcessoLocacao(false); 
                            }
                            else
                                this.setAcessoLocacao(true);
                        }
                        else{
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("************************ <Código inválido!> *************************");
                            System.out.println("---------------------------------------------------------------------");
                        }  
                    }
                    else{
                        if(cod.equals(cd.getCodigo()) && cd.getCodigo() != null){
                            if(status != cd.getLocado()){
                                System.out.println("---------------------------------------------------------------------");
                                System.out.println("********************** <Baixa indisponível!> ************************");
                                System.out.println("---------------------------------------------------------------------");
                                this.setAcessoLocacao(true); 
                            }
                            else
                                this.setAcessoLocacao(false);
                        }
                        else{
                            this.setAcessoLocacao(true);
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("************************ <Código inválido!> *************************");
                            System.out.println("---------------------------------------------------------------------");
                        }
                    }
                    stmt.close();
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }   
        return acessoLocacao ;    
    }
    
    // mudando o valor do locado
    public void statusLocacaoUp(int opcaoProduto ,String cod){
        
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        
        //ResultSet rs = null;
        
        try{
            if(opcaoProduto == 1){
                sql = "UPDATE filmes SET locado = ? WHERE codigo = ?";
                
                Filmes f = new Filmes() ;
                
                con = ConexaoBD.createConnectionToMySQL();
                
                f.setLocado(true);
                f.setCodigo(cod);
                
                
                stmt = con.prepareStatement(sql);
                stmt.setBoolean(1,f.getLocado());
                stmt.setString(2, f.getCodigo());
            
                stmt.executeUpdate();
                
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("******************* <Locação feita com sucesso!> ********************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
                
            }else if(opcaoProduto == 2){
                sql = "UPDATE cd SET locado = ? WHERE codigo = ?";
                
                con = ConexaoBD.createConnectionToMySQL();
                
                Cd cd = new Cd() ;
                cd.setLocado(true);
                cd.setCodigo(cod);
                stmt = con.prepareStatement(sql);
                stmt.setBoolean(1, cd.getLocado());
                stmt.setString(2, cd.getCodigo());
                
                stmt.executeUpdate();
                
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("******************* <Locação feita com sucesso!> ********************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }else{
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("************************ <Opção invalida!> **************************");
                System.out.println("");
                System.out.println("---------------------------------------------------------------------");
            }
              
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
              //  if(rs != null) rs.close();
                
                if(stmt != null) stmt.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void statusLocacaoDelete(int opcaoProduto ,String cod){
        
        String sql;
        Connection con = null;
        PreparedStatement stmt = null;
        
        //ResultSet rs = null;
        
        try{
            if(opcaoProduto == 1){
                try{
                    sql = "UPDATE filmes SET locado = ? WHERE codigo = ?";
                
                    Filmes f = new Filmes();
                
                    con = ConexaoBD.createConnectionToMySQL();
                
                    f.setLocado(false);
                    f.setCodigo(cod);
                
                    stmt = con.prepareStatement(sql);
                    stmt.setBoolean(1,f.getLocado());
                    stmt.setString(2, f.getCodigo());
            
                    stmt.executeUpdate();
                
                   
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        //  if(rs != null) rs.close();
                
                        if(stmt != null) stmt.close();
                
                        if(con != null) con.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                
                try{
                    sql = "UPDATE cd SET locado = ? WHERE codigo = ?";
                
                    con = ConexaoBD.createConnectionToMySQL();
                
                    Cd cd = new Cd();
                    cd.setLocado(false);
                    cd.setCodigo(cod);
                    stmt = con.prepareStatement(sql);
                    stmt.setBoolean(1, cd.getLocado());
                    stmt.setString(2, cd.getCodigo());
                
                    stmt.executeUpdate();
                
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        //  if(rs != null) rs.close();
                
                        if(stmt != null) stmt.close();
                
                        if(con != null) con.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }  
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
              //  if(rs != null) rs.close();
                
                if(stmt != null) stmt.close();
                
                if(con != null) con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    

}