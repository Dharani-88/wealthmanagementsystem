package com.cts.wealthmanagementsystem.config;
	import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.provisioning.InMemoryUserDetailsManager;
   
	public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/", "/signup", "/signup/save", "/login").permitAll() // Public access
	                .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only access
	                .anyRequest().authenticated()
	            )
	            .formLogin(login -> login
	                .loginPage("admin/login")
	                .defaultSuccessUrl("admin/main", true)
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	                .permitAll()
	            );

	        return http.build();
	    }

	    @SuppressWarnings("deprecation")
		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance(); // ✅ Allows `{noop}` plain-text passwords
	    }
	    @Bean
	    public UserDetailsService userDetailsService() {
	        PasswordEncoder encoder = passwordEncoder();

	        // 🔹 Fixed Admin Credentials
	        UserDetails admin = User.withUsername("admin@wealthy.com")
	                .password(encoder.encode("Admin@123")) // ✅ Custom admin password
	                .roles("ADMIN")
	                .build();

	        return new InMemoryUserDetailsManager(admin);
	    }
	}

