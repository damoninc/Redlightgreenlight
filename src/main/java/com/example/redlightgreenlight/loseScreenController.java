package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class loseScreenController {

    @FXML
    private Text Cry;

    @FXML
    private Text PlayAgain;

    @FXML
    void cryButtonPressed(ActionEvent event) {
        System.exit(0);  // exits out the game

    }

    @FXML
    void playAgainButtonPressed(ActionEvent event) throws IOException {  // User goes back to game menu when button pressed
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("GameMenuScreen.fxml");  // changes scene to Game Menu

    }

    @FXML
    void cryMouseEntered(MouseEvent event) {
        Cry.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // Changes color to green
        Cry.setTranslateX(30);  // Moves Cry About It to the right

    }

    @FXML
    void cryMouseExited(MouseEvent event) {
        Cry.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // Changes color to pink
        Cry.setTranslateX(0);  // Moves Cry About it back to its orig. spot

    }

    @FXML
    void playAgainMouseEntered(MouseEvent event) {
        PlayAgain.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // Changes color to green
        PlayAgain.setTranslateX(30);  // Moves Play Again to the right


    }

    @FXML
    void playAgainMouseExited(MouseEvent event) {
        PlayAgain.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // Changes color to pink
        PlayAgain.setTranslateX(0);  // Moves Play back to its orig. spot

    }



}

