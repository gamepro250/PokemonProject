package application;

import java.io.IOException ;
import java.net.URL ;
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.util.ResourceBundle ;

import database.DBQuery ;
import javafx.event.ActionEvent ;
import javafx.event.Event ;
import javafx.fxml.FXML ;
import javafx.fxml.FXMLLoader ;
import javafx.fxml.Initializable ;
import javafx.scene.Parent ;
import javafx.scene.Scene ;
import javafx.scene.control.Button ;
import javafx.scene.control.ComboBox ;
import javafx.scene.control.Label ;
import javafx.scene.control.MenuItem ;
import javafx.scene.control.Tab ;
import javafx.scene.control.TextArea ;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView ;
import javafx.scene.layout.GridPane ;
import javafx.scene.text.Text ;
import javafx.stage.Modality ;
import javafx.stage.Stage ;
import types.Matchups ;

public class SearchResultController implements Initializable
{
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	@FXML private GridPane mainWindow ;
	@FXML private MenuItem newSearchButton ;
	@FXML private Button newSearchButton2 ;
	@FXML Tab alolanTab ;
	@FXML Tab galarianTab ;
	
	@FXML ComboBox<String> formChoice ;
	@FXML public Label formTitle ;
	@FXML public Label formChangeTitle ;
	@FXML public TextArea formChangeInfo ;
	
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

	@FXML public Text normalEffectA ;
	@FXML public Text fireEffectA ;
	@FXML public Text waterEffectA ;
	@FXML public Text grassEffectA ;
	@FXML public Text electricEffectA ;
	@FXML public Text iceEffectA ;
	@FXML public Text fightingEffectA ;
	@FXML public Text poisonEffectA ;
	@FXML public Text groundEffectA ;
	@FXML public Text flyingEffectA ;
	@FXML public Text psychicEffectA ;
	@FXML public Text bugEffectA ;
	@FXML public Text rockEffectA ;
	@FXML public Text ghostEffectA ;
	@FXML public Text darkEffectA ;
	@FXML public Text dragonEffectA ;
	@FXML public Text steelEffectA ;
	@FXML public Text fairyEffectA ;

	@FXML public Text normalEffectG ;
	@FXML public Text fireEffectG ;
	@FXML public Text waterEffectG ;
	@FXML public Text grassEffectG ;
	@FXML public Text electricEffectG ;
	@FXML public Text iceEffectG ;
	@FXML public Text fightingEffectG ;
	@FXML public Text poisonEffectG ;
	@FXML public Text groundEffectG ;
	@FXML public Text flyingEffectG ;
	@FXML public Text psychicEffectG ;
	@FXML public Text bugEffectG ;
	@FXML public Text rockEffectG ;
	@FXML public Text ghostEffectG ;
	@FXML public Text darkEffectG ;
	@FXML public Text dragonEffectG ;
	@FXML public Text steelEffectG ;
	@FXML public Text fairyEffectG ;
	
	@FXML public Label pokeName ;
	@FXML public Label pokeNameA ;
	@FXML public Label pokeNameG ;
	@FXML public Label pokeNameL ;
	
	@FXML public Label catchRate ;
	@FXML public Label catchRateA ;
	@FXML public Label catchRateG ;
	
	@FXML public Label pokeType ;
	
	@FXML public Text nationDexNum ;
	@FXML public Text alolaDexNum ;
	@FXML public Text galarDexNum ;
	
	@FXML public Label primaryType ;
	@FXML public Label SecondaryType ;
	@FXML public Label primaryTypeA ;
	@FXML public Label SecondaryTypeA ;
	@FXML public Label primaryTypeG ;
	@FXML public Label SecondaryTypeG ;
	
