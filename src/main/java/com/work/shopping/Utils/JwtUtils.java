package com.work.shopping.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtils {
    private static String secret = "thisIsATestSecretForThisProjectINeedItBeLongerAndLonger";
    private static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    public static String generateToken(String pwd, String userId){
        return Jwts.builder().setId(userId).setSubject(pwd).signWith(key).compact();
    }

    public static Claims parseToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean checkPwd(String token, String pwd){
        return parseToken(token).getSubject().equals(pwd);
    }
}