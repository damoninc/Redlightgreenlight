package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class StoryScene1Controller{

    @FXML
    private Text EnterLevel1;

    @FXML
    void EnterLevel1ButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("StoryScene2.fxml");  // changes scene to next Story Scene 2

    }

    @FXML
    void enterLevel1MouseEntered(MouseEvent event) {
        EnterLevel1.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0)); // changes color to green
        EnterLevel1.setTranslateX(30);  // Moves Enter Level1 to the right

    }

    @FXML
    void enterLevel1MouseExited(MouseEvent event) {
        EnterLevel1.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));  // changes color to pink
        EnterLevel1.setTranslateX(0);  // Moves Enter Level 1 back to its orig. spot

    }

}