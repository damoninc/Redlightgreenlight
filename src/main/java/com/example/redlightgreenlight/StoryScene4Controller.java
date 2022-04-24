package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoryScene4Controller {

    @FXML
    private ImageView fieldImg;

    @FXML
    private Label instructionLabel;

    @FXML
    private Button lvl3Button;

    @FXML
    private ImageView ronaldoImg;

    @FXML
    private Label ronaldoLabel;

    @FXML
    private AnchorPane storyPane;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level3.fxml");

    }
    //
}
