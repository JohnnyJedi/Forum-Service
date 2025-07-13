package telran.java58.accounting.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dao.AccountRepository;
import telran.java58.accounting.dto.*;
import telran.java58.accounting.model.Account;
import telran.java58.forum.dto.exceptions.ConflictException;
import telran.java58.forum.dto.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class AccountingServiceImpl implements AccountingService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        if (accountRepository.existsByLogin(userRegisterDto.getLogin())) {
            throw new ConflictException();
        }
        Account account = modelMapper.map(userRegisterDto, Account.class);
        account.addRole(Roles.USER);
        accountRepository.save(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserDto loginUser(AuthDto authDto) {
        Account account = accountRepository.findByLogin(authDto.getLogin());
        if (account != null && !account.getPassword().equals(authDto.getPassword())) {
            throw new ConflictException();
        }
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String username) {
        Account account = accountRepository.findById(username).orElseThrow(NotFoundException::new);
        accountRepository.delete(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto, String username) {
        Account account = accountRepository.findById(username).orElseThrow(NotFoundException::new);
        account.setFirstName(userUpdateDto.getFirstName());
        account.setLastName(userUpdateDto.getLastName());
        accountRepository.save(account);
        return modelMapper.map(account, UserDto.class);
    }

    @Override
    public UserUpdateRoleDto addRole(String username,String role) {
        Account account = accountRepository.findById(username).orElseThrow(NotFoundException::new);
        account.addRole(Roles.valueOf(role.toUpperCase()));
        accountRepository.save(account);
        return modelMapper.map(account, UserUpdateRoleDto.class);
    }

    @Override
    public UserUpdateRoleDto deleteRole(String username, String role) {
        Account account = accountRepository.findById(username).orElseThrow(NotFoundException::new);
        account.removeRole(Roles.valueOf(role.toUpperCase()));
        accountRepository.save(account);
        return modelMapper.map(account, UserUpdateRoleDto.class);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        Account account = accountRepository.findById(changePasswordDto.getLogin()).orElseThrow(NotFoundException::new);
        if (!account.getPassword().equals(changePasswordDto.getOldPassword())) {
            throw new ConflictException();
        }
        account.setPassword(changePasswordDto.getNewPassword());
        accountRepository.save(account);
    }

    @Override
    public UserDto getUserByLogin(String username) {
        Account account = accountRepository.findById(username).orElseThrow(NotFoundException::new);
        return modelMapper.map(account, UserDto.class);
    }
}
