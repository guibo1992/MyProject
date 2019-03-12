package com.gb.chrom.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author Summer
 *         <p>
 *         2017年7月4日
 */
public class PasswordHash {

    private static final int DEFAULT_ITERATIONS = 0x2;

    private static final String PLAINTEXT_SEPARATOR = "^";

    private String credentialsSalt;

    public static PasswordHash getInstance() {
        return new PasswordHash();
    }

    public String encryptToHex(String plaintext) {
        return getSimpleHash(plaintext).toHex();
    }

    public String encryptToHex(String plaintext, String prefix) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix).append(PLAINTEXT_SEPARATOR).append(plaintext);
        //System.out.println("==================================");
        //System.out.println(buffer.append(prefix).append(PLAINTEXT_SEPARATOR).append(plaintext));
        //System.out.println(getSimpleHash(buffer.toString()).toHex());
        return getSimpleHash(buffer.toString()).toHex();
        
    }

    public String encryptToBase64(String plaintext) {
        return getSimpleHash(plaintext).toBase64();
    }

    //密码加密
    public SimpleHash getSimpleHash(String plaintext) {
        String algorithmName = Sha256Hash.ALGORITHM_NAME;
        if (StringUtils.isBlank(credentialsSalt)) {
            credentialsSalt = new SecureRandomNumberGenerator().nextBytes().toHex();           
        }

        ByteSource byteSalt = ByteSource.Util.bytes(credentialsSalt);
        //System.out.println("==========================");
        //System.out.println(credentialsSalt);   密码加密密钥
        //System.out.println("==========================");
        //System.out.println(byteSalt);
        //System.out.println("==========================");
        //System.out.println(algorithmName);
        //System.out.println("==========================");
        //System.out.println(plaintext);
        //System.out.println("==========================");
        //System.out.println(DEFAULT_ITERATIONS);
        return new SimpleHash(algorithmName, plaintext, byteSalt, DEFAULT_ITERATIONS);
    }
    
    /**
     * @return the {@link #credentialsSalt}
     */
    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    /**
     * @param credentialsSalt the {@link #credentialsSalt} to set
     */
    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public static void main(String[] args) throws Exception {
//        PasswordHash ph = PasswordHash.getInstance();
//        System.out.println(ph.encryptToHex("", "test001"));
//        System.out.println(ph.getCredentialsSalt());
        
        
        System.out.println(ConfigTools.encrypt("Chromsys123"));
    }

}
