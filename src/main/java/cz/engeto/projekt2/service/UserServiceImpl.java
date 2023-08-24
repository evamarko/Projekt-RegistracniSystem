package cz.engeto.projekt2.service;

import cz.engeto.projekt2.model.User;
import cz.engeto.projekt2.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveUser(User user) {
        return jdbcTemplate.update("INSERT INTO Users (name, surname, personId, personUuid) VALUES(?,?,?,?)",
                new Object[] {user.getName(), user.getSurname(), user.getPersonId(), user.getPersonUuid()});
    }

    @Override
    public UserResponse findUserById(long id) {
            return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE id=?",
                BeanPropertyRowMapper.newInstance(UserResponse.class), id);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM Users",
                BeanPropertyRowMapper.newInstance(UserResponse.class));
    }

    @Override
    public int updateUser(UserResponse userResponse) {
        return jdbcTemplate.update("UPDATE Users SET name = ?, surname = ?, personId = ?, personUuid = ? WHERE id=?",
                new Object[] {userResponse.getName(), userResponse.getSurname(), userResponse.getPersonId(), userResponse.getPersonUuid(), userResponse.getId()});
    }

    @Override
    public int deleteUserById(long id) {
        return jdbcTemplate.update("DELETE FROM Users WHERE id=?", id);
    }
}