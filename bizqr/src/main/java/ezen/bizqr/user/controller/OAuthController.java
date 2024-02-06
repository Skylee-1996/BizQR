package ezen.bizqr.user.controller;

import ezen.bizqr.user.service.OAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class OAuthController {

    OAuthService oAuthService;

    public OAuthController(OAuthService oAuthService){
        this.oAuthService = oAuthService;
    }

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId ){
        oAuthService.socialLogin(code, registrationId);
    }

}
