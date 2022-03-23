package com.example.jbdl.minorproject1.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "myUser")
public class User implements UserDetails  {
    @Id
    @SequenceGenerator(name="MyUserSequence" , sequenceName = "MyUserSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "MyUserSequence")
    private int id ;

    @Value("{authorities.delimiter}")
    private String DELIMITER ;

    private String userName;
    private String password;

    private String authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String[] authority_list = this.authorities.split(DELIMITER);
        return Arrays.stream(authority_list).map(x-> new SimpleGrantedAuthority(x)).collect(Collectors.toList());
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
