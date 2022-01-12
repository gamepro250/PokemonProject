package application;


import java.io.IOException ;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application 
{

public static void main(String[] args) 
{
 launch(args);
}

 @Override
 public void start(Stage stage) 
 {
	 try
	{
		Parent root = FXMLLoader.load(getClass().getResource("MainSearch.fxml")) ;
		Scene scene = new Scene(root) ;
		stage.setTitle("Pokemon Assistant") ;
		stage.setScene(scene) ;
		stage.show() ;
		
	} catch (IOException e)
	{
		e.printStackTrace() ;
	}
 } 

}



















