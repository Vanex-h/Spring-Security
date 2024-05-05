package com.learn.spring.student.config;

import com.learn.spring.student.config.user.User;
import com.learn.spring.student.config.user.UserSecurityDetails;
import com.learn.spring.util.Mapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    private String jwtExpirationInMs = "86400000";
    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_EMAIL = "email";
    private static final String CLAIM_KEY_ROLE = "role";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    public String generateToken(Authentication authentication) {
        UserSecurityDetails userPrincipal = (UserSecurityDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (GrantedAuthority role : userPrincipal.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }


        User authUser = Mapper.getUserFromDTO(userPrincipal);


        return Jwts.builder()
                .setId(authUser.getId() + "")
                .setSubject(userPrincipal.getId() + "")
                .claim("user", authUser)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String createToken(Long userId , String email , String role){

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH , 1);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return  Jwts.builder()
                .claim(CLAIM_KEY_USER_ID, userId)
                .claim(CLAIM_KEY_EMAIL, email)
                .claim(CLAIM_KEY_ROLE, role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256 , SECRET_KEY).compact();
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username =     extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
