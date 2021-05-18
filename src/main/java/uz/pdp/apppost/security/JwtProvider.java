package uz.pdp.apppost.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {


    long expireDate = 64 * 10000000;
    String secretKey = "Bu project bankomat uchun";

    public String generateToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expireDate + System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
