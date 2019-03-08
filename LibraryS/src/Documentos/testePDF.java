
package Documentos;

import Classes.Livro;
import Classes.Material;
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
        List<Object> listLivro = livro.listarMaterial();
        PDF.gerarLivroPDF(listLivro);
    }
    
}