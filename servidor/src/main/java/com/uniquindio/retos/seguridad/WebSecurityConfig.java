package com.uniquindio.retos.seguridad;


import com.uniquindio.retos.seguridad.config.JwtAuthenticationEntryPoint;
import com.uniquindio.retos.seguridad.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.authorizeHttpRequests().requestMatchers(
                        "/doc/**",
                        "javaguides",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/auth/**",
                        "/api/auth/publish",
                        "/api/usuario/**",
                        "/health",
                        "/health/live",
                        "/health/ready",
                        "/health/details",
                        "/api/cambiarContrasenia/**").permitAll()
                .requestMatchers(
                        "/api/productoModerador/**").hasAuthority("MODERADOR")
                .requestMatchers(

                        "/api/comentario/**",
                        "/api/producto/actualizar/**",
                        "/api/producto/crear/**",
                        "/api/producto/obtener/**",
                        "/api/producto/listarUsuario/**",
                        "/api/producto/listarFavoritosUsuario/**",
                        "/api/descuento/**",
                        "/api/envio/**",
                        "/api/descuento/**",
                        "/api/favoritos/**",
                        "/api/detalleCompra/**",
                        "/api/compra/**",
                        "/api/comentario/crear/**").hasAuthority("CLIENTE")
                .requestMatchers(
                        "/api/calificacion/promedio/**",
                        "/api/imagenes/**").permitAll().anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
