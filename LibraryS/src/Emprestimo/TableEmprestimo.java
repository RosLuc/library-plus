/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;

import Classes.Material;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Classe que representa os dados para inserir na tableView de Emprestimo.
 * @author lucas.
 */
public class TableEmprestimo {
    
    private int codinsc;
    private String nome;
    private int chamada;
    private String titulo;
    private Date dataemp;
    private Date datadev;
    private String status;

    /**
     * Construtor completo da classe.
     * @param codinsc Código de inscrição da pessoa.
     * @param nome Nome da pessoa.
     * @param chamada Número de chamada do material.
     * @param titulo Título do material.
     * @param dataemp Data do empréstimo do material.
     * @param datadev Data de devolução do material.
     * @param status Status do material.
     */
    public TableEmprestimo(int codinsc, String nome, int chamada, String titulo, Date dataemp, Date datadev, String status) {
        this.codinsc = codinsc;
        this.nome = nome;
        this.chamada = chamada;
        this.titulo = titulo;
        this.dataemp = dataemp;
        this.datadev = datadev;
        this.status = status;
    }
    
    /**
     * @return the codinsc
     */
    public int getCodinsc() {
        return codinsc;
    }

    /**
     * @param codinsc the codinsc to set
     */
    public void setCodinsc(int codinsc) {
        this.codinsc = codinsc;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the chamada
     */
    public int getChamada() {
        return chamada;
    }

    /**
     * @param chamada the chamada to set
     */
    public void setChamada(int chamada) {
        this.chamada = chamada;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the dataemp
     */
    public Date getDataemp() {
        return dataemp;
    }

    /**
     * @param dataemp the dataemp to set
     */
    public void setDataemp(Date dataemp) {
        this.dataemp = dataemp;
    }

    /**
     * @return the datadev
     */
    public Date getDatadev() {
        return datadev;
    }

    /**
     * @param datadev the datadev to set
     */
    public void setDatadev(Date datadev) {
        this.datadev = datadev;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Método responsavel por converte empréstimos para uma lista de forma adequada para a tableView.
     * @return List - List de TableEmprestimo.
     */
    static public List<TableEmprestimo> converteEmprestimos(){
        List<Emprestimo> emTemp = Emprestimo.ListaDeEmprestimo();
        List<TableEmprestimo> lista = new ArrayList<>();
        for (Emprestimo emprestimo : emTemp) {
            Set<Material> mat = emprestimo.getMateriais();
            for (Material material : mat) {
                lista.add(
                        new TableEmprestimo(emprestimo.getPessoa().getCodinsc(), 
                                emprestimo.getPessoa().getNome(), 
                                material.getNchamada(), 
                                material.getTitulo(), 
                                emprestimo.getDataemp(), 
                                emprestimo.getDatadev(),
                                emprestimo.getStatus())
                );
            }
        }
        return lista;
    }
}