	@FXML public Label secondaryAbilityLabel ;
	@FXML public Label hiddenAbilityLabel ;
	@FXML public Button primaryAbilityButtonText ;
	@FXML public Button secondaryAbilityButtonText ;
	@FXML public Button hiddenAbilityButtonText ;	
	@FXML public Label secondaryAbilityLabelA ;
	@FXML public Label hiddenAbilityLabelA ;
	@FXML public Button primaryAbilityButtonTextA ;
	@FXML public Button secondaryAbilityButtonTextA ;
	@FXML public Button hiddenAbilityButtonTextA ;	
	@FXML public Label secondaryAbilityLabelG ;
	@FXML public Label hiddenAbilityLabelG ;
	@FXML public Button primaryAbilityButtonTextG ;
	@FXML public Button secondaryAbilityButtonTextG ;
	@FXML public Button hiddenAbilityButtonTextG ;
	
	@FXML public TextArea evolutionMethod ;
	@FXML public TextArea evolutionMethodA ;
	@FXML public TextArea evolutionMethodG ;
	@FXML public Text hasMega ;
	
	@FXML public ImageView pokemonSprite ;
	@FXML public ImageView pokemonSpriteA ;
	@FXML public ImageView pokemonSpriteG ;
	
	@FXML public TextArea locationInfo ;
	@FXML ComboBox<String> regionSelect ;
	
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		//Sets location drop down with available Regions
		regionSelect.getItems().addAll("Unova", "Kalos") ;
		
