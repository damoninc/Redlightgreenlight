package com.example.redlightgreenlight;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
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

public class Level1Controller implements Initializable {

    
    @FXML
    private ImageView ronaldo;

    @FXML
    private ImageView messi;

    @FXML
    private Label timerNumLabel;

    @FXML
    private Pane scene;

    @FXML
    private Rectangle finishLine;

    @FXML
    void start(){
        System.out.println(ronaldo.getLayoutX());
        System.out.println(ronaldo.getLayoutY());
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

            if (checkCollision(ronaldo, messi)){
                ronaldo.setLayoutX(120);
                ronaldo.setLayoutY(260);
                labelTimer.cancel();
                setTimer();
            }

            if (checkCollision(ronaldo, finishLine)){
                gameWin();
            }

            if(wPressed.get() && ronaldo.getLayoutY() > 364) {
                ronaldo.setLayoutY(ronaldo.getLayoutY() - movementVariable);
            }

            if(sPressed.get() && ronaldo.getLayoutY() < 484){
                ronaldo.setLayoutY(ronaldo.getLayoutY() + movementVariable);
            }

            if(aPressed.get() && ronaldo.getLayoutX() > 0){
                ronaldo.setLayoutX(ronaldo.getLayoutX() - movementVariable);
            }

            if(dPressed.get() && ronaldo.getLayoutX() < scene.getPrefWidth() - ronaldo.getFitWidth()){
                ronaldo.setLayoutX(ronaldo.getLayoutX() + movementVariable);
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerNumLabel.setText(String.valueOf(time));
        setTimer();
        movementSetup();
        moveImage(messi);
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
                        game.changeScene("titleScreenController.fxml");
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

    public void moveImage(ImageView image1){
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(2));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToY(scene.getPrefHeight() - messi.getFitHeight());
        transition.setAutoReverse(true);
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