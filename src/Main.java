import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Scott on 15/5/24.
 */
public class Main {

    public static void main(String[] args) {

        //give RSAImplement instance
        RSAImplement rsaImplement = new RSAImplement();

        //get key pair by 1024 bits
        RSAKeyPair rsaKeyPair = rsaImplement.generateKeyPair(1024);

        //get public key by key pair
        RSAPublicKey rsaPublicKey = rsaImplement.getPublicKey(rsaKeyPair);

        //get private key by key pair
        RSAPrivateKey rsaPrivateKey = rsaImplement.getPrivateKey(rsaKeyPair);

        //plaintext
        String plaintext = "Hello world!!";

        long startEncrypt = System.currentTimeMillis();

        //encrypt plaintext by public key
        byte[] ciphertext = rsaImplement.encrypt(plaintext, rsaPublicKey);

        long endEncrypt = System.currentTimeMillis();
        long EncryptExecutionTime  = endEncrypt - startEncrypt;

        System.out.println("Ciphertext : " + new String(ciphertext));
        System.out.println("Execution time : " + EncryptExecutionTime + "ms");

        System.out.println("-----------------------------------------------");

        long startDecrypt = System.currentTimeMillis();

        //decrypt ciphertext by private key
        plaintext = rsaImplement.decrypt(ciphertext, rsaPrivateKey);

        long endDecrypt = System.currentTimeMillis();
        long decryptExecutionTime  = endDecrypt - startDecrypt;

        System.out.println("Plaintext : " + plaintext);
        System.out.println("Execution time : " +  decryptExecutionTime + "ms");
    }
}
