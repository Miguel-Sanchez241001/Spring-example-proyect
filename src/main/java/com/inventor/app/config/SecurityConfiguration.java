package com.inventor.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.inventor.app.security.InMemoryUserDetail;

import org.springframework.security.config.Customizer;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
  
	@Autowired
	InMemoryUserDetail userDetailsService;
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

	 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.//csrf((csrf) -> csrf.disable()).
                authorizeHttpRequests((requests) -> requests
				.requestMatchers( "/", "/home").permitAll()
                .requestMatchers("/public/**").permitAll()
				.requestMatchers("/error/**").permitAll()
				.requestMatchers("/login/**").permitAll()
				.requestMatchers("/consultas/**").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
				.requestMatchers("/buscar/**").hasAnyRole("ADMIN")
				.requestMatchers("/listar/**").hasAnyRole("ADMIN")

                .requestMatchers("/private/**").authenticated()
				
				)
				
                .formLogin(t -> t.loginPage("/login/loguear")
					.usernameParameter("user")
					.passwordParameter("pass") 
					.loginProcessingUrl("/iniciar")
					.successHandler((request, response, authentication) -> response.
					sendRedirect("/consultas/reporte") )
					
					)
                .logout(t ->
				t
				.invalidateHttpSession(true)
				.logoutUrl("/login/logout"))
				.httpBasic(Customizer.withDefaults())
				;

		

        return http.build();
    }

	@Bean
    public UserDetailsService userDetailsService() {
        return this.userDetailsService;
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
