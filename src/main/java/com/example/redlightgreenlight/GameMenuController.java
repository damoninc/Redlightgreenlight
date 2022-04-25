package com.example.redlightgreenlight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Text Play;

    @FXML
    private Text howToPlayTextPt1;

    @FXML
    private Text howToPlayTextPt2;

    @FXML
    private Text howToPlayTextPt3;


    @FXML
    void exitButtonPressed(ActionEvent event) {  // Closes out the game when Exit button is pressed
        System.exit(0);

    }

    @FXML
    void exitMouseEntered(MouseEvent event) {  // Changes text color from pink to green when Exit is hovered over
        Exit.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));
        Exit.setTranslateX(30);  // Moves Exit to the right

    }

    @FXML
    void exitMouseExited(MouseEvent event) {  // Changes text color back to pink when mouse leaves
        Exit.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));
        Exit.setTranslateX(0);  // Moves Exit back to its orig spot

    }

    @FXML
    void howToPlayMouseEntered(MouseEvent event) { // Changes text color from pink to green when HTP is hovered over
        HowToPlay.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));
        HowToPlay.setTranslateX(30);  // moves HTP to the right

    }

    @FXML
    void howToPlayMouseExited(MouseEvent event) {  // Changes text color back to pink when mouse leaves
        HowToPlay.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));
        HowToPlay.setTranslateX(0);  // Moves HTP back to its orig spot

    }

    @FXML
    void howToPlayPressed(ActionEvent event) {  // When HTP is pressed it will set the text and rect. bg to be visible
        InstructionsRectangle.setOpacity(1);
        howToPlayTextPt1.setOpacity(1);
        howToPlayTextPt2.setOpacity(1);
        howToPlayTextPt3.setOpacity(1);


    }

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {  // Plays the first level when Play is pressed
        Redlightgreenlight game = new Redlightgreenlight();  // starts a new game
        game.changeScene("StoryScene1.fxml");  // changes scene to level 1

    }

    @FXML
    void playMouseEntered(MouseEvent event) {  // Changes text color from pink to green when Play is hovered over
        Play.setFill(Color.color(0.3176, 0.9216, 0.3373, 1.0));
        Play.setTranslateX(30);  // Moves play to the right

    }

    @FXML
    void playMouseExited(MouseEvent event) {  // Changes text color back to pink when mouse leaves
        Play.setFill(Color.color(0.6118, 0.0078, 0.2627, 1.0));
        Play.setTranslateX(0);  // Moves Play back to its orig. spot

    }

}
