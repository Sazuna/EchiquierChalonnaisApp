package user;

import java.util.Properties;


public class Infos {
    
    private Properties properties = new Properties();
    
    
    private String NAME;
    
    private String PASSWORD;
    
    private String MAIL;
    
    public Infos() {
        NAME = properties.containsKey("name") ? properties.getProperty("name") : "Echiquier Chalonnais";
        PASSWORD = properties.containsKey("password") ? InfosUtil.decode(properties.getProperty("password")) : "pass";
        MAIL = properties.containsKey("mail") ? properties.getProperty("mail") : "fretelliza@gmail.com";
    }
    
    public void saveInfo() {
        properties.put("name", NAME);
        properties.put("password", InfosUtil.encode(PASSWORD));
        properties.put("mail", MAIL);
    }

    public String getName() {
        return NAME;
    }

    public void setName(String name) {
        NAME = name;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String password) {
        PASSWORD = password;
    }

    public String getMail() {
        return MAIL;
    }

    public void setMail(String mail) {
        MAIL = mail;
    }
}
