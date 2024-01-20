package com.barbie.haircut.api.security.filter;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Constant;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.TokenDto;
import com.barbie.haircut.api.dto.UserDto;
import com.barbie.haircut.api.security.jwt.JwtTokenProvider;
import com.barbie.haircut.api.security.provider.UserAuthenticationProvider;
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
import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthenticationProvider userProvider;
    private UserDto user = null;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            user = objectMapper.readValue(request.getInputStream(), UserDto.class);
            log.debug("userInfo==>"+ user.getUserId() + ", " + user.getPasswd());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPasswd());

            return userProvider.authenticate(authToken);
        } catch (IOException e) {
            //ExceptionUtils.getMessage(e);
            e.printStackTrace();
            VersionResponseResult resResult = new VersionResponseResult();

            response.setHeader(Constant.HEADER_ACCESS_TOKEN,"");
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
        map.put("ROLE", "USER");

        VersionResponseResult resResult = new VersionResponseResult();
        response.setContentType("application/json");

        String accessToken = (String)map.get("accessToken");
        Result validToken = jwtTokenProvider.validateToken(accessToken);

        if(validToken == Result.SUCCESS){
            response.setHeader(Constant.HEADER_USER_NAME,"");
            response.setHeader(Constant.HEADER_ROLE, "");
            response.setHeader(Constant.HEADER_ACCESS_TOKEN, "");
            response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");
        }
        else {
            TokenDto tokenInfo = jwtTokenProvider.generateToken(authResult);

            if (tokenInfo != null) {
                response.setHeader(Constant.HEADER_ACCESS_TOKEN, tokenInfo.getAccessToken());
                response.setHeader(Constant.HEADER_REFRESH_TOKEN, tokenInfo.getRefreshToken());
                response.setHeader(Constant.HEADER_ROLE, "USER");
                response.setHeader(Constant.HEADER_USER_NAME, (String) map.get("eamil"));

                resResult.setResultCode(Result.SUCCESS.getCodeToString());
                resResult.setResultMsg(Result.SUCCESS.getMessage());
                resResult.setResultData(map);
            } else {
                response.setHeader(Constant.HEADER_USER_NAME, "");
                response.setHeader(Constant.HEADER_ROLE, "");
                response.setHeader(Constant.HEADER_ACCESS_TOKEN, "");
                response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");

                resResult.setResultCode(Result.LOGIN_INVALID_TOKEN.getCodeToString());
                resResult.setResultMsg(Result.LOGIN_INVALID_TOKEN.getMessage());
            }
        }
        new ObjectMapper().writeValue(response.getOutputStream(), resResult);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        log.debug("unsuccessfulAuthentication================");
        VersionResponseResult resResult = new VersionResponseResult();

        response.setHeader(Constant.HEADER_ACCESS_TOKEN,"");
        response.setHeader(Constant.HEADER_REFRESH_TOKEN, "");
        response.setHeader(Constant.HEADER_ROLE, "");
        response.setHeader(Constant.HEADER_USER_NAME, "");

        resResult.setResultCode(Result.SUCCESS.getCodeToString());
        resResult.setResultMsg(Result.SUCCESS.getMessage());
    }
}
