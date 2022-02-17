package ir.maktab.homeserviceprovidersystemwithspringboot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletContext;

@Configuration

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    private final UserDetailsService userDetailsService;



    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/customer/signup", "/customer/login",
                        "/expert/signup", "/expert/login",
                        "/admin/login").permitAll()
                .antMatchers("/css/**", "/image/**", "/js/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    //admin
    @Configuration
    @Order(1)
    public class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;

        public App1ConfigurationAdapter(@Qualifier("adminDetailService") UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/login").permitAll()
                    .antMatchers("/css/**", "/image/**", "/js/**").permitAll()
                    .anyRequest()
                    .hasRole("ADMIN")

                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    /*.loginProcessingUrl("/admin/login")*/
                    .defaultSuccessUrl("/admin/dashboard")


                    .and()
                    .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }
    }

    //customer
    @Configuration
    @Order(2)
    public class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;


        public App2ConfigurationAdapter(@Qualifier("customerDetailService") UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }


        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/customer/**")
                    .authorizeRequests()
                    .antMatchers("/customer/signup", "/customer/login").permitAll()
                    .antMatchers("/css/**", "/image/**", "/js/**").permitAll()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest()
                    .hasRole("CUSTOMER")
                    .and()
                    .formLogin()
                    .loginPage("/customer/login")
                    /*.loginProcessingUrl("/customer/login")*/
                    .failureUrl("/loginUser?error=loginError")
                    .defaultSuccessUrl("/customer/dashboard")

                    .and()
                    .logout()
                    .logoutUrl("/costumer/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }
    }

    //expert
    @Configuration
    @Order(3)
    public class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;


        public App3ConfigurationAdapter(@Qualifier("expertDetailService") UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }


        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/expert/**")
                    .authorizeRequests()
                    .antMatchers("/expert/signup", "/expert/login").permitAll()
                    .antMatchers("/css/**", "/image/**", "/js/**").permitAll()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest()
                    .hasRole("EXPERT")

                    .and()
                    .formLogin()
                    .loginPage("/expert/login")
                    /*.loginProcessingUrl("/customer/login")*/
                    .failureUrl("/loginUser?error=loginError")
                    .defaultSuccessUrl("/expert/dashboard")
                   /* .formLogin()
                    .loginPage("/expert/login")
                    *//*.loginProcessingUrl("/expert/login")*//*
                    .failureUrl("/loginUser?error=loginError")
                    .defaultSuccessUrl("/expert/dashboard")*/


                    .and()
                    .logout()
                    .logoutUrl("/costumer/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }
    }
}


