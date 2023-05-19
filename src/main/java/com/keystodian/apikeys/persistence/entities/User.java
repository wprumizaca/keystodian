package com.keystodian.apikeys.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;


    @Column(unique = true)
    private String username;


    private String email;

    private String password;


    @ElementCollection(fetch = FetchType.EAGER) //Relacionamos con UserRole
    @Enumerated(EnumType.STRING) //la enumeracion de roles se almacenará en BBDD como string
    private Set<UserRole> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //nos devuelve los roles/permisos del usuario
        return roles
                .stream()
                .map(role-> new SimpleGrantedAuthority("ROLE_"+role.toString()))//Permisos que tendrá. No confundir ROL con AUTHORITIES
                .toList();
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
