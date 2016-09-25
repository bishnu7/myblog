package com.sd.dao;

import com.sd.dto.PersonDto;

import java.util.List;

public interface PersonDao
{
    PersonDto findById(int id);

    List<PersonDto> findByCity(String city);

    PersonDto findByFirstName(String firstName);
}
