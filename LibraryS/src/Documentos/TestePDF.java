/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

import Classes.Livro;
import Classes.Material;
import java.util.List;
import Documentos.PDF;
import java.io.FileNotFoundException;
/**
 *
 * @author lucas
 */
public class TestePDF {
    
    static public void main(String[] args) throws FileNotFoundException{
        Material livro = new Livro();
        List<Livro> listLivro = livro.listarMaterial();
        PDF.gerarLivroPDF(listLivro);
    }
    
}
