package Email;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFGenerator {
    public static void generateInvoice(String filePath, String customerName, String details) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();
        document.add(new Paragraph("Factura de Compra"));
        document.add(new Paragraph("Cliente: " + customerName));
        document.add(new Paragraph("Detalles: " + details));
        document.close();
    }
}


