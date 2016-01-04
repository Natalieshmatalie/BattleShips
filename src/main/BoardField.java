package main;


import javafx.scene.control.Button;


public class BoardField {

    //nupul asub laev
    private boolean hasShip;
    private Button button;
    private GameBoard parentBoard;

    public BoardField(GameBoard parentBoard, boolean clickAction){
        button = new Button();
        button.setStyle("-fx-font-weight: bold");
        button.setMinSize(60, 60);
        if (clickAction){
            button.setOnAction(event -> {
                clickButton(true);
            });
        }

        this.parentBoard = parentBoard;
    }

    public void setHasShip(boolean value){
        hasShip = value;
    }

    public boolean isHasShip() {
        return hasShip;
    }

    public Button getButton() {
        return button;
    }

    public void clickButton(boolean humanPlayer){
        // lülitab nupu välja
        button.setDisable(true);
        if (hasShip){
            button.setText("X");
            button.setStyle(
                    "-fx-background-color: dimgrey;" +
                            "-fx-text-fill: red; " +
                            "-fx-font-size: 150%"
            );
            if (humanPlayer){
                parentBoard.receiveButtonClick(true);
            }
        }else{
            button.setStyle("-fx-background-color: white");
            if (humanPlayer){
                parentBoard.receiveButtonClick(false);
            }
        }
    }




}
