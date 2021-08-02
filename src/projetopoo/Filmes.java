
package projetopoo;


public  class Filmes extends Produtos{
    
    private int anoLancamento;
    private String duracao;
    
    public Filmes(){
        
    }
    public Filmes(String codigo, String titulo, boolean locado,int anoLancamento,String duracao){
        super(codigo,titulo,locado);
        this.anoLancamento = anoLancamento;
        this.duracao       = duracao;
    }
    public void setAnoLancamento(int anoLancamento){
        this.anoLancamento = anoLancamento; 
    }
    public void setDuracao(String duracao){
        this.duracao = duracao;
    }
    public int getAnoLancamento(){
        return anoLancamento;
    }
    public String getDuracao(){
        return duracao;
    }
     
   
}



