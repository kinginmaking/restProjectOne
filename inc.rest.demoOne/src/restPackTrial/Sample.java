package restPackTrial;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dom.ds.ManageDataSource;

@Path("/v1/status")
public class Sample {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStatus()
	{
		return "<p>java webservice</p>";
	}
	
	@GET
	@Path("/client")
	@Produces(MediaType.TEXT_HTML)
	public String getClients()
	{
		return "<h2>Intesa</h2>";
	}
	
	@GET
	@Path("/database")
	@Produces(MediaType.TEXT_HTML)
	public String getDatabaseStatus() throws SQLException
	{
		Connection conn=null;
		PreparedStatement query=null;
		String myString=null;
		
		try 
		{
			conn = ManageDataSource.returnDataSource().getConnection();
			query = conn.prepareStatement("select EMPID,EMPLOYEE_NAME,SAL,MY_DEPT from EMPLOYEE where EMPID=1");
			ResultSet rs = query.executeQuery();
			
			while(rs.next())
			{
				myString = rs.getString("EMPLOYEE_NAME");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
				conn.close();
			query.close();
		}
		return "<h2>"+myString+"</h2>";
	}
}
