package Client;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Client extends Application {
    // Class Parameters
    private PrintWriter clientOutput;
    private BufferedReader clientInput;
    private Socket socket = null;

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        // Vertical and horizontal distance between each node
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels
        Label userNameLabel = new Label("Username:");
        Label messageLabel = new Label("Message:");

        // Text fields
        TextField userName = new TextField();
        TextField message = new TextField();

        gridPane.add(userNameLabel, 5,8);
        gridPane.add(userName, 6, 8);
        gridPane.add(messageLabel, 5, 9);
        gridPane.add(message, 6, 9);

        // Button
        Button sendButton = new Button("Send");
        Button exitButton = new Button("Exit");

        gridPane.add(sendButton, 5, 10);
        gridPane.add(exitButton, 5, 11);

        sendButton.setOnAction(actionEvent ->  {
            reconnect();
            clientOutput.println(userName.getText()+": "+message.getText());
            userName.setText("");
            message.setText("");
            try {
                socket.close();
                clientInput.close();
                clientOutput.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        });

        exitButton.setOnAction(actionEvent ->  {
            System.exit(0);
        });

        root.getChildren().add(gridPane);

        primaryStage.setTitle("SimpleBBS Client v1.0");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void reconnect(){
        try {
            socket = new Socket("127.0.0.1", 1024);                            // Connect to the server
            clientOutput = new PrintWriter(socket.getOutputStream(), true);            // Output stream
            clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // Input Stream

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
