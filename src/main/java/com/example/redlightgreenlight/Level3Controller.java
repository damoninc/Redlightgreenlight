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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level3Controller implements Initializable {

    @FXML
    private ImageView player; // player

    @FXML
    private ImageView felaini; // obstacle

    @FXML
    private ImageView slidingGuy; // obstacle

    @FXML
    private ImageView messi; // obstacle

    @FXML
    private ImageView neymar; // obstacle

    @FXML
    private ImageView courtois; // obstacle

    @FXML
    private ImageView endLine; // endLine Object used for transitioning to the winning scene

    @FXML
    private Label timerNumLabel; // timer label

    @FXML
    private Label greenredLabel; //greenred label

    @FXML
    private Pane scene; // pane

    @FXML
    private ImageView peeker; // the peeker image

    // seeker images
    Image peeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("RefLooking.png")));
    Image notpeeking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("RefNotLooking.png")));

    @FXML
    void start(){ // Needed for movement
    }

    // GAME FUNCTIONALITY ALL BELOW
    //----------------------------------

    int game_time;
    int redLightTimer;
    int greenLightTimer;
    Timer labelTimer;
    Timer redTimer;
    Timer greenTimer;
    double x;
    double y;

    // PLAYER MOVEMENTS
    // Boolean properties for key presses
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 3; // Player movement speed (IMPACTED BY MONITOR'S REFRESH RATE)

    // Used for moving player
    private TranslateTransition transition;


    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {  // it checks if any of the keys is pressed to move the character
                                            // and checks for collision between objects

            if ((checkCollision(player, messi)) || (checkCollision(player, neymar)) || (checkCollision(player, courtois)) || (checkCollision(player, felaini)) || (checkCollision(player, slidingGuy)) ){
                // if player collides with any of the objects, send it back to the start.
                player.setLayoutX(60);
                player.setLayoutY(320);
            }

            if (checkCollision(player, endLine)){ // if player collides with the endline, gameWin method gets called.
                gameWin();
            }

            if(wPressed.get() && player.getLayoutY() > 0) { // if W gets pressed, move player up
                player.setLayoutY(player.getLayoutY() - movementVariable);
            }

            if(sPressed.get() && player.getLayoutY() < scene.getPrefHeight() - player.getFitHeight()){ // if S gets pressed, move player down
                    player.setLayoutY(player.getLayoutY() + movementVariable);
            }

            if(aPressed.get() && player.getLayoutX() > 0){// if A gets pressed, move player left
                player.setLayoutX(player.getLayoutX() - movementVariable);
            }

            if(dPressed.get() && player.getLayoutX() < scene.getPrefWidth() - player.getFitWidth()){ // if D gets pressed, move player right.
                player.setLayoutX(player.getLayoutX() + movementVariable);
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // initializes the game components:
                                                                     // timer label, timer, movement of the player, object's movement
        timerNumLabel.setText(String.valueOf(game_time));
        setTimer();
        movementSetup();
        countdownGl();
        moveImageY(neymar, 3, scene.getPrefHeight());
        moveImageY(messi, 2, scene.getPrefHeight());
        moveImageY(courtois,0.8, scene.getPrefHeight()/1.25);
        moveImageX(felaini, 2, scene.getPrefWidth()/1.85);
        moveImageX(slidingGuy, 2, -scene.getPrefWidth()/2.30);
        keyPressed.addListener(((observableValue, aBoolean, t1)-> {
                timer.start();
        }));
    }

    public void setTimer() { // this sets the timer that the player has to beat each level
        Redlightgreenlight game = new Redlightgreenlight();
        labelTimer = new Timer();
        game_time = 40;
        labelTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(game_time > 0) // if game time is bigger than zero, subtract one and update the timer label
                {
                    game_time -= 1;
                    Platform.runLater(() -> timerNumLabel.setText(String.valueOf(game_time)));
                }
                else{ //else you lose and the scene changes to the loseScreen
                    try {
                        game.changeScene("loseScreen.fxml");
                        labelTimer.cancel();
                        greenTimer.cancel();
                        redTimer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0,1000);
    }

    public void countdownGl() { // count down timer for the green light
        greenTimer = new Timer();
        int random = (int) (Math.random() * 6) + 3;
        greenLightTimer = random;
        greenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (greenLightTimer > 1) { // if timer is bigger than 1, subtract one from the greenLightTimer integer
                    greenLightTimer -= 1;
                } else if (greenLightTimer == 1) { // else it changes to redlight, the label changes to red and the label gets updated to Red light
                    greenredLabel.setTextFill(Color.RED);
                    Platform.runLater(() -> greenredLabel.setText("Red light!"));
                    greenLightTimer -= 1;
                } else { // after it changes to red light, the seeker turns toward you, and the position of the player gets recorded.
                    peeker.setImage(peeking);
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    greenTimer.cancel();
                    countdownRl();
                }
            }
        }, 0, 500);
    }

    public void countdownRl() { // count down timer for the red light
        redTimer = new Timer();
        int random = (int) (Math.random() * 6) + 3;
        redLightTimer = random;
        redTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (redLightTimer != 0) { // if redLightTimer is not 0, checkMovement method gets called
                    checkMovement(player, x, y);
                }
                if (redLightTimer > 1) { // if redLightTimer is bigger than 1, 1 gets subtracted from the redLightTimer
                    redLightTimer -= 1;
                } else if (redLightTimer == 1) { // if redLightTimer is equal to 1, it changes to green light, the label changes its color to green
                                                 // and the label gets updated to green light. The seeker image gets set to notpeeking.
                    greenredLabel.setTextFill(Color.GREEN);
                    Platform.runLater(() -> greenredLabel.setText("Green light!"));
                    peeker.setImage(notpeeking);
                    redLightTimer -= 1;

                } else {// redTimer gets cancel and countdownGL gets called
                    redTimer.cancel();
                    countdownGl();
                }

            }
        }, 0, 500);
    }


    public void movementSetup(){ // this method sets the key booleans to true when they are pressed and false when they are released
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) { // if W gets pressed, set boolean to true
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A) { // if A gets pressed, set boolean to true
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S) { // if S gets pressed, set boolean to true
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) { // if D gets pressed, set boolean to true
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) { // if W gets released, set boolean to false
                wPressed.set(false);
            }

            if(e.getCode() == KeyCode.A) {// if A gets released, set boolean to false
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S) { // if S gets released, set boolean to false
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) { //if D gets released, set boolean to false
                dPressed.set(false);
            }
        });
    }

    public boolean checkCollision(ImageView image1, ImageView image2){ // this method checks collision between ImageViews
        if(image1.getBoundsInParent().intersects(image2.getBoundsInParent())){ // if the bounds of image1 intersects the bounds of image 2, then return true.
                                                                               // Otherwise return false.
            return true;
        }
        return false;
    }
    public void moveImageY(ImageView image1, double velocity, double distance){ // this method moves the image on the y axis as a loop.
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(velocity));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToY(distance - image1.getFitHeight());
        transition.setAutoReverse(true);
        transition.play();
    }
    public void moveImageX(ImageView image1, double velocity, double distance){ // this method moves the image on the x axis as a loop.
        transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(velocity));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setToX(distance - image1.getFitHeight());
        transition.setAutoReverse(true);
        transition.play();
    }

    public void checkMovement(ImageView image, double x, double y){ // this method checks if the player moves from its current position.
        if (image.getLayoutX() != x || image.getLayoutY() != y){ // if player's location changes, move player back to the start.
                                                                // Also, the label gets resetted.
            player.setLayoutX(0);
            player.setLayoutY(431);
            redTimer.cancel();
            greenredLabel.setTextFill(Color.GREEN);
            Platform.runLater(() -> greenredLabel.setText("Green light!"));
            peeker.setImage(notpeeking);
            countdownGl();
        }
    }

    public void gameWin(){ // method that changes the scene to the winScreen scene
        Redlightgreenlight game = new Redlightgreenlight();
        try {
            player.setLayoutX(2000);
            labelTimer.cancel();
            greenTimer.cancel();
            redTimer.cancel();
            game.changeScene("winScreen.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}