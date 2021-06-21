package com.example.service;

import com.example.controller.dao.InsertUserRequest;
import com.example.data.User;
import com.example.data.UserRepository;
import com.example.exception.NoRecordFoundException;
import com.example.model.UserModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() throws NoRecordFoundException {
        List<User> user = userRepository.findAll();
        if (user != null && !user.isEmpty()){
            log.info("Users found");
            return user.stream().map(
                    user1 -> new UserModel(user1.getName(), user1.getSalary())
            ).collect(Collectors.toList());
        } else {
            log.info("No users");
            throw new NoRecordFoundException("No Users");
        }
    }

    public List<UserModel> getByName(String name) throws NoRecordFoundException {
        List<User> user = userRepository.findByName(name);
        if(!user.isEmpty()) {
            log.info("user found");
            return user.stream().map(
                    userDB -> new UserModel(userDB.getName(),userDB.getSalary())
            ).collect(Collectors.toList());
        }else{
            throw new NoRecordFoundException("user not found");
        }
    }

    public void addUser(InsertUserRequest user) throws NoRecordFoundException {
        if (user != null) {
            log.info("add is successful");
            userRepository.save(new User(user.getId(),user.getName(),user.getSalary()));
        }else {
            throw new NoRecordFoundException("add is failed");
        }
    }

    public void updateUser(String salary ,String id) throws NoRecordFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setSalary(salary);
            this.userRepository.save(user.get());
            log.info("update is successful");
        } else
            throw new NoRecordFoundException("update is failed");
    }

        public String deleteUser (String id) throws EmptyResultDataAccessException {

            userRepository.deleteById(id);
            log.info("delete is successful");
            return "delete is successful";
        }
    }
