package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import net.dontdrinkandroot.example.angularrestspringsecurity.rest.TokenUtils;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.TokenTransfer;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.UserTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
@Path("/user")
public class UserResource
{

//	@Autowired
//	private UserDetailsService userService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;


	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserTransfer getUser()
	{
		System.out.println("*************************GET*****************************");
		System.out.println("*************************GET*****************************");
		System.out.println("*************************GET*****************************");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication------------>"+authentication);
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;
		System.out.println("userDetails------------>"+userDetails);
		return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
	}


	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@Path("authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password)
	{
		System.out.println("username--------------->"+username);
		System.out.println("password--------------->"+password);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		System.out.println("Current Authentication :::"+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
//		UserDetails userDetails = this.userService.loadUserByUsername(username);
		
		List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		gList.add(ga);
		UserDetails userDetails = new User(username,"user",gList);
		
		System.out.println("Now userDetails--------------->"+userDetails);
		
//		System.out.println("userService----------->"+userService);
		System.out.println("userDetails--------------->"+userDetails);
		
		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}


	private Map<String, Boolean> createRoleMap(UserDetails userDetails)
	{
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}

}