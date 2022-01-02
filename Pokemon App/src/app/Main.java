package app;
import types.Matchups;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBQuery ;

public class Main 
{
	static int numTypes = 18 ;

	public static void main(String[] args)
	{
		

		double[] poison = Matchups.effectiveness("Ghost", "Fire") ;
		
		String poisonChart = Matchups.readableEffectiveness(poison) ;
		
		System.out.print(poisonChart) ;
	}
	
	
	
}
