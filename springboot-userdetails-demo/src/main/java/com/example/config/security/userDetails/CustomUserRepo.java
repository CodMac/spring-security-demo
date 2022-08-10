package com.example.config.security.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomUserRepo {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, CustomUser> map = null;

    @PostConstruct
    public void init(){
        map = new HashMap();
        map.put("machao", new CustomUser(1, "machao", passwordEncoder.encode("machao")));
        map.put("zhangfei", new CustomUser(1, "zhangfei", passwordEncoder.encode("zhangfei")));
        map.put("liubei", new CustomUser(1, "liubei", passwordEncoder.encode("liubei")));
        map.put("guangyu", new CustomUser(1, "guangyu", passwordEncoder.encode("guangyu")));
    }

    public CustomUser findByCode(String code) {
        return map.get(code);
    }
}
