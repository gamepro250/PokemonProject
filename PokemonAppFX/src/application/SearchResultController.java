package application;

import java.io.IOException ;
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

import database.DBQuery ;
import javafx.event.ActionEvent ;
import javafx.event.Event ;
import javafx.fxml.FXML ;
import javafx.fxml.FXMLLoader ;
import javafx.scene.Parent ;
import javafx.scene.Scene ;
import javafx.scene.control.Button ;
import javafx.scene.control.Label ;
import javafx.scene.control.MenuItem ;
import javafx.scene.control.TextArea ;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView ;
import javafx.scene.layout.GridPane ;
import javafx.scene.text.Text ;
import javafx.stage.Modality ;
import javafx.stage.Stage ;
import types.Matchups ;

public class SearchResultController
{
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	@FXML private GridPane mainWindow ;
	@FXML private MenuItem newSearchButton ;
	
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
	
	@FXML public Label pokeName ;
	@FXML public Label pokeType ;
	@FXML public Text nationDexNum ;
	@FXML public Label primaryType ;
	@FXML public Label SecondaryType ;
	@FXML public Label secondaryAbilityLabel ;
	@FXML public Label hiddenAbilityLabel ;
	@FXML public Button primaryAbilityButtonText ;
	@FXML public Button secondaryAbilityButtonText ;
	@FXML public Button hiddenAbilityButtonText ;
	@FXML public TextArea evolutionMethod ;
	@FXML public Text hasMega ;
	
	@FXML public ImageView pokemonSprite ;
	
	
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
	
	public void setPokeName(String name)
	{
		pokeName.setText(name) ;
	}	
	
	public void setPrimaryType(String type)
	{
		primaryType.setText(type) ;
	}
	
	public void setSecondaryType(String type)
	{
		SecondaryType.setText(type) ;
	}
	
	public void setSprite(int number)
	{
		String stringNum = "" ;
		
		if(number < 10)
		{
			stringNum += "00" + number ;
		}else if (number <100) {
			stringNum += "0" + number ;
		}else {
			stringNum += Integer.toString(number) ;
		}
		
		String sprite = "file:resources/images/" + stringNum + ".png" ;
		Image pokemon = new Image(sprite,400,400,true,true) ;
		pokemonSprite.setImage(pokemon) ;
	}
	
	public void setNationalDexNum(int number)
	{
		String stringNum = "#";
		
		if(number < 10)
		{
			stringNum += "00" + number ;
		}else if (number <100) {
			stringNum += "0" + number ;
		}else {
			stringNum += Integer.toString(number) ;
		}
		nationDexNum.setText(stringNum) ;
	}
	
	public void setAbility2Label(String type)
	{
		secondaryAbilityLabel.setText(type) ;
	}
	
	public void setHiddenAbilityLabel(String type)
	{
		hiddenAbilityLabel.setText(type) ;
	}
	
	public void setAbility1ButtonText(String type)
	{
		primaryAbilityButtonText.setText(type) ;
		
	}
	
	public void setAbility2ButtonText(String type)
	{
		secondaryAbilityButtonText.setText(type) ;
	}
	
	public void hideAbility2Button() 
	{
		secondaryAbilityButtonText.setVisible(false) ;
	}
	
	public void setHiddenAbilityButtonText(String type)
	{
		hiddenAbilityButtonText.setText(type) ;
	}
	
	public void hideAbilityHiddenButton() 
	{
		hiddenAbilityButtonText.setVisible(false) ;
	}
	
	public void showAbilityInfo(Event e) throws IOException, SQLException
	{					
		String abilityText = null ;
		Button b = (Button)e.getSource() ;
		String bText = b.getText() ;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AbilityInfo.fxml")) ;
		root = loader.load() ;		
		
		AbilityInfoController abilityInfoController = loader.getController() ;
		abilityInfoController.setAbilityNameText(bText);
		
		Connection connection = DBQuery.connect() ;
		String sql = "SELECT * FROM abilities where UPPER(abilityname) = UPPER('" + bText + "')" ;
		Statement statement = connection.createStatement() ;
		ResultSet result = statement.executeQuery(sql) ; 
		
		if(result.next())
		{
			abilityText = result.getString("description") ;
		}
		
		abilityInfoController.setAbilityDescriptionText(abilityText) ;
		
		Stage stage = new Stage() ;
		stage.setScene(new Scene(root)) ;  
		stage.initModality(Modality.APPLICATION_MODAL) ;
		stage.show() ;
	}
	
	public void setEvolutionMethod(String type)
	{
		evolutionMethod.setText(type) ;
	}
	
	public void setHasMega(String type)
	{
		hasMega.setText(type) ;
	}
	
	public void setEffectiveness(String type1, String type2)
	{
		//order of types: normal, fire, water, grass, electric
		//ice, fighting, poison, ground, flying, psychic, bug, 
		//rock, ghost, dark, dragon, steel, fairy
		
		double[] typeChart ;
		
		if(type2 == null)
		{
			typeChart = Matchups.effectiveness(type1) ;
		}
		else {
			typeChart = Matchups.effectiveness(type1, type2) ;
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

	public void newSearch(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSearch.fxml")) ;
		root = loader.load() ;		
		//SearchResultController searchResultController = loader.getController() ;
		stage = (Stage)newSearchButton.getParentPopup().getOwnerWindow() ;
		scene = new Scene(root) ;
		stage.setScene(scene) ;
		stage.show() ;
	}
	
	public void closeProgram(ActionEvent actionEvent)
	{
		stage = (Stage) mainWindow.getScene().getWindow() ;
		stage.close() ;
	}
}















