package com.dailycircular.dailycircular.constants;

public class SecurityConstants {
    public static final String LOGIN_URL = "/api/auth/login";
    public static final String SIGNUP_URL = "/api/user/register";
    public static final String CIRCULAR_PUBLIC_URLS = "/api/circular/get/*";
    public static final String SECRET_KEY = "SecretKey";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final long TOKEN_DURATION = 2592000000L;
    public static final int PASSWORD_MINIMUM_LENGTH = 8;
    public static final int PASSWORD_MAXIMUM_LENGTH = 30;
    public static final String EMAIL_CONFIRMATION_URL = "/api/user/registrationConfirm";
    public static final int EMAIL_CONFIRMATION_TOKEN_LENGTH = 6;
    public static final String EMAIL_CONFIRMATION_TOKEN_FORMAT = "%06d";
    public static final int EMAIL_CONFIRMATION_TOKEN_GENERATOR_LOWER_LIMIT = 999999;
}
