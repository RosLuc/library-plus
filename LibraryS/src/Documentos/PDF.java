
package Documentos;


import Classes.Livro;
import com.itextpdf.text.BadElementException;
//import Classes.Multimidia;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.fonts.*;
import java.awt.Color;
import java.io.File;

/**
 *
 * @author lucas
 */
public class PDF {
    
    static public void gerarLivroPDF(List<Livro> listLivro) throws FileNotFoundException{
        
        //Criando paramentros do documento.
        Document document=null;
        OutputStream outPutstream;
        try{
            document = new Document(PageSize.A4.rotate(),30,20,20,30);
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(245, 245, 245));
            new File("Documento").mkdir();
            outPutstream = new FileOutputStream("Documento/TabelaLivros.pdf");
            try{
                PdfWriter writer = PdfWriter.getInstance(document, outPutstream);
                document.open();
                Paragraph paragrafo = new Paragraph();
                document.add(paragrafo);
                PdfContentByte canvas = writer.getDirectContentUnder();
                Image image = null;
                try {
                    image = Image.getInstance("imagem.jpg");
                    image.scaleAbsolute(PageSize.A4.rotate());
                    image.setAbsolutePosition(0, 0);
                    canvas.saveState();
                    PdfGState state = new PdfGState();
                    state.setFillOpacity(0.6f);
                    canvas.setGState(state);
                    canvas.addImage(image);
                    canvas.restoreState();
                } catch (BadElementException | IOException ex) {
                    Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
                }
                //colunas
                PdfPTable Tabela = new  PdfPTable(7);
                PdfPCell cabecalho = new PdfPCell(new Paragraph("Lista de livros", fonte));
                cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
                cabecalho.setBorder(PdfPCell.NO_BORDER);
                cabecalho.setBackgroundColor(new BaseColor(100,150,200));
            
                //qnt de colunas
                cabecalho.setColspan(7);
                Tabela.addCell(cabecalho);
                Tabela.addCell("Nº Chamada");
                Tabela.addCell("Titulo");
                Tabela.addCell("CDD");
                Tabela.addCell("CDU");
                Tabela.addCell("Volume");
                Tabela.addCell("Quantidade");
                Tabela.addCell("Aquisição");
             
                //Parte que não esta dando certo
                for(int i = 0; i < listLivro.size();i++){
                    Tabela.addCell(String.valueOf(listLivro.get(i).getNchamada()));
                    Tabela.addCell(listLivro.get(i).getTitulo());
                    Tabela.addCell(String.valueOf(listLivro.get(i).getCdd()));
                    Tabela.addCell(String.valueOf(listLivro.get(i).getCdu()));
                    Tabela.addCell(String.valueOf(listLivro.get(i).getVolume()));
                    Tabela.addCell(String.valueOf(listLivro.get(i).getExemplar()));
                    Tabela.addCell(listLivro.get(i).getFormadeaquisicao());
                    
                }
                document.add(Tabela);
            }catch(DocumentException ex){    
                Logger.getLogger(Livro.class.getName()).log(Level.SEVERE,null, ex);        
            }       
        }finally{
            if(document != null) document.close();
        }
    } 
    
}