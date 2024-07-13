package Email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String to, String subject, String content) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rodrigopro17371@hotmail.com", "123");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rodrigopro17371@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rodrigopro17371@hotmail.com"));
            message.setSubject("Asunto del correo");
            message.setText("Hola, este es un correo de prueba.");

            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        session.setDebug(true);
    }
}
