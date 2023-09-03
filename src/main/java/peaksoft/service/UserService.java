package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.entity.User;

public interface UserService {
    SimpleResponse SaveUser(User user);
}
