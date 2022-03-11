package uz.pdp.pdpspring7thlessonn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.pdpspring7thlessonn.entity.enums.Permissions;
import uz.pdp.pdpspring7thlessonn.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbsEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Lavozim lavozim;

    private boolean enabled;

    private boolean accountNonExpired=true;

    private boolean accountNonLocked=true;

    private boolean credentialsNonExpired=true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissions> huquqList = this.lavozim.getHuquqList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Permissions permissions : huquqList) {
//            grantedAuthorities.add((GrantedAuthority) () -> permissions.name());
//        }
        for (Permissions permissions : huquqList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissions.name()));
        }
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Lavozim lavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }
}
