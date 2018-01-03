package com.monthly.expenses.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.service.InvoiceService;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	

	@Override
	public boolean generateInvoicePdf(ServletContext context, HttpServletRequest request, HttpServletResponse response, List<Transactions> transactions, StatisticDTO dateRange,StatisticDTO dbstatistic, String invoiceNumber) {
		Document document = new Document(PageSize.A4, 10, 10, 50, 50);
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		 
        try
        {
            String filePath=context.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = new File(filePath).exists();
            if(!exists){
                new File(filePath).mkdirs();
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/Invoice"+".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Image image = Image.getInstance(new ClassPathResource("logo_dark.png").getURL());
            image.setAbsolutePosition(10, 760);
            image.scaleToFit(120, 100);
            image.setAlignment(Element.ALIGN_LEFT);
            image.setSpacingAfter(50);
            document.add(image);
            
            Font invoice = FontFactory.getFont("Arial", 15, BaseColor.BLACK);
            Paragraph inovice = new Paragraph("INVOICE #"+invoiceNumber, invoice);
            inovice.setAlignment(Element.ALIGN_RIGHT);
            inovice.setIndentationLeft(10);
            inovice.setIndentationRight(10);
            inovice.setSpacingAfter(10);
            document.add(inovice);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            String fromDate = sdf.format(dateRange.getStartDate());
            
            Font datefont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Paragraph fromdate = new Paragraph("From : "+fromDate, datefont);
            fromdate.setAlignment(Element.ALIGN_LEFT);
            fromdate.setIndentationLeft(10);
            fromdate.setIndentationRight(10);
            fromdate.setSpacingAfter(10);
            document.add(fromdate);
            
            String toDate = sdf.format(dateRange.getEndDate());
            Paragraph todate = new Paragraph("To : "+toDate, datefont);
            todate.setAlignment(Element.ALIGN_RIGHT);
            todate.setIndentationLeft(10);
            todate.setIndentationRight(10);
            todate.setSpacingAfter(10);
            document.add(todate);
            

            Paragraph p=new Paragraph("", mainFont);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setIndentationLeft(50);
            p.setIndentationRight(50);
            p.setSpacingAfter(10);

            PdfPTable table = new PdfPTable(6); // 15 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table
            Font titleFont = FontFactory.getFont("ARIAL", 10, BaseColor.BLACK);
            Font subtitleFont = FontFactory.getFont("ARIAL", 9, BaseColor.BLACK);
            //Set Column widths
            float[] columnWidths = {2f,3f,4f,4f,3f,3f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Category", titleFont));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setExtraParagraphSpace(5f);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Location", titleFont));
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setExtraParagraphSpace(5f);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Description", titleFont));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setExtraParagraphSpace(5f);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Transaction Date", titleFont));
            cell4.setBorderColor(BaseColor.BLACK);
            cell4.setPaddingLeft(10);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setExtraParagraphSpace(5f);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Credit", titleFont));
            cell5.setBorderColor(BaseColor.BLACK);
            cell5.setPaddingLeft(10);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_CENTER);
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell5.setExtraParagraphSpace(5f);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Debit", titleFont));
            cell6.setBorderColor(BaseColor.BLACK);
            cell6.setPaddingLeft(10);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell6.setExtraParagraphSpace(5f);

            table.addCell(cell1);table.addCell(cell2);table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);table.addCell(cell6);

            for(Transactions transaction : transactions){
                PdfPCell c1 = new PdfPCell(new Paragraph(transaction.getCategory(), subtitleFont));
                c1.setBorderColor(BaseColor.BLACK);
                c1.setPaddingLeft(10);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                c1.setVerticalAlignment(Element.ALIGN_LEFT);
                c1.setBackgroundColor(BaseColor.WHITE);
                c1.setExtraParagraphSpace(5f);

                PdfPCell c2 = new PdfPCell(new Paragraph(transaction.getLocation(), subtitleFont));
                c2.setBorderColor(BaseColor.BLACK);
                c2.setPaddingLeft(10);
                c2.setHorizontalAlignment(Element.ALIGN_LEFT);
                c2.setVerticalAlignment(Element.ALIGN_LEFT);
                c2.setBackgroundColor(BaseColor.WHITE);
                c2.setExtraParagraphSpace(5f);

                PdfPCell c3 = new PdfPCell(new Paragraph(transaction.getDescription(), subtitleFont));
                c3.setBorderColor(BaseColor.BLACK);
                c3.setPaddingLeft(10);
                c3.setHorizontalAlignment(Element.ALIGN_LEFT);
                c3.setVerticalAlignment(Element.ALIGN_LEFT);
                c3.setBackgroundColor(BaseColor.WHITE);
                c3.setExtraParagraphSpace(5f);

                String transactionDate = sdf.format(transaction.getTransactionDate());
                PdfPCell c4 = new PdfPCell(new Paragraph(transactionDate,subtitleFont));
                c4.setBorderColor(BaseColor.BLACK);
                c4.setPaddingLeft(10);
                c4.setHorizontalAlignment(Element.ALIGN_LEFT);
                c4.setVerticalAlignment(Element.ALIGN_LEFT);
                c4.setBackgroundColor(BaseColor.WHITE);
                c4.setExtraParagraphSpace(5f);
                
                
                String credit = format.format(transaction.getCreditAmount());
                PdfPCell c5 = new PdfPCell(new Paragraph(credit,subtitleFont));
                c5.setBorderColor(BaseColor.BLACK);
                c5.setPaddingLeft(10);
                c5.setHorizontalAlignment(Element.ALIGN_LEFT);
                c5.setVerticalAlignment(Element.ALIGN_LEFT);
                c5.setBackgroundColor(BaseColor.WHITE);
                c5.setExtraParagraphSpace(5f);

                String debit = format.format(transaction.getDebitAmount());
                PdfPCell c6 = new PdfPCell(new Paragraph(debit,subtitleFont));
                c6.setBorderColor(BaseColor.BLACK);
                c6.setPaddingLeft(10);
                c6.setHorizontalAlignment(Element.ALIGN_LEFT);
                c6.setVerticalAlignment(Element.ALIGN_LEFT);
                c6.setBackgroundColor(BaseColor.WHITE);
                c6.setExtraParagraphSpace(5f);
               
                table.addCell(c1);table.addCell(c2);table.addCell(c3);
                table.addCell(c4);table.addCell(c5);table.addCell(c6);
            }
            
            PdfPCell cell7 = new PdfPCell(new Paragraph("Transactions", titleFont));
            cell7.setBorderColor(BaseColor.BLACK);
            cell7.setPaddingLeft(10);
            cell7.setColspan(4);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_CENTER);
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell7.setExtraParagraphSpace(5f);
            
            String credittotal = format.format(dbstatistic.getIncome());
            PdfPCell cell8 = new PdfPCell(new Paragraph(credittotal, titleFont));
            cell8.setBorderColor(BaseColor.BLACK);
            cell8.setPaddingLeft(10);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setVerticalAlignment(Element.ALIGN_CENTER);
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell8.setExtraParagraphSpace(5f);
            
            String debittotal = format.format(dbstatistic.getExpenses());
            PdfPCell cell9 = new PdfPCell(new Paragraph(debittotal, titleFont));
            cell9.setBorderColor(BaseColor.BLACK);
            cell9.setPaddingLeft(10);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell9.setExtraParagraphSpace(5f);
            
            table.addCell(cell7);table.addCell(cell8);table.addCell(cell9);

            document.add(p);
            document.add(table);

            document.close();
            writer.close();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}

}
