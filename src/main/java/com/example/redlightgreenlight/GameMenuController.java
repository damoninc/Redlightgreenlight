package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameMenuController {

    @FXML
    private Text Exit;


    @FXML
    private Text HowToPlay;


    @FXML
    private Rectangle InstructionsRectangle;

    @FXML
    private Text InstructionsTextArea;

    @FXML
    private Text Play;


    @FXML
    void exitButtonPressed(ActionEvent event) {
        // Exits game

    }

    @FXML
    void exitMouseEntered(MouseEvent event) {
        Exit.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));

    }

    @FXML
    void exitMouseExited(MouseEvent event) {
        Exit.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));

    }

    @FXML
    void howToPlayMouseEntered(MouseEvent event) {
        HowToPlay.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));

    }

    @FXML
    void howToPlayMouseExited(MouseEvent event) {
        HowToPlay.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));

    }

    @FXML
    void howToPlayPressed(ActionEvent event) {
        // change opacity to 1 for text and rectangle

    }

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level1.fxml");

    }

    @FXML
    void playMouseEntered(MouseEvent event) {
        Play.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));

    }

    @FXML
    void playMouseExited(MouseEvent event) {
        Play.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));

    }

}
