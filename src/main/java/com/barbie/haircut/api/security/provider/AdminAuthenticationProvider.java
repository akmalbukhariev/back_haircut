package com.barbie.haircut.api.security.provider;

import com.barbie.haircut.api.security.CommUserDetails;
import com.barbie.haircut.api.security.AdminDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminAuthenticationProvider implements AuthenticationProvider {

    private final AdminDetailsServiceImpl adminDetailService;
    private final PasswordEncoder psssEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String adminId 	= authentication.getName();
        String passwd 	= (String)authentication.getCredentials();

        log.debug("adminId =>"+adminId);
        log.debug("passwd =>"+passwd);

        CommUserDetails user = adminDetailService.loadUserByUsername(adminId);

        if(!psssEncoder.matches(passwd, user.getPassword())) {
            throw new BadCredentialsException("Password is not matched!!");
        }
        try {
            user = adminDetailService.setting(adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(adminId, passwd, user.getAuthorities());
        user.getDataMap().remove("passwd");
        authToken.setDetails(user.getDataMap());
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
