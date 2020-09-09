package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.request.PersonDTO;
import com.example.demo.dto.response.MessageResponseDTO;
import com.example.demo.entities.Person;
import com.example.demo.exception.PersonNotFoundException;
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
        return MessageResponseDTO.builder().message("Criado pessoa com o ID " + savedPerson.getId()).build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verificaPerson(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verificaPerson(id);

        personRepository.deleteById(id);

    }

    public MessageResponseDTO update(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
        verificaPerson(id);
        Person updatePersonTosave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(updatePersonTosave);
        return MessageResponseDTO.builder().message("Pessoa com o ID " + savedPerson.getId() + " atualizado com sucesso")
                .build();
    }

    private Person verificaPerson(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
