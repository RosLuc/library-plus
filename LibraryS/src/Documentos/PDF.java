/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;


import Classes.Livro;
import Classes.Multimidia;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            document = new Document(PageSize.A4,30,20,20,30);
            outPutstream = new FileOutputStream ("TabelaLivros.pdf");
            try{
                PdfWriter.getInstance(document, outPutstream);
                document.open();
                Paragraph paragrafo = new Paragraph();
                document.add(paragrafo);
                //colunas
                PdfPTable Tabela = new  PdfPTable(3);
                PdfPCell cabecalho = new PdfPCell(new Paragraph("Lista de livros"));
                cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
                cabecalho.setBorder(PdfPCell.NO_BORDER);
                cabecalho.setBackgroundColor(new BaseColor(100,150,200));
            
                //qnt de colunas
                cabecalho.setColspan(3);
                Tabela.addCell(cabecalho);
                Tabela.addCell("Nº Sequencia");
                Tabela.addCell("Nº Chamada");
                Tabela.addCell("Titulo");
             
                //Parte que não esta dando certo
                for(int i = 0; i < listLivro.size();i++){
                    Tabela.addCell(String.valueOf(listLivro.get(i).getNsequencia()));
                    Tabela.addCell(listLivro.get(i).getNchamada());
                    Tabela.addCell(listLivro.get(i).getTitulo());
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
