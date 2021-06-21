package com.example.controller;

import com.example.controller.dao.InsertUserRequest;
import com.example.controller.dao.SuccessResponse;
import com.example.controller.dao.UpdateUserRequest;
import com.example.controller.dao.UserResponse;
import com.example.exception.NoRecordFoundException;
import com.example.model.UserModel;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/User")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        try {
            return new ResponseEntity<>(userService.getAllUsers().stream().map(
                    user -> new UserResponse(user.getName(), user.getSalary())
            ).collect(Collectors.toList()), HttpStatus.OK);
        } catch (NoRecordFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getByName(@PathVariable String id){
        try {
            UserModel userModel = (UserModel) userService.getByName(id);
            return new ResponseEntity<>(
                    new UserResponse(userModel.getName(), userModel.getSalary())
                    ,HttpStatus.OK);
        }catch (NoRecordFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/User")
    public ResponseEntity<SuccessResponse> addUser(@RequestBody InsertUserRequest user){
        try {
            userService.addUser(user);
            return new ResponseEntity<>(new SuccessResponse("add is successful"),HttpStatus.OK);
        }catch (NoRecordFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/User/{id}")
    public ResponseEntity<SuccessResponse> updateUser(@RequestBody UpdateUserRequest user, @PathVariable String id){
        try {
            userService.updateUser(user.getSalary(), id);
            return new ResponseEntity<>(new SuccessResponse("update is successful"),HttpStatus.OK);
        }catch (NoRecordFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/User/{id}")
    public ResponseEntity<SuccessResponse>  deleteUser(@PathVariable String id){
        try {
            return new ResponseEntity<>(new SuccessResponse(userService.deleteUser(id)),HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}   }
}


