package services.rest.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class ToJASON {

	
	public JSONArray toJASONArray(ResultSet rs) throws JSONException
	{
		JSONArray json = new JSONArray();
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next())
			{
				JSONObject obj = new JSONObject();
				int columnCount = rsmd.getColumnCount();
				for(int i=1;i<=columnCount;i++)
				{
					String column_name = rsmd.getColumnName(i);
					System.out.println(rsmd.getColumnTypeName(i));
					if(rsmd.getColumnType(i)==java.sql.Types.ARRAY)
					{
						
							obj.put(column_name, rs.getArray(column_name));
							System.out.println("to Jason: Array -");
						
					}
					
					
					
					else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER)
					{
						obj.put(column_name, rs.getInt(column_name));
						
						System.out.println("to Jason:  INTEGER-");
					}
					
					else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT)
					{
						obj.put(column_name, rs.getInt(column_name));
						
						System.out.println("to Jason:  BIGINT-");
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.DECIMAL)
					{
						obj.put(column_name, rs.getInt(column_name));
						
						System.out.println("to Jason:  DECIMAL-");
					}
					
					else if(rsmd.getColumnType(i)==(java.sql.Types.VARCHAR))
					{
						obj.put(column_name, rs.getString(column_name));
						System.out.println("to Jason: String -");
					}
					else
					{
						obj.put(column_name, rs.getString(column_name));
						System.out.println("to Jason: ANY -");
					}
					
				}
				json.put(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return json;
	}
}
