package main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Game {

    private GameBoard playerBoard;
    private GameBoard enemyBoard;
    private Scene scene;
    private Label playerScoreLabel;
    private int playerScore = 0;
    private Label enemyScoreLabel;
    private int enemyScore = 0;
    private Label message;
    private int shipCount;

    public Game(int shipCount){
        this.shipCount = shipCount;
        VBox gameLayout = new VBox();
        gameLayout.setPrefSize(300, 650);

        Label playerLabel = new Label("Sinu mängulaud");
        // this on refereering sellele objektile, et nupp teaks, millise mängulaua poole pöörduda,
        //loob mängija mänguvälja, falsega keelan enda mänguvälja nuppude klikkimise
        playerBoard = new GameBoard(shipCount, this, false);
        GridPane playerGrid = playerBoard.getBoardLayout();

        Label enemyLabel = new Label ("Vastase mängulaud");
        // truega luban endal vastase mängulaua nuppe klikkida
        enemyBoard = new GameBoard(shipCount, this, true);
        GridPane enemyGrid = enemyBoard.getBoardLayout();

        //lisan elemendid
        gameLayout.getChildren().addAll(playerLabel, playerGrid, enemyLabel, enemyGrid);

        //parempoolne layout
        VBox rightLayout = new VBox();
        rightLayout.setPadding(new Insets(0, 0, 0, 10));

        Button button = new Button("Uus mäng");
        button.setOnAction(event -> {
            BattleShips.newGameAction();
        });
        playerScoreLabel = new Label("Sinu punkte: 0");
        enemyScoreLabel = new Label("Vastase punkte: 0");
        message = new Label("");
        //lisan layout-i
        rightLayout.getChildren().addAll(button, playerScoreLabel, enemyScoreLabel, message);

        //terve akna layout
        HBox fullLayout = new HBox();
        fullLayout.getChildren().addAll(gameLayout, rightLayout);
        scene = new Scene(fullLayout, 500, 650);

    }

    public Scene getScene() {
        return scene;
    }

    public void endGame(){
        if (playerScore > enemyScore){
            message.setText("Sinu võit!");
        } else if (playerScore < enemyScore){
            message.setText("Oi oi, kaotasid!");
        }else {
            message.setText("Viik!");
        }
        message.setStyle("-fx-font-size: 150%; -fx-text-fill: red");
        //lülita nupud välja
        playerBoard.disableButtons();
        enemyBoard.disableButtons();
    }

    public void enemyPlay(){
        //leiab mänguväljalt juhusliku arvu, mis on vahemikus 0 kuni kasutamata nuppude hulk
        int random = (int) (Math.random() * playerBoard.getButtonList().size());

        //enemy klikib minu mänguvälja nupul
        BoardField field = playerBoard.getButtonList().get(random);
        field.clickButton(false);

        //eemaldan nupu mänguvälja nuppude nimekirjast, kuna sellele uuest nagunii klikkida ei saa
        playerBoard.removeButton(field);

        //kui enemy sai laevale pihta, saab uuesti mängida
        if (field.isHasShip()){
            increaseEnemyScore();
            if (playerBoard.getButtonList().size() > 0 && enemyScore < shipCount){
                enemyPlay();
            }
        }
        if (enemyScore == shipCount){
            endGame();
        }
    }

    public void increasePlayerScore(){
        playerScore++;
        playerScoreLabel.setText("Sinu punkte: " + playerScore);
    }

    public void increaseEnemyScore(){
        enemyScore++;
        enemyScoreLabel.setText("Vastase punkte: " + enemyScore);
    }
}
