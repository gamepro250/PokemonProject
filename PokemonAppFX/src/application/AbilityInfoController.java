package application;



import javafx.event.ActionEvent ;
import javafx.fxml.FXML ;
import javafx.scene.control.Label ;
import javafx.scene.control.TextArea ;
import javafx.scene.layout.GridPane ;
import javafx.stage.Stage ;

public class AbilityInfoController
{
	@FXML TextArea AbilityDescription ;
	@FXML Label AbilityName ;
	@FXML private GridPane mainWindow ;
	private Stage stage ;
	
	//Sets the text box to display the Ability's text
	public void setAbilityDescriptionText(String description)
	{
		AbilityDescription.setText(description) ;
	}
	
	//Sets the title as the Ability's name
	public void setAbilityNameText(String ability)
	{
		AbilityName.setText(ability) ;
	}
	
	//Close window when the Close Button is clicked
	public void closeProgram(ActionEvent actionEvent)
	{
		stage = (Stage) mainWindow.getScene().getWindow() ;
		stage.close() ;
	}
}
