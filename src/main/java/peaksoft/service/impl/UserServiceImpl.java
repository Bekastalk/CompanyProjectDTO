package peaksoft.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.User;
import peaksoft.repasitory.UserRepository;
import peaksoft.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public SimpleResponse SaveUser(User user) {
        userRepository.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s successfully saved",user.getId()))
                .build();
    }
}
