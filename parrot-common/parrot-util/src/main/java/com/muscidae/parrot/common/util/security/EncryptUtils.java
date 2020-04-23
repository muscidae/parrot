package com.muscidae.parrot.common.util.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author muscidae
 * @date 2019/5/27 16:46
 * @description 哈希加密/解密工具类
 */
public final class EncryptUtils {

    private EncryptUtils(){ }

    public String sortAndJoint(final String... plainText){
        Arrays.sort(plainText);
        StringBuilder sb = new StringBuilder();
        for (String str : plainText)
            sb.append(str);
        return sb.toString();
    }

    //region Hash加密算法
    public String sha1(final String plainText, final byte[] salt) throws NoSuchAlgorithmException {
        return hashEncrypt(plainText, salt, "SHA-1");
    }

    public String sha256(final String plainText, final byte[] salt) throws NoSuchAlgorithmException {
        return hashEncrypt(plainText, salt, "SHA-256");
    }

    public String sha512(final String plainText, final byte[] salt) throws NoSuchAlgorithmException {
        return hashEncrypt(plainText, salt, "SHA-512");
    }

    public String md5(final String plainText, final byte[] salt) throws NoSuchAlgorithmException {
        return hashEncrypt(plainText, salt, "MD5");
    }

    /**
     * @author muscidae
     * @date 2019/5/27 17:00
     * @description 当向加密算法
     * @param plainText 待加密明文
     * @param salt 盐
     * @param encryptType 加密类型
     * @return java.lang.String 密文
     */
    private String hashEncrypt(final String plainText, final byte[] salt, final String encryptType) throws NoSuchAlgorithmException {
        if (null == plainText || 0 == plainText.length()) return null;
        MessageDigest digest = MessageDigest.getInstance(encryptType);
        if (null != salt && 0 != salt.length) {
            digest.reset();
            digest.update(salt);
        }
        StringBuilder cipherText = new StringBuilder();
        for (byte b : digest.digest(plainText.getBytes())) {
            String hex = Integer.toHexString(0xff & b);
            if (1 == hex.length())
                cipherText.append('0');
            cipherText.append(hex);
        }
        return cipherText.toString();
    }
    //endregion


    public byte[] aesCbcNoPaddingEncrypt(final byte[] plainText, final byte[] secretKey,
                                                final byte[] iv, final int offset, final int len)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return symmetricalEncrypt(plainText, secretKey, "AES", "AES/CBC/NoPadding",
                iv, offset, len, Cipher.ENCRYPT_MODE);
    }

    public byte[] aesCbcNoPaddingEncrypt(final byte[] plainText, final byte[] secretKey, final byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return symmetricalEncrypt(plainText, secretKey, "AES", "AES/CBC/NoPadding",
                iv, 0, iv.length, Cipher.ENCRYPT_MODE);
    }


    public byte[] aesCbcNoPaddingDecrypt(final byte[] plainText, final byte[] secretKey,
                                                final byte[] iv, final int offset, final int len)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return symmetricalEncrypt(plainText, secretKey, "AES", "AES/CBC/NoPadding",
                iv, offset, len, Cipher.DECRYPT_MODE);
    }

    public byte[] aesCbcNoPaddingDecrypt(final byte[] plainText, final byte[] secretKey, final byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return symmetricalEncrypt(plainText, secretKey, "AES", "AES/CBC/NoPadding",
                iv, 0, iv.length, Cipher.DECRYPT_MODE);
    }


    /**
     * @author muscidae
     * @date 2019/10/18 11:28
     * @description 对称加密/解密
     * @param plainText 明文
	 * @param secretKey 秘钥
	 * @param encryptType 加密类型
	 * @param encryptSpec 加密规格
	 * @param iv IvParameterSpec
	 * @param offset 偏移量
	 * @param len iv 长度
	 * @param mode Cipher.Mode 加/解密模型
     * @return byte[] 密文字节
     */
    private byte[] symmetricalEncrypt(byte[] plainText, byte[] secretKey, String encryptType,
                                  String encryptSpec, byte[] iv, int offset, int len, int mode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        if (null == plainText || 0 == plainText.length ) return null;
        Cipher cipher = Cipher.getInstance(encryptSpec);
        cipher.init(mode, new SecretKeySpec(secretKey, encryptType), new IvParameterSpec(iv, offset, len) );
        return cipher.doFinal(plainText);
    }

    public enum Singleton{
        INSTANCE;
        private EncryptUtils encryptUtils;
        Singleton(){ encryptUtils = new EncryptUtils(); }
        public EncryptUtils newInstance(){ return encryptUtils; }
    }

}
