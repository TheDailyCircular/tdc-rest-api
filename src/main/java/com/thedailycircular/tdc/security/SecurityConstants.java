package com.thedailycircular.tdc.security;

public class SecurityConstants {
    public static final String LOGIN_URL = "/api/auth/login";
    public static final String SIGNUP_URL = "/api/user/register";
    public static final String CIRCULAR_PUBLIC_URLS = "/api/circular/get/*";
    public static final String SECRET_KEY = "SecretKey";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String REQUEST_HEADER_NAME = "Authorization";
    public static final long TOKEN_DURATION = 2592000000L;
}
