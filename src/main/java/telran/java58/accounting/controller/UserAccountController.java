package telran.java58.accounting.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.java58.accounting.dto.*;
import telran.java58.accounting.service.UserAccountService;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;


    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return userAccountService.registerUser(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto loginUser(Principal principal) {
        return userAccountService.getUserByLogin(principal.getName());
    }

    @DeleteMapping("/user/{user}")
    public UserDto deleteUser(@PathVariable String user) {
        return userAccountService.deleteUser(user);
    }

    @PatchMapping("/user/{user}")
    public UserDto updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable String user) {
        return userAccountService.updateUser(userUpdateDto, user);
    }

    @PatchMapping("/user/{user}/role/{role}")
    public UserUpdateRoleDto addRole(@PathVariable("user") String username, @PathVariable String role) {
        return userAccountService.addRole(username, role);
    }

    @DeleteMapping("/user/{user}/role/{role}")
    public UserUpdateRoleDto deleteRole(@PathVariable("user") String username, @PathVariable String role) {
        return userAccountService.deleteRole(username, role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void changePassword(Principal principal, @RequestHeader("X-Password") @Size(min = 4, max = 20, message = "password must be between 4 and 20 characters")
    String newPassword) {
        userAccountService.changePassword(principal.getName(), newPassword);
    }

    @GetMapping("/user/{user}")
    public UserDto getUserByLogin(@PathVariable("user") String username) {
        return userAccountService.getUserByLogin(username);
    }
}
