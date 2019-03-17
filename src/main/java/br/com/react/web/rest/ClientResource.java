package br.com.react.web.rest;

import br.com.react.service.ClientService;
import br.com.react.service.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/clients")
//    @Secured(Authority.ADMIN)
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientDTO);
        if (clientDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        ClientDTO client =clientService.save(clientDTO);
        return ResponseEntity.created(new URI("/api/clients/" + client.getId()))
                .body(client);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        log.debug("REST request to get all Clients");
        List<ClientDTO> list = clientService.getAll();
        return ResponseEntity.ok(list);
    }
}
