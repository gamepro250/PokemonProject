package database;
import java.sql.*;


public class DBQuery 
{
	// Connection to Microsoft SQL Server
	//private static String url = "jdbc:sqlserver://BILLYS-PC\\SQLEXPRESS;databaseName=Pokemon" ;
	//private static String user = "gamepro250" ;
	//private static String password = "CedarCliff#25" ;
	
	static String url = "jdbc:h2:./database/Pokedex;IFEXISTS=TRUE" ;
	static String user = "admin" ;
	static String password = "" ;
	
	public static Connection connect()
	{
		try {
			Class.forName("org.h2.Driver") ;
			Connection connection = DriverManager.getConnection(url, user, password) ;
			return connection ;
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}
	public static void disconnect(Connection connection)
	{
		try {
			connection.close() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
