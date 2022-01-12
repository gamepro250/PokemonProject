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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label ;
import javafx.scene.control.RadioButton ;
import javafx.scene.control.TextField ;
import javafx.scene.layout.BorderPane ;
import javafx.stage.Stage;

public class MainSearchController 
{
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	//@FXML MenuItem closeMenu ;
	@FXML BorderPane mainWindow ;
	@FXML TextField searchField ;
	@FXML Label errorMessage ;
	@FXML RadioButton nameSearch ;
	@FXML RadioButton numberSearch ;
	private Boolean pokeFound ;
	String sql ;
	
	public void executeSearch(ActionEvent event) throws IOException, SQLException
	{
		Connection connection = DBQuery.connect() ;
				
		
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
			
			String evoMethod = result.getString("evolutionMethod") ;
			searchResultController.setEvolutionMethod(evoMethod) ;
			
			String ability1Text = result.getString("ability1") ;
			searchResultController.setAbility1LabelText(ability1Text) ;
			
			String ability2Text = result.getString("ability2") ;
			if(ability2Text == null)
			{
				searchResultController.setAbility2Label("") ;
			}
			searchResultController.setAbility2LabelText(ability2Text) ;
			
			String hiddenAbilityText = result.getString("abilityhidden") ;
			if(hiddenAbilityText == null)
			{
				searchResultController.setHiddenAbilityLabel("") ;
			}
			searchResultController.setHiddenAbilityLabelText(hiddenAbilityText) ;
			
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
		
		if(pokeFound)
		{
			//root = FXMLLoader.load(getClass().getResource("SearchResult.fxml")) ;
			stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
			scene = new Scene(root) ;
			stage.setScene(scene) ;
			stage.show() ;
			
			DBQuery.disconnect(connection) ;
		}
	}
	
	public void closeProgram(ActionEvent actionEvent)
	{
		stage = (Stage) mainWindow.getScene().getWindow() ;
		stage.close() ;
	}
	
	
	
	
	
}
