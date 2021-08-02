package projetopoo;

public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    
    public Pessoa(){
        
    }
    public Pessoa (String nome, String cpf){
        this.nome = nome;
        this.cpf  = cpf;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
           
   
}
