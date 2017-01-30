package com.dom.ds;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ManageDataSource {

	private static DataSource dsLocal=null;
	private static Context context=null;
	
	public static DataSource returnDataSource()
	{
		if(dsLocal!=null)
			return dsLocal;
		
		try
		{
			if(context==null)
			{
				context = new InitialContext();
			}
			
			dsLocal = (DataSource)context.lookup("localDatabase");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsLocal;
	}
}
