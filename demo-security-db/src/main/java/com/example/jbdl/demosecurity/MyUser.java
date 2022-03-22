package com.example.jbdl.demosecurity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser implements UserDetails {

    private static final String DELEMITER =":";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String userName;
    private String password;
    private String authorities; // deploy:monitor (will store in the DB in this way each authority is separate by a delimiter .
    /*
                       Developer     Manager     CEO     Director      VP
         Deploy            Y            Y         N         N          N
         Monitoring        Y            Y         Y         Y          Y

         Designation :
         .antmatchers("/monitor/**").hasAnyAuthority("Developer","Manager","CEO", "Director" ,"VP")
         .antmatchers("/deploy/**").hasAnyAuthority("Developer","Manager")

         Roles that a user can perform : (this one more scalable)
         .antmatchers("/monitor/**").hasAuthority("monitor")
         .antmatchers("/deploy/**).hasAuthority("deploy")
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String[] authority_list = this.authorities.split(DELEMITER);
        return Arrays.stream(authority_list)
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
