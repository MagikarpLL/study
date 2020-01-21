package encrypt.method;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtil {

    private static final String PUBLIC_KEY_NAME = "public_key_generate.key";

    private static final String PRIVATE_KEY_NAME = "private_key_generate.key";

    public static void main(String[] args){
        PrivateKey privateKey = KeyUtils.readPrivateKeyFromFiles(PRIVATE_KEY_NAME);
        PublicKey publicKey = KeyUtils.readPublicKeyFromFiles(PUBLIC_KEY_NAME);

        String origin = "leizhen1996";
        System.out.println(origin);
        String encodedPassword = encodePassword(origin, publicKey);
        System.out.println(encodedPassword);
        String decodePassword = decodePassword(encodedPassword, privateKey);
        System.out.println(decodePassword);
    }

    public static String encodePassword(String password, PublicKey publicKey){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherData = cipher.doFinal(password.getBytes());
            return Base64Utils.encodeToString(cipherData);
        }catch (Exception e){

        }
        return null;
    }

    public static String decodePassword(String password, PrivateKey privateKey){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decodePass = Base64Utils.decodeFromString(password);
            byte[] cipherData = cipher.doFinal(decodePass);
            return new String(cipherData);
        }catch (Exception e){

        }
        return null;
    }


}
