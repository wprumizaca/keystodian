package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.exceptions.NombreExistenteException;
import com.keystodian.apikeys.expose.dto.dtoUser.CreateUserRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.mapstruct.IUserMapper;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.entities.UserRole;
import com.keystodian.apikeys.persistence.repository.AuthRepository;
import com.keystodian.apikeys.services.contract.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {


    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    private final IUserMapper iUserMapper; //mapeador

    @Override
    public Optional<User> findByUser(String username) {

        return authRepository.findByUsername(username);

    }

    @Override
    public UserResponse save(CreateUserRequest createUserDTO, Set<UserRole> roles) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRoles(roles);

        try {
            authRepository.save(user);
            UserResponse getUserDTO = iUserMapper.mapToDto(user);
            return getUserDTO;

        } catch (DataIntegrityViolationException ex) { //Se usa para el Nombre de usuario (unique=true)
            throw  new NombreExistenteException();
        }
    }

    public UserResponse saveClient(CreateUserRequest createUserDTO){
        return save(createUserDTO, Set.of(UserRole.CLIENT)) ;
    }

    public UserResponse saveMaster(CreateUserRequest createUserDTO){
        return save(createUserDTO, Set.of(UserRole.MASTER));
    }

    public UserResponse saveAdminApp(CreateUserRequest createUserDTO){
        return save(createUserDTO, Set.of(UserRole.ADMIN_APP)) ;
    }

    public UserResponse saveAdminUser(CreateUserRequest createUserDTO){
        return save(createUserDTO, Set.of(UserRole.ADMIN_USER));
    }

}
