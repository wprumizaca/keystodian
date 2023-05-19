package com.keystodian.apikeys.services.impl;



import com.keystodian.apikeys.exceptions.IdUserNotFoundException;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserEmailDTO;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserPasswDTO;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.mapstruct.IUserMapper;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.UserRepository;
import com.keystodian.apikeys.services.contract.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper iUserMapper;
    private final PasswordEncoder passwordEncoder;


    /**
     INICIO Buscar usuario
     */
    @Override
    public List<UserResponse> findAll() {

        return userRepository.findAll()
                .stream()
                .map(user -> iUserMapper.mapToDto(user))
                .collect(Collectors.toList());

    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IdUserNotFoundException(id));

        UserResponse getUserDTO = iUserMapper.mapToDto(user);
        return getUserDTO;
    }
    /**
     FIN Buscar usuario
     */


    /**
     INICIO Modificar email y/o password
     */
    @Override
    public UserResponse editEmail(UpdateUserEmailDTO updateUserEmailDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IdUserNotFoundException(id));

        user.setEmail(updateUserEmailDTO.getEmail());
        userRepository.save(user);

        return iUserMapper.mapToDto(user); //Me devuelve un GetUsetDTO
    }

    @Override
    public UserResponse editPassw(UpdateUserPasswDTO updateUserPasswDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IdUserNotFoundException(id));

        user.setPassword(passwordEncoder.encode(updateUserPasswDTO.getPassword()));
        userRepository.save(user);

        return iUserMapper.mapToDto(user); //Me devuelve un GetUsetDTO
    }

    /**
     FIN Modificar email y/o password
     */


    /**
     INICIO Borrar Usuario
     */
    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw  new IdUserNotFoundException(id);
        }
    }

    @Override
    public void delete(User user) {
        deleteById(user.getId());
    }

    /**
     FIN Borrar Usuario
     */

}
