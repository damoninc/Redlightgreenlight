package com.example.redlightgreenlight;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameMenu extends Application {

    private static GameMenu.gameMenu gameMenu;
    private static Stage game_stage;
    @Override
    public void start(Stage stage) throws Exception {
        game_stage = stage;
        Pane root = new Pane();
        root.setPrefSize(1280,720);  // set dimensions
        // Load in image
        InputStream is = Files.newInputStream(Paths.get("C:\\Users\\Damon\\Documents\\Java Projects\\CSC 331\\Redlightgreenlight\\src\\main\\resources\\com\\example\\redlightgreenlight\\impossiblepentagonlooping.gif"));
        Image img = new Image(is);  // set image to variable
        is.close();  // close image

        ImageView imgView = new ImageView(img);  //  new ImageView for display
        imgView.setFitWidth(800);  // fits the image to this width
        imgView.setFitHeight(600); // fits the image to this height

        gameMenu = new gameMenu();
        gameMenu.setVisible(false);  // game menu not visible from the start

        root.getChildren().addAll(imgView,gameMenu);  // attach background image and menu on top

        Scene scene = new Scene(root); // new scene
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.ENTER){ // if user presses enter key
                if(!gameMenu.isVisible()){  // not visible
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);  //  change screen from not visible
                    ft.setToValue(1);  // to fully visible

                    gameMenu.setVisible(true);  // make it visible
                    ft.play();  // plays animation of fading screen
                }
                else{  // game menu is visible and needs to be hid
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1); // fully visible
                    ft.setToValue(0);  // change to not visible
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));  // menu is no longer visible
                    ft.play();  // plays animation
                }
            }
        });


        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }  // end bracket for start

    protected void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        game_stage.getScene().setRoot(pane);
    }

    private class gameMenu extends Parent {
        public gameMenu(){
            VBox menu0 = new VBox(10);  // main menu
            VBox menu1 = new VBox(10);  // sub menu
            menu0.setTranslateX(100);
            menu0.setTranslateY(200);
            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);


            MenuButton buttonPlay = new MenuButton("PLAY");  // Play button
            buttonPlay.setOnMouseClicked(event -> {
                try {
                    gameMenu.changeScene("Level1.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            MenuButton buttonInstructions = new MenuButton("HOW TO PLAY"); // Instructions button
            buttonInstructions.setOnMouseClicked(event ->{
                getChildren().add(menu1);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu0); // apply animation to main menu
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu1); // apply animation to sub menu
                tt1.setToX(menu0.getTranslateX());

                tt.play(); // play animation for main menu
                tt1.play();  // play animation for sub menu

                tt.setOnFinished(evt ->{  // when main menu animation finishes
                    getChildren().remove(menu0);  // no longer display main menu
                });

            });

            MenuButton buttonExit = new MenuButton("EXIT");  // Exit button
            buttonExit.setOnMouseClicked(event ->{
                System.exit(0);  // exits out game menu
            });

            MenuButton buttonBack = new MenuButton("BACK");  // Back Button
            buttonBack.setOnMouseClicked(event ->{
                getChildren().add(menu0);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu1);
                tt.setToX(menu1.getTranslateX() + offset);  // moves sub menu to the right

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu0);
                tt1.setToX(menu1.getTranslateX());  // moves main menu back to its orig pos

                tt.play();  // plays animation
                tt1.play();  // plays animation

                tt.setOnFinished(evt ->{  // removes sub menu from screen
                    getChildren().remove(menu1);
                });



            });


            menu0.getChildren().addAll(buttonPlay,buttonInstructions,buttonExit);  // main menu
            menu1.getChildren().addAll(buttonBack);  // sub menu

            Rectangle background = new Rectangle(800,600);
            background.setFill(Color.TRANSPARENT);
            background.setOpacity(0.4);

            getChildren().addAll(background,menu0);



        }
        public void changeScene(String fxml) throws IOException {
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            gameMenu.getScene().setRoot(pane);
        }

    }
    private static class TitleName extends StackPane{
        private Text text;

        public TitleName(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(100));
            text.setFill(Color.DEEPPINK);
            Rectangle background = new Rectangle(500,70);
            background.setFill(Color.TRANSPARENT);
            setAlignment(Pos.CENTER);
            getChildren().addAll(text);
            setOnMouseEntered(event -> {  // when mouse hovers over any option box
                background.setTranslateX(10);  // the box will slightly move to the right 10 units
                text.setTranslateX(10);  // and the text
                background.setFill(Color.BLACK);  // the box will turn white
                text.setFill(Color.LIMEGREEN);  // the text will turn black
            });  //setOnMouseEntered bracket

            // reverts when mouse leaves box
            setOnMouseExited(event -> {
                background.setTranslateX(0);
                text.setTranslateX(0);
                background.setFill(Color.BLACK);
                text.setFill(Color.DEEPPINK);
            });  //setOnMouseEntered bracket

        }

    }
    private static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(40));
            text.setFill(Color.DEEPPINK);  // color on font

            Rectangle background = new Rectangle(100,30);  // dimensions
            background.setOpacity(0);  // transparency
            background.setFill(Color.TRANSPARENT);  // color of option boxes
            background.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);  // for text in the option boxes
            setRotate(-0.5);
            getChildren().addAll(background,text);  // add background first, the text on top

            setOnMouseEntered(event -> {  // when mouse hovers over any option box
                background.setTranslateX(10);  // the box will slightly move to the right 10 units
                text.setTranslateX(10);  // and the text
                background.setFill(Color.BLACK);  // the box will turn white
                text.setFill(Color.LIMEGREEN);  // the text will turn black
            });  //setOnMouseEntered bracket

            // reverts when mouse leaves box
            setOnMouseExited(event -> {
                background.setTranslateX(0);
                text.setTranslateX(0);
                background.setFill(Color.BLACK);
                text.setFill(Color.DEEPPINK);
            });  //setOnMouseEntered bracket

            DropShadow drop = new DropShadow(200, Color.LIMEGREEN); // gives the box a white glow
            drop.setInput(new Glow(200));
            setOnMousePressed(event -> setEffect(drop));  // applies effect
            setOnMouseReleased(event -> setEffect(null)); // removes effect



            } // Public MenuButton bracket
        }  // class Menu Button extends StackPane bracket




    public static void main(String[]args) {
        launch(args);
    } // end bracket for main
}  // class GameMenu extends Application bracket

