package com.example.citywarsphase2_1;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SecondPlayerLogin extends CommonFunctions{
    static Pane pane = new Pane();
    static Scene scene = new Scene(pane, Main.ScreenWidth, Main.ScreenHeight);
    static boolean CharacterInit = false;
    static TextField UsernameField = new TextField();
    static TextField PasswordField = new TextField();
    public static void Init(Stage stage){
        Text UsernameText = new Text(0,30,"Username: ");
        UsernameText.setFont(new Font(20));

        UsernameField.setFont(UsernameText.getFont());
        UsernameField.setLayoutX(100);
        UsernameField.setLayoutY(0);
        UsernameField.setPrefWidth(250);

        Text UsernameWarning = new Text(360,UsernameText.getY(),"");
        UsernameWarning.setFont(UsernameText.getFont());
        UsernameWarning.setFill(Color.RED);
        UsernameWarning.setVisible(false);

        Text PasswordText = new Text(0, 80, "Password:");
        PasswordText.setFont(UsernameText.getFont());

        PasswordField.setFont(UsernameText.getFont());
        PasswordField.setLayoutX(UsernameField.getLayoutX());
        PasswordField.setLayoutY(50);
        UsernameField.setPrefWidth(UsernameField.getPrefWidth());

        Text PasswordWarning = new Text(UsernameWarning.getX(), PasswordText.getY(), "");
        PasswordWarning.setFont(UsernameText.getFont());
        PasswordWarning.setFill(Color.RED);
        PasswordWarning.setVisible(false);

        Button BackButton = new Button("Back");
        BackButton.setFont(UsernameText.getFont());
        BackButton.setLayoutX(0);
        BackButton.setLayoutY(110);
        BackButton.setOnAction(event -> {
            UsernameField.setText("");
            PasswordField.setText("");
            Main.SecondPlayer = null;
            stage.setScene(ChooseMode.scene);
            stage.setTitle("Choose mode");
        });

        Button SubmitButton = new Button("Submit");
        SubmitButton.setFont(UsernameText.getFont());
        SubmitButton.setLayoutX(80);
        SubmitButton.setLayoutY(BackButton.getLayoutY());
        SubmitButton.setOnAction(event -> {
            if(UsernameField.getText().isEmpty()){
                UsernameWarning.setText("Empty field!");
                UsernameWarning.setVisible(true);
            }
            else if(GetUser(UsernameField.getText()) == null){
                UsernameWarning.setText("Username doesn't exist!");
                UsernameWarning.setVisible(true);
            }
            else {
                UsernameWarning.setVisible(false);
                User u = GetUser(UsernameField.getText());
                if(PasswordField.getText().isEmpty()){
                    PasswordWarning.setText("Empty field!");
                    PasswordWarning.setVisible(true);
                }
                else if(!PasswordField.getText().equals(u.Password)){
                    PasswordWarning.setText("Password is incorrect!");
                    PasswordWarning.setVisible(true);
                }
                else{
                    if(u.cards.isEmpty()) {
                        for (int i = 0; i < 20; i++) {
                            u.cards.add(Main.Cards.get(random.nextInt(Main.Cards.size())));
                        }
                        Main.Users.get(GetUserIndex(UsernameField.getText())).cards = u.cards;
                        showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + u.Username
                                + "\n" + u.Username + " is gifted with 20 cards!" );
                    }
                    Main.SecondPlayer = u;
                    Update_Cards_Of_Players();
                    if(!CharacterInit){
                        ChooseCharacter.Init(stage);
                        CharacterInit = true;
                    }
                    stage.setTitle("Choose character");
                    stage.setScene(ChooseCharacter.scene);
                }
            }

        });

        pane.getChildren().addAll(UsernameText, UsernameField, UsernameWarning, PasswordText, PasswordField,
                PasswordWarning, BackButton, SubmitButton);
    }
}
