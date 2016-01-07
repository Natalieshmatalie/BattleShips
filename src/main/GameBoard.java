package main;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;


public class GameBoard {

    private GridPane boardLayout;
    private List<BoardField> buttonList;
    private int shipCount;
    private int hitCount;
    private Game curGame;

    //konstruktor, läheb käima koheselt, kui objekt luuakse
    public GameBoard(int shipCount, Game curGame, boolean enemyBoard){
        this.shipCount = shipCount;
        //loon uue layout-i nuppudega
        boardLayout = new GridPane();
        //loon uue ArrayList-i, milles on kõik mänguvälja nupud
        buttonList = new ArrayList<>();
        //refereering praegusele mänguObjektile
        this.curGame = curGame;

        //loo mänguvälja nupud
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                //uus mänguvälja nupp
                BoardField field = new BoardField(this, enemyBoard);
                //määran nupu asukohta GridPane-il
                GridPane.setConstraints(field.getButton(), i, j);
                //lisan nupu gridpane-ile
                boardLayout.getChildren().add(field.getButton());
                //lisab nupu klikkimata nuppude nimekirja
                buttonList.add(field);
            }
        }
        fillBoardRandomly(shipCount);
    }

    public GridPane getBoardLayout() {
        return boardLayout;
    }

    public void receiveButtonClick(boolean hit){
        //loendan laevade pihtasaamisi
        if (hit){
            hitCount++;
            curGame.increasePlayerScore();
        }
        //kontrollin, kas kõik laevad on hävitatud ja peaks mängu lõpetama
        if (hitCount == shipCount){
            curGame.endGame();
        }

        //arvuti kord mängida, kui mängija ei tabanud laeva
        if (!hit){
            curGame.enemyPlay();
        }

    }

    public void removeButton(BoardField field){
        buttonList.remove(field);
    }

    public List<BoardField> getButtonList() {
        return buttonList;
    }

    public void fillBoardRandomly(int shipCount){
        for (int i = 0; i < shipCount; i++){
            boolean shipAdded = false;
            // teen, kuni leian nupu, millel pole juba laeva
            while(!shipAdded){
                //juhuslik nupp nuppude nimekirjast
                int random = (int)(Math.random() * buttonList.size() - 1);
                //kontrollin
                if (!buttonList.get(random).isHasShip()){
                    buttonList.get(random).setHasShip(true);
                    shipAdded = true;
                }
            }
        }
        //laevad lisatud
    }

    public void disableButtons(){
        for (int i = 0; i < buttonList.size(); i++){
            buttonList.get(i).getButton().setDisable(true);
        }
    }

}
