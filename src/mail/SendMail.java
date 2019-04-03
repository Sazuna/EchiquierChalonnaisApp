package mail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import user.Infos;

/*
 * https://www.google.com/settings/security/lesssecureapps
 */


public class SendMail {

    /*
    Infos infos = new Infos();
    String name = "Echiquier Chalonnais";
    String subject = "Envoi email";
    String body = "Ceci est le contenu du mail";
    String from = "fretelliza@gmail.com";
    String to = "fretelliza@gmail.com";


    public void sendMail() {
        Properties properties = new Properties();
        properties.put("mail" , from);
        Session session  = Session.getDefaultInstance( properties , null);
        Message msg = new MimeMessage(session);
        try {
            //Essai HTML
            String texte = "<H1>bonjour</H1><a href=\"mailto:moi@moi.fr\">mail</a>";
            msg.setContent(texte, "text/html");


            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
            msg.setSubject(subject);
            //msg.setText(body);
            msg.writeTo(System.out);
            Transport trans = session.getTransport();
            trans.connect();

            Transport.send(msg);  
        }  catch(Exception e) {
            e.printStackTrace();
        }
    }


    private final static String MAILER_VERSION = "Java";
    public static boolean envoyerMailSMTP(String serveur, boolean debug) {
        boolean result = false;
        try {
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", serveur);
            Session session = Session.getDefaultInstance(prop,null);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fretelliza@gmail.com"));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress("fretelliza@gmail.com");
            message.setRecipients(Message.RecipientType.TO,internetAddresses);
            message.setSubject("Test");
            message.setText("test mail");
            message.setHeader("X-Mailer", MAILER_VERSION);
            message.setSentDate(new Date());
            message.writeTo(System.out);
            session.setDebug(debug);
            Transport.send(message);
            result = true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    */



    public static void main(String[] args) {

       // envoyerMailSMTP("10.39.5.39",true);
       // new SendMail().sendMail();
        
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


