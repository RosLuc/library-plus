/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notificar;

import Classes.Material;
import Emprestimo.Emprestimo;
import java.util.Set;

/**
 * Classe de objetos Notificar que forma a mensagens de notificação para envior via correio eletrônico.
 * @author Maria Adriana
 */
public class Notificar {
    
    
   /**
    * Método responsavel pro gerar a menssagem de notificação de emprestimo. 
    * @param nome String com o nome da pessoa.
    * @param emp Objeto do emprestimo.
    * @return Retorna uma string contendo a menssagem de emprestimo.
    */
   public static String notificarEmprestimo(String nome, Emprestimo emp){
        String notEmp = "\tConfimação de Empréstimos realizados:\nCaro(a) " + nome +", o(s) empréstimo(s) abaixo foram realizados com sucesso:\n"
               + "Data do empréstimo: " + emp.getDataemp() + " - Data para devolução: " + emp.getDatadev() + "\nMateriais:\n";
        String materiais = "";
        Set<Material> mat = emp.getMateriais();
        for(Material x : mat){
            materiais += ("Nº de chamada: " + x.getNchamada() 
                    + " - Título: "+ x.getTitulo() + " - Exemplar: " + x.getExemplar() + ".\n");
        }
       return notEmp + materiais;
   }
   
   /**
    * Método responsavel pro gerar a menssagem de notificação de atraso. 
    * @param nome String com o nome da pessoa.
    * @param emp Objeto do emprestimo.
    * @return Retorna uma string contendo a menssagem de atraso.
    */
   public static String notificarAtraso(String nome, Emprestimo emp){
        String notEmp = "\tAviso de Emprestimos atrasados: \nCaro(a)" + nome 
                +", o(s) emprestimo(s) do(s) material(is):\n ";
        String materiais = "";
        Set<Material> mat = emp.getMateriais();
        for(Material x : mat){
            materiais += ("Nº de chamada: " + x.getNchamada()
                    + " - Título: " + x.getTitulo() + " - Exemplar: " + x.getExemplar() + ", \n");
        }
        return notEmp + materiais + "\nse venceu(ram) dia: " + emp.getDatadev() + ". NÃO ESQUEÇA DE DEVOLVE-LO(S)!";
   }
   
   /**
    * Método responsavel pro gerar a menssagem de notificação de devolução. 
    * @param nome String com o nome da pessoa.
    * @param m material devolvido.
    * @return Retorna uma string contendo a menssagem de devolução.
    */
   public static String notificarDevolucao(String nome, Material m){
        String notEmp = "\tConfirmação de devolução do empréstimo:\nCaro(a)" + nome + ", o emprestimo abaixo foi devolvido com sucesso:\n"
               + "Materiail: ";
        String materiais = "";
        materiais += ("Nº de chamada: " + m.getNchamada()
                    + " - Título: " + m.getTitulo() + " - Exemplar: " + m.getExemplar() + ".\n");
       return notEmp + materiais;
   } 
   
}
