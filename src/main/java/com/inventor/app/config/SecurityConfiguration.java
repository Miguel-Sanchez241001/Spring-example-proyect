package com.inventor.app.config;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


import org.springframework.security.config.Customizer;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
  
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
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    //     http
    //     .authorizeHttpRequests((authz) -> authz
    //         .anyRequest().authenticated()
    //     )
    //     .httpBasic(withDefaults());
    // return http.build();
        
    // }

	//  @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

	// 	http.//csrf((csrf) -> csrf.disable()).
    //             authorizeHttpRequests((requests) -> requests
	// 			.requestMatchers( "/", "/home").permitAll()
    //             .requestMatchers("/public/**").permitAll()
	// 			.requestMatchers("/error/**").permitAll()
	// 			.requestMatchers("/loguear/**").authenticated()
	// 			.requestMatchers("/consultas/**").permitAll()
    //             .requestMatchers("/admin/**").hasAnyRole("ADMIN")
	// 			.requestMatchers("/buscar/**").hasAnyRole("ADMIN")
	// 			.requestMatchers("/listar/**").hasAnyRole("ADMIN")

    //             .requestMatchers("/private/**").authenticated()
				
	// 			)
				
    //             .formLogin(t -> t.
	// 				loginPage("/loguear")
	// 				.usernameParameter("user")
	// 				.passwordParameter("pass") 
	// 				.loginProcessingUrl("/iniciar")
	// 				.successHandler((request, response, authentication) -> response.
	// 				sendRedirect("/consultas/reporte") )
					
	// 				)
    //             .logout(t ->
	// 			t
	// 			.invalidateHttpSession(true)
	// 			.logoutUrl("/logout"))
	// 			.httpBasic(Customizer.withDefaults())
	// 			;

		

    //     return http.build();
    // }



		// configuracion web security 
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			return http
					.csrf(c -> c.disable())   // vulnerabilidad de formularios se capturan los datos en el cmaino
					.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/index").permitAll();
					auth.requestMatchers("/").permitAll();

					auth.anyRequest().authenticated();
				})
				.formLogin(login -> {
					
					login.permitAll();
					login.loginPage("/login");
					login.successHandler((request, response, authentication) -> response.sendRedirect("/") );
				
				} )

				.sessionManagement(sesionconfig -> {
					sesionconfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
					sesionconfig.invalidSessionUrl("/login");
					sesionconfig.maximumSessions(1)
					.expiredUrl("/login")
					.sessionRegistry(sessionRegistry())
					;
					
					sesionconfig.sessionFixation()
					
					.migrateSession(); // previene que se obtenga el ID de session 
					

				})
				
				
			.build();	


		}


		// registro de sesiones
@Bean
public SessionRegistry sessionRegistry(){
	return new SessionRegistryImpl();
}



// autenticacion en memoria
@Bean
    public UserDetailsService userDetailsService() {
		        List<UserDetails> users = new ArrayList<>();

        UserDetails admin = User.builder().username("admin").password("{noop}admin").roles("ADMIN")
                .build();
				users.add(admin);
				UserDetails usuario = User.builder().username("usuario").password("{noop}usuario").roles("USER")
                .build();
				users.add(usuario);
        return new InMemoryUserDetailsManager(users);
    }
    





    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager(){

    //     return new InMemoryUserDetailsManager(
    //             new User(
    //                 "username",
    //                 "password", 
    //                 Arrays.asList(new SimpleGrantedAuthority("ADMIN"))
    //                     )
    //     );
    // }


}