package br.com.react.service;

import br.com.react.service.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    ClientDTO save(ClientDTO clientDTO);

    List<ClientDTO> getAll();
}