		//Sets default for location search
		regionSelect.setValue("Unova") ;
	}

	//Sets the effectiveness of the type against a chosen type
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

	//Sets the effectiveness of the type against a chosen type for Alolan forms
	public void setBugEffectA(String effect)
	{
		bugEffectA.setText(effect) ;
	}	
	public void setDarkEffectA(String effect)
	{
		darkEffectA.setText(effect) ;
	}	
	public void setDragonEffectA(String effect)
	{
		dragonEffectA.setText(effect) ;
	}	
	public void setElectricEffectA(String effect)
	{
		electricEffectA.setText(effect) ;
	}	
	public void setFairyEffectA(String effect)
	{
		fairyEffectA.setText(effect) ;
	}	
	public void setGhostEffectA(String effect)
	{
		ghostEffectA.setText(effect) ;
	}	
	public void setFlyingEffectA(String effect)
	{
		flyingEffectA.setText(effect) ;
	}	
	public void setGrassEffectA(String effect)
	{
		grassEffectA.setText(effect) ;
	}	
	public void setFireEffectA(String effect)
	{
		fireEffectA.setText(effect) ;
	}		
	public void setFightingEffectA(String effect)
	{
		fightingEffectA.setText(effect) ;
	}		
	public void setIceEffectA(String effect)
	{
		iceEffectA.setText(effect) ;
	}	
	public void setGroundEffectA(String effect)
	{
		groundEffectA.setText(effect) ;
	}	
	public void setPoisonEffectA(String effect)
	{
		poisonEffectA.setText(effect) ;
	}	
	public void setNormalEffectA(String effect)
	{
		normalEffectA.setText(effect) ;
	}	
	public void setSteelEffectA(String effect)
	{
		steelEffectA.setText(effect) ;
	}		
	public void setWaterEffectA(String effect)
	{
		waterEffectA.setText(effect) ;
	}		
	public void setRockEffectA(String effect)
	{
		rockEffectA.setText(effect) ;
	}		
	public void setPsychicEffectA(String effect)
	{
		psychicEffectA.setText(effect) ;
	}	

	//Sets the effectiveness of the type against a chosen type for Galarian forms	
	public void setBugEffectG(String effect)
	{
		bugEffectG.setText(effect) ;
	}	
	public void setDarkEffectG(String effect)
	{
		darkEffectG.setText(effect) ;
	}	
	public void setDragonEffectG(String effect)
	{
		dragonEffectG.setText(effect) ;
	}	
	public void setElectricEffectG(String effect)
	{
		electricEffectG.setText(effect) ;
	}	
	public void setFairyEffectG(String effect)
	{
		fairyEffectG.setText(effect) ;
	}	
	public void setGhostEffectG(String effect)
	{
		ghostEffectG.setText(effect) ;
	}	
	public void setFlyingEffectG(String effect)
	{
		flyingEffectG.setText(effect) ;
	}	
	public void setGrassEffectG(String effect)
	{
		grassEffectG.setText(effect) ;
	}	
	public void setFireEffectG(String effect)
	{
		fireEffectG.setText(effect) ;
	}		
	public void setFightingEffectG(String effect)
	{
		fightingEffectG.setText(effect) ;
	}		
	public void setIceEffectG(String effect)
	{
		iceEffectG.setText(effect) ;
	}	
	public void setGroundEffectG(String effect)
	{
		groundEffectG.setText(effect) ;
	}	
	public void setPoisonEffectG(String effect)
	{
		poisonEffectG.setText(effect) ;
	}	
	public void setNormalEffectG(String effect)
	{
		normalEffectG.setText(effect) ;
	}	
	public void setSteelEffectG(String effect)
	{
		steelEffectG.setText(effect) ;
	}		
	public void setWaterEffectG(String effect)
	{
		waterEffectG.setText(effect) ;
	}		
	public void setRockEffectG(String effect)
	{
		rockEffectG.setText(effect) ;
	}		
	public void setPsychicEffectG(String effect)
	{
		psychicEffectG.setText(effect) ;
	}	
	
	//Sets the name label for the Pokemon
	public void setPokeName(String name)
	{
		pokeName.setText(name) ;
		pokeNameA.setText(name) ;
		pokeNameG.setText(name) ;
	}	
	
	//Sets the capture rate of the Pokemon
	public void setCatchRate(String rate)
	{
		catchRate.setText(rate) ;
		catchRateA.setText(rate) ;
		catchRateG.setText(rate) ;
	}
	
	//Sets the primary type of the Pokemon
	public void setPrimaryType(String type)
	{
		primaryType.setText(type) ;
	}	
	//Sets the primary type of the Alolan form
	public void setPrimaryTypeA(String type)
	{
		primaryTypeA.setText(type) ;
	}		
	//Sets the primary type of the Galarian form
	public void setPrimaryTypeG(String type)
	{
		primaryTypeG.setText(type) ;
	}

	//Sets the secondary type of the Pokemon
	public void setSecondaryType(String type)
	{
		SecondaryType.setText(type) ;
	}	
	//Sets the secondary type of the Alolan form
	public void setSecondaryTypeA(String type)
	{
		SecondaryTypeA.setText(type) ;
	}	
	//Sets the secondary type of the Galarian form
	public void setSecondaryTypeG(String type)
	{
		SecondaryTypeG.setText(type) ;
	}
	
	//Sets the sprite image for the Pokemon
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

	//Sets the sprite image for the Pokemon when a different form is selected
	public void setFormSprite(String imageName)
	{
		
		String sprite = "file:resources/images/" + imageName + ".png" ;
		Image pokemon = new Image(sprite,400,400,true,true) ;
		pokemonSprite.setImage(pokemon) ;
	}

	//Sets the sprite image for the Alolan form
	public void setSpriteA(int number)
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
		
		String sprite = "file:resources/images/" + stringNum + "_2.png" ;
		Image pokemon = new Image(sprite,400,400,true,true) ;
		pokemonSpriteA.setImage(pokemon) ;
	}

	//Sets the sprite image for the Galarian form
	public void setSpriteG(int number)
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
		
		String sprite = "file:resources/images/" + stringNum + "_3.png" ;
		Image pokemon = new Image(sprite,400,400,true,true) ;
		pokemonSpriteG.setImage(pokemon) ;
	}
	
	//Sets the dex number of the selected Pokemon
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
	//Sets the dex number of the selected Pokemon on the Alola tab
	public void setAlolanDexNum(int number)
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
		alolaDexNum.setText(stringNum) ;
	}
	//Sets the dex number of the selected Pokemon on the Galar tab
	public void setGalarDexNum(int number)
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
		galarDexNum.setText(stringNum) ;
	}
	
	//Allows for hiding of Label if no ability is present 
	public void setAbility2Label(String type)
	{
		secondaryAbilityLabel.setText(type) ;
	}
	public void setAbility2LabelA(String type)
	{
		secondaryAbilityLabelA.setText(type) ;
	}
	public void setAbility2LabelG(String type)
	{
		secondaryAbilityLabelG.setText(type) ;
	}
	
	public void setHiddenAbilityLabel(String type)
	{
		hiddenAbilityLabel.setText(type) ;
	}	
	public void setHiddenAbilityLabelA(String type)
	{
		hiddenAbilityLabelA.setText(type) ;
	}	
	public void setHiddenAbilityLabelG(String type)
	{
		hiddenAbilityLabelG.setText(type) ;
	}
	
	//Sets the button's text to the name of the Pokemon's ability
	public void setAbility1ButtonText(String type)
	{
		primaryAbilityButtonText.setText(type) ;
		
	}	
	public void setAbility1ButtonTextA(String type)
	{
		primaryAbilityButtonTextA.setText(type) ;
		
	}	
	public void setAbility1ButtonTextG(String type)
	{
		primaryAbilityButtonTextG.setText(type) ;
		
	}
	
	public void setAbility2ButtonText(String type)
	{
		secondaryAbilityButtonText.setText(type) ;
	}	
	public void setAbility2ButtonTextA(String type)
	{
		secondaryAbilityButtonTextA.setText(type) ;
	}	
	public void setAbility2ButtonTextG(String type)
	{
		secondaryAbilityButtonTextG.setText(type) ;
	}
	
	//Allows hiding of buttons if no ability is available
	public void hideAbility2Button() 
	{
		secondaryAbilityButtonText.setVisible(false) ;
	}	
	public void hideAbility2ButtonA() 
	{
		secondaryAbilityButtonTextA.setVisible(false) ;
	}	
	public void hideAbility2ButtonG() 
	{
		secondaryAbilityButtonTextG.setVisible(false) ;
	}

	//Sets the button's text to the name of the Pokemon's ability
	public void setHiddenAbilityButtonText(String type)
	{
		hiddenAbilityButtonText.setText(type) ;
	}	
	public void setHiddenAbilityButtonTextA(String type)
	{
		hiddenAbilityButtonTextA.setText(type) ;
	}	
	public void setHiddenAbilityButtonTextG(String type)
	{
		hiddenAbilityButtonTextG.setText(type) ;
	}

	//Allows hiding of buttons if no ability is available
	public void hideAbilityHiddenButton() 
	{
		hiddenAbilityButtonText.setVisible(false) ;
	}	
	public void hideAbilityHiddenButtonA() 
	{
		hiddenAbilityButtonTextA.setVisible(false) ;
	}	
	public void hideAbilityHiddenButtonG() 
	{
		hiddenAbilityButtonTextG.setVisible(false) ;
	}
	
	//When an ability button is clicked, an ability search is executed using the text on the button
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
		
		DBQuery.disconnect(connection) ;
		
		Stage stage = new Stage() ;
		stage.setScene(new Scene(root)) ;  
		stage.initModality(Modality.APPLICATION_MODAL) ;
		stage.show() ;
	}
	
	//Sets the Pokemon's method to evolve
	public void setEvolutionMethod(String type)
	{
		evolutionMethod.setText(type) ;
	}
	public void setEvolutionMethodA(String type)
	{
		evolutionMethodA.setText(type) ;
	}
	public void setEvolutionMethodG(String type)
	{
		evolutionMethodG.setText(type) ;
	}
	
	//Sets whether or not a Pokemon has a mega evolution
	public void setHasMega(String type)
	{
		hasMega.setText(type) ;
	}

	//Calculates and sets the effectiveness of each individual type against any two type combination
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
	public void setEffectivenessA(String type1, String type2)
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
		
		this.setNormalEffectA(Matchups.getMultiplyer(typeChart[0])) ;
		this.setFireEffectA(Matchups.getMultiplyer(typeChart[1])) ;
		this.setWaterEffectA(Matchups.getMultiplyer(typeChart[2])) ;
		this.setGrassEffectA(Matchups.getMultiplyer(typeChart[3])) ;
		this.setElectricEffectA(Matchups.getMultiplyer(typeChart[4])) ;
		this.setIceEffectA(Matchups.getMultiplyer(typeChart[5])) ;
		this.setFightingEffectA(Matchups.getMultiplyer(typeChart[6])) ;
		this.setPoisonEffectA(Matchups.getMultiplyer(typeChart[7])) ;
		this.setGroundEffectA(Matchups.getMultiplyer(typeChart[8])) ;
		this.setFlyingEffectA(Matchups.getMultiplyer(typeChart[9])) ;
		this.setPsychicEffectA(Matchups.getMultiplyer(typeChart[10])) ;
		this.setBugEffectA(Matchups.getMultiplyer(typeChart[11])) ;
		this.setRockEffectA(Matchups.getMultiplyer(typeChart[12])) ;
		this.setGhostEffectA(Matchups.getMultiplyer(typeChart[13])) ;
		this.setDarkEffectA(Matchups.getMultiplyer(typeChart[14])) ;
		this.setDragonEffectA(Matchups.getMultiplyer(typeChart[15])) ;
		this.setSteelEffectA(Matchups.getMultiplyer(typeChart[16])) ;
		this.setFairyEffectA(Matchups.getMultiplyer(typeChart[17])) ;
	}	
	public void setEffectivenessG(String type1, String type2)
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
		
		this.setNormalEffectG(Matchups.getMultiplyer(typeChart[0])) ;
		this.setFireEffectG(Matchups.getMultiplyer(typeChart[1])) ;
		this.setWaterEffectG(Matchups.getMultiplyer(typeChart[2])) ;
		this.setGrassEffectG(Matchups.getMultiplyer(typeChart[3])) ;
		this.setElectricEffectG(Matchups.getMultiplyer(typeChart[4])) ;
		this.setIceEffectG(Matchups.getMultiplyer(typeChart[5])) ;
		this.setFightingEffectG(Matchups.getMultiplyer(typeChart[6])) ;
		this.setPoisonEffectG(Matchups.getMultiplyer(typeChart[7])) ;
		this.setGroundEffectG(Matchups.getMultiplyer(typeChart[8])) ;
		this.setFlyingEffectG(Matchups.getMultiplyer(typeChart[9])) ;
		this.setPsychicEffectG(Matchups.getMultiplyer(typeChart[10])) ;
		this.setBugEffectG(Matchups.getMultiplyer(typeChart[11])) ;
		this.setRockEffectG(Matchups.getMultiplyer(typeChart[12])) ;
		this.setGhostEffectG(Matchups.getMultiplyer(typeChart[13])) ;
		this.setDarkEffectG(Matchups.getMultiplyer(typeChart[14])) ;
		this.setDragonEffectG(Matchups.getMultiplyer(typeChart[15])) ;
		this.setSteelEffectG(Matchups.getMultiplyer(typeChart[16])) ;
		this.setFairyEffectG(Matchups.getMultiplyer(typeChart[17])) ;
	}

	//Allows the user to return to the original search window to search a new Pokemon when the "New Search" option is chosen in the File Menu
	public void newSearch(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSearch.fxml")) ;
		root = loader.load() ;		
		stage = (Stage)newSearchButton.getParentPopup().getOwnerWindow() ;
		scene = new Scene(root) ;
		stage.setScene(scene) ;
		stage.show() ;
	}
	
	//Allows the user to return to the original search window to search a new Pokemon when the "New Search" button is clicked
	public void newSearch2(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSearch.fxml")) ;
		root = loader.load() ;		
		stage = (Stage)newSearchButton2.getScene().getWindow() ;
		scene = new Scene(root) ;
		stage.setScene(scene) ;
		stage.show() ;
	}
	
	//Allows the user to close the window with the "Close" option from the File Menu
	public void closeProgram(ActionEvent actionEvent)
	{
		stage = (Stage) mainWindow.getScene().getWindow() ;
		stage.close() ;
	}

	
	//Diables the Alola tab when no Alolan form is present
	public void disableAlola()
	{
		alolanTab.setDisable(true) ;		
	}

	//Diables the Alola tab when no Galarian form is present
	public void disableGalar()
	{
		galarianTab.setDisable(true) ;		
	}

	//Allows the form changing options to be hidden when no alternate forms are present
	public void setFormChoice(boolean visibility)
	{
		formChoice.setVisible(visibility) ;
		formTitle.setVisible(visibility) ;
		formChangeTitle.setVisible(visibility) ;
		formChangeInfo.setVisible(visibility) ; ;
	}
	
	//Allows adding of new form names to the form dropdown menu
	public void addForm(String formName)
	{
		formChoice.getItems().add(formName) ;
		formChoice.getSelectionModel().selectFirst();
	}
	
	//Sets the text describing how to achieve alternate forms
	public void setFormChange(String changeText)
	{
		formChangeInfo.setText(changeText) ;
	}
	
	//Changes the relevant information (image, type, effectiveness, etc.) when a new form is selected
	public void changeForm() throws SQLException
	{
		Connection connection = DBQuery.connect() ;
		
		String sqlForm = "SELECT * FROM pokemonforms where UPPER(pokename) = UPPER('" + pokeName.getText() + "') and UPPER(formname) = UPPER('" + formChoice.getValue() + "')" ;
		Statement statementForm = connection.createStatement() ;
		ResultSet resultForm = statementForm.executeQuery(sqlForm) ;
		
		resultForm.next() ;
		
		String type1 = resultForm.getString("type1") ;
		setPrimaryType(type1) ;
		
		String type2 = resultForm.getString("type2") ;
		setSecondaryType(type2) ;
		
		setEffectiveness(type1, type2) ;
		
		String ability1Text = resultForm.getString("ability1") ;
		setAbility1ButtonText(ability1Text) ;
		
		String ability2Text = resultForm.getString("ability2") ;
		if(ability2Text == null)
		{
			setAbility2Label("") ;
			hideAbility2Button() ;
		}
		setAbility2ButtonText(ability2Text) ;
		
		String hiddenAbilityText = resultForm.getString("abilityhidden") ;
		if(hiddenAbilityText == null)
		{
			setHiddenAbilityLabel("") ;
			hideAbilityHiddenButton() ;
		}
		setHiddenAbilityButtonText(hiddenAbilityText) ;
		
		String formImage  = resultForm.getString("imagename") ;
		setFormSprite(formImage) ;
		
		String changeMethod = resultForm.getString("howtochange") ;
		setFormChange(changeMethod) ;
		
		DBQuery.disconnect(connection) ;
		
	}
	
	//Displays information on where and how to encounter each Pokemon in the available regions
	public void setLocationInfo() throws SQLException
	{
		pokeNameL.setText(pokeName.getText().toUpperCase() + " Locations") ;
		
		Connection connection = DBQuery.connect() ;
		
		if(regionSelect.getValue().equals("Unova"))
		{
			String locations = "" ;
			String header = "                Location                 |   Encounter Method   |           Seasons Available          |    Rarity   |            Games Available           |      Level Range      |                                 Special                                  \n" ;
			String divider = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" ;
			
			locations += "Rarity: Common (21-100%) Uncommon (6-20%) Rare (1-5%)\n\n" ;
			locations += header + divider ;
	
			String sql = "SELECT * FROM unovalocations where UPPER(pokemon) = UPPER('" + pokeName.getText() + "')" ; //Create SELECT statement
	
			Statement statement = connection.createStatement() ; //Create statement on the connection	
			ResultSet result = statement.executeQuery(sql) ; //Create ResultSet for result to be stored
			
			if(result.next())
			{				
				do
				{
					String place = result.getString("Location") ;
					place = String.format("%-" + 40 + "s", place) ;
					String method = result.getString("Method") ;
					method = String.format("%-" + 20 + "s", method) ;				
					String season = result.getString("Seasons") ;
					season = String.format("%-" +35 + "s", season) ;
					String rarity = result.getString("Rarity") ;
					rarity = String.format("%-" + 10 + "s", rarity) ;
					String levels = result.getString("Levels") ;
					levels = String.format("%-" + 15 + "s", levels) ;
					String  gameFound = "" ;
					
					locations += place + " | " + method + " |  " + season + " |  " + rarity + " |  " ;
					Boolean black = false;
					Boolean white = false ;
					Boolean black2 = false ;
					
					if(result.getString("Black").equals("Y"))
					{
							gameFound += "Black" ;
							black = true ;
					}
					if(result.getString("White").equals("Y") && black)
					{
						gameFound += ", White" ;
						white = true ;
					}
					else if(result.getString("White").equals("Y")){
						gameFound += "White" ;
						white = true ;
					}
					if(result.getString("Black2").equals("Y") && (black||white))
					{
						gameFound += ", Black 2" ;
						black2 = true ;
					}
					else if(result.getString("Black2").equals("Y")){
						gameFound += "Black 2" ;
						black2 = true ;
					}
					if(result.getString("White2").equals("Y") && (black||white||black2))
					{
						gameFound += ", White 2" ;
						white = true ;
					}
					else if(result.getString("White2").equals("Y")){
						gameFound += "White 2" ;
					}
							
		
					gameFound = String.format("%-" + 35 + "s", gameFound) ;
					String special = result.getString("Special") ;				
					if(special == null)
					{
						special = "" ;
					}			
					special = String.format("%-" + 70 + "s", special) ;	
					
					locations += gameFound + " |       " + levels + " | " + special ;
					
							
					locations += "\n" ;
				}while(result.next()) ;
				
				locationInfo.setText(locations) ;		
			}
			else {
				locationInfo.setText("This Pokemon is not available in this region") ;
			}
		}
		if(regionSelect.getValue().equals("Kalos"))
		{
			String locations = "" ;
			String header = "                Location                 |   Encounter Method   |    Rarity   |       Games Available      |     Level Range    |                                 Special                                  \n" ;
			String divider = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" ;
			
			locations += "Rarity: Common (21-100%) Uncommon (6-20%) Rare (1-5%)\n\n" ;
			locations += header + divider ;
	
			String sql = "SELECT * FROM kaloslocations where UPPER(pokemon) = UPPER('" + pokeName.getText() + "')" ; //Create SELECT statement
	
			Statement statement = connection.createStatement() ; //Create statement on the connection	
			ResultSet result = statement.executeQuery(sql) ; //Create ResultSet for result to be stored
			
			if(result.next())
			{				
				do
				{
					String place = result.getString("Location") ;
					place = String.format("%-" + 40 + "s", place) ;
					String method = result.getString("Method") ;
					method = String.format("%-" + 20 + "s", method) ;
					String rarity = result.getString("Rarity") ;
					rarity = String.format("%-" + 10 + "s", rarity) ;
					String levels = result.getString("Levels") ;
					levels = String.format("%-" + 20 + "s", levels) ;
					String  gameFound = "" ;
					
					locations += place + " | " + method + " |  " + rarity + " |  " ;
					Boolean x = false;
					
					if(result.getString("X").equals("Y"))
					{
						gameFound += "          X" ;
						x = true ;
					}
					if(result.getString("Y").equals("Y") && x)
					{
						gameFound += " and Y" ;
					}
					else if(result.getString("Y").equals("Y")){
						gameFound += "          Y" ;
					}							
		
					gameFound = String.format("%-" + 25 + "s", gameFound) ;
					String special = result.getString("Special") ;				
					if(special == null)
					{
						special = "" ;
					}			
					special = String.format("%-" + 70 + "s", special) ;	
					
					locations += gameFound + " | " + levels + " | " + special ;
					 
							
					locations += "\n" ;
				}while(result.next()) ;
				
				locationInfo.setText(locations) ;		
			}
			else {
				locationInfo.setText("This Pokemon is not available in this region") ;
			}
		}
		
		DBQuery.disconnect(connection) ;
	}
}















