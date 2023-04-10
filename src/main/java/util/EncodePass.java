package util;

import java.security.MessageDigest;
import java.util.Base64;

public class EncodePass {
    public static String encode(String password) {
        String salt = ";'[]ikjsammeask iwi(*&^";
        String res = "";
        password = password + salt;
        try{
            //SHA-1 algorithm
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] thedigest = md.digest(bytesOfMessage);
            res = Base64.getEncoder().encodeToString(thedigest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
