package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class titleScreenController {

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level2.fxml");

    }

}
