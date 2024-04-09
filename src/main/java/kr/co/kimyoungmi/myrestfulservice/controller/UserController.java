package kr.co.kimyoungmi.myrestfulservice.controller;

import jakarta.validation.Valid;
import kr.co.kimyoungmi.myrestfulservice.bean.User;
import kr.co.kimyoungmi.myrestfulservice.dao.UserDaoService;
import kr.co.kimyoungmi.myrestfulservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        return user;
    }

    // CREATE
    // input - details of user
    // output - CREATED & Return thr created URI
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // 현재 요청에 대한 URI 생성 => 조회 용도의 URI 반환
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if (deletedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return ResponseEntity.noContent().build();
    }
}
