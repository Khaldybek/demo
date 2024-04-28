package com.example.demo.service;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value(value = "${token.sign.secret-key}")
    String secretKey;

    public String generateToken(UserDetails  userDetails) {
        Map<String,Object> claims =new HashMap<>();
        if(userDetails instanceof  User customUserDetails){
            claims.put("name",customUserDetails.getFirstName());
            claims.put("role",customUserDetails.getRole());
        }
        return  generateToken(claims,userDetails);
    }




    public String generateToken(Map<String , Object> claims , UserDetails userDetails){
        return  Jwts.builder().setClaims(claims).
                setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
}
public boolean isTokenValid(String token , UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
}

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token,Claims ::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims ,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }
    private Key getSignInKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject) ;
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}