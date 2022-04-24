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

    }

    @FXML
    void exitMouseEntered(MouseEvent event) {

    }

    @FXML
    void exitMouseExited(MouseEvent event) {

    }

    @FXML
    void howToPlayMouseEntered(MouseEvent event) {

    }

    @FXML
    void howToPlayMouseExited(MouseEvent event) {

    }

    @FXML
    void howToPlayPressed(ActionEvent event) {

    }

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {
        Redlightgreenlight game = new Redlightgreenlight();
        game.changeScene("Level1.fxml");

    }

    @FXML
    void playMouseEntered(MouseEvent event) {
        Play.setFill(Color.GREEN);
        System.out.println("Hello");

    }

    @FXML
    void playMouseExited(MouseEvent event) {
        Play.setFill(Color.DEEPPINK);

    }

}
