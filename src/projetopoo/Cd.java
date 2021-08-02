package projetopoo;


public  class Cd extends Produtos{
     
    private String autor;
    private int numFaixas;
    
    public Cd (){
        
    }
    public Cd(String codigo, String titulo, boolean locado,String autor,int numFaixas){
        super(codigo,titulo,locado);
        this.autor     = autor;
        this.numFaixas = numFaixas;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setNumFaixas(int numFaixas){
        this.numFaixas = numFaixas;
    }
    public String getAutor(){
        return autor;
    }
    public int getNumFaixas(){
        return numFaixas;
    }
}
