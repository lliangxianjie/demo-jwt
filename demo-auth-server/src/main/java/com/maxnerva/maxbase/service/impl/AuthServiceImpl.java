package com.maxnerva.maxbase.service.impl;

import com.maxnerva.maxbase.feign.IUserService;
import com.maxnerva.maxbase.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private static final  String SECRET = "MyJwtSecret";
    @Autowired
    private IUserService userService;
    @Override
    public String login(String username, String password) throws Exception {
        Map<String, String> data = userService.validate(username,password).getData();
        String token = "";
        if (data == null)
        {
             return  token;
        }
         token = Jwts.builder().setSubject(data.get("username")).
                setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)).
                signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return token;
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return null;
    }

    @Override
    public void validate(String token) throws Exception {
        String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        System.out.println(user);
    }

    @Override
    public Boolean invalid(String token) throws Exception {
        return null;
    }
}
