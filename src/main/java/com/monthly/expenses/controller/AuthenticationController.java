package com.monthly.expenses.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.domain.User;
import com.monthly.expenses.exception.UnauthorizedException;
import com.monthly.expenses.model.UserDTO;
import com.monthly.expenses.security.JwtTokenUtil;
import com.monthly.expenses.security.JwtUser;



@RestController
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
   


    @PostMapping({ "/login", "/adminLogin" })
    public ResponseEntity<UserDTO> createAuthenticationToken(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
         try {
        	  Authentication authentication = null;
        	 if(StringUtils.hasText(user.getPassword())) {
        		 authentication = authenticationManager
                         .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        	 }else {
        		 authentication = authenticationManager
                         .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getOrginalPassword()));
        	 }
        	 
             final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
             
             SecurityContextHolder.getContext().setAuthentication(authentication);
             final String token = jwtTokenUtil.generateToken(userDetails);
             response.setHeader("Token", token);
             return ResponseEntity.ok(new UserDTO(userDetails.getUser(), "", token)); 
         }catch (Exception e) {
        	 throw new UnauthorizedException(e.getMessage());
		}
        
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> refreshAndGetAuthenticationToken(HttpServletRequest request,
            HttpServletResponse response) {
        String authToken = request.getHeader(tokenHeader);
        if (authToken != null && authToken.length() > 7) {
            authToken = authToken.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(authToken, userDetails.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(authToken);
            response.setHeader("Token", refreshedToken);
            return ResponseEntity.ok(new UserDTO(userDetails.getUser(), "", refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
