package cz.engeto.projekt2.controller;

import cz.engeto.projekt2.model.User;
import cz.engeto.projekt2.model.UserResponse;
import cz.engeto.projekt2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            String uuid = UUID.randomUUID().toString();
            userService.saveUser(new User(user.getName(), user.getSurname(), user.getPersonId(), uuid));
            return new ResponseEntity<>(Settings.USER_CREATED, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Settings.USER_NOT_CREATED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") long id, @RequestParam(required = false) boolean detail) {
        UserResponse user = userService.findUserById(id);
        if (user != null) {
            if (detail) {
                UserResponse newUserDetail = new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getPersonId(), user.getPersonUuid());
                return new ResponseEntity<>(newUserDetail, HttpStatus.OK);
            }
            UserResponse newUser = new UserResponse(user.getId(), user.getName(), user.getSurname());
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam (required = false) boolean detail) {
        List<UserResponse> usersDetail = new ArrayList<>();
        usersDetail.addAll(userService.findAllUsers());
        if (usersDetail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<UserResponse> users = new ArrayList<>();
            if (!detail) {
                for (UserResponse userResponse : usersDetail) {
                    UserResponse newUserDetail = new UserResponse(userResponse.getId(), userResponse.getName(), userResponse.getSurname());
                    users.add(newUserDetail);
                }
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            return new ResponseEntity<>(usersDetail, HttpStatus.OK);
        }
    }
    
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody UserResponse userResponse) {
        Long userResponseId = Long.parseLong(userResponse.getId());
        UserResponse updatedUser = userService.findUserById(userResponseId);
        if (updatedUser != null) {
            updatedUser.setId(String.valueOf(userResponseId));
            updatedUser.setName(userResponse.getName());
            updatedUser.setSurname(userResponse.getSurname());
            userService.updateUser(updatedUser);
            return new ResponseEntity<>(Settings.USER_UPDATED, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Settings.USER_NOT_FOUND + userResponseId, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        int result = userService.deleteUserById(id);
        if (result == 0) {
            return new ResponseEntity<>(Settings.USER_NOT_FOUND + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(Settings.USER_DELETED, HttpStatus.OK);
    }
}
