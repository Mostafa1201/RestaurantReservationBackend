package io.taher.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.taher.models.User;
import io.taher.services.UserService;
import io.taher.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody User authenticationRequest) throws Exception {
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> userObject = new HashMap<>();
		ObjectNode authObject = authenticateUser(authenticationRequest);
		HttpStatus statusCode = HttpStatus.OK;
		if(authObject.get("error") != null) {
			statusCode = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Object>(authObject,statusCode);
		}
		User user = userService.getUserByEmail(authenticationRequest.getEmail());
		response.put("token", authObject.get("token"));
		userObject.put("id", user.getId());
		userObject.put("name", user.getName());
		userObject.put("email", user.getEmail());
		userObject.put("mobile", user.getMobile());
		userObject.put("type", user.getType());
		response.put("user", userObject);
		return new ResponseEntity<Object>(response,statusCode);
	}
	
	@RequestMapping(value = "/auth/register", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody User authenticationRequest) throws Exception {
		User userFound = userService.getUserByEmail(authenticationRequest.getEmail());
		HttpStatus statusCode = HttpStatus.OK;
		if(userFound != null) {
			ObjectNode response = mapper.createObjectNode();
			response.put("message", "Email already exists , please try another one , or login if you have one");
			statusCode = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Object>(response,statusCode);
		}
		User userProperties = new User(
				authenticationRequest.getId(),
				authenticationRequest.getName(),
				authenticationRequest.getEmail(),
				authenticationRequest.getPassword(),
				authenticationRequest.getMobile(),
				"user"
		);
		userProperties.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
		User user = userService.createUser(userProperties);
		ObjectNode authObject = authenticateUser(authenticationRequest);
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> userObject = new HashMap<>();
		response.put("token", authObject.get("token"));
		if(authObject.get("error") != null) {
			statusCode = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Object>(authObject,statusCode);
		}
		userObject.put("id", user.getId());
		userObject.put("name", user.getName());
		userObject.put("email", user.getEmail());
		userObject.put("mobile", user.getMobile());
		userObject.put("type", user.getType());
		response.put("user", userObject);
		return new ResponseEntity<Object>(response,statusCode);
	}

	public ObjectNode authenticateUser(User authenticationRequest){
		ObjectNode response = mapper.createObjectNode();
		try {
			jwtTokenUtil.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
			final String token = jwtTokenUtil.generateToken(userDetails);
			response.put("token", token);
			return response;

		} catch (Exception e) {
			response.put("error", "Wrong email or password");
			return response;
		}
	}


}