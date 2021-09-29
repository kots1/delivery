package com.delivery;

import com.delivery.entity.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ConvertToPDF {
    //private static String FILE = "FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void generatePDF(String file,List<Order> orders) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addTitlePage(document);
            addContent(document,orders);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface);
        // Lets write a big header
        preface.add(new Paragraph("Manager report of orders", catFont));

        addEmptyLine(preface);
        SimpleDateFormat dateFormat =new SimpleDateFormat();

        preface.add(new Paragraph(
                "Report generated : " + dateFormat.format(new Date()),
                smallBold));

        document.add(preface);

    }

    private static void addContent(Document document, List<Order> orders) throws DocumentException {
        Paragraph preface = new Paragraph("\n");
        // We add one empty line
        addEmptyLine(preface);
        document.add(createTable( orders));


    }

    private static PdfPTable createTable( List<Order> orders) {
        float[] columnWidths = {2, 3, 6,5,3,4,3};

        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tariff"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("User"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Direction"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Type of Baggage"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Order Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (Order order:orders){
            table.addCell(""+order.getOrderId());
            table.addCell(""+order.getTariff().getName());
            table.addCell(""+order.getUser().getSecondName()+" "+order.getUser().getName());
            table.addCell(""+order.getDirection().getStartCity()+" - "+order.getDirection().getFinalCity());
            table.addCell(""+order.getTypeBaggage().getType());
            table.addCell(""+ order.getOrderDate() );
            table.addCell(""+order.getPrice());

        }

       return table;

    }

    private static void addEmptyLine(Paragraph paragraph) {
        for (int i = 0; i < 1; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}