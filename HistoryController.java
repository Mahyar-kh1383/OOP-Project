package com.example.citywarsphase2_1;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.citywarsphase2_1.SignUp.EmptyingFields;

public class HistoryController implements Initializable {
    @FXML
    TableView<HistoryOfGame> HistoryTable;
    @FXML
    TableColumn<HistoryOfGame , String> DateColumn;
    @FXML
    TableColumn<HistoryOfGame , String> TimeColumn;
    @FXML
    TableColumn<HistoryOfGame , String> ResultColumn;
    @FXML
    TableColumn<HistoryOfGame , String> OpponentColumn;
    @FXML
    TableColumn<HistoryOfGame , String> RewardOrPenaltyColumn;
    @FXML
    Button Back;
    @FXML
    Button SBDA;
    @FXML
    Button SBDD;
    @FXML
    Button SBWOLA;
    @FXML
    Button SBWOLD;
    @FXML
    Button SBCNA;
    @FXML
    Button SBCND;
    @FXML
    Button NP;
    @FXML
    Button PP;
    @FXML
    Label label;
    static int pageNumber = 1 , totalPageNumber = (int)Math.ceil(((double)Main.LoggedInUser.historyOfGames.size())/10);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageNumber = 1 ; totalPageNumber = (int)Math.ceil(((double)Main.LoggedInUser.historyOfGames.size())/10);
        Main.LoggedInUser.historyOfGames.sort((o1, o2) -> {
            // Assuming date and time are in the format "yyyy/MM/dd HH:mm:ss"
            String dateTime1 = o1.date + " " + o1.Time;
            String dateTime2 = o2.date + " " + o2.Time;
            return dateTime1.compareTo(dateTime2);
        });
        SetTable(pageNumber , totalPageNumber);
        Back.setOnAction(this::handleBack);
        SBDA.setOnAction(this::handleSBDA);
        SBDD.setOnAction(this::handleSBDD);
        SBWOLA.setOnAction(this::handleSBWOLA);
        SBWOLD.setOnAction(this::handleSBWOLD);
        SBCNA.setOnAction(this::handleSBCNA);
        SBCND.setOnAction(this::handleSBCND);
        NP.setOnAction(this::NextPage);
        PP.setOnAction(this::PreviousPage);




    }



    public void handleBack(ActionEvent event) {
        EmptyingFields();
        SceneUtil.getPrimaryStage().setTitle("Main Menu");
        SceneUtil.getPrimaryStage().setScene(MainMenu.scene);
    }
    public void handleSBDA(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort((o1, o2) -> {
            String dateTime1 = o1.date + " " + o1.Time;
            String dateTime2 = o2.date + " " + o2.Time;
            return dateTime1.compareTo(dateTime2);
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void handleSBDD(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort((o1, o2) -> {
            String dateTime1 = o1.date + " " + o1.Time;
            String dateTime2 = o2.date + " " + o2.Time;
            return dateTime2.compareTo(dateTime1);
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void handleSBWOLA(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort(new Comparator<>() {
            @Override
            public int compare(HistoryOfGame o1, HistoryOfGame o2) {
                return o1.Result.compareTo(o2.Result);
            }
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void handleSBWOLD(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort(new Comparator<>() {
            @Override
            public int compare(HistoryOfGame o1, HistoryOfGame o2) {
                return o2.Result.compareTo(o1.Result);
            }
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void handleSBCNA(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort(new Comparator<>() {
            @Override
            public int compare(HistoryOfGame o1, HistoryOfGame o2) {
                return o1.Opponent.Username.compareTo(o2.Opponent.Username);
            }
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void handleSBCND(ActionEvent event) {
        Main.LoggedInUser.historyOfGames.sort(new Comparator<>() {
            @Override
            public int compare(HistoryOfGame o1, HistoryOfGame o2) {
                return o2.Opponent.Username.compareTo(o1.Opponent.Username);
            }
        });
        SetTable(pageNumber , totalPageNumber);
    }
    public void NextPage(ActionEvent event) {
        if(pageNumber+1 > totalPageNumber) {
            CommonFunctions.showAlert(Alert.AlertType.ERROR , "Error" , "There is no next page!" );
            return;
        }
        pageNumber++;
        SetTable(pageNumber , totalPageNumber);
    }
    public void PreviousPage(ActionEvent event) {
        if(pageNumber-1 <= 0) {
            CommonFunctions.showAlert(Alert.AlertType.ERROR , "Error" , "There is no previous page!" );
            return;
        }
        pageNumber--;
        SetTable(pageNumber , totalPageNumber);
    }

    public void SetTable(int pageNumber , int totalPageNumber) {
        ObservableList<HistoryOfGame> information = FXCollections.observableArrayList();
        for(int i = 10*(pageNumber-1); i < Main.LoggedInUser.historyOfGames.size(); i++) {
            information.add(Main.LoggedInUser.historyOfGames.get(i));
            if((i+1)%10 == 0) {
                break;
            }
        }
        label.setText("Page " + pageNumber);
        DateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().date));
        TimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Time));
        ResultColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Result));
        OpponentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Opponent.Username));
        RewardOrPenaltyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().RewardOrPenalty));
        HistoryTable.setItems(information);
    }
}
