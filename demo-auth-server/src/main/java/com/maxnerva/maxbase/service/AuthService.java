package com.maxnerva.maxbase.service;

public interface AuthService {
    String login(String username, String password) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
    Boolean invalid(String token) throws Exception;
}
