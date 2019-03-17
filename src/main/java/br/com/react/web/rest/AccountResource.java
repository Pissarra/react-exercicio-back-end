package br.com.react.web.rest;

import br.com.react.service.UserService;
import br.com.react.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to authenticate USER : {}", userDTO);
        Optional<UserDTO> userAuthenticated = userService.findByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return userAuthenticated.map(user -> ResponseEntity.ok(user)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
