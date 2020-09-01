package com.thedailycircular.tdc.security;

public class SecurityConstants {
    public static final String APP_BASE_URL = "http://localhost:8080";
    public static final String LOGIN_URL = "/api/auth/login";
    public static final String SIGNUP_URL = "/api/user/register";
    public static final String CIRCULAR_PUBLIC_URLS = "/api/circular/get/*";
    public static final String SECRET_KEY = "SecretKey";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String REQUEST_HEADER_NAME = "Authorization";
    public static final long TOKEN_DURATION = 2592000000L;
    public static final int PASSWORD_MINIMUM_LENGTH = 8;
    public static final long EMAIL_CONFIRMATION_TOKEN_DURATION = 10080000;
    public static final String EMAIL_CONFIRMATION_SENDER = "circular.daily@gmail.com";
    public static final String EMAIL_CONFIRMATION_URL = "/api/user/registrationConfirm";
    public static final String EMAIL_CONFIRMATION_SUBJECT = "Daily Circular Email Confirmation";
}
