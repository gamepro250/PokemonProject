package application;

import java.io.IOException;
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

import database.DBQuery ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML ;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D ;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label ;
import javafx.scene.control.RadioButton ;
import javafx.scene.control.TextField ;
import javafx.scene.layout.GridPane ;
import javafx.stage.Modality ;
import javafx.stage.Screen ;
import javafx.stage.Stage;

public class MainSearchController 
{
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	//@FXML MenuItem closeMenu ;
	@FXML GridPane mainWindow ;
	@FXML TextField searchField ;
	@FXML Label errorMessage ;
	@FXML RadioButton nameSearch ;
	@FXML RadioButton numberSearch ;
	@FXML RadioButton abilitySearch ;
	private Boolean pokeFound ;
	String sql ;
	
	public void executeSearch(ActionEvent event) throws IOException, SQLException
	{
		Connection connection = DBQuery.connect() ;
				
		if(abilitySearch.isSelected())
		{
			String abilityText = null ;
			String abilityName = searchField.getText() ;
			
			connection = DBQuery.connect() ;
			String sql = "SELECT * FROM abilities where UPPER(abilityname) = UPPER('" + abilityName + "')" ;
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
				stage.show() ;
				
		        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
				
				
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
			
			pokeFound = true ;

		}
	}
	
	
	
}
