package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class BattleShips extends Application{

    private static Stage window;


    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        //akna suurus mittemuudetavaks
        this.window.setResizable(false);
        window.setTitle("Laevade pommitamine");

        //uus põhiline layout tervele aknale
        HBox layout = new HBox();
        layout.setId("pane");
        layout.setAlignment(Pos.BOTTOM_CENTER);

        Button newGameButton = new Button("Uus mäng");
        newGameButton.setPrefSize(200, 75);
        newGameButton.setStyle("-fx-font-size: 200%; -fx-background-color: rgba(255, 255, 255, 0.5);");
        newGameButton.setOnAction(event -> newGameAction());





        layout.getChildren().addAll(newGameButton);

        // alguskuva, millel nupp "uus mäng"
        Scene scene = new Scene(layout, 600, 850);
        scene.getStylesheets().addAll(this.getClass().getResource("mainClass.css").toExternalForm());
        //määran programmi akna scene-iks vaikimisi scene-i
        window.setScene(scene);
        //kuvan akent
        window.show();
    }

    public static void newGameAction(){
        //loon uue objekti "Game"
        //game loob uue mänguvälja koos loogikaga
        Game newGame = new Game(5, 5, 10);
        //Game-il on oma scene. määran selle programmi akna jaoks
        Scene gameScene = newGame.getScene();
        window.setScene(gameScene);
    }





}
