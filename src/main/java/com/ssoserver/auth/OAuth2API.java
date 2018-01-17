package com.ssoserver.auth;

import com.ssoserver.model.entity.Organization;
import com.ssoserver.model.entity.Role;
import com.ssoserver.model.request.AuthRequest;
import com.ssoserver.model.response.AuthResponse;
import com.ssoserver.model.response.Response;
import com.ssoserver.model.response.ResponseGenerator;
import com.ssoserver.security.JwtTokenUtil;
import com.ssoserver.service.OrganizationService;
import com.ssoserver.service.RoleService;
import com.ssoserver.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OAuth2API {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2API.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value="token", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(
            @RequestBody AuthRequest authRequest
    ) throws AuthenticationException {
        // Perform the security
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, authRequest.getPassword(), null);

//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Auto login %s successfully!", authRequest.getUsername())) ;
        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        Organization org = organizationService.getOrgByUsername(authRequest.getUsername());
        Role role = roleService.getRoleByUsername(authRequest.getUsername());


        // generate token using JWT
        final String jwtToken = jwtTokenUtil.generateToken(userDetails, null);
        // save to the database (redis)

        // return JWT
        AuthResponse token = new AuthResponse();
        token.setAccessToken(jwtToken);
        token.setRefreshToken(CommonUtil.generateUUID());
        token.setTokenType("BEARER");
        token.setPublicKey("");
        Response response = new Response(200, "", token);
        return new ResponseGenerator().successResponse(response);

    }

    @RequestMapping(value = "refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Response> refreshToken(
            @RequestParam(value = "grant_type", defaultValue = "refresh_token", required = false) String grantType,
            @RequestParam(value= "refresh_token") String refreshToken
    ) {
        return null;
    }
}