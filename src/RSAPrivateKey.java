import java.math.BigInteger;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAPrivateKey {
    private BigInteger privateExponent, modulus;

    public BigInteger getPrivateExponent() {
        return privateExponent;
    }

    public void setPrivateExponent(BigInteger privateExponent) {
        this.privateExponent = privateExponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }
}
