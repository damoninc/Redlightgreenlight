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

public class Level1Controller implements Initializable {

    
    @FXML
    private ImageView player; // Player

    @FXML
    private ImageView rock1; // Obstacle

    @FXML
    private ImageView rock2; // Obstacle

    @FXML
    private ImageView snowman1; // Obstacle

    @FXML
    private ImageView snowman2; // Obstacle

    @FXML
    private ImageView snowman3; // Obstacle

    @FXML
    private Label timerNumLabel; // Obstacle

    @FXML
    private Pane scene;

    @FXML
    private Rectangle finishLine; // Touch finish line to continue

    @FXML
    private Label greenredLabel; // Changes colors

    @FXML
    private ImageView peeker; // Changes images

    // Peeker changes orientation
    Image peeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("SansLooking.png")));
    Image notpeeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("SansNotLooking.png")));

    @FXML
    void start(){ // Needed for movement
    }

    // GAME FUNCTIONALITY ALL BELOW
    //----------------------------------

    // Timers
    int game_time; // Game timer to decrement
    int redLightTimer; // Redlight timer to decrement
    int greenLightTimer; // Greenlight timer to decrement
    Timer labelTimer; // Main game timer object
    Timer redTimer; // Redlight timer object
    Timer greenTimer; // Greenlight timer object
    double x; // X value for checking player movement
    double y; // Y value for checking player movement


    // PLAYER MOVEMENTS
    // Boolean properties for key presses
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    // Player movement speed (IMPACTED BY MONITOR'S REFRESH RATE)
    private int movementVariable = 2;

    // Used for moving player
    private TranslateTransition transition;


    // Animation timer including a handler, constantly updating.
    // Includes various method calls for collision checkers and key presses.
    AnimationTimer timer = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            // Collision checkers
            if (checkCollision(player, finishLine)){
                gameWin();
            }

            if (checkCollision(player, rock1)){
                movementVariable = 1;
            }

            else if (checkCollision(player, rock2)){
                movementVariable = 1;
            }

            else if (checkCollision(player, snowman1)){
                movementVariable = 1;
            }

            else if (checkCollision(player, snowman2)){
                movementVariable = 1;
            }

            else if (checkCollision(player, snowman3)){
                movementVariable = 1;
            }
            else movementVariable = 2;


            // Movement inputs
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

    // Initialize method that sets up the game at start of level.
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

    // TIMERS
    // Main game timer
    public void setTimer() {
        Redlightgreenlight game = new Redlightgreenlight();
        labelTimer = new Timer();
        game_time = 30;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(game_time > 0) // Decrements game timer
                {
                    game_time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(game_time)));
                }
                else {
                    try { // Only occurs when timer reaches 0 i.e gameover.
                        System.out.println("transition");
                        game.changeScene("loseScreen.fxml");
                        labelTimer.cancel();
                        redTimer.cancel();
                        greenTimer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0,1000); // Timer intervals
    }

    // Timer during Greenlight phase
    public void countdownGl(){
        greenTimer = new Timer();
        int random = (int)(Math.random() * 6) + 3;
        greenLightTimer = random;
        greenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (greenLightTimer > 1) { // Decrements greenlight counter
                    greenLightTimer -= 1;
                } else if (greenLightTimer == 1) { // Decrements greenlight one last time
                    greenredLabel.setTextFill(Color.RED);
                    Platform.runLater(() -> greenredLabel.setText("Red light!")); // Changes text to Red Light
                    greenLightTimer -= 1;
                }else{ // Player movement checker and changes peeker
                    peeker.setImage(peeking);
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    greenTimer.cancel();
                    countdownRl(); // Transition to Red Light
                }
            }
        }, 0, 500); // 500ms grace period to allow player to have reaction time.

    }

    // Timer for Red Light phase
    public void countdownRl(){
        redTimer = new Timer();
        int random = (int)(Math.random() * 6) + 3;
        redLightTimer = random;
        redTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (redLightTimer != 0){ // During phase, checks for player movement.
                    checkMovement(player,x,y);
                }
                if (redLightTimer > 1) {
                    redLightTimer -= 1;
                } else if (redLightTimer == 1) { // Changes Color and text to Red Light and changes peeker
                    greenredLabel.setTextFill(Color.GREEN);
                    Platform.runLater(() -> greenredLabel.setText("Green light!"));
                    peeker.setImage(notpeeking);
                    redLightTimer -= 1;

                }else{
                    redTimer.cancel();
                    countdownGl(); // Transition to Green Light
                }

            }
        }, 0, 500); // 500ms grace period for player movement.

    }
    // END TIMERS

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
    } // End Collision Checkers

    // Movement Checker, if player moves during Redlight, takes back to start and starts greenlight again.
    // Main game timer remains the same.
    public void checkMovement(ImageView image, double x, double y){
        if (image.getLayoutX() != x || image.getLayoutY() != y){
            player.setLayoutX(0);
            player.setLayoutY(431);
            redTimer.cancel();
            greenredLabel.setTextFill(Color.GREEN);
            Platform.runLater(() -> greenredLabel.setText("Green light!"));
            peeker.setImage(notpeeking);
            countdownGl();
        }
    }


    // Game Win method, changes scenes when game wins.
    public void gameWin(){
        Redlightgreenlight game = new Redlightgreenlight();
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