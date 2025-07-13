package telran.java58.accounting.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dto.*;
import telran.java58.accounting.service.AccountingService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountingController {
    private final AccountingService accountingService;


    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        return accountingService.registerUser(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto loginUser(@RequestBody AuthDto authDto) {
        return accountingService.loginUser(authDto);
    }

    @DeleteMapping("/user/{user}")
    public UserDto deleteUser(@PathVariable String user) {
        return accountingService.deleteUser(user);
    }

    @PatchMapping("/user/{user}")
    public UserDto updateUser(@RequestBody UserUpdateDto userUpdateDto, @PathVariable String user) {
        return accountingService.updateUser(userUpdateDto, user);
    }

    @PatchMapping("/user/{user}/role/{role}")
    public UserUpdateRoleDto addRole(@PathVariable("user") String username, @PathVariable String role) {
        return accountingService.addRole(username, role);
    }

    @DeleteMapping("/user/{user}/role/{role}")
    public UserUpdateRoleDto deleteRole(@PathVariable("user") String username, @PathVariable String role) {
        return accountingService.deleteRole(username, role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        accountingService.changePassword(changePasswordDto);
    }

    @GetMapping("/user/{user}")
    public UserDto getUserByLogin(@PathVariable("user") String username) {
        return accountingService.getUserByLogin(username);
    }
}
