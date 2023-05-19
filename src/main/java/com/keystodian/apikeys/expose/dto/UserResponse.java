package com.keystodian.apikeys.expose.dto;

import java.util.Set;

public record UserResponse (String username, String email, Set<String> roles) {


}