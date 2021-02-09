package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 04 Solution");

        // >> Create Java Layout <<
        GridPane javaLayout = new GridPane();

        // >> Properties of the Layout <<
        javaLayout.setAlignment(Pos.TOP_LEFT); // Determines where components are placed
        javaLayout.setHgap(10);
        javaLayout.setVgap(10);
        javaLayout.setPadding(new Insets(25,25,25,25));

        // >> Create a custom scene <<
        Scene myScene = new Scene(javaLayout,800,475); // size n x n
        primaryStage.setScene(myScene);

        // >> Create login UI controls <<

        // >> Labels <<
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label nameLabel = new Label("Full Name:");
        Label emailLabel = new Label("E-Mail:");
        Label phoneNumLabel = new Label("Phone #:");
        Label dateOfBirthLabel = new Label("Date of Birth:");

        // >> Inputs <<
        TextField txUsername = new TextField();
        PasswordField psPassword = new PasswordField();
        TextField txName = new TextField();
        TextField txEmail = new TextField();
        TextField txPhoneNumber = new TextField();
        DatePicker datePicker = new DatePicker();

        // >> Button <<
        Button button = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(button);

        // >> Handle Event for Button <<
        button.setOnAction(e -> System.out.println(txName.getText() + "\n" +
                txEmail.getText() + "\n" + txPhoneNumber.getText()
                + "\n" + datePicker.getValue()));

        javaLayout.add(usernameLabel, 0,1);
        javaLayout.add(txUsername, 1,1);
        javaLayout.add(passwordLabel, 0,2);
        javaLayout.add(psPassword, 1,2);
        javaLayout.add(nameLabel, 0,3);
        javaLayout.add(txName, 1,3);
        javaLayout.add(emailLabel, 0,4);
        javaLayout.add(txEmail, 1,4);
        javaLayout.add(phoneNumLabel, 0,5);
        javaLayout.add(txPhoneNumber, 1,5);
        javaLayout.add(dateOfBirthLabel, 0,6);
        javaLayout.add(datePicker, 1,6);
        javaLayout.add(hbBtn,1,7);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
