package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.request.LoginForm;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.request.SignUpForm;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.response.JwtResponse;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.response.ResponseMessage;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Role;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.RoleName;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.User;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.RoleRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.UserRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Security.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

@Api(value = "User Management", description = "Operations pertaining to user in User Management")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
    @Autowired
    private JavaMailSender javaMailSender;

	@ApiOperation(value = "Login a user to a created account")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@ApiOperation(value = "Create a user account")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),signUpRequest.getPasswordConfirm(), signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getCi(), signUpRequest.getAddres());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);
		//SEND TEST EMAIL
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("carlosterceros11@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email \n Posdata: eres gei .-.XD");

        javaMailSender.send(msg);
		/////////////////////////
    
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}