package itis.semesterwork.infosec.helpers;

import itis.semesterwork.infosec.encryption.RSA;

import java.math.BigInteger;

public class RsaCrack {

    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static String crackE3RSA(byte[] ciphertext1,
                             RSA.RSAPublicKey publicKey1,
                             byte[] ciphertext2,
                             RSA.RSAPublicKey publicKey2,
                             byte[] ciphertext3,
                             RSA.RSAPublicKey publicKey3
    ) {
        BigInteger[] constraints = new BigInteger[]{new BigInteger(ciphertext1), new BigInteger(ciphertext2), new BigInteger(ciphertext3)};
        BigInteger[] mods = new BigInteger[]{publicKey1.getN(), publicKey2.getN(), publicKey3.getN()};
        BigInteger M = publicKey1.getN().multiply(publicKey2.getN().multiply(publicKey3.getN()));
        BigInteger crt = Crt.crt(constraints, mods, M);
        BigInteger messageNumber = cubeRoot(crt);

        return new String(messageNumber.toByteArray());
    }

    private static BigInteger cubeRoot(BigInteger B) {
        BigInteger[] cubique = new BigInteger[2];
        cubique[0] = B.divide(THREE);
        for (int i = 0; i < 500; i++) {
            cubique[1] = ((cubique[0].multiply(TWO)).add(B.divide(cubique[0].pow(2)))).divide(THREE);
            cubique[0] = cubique[1];
        }
        return cubique[0];
    }
}
