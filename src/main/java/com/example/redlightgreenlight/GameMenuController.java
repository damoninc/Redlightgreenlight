package com.example.redlightgreenlight;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameMenuController {

    public class GameMenu {

        @FXML
        private ImageView BackgroundIMGView;

        @FXML
        private Text Exit;

        @FXML
        private Rectangle ExitRectangle;

        @FXML
        private Label GreenLightLabel;

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
        private Label RedLightLabel;

        @FXML
        private Pane ScreenPane;

        @FXML
        void OnMouseClicked(MouseEvent event) {

        }

        @FXML
        void OnMouseEntered(MouseEvent event) {

        }

        @FXML
        void OnMouseExited(MouseEvent event) {

        }

    }

}
