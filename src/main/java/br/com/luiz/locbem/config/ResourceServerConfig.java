package br.com.luiz.locbem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .antMatchers("/users").authenticated();
//    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .antMatchers("/users").authenticated()
//                .antMatchers("/oauth/token").permitAll();;
//    }

//    @Bean
////    @Order(Ordered.HIGHEST_PRECEDENCE)
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////
////        config.setAllowCredentials(true); // permite credenciais
////        config.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // permite todas as origens
////        config.setAllowedMethods(Arrays.asList("*")); // permite todos os métodos HTTP
////        config.setAllowedHeaders(Arrays.asList("*")); // permite todos os headers
////        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type")); // permite expor certos headers
////
////        source.registerCorsConfiguration("/**", config);
////        return new CorsFilter(source);
////    }
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // permite credenciais
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // permite origem sem barra
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // permite métodos HTTP
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Access-Control-Allow-Headers")); // permite headers
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type")); // permite expor headers

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
@Override
public void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/users").permitAll()
//            .antMatchers(HttpMethod.GET, "/users").permitAll()
//            .antMatchers("/users").authenticated()
//            .antMatchers("/oauth/token").permitAll()
            .anyRequest().permitAll(); // Permitir o endpoint /oauth/token
}

}
