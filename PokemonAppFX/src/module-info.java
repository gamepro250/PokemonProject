module PokemonAppFX {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	requires java.sql ;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	requires java.desktop;
}
