package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class winScreenController {

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

}