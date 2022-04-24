package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class StoryScene4Controller {

    @FXML
    private Text PlayLevel3;

    @FXML
    void playLevel3ButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("level3.fxml");  // changes scene to Level3
    }


    @FXML
    void playLevel3MouseEntered(MouseEvent event) {
        PlayLevel3.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // changes color to green
        PlayLevel3.setTranslateX(30);  // Moves PlayLevel3 to the right
    }

    @FXML
    void playLevel3MouseExited(MouseEvent event) {
        PlayLevel3.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // changes color to pink
        PlayLevel3.setTranslateX(0);  // Moves PlayLevel3 back to its orig. spot

    }
}
