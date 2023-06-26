package br.com.ffcs.javacleanarchitecture.infra.indicacao;

import br.com.ffcs.javacleanarchitecture.aplicacao.indicacao.EnviarEmailIndicacao;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EnviarEmailIndicacaoComJavaMail implements EnviarEmailIndicacao {
    @Override
    public void enviarPara(Aluno indicado) {
        // SMTP server configuration
        String smtpHost = "your_smtp_host";
        int smtpPort = 587;
        String username = "your_username";
        String password = "your_password";

        // Sender and recipient email addresses
        String senderEmail = "sender@example.com";
        String recipientEmail = "recipient@example.com";

        // Email subject and content
        String subject = "JavaMail API Test";
        String content = "This is a test email sent using JavaMail API.";

        // Create properties for the JavaMail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
