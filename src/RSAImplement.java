import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAImplement {

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
    private BigInteger e, N;
    private BigInteger d;

    public RSAKeyPair generateKeyPair(int bitLength) {
        SecureRandom seed = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, seed);
        BigInteger q = new BigInteger(bitLength / 2, 100, seed);

        System.out.println("P = " + p);
        System.out.println("Q = " + q);

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

    public RSAPublicKey getPublicKey(RSAKeyPair rsaKeyPair) {
        rsaPublicKey = new RSAPublicKey();
        rsaPublicKey.setN(rsaKeyPair.getN());
        rsaPublicKey.setE(rsaKeyPair.getE());
        return rsaPublicKey;
    }

    public RSAPrivateKey getPrivateKey(RSAKeyPair rsaKeyPair) {
        rsaPrivateKey = new RSAPrivateKey();
        rsaPrivateKey.setD(rsaKeyPair.getD());
        rsaPrivateKey.setN(rsaKeyPair.getN());
        return rsaPrivateKey;
    }

    public byte[] encrypt(String plaintext, RSAPublicKey rsaPublicKey) {
        return (new BigInteger(plaintext.getBytes())).modPow(rsaPublicKey.getE(), rsaPublicKey.getN()).toByteArray();
    }

    public String decrypt(byte[] ciphertext, RSAPrivateKey rsaPrivateKey) {
        return new String(new BigInteger(ciphertext).modPow(rsaPrivateKey.getD(), rsaPrivateKey.getN()).toByteArray());
    }

}
