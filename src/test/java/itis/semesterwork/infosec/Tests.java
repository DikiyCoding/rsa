package itis.semesterwork.infosec;

import itis.semesterwork.infosec.encryption.RSA;
import itis.semesterwork.infosec.helpers.RsaCrack;
import itis.semesterwork.infosec.helpers.RsaDecryptor;
import itis.semesterwork.infosec.helpers.Utils;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void ex39() {
        RSA rsa = new RSA();
        String message = "password";
        byte[] ciphertext = rsa.encrypt(message.getBytes());
        byte[] decrypt = rsa.decrypt(ciphertext);

        assertEquals(message, new String(decrypt));
    }

    @Test
    public void ex40() {
        String message = "broadcasted secret";

        RSA rsa1 = new RSA();
        byte[] ciphertext1 = rsa1.encrypt(message.getBytes());
        RSA.RSAPublicKey publicKey1 = rsa1.getPublicKey();

        RSA rsa2 = new RSA();
        byte[] ciphertext2 = rsa2.encrypt(message.getBytes());
        RSA.RSAPublicKey publicKey2 = rsa2.getPublicKey();

        RSA rsa3 = new RSA();
        byte[] ciphertext3 = rsa3.encrypt(message.getBytes());
        RSA.RSAPublicKey publicKey3 = rsa3.getPublicKey();

        String cracked = RsaCrack.crackE3RSA(ciphertext1, publicKey1, ciphertext2, publicKey2, ciphertext3, publicKey3);
        assertEquals(message, cracked);
    }

    @Test
    public void ex41() {
        RsaDecryptor decriptor = new RsaDecryptor();
        String message = "secret";
        byte[] ciphertext = decriptor.encrypt(message.getBytes());
        decriptor.decrypt(ciphertext);
        byte[] recoveredMessage = Utils.recoverMessage(decriptor, ciphertext);
        assertArrayEquals(message.getBytes(), recoveredMessage);
    }
}
