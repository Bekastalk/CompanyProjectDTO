package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.User;
import peaksoft.service.UserService;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserApi {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    SimpleResponse save(@RequestBody User user){
        return userService.SaveUser(user);
    }
}
