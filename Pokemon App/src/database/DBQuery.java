package database;
import java.sql.*;

import types.Matchups;

public class DBQuery 
{
	// Connection to Microsoft SQL Server
	//private static String url = "jdbc:sqlserver://BILLYS-PC\\SQLEXPRESS;databaseName=Pokemon" ;
	//private static String user = "gamepro250" ;
	//private static String password = "CedarCliff#25" ;
	
	static String url = "jdbc:h2:./database/Pokedex;IFEXISTS=TRUE" ;
	static String user = "admin" ;
	static String password = "" ;
	
	public static void main(String[] args)
	{
		
		try {
			Class.forName("org.h2.Driver") ;
			Connection connection = DriverManager.getConnection(url, user, password) ;
			
			String sql = "SELECT * FROM nationaldex where type1 like '%Electric%'" ; //Create SELECT statement
			
			System.out.println("Connected");
			
			Statement statement = connection.createStatement() ; //Create statement on the connection
			
			ResultSet result = statement.executeQuery(sql) ; //Create ResultSet for result to be stored
			
			int count = 0;
			
			while (result.next()) //iterate through selected rows
			{
				count++ ;
				int number = result.getInt("nationaldex") ; //store Int from "NationalDex" column
				String name = result.getString("name") ; //store String from "Name" column
				String type = result.getString("type1") ;
				
				if(result.getString("type2") != null)
				{
					type += "/" + result.getString("type2") ;
				}
				
				
				System.out.println(number + ". " + name + " " + type) ;
			}
					
			connection.close() ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Server connection unavailable.") ;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection connect()
	{
		try {
			Connection connection = DriverManager.getConnection(url, user, password) ;
			return connection ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void disconnect(Connection connection)
	{
		try {
			connection.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
