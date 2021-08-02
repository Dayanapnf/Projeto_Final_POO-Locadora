package projetopoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
import static projetopoo.ConexaoBD.*;

public class ProjetoPoo {

    public static void main(String[] args) throws Exception {
        
        //Recuperar conexão com o BD
        Connection con = createConnectionToMySQL();
        
        //Testar se a conexão eh nula
        if(con != null){
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        }
      
        Gerente  g  = new Gerente();
        Operador o  = new Operador();
        Cliente  c  = new Cliente();
        
        Login    l  = new Login();
        Funcionario f = new Funcionario();
        Locacao loc = new Locacao();
        
        int opcao;
        int opcaoGerente;
        int opcaoOperador;

    
        do{ 
            
            System.out.println("************************* <Seja bem-vindo!> *************************");
            System.out.println("                      Escolha uma opção abaixo:                      ");
            System.out.println("1- Gerente                                                           ");
            System.out.println("2- Operador de sistema                                               ");
            System.out.println("3- Cadastrar Gerente                                                 ");
            System.out.println("4- Sair                                                              ");
            System.out.println("Digite a opção:                                                      ");
        
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextInt();
                
                if(opcao == 1){
                    
                        l.loginTesteG();
                        System.out.println("---------------------------------------------------------------------");
                        if(l.getCadastroStatus()== true){
 
                            do{                     
                                System.out.println("*************************** <Olá Gerente!> **************************");
                                System.out.println("1- Cadastrar Produto "           );
                                System.out.println("2- Cadastrar Cliente "           );
                                System.out.println("3- Cadastrar Operador"           );
                                System.out.println("4- Listar Produtos   "           );
                                System.out.println("5- Listar Cliente    "           );
                                System.out.println("6- Listar Operador"              );
                                System.out.println("7- Procurar Produto  "           );
                                System.out.println("8- Procurar Cliente  "           );
                                System.out.println("9- Procurar Operador"            );
                                System.out.println("10- Voltar para o menu principal");
                                System.out.println("Digite a opção:");
                                opcaoGerente = sc.nextInt();
                        
                                switch (opcaoGerente){
                                    case 1:
                                        g.createProduto();
                                        break;
                                    case 2 :
                                        g.createCliente();
                                        break;
                                    case 3 :
                                        g.createOperador();
                                        break;
                                    case 4 :
                                        g.listarProdutos ();
                                        break;
                                    case 5 :
                                        g.listarClientes();
                                        break;
                                    case 6 :
                                        g.listarOperador();
                                        break;
                                    case 7:
                                        f.procurarProduto();
                                        break;
                                    case 8 :
                                        g.procurarCliente();
                                        break;
                                    case 9 :
                                        g.procurarOperador();
                                        break;
                                    
                                    default: 
                                        if(opcaoGerente !=10 && opcaoGerente >9){
                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("************************* <Opção inválida!> *************************");
                                            System.out.println("---------------------------------------------------------------------");
                                        }
                                        break;
                                }
                            }while(opcaoGerente != 10);
                        }
                        else{
                            System.out.println("************************* <Login inválido!> *************************");
                            System.out.println("---------------------------------------------------------------------");
                        }
                }   
                
                else if(opcao == 2){
                        l.loginTesteO();
                        System.out.println("---------------------------------------------------------------------");
                        if(l.getCadastroStatus()== true){
                            do{
                                                    
                                System.out.println("**************************  <Olá Operador!>  ************************");
                                System.out.println("1- Fazer Locação        ");
                                System.out.println("2- Dar baixa em Locação ");
                                System.out.println("3- Procurar Produtos    ");
                                System.out.println("4- Procurar Cliente     ");
                                System.out.println("5- Atualizar Cliente    ");
                                System.out.println("6- Excluir Produto      ");
                                System.out.println("7- Sair                 ");
                                System.out.println("Digite a opção:         ");
                                opcaoOperador = sc.nextInt();
                            
                                switch (opcaoOperador){
                                    case 1 :
                                        o.fazerLocacao();
                                        break;
                                    case 2 :
                                        o.baixaLocacao();      
                                        break;     
                                    case 3 :
                                        f.procurarProduto();
                                        break;
                                    case 4 :
                                        f.procurarCliente();
                                        break;
                                    case 5 :
                                        o.atualizarCliente();
                                        break;
                                    case 6 :
                                        o.excluirProduto();
                                        break;
                                    default : System.out.println("");
                                        break;
                          
                                }
                            }while(opcaoOperador != 7); 
                        }
                        else{
                            System.out.println("************************* <Login inválido!> *************************");
                            System.out.println("---------------------------------------------------------------------");
                        }
                       
                }
                else if  (opcao == 3){
                        g.createGerente();
                }
                else{
                    if(opcao != 4 && opcao >3){
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("************************* <Opção inválida!> *************************");
                        System.out.println("---------------------------------------------------------------------");
                    }
                       
                }
                   
                
               
                    
        }while(opcao != 4);
        
        if(opcao == 4){
            System.out.println("---------------------------------------------------------------------");
            System.out.println("*************************** <Volte Sempre!> *************************");
            System.out.println("---------------------------------------------------------------------");
        }
            
        
    }
}
