package com.example.redlightgreenlight;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameMenuController {

        @FXML
        private Text Exit;

        @FXML
        private Rectangle ExitRectangle;

        @FXML
        private Text HowToPlay;

        @FXML
        private Rectangle HowToPlayRectangle;

        @FXML
        private Rectangle InstructionsRectangle;

        @FXML
        private Text InstructionsTextArea;

        @FXML
        private Text Play;

        @FXML
        private Rectangle PlayRectangle;


        @FXML
        private Pane ScreenPane;

        @FXML
        void OnMouseClicked(MouseEvent event) throws IOException {

        }

        @FXML
        void OnMouseEntered(MouseEvent event) {


        }

        @FXML
        void OnMouseExited(MouseEvent event) {

        }

    }

