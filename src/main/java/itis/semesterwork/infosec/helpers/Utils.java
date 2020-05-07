package itis.semesterwork.infosec.helpers;

import itis.semesterwork.infosec.encryption.RSA;

import java.math.BigInteger;

public class Utils {

    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static byte[] recoverMessage(RsaDecryptor decriptor, byte[] ciphertext) {
        RSA.RSAPublicKey publicKey = decriptor.getPublicKey();
        BigInteger N = publicKey.getN();
        BigInteger e = publicKey.getE();

        BigInteger cipher = new BigInteger(ciphertext);
        BigInteger modifiedCipher = cipher.multiply(TWO.modPow(e, N)).mod(N);

        byte[] modifiedPlaintext = decriptor.decrypt(modifiedCipher.toByteArray());

        BigInteger modified = new BigInteger(modifiedPlaintext);
        BigInteger plain = half(modified, N);

        return plain.toByteArray();
    }

    private static BigInteger half(BigInteger number, BigInteger modulus) {
        return number.multiply(TWO.modInverse(modulus)).mod(modulus);
    }
}
