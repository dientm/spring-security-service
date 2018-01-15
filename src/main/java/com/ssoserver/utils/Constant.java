package com.ssoserver.utils;

public interface Constant {

    String SYSTEM_ADMIN_ID = "th1s1ssyst3m4dm1n0d3l3t3th1sus3r";

    /**
     * ****************************
     * define AIPs path
     */
    String API_PREFIX = "/api/v1";
    // auth APIs
    String AUTH_API = API_PREFIX + "/auth";
    String CONSOLE_LOGIN_API = "/console/login";
    String USER_LOGIN_API = "/login";
    String LOGOUT_API = "/logout";
    String FORGOT_PASSWORD = "/password/forgot";
    String RESET_PASSWORD = "/password/reset";
    // client
    String CLIENT_API = API_PREFIX + "/client";
    // user API
    String USER_API = API_PREFIX + "/users";
    String PROFILE = "/profile";
    String MY_PROFILE = "/my_profile";
    String MY_PROFILE_WITH_ID = "/my_profile/{id}";
    String CHANGE_PASSWORD = "/change_password";
    String USER_FORGOT_PASSWORD = "/user/password/forgot";
    String USER_RESET_PASSWORD = "/user/password/reset";
    String USER_REGISTER = "/user/register";
    String ADD_USER = "/add_user";
    String ADMIN_CONSOLE_REGISTER = "/console/register";
    // app API
    String APP_API = API_PREFIX + "/apps";
    // app role API
    String APP_ROLE_API = API_PREFIX + "/roles";
    // app user detail API
    String APP_USER_DETAIL_API = API_PREFIX + "/userdetails";
    // statistical
    String STATISTIC = API_PREFIX + "/statistic";

    // oauth2 APIs
    String OAUTH2_API = API_PREFIX + "/oauth2";
    String OAUTH2_AUTHORIZE = "/authorize";
    String OAUTH2_TOKEN = "/token";
    String OAUTH2_REFRESH_TOKEN = "/refreshtoken";

    /**
     * ****************************
     * regular expression patterns
     */
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String USERNAME_PATTERN = "^[a-z0-9_-\\u3000-\\u303F\\u3040-\\u309F\\u30A0-\\u30FF\\uFF00-\\uFFEF\\u4E00-\\u9FAF\\u2605-\\u2606\\u2190-\\u2195]{3,45}$";
    String NAME_PATTERN = "^[a-zA-Z \\u3000-\\u303F\\u3040-\\u309F\\u30A0-\\u30FF\\uFF00-\\uFFEF\\u4E00-\\u9FAF\\u2605-\\u2606\\u2190-\\u2195]{3,45}$";
    String PHONE_PATTERN = "^\\+?[0-9]\\d{1,16}$";  // E.164 format
    String PASSWORD_PATTERN = "^.{6,}$";
    String APP_NAME_PATTERN = "^[a-zA-Z0-9 _-\\u3000-\\u303F\\u3040-\\u309F\\u30A0-\\u30FF\\uFF00-\\uFFEF\\u4E00-\\u9FAF\\u2605-\\u2606\\u2190-\\u2195]{3,45}$";
    String DOMAIN_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9-_]{0,61}[a-zA-Z0-9]{0,1}\\.([a-zA-Z]{1,6}|[a-zA-Z0-9-]{1,30}\\.[a-zA-Z]{2,3})$";
    //String SERVER_KEY_PATTERN = "^[a-zA-Z0-9]{0,255}$";
    String SERVER_KEY_PATTERN = ".*";
    String URL_PATTERN = "^https?:\\/\\/.*";
    String ROLE_NAME_PATTERN = "^[A-Za-z0-9\\u3000-\\u303F\\u3040-\\u309F\\u30A0-\\u30FF\\uFF00-\\uFFEF\\u4E00-\\u9FAF\\u2605-\\u2606\\u2190-\\u2195]{3,45}$";
    String ROLE_DESC_PATTERN = "^[\\s\\w.,-:\\u3000-\\u303F\\u3040-\\u309F\\u30A0-\\u30FF\\uFF00-\\uFFEF\\u4E00-\\u9FAF\\u2605-\\u2606\\u2190-\\u2195]{0,255}$";

    int DEFAULT_EXPIRE_TIME = 3600000; // milliseconds (1 hour)
    int CRON_JOB_TOKEN_DAYS = 30;
    int CRON_JOB_MESSAGE_DAYS = 30;
    int MAX_EXPIRE_TIME = 86400000; // milliseconds (1 day)
    String DEFAULT_EXPIRE_TIME_STR = "3600000"; // milliseconds (1 hour)
    int SALT_LENGTH = 6;
    String HEADER_TOKEN = "X-Access-Token";
    String REDIS_SESSION_KEY = "session";

    int CORE_POOL_SIZE = 5;
    int MAX_POOL_SIZE = 10;
    int QUEUE_CAPACITY = 25;

    // schedule time
    long CLEANUP_TOKEN_TIME = 86400000; // 1 day

    /**
     * ****************************
     * Model's status
     */
    enum Status {

        INACTIVE(0),
        ACTIVE(1);

        private final int value;

        private Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * *****************************
     * Default DefaultAppRole
     */
    enum DefaultAppRole {

        AppAdminRole("Admin", "Admin role", true, 0),
        AppUserRole("User", "User role", false, 1);

        private final String name;
        private final String desc;
        private final boolean enableAppAdmin;
        private final int level;

        DefaultAppRole(String name, String desc, boolean enableAppAdmin, int level) {
            this.name = name;
            this.desc = desc;
            this.enableAppAdmin = enableAppAdmin;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public boolean isEnableAppAdmin() {
            return enableAppAdmin;
        }

        public int getLevel() {
            return level;
        }
    }

    enum ParamError {

        MISSING_USERNAME_AND_EMAIL("accountName", "Missing both user name and email address"),
        USER_NAME("userName", "Invalid user name"),
        EMAIL_ADDRESS("email", "Invalid email address"),
        PASSWORD("passwordHash", "Invalid password hash"),
        PHONE_NUMBER("phone", "Invalid phone number"),
        FIRST_NAME("firstName", "Invalid first name"),
        LAST_NAME("lastName", "Invalid last name"),
        APP_NAME("appName", "Invalid app name"),
        APP_DOMAIN("appDomain", "Invalid app domain"),
        SERVER_KEY("serverKey", "Invalid server key"),
        TOKEN_EXPIRE_DURATION("tokenExpireDuration", "Invalid token expiry duration"),
        REDIRECT_URL("redirectUrl", "Invalid redirect URL"),
        ROLE_NAME("roleName", "Invalid role name"),
        ROLE_DESC("roleDescription", "Invalid role description");

        private final String name;
        private final String desc;

        ParamError(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
    }
    
    enum ResetCodeType {

        USER_CLIENT(1), ADMIN_SSO(2);

        private final int value;

        private ResetCodeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
