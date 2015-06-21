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
    private int bitLength;

    public RSAKeyPair generateKeyPair(int bitLength) {
        SecureRandom seed = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, seed);
        BigInteger q = new BigInteger(bitLength / 2, 100, seed);
        this.bitLength = bitLength;

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

        // Convert to bytes
        byte[] plainBytes = plaintext.getBytes();

        byte[] encryptionBytes = new byte[bitLength / 8];

        encryptionBytes[0] = 0; // Leading 0
        encryptionBytes[1] = 1; // Block type

        // Padding String
        int paddingEnd = (bitLength / 8) - plainBytes.length - 2;
        for (int i = 2; i < paddingEnd; i++) {
            encryptionBytes[i] = (byte) 0xff;
        }

        // Actual data
        System.arraycopy(plainBytes, 0, encryptionBytes, paddingEnd + 1, plainBytes.length);

        return (new BigInteger(1, encryptionBytes)).modPow(rsaPublicKey.getE(), rsaPublicKey.getN()).toByteArray();
    }

    public String decrypt(byte[] ciphertext, RSAPrivateKey rsaPrivateKey) {

        // Decrypt
        byte[] decryptedBytes = (new BigInteger(1, ciphertext)).modPow(rsaPrivateKey.getD(), rsaPrivateKey.getN()).toByteArray();

        // Extract msg
        int msgStart = 0;
        do {
        }while(decryptedBytes[msgStart++] != 0);

        byte finalBytes[] = new byte[decryptedBytes.length - msgStart];
        System.arraycopy(decryptedBytes, msgStart, finalBytes, 0, finalBytes.length);

        return new String(finalBytes);
    }

}
