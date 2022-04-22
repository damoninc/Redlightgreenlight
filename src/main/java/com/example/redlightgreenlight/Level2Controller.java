package com.example.redlightgreenlight;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level2Controller implements Initializable {

    @FXML
    private ImageView blackCar;

    @FXML
    private ImageView blueCar;

    @FXML
    private Rectangle finishLine;

    @FXML
    private ImageView greenCar;

    @FXML
    private ImageView ronaldo;

    @FXML
    private Pane scene;

    @FXML
    private Label timerNumLabel;

    @FXML
    private ImageView yellowCar;

    @FXML
    void start(){ // Needed for movement
    }

    // GAME FUNCTIONALITY
    int time;
    Timer labelTimer;

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 10;

    private TranslateTransition transition;

    AnimationTimer timer = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            //COLLISIONS
            if (checkCollision(ronaldo, yellowCar)){
                ronaldo.setLayoutX(120);
                ronaldo.setLayoutY(260);
                labelTimer.cancel();
                setTimer();
            }
            if (checkCollision(ronaldo, blackCar)){
                ronaldo.setLayoutX(120);
                ronaldo.setLayoutY(260);
                labelTimer.cancel();
                setTimer();
            }
            if (checkCollision(ronaldo, blueCar)){
                ronaldo.setLayoutX(120);
                ronaldo.setLayoutY(260);
                labelTimer.cancel();
                setTimer();
            }
            if (checkCollision(ronaldo, greenCar)){
                ronaldo.setLayoutX(120);
                ronaldo.setLayoutY(260);
                labelTimer.cancel();
                setTimer();
            }
            if (checkCollision(ronaldo, finishLine)){
                gameWin();
            }


            // CONTROL INPUTS
            if(wPressed.get() && ronaldo.getLayoutY() > 0) {
                ronaldo.setLayoutY(ronaldo.getLayoutY() - movementVariable);
            }

            if(sPressed.get() && ronaldo.getLayoutY() < scene.getPrefHeight() - ronaldo.getFitHeight()){
                ronaldo.setLayoutY(ronaldo.getLayoutY() + movementVariable);
            }

            if(aPressed.get() && ronaldo.getLayoutX() > 0){
                ronaldo.setLayoutX(ronaldo.getLayoutX() - movementVariable);
            }

            if(dPressed.get() && ronaldo.getLayoutX() < scene.getPrefWidth() - ronaldo.getFitWidth()){
                ronaldo.setLayoutX(ronaldo.getLayoutX() + movementVariable);
            }
        } // END EVENT HANDLING
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerNumLabel.setText(String.valueOf(time));
        setTimer();
        movementSetup();
        moveImage(yellowCar, 3, false, 1300);
        moveImage(blackCar, 2.5, true, 1300);
        moveImage(blueCar, 1.5, false, 1300);
        moveImage(greenCar, 1.3, true, 1300);
        keyPressed.addListener(((observableValue, aBoolean, t1)-> {
            timer.start();
        }));
    }

    public void setTimer() {
        Redlightgreenlight game = new Redlightgreenlight();
        labelTimer = new Timer();
        time = 11;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(time > 0)
                {
                    time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(time)));
                }
                else {
                    try {
                        System.out.println("transition");
                        game.changeScene("titleScreen.fxml");
                        labelTimer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000,1000);
    }

    public void movementSetup(){
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }

    // Collision Checkers
    public boolean checkCollision(ImageView image1, ImageView image2){
        if(image1.getBoundsInParent().intersects(image2.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public boolean checkCollision(ImageView image1, Rectangle finish){
        if(image1.getBoundsInParent().intersects(finish.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public void moveImage(ImageView image1, double velocity, boolean up, double distance){
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(velocity));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        if (up){
            transition.setToY(-distance);
        }
        else{
            transition.setToY(distance);
        }
        transition.play();
    }

    public void gameWin(){
        Redlightgreenlight game = new Redlightgreenlight();
        System.out.println("transition");
        try {
            ronaldo.setLayoutX(2000);
            labelTimer.cancel();
            game.changeScene("Level3.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
