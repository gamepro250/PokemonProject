package types;
public class Matchups 
{
	//Total number of different types
	static int numTypes = 18 ;
	
	//Assigning a number to each type
	static int normal =   0 ;
	static int fire =     1 ;
	static int water =    2 ;
	static int grass =    3 ;
	static int electric = 4 ;
	static int ice =      5 ;
	static int fighting = 6 ;
	static int poison =   7 ;
	static int ground =   8 ;
	static int flying =   9 ;
	static int psychic = 10 ;
	static int bug =     11 ;
	static int rock =    12 ;
	static int ghost =   13 ;
	static int dark =    14 ;
	static int dragon =  15 ;
	static int steel =   16 ;
	static int fairy =   17 ;
	
	/* takes a Pokemon's type, single, and returns an array of Doubles with multipliers
	 to show what effect different types will have on that Pokemon */
	public static double[] effectiveness(String type)
	{
		double[] effectiveness = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0} ;
		
		
		switch(type.toLowerCase())
		{
		case "normal": 
			effectiveness[fighting] = 2.0 ;
			effectiveness[ghost] = 0.0 ;
			break ;
		case "fire":
			effectiveness[ground] = 2.0 ;
			effectiveness[rock] = 2.0 ;
			effectiveness[water] = 2.0 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[steel] = 0.5 ;
			effectiveness[fire] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[ice] = 0.5 ;
			effectiveness[fairy] = 0.5 ;
			break ;
		case "water":
			effectiveness[grass] = 2.0 ;
			effectiveness[electric] = 2.0 ;
			effectiveness[steel] = 0.5 ;
			effectiveness[fire] = 0.5 ;
			effectiveness[water] = 0.5 ;
			effectiveness[ice] = 0.5 ;
			break ;
		case "grass":
			effectiveness[flying] = 2.0 ;
			effectiveness[poison] = 2.0 ;
			effectiveness[bug] = 2.0 ;
			effectiveness[fire] = 2.0 ;
			effectiveness[ice] = 2.0 ;
			effectiveness[ground] = 0.5 ;
			effectiveness[water] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[electric] = 0.5 ;
			break ;
		case "electric":
			effectiveness[ground] = 2.0 ;
			effectiveness[flying] = 0.5 ;
			effectiveness[steel] = 0.5 ;
			effectiveness[electric] = 0.5 ;
			break ;
		case "ice":
			effectiveness[fighting] = 2.0 ;
			effectiveness[rock] = 2.0 ;
			effectiveness[steel] = 2.0 ;
			effectiveness[fire] = 2.0 ;
			effectiveness[ice] = 0.5 ;
			break ;
		case "fighting":
			effectiveness[flying] = 2.0 ;
			effectiveness[psychic] = 2.0 ;
			effectiveness[fairy] = 2.0 ;
			effectiveness[rock] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[dark] = 0.5 ;
			break ;
		case "poison":
			effectiveness[ground] = 2.0 ;
			effectiveness[psychic] = 2.0 ;
			effectiveness[fighting] = 0.5 ;
			effectiveness[poison] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[fairy] = 0.5 ;
			break ;
		case "ground":
			effectiveness[water] = 2.0 ;
			effectiveness[grass] = 2.0 ;
			effectiveness[ice] = 2.0 ;
			effectiveness[poison] = 0.5 ;
			effectiveness[rock] = 0.5 ;
			effectiveness[electric] = 0.0 ;
			break ;
		case "flying":
			effectiveness[rock] = 2.0 ;
			effectiveness[electric] = 2.0 ;
			effectiveness[ice] = 2.0 ;
			effectiveness[fighting] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[ground] = 0.0 ;
			break ;
		case "psychic":
			effectiveness[bug] = 2.0 ;
			effectiveness[ghost] = 2.0 ;
			effectiveness[dark] = 2.0 ;
			effectiveness[fighting] = 0.5 ;
			effectiveness[psychic] = 0.5 ;
			break ;
		case "bug":
			effectiveness[flying] = 2.0 ;
			effectiveness[rock] = 2.0 ;
			effectiveness[fire] = 2.0 ;
			effectiveness[fighting] = 0.5 ;
			effectiveness[ground] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			break ;
		case "rock":
			effectiveness[fighting] = 2.0 ;
			effectiveness[ground] = 2.0 ;
			effectiveness[steel] = 2.0 ;
			effectiveness[water] = 2.0 ;
			effectiveness[grass] = 2.0 ;
			effectiveness[normal] = 0.5 ;
			effectiveness[flying] = 0.5 ;
			effectiveness[poison] = 0.5 ;
			effectiveness[fire] = 0.5 ;
			break ;
		case "ghost":
			effectiveness[ghost] = 2.0 ;
			effectiveness[dark] = 2.0 ;
			effectiveness[poison] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[normal] = 0.0 ;
			effectiveness[fighting] = 0.0 ;
			break ;
		case "dark":
			effectiveness[fighting] = 2.0 ;
			effectiveness[bug] = 2.0 ;
			effectiveness[fairy] = 2.0 ;
			effectiveness[ghost] = 0.5 ;
			effectiveness[dark] = 0.5 ;
			effectiveness[psychic] = 0.0 ;
			break ;
		case "dragon":
			effectiveness[ice] = 2.0 ;
			effectiveness[dragon] = 2.0 ;
			effectiveness[fairy] = 2.0 ;
			effectiveness[fire] = 0.5 ;
			effectiveness[water] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[electric] = 0.5 ;
			break ;
		case "steel":
			effectiveness[fighting] = 2.0 ;
			effectiveness[ground] = 2.0 ;
			effectiveness[fire] = 2.0 ;
			effectiveness[normal] = 0.5 ;
			effectiveness[flying] = 0.5 ;
			effectiveness[rock] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[steel] = 0.5 ;
			effectiveness[grass] = 0.5 ;
			effectiveness[psychic] = 0.5 ;
			effectiveness[ice] = 0.5 ;
			effectiveness[dragon] = 0.5 ;
			effectiveness[fairy] = 0.5 ;
			effectiveness[poison] = 0.0 ;
			break ;
		case "fairy":
			effectiveness[poison] = 2.0 ;
			effectiveness[steel] = 2.0 ;
			effectiveness[fighting] = 0.5 ;
			effectiveness[bug] = 0.5 ;
			effectiveness[dark] = 0.5 ;
			effectiveness[dragon] = 0.0 ;
			break ;
		default:
			break ;
		}

		
		

		
		return effectiveness ;
	}
	
	/* takes a Pokemon with a dual type and determines the effectiveness for each individual type
	   and then combines the resuts to get the total effectiveness */
	
	public static double[] effectiveness(String type1, String type2)
	{
						
		double[] firstType = effectiveness(type1) ;
		double[] secondType = effectiveness(type2) ;
		double[] combinedEffectiveness = new double[numTypes] ;
		
		for(int i=0;i<numTypes;i++)
		{
			combinedEffectiveness[i] = firstType[i] * secondType[i] ;
		}
		
		return combinedEffectiveness ;
		
	}
	/* Type order normal, fire, water, grass, electric, ice, fighting, poison, ground, 
	   flying, psychic, bug, rock, ghost, dark, dragon, steel, fairy */
	public static String readableEffectiveness(double[] types)
	{
		String output = "" ;
		String typeArray[] = {"Normal  ", "Fire    ", "Water   ", "Grass   ", "Electric", "Ice     ", "Fighting", "Poison  ",
				"Ground  ", "Flying  ", "Psychic ", "Bug     ", "Rock    ", "Ghost   ", "Dark    ", "Dragon  ", "Steel   ", "Fairy   "} ;
		
		for (int i=0;i<numTypes;i++)
		{
			output += typeArray[i] + ": " + types[i] ;
			
			if((i+ 1)%4 == 0)
			{
				output += "\n" ;
			}
			else
			{
				output += "\t" ;
			}
		}
		
		output += "\n" ;
		
		return output ;
	}
	
	public static String getMultiplyer(double effective)
	{
		if(effective == 4.0)
		{
			return "x4" ;
		}
		else if(effective == 2.0)  {
			return "x2" ;
		}
		else if (effective == 1.0) {
			return "x1" ;
		}
		else if (effective == 0.5) {
			return "x0.5" ;
		}
		else if (effective == 0.25) {
			return "x0.25" ;
		}
		else {
			return "x0" ;
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	