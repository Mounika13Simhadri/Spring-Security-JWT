package net.javaguides.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.config.JwtUtil;
import net.javaguides.springboot.model.AuthenticationRequest;
import net.javaguides.springboot.model.AuthenticationResponse;
import net.javaguides.springboot.service.UserServiceImpl;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> cretaeAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),authenticationRequest.getPassword()));
			}
			catch(DisabledException e) {
				throw new Exception("User_Diabled",e);
			}
			catch(BadCredentialsException e) {
				throw new Exception("Invalid_credentials",e);
			}
			final UserDetails userDetails=userDetailsService.
					loadUserByUsername(authenticationRequest.getUsername());
			String token=jwtUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(token));
		}
		
	}
	
