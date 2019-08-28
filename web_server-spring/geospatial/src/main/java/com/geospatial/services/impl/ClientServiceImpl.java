package com.geospatial.services.impl;

import java.util.List;

import com.geospatial.dtos.ClientDTO;
import com.geospatial.entities.Client;
import com.geospatial.exceptions.EmailExistsException;
import com.geospatial.exceptions.UsernameExistsException;
import com.geospatial.repositories.ClientRepo;
import com.geospatial.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public List<Client> getClients() {
        return clientRepo.getClients();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client getClientByUsername(String username){
        return clientRepo.findClientByUsername(username);
    }

    @Override
    public Client getClientByEmail(String email){
        return clientRepo.findClientByEmail(email);
    }

    @Override
    public String deleteClientByUsername(String username){
        return "deleted " + username;
    }

    @Override
    @Transactional
    public Client registerClient(Client client) throws UsernameExistsException, EmailExistsException{
        Client check = clientRepo.findClientByUsername(client.getname());
        if(check != null) {
            throw new UsernameExistsException("That username is already in use");
        }
        check = clientRepo.findClientByEmail(client.getEmail());
        if(check != null) {
            throw new EmailExistsException("That email is already in use");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepo.save(client);

    }
    
    @Override
    @Transactional
    public Client createClientDTO(ClientDTO clientDto) throws UsernameExistsException, EmailExistsException{
        Client check = clientRepo.findClientByUsername(clientDto.getname());
        if(check != null){
            throw new UsernameExistsException("That username is already in use");
        }
        check = clientRepo.findClientByEmail(clientDto.getEmail());
        if(check != null) {
            throw new EmailExistsException("That email is already in use");
        }
        Client newClient = new Client(clientDto.getname(), clientDto.getEmail(), passwordEncoder.encode(clientDto.getPassword()));
        newClient.setRoles("CLIENT");
        return clientRepo.save(newClient);
    }
}