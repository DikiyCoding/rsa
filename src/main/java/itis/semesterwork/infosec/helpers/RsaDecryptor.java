package itis.semesterwork.infosec.helpers;

import itis.semesterwork.infosec.encryption.RSA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RsaDecryptor {

    private final RSA rsa = new RSA();
    private final Set<Integer> used = new HashSet<Integer>();

    public byte[] encrypt(byte[] plaintext) {
        return rsa.encrypt(plaintext);
    }

    public byte[] decrypt(byte[] ciphertext) {
        int hashCode = Arrays.hashCode(ciphertext);

        if (used.contains(hashCode))
            throw new IllegalAccessError("Message was already decrypted.");

        used.add(hashCode);
        return rsa.decrypt(ciphertext);
    }

    public RSA.RSAPublicKey getPublicKey() {
        return rsa.getPublicKey();
    }
}
