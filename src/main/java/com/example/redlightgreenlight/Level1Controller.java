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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level1Controller implements Initializable {

    
    @FXML
    private ImageView player;

    @FXML
    private ImageView rock;

    @FXML
    private Label timerNumLabel;

    @FXML
    private Pane scene;

    @FXML
    private Rectangle finishLine;

    @FXML
    private Label greenredLabel;

    @FXML
    private ImageView peeker;

    Image peeking = new Image(getClass().getResourceAsStream("SansLooking.png"));
    Image notpeeking = new Image(getClass().getResourceAsStream("SansNotLooking.png"));

    @FXML
    void start(){
    }

    // GAME FUNCTIONALITY
    int game_time;
    int redLightTimer;
    int greenLightTimer;
    Timer labelTimer;
    Timer redTimer;
    Timer greenTimer;
    double x;
    double y;


    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 2;

    private TranslateTransition transition;


    AnimationTimer timer = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            if (checkCollision(player, finishLine)){
                gameWin();
            }

            if (checkCollision(player, rock)){
                movementVariable = 1;
            }
            else movementVariable = 2;


            if(wPressed.get() && player.getLayoutY() > 364) {
                player.setLayoutY(player.getLayoutY() - movementVariable);

            }

            if(sPressed.get() && player.getLayoutY() < 484){
                player.setLayoutY(player.getLayoutY() + movementVariable);

            }

            if(aPressed.get() && player.getLayoutX() > 0){
                player.setLayoutX(player.getLayoutX() - movementVariable);

            }

            if(dPressed.get() && player.getLayoutX() < scene.getPrefWidth() - player.getFitWidth()){
                player.setLayoutX(player.getLayoutX() + movementVariable);

            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerNumLabel.setText(String.valueOf(game_time));
        countdownGl();
        setTimer();
        movementSetup();
        keyPressed.addListener(((observableValue, aBoolean, t1)-> {
            timer.start();
        }));
    }

    public void setTimer() {
        Redlightgreenlight game = new Redlightgreenlight();
        labelTimer = new Timer();
        game_time = 30;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(game_time > 0)
                {
                    game_time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(game_time)));
                }
                else {
                    try {
                        System.out.println("transition");
                        game.changeScene("loseScreen.fxml");
                        labelTimer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0,1000);
    }

    public void countdownGl(){
        greenTimer = new Timer();
        int random = (int)(Math.random() * 6) + 3;
        greenLightTimer = 5;
        System.out.println(greenLightTimer);
        greenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (greenLightTimer > 1) {
                    greenLightTimer -= 1;
                    System.out.println(greenLightTimer);
                } else if (greenLightTimer == 1) {
                    System.out.println("Redlight");
                    greenredLabel.setTextFill(Color.RED);
                    Platform.runLater(() -> greenredLabel.setText("Red light!"));
                    greenLightTimer -= 1;
                }else{
                    peeker.setImage(peeking);
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    greenTimer.cancel();
                    countdownRl();
                }

            }
        }, 0, 1000);

    }

    public void countdownRl(){
        redTimer = new Timer();
        int random = (int)(Math.random() * 6) + 3;
        redLightTimer = 5;
        System.out.println(redLightTimer);
        redTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (redLightTimer != 0){
                    checkMovement(player,x,y);
                }
                if (redLightTimer > 1) {
                    redLightTimer -= 1;
                    System.out.println(redLightTimer);
                } else if (redLightTimer == 1) {
                    System.out.println("GreenLight");
                    greenredLabel.setTextFill(Color.GREEN);
                    System.out.println(greenredLabel.getTextFill());
                    Platform.runLater(() -> greenredLabel.setText("Green light!"));
                    peeker.setImage(notpeeking);
                    redLightTimer -= 1;

                }else{
                    redTimer.cancel();
                    countdownGl();
                }

            }
        }, 0, 1000);

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
    } // End Collision Checkers

    public void checkMovement(ImageView image, double x, double y){
        if (image.getLayoutX() != x || image.getLayoutY() != y){
            player.setLayoutX(0);
            player.setLayoutY(431);
            redTimer.cancel();
            greenredLabel.setTextFill(Color.GREEN);
            Platform.runLater(() -> greenredLabel.setText("Green light!"));
            countdownGl();
        }
    }

    public void moveImage(ImageView image1){
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(2));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToY(scene.getPrefHeight() - image1.getFitHeight());
        transition.setAutoReverse(true);
        transition.play();
    }

    public void gameWin(){
        Redlightgreenlight game = new Redlightgreenlight();
        System.out.println("transition");
        try {
            player.setLayoutX(2000);
            labelTimer.cancel();
            greenTimer.cancel();
            redTimer.cancel();
            game.changeScene("Level2.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}