package com.barbie.haircut.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@Tag(name = "User Common", description = "User API")
@RequestMapping(value={"/haircut/api/v1/user"})
public class UserCommonController {

    @Operation(tags = {"User common"}, summary = "1. login", description = "login", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/login", headers = { "Content-type=application/json" })
    public String login(){

        return "it is working";
    }
}
