package mapper;

import com.sd.dto.PersonDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 9/24/2016.
 */
public class PersonDtoMapper implements ResultSetMapper<PersonDto>
{
    public PersonDto map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new PersonDto(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getBoolean("active"));
    }
}
