package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import server.ServerMap;
import user.Infos;

/*
 * https://www.google.com/settings/security/lesssecureapps
 */


public class SendMail {
    
    Infos infos = new Infos();
    String[] socket = new ServerMap().getServer(infos.getMail());

    String name = "Echiquier Chalonnais";
    String subject = "Envoi email";
    String body = "Ceci est le contenu du mail";
    String from = "fretelliza@gmail.com";
    String to = "fretelliza@gmail.com";
    
    public static void main(String[] args) {
        
        String host = "smtp.gmail.com";
        String user = "user";
        String pass = "pass";
        String to = "to";
        String from = "from";
        String subject = "Subject";
        String text = "Text";
        
        boolean sessionDebug = false;
        
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        
        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(sessionDebug);

        Message msg = new MimeMessage(mailSession);
        try {
            msg.setFrom(new InternetAddress(from));
            InternetAddress address = (new InternetAddress(to));
            msg.setRecipient(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg,  msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


