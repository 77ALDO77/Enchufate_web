package ServLets;

import Email.EmailSender;
import Email.PDFGenerator;
import com.itextpdf.text.DocumentException;
import modelo.dao.CustomerDAO;
import modelo.dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;

public class procesarPago extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            String email = customer.getCorreo();
            String customerName = customer.getNombre();
            
            // Obtener datos de la tarjeta
            String cardNumber = request.getParameter("card-number");
            String cardName = request.getParameter("card-name");
            String cardExpiry = request.getParameter("card-expiry");
            String cardCVV = request.getParameter("card-cvv");
            
            // Generar el PDF
            String pdfFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "factura.pdf").toString();
            try {
                PDFGenerator.generateInvoice(pdfFilePath, customerName, "Detalles de la compra...");
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
            
            // Enviar el correo
            String subject = "Factura de Compra";
            String content = "Estimado/a " + customerName + ", adjuntamos la factura de su compra.";
            
            try {
                EmailSender.sendEmail(email, subject, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Redirigir a una página de confirmación o similar
            response.sendRedirect("procesarPago.jsp");
        } else {
            // Si no hay usuario en sesión, redirigir al login
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar pagos y enviar facturas";
    }
}
