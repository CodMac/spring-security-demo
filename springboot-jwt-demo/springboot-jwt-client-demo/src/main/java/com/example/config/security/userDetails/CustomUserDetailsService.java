package com.example.config.security.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    CustomUserRepo customUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 自定义用户来源, 可自由切换数据来源
         */
        CustomUser user = customUserRepo.findByCode(username);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new CustomUserDetails(user.getId(), user.getCode(), user.getPwd());
    }

    static final class CustomUserDetails extends CustomUser implements UserDetails {

        public CustomUserDetails(long id, String code, String password) {
            super(id, code, password);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return getPwd();
        }

        @Override
        public String getUsername() {
            return getCode();
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
}
