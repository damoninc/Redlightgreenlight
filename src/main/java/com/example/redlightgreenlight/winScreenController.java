package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class winScreenController {


    @FXML
    private Text PlayAgain;

    @FXML
    private Text Return;

    //Code for button that takes player back into Level 1
    @FXML
    void playAgainButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight playAgain = new Redlightgreenlight();
        playAgain.changeScene("Level1.fxml");
    }

    //Code for button that takes player back to menu
    @FXML
    void returnMenuButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight menuTrans = new Redlightgreenlight();
        menuTrans.changeScene("GameMenuScreen.fxml");
    }


    @FXML
    void playAgainMouseEntered(MouseEvent event) {
        PlayAgain.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // Changes color to green
        PlayAgain.setTranslateX(30);  // Moves PlayAgain to the right

    }

    @FXML
    void playAgainMouseExited(MouseEvent event) {
        PlayAgain.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // Changes color to pink
        PlayAgain.setTranslateX(0);  // Moves PlayAgain back to its orig. spot
    }

    @FXML
    void returnMouseEntered(MouseEvent event) {
        Return.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // Changes color to green
        Return.setTranslateX(30);  // Moves Return to the right

    }

    @FXML
    void returnMouseExited(MouseEvent event) {
        Return.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // Changes color to pink
        Return.setTranslateX(0);  // Moves Return back to its orig. spot


    }

}