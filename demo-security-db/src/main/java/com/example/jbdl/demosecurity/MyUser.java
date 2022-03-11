package com.example.jbdl.demosecurity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
