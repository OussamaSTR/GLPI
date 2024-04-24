package ma.emsi.glpi;


import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.User;
import ma.emsi.glpi.Services.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.ArrayList;
import java.util.Collection;

@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

    private UserServ userServ;

    public securityConfig(UserServ userServ) {
        this.userServ = userServ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // Rechercher l'utilisateur dans la base de données par son nom d'utilisateur
                User user = userServ.loadUserByUsername(username);

                // Créer un UserDetails à partir de l'utilisateur trouvé
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRoles().stream().map(Role::getRoleName).toArray(String[]::new))
                        .build();
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin();
        http.authorizeHttpRequests().anyRequest().authenticated();
    }
}

