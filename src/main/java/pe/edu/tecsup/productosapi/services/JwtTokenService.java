package pe.edu.tecsup.productosapi.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
public class JwtTokenService {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String JWT_TYPE_BEARER = "Bearer ";

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private Long JWT_EXPIRATION;

    public String parseToken(String token) {
        log.info("parseToken: token=" + token);
        token = token.replace(JWT_TYPE_BEARER, "");
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (claims == null)
            throw new JwtException("Token Invalid");

        String subject = claims.getSubject();
        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        log.info("END parseToken: subject=" + subject);

        return subject;
    }

    public String generateToken(String subject) {
        String token = JWT_TYPE_BEARER + Jwts.builder()
                .setIssuer(this.getClass().getName())
                .setSubject(subject)
                //                .setAudience("")
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION * 1000))
                //                .setNotBefore(new Date())
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                //                .claim(CLAIM_KEY_NAME, user.Fullname())
                //                .claim(CLAIM_KEY_ROLE, user.getRole())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        log.info("Generated Token: " + token); // Test token in https://jwt.io/
        return token;
    }

    /**
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String subject = parseToken(token);
        String refreshedToken = generateToken(subject);
        log.info("Refreshed Token: " + token);
        return refreshedToken;
    }
}
