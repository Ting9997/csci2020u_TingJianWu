package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 05 Solutions");

        // >> Instantiating Tableview object <<
        TableView<StudentRecord> tableView = new TableView<>();

        // >> Instantiating the column headers <<
        TableColumn<StudentRecord, String> studentNumColumn = new TableColumn<>("SID");
        TableColumn<StudentRecord, String> assignmentsColumn = new TableColumn<>("Assignments");
        TableColumn<StudentRecord, String> midtermColumn = new TableColumn<>("Midterm");
        TableColumn<StudentRecord, String> finalExamColumn = new TableColumn<>("Final Exam");
        TableColumn<StudentRecord, String> finalMarkColumn = new TableColumn<>("Final Mark");
        TableColumn<StudentRecord, String> letterGradeColumn = new TableColumn<>("Letter Grade");

        // >> Add the column headers to the tableview <<
        tableView.getColumns().add(studentNumColumn);
        tableView.getColumns().add(assignmentsColumn);
        tableView.getColumns().add(midtermColumn);
        tableView.getColumns().add(finalExamColumn);
        tableView.getColumns().add(finalMarkColumn);
        tableView.getColumns().add(letterGradeColumn);

        // >> Set the widths of the column headers <<
        studentNumColumn.setMinWidth(100);
        assignmentsColumn.setMinWidth(100);
        midtermColumn.setMinWidth(100);
        finalExamColumn.setMinWidth(100);
        finalMarkColumn.setMinWidth(100);
        letterGradeColumn.setMinWidth(100);

        // >> Define what data will be displayed in each cell of a header <<
        studentNumColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        // >> Instantiating the java layout <<
        VBox vbox = new VBox(tableView);

        // >> Create a custom scene <<
        Scene scene = new Scene(vbox,600,400); // size n x n
        primaryStage.setScene(scene);

        // >> Add data to the tableview <<
        tableView.setItems(DataSource.getAllMarks());

        // >> Display the stage <<
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
