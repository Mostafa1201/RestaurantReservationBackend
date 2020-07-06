package io.taher.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.taher.services.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// we are checking by email since we are using email instead of username in application 
		io.taher.models.User user = userService.getUserByEmail(email);

	    if (user == null) {
	        throw new UsernameNotFoundException("Not found!");
	    }
	    return new User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}