package com.sd.dao;

import com.sd.dao.connection.DbConnection;
import com.sd.dto.PersonDto;
import com.sd.sql.PersonDaoSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PersonDaoImpl implements PersonDao
{

    public PersonDto findById(int id)
    {
        PersonDto personDto;
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        Map<Integer, Object> prepStmtParams = new LinkedHashMap<Integer, Object>();
        params.put("sql", PersonDaoSQL.findById());
        params.put("class", PersonDto.class);
        prepStmtParams.put(1, id);
        params.put("stmtParams", prepStmtParams);
        GenericResultFinder<PersonDto> resultFinder = new GenericResultFinder<PersonDto>();
        personDto = resultFinder.find(params);
        return personDto;
    }

    public PersonDto findByFirstName(String firstName)
    {
        Connection connection = DbConnection.getDbConnection();
        PreparedStatement prepStmt;
        PersonDto personDto = null;
        try
        {
            prepStmt = connection.prepareStatement(PersonDaoSQL.findByFirstName());
            prepStmt.setString(1, firstName);
            ResultSet rs = prepStmt.executeQuery();
            ResultSetMapper<PersonDto> mapper = new ResultSetMapper<PersonDto>();
            personDto = mapper.mapResultSetToObject(rs, PersonDto.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return personDto;
    }

    public List<PersonDto> findByCity(String city) {
        List<PersonDto> personDtos = null;
        Connection connection = DbConnection.getDbConnection();
        Integer cityId = null;
        PreparedStatement prepStmt;
        try
        {
            prepStmt = connection.prepareStatement("SELECT id FROM city WHERE name = ?");
            prepStmt.setString(1, city);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next())
            {
                cityId = rs.getInt("id");
            }
            prepStmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            prepStmt = connection.prepareStatement(PersonDaoSQL.findByCity());
            prepStmt.setInt(1, cityId);
            ResultSet rs = prepStmt.executeQuery();
            ResultSetMapper<PersonDto> resultSetMapper = new ResultSetMapper<PersonDto>();
            personDtos = resultSetMapper.mapResultSetToObjects(rs, PersonDto.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        DbConnection.close(connection);

        return personDtos;
    }
}
