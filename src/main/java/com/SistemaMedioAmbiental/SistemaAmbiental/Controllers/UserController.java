package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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

		if(userRepository.findByUsername(loginRequest.getUsername()).get().getStatus()){
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		}
		else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> You don't have permission to access the page yet!"),
					HttpStatus.BAD_REQUEST);
		}
		
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
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), encoder.encode(signUpRequest.getPasswordConfirm()), signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getCi(), signUpRequest.getAddress());

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


		user.setStatus(false);
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@ApiOperation(value = "Accept a user request")
	@PutMapping(value="/acceptRequest/{id}")
	public User acceptRequest(@PathVariable( "id" ) Long id) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(userRepository.findById(id).get().getEmail());
        msg.setSubject("Solicitud de registro");
        msg.setText("Con mucha emocion y espectancia de sus aportes a la pagina y a la mejora de la ciudad de Cochabamba nuestro equipo le da la bienvenida a nuestro sitio. \n CIMA");
        javaMailSender.send(msg);
		return userRepository.findById(id)
                .map(user -> {
					user.setStatus(true);
					return userRepository.save(user);
				}).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
	}

	@ApiOperation(value = "Deny a user request")
    @DeleteMapping(value = "/denyRequest/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void denyRequest(@PathVariable("id") Long id) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(userRepository.findById(id).get().getEmail());
        msg.setSubject("Solicitud de registro");
        msg.setText("Despues de una revision cuidadosa nos apena informarle que su solicitud de registro para participar en nuestro sitio fue denegada. \n CIMA");
        javaMailSender.send(msg);
		userRepository.deleteById(id);
	}

	@ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of trees"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found")
    })
	@GetMapping("/users")
    public List<User> showUsers() {
        return userRepository.findAll();
	}
	
	@ApiOperation(value = "Get a user by Id")
    @GetMapping("/user/{id}")
    public User showUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }
}