import java.math.BigInteger;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAPublicKey {
    private BigInteger modulus, publicExponent;

    public BigInteger getModulus() {
        return modulus;
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }

    public BigInteger getPublicExponent() {
        return publicExponent;
    }

    public void setPublicExponent(BigInteger publicExponent) {
        this.publicExponent = publicExponent;
    }
}
