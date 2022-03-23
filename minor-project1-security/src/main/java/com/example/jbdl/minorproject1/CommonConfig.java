package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    // TODO: NEED TO CHANGE THE AUTHORITIES
    @Value("${ADMIN_AUTHORITY}")
    private String ADMIN_AUTHORITY ;

    @Value("${STUDENT_ATTENDANCE_AUTHORITY}")
    private String STUDENT_ATTENDANCE_AUTHORITY;

    @Value("${STUDENT_ONLY_AUTHORITY}")
    private String STUDENT_ONLY_AUTHORITY;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: NEED TO ADD ANT MATHCERS
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/student/attendance").hasAuthority(STUDENT_ATTENDANCE_AUTHORITY)
                .antMatchers("/student/**").hasAuthority(STUDENT_ONLY_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();

    }
    @Bean
    public PasswordEncoder getPE(){
        return  new BCryptPasswordEncoder();
    }

//    public LettuceConnectionFactory getRedisFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
//                "10.241.3.119", 6379);
//        LettuceConnectionFactory jedisConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> getTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());    // for list in
//        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());//for hash in redis
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setConnectionFactory(getRedisFactory());
//        return redisTemplate;
//
//    }
}
