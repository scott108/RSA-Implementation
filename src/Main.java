
/**
 * Created by Scott on 15/5/24.
 */
public class Main {


    public static void main(String[] args) {
        RSAImplement rsaImplement = new RSAImplement();
        RSAKeyPair rsaKeyPair = rsaImplement.generateKeyPair(1024);
        RSAPublicKey rsaPublicKey = rsaImplement.getPublicKey(rsaKeyPair);
        RSAPrivateKey rsaPrivateKey = rsaImplement.getPrivateKey(rsaKeyPair);

        String plaintext = "Hello world";
        String ciphertext = rsaImplement.encrypt(plaintext, rsaPublicKey);
        System.out.println(ciphertext);

        plaintext = rsaImplement.decrypt(ciphertext, rsaPrivateKey);
        System.out.println(plaintext);

    }
}
