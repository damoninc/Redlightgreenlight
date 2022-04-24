package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class StoryScene3Controller {

    @FXML
    private Text PlayLevel2;

    @FXML
    void playLevel2ButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level2.fxml");  // changes scene to level 2

    }

    @FXML
    void playLevel2MouseEntered(MouseEvent event) {
        PlayLevel2.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // changes color to green
        PlayLevel2.setTranslateX(30);  // Moves PlayLevel2 to the right

    }

    @FXML
    void playLevel2MouseExited(MouseEvent event) {
        PlayLevel2.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // changes color to pink
        PlayLevel2.setTranslateX(0);  // Moves PlayLevel2 back to its orig. spot

    }

}