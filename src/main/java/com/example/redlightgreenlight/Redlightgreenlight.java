package com.example.redlightgreenlight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Redlightgreenlight extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    private static Stage game_stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        game_stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("titleScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Red Light Green Light");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        game_stage.getScene().setRoot(pane);
    }

}
