
package projetopoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Produtos  {
    
    
    String  codigo;
    String  titulo;
    boolean locado;
   
    Scanner sc = new Scanner(System.in);
    
    public Produtos(){
        
    }
    public Produtos(String codigo, String titulo,boolean locado){
        this.codigo = codigo;
        this.titulo = titulo;
        this.locado = locado;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public boolean getLocado() {
        return locado;
    }
    public void setLocado(boolean locado) {
        this.locado = locado;
    }

    
   
}
       
         

