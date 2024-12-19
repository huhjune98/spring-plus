package com.example.demo;

import com.example.demo.util.PasswordEncoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PasswordEncoderTest {

    @Test
    public void testEncode() {
        String rawPassword = "password123";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // 암호화된 비밀번호가 원본과 다르다는 것을 확인
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    public void testMatches() {
        String rawPassword = "password123";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // 원본 비밀번호와 암호화된 비밀번호가 일치하는지 확인
        assertTrue(PasswordEncoder.matches(rawPassword, encodedPassword));
    }
}