package Server;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Interface extends Application {
    // Class Parameters
    public static TextArea textArea = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        // GridPane Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 50, 50, 60));
        gridPane.setAlignment(Pos.CENTER);

        // TextArea
        textArea = new TextArea();
        textArea.setPrefHeight(300);
        textArea.setPrefWidth(375);
        gridPane.getChildren().add(textArea);

        // Button
        Button exitButton = new Button("Exit");

        exitButton.setOnAction(actionEvent ->  {
            System.exit(0);
        });

        gridPane.add(exitButton, 0, 1);

        root.getChildren().add(gridPane);

        primaryStage.setTitle("SimpleBBS Server v1.0");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public static void loadInterface() {
        launch();
    }
}

