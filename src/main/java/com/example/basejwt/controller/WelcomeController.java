package com.example.basejwt.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basejwt.entity.AuthorizeRequest;
import com.example.basejwt.utils.JwtUtilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WelcomeController {

  private final AuthenticationManager authenticationManager;
	private final JwtUtilService jwtUtilService;
  
  @GetMapping("/")
	public String welcomePage() {
		return "Success to load welcome page...!";
	}

  @PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthorizeRequest authorizeRequest) throws Exception {
		
		try {
			System.out.println("Action in controller authenticat....");
			authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authorizeRequest.getUsername(), authorizeRequest.getPassword())
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Invalid username/password");
		}
		
		return jwtUtilService.generateToken(authorizeRequest.getUsername());
	}
}