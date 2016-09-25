package com.sd.dao;

import com.sd.dto.PersonDto;

public interface PersonDao
{
    PersonDto findById(int id);
}
