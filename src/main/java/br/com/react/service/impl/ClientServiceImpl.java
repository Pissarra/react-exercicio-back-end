package br.com.react.service.impl;

import br.com.react.domain.Client;
import br.com.react.repository.ClientRepository;
import br.com.react.service.ClientService;
import br.com.react.service.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static ModelMapper modelMapper = new ModelMapper();

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO save(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client.getPhones().forEach(a -> a.setClient(client));
        return modelMapper.map(clientRepository.save(client), ClientDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAll() {
        return clientRepository.findAll().stream()
                .map(a -> modelMapper.map(a, ClientDTO.class))
                .collect(Collectors.toList());
    }
}
