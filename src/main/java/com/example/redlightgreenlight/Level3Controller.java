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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level3Controller implements Initializable {

    int time;
    Timer labelTimer;

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 5;

    private TranslateTransition transition;

    @FXML
    private ImageView ronaldo;

    @FXML
    private ImageView felaini;

    @FXML
    private ImageView imageForCollision;

    @FXML
    private ImageView slidingGuy;

    @FXML
    private ImageView messi;

    @FXML
    private ImageView neymar;

    @FXML
    private ImageView courtois;

    @FXML
    private ImageView endLine;

    @FXML
    private Label timerNumLabel;

    @FXML
    private Pane scene;

    @FXML
    void start(){
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if ((checkCollision(ronaldo, messi)) || (checkCollision(ronaldo,neymar) == true) || (checkCollision(ronaldo,courtois) == true) || (checkCollision(ronaldo,felaini) == true) || (checkCollision(ronaldo,slidingGuy) == true) ){
                ronaldo.setLayoutX(60);
                ronaldo.setLayoutY(320);
                labelTimer.cancel();
                setTimer();
            }

            if (checkCollision(ronaldo,endLine) == true){
                gameWin();
            }

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
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerNumLabel.setText(String.valueOf(time));
        setTimer();
        movementSetup();
        moveImageY(neymar, 3, scene.getPrefHeight());
        moveImageY(messi, 2, scene.getPrefHeight());
        moveImageY(courtois,0.8, scene.getPrefHeight()/1.25);
        moveImageX(felaini, 2, scene.getPrefWidth()/1.85);
        moveImageX(slidingGuy, 2, -scene.getPrefWidth()/2.30);
        keyPressed.addListener(((observableValue, aBoolean, t1)-> {
                timer.start();
        }));
    }

    public void setTimer() {
        labelTimer = new Timer();
        time = 11;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(time > 0)
                {
                    time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(time)));
                }
                else
                    labelTimer.cancel();
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
    public boolean checkCollision(ImageView image1, ImageView image2){
        if(image1.getBoundsInParent().intersects(image2.getBoundsInParent())){
            return true;
        }
        return false;
    }
    public void moveImageY(ImageView image1, double velocity, double distance){
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(velocity));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToY(distance - image1.getFitHeight());
        transition.setAutoReverse(true);
        transition.play();
    }
    public void moveImageX(ImageView image1, double velocity, double distance){
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(velocity));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToX(distance - image1.getFitHeight());
        transition.setAutoReverse(true);
        transition.play();
    }

    public void gameWin(){
        Redlightgreenlight game = new Redlightgreenlight();
        System.out.println("transition");
        try {
            ronaldo.setLayoutX(2000);
            labelTimer.cancel();
            game.changeScene("winScreen.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}