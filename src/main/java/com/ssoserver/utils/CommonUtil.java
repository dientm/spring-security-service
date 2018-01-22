package com.ssoserver.utils;

import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static final Random RANDOM = new SecureRandom();

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

   /* public static String generateSalt() {
        byte[] salt = new byte[Constant.SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }*/
}
