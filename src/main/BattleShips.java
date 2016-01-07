package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        layout.setPadding(new Insets(0,0,150,0));

        Button newGameButton = new Button("Uus mäng");
        newGameButton.setPrefSize(200, 75);
        newGameButton.setStyle("-fx-font-size: 200%; -fx-background-color: rgba(255, 255, 255, 0.5);");
        newGameButton.setOnAction(event -> newGameAction());

        layout.getChildren().add(newGameButton);

        // alguskuva, millel nupp "uus mäng"
        Scene scene = new Scene(layout, 500, 650);
        scene.getStylesheets().addAll(this.getClass().getResource("mainClass.css").toExternalForm());
        //määran programmi akna scene-iks vaikimisi scene-i
        window.setScene(scene);
        //kuvan akent
        window.show();
    }

    public static void newGameAction(){
        //loon uue objekti "Game"
        // loob uue mängu, parameetriks laevade arv
        Game newGame = new Game(10);
        //Game-il on oma scene. määran selle programmi akna jaoks
        Scene gameScene = newGame.getScene();
        window.setScene(gameScene);
    }





}
