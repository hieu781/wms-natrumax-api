package com.natrumax.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.natrumax.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String accountName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String detail;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final boolean status;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String accountName, String email, String phoneNumber, String address,
                           String detail, LocalDateTime createDate, LocalDateTime modifyDate,
                           boolean status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.accountName = accountName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detail = detail;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().getName().name())
        );

        return new UserDetailsImpl(
                user.getId(),
                user.getAccountName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getDetail(),
                user.getCreateDate(),
                user.getModifyDate(),
                user.isStatus(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDetail() {
        return detail;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public boolean isStatus() {
        return status;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // Security policy may require handling password differently
    }

    @Override
    public String getUsername() {
        return accountName; // Or email if required
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
        return status;
    }
}
