package com.ssoserver.utils;

import com.ssoserver.model.entity.AppRole;
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

    /**
     * Validate {@code str} not null, not empty and matches {@code regex}
     *
     * @param str
     * @param regex
     * @return {@code str} matches {@code regex}
     * @throws IllegalArgumentException if {@code regex} is null
     */
    public static boolean isValidPattern(String str, String regex) {

        if (regex == null) {
            throw new IllegalArgumentException("Regex pattern must not be null");
        }

        if (!StringUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }

        return false;
    }

    /**
     *
     * @param number
     * @param min
     * @param max
     * @return
     * @throws IllegalArgumentException if (number == null || (min == null &&
     * max == null) || (max != null && min != null && max.doubleValue() <
     * min.doubleValue()))
     */
    public static boolean isBetweenRange(Number number, Number min, Number max) {

        if (number == null || (min == null && max == null) || (max != null && min != null && max.doubleValue() < min.doubleValue())) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        return !((min == null && number.doubleValue() > max.doubleValue())
                || (max == null && number.doubleValue() < min.doubleValue())
                || (min != null && max != null && (number.doubleValue() < min.doubleValue() || number.doubleValue() > max.doubleValue())));
    }

    /**
     * Check {@code userId} matches {@link Constant.SYSTEM_ADMIN_ID}
     *
     * @param userId
     * @return
     */
    @Deprecated
    public static boolean isSystemAdmin(String userId) {
        return (userId != null && userId.equals(Constant.SYSTEM_ADMIN_ID));
    }

    /**
     * Give a list of AppRole and return true if the list of AppRole are
     * {@code null} or {@code empty} or have an element with
     * {@code enableAppAdmin} field is true
     *
     * @param appRoles
     * @return
     */
    @Deprecated
    public static boolean isAdminConsole(List<AppRole> appRoles) {

        if (appRoles != null && !appRoles.isEmpty()) {
            if (appRoles.stream().anyMatch((appRole) -> (appRole.isEnableAppAdmin()))) {
                return true;
            }
        }

        return (appRoles == null || appRoles.isEmpty());
    }

    /**
     * Use to compare length of {@code str} is between min & max value
     *
     * @param str
     * @param min must be greater than or equal to 0
     * @param max must be greater than or equal to the min value
     * @return true if and only if {@code str} not null and between the min &
     * max value
     * @throws IllegalArgumentException if the min value greater than 0 or the
     * max value greater than the min value
     */
    public static boolean isLengthBetween(String str, int min, int max) {

        if (min < 0 || max < min) {
            throw new IllegalArgumentException("The min value must be greater than or equal to 0 and less than the max value");
        }

        return (str != null && str.length() > 0 && str.length() < max);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateSalt() {
        byte[] salt = new byte[Constant.SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
