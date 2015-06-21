import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAImplement {

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
    private BigInteger publicExponent, modulus;
    private BigInteger privateExponent;
    private int bitLength;

    public RSAKeyPair generateKeyPair(int bitLength) {
        SecureRandom seed = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, seed);
        BigInteger q = new BigInteger(bitLength / 2, 100, seed);
        this.bitLength = bitLength;

        modulus = p.multiply(q);

        BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        /*publicExponent = new BigInteger("3");
        while (fiN.gcd(publicExponent).intValue() > 1) {
            publicExponent = publicExponent.add(new BigInteger("2"));
        }*/
        publicExponent = new BigInteger("65537");
        privateExponent = publicExponent.modInverse(fiN);


        RSAKeyPair rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.setModulus(modulus);
        rsaKeyPair.setPublicExponent(publicExponent);
        rsaKeyPair.setPrivateExponent(privateExponent);

        System.out.println("Modulus = " + modulus);
        System.out.println("publicExponent = " + publicExponent);
        System.out.println("privateExponent = " + privateExponent);

        return rsaKeyPair;

    }

    public RSAPublicKey getPublicKey(RSAKeyPair rsaKeyPair) {
        rsaPublicKey = new RSAPublicKey();
        rsaPublicKey.setModulus(rsaKeyPair.getModulus());
        rsaPublicKey.setPublicExponent(rsaKeyPair.getPublicExponent());
        return rsaPublicKey;
    }

    public RSAPrivateKey getPrivateKey(RSAKeyPair rsaKeyPair) {
        rsaPrivateKey = new RSAPrivateKey();
        rsaPrivateKey.setPrivateExponent(rsaKeyPair.getPrivateExponent());
        rsaPrivateKey.setModulus(rsaKeyPair.getModulus());
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

        return (new BigInteger(1, encryptionBytes)).modPow(rsaPublicKey.getPublicExponent(), rsaPublicKey.getModulus()).toByteArray();
    }

    public String decrypt(byte[] ciphertext, RSAPrivateKey rsaPrivateKey) {

        // Decrypt
        byte[] decryptedBytes = (new BigInteger(1, ciphertext)).modPow(rsaPrivateKey.getPrivateExponent(), rsaPrivateKey.getModulus()).toByteArray();

        // Extract msg
        int msgStart = 0;
        do {
        }while(decryptedBytes[msgStart++] != 0);

        byte finalBytes[] = new byte[decryptedBytes.length - msgStart];
        System.arraycopy(decryptedBytes, msgStart, finalBytes, 0, finalBytes.length);

        return new String(finalBytes);
    }

}
