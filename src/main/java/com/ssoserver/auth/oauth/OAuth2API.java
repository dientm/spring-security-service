package com.ssoserver.auth.oauth;

import com.ssoserver.model.Response;
import com.ssoserver.model.response.TokenResponse;
import com.ssoserver.service.OrganizationService;
import com.ssoserver.service.RoleService;
import com.ssoserver.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class OAuth2API {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="token", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "grant_type", defaultValue = "password", required = false) String responseType
    ) {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Organization org = organizationService.getOrgByUsername(usename);
        Role role = roleService.getRoleByUsername(username);


        // generate token using JWT

        // save to the database (redis)

        // return JWT
        TokenResponse token = new TokenResponse();
        token.setAccessToken(jwtString);
        token.setRefreshToken(CommonUtil.generateUUID());
        token.setAccessToken("BEARER");
        token.setPublicKey("");
        Response response = new Response(200, "", token);
        return response;

    }

    @RequestMapping(value = "refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Response> refreshToken(
            @RequestParam(value = "grant_type", defaultValue = "refresh_token", required = false) String grantType,
            @RequestParam(value= "refresh_token") String refreshToken
    ) {
        return null;
    }
}