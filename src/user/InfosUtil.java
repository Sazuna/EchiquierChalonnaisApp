package user;

public final class InfosUtil {

    public static String decode(String encoded) {
        String decoded = "";
        for (int i = encoded.length() - 1 ; i >= 0; i-- ) {
            char c = (char) (encoded.charAt(i) / i);
            decoded += c;
        }
        return decoded;
    }
    
    public static String encode(String decoded) {
        String encoded = "";
        for (int i = decoded.length() - 1; i >= 0; i--) {
            char c = (char) (decoded.charAt(i) * i);
            encoded += c;
        }
        return encoded;
    }
}
