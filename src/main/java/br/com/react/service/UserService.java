package br.com.react.service;

import br.com.react.service.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByNameAndPassword(String name, String password);
}
