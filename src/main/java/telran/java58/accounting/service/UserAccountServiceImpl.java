package telran.java58.accounting.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dao.UserAccountRepository;
import telran.java58.accounting.dto.*;
import telran.java58.accounting.dto.exceptions.InvalidDataException;
import telran.java58.accounting.dto.exceptions.UserExistsException;
import telran.java58.accounting.model.UserAccount;
import telran.java58.forum.dto.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner {
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        if (userAccountRepository.existsByLogin(userRegisterDto.getLogin())) {
            throw new UserExistsException();
        }
        UserAccount account = modelMapper.map(userRegisterDto, UserAccount.class);
        account.addRole(Roles.USER);
        String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
        account.setPassword(password);
        userAccountRepository.save(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String username) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        userAccountRepository.delete(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto, String username) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        if (userUpdateDto.getFirstName() != null) {
            account.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            account.setLastName(userUpdateDto.getLastName());
        }
        userAccountRepository.save(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserUpdateRoleDto addRole(String username, String role) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        try {
            account.addRole(Roles.valueOf(role.toUpperCase()));
        } catch (Exception e) {
            throw new InvalidDataException();
        }
        userAccountRepository.save(account);
        return modelMapper.map(account, UserUpdateRoleDto.class);
    }

    @Override
    public UserUpdateRoleDto deleteRole(String username, String role) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        try {
            account.removeRole(Roles.valueOf(role.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException();
        }
        userAccountRepository.save(account);
        return modelMapper.map(account, UserUpdateRoleDto.class);
    }


    @Override
    public UserDto getUserByLogin(String username) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        UserAccount account = userAccountRepository.findById(username).orElseThrow(NotFoundException::new);
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        account.setPassword(hashedPassword);
        userAccountRepository.save(account);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userAccountRepository.existsById("admin")){
            UserAccount admin = UserAccount.builder()
                    .login("admin")
                    .password(BCrypt.hashpw("admin",BCrypt.gensalt()))
                    .firstName("Admin")
                    .lastName("Admin")
                    .role(Roles.USER)
                    .role(Roles.ADMINISTRATOR)
                    .role(Roles.MODERATOR)
                    .build();
            userAccountRepository.save(admin);
        }
    }
}
