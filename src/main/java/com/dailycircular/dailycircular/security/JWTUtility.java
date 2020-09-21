package com.dailycircular.dailycircular.security;

import com.dailycircular.dailycircular.model.ApplicationUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.dailycircular.dailycircular.constants.SecurityConstants.SECRET_KEY;
import static com.dailycircular.dailycircular.constants.SecurityConstants.TOKEN_DURATION;


@Service
public class JWTUtility {

    private Boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string empty");
        }
        return false;
    }

    private Claims extractAllClaims(String token) {
        if (!isValidToken(token)) return null;
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token) {
        if (!isValidToken(token)) return null;
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        if (!isValidToken(token)) return null;
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        if (!isValidToken(token)) return null;
        return extractExpirationDate(token).before(new Date());
    }

    public Boolean validateTokenByUserDetails(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * generate token
     *
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + TOKEN_DURATION);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", applicationUser.getUsername());
        claims.put("firstName", applicationUser.getResume().getFirstName());
        claims.put("lastName", applicationUser.getResume().getLastName());

        return Jwts.builder()
                .setSubject(Long.toString(applicationUser.getId()))
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
