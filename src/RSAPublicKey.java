import java.math.BigInteger;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAPublicKey {
    private BigInteger n, e;

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }
}
