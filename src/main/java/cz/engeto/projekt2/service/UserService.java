package cz.engeto.projekt2.service;


import cz.engeto.projekt2.model.User;
import cz.engeto.projekt2.model.UserResponse;

import java.util.List;

public interface UserService {

    int saveUser(User user);
    int update(UserResponse userResponse);
    UserResponse findUserById(long id);
    List<UserResponse> findAllUsers();
    int deleteById(long id);
}
