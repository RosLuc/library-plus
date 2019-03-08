
package Documentos;

import Classes.Livro;
import Classes.Material;
import Classes.Multimidia;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author lucas
 */
public class testePDF {
    
    static public void main(String[] args) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        Material livro = new Livro();
        List<Livro> listLivro = livro.listarMaterial();
        PDF.gerarLivroPDF(listLivro);
        Material mult = new Multimidia();
        List<Multimidia> listmult = mult.listarMaterial();
        PDF.gerarMultimidiaPDF(listmult);
    }
    
}