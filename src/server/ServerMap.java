package server;

import java.util.HashMap;

public class ServerMap{

    private HashMap<String, String[]> map = new HashMap<>();

    public ServerMap() {
        map.put("gmail", new String[] {"smtp.gmail.com", "587"});
        map.put("wanadoo", new String[] {"smtp.orange.fr", "465"});
    }

    public String[] getServer(String mail) {
        String key = mail.split("@")[1];
        key = key.split(".")[0];
        return map.get(key);
    }
}
