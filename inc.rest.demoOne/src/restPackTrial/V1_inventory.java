package restPackTrial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.dom.ds.ManageDataSource;

import services.rest.util.ToJASON;


@Path("/v1/inventory")
public class V1_inventory {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getInventory() throws SQLException
	{
		
		String myString=null;
		Connection conn = null;
		PreparedStatement query=null;
		
		try
		{
			conn = ManageDataSource.returnDataSource().getConnection();
			query = conn.prepareStatement("select * from Inventory");
			ResultSet rs = query.executeQuery();
			ToJASON convertor = new ToJASON();
			JSONArray json = new JSONArray();
			json = convertor.toJASONArray(rs);
			myString = json.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
				conn.close();
		}
		
		return myString;
		
	}
}
