package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class loseScreenController {

    //Code for button that returns player to the title screen
    @FXML
    void retryButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight trans = new Redlightgreenlight();
        trans.changeScene("titleScreen.fxml");

    }

}

