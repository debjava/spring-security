package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public RoleHierarchy roleHierarchy(){
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    /* tricks lies here */
	    roleHierarchy.setHierarchy("ROLE_SUPREME > ROLE_ADMIN ROLE_ADMIN > ROLE_OPERATOR ROLE_OPERATOR > ROLE_GUEST");
	    return roleHierarchy;
	}
	
	private SecurityExpressionHandler<FilterInvocation>    webExpressionHandler() {
	    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler     = new DefaultWebSecurityExpressionHandler();
	    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
	    return defaultWebSecurityExpressionHandler;
	}

	// https://stackoverflow.com/questions/26661192/spring-boot-spring-security-hierarchical-roles
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().expressionHandler(webExpressionHandler());
		
		http.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/", "/admin").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
		
		//http.authorizeRequests().antMatchers("/", "/admin").hasAnyAuthority("ROLE_ADMIN");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("u").password("u").roles("USER")
		.and()
		.withUser("d").password("d").authorities("ROLE_ADMIN");
		
//		auth.inMemoryAuthentication().withUser("deb").password("deb").authorities("ROLE_ADMIN");
//		auth.inMemoryAuthentication().withUser("deb").password("deb").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/*");
	}
}