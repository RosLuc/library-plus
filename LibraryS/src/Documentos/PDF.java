/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

/**
 *
 * @author maria
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Classes.Livro;
import Classes.Multimidia;


public class PDF {
   
    
    static public void Gerar_certificado(String nome) throws DocumentException, IOException{
  
        Document document=null;
        OutputStream outPutstream;
     
        try{
            document = new Document(PageSize.A4.rotate(),30,20,20,30);
            BaseFont Century_Schoolbook = BaseFont.createFont("Documentos/CenturySchoolbook.ttf", BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
            Font font = new Font(Century_Schoolbook, 58, Font.ITALIC, new BaseColor(43,57,144));
            Font font1 = new Font(Century_Schoolbook, 29, Font.ITALIC, new BaseColor(43,57,144));
            Font font2 = new Font(Century_Schoolbook, 14, Font.ITALIC, new BaseColor(43,57,144));
            Font font3 = new Font(Century_Schoolbook, 20, Font.ITALIC, new BaseColor(4,5,12));
        
                 
            outPutstream = new FileOutputStream ("Documentos/Modelo_Certificado.pdf");
            try{
                PdfWriter writer = PdfWriter.getInstance(document, outPutstream);
                document.open();
            
                PdfContentByte canvas = writer.getDirectContentUnder();
                Image imagem = null;
                try{
                    imagem = Image.getInstance("imagem/modelo.jpg");
                    imagem.scaleAbsolute(PageSize.A4.rotate());
                    imagem.setAbsolutePosition(0, 0);
                    canvas.saveState();
                    PdfGState state = new PdfGState();
                    state.setFillOpacity(100);
                    canvas.setGState(state);
                    canvas.addImage(imagem);
                    canvas.restoreState(); 
              
                    Paragraph paragrafo = new Paragraph ("Certificado", font);
                    paragrafo.setAlignment(Element.ALIGN_CENTER);
                    paragrafo.setLeading(0,2.25f);
                        
                    document.add(paragrafo);
                
                    Paragraph paragrafo1 = new Paragraph ("LEITOR DO MÊS", font1);
                    paragrafo1.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragrafo1);
                
                    Paragraph paragrafo2 = new Paragraph ("concedido a", font2);
                    paragrafo2.setAlignment(Element.ALIGN_CENTER);
                    paragrafo2.setSpacingBefore(30);
                    document.add(paragrafo2);
                
                    Paragraph paragrafo5 = new Paragraph (nome, font3);
                    paragrafo5.setAlignment(Element.ALIGN_CENTER);
                    paragrafo5.setSpacingBefore(65);
                    document.add(paragrafo5);
                               
                    Paragraph paragrafo3 = new Paragraph ("em reconhecimento a sua dedicação e entusiasmo.", font2);
                    paragrafo3.setAlignment(Element.ALIGN_CENTER);
                    paragrafo3.setSpacingBefore(50);
                    document.add(paragrafo3);
                
                    Paragraph paragrafo4 = new Paragraph ("                                          Nome/Bibliotecária                                      "
                        + "                  Data", font2);
                    paragrafo4.setAlignment(Element.ALIGN_JUSTIFIED);
                    paragrafo4.setSpacingBefore(100);
                    document.add(paragrafo4);
                              
                
                }catch(BadElementException | IOException ex){
                    System.err.println("ERRO: " + ex);            
                }
           
            }catch(DocumentException ex){    
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE,null, ex);        
            }        
        } finally{
            if(document != null){
                document.close();
            }
        }
    }
    
    static public void gerarLivroPDF(List listMaterial) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        
        Document document=null;
        OutputStream outPutstream;
        try{
            document = new Document(PageSize.A4.rotate(),30,20,20,30);
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(4,5,12));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(255,255,255));
            new File("Documento").mkdir();
            outPutstream = new FileOutputStream("Documento/TabelaLivros.pdf");
            try{
                PdfWriter writer = PdfWriter.getInstance(document, outPutstream);
                document.open();
                Paragraph paragrafo4 = new Paragraph(".....", font);
                document.add(paragrafo4);
                Image imagem = Image.getInstance("Documento/Brasão.png");
                imagem.setAlignment(Element.ALIGN_CENTER);
                imagem.scalePercent(40);
              
                document.add(imagem);
                Paragraph paragrafo = new Paragraph("ESC. EST. TEMPO INTEGRAL DR. JOSÉ FERNANDES DE MELO", fonte);
                paragrafo.setLeading(0,3);
                paragrafo.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo);
                
                Paragraph paragrafo1 = new Paragraph("GOVERNO DO ESTADO DO RIO GRANDE DO NORTE", fonte);
                paragrafo1.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo1);
                        
                Paragraph paragrafo2 = new Paragraph("SECRETARIA DE EDUCAÇÃO CULTURA E DESPORTOS", fonte);
                paragrafo2.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo2);
                
                Paragraph paragrafo3 = new Paragraph(".....", font);
                if(listMaterial.contains(Livro.class)){
                    document.add(paragrafo3);
                    PdfPTable Tabela = new  PdfPTable(3);
                
                    PdfPCell cabecalho = new PdfPCell(new Paragraph("LISTA DE LIVROS", fonte));
                    cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                    cabecalho.setBorder(PdfPCell.NO_BORDER);
                    cabecalho.setBackgroundColor(new BaseColor(210,105,30));
                    cabecalho.setColspan(3);
                    Tabela.addCell(cabecalho);
                    Tabela.addCell("NOME");
                    Tabela.addCell("EMAIL");
                    Tabela.addCell("ID_PDF");
             
                    for(int i = 0; i < list_pdf.size();i++){
                        Tabela.addCell(list_pdf.get(i).getNome());
                        Tabela.addCell(list_pdf.get(i).getEmail());
                        Tabela.addCell(String.valueOf(list_pdf.get(i).getId_pdf()));
                    }
                    document.add(Tabela);
                }
                
            }catch(DocumentException ex){    
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE,null, ex);        
            }        
        }finally{
            if(document != null){
                document.close();
            }
        }
    }
}