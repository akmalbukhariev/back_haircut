package com.barbie.haircut.api.security.filter;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Constant;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.TokenDto;
import com.barbie.haircut.api.param.AdminParam;
import com.barbie.haircut.api.security.jwt.JwtTokenProvider;
import com.barbie.haircut.api.security.provider.AdminAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminAuthenticationProvider adminProvider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            AdminParam user = objectMapper.readValue(request.getInputStream(), AdminParam.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getAdminId(), user.getPasswd());

            //When authenticating is called, loadUserByUsername() of UserDetailsService is called.
            return adminProvider.authenticate(authToken);

            // Generate JWT token based on authentication information
            //TokenDto tokenInfo = jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {

            //ExceptionUtils.getMessage(e);

            VersionResponseResult resResult = new VersionResponseResult();

            response.setContentType("application/json");
            response.setHeader(Constant.HEADER_ACCESS_TOKEN, "");
            response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");
            resResult.setResultCode(Result.LOGIN_INVALID_TOKEN.getCodeToString());
            resResult.setResultMsg(Result.LOGIN_INVALID_TOKEN.getMessage());

            try {
                new ObjectMapper().writeValue(response.getOutputStream(), resResult);
            } catch (IOException e1) {
                //ExceptionUtils.getMessage(e1);
            }
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        CamelCaseMap map = (CamelCaseMap)authResult.getDetails();

        //map.put("role" , "ADMIN");
        map.put("user_id" , map.get("adminId"));
        map.remove("adminId");
        /*
         * Return token information in header
         */
        TokenDto tokenInfo = jwtTokenProvider.generateToken(authResult);

        VersionResponseResult resResult = new VersionResponseResult();

        response.setContentType("application/json");

        if(tokenInfo != null) {
            response.setHeader(Constant.HEADER_ACCESS_TOKEN, tokenInfo.getAccessToken());
            response.setHeader(Constant.HEADER_REFRESH_TOKEN, tokenInfo.getRefreshToken());
            response.setHeader(Constant.HEADER_USER_NAME, (String)map.get("adminNm"));
            resResult.setResultCode(Result.SUCCESS.getCodeToString());
            resResult.setResultMsg(Result.SUCCESS.getMessage());
            resResult.setResultData(map);

        }else {
            response.setHeader(Constant.HEADER_ACCESS_TOKEN, "");
            response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");
            response.setHeader(Constant.HEADER_ROLE, "");
            response.setHeader(Constant.HEADER_USER_NAME, "");

            resResult.setResultCode(Result.LOGIN_INVALID_TOKEN.getCodeToString());
            resResult.setResultMsg(Result.LOGIN_INVALID_TOKEN.getMessage());
        }

        new ObjectMapper().writeValue(response.getOutputStream(), resResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

//		super.unsuccessfulAuthentication(request, response, failed);
        log.debug("unsuccessfulAuthentication================");
        VersionResponseResult resResult = new VersionResponseResult();

        response.setContentType("application/json");
        response.setHeader(Constant.HEADER_ACCESS_TOKEN, "");
        response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");
        response.setHeader(Constant.HEADER_ROLE, "");
        response.setHeader(Constant.HEADER_USER_NAME, "");
        resResult.setResultCode(Result.LOGIN_INVALID_TOKEN.getCodeToString());
        resResult.setResultMsg(Result.LOGIN_INVALID_TOKEN.getMessage());

        new ObjectMapper().writeValue(response.getOutputStream(), resResult);
    }
}
