package application;

import java.io.IOException;
import java.net.URL ;
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.util.ResourceBundle ;


import database.DBQuery ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML ;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable ;
import javafx.geometry.Rectangle2D ;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox ;
import javafx.scene.control.Label ;
import javafx.scene.control.RadioButton ;
import javafx.scene.control.TabPane ;
import javafx.scene.control.TextField ;
import javafx.scene.text.Text ;
import javafx.stage.Modality ;
import javafx.stage.Screen ;
import javafx.stage.Stage;
import types.Matchups ;

public class MainSearchController  implements Initializable
{
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	//@FXML MenuItem closeMenu ;
	@FXML TabPane mainWindow ;
	@FXML TextField searchField ;
	@FXML Label errorMessage ;
	@FXML RadioButton nameSearch ;
	@FXML RadioButton numberSearch ;
	@FXML RadioButton abilitySearch ;
	@FXML ComboBox<String> type1 ;
	@FXML ComboBox<String> type2 ;
	
	@FXML public Text normalEffect ;
	@FXML public Text fireEffect ;
	@FXML public Text waterEffect ;
	@FXML public Text grassEffect ;
	@FXML public Text electricEffect ;
	@FXML public Text iceEffect ;
	@FXML public Text fightingEffect ;
	@FXML public Text poisonEffect ;
	@FXML public Text groundEffect ;
	@FXML public Text flyingEffect ;
	@FXML public Text psychicEffect ;
	@FXML public Text bugEffect ;
	@FXML public Text rockEffect ;
	@FXML public Text ghostEffect ;
	@FXML public Text darkEffect ;
	@FXML public Text dragonEffect ;
	@FXML public Text steelEffect ;
	@FXML public Text fairyEffect ;
	
	private Boolean pokeFound ;
	String sql ;
	
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		String typeArray[] = {"Bug", "Dark", "Dragon", "Electric", "Fairy", "Fighting", "Fire", "Flying", "Ghost", 
				"Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Steel", "Water"} ;                    
		
		type2.getItems().add("None") ;
		
