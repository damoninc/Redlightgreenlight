package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class winScreenController {

    @FXML
    void playAgainButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight playAgain = new Redlightgreenlight();
        playAgain.changeScene("Level1.fxml");
    }

    @FXML
    void returnMenuButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight menuTrans = new Redlightgreenlight();
        menuTrans.changeScene("titleScreenController.fxml");
    }

}