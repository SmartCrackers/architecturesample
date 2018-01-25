package com.servlet3;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("deskAppAuthenticationProvider")
public class DeskAppAuthenticationProvider implements AuthenticationProvider {
  
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    List<GrantedAuthority> authorities = new ArrayList<>();
    /*
    authoritiesNode.forEach(authority -> {
      authorities.add(new SimpleGrantedAuthority(authority.get(Constants.AUTHORITY).asText()));
    });*/
    return new UsernamePasswordAuthenticationToken("username", "password", authorities);
  }

  @Override
  public boolean supports(Class<?> arg0) {
    return true;
  }
}
