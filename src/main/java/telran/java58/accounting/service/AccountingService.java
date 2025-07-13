package telran.java58.accounting.service;

import org.apache.tomcat.util.http.parser.Authorization;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dto.*;


public interface AccountingService {
    UserDto registerUser(UserRegisterDto userRegisterDto);

    UserDto loginUser(AuthDto authDto);

    UserDto deleteUser(String username);

    UserDto updateUser(UserUpdateDto userUpdateDto,String username);

    UserUpdateRoleDto addRole(String username, String role);

    UserUpdateRoleDto deleteRole( String username, String role);

    void changePassword(ChangePasswordDto changePasswordDto);

    UserDto getUserByLogin( String username);
}
