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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level2Controller implements Initializable {

    @FXML
    private ImageView blackCar; // Moving Obstacle

    @FXML
    private ImageView blueCar; // Moving Obstacle

    @FXML
    private Rectangle finishLine; // finish line, proceeds to next level

    @FXML
    private ImageView greenCar; // Moving Obstacle

    @FXML
    private ImageView player; // Player

    @FXML
    private Pane scene;

    @FXML
    private Label timerNumLabel; // Game timer

    @FXML
    private ImageView yellowCar; // Moving Obstacle

    @FXML
    private Label greenredLabel; // Redlight green light call out

    @FXML
    private ImageView peeker; // Reorients based on phase

    // Peeker reorientation
    Image peeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("CartmanLooking.png")));
    Image notpeeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("CartmanNotLooking.png")));

    @FXML
    void start(){ // Needed for movement
    }

    // ALL GAME FUNCTIONALITY BELOW
    // -----------------------------
    int game_time; // Main game timer to decrement
    int redLightTimer; // Red light timer to decrement
    int greenLightTimer; // Green light timer to decrement
    Timer labelTimer; // Main game timer object
    Timer redTimer; // Red light timer object
    Timer greenTimer; // Green light timer object.
    double x; // Player x value for movement checking
    double y; // Player y value for movement checking

    // Boolean properties for key presses
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    //PLAYER MOVEMENT
    // Player movement speed
    private int movementVariable = 4;

    // Moves player
    private TranslateTransition transition;

    // Animation timer with handlers updating constantly
    // Contains various method calls to collision checkers and key presses
    AnimationTimer timer = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            //COLLISIONS
            if (checkCollision(player, yellowCar)){
                player.setLayoutX(0);
                player.setLayoutY(260);

            }
            if (checkCollision(player, blackCar)){
                player.setLayoutX(0);
                player.setLayoutY(260);

            }
            if (checkCollision(player, blueCar)){
                player.setLayoutX(0);
                player.setLayoutY(260);

            }
            if (checkCollision(player, greenCar)){
                player.setLayoutX(0);
                player.setLayoutY(260);
            }
            if (checkCollision(player, finishLine)){
                gameWin();
            }


            // CONTROL INPUTS
            if(wPressed.get() && player.getLayoutY() > 0) {
                player.setLayoutY(player.getLayoutY() - movementVariable);
            }

            if(sPressed.get() && player.getLayoutY() < scene.getPrefHeight() - player.getFitHeight()){
                player.setLayoutY(player.getLayoutY() + movementVariable);
            }

            if(aPressed.get() && player.getLayoutX() > 0){
                player.setLayoutX(player.getLayoutX() - movementVariable);
            }

            if(dPressed.get() && player.getLayoutX() < scene.getPrefWidth() - player.getFitWidth()){
                player.setLayoutX(player.getLayoutX() + movementVariable);
            }
        } // END EVENT HANDLING
    };

    // Initialize method that starts the level with requirements
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerNumLabel.setText(String.valueOf(game_time));
        setTimer();
        movementSetup();
        countdownGl();
        moveImage(yellowCar, 3, false, 1300);
        moveImage(blackCar, 2.5, true, 1300);
        moveImage(blueCar, 1.5, false, 1300);
        moveImage(greenCar, 2.5, true, 1300);
        keyPressed.addListener(((observableValue, aBoolean, t1)-> {
            timer.start();
        }));
    }

    // TIMERS
    // Main game timer
    public void setTimer() {
        Redlightgreenlight game = new Redlightgreenlight();
        labelTimer = new Timer();
        game_time = 45;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(game_time > 0) // Decrements the game timer
                {
                    game_time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(game_time)));
                }
                else {
                    try { // Only occurs when timer hits 0 and then player loses game
                        System.out.println("transition");
                        game.changeScene("loseScreen.fxml");
                        labelTimer.cancel();
                        greenTimer.cancel();
                        redTimer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0,1000); // Game timer intervals
    }

    // Green light phase timer
    public void countdownGl() {
        greenTimer = new Timer();
        int random = (int) (Math.random() * 6) + 3;
        greenLightTimer = random;
        greenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (greenLightTimer > 1) { // Decrements time
                    greenLightTimer -= 1;
                } else if (greenLightTimer == 1) { // When it reaches 1, changes to Redlight
                    greenredLabel.setTextFill(Color.RED);
                    Platform.runLater(() -> greenredLabel.setText("Red light!"));
                    greenLightTimer -= 1;
                } else { // Checks for player movement at 0 and changes peeker
                    peeker.setImage(peeking);
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    greenTimer.cancel();
                    countdownRl();
                }
            }
        }, 0, 500); // 500ms intervals allowing for .5sec grace period for player
    }
        // Red Light timer
        public void countdownRl() {
            redTimer = new Timer();
            int random = (int) (Math.random() * 6) + 3;
            redLightTimer = random;
            redTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (redLightTimer != 0) { // Decrements timer and checks player movement
                        checkMovement(player, x, y);
                    }
                    if (redLightTimer > 1) { // One last decrement before change to Green Light
                        redLightTimer -= 1;
                    } else if (redLightTimer == 1) { // Changes to Green light and changes peeker
                        greenredLabel.setTextFill(Color.GREEN);
                        Platform.runLater(() -> greenredLabel.setText("Green light!"));
                        peeker.setImage(notpeeking);
                        redLightTimer -= 1;

                    } else {
                        redTimer.cancel();
                        countdownGl();
                    }

                }
            }, 0, 500);
        }
        // END TIMERS

    // ALL USED IN HANDLER
    // Movement setup needed to validate key presses.
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
    // Player movement checker, sets player back to start if they
    public void checkMovement(ImageView image, double x, double y){
        if (image.getLayoutX() != x || image.getLayoutY() != y){
            player.setLayoutX(0);
            player.setLayoutY(260);
            redTimer.cancel();
            greenredLabel.setTextFill(Color.GREEN);
            Platform.runLater(() -> greenredLabel.setText("Green light!"));
            peeker.setImage(notpeeking);
            countdownGl();
        }
    }

    // Moves the moving obstacles dependent upon orientation speed and distance
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

    // Game win method that takes player to next level
    public void gameWin(){
        Redlightgreenlight game = new Redlightgreenlight();
        try {
            player.setLayoutX(2000);
            labelTimer.cancel();
            greenTimer.cancel();
            redTimer.cancel();
            game.changeScene("StoryScene4.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
