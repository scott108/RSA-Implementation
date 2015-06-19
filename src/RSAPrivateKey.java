import java.math.BigInteger;

/**
 * Created by Scott on 15/5/24.
 */
public class RSAPrivateKey {
    private BigInteger d, n;

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }
}
