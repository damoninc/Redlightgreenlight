package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class StoryScene2Controller {

    @FXML
    private Text PlayLevel1;

    @FXML
    void playLevel1ButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level1.fxml");  // changes scene to level 1

    }

    @FXML
    void playLevel1MouseEntered(MouseEvent event) {
        PlayLevel1.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // changes color to green
        PlayLevel1.setTranslateX(30);  // Moves PlayLevel1 to the right

    }

    @FXML
    void playLevel1MouseExited(MouseEvent event) {
        PlayLevel1.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // changes color to pink
        PlayLevel1.setTranslateX(0);  // Moves PlayLevel1 back to its orig. spot

    }

}
