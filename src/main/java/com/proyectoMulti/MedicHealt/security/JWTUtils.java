package com.proyectoMulti.MedicHealt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    public static String generateToken(String jwtSecret, String email, Map<String, Object> payload){
        Date expirationTime = new Date(System.currentTimeMillis()+ 1000L*60000*600000000);

        return Jwts.builder()
                .setSubject(email)
                .addClaims(payload)
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()),SignatureAlgorithm.HS256)
                .compact();
    }

 /*   public static Boolean isValidateToken(String jwtSecret,String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return Boolean.TRUE;
        }catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException("Token has expired");
        }
    }*/
    public static Boolean isValidateToken(String jwtSecret, String token) {
        try {
            // Crear una clave secreta a partir de jwtSecret
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            // Validar el token con la clave secreta y el algoritmo HS256
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            // Si el token es válido, retorna true
            return true;
        } catch (Exception exception) {
            // Si ocurre algún error, lanza una excepción de autenticación
            throw new AuthenticationCredentialsNotFoundException("Token has expired or is invalid");
        }
    }
    public static String getEmailFromJWT(String jwtSecret, String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes()).build()
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
