package com.example.demo.mapper;

import com.example.demo.dto.request.PersonDTO;
import com.example.demo.entities.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MMM-yyyy")
    Person toModel(PersonDTO dto);
    
    PersonDTO toDTO (Person dto);

    
}
