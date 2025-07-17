package telran.java58.accounting.service;

import telran.java58.accounting.dto.*;


public interface UserAccountService {
    UserDto registerUser(UserRegisterDto userRegisterDto);


    UserDto deleteUser(String username);

    UserDto updateUser(UserUpdateDto userUpdateDto, String username);

    UserUpdateRoleDto addRole(String username, String role);

    UserUpdateRoleDto deleteRole(String username, String role);

    UserDto getUserByLogin(String username);

    void changePassword(String username, String newPassword);
}
