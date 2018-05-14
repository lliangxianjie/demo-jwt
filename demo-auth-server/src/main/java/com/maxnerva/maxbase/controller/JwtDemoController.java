package com.maxnerva.maxbase.controller;

import com.maxnerva.maxbase.msg.ObjectRestResponse;
import com.maxnerva.maxbase.service.AuthService;
import com.maxnerva.maxbase.user.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("jwt")
public class JwtDemoController {

    @Autowired
    private AuthService  authService;

    @RequestMapping(value = "token", method =  RequestMethod.POST)
    public ObjectRestResponse<String> createAuthenticationToken
            ( @RequestBody JwtAuthenticationRequest authenticationRequest)
            throws Exception {
      String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
      if (token.isEmpty())
      {
          ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<String>();
          objectRestResponse.setStatus(400);
          objectRestResponse.setMessage(" username or password is not correct.");
          return objectRestResponse;
      }
      return new ObjectRestResponse<String>().date(token)  ;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ObjectRestResponse<String> sayHello(HttpServletRequest request) throws Exception {
         String token = request.getHeader("Authorization");
         authService.validate(token);
        return new  ObjectRestResponse<String>().date("hello world.");
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token) throws Exception
    {
        authService.validate(token);
        return ResponseEntity.ok(true);
    }
}
