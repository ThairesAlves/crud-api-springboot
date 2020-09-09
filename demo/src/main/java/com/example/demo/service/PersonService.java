package com.example.demo.service;

import com.example.demo.dto.request.PersonDTO;
import com.example.demo.dto.response.MessageResponseDTO;
import com.example.demo.entities.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public MessageResponseDTO createPerson(PersonDTO personDto) {
        Person personTosave = personMapper.toModel(personDto);

        Person savedPerson = personRepository.save(personTosave);
        return MessageResponseDTO.builder()
            .message("Criado pessoa com o ID" + savedPerson.getId())
            .build();
    }

}
