package encrypt.method;

import org.springframework.util.Base64Utils;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyUtils {

    private static final String PUBLIC_KEY_NAME = "public_key_generate.key";

    private static final String PRIVATE_KEY_NAME = "private_key_generate.key";

    public static void generateAndSaveKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            byte[] publicKeyBytes = publicKey.getEncoded();
            byte[] privateKeyBytes = privateKey.getEncoded();
            String pubEncode64 = Base64Utils.encodeToString(publicKeyBytes);
            String priEndCode64 = Base64Utils.encodeToString(privateKeyBytes);

            IOUtils.writeFiles(pubEncode64, new File(PUBLIC_KEY_NAME));
            IOUtils.writeFiles(priEndCode64, new File(PRIVATE_KEY_NAME));
        } catch (Exception e) {

        } finally {

        }
    }

    public static PublicKey readPublicKeyFromFiles(String fileName) {
        try {
            String pubEncode64 = IOUtils.readFiles(new File(fileName));
            byte[] encodeByte = Base64Utils.decodeFromString(pubEncode64);
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodeByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);
            return publicKey;
        } catch (Exception e) {

        }
        return null;
    }

    public static PrivateKey readPrivateKeyFromFiles(String fileName) {
        try {
            String pubEncode64 = IOUtils.readFiles(new File(fileName));
            byte[] encodeByte = Base64Utils.decodeFromString(pubEncode64);
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(encodeByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
            return privateKey;
        } catch (Exception e) {

        }
        return null;
    }
}
