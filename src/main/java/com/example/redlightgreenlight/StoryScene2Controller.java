package com.example.redlightgreenlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoryScene2Controller {

    @FXML
    private ImageView friskImage;

    @FXML
    private Label friskLabel;

    @FXML
    private Button lvl1Button;

    @FXML
    private ImageView sceneBackground;

    @FXML
    private AnchorPane storyPane;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level1.fxml");
    }
    //
}
