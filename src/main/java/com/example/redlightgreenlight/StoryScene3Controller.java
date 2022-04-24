package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoryScene3Controller {

    @FXML
    private ImageView buttersImg;

    @FXML
    private Label buttersLabel;

    @FXML
    private Label instructLabel;

    @FXML
    private Button lvl2Button;

    @FXML
    private ImageView roadImg;

    @FXML
    private ImageView roadImg2;

    @FXML
    private AnchorPane storyPane;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level2.fxml");
    }
}

