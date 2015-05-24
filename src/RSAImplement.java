import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAImplement {

    private RSAImplement rsa;
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
    private BigInteger e, N;
    private BigInteger d;
    private String plaintext, ciphertext;

    public RSAImplement() {

    }

    public synchronized RSAKeyPair generateKeyPair(int bitLength) {
        SecureRandom seed = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, seed);
        BigInteger q = new BigInteger(bitLength / 2, 100, seed);
        N = p.multiply(q);
        BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = new BigInteger("3");
        while (fiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(fiN);


        RSAKeyPair rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.setN(N);
        rsaKeyPair.setE(e);
        rsaKeyPair.setD(d);

        return rsaKeyPair;

    }

    public synchronized RSAPublicKey getPublicKey(RSAKeyPair rsaKeyPair) {
        rsaPublicKey = new RSAPublicKey();
        rsaPublicKey.setN(rsaKeyPair.getN());
        rsaPublicKey.setE(rsaKeyPair.getE());
        return rsaPublicKey;
    }

    public synchronized RSAPrivateKey getPrivateKey(RSAKeyPair rsaKeyPair) {
        rsaPrivateKey = new RSAPrivateKey();
        rsaPrivateKey.setD(rsaKeyPair.getD());
        rsaPrivateKey.setN(rsaKeyPair.getN());
        return rsaPrivateKey;
    }

    public synchronized String encrypt(String plaintext, RSAPublicKey rsaPublicKey) {
        ciphertext = (new BigInteger(plaintext.getBytes())).modPow(rsaPublicKey.getE(), rsaPublicKey.getN()).toString();
        return ciphertext;
    }

    public synchronized String decrypt(String ciphertext, RSAPrivateKey rsaPrivateKey) {
        plaintext = new String(new BigInteger(ciphertext).modPow(rsaPrivateKey.getD(), rsaPrivateKey.getN()).toByteArray());
        return plaintext;
    }

}
