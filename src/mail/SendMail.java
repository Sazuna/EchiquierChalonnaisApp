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
    private String user;
    private String from;
    private String to;
    private String host;
    private String port;
    private String name;
    private String pass;
    
    public SendMail() {
        Infos infos = new Infos();
        user = infos.getMail();
        from = infos.getMail();
        to = "fretelliza@gmail.com";
        String[] socket = new ServerMap().getServer(from);
        host = socket[0];
        port = socket[1];
        name = infos.getName();
        pass = infos.getPassword();
    }
    
    public void sendMail() {
        String subject = "Subject";
        String text = "Text";
        
        boolean sessionDebug = false;
        
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
       // props.put("mail.smtp.port", "587");
        props.put("mail.smtp.port", port);
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


