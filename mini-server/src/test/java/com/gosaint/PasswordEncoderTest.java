package com.gosaint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        // 测试密码编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 原始密码
        String rawPassword = "admin123";
        
        // 生成加密密码
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("加密后的密码: " + encodedPassword);
        
        // 验证密码是否匹配（使用初始化脚本中的密码哈希）
        String initPasswordHash = "$2a$10$F8wXFDRlU20.tVkuBjQwvOPz4zia2D2x20HPySdDrXjSzcCcoATfa";
        boolean matches = encoder.matches(rawPassword, initPasswordHash);
        System.out.println("原始密码与初始化密码哈希匹配: " + matches);
    }
}