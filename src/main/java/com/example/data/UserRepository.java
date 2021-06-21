package com.example.data;

import com.example.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
     List<User> findByName (String name);

}
