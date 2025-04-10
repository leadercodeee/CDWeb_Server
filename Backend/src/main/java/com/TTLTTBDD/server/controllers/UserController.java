package com.TTLTTBDD.server.controllers;

import com.TTLTTBDD.server.models.dto.UserDTO;
import com.TTLTTBDD.server.models.entity.User;
import com.TTLTTBDD.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:31415"})
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id")
    public Optional<UserDTO> getUserById(@RequestParam int id){
        return userService.getUserById(id);
    }

    @CrossOrigin(origins = {"http://localhost:31415"})
    @PostMapping("/login")
    public Optional<UserDTO> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @CrossOrigin(origins = {"http://localhost:31415"})
    @PostMapping("/register")
    public UserDTO register(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping("/updateInfoAccount")
    public UserDTO updateUser(@RequestParam("id") Integer id, @RequestParam("username") String username, @RequestParam("fullname") String fullname, @RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("email") String email) {
        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .username(username)
                .fullname(fullname)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
        return userService.updateUserInfoAccount(userDTO);
    }

    @PutMapping("/updateAvata")
    public UserDTO updateUser(@RequestParam("id") Integer id, @RequestParam("avataFile") MultipartFile avataFile) {
        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .build();
        return userService.updateUserAvata(userDTO, avataFile); 
    }

    @PutMapping("/update")
    public UserDTO updateUser(
            @RequestParam("id") Integer id,
            @RequestParam("username") String username,
            @RequestParam("fullname") String fullname,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("role") boolean role,
            @RequestParam(value = "avataFile", required = false) MultipartFile avataFile) {

        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .username(username)
                .fullname(fullname)
                .address(address)
                .phone(phone)
                .email(email)
                .role(role)
                .build();

        return userService.updateUser(userDTO, avataFile);  // Chuyển file avatar nếu có, nếu không thì null
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "User with ID " + id + " has been deleted.";
    }
}
