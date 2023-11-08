package com.inventor.app.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {




	/**
	 * @HttpSecurity: es equivalente a trabajar con un fichero XML en los que
	 *                definir la seguridad de las peticiones. Por tanto, esta clase
	 *                sería la alternativa mediante a la cual vamos a realizar la
	 *                configuración.
	 * @authorizeHttpRequests: nos permite autorizar a la petición HTTP, es decir, a
	 *                         la HTTP request
	 * @anyRequest: mediante el método anterior ya hemos autorizado la request, en
	 *              este caso en concrecto, vamos a autorizar a todas las request,
	 *              ya que con este metodo especificamos any request (cualquier
	 *              request).
	 * @authentificated: el usuario debe de estar autentificado.
	 * @httpBasic: nos permite agregar una instancia a la cadena de filtros en la
	 *             que se añadirá el filtro de BasicAutheticationFilter, que como ya
	 *             hemos explicado, es el encargado de realizar una autentificación
	 *             básica de la manera más típica, con una ventana de login, user y
	 *             password. Cuando añadimos este método, la autentificación
	 *             intentará realizar el logeo y cuando este sea satisfactorio
	 *             guardará el objeto de autentificación resultante en el
	 *             SegurityContextHolder para que posteriormente podamos consultar
	 *             dicha autentificación en el futuro.
	 */
	

	// configuracion web security
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(c -> c.disable()) // vulnerabilidad de formularios se capturan los datos en el cmaino

				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/","/nuevo","/registro").permitAll()
					.requestMatchers("/doctor/**").hasRole("DOCTOR")
					.requestMatchers("/personal/**").hasRole("PMEDICO")
					.requestMatchers("/paciente/**").hasRole("PACIENTE")
							.anyRequest().authenticated();
				})
				.formLogin(login -> {
					login.permitAll();
					login.loginPage("/login");
					login.successHandler((request, response, authentication) -> {
						// Obtiene el rol del usuario autenticado
						Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
						String role = authorities.stream()
								.map(GrantedAuthority::getAuthority)
								.findFirst()
								.orElse("");
						// Redirecciona al controlador correspondiente al rol del usuario
						switch (role) {
							case "ROLE_DOCTOR":
								response.sendRedirect("/doctor/");
								break;
							case "ROLE_PACIENTE":
								response.sendRedirect("/paciente/");
								break;
							default:
								response.sendRedirect("/personal/");
								break;
						}
					});
				})
				.sessionManagement(sesionconfig -> {
					sesionconfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
					sesionconfig.invalidSessionUrl("/login");
					sesionconfig.maximumSessions(1)
							.expiredUrl("/login")
							.sessionRegistry(sessionRegistry());

					sesionconfig.sessionFixation()

							.migrateSession(); // previene que se obtenga el ID de session

				})
				.exceptionHandling(	httpSecurityExceptionHandlingConfigurer ->{
							httpSecurityExceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) ->
							{
								request.getSession().setAttribute("mensaje", "Accede con el perfil correspondiente");
								response.sendRedirect("/login");

							});

						}	)
				.build();

	}

	// registro de sesiones
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/templates/assets/**");
	}






	// ROLES
//	 @Bean
//    public UserDetailsService userDetailsService() {
//  		List<UserDetails> users = new ArrayList<>();
//
//  		UserDetails Pmedico = User.builder()
//  		.username("pmedico")
//  		.password("{noop}pmedico")
//  		.roles("PMEDICO")
//  		.build();
//  		users.add(Pmedico);
//  		UserDetails doctor = User.builder()
//  		.username("doctor")
//  		.password("{noop}doctor")
// 		.roles("DOCTOR").build();
//  		users.add(doctor);
//
//  		UserDetails PACIENTE = User.builder()
//  		.username("paciente")
//  		.password("{noop}paciente")
//  		.roles("PACIENTE")
// 				.build();
//  		users.add(PACIENTE);
//
//  		return new InMemoryUserDetailsManager(users);
//     }

// @Bean
// 	public UserDetailsService userDetailsService() {
// 		return username -> usuarioServiceImpl.cargarUsuarioForLogin(username);
// 	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); //No apto para producción
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
