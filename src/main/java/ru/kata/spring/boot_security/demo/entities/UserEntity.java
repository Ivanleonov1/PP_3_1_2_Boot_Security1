package ru.kata.spring.boot_security.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Transient
    private String confirm;
    private String nickname;
    private String phoneNumber;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleEntity> roles;

    public UserEntity(String username, String password, String nickname, String phoneNumber, Collection<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles != null
                ? new HashSet<>(roles)
                : Collections.emptySet();
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirm='" + confirm + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roles=" + roles +
                '}';
    }
}
