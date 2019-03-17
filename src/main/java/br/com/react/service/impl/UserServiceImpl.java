package br.com.react.service.impl;

import br.com.react.domain.User;
import br.com.react.repository.UserRepository;
import br.com.react.service.UserService;
import br.com.react.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static ModelMapper modelMapper = new ModelMapper();

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> findByNameAndPassword(String name, String password) {
        Optional<User> user = userRepository.findOneByNameAndPassword(name, password);
        if (user.isPresent()) {
            return Optional.of(modelMapper.map(user.get(), UserDTO.class));
        }
        return Optional.empty();
    }
}
