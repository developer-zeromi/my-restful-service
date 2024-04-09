package kr.co.kimyoungmi.myrestfulservice.controller;

import jakarta.validation.Valid;
import kr.co.kimyoungmi.myrestfulservice.bean.User;
import kr.co.kimyoungmi.myrestfulservice.dto.RetrieveAllUsersDto;
import kr.co.kimyoungmi.myrestfulservice.exception.UserNotFoundException;
import kr.co.kimyoungmi.myrestfulservice.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {

    private UserRepository userRepository;

    public UserJPAController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity retrieveAllUsers() {
        List<User> users = userRepository.findAll();
        int count = (int) users.stream().count();

        RetrieveAllUsersDto usersDto = new RetrieveAllUsersDto(count, users);

        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        EntityModel entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users http://localhost:8088/users

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
