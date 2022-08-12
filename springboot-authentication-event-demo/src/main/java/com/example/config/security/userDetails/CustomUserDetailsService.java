package com.example.config.security.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 1. 页面提交的 `username` 和 `password` 会被 UsernamePasswordAuthenticationFilter 拦截捕获, 后面使用 `ui-username` 、`ui-password` 特指
 * 2. 通过 loadUserByUsername(userName) 获得 UserDetails, 这里传递的`userName`变量为 `ui-username`
 * 3. UserDetails 的 password 属性 会与 经过 PasswordEncoder 编码后的 `ui-password` 进行比较
 */
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