		for(String type:typeArray)
		{
			type1.getItems().add(type) ;
			type2.getItems().add(type) ;
		}
	}
	
	public void executeSearch(ActionEvent event) throws IOException, SQLException
	{
		Connection connection = DBQuery.connect() ;
				
		if(abilitySearch.isSelected())
		{
			String abilityText = null ;
			String abilityName = searchField.getText() ;
			
			connection = DBQuery.connect() ;
			sql = "SELECT * FROM abilities where UPPER(abilityname) = UPPER('" + abilityName + "')" ;
			Statement statement = connection.createStatement() ;
			ResultSet result = statement.executeQuery(sql) ; 
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AbilityInfo.fxml")) ;
			root = loader.load() ;		
	
			AbilityInfoController abilityInfoController = loader.getController() ;
			
			
			if(result.next())
			{
				abilityText = result.getString("description") ;
				abilityName = result.getString("abilityname") ;
				abilityInfoController.setAbilityDescriptionText(abilityText) ;
				abilityInfoController.setAbilityNameText(abilityName) ;
				
				Stage stage = new Stage() ;
				stage.setScene(new Scene(root)) ;  
				stage.initModality(Modality.APPLICATION_MODAL) ;
				stage.show() ;
			}
			else
			{
				fuzzyAbilitySearch(abilityInfoController, connection);
			}
			
		}
		else {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchResult.fxml")) ;
			root = loader.load() ;		
			SearchResultController searchResultController = loader.getController() ;
			
			if(nameSearch.isSelected())
			{
				sql = "SELECT * FROM nationaldex where UPPER(name) = UPPER('" + searchField.getText() + "')" ; //Create SELECT statement
			}
			else if (numberSearch.isSelected()) 
			{
				
				try
				{
					Integer.parseInt(searchField.getText()) ;
					sql = "SELECT * FROM nationaldex where nationaldex = '" + searchField.getText() + "'" ; //Create SELECT statement
				} catch (Exception e)
				{
					sql = "SELECT * FROM nationaldex where nationaldex = '0'" ; //Create SELECT statement
				}
								
			}
			
			Statement statement = connection.createStatement() ; //Create statement on the connection	
			ResultSet result = statement.executeQuery(sql) ; //Create ResultSet for result to be stored
			
			if (result.next() == false)
			{
				errorMessage.setText("No results found.") ;
				pokeFound = false ;
			}
			else
			{
				errorMessage.setText("Searching...") ;
				
				String name = result.getString("name") ;
				searchResultController.setPokeName(name) ;
				
				String type1 = result.getString("type1") ;
				searchResultController.setPrimaryType(type1) ;
				
				String type2 = result.getString("type2") ;
				searchResultController.setSecondaryType(type2) ;
				
				searchResultController.setEffectiveness(type1, type2) ;
				
				int nationalDexNum = result.getInt("nationaldex") ;
				searchResultController.setNationalDexNum(nationalDexNum) ;
				searchResultController.setSprite(nationalDexNum) ;
				
				String evoMethod = result.getString("evolutionMethod") ;
				searchResultController.setEvolutionMethod(evoMethod) ;
				
				String ability1Text = result.getString("ability1") ;
				searchResultController.setAbility1ButtonText(ability1Text) ;
				
				String ability2Text = result.getString("ability2") ;
				if(ability2Text == null)
				{
					searchResultController.setAbility2Label("") ;
					searchResultController.hideAbility2Button() ;
				}
				searchResultController.setAbility2ButtonText(ability2Text) ;
				
				String hiddenAbilityText = result.getString("abilityhidden") ;
				if(hiddenAbilityText == null)
				{
					searchResultController.setHiddenAbilityLabel("") ;
					searchResultController.hideAbilityHiddenButton() ;
				}
				searchResultController.setHiddenAbilityButtonText(hiddenAbilityText) ;
				
				String hasMega = result.getString("mega") ;
				if(hasMega.equals("Y"))
				{
					searchResultController.setHasMega("Yes") ;
				}
				else {
					searchResultController.setHasMega("No") ;
				}
				
				if(result.getString("alola").equals("Y")) 
				{
					setAlolanData(searchResultController, connection, name, nationalDexNum) ;
				}
				else {
					searchResultController.disableAlola() ;
				}
				
				if(result.getString("galar").equals("Y")) 
				{
					setGalarianData(searchResultController, connection, name, nationalDexNum) ;
				}
				else {
					searchResultController.disableGalar() ;
				}
				
				pokeFound = true ;
			}
	
			if(!pokeFound && nameSearch.isSelected())
			{
				fuzzySearch(searchResultController, connection) ;
			}
			
			if(pokeFound)
			{
				//root = FXMLLoader.load(getClass().getResource("SearchResult.fxml")) ;
				stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
				scene = new Scene(root) ;
				stage.setScene(scene) ;
		        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setHeight(primScreenBounds.getHeight() - 200) ;
				stage.setWidth(primScreenBounds.getWidth() - 550) ;
		        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
				stage.show() ;
				
				
				
				DBQuery.disconnect(connection) ;
			}
		}
	}
	
	public void closeProgram(ActionEvent actionEvent)
	{
		stage = (Stage) mainWindow.getScene().getWindow() ;
		stage.close() ;
	}
	
	public void fuzzyAbilitySearch(AbilityInfoController controller, Connection connection) throws SQLException, IOException
	{
		String abilityText = null ;
		String abilityName = searchField.getText() ;
		
		String sql = "SELECT * FROM abilities where soundex(UPPER(abilityname)) = soundex(UPPER('" + abilityName + "'))" ;
		Statement statement = connection.createStatement() ;
		ResultSet result = statement.executeQuery(sql) ; 
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AbilityInfo.fxml")) ;
		root = loader.load() ;		

		AbilityInfoController abilityInfoController = loader.getController() ;
		
		
		if(result.next())
		{
			abilityText = result.getString("description") ;
			abilityName = result.getString("abilityname") ;
			abilityInfoController.setAbilityDescriptionText(abilityText) ;
			abilityInfoController.setAbilityNameText(abilityName) ;
			
			Stage stage = new Stage() ;
			stage.setScene(new Scene(root)) ;  
			stage.initModality(Modality.APPLICATION_MODAL) ;
			stage.show() ;
		}
		else
		{

			errorMessage.setText("No results found.") ;
		}
	}
	
	public void fuzzySearch(SearchResultController searchResultController, Connection connection) throws SQLException
	{
	
		sql = "SELECT * FROM nationaldex where soundex(UPPER(name)) = soundex(UPPER('" + searchField.getText() + "'))" ; //Create SELECT statement
		
		
		Statement statement = connection.createStatement() ; //Create statement on the connection	
		ResultSet result = statement.executeQuery(sql) ; //Create ResultSet for result to be stored
		
		if (result.next() == false)
		{
			errorMessage.setText("No results found.") ;
			pokeFound = false ;
		}
		else
		{
			errorMessage.setText("Searching...") ;
			
			String name = result.getString("name") ;
			searchResultController.setPokeName(name) ;
			
			String type1 = result.getString("type1") ;
			searchResultController.setPrimaryType(type1) ;
			
			String type2 = result.getString("type2") ;
			searchResultController.setSecondaryType(type2) ;
			
			searchResultController.setEffectiveness(type1, type2) ;
			
			int nationalDexNum = result.getInt("nationaldex") ;
			searchResultController.setNationalDexNum(nationalDexNum) ;
			searchResultController.setSprite(nationalDexNum) ;
			System.out.println(nationalDexNum) ;
			
			String evoMethod = result.getString("evolutionMethod") ;
			searchResultController.setEvolutionMethod(evoMethod) ;
			
			String ability1Text = result.getString("ability1") ;
			searchResultController.setAbility1ButtonText(ability1Text) ;
			
			String ability2Text = result.getString("ability2") ;
			if(ability2Text == null)
			{
				searchResultController.setAbility2Label("") ;
				searchResultController.hideAbility2Button() ;
			}
			searchResultController.setAbility2ButtonText(ability2Text) ;
			
			String hiddenAbilityText = result.getString("abilityhidden") ;
			if(hiddenAbilityText == null)
			{
				searchResultController.setHiddenAbilityLabel("") ;
				searchResultController.hideAbilityHiddenButton() ;
			}
			searchResultController.setHiddenAbilityButtonText(hiddenAbilityText) ;
			
			String hasMega = result.getString("mega") ;
			if(hasMega.equals("Y"))
			{
				searchResultController.setHasMega("Yes") ;
			}
			else {
				searchResultController.setHasMega("No") ;
			}
			
			if(result.getString("alola").equals("Y")) 
			{
				setAlolanData(searchResultController, connection, name, nationalDexNum) ;
			}
			else {
				searchResultController.disableAlola() ;
			}
			
			if(result.getString("galar").equals("Y")) 
			{
				setGalarianData(searchResultController, connection, name, nationalDexNum) ;
			}
			else {
				searchResultController.disableGalar() ;
			}			
			pokeFound = true ;

		}
	}
	
	public void setAlolanData(SearchResultController searchResultController, Connection connect, String name, int number) throws SQLException
	{
		String sqlA = "select * from ALOLAFORMS where UPPER(name) = UPPER('" + name +"')" ;
		Statement statementA = connect.createStatement() ;
		ResultSet resultA = statementA.executeQuery(sqlA) ;

		resultA.next() ;
		searchResultController.setEvolutionMethodA(resultA.getString("evolutionMethod")) ;
		searchResultController.setSpriteA(number) ;
		searchResultController.setAlolanDexNum(resultA.getInt("alolandex")) ;
		searchResultController.setPrimaryTypeA(resultA.getString("type1")) ;
		searchResultController.setSecondaryTypeA(resultA.getString("type2")) ;
		searchResultController.setEffectivenessA(resultA.getString("type1"), resultA.getString("type2")) ; 
		
		String ability1Text = resultA.getString("ability1") ;
		searchResultController.setAbility1ButtonTextA(ability1Text) ;
		
		String ability2Text = resultA.getString("ability2") ;
		if(ability2Text == null)
		{
			searchResultController.setAbility2LabelA("") ;
			searchResultController.hideAbility2ButtonA() ;
		}
		searchResultController.setAbility2ButtonTextA(ability2Text) ;
		
		String hiddenAbilityText = resultA.getString("abilityhidden") ;
		if(hiddenAbilityText == null)
		{
			searchResultController.setHiddenAbilityLabelA("") ;
			searchResultController.hideAbilityHiddenButtonA() ;
		}
		searchResultController.setHiddenAbilityButtonTextA(hiddenAbilityText) ;		
	}
	
	public void setGalarianData(SearchResultController searchResultController, Connection connect, String name, int number) throws SQLException
	{
		String sqlG = "select * from GALARFORMS where name = '" + name +"'" ;
		Statement statementG = connect.createStatement() ;
		ResultSet resultG = statementG.executeQuery(sqlG) ;
		
		resultG.next() ;
		searchResultController.setEvolutionMethodG(resultG.getString("evolutionMethod")) ;
		searchResultController.setSpriteG(number) ;
		searchResultController.setGalarDexNum(resultG.getInt("galardex")) ;
		searchResultController.setPrimaryTypeG(resultG.getString("type1")) ;
		searchResultController.setSecondaryTypeG(resultG.getString("type2")) ;
		searchResultController.setEffectivenessG(resultG.getString("type1"), resultG.getString("type2")) ; 
		
		String ability1Text = resultG.getString("ability1") ;
		searchResultController.setAbility1ButtonTextG(ability1Text) ;
		
		String ability2Text = resultG.getString("ability2") ;
		if(ability2Text == null)
		{
			searchResultController.setAbility2LabelG("") ;
			searchResultController.hideAbility2ButtonG() ;
		}
		searchResultController.setAbility2ButtonTextG(ability2Text) ;
		
		String hiddenAbilityText = resultG.getString("abilityhidden") ;
		if(hiddenAbilityText == null)
		{
			searchResultController.setHiddenAbilityLabelG("") ;
			searchResultController.hideAbilityHiddenButtonG() ;
		}
		searchResultController.setHiddenAbilityButtonTextG(hiddenAbilityText) ;	
	}
	
	public void setBugEffect(String effect)
	{
		bugEffect.setText(effect) ;
	}	
	
	public void setDarkEffect(String effect)
	{
		darkEffect.setText(effect) ;
	}	
	
	public void setDragonEffect(String effect)
	{
		dragonEffect.setText(effect) ;
	}	
	
	public void setElectricEffect(String effect)
	{
		electricEffect.setText(effect) ;
	}	
	
	public void setFairyEffect(String effect)
	{
		fairyEffect.setText(effect) ;
	}	
	
	public void setGhostEffect(String effect)
	{
		ghostEffect.setText(effect) ;
	}	
	
	public void setFlyingEffect(String effect)
	{
		flyingEffect.setText(effect) ;
	}	
	
	public void setGrassEffect(String effect)
	{
		grassEffect.setText(effect) ;
	}	
	
	public void setFireEffect(String effect)
	{
		fireEffect.setText(effect) ;
	}	
	
	public void setFightingEffect(String effect)
	{
		fightingEffect.setText(effect) ;
	}	
	
	public void setIceEffect(String effect)
	{
		iceEffect.setText(effect) ;
	}	
	
	public void setGroundEffect(String effect)
	{
		groundEffect.setText(effect) ;
	}	
	
	public void setPoisonEffect(String effect)
	{
		poisonEffect.setText(effect) ;
	}	
	
	public void setNormalEffect(String effect)
	{
		normalEffect.setText(effect) ;
	}	
	
	public void setSteelEffect(String effect)
	{
		steelEffect.setText(effect) ;
	}	
	
	public void setWaterEffect(String effect)
	{
		waterEffect.setText(effect) ;
	}	
	
	public void setRockEffect(String effect)
	{
		rockEffect.setText(effect) ;
	}	
	
	public void setPsychicEffect(String effect)
	{
		psychicEffect.setText(effect) ;
	}

	public void setEffectiveness()
	{
		//order of types: normal, fire, water, grass, electric
		//ice, fighting, poison, ground, flying, psychic, bug, 
		//rock, ghost, dark, dragon, steel, fairy
		
		//String typeFirst = type1.getValue() ;
		
		//if(type2.getValu
		//String typeSecond = type2.getValue() ;
		
		double[] typeChart ;
		
		if(type2.getValue() == null)
		{
			typeChart = Matchups.effectiveness(type1.getValue()) ;
		}
		else {
			typeChart = Matchups.effectiveness(type1.getValue(), type2.getValue()) ;
		}
		
		this.setNormalEffect(Matchups.getMultiplyer(typeChart[0])) ;
		this.setFireEffect(Matchups.getMultiplyer(typeChart[1])) ;
		this.setWaterEffect(Matchups.getMultiplyer(typeChart[2])) ;
		this.setGrassEffect(Matchups.getMultiplyer(typeChart[3])) ;
		this.setElectricEffect(Matchups.getMultiplyer(typeChart[4])) ;
		this.setIceEffect(Matchups.getMultiplyer(typeChart[5])) ;
		this.setFightingEffect(Matchups.getMultiplyer(typeChart[6])) ;
		this.setPoisonEffect(Matchups.getMultiplyer(typeChart[7])) ;
		this.setGroundEffect(Matchups.getMultiplyer(typeChart[8])) ;
		this.setFlyingEffect(Matchups.getMultiplyer(typeChart[9])) ;
		this.setPsychicEffect(Matchups.getMultiplyer(typeChart[10])) ;
		this.setBugEffect(Matchups.getMultiplyer(typeChart[11])) ;
		this.setRockEffect(Matchups.getMultiplyer(typeChart[12])) ;
		this.setGhostEffect(Matchups.getMultiplyer(typeChart[13])) ;
		this.setDarkEffect(Matchups.getMultiplyer(typeChart[14])) ;
		this.setDragonEffect(Matchups.getMultiplyer(typeChart[15])) ;
		this.setSteelEffect(Matchups.getMultiplyer(typeChart[16])) ;
		this.setFairyEffect(Matchups.getMultiplyer(typeChart[17])) ;
	}
}
