package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable final long id) {
        log.info("GET user: {}", id);
        return userRepository.findById(id)
                .map(UserDto::fromUser)
                .orElseThrow(() -> new NotFoundException(User.class.getSimpleName(), id));
    }

    @PostMapping
    public User createUser(@RequestBody final User request) {
        User user = User.from(request);
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User editUser(@PathVariable final long id, @RequestBody final User request) {
        User original = userRepository.getOne(id);
        original.copyAttributesFrom(request);
        return userRepository.save(original);
    }
}
