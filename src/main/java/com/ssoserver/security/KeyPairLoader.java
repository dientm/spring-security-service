package com.ssoserver.security;

import com.ssoserver.auth.OAuth2API;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairLoader.class);

    private static KeyPairLoader instance;
    private static KeyPair keyPair;

    public KeyPairLoader() throws Exception {
        loadKeyPair();
    }

    public static KeyPairLoader getInstance() throws Exception {
        if (null == instance) {
            instance = new KeyPairLoader();
        }
        return instance;
    }

    private static String keyPassword = "123456";
    private static String keyAlias = "identity";

    private static KeyPair loadKeyPair() throws Exception {
        try {

            Resource resource = new ClassPathResource("cer.jks");
            InputStream ins = resource.getInputStream();
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(ins, keyPassword.toCharArray());
            KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(keyPassword.toCharArray());

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, passwordProtection);

            java.security.cert.Certificate cert = keyStore.getCertificate(keyAlias);
            PublicKey publicKey = cert.getPublicKey();
            PrivateKey privateKey = privateKeyEntry.getPrivateKey();

            keyPair =  new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            LOGGER.error("Exception while load KeyPair from resource. Please check the certificate / password/ key alias.");
            LOGGER.error("Application will be shutdown");
            throw new Exception();
        }
        return keyPair;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }
}
