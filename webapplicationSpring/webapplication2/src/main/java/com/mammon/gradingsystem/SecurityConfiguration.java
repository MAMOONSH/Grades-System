package com.mammon.gradingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    GradingSystemDAO gradingSystemDAO;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(gradingSystemDAO.getDataSource()).usersByUsernameQuery("select id,password,'true' as enabled" +
                " from students where id=?;")
                .authoritiesByUsernameQuery("select id,'STUDENT' as authority" +
                        " from students where id=?;");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/*").authenticated()
                .and().formLogin().defaultSuccessUrl("/",true);

    }
}
