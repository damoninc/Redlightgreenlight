package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class StoryScene1Controller{

    @FXML
    private ImageView backGroundBirthday;

    @FXML
    private Label labelOne;

    @FXML
    private Button nextButton;

    @FXML
    private Label instructionLabel;

    @FXML
    private AnchorPane sceneOne;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("StoryScene2");
    }
//
}