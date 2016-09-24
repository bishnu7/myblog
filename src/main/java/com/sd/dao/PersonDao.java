package com.sd.dao;

import com.sd.dto.PersonDto;
import mapper.PersonDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface PersonDao
{

    @SqlUpdate("insert into user (id, first_name, last_name, active) values (null, :firstName, :lastName, :active)")
    void insert(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("active") boolean active);

    @SqlQuery("SELECT * FROM user")
    @Mapper(PersonDtoMapper.class)
    List<PersonDto> findAll();

    void close();
}
