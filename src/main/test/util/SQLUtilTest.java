package util;


import com.sd.util.SQLUtil;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SQLUtilTest
{
    @Test
    public void testGetColumnsString()
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("first_name", "Sam");
        map.put("active", true);
        String exceptedResult = "first_name = 'Sam' AND active = '1'";
        String actualResult = SQLUtil.getColumns(map);
        assertEquals(exceptedResult, actualResult);
    }

    @Test
    public void testCreateSelect()
    {
        String tableName = "user";
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("first_name", "Sam");
        map.put("active", true);

        String exceptedResult = "SELECT * FROM user WHERE first_name = 'Sam' AND active = '1'";
        String actualResult =  SQLUtil.createSelect(tableName, map);

        assertEquals(exceptedResult, actualResult);
    }
}
