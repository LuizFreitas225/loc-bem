package br.com.luiz.locbem.config;

import br.com.luiz.locbem.dto.security.UserCustomDTO;
import br.com.luiz.locbem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserService userService) throws Exception {

        builder.userDetailsService(login -> new UserCustomDTO(userService.findValidUserByEmail(login))).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().and()  // Ativa o suporte ao CORS
//                .authorizeRequests()
//                .anyRequest().authenticated()  // Configura a autenticação para as requisições
//                .and()
//                .oauth2Login(); // Configuração para OAuth2
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*"); // Permite todas as origens
//        config.addAllowedHeader("*"); // Permite todos os cabeçalhos
//        config.addAllowedMethod("*"); // Permite todos os métodos (GET, POST, PUT, DELETE, etc.)
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
