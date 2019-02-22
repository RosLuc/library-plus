
package Documentos;

import Classes.Livro;
import Classes.Material;
import java.util.List;
import java.io.FileNotFoundException;
/**
 *
 * @author lucas
 */
public class testePDF {
    
    static public void main(String[] args) throws FileNotFoundException{
        Material livro = new Livro();
        List<Livro> listLivro = livro.listarMaterial();
        PDF.gerarLivroPDF(listLivro);
    }
    
}