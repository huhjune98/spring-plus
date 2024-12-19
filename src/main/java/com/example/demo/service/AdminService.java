package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {
        //모든 사용자 한번의 쿼리로 갖고오기
        List<User> users = userRepository.findAllById(userIds);

        if(users.size() != userIds.size()) {
            throw new IllegalArgumentException("사용자 ID에 해당하는 값이 일부 존재하지 않습니다.");
        }

        //상태 일괄 변경
        users.forEach(User::updateStatusToBlocked);
        //변경된 내용 한번에 저장
        userRepository.saveAll(users);
    }
}
