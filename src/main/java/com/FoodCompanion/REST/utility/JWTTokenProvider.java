package com.FoodCompanion.REST.utility;

import com.FoodCompanion.REST.constant.SecurityConstant;
import com.FoodCompanion.REST.model.UserPrincipals;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
@Component
public class JWTTokenProvider {
    @Value("jkw.secret")
    private String secret;

    public String generateJwtToken(UserPrincipals userPrincipal){
        String[] claims = getCaimsFromUser(userPrincipal);

    return JWT.create()
            .withIssuer(SecurityConstant.GET_FOODCOMPANION_LLC)
            .withAudience(SecurityConstant.GET_FOODCOMPANION_ADMINISTRATION)
            .withIssuedAt(new Date())
            .withSubject(userPrincipal.getUsername())
            .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
            .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConstant.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public List<GrantedAuthority> getAuth(String token) {
        String[] claims = getCaimsFromToken(token);

        return stream(claims).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public Authentication getAuthentication(
            String username,
            List<GrantedAuthority> grantedAuthorities,
            HttpServletRequest httpServletRequest ){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username,null, grantedAuthorities );
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
    return usernamePasswordAuthenticationToken;
    }
    public boolean isTokenValid(String username, String token){
        JWTVerifier verifier = getVerifier();
        return StringUtils.isNoneEmpty(username) &&  !isTokenExpired(verifier,token);
    }

    public String getSubject(String token){
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token).getSubject();
    }


    private boolean isTokenExpired(JWTVerifier verifier ,String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }


    private String[] getCaimsFromToken(String token) {
        JWTVerifier verifier = getVerifier();

        return verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_FOODCOMPANION_LLC).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException(SecurityConstant.TOKEN_CAN_NOT_BE_VERIFIED);
        }
        return verifier;
    }


    private String[] getCaimsFromUser(UserPrincipals userPrincipal) {
        List<String> auth = new ArrayList<>();
        for(GrantedAuthority grantedAuthority: userPrincipal.getAuthorities()){
            auth.add(grantedAuthority.getAuthority());
        }
        return auth.toArray(new String[0]);
    }

}
