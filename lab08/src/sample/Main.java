package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    private File currentFileName = null;

    private TableView<StudentRecord> tableView = new TableView<>();

    // >> Data for each student will be stored here <<
    private ObservableList<StudentRecord> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        // >> Create the menu <<
        MenuBar menuBar = new MenuBar();
        VBox vbox = new VBox(menuBar);
        Menu menu1 = new Menu("File");
        menuBar.getMenus().add(menu1);

        // >> Add the menu items <<
        MenuItem menuItem1 = new MenuItem("New");
        MenuItem menuItem2 = new MenuItem("Open");
        MenuItem menuItem3 = new MenuItem("Save");
        MenuItem menuItem4 = new MenuItem("Save As");
        MenuItem menuItem5 = new MenuItem("Exit");
        menu1.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5);

        // >> Setting the menu event handlers <<
        menu1.setOnShowing(e -> { System.out.println("Showing Menu 1"); });
        menu1.setOnShown  (e -> { System.out.println("Shown Menu 1"); });
        menu1.setOnHiding (e -> { System.out.println("Hiding Menu 1"); });
        menu1.setOnHidden (e -> { System.out.println("Hidden Menu 1"); });

        // >> Settting the event handlers for menu items <<
        menuItem1.setOnAction(e -> {
            data.clear();
        });

        menuItem2.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            currentFileName = fileChooser.showOpenDialog(primaryStage);
            try{
                data.clear();
                data = load(currentFileName);
                tableView.setItems(data);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        menuItem3.setOnAction(e -> {
            if (currentFileName == null){ // If this file was never saved as before
                FileChooser fileChooser = new FileChooser();

                // >> Set the file extenions that can be saved as <<
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", ".csv"));
                currentFileName = fileChooser.showSaveDialog(primaryStage);
                save(currentFileName);
            }
            else{
                save(currentFileName);
            }
        });

        menuItem4.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            // >> Set the file extenions that can be saved as <<
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", ".csv"));
            currentFileName = fileChooser.showSaveDialog(primaryStage);
            save(currentFileName);
        });

        menuItem5.setOnAction(e -> {
            System.exit(0);
        });

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
        vbox.getChildren().add(tableView);
        tableView.setItems(data);

        // >> Add Button Section <<
        Text addStudentNumLabel = new Text("SID:");
        TextField addStudentNum = new TextField();
        addStudentNum.setPromptText("SID"); // Background text
        addStudentNum.setMaxWidth(studentNumColumn.getPrefWidth());

        Text addAssignmentLabel = new Text("Assignments:");
        TextField addAssignments = new TextField();
        addAssignments.setPromptText("Assignments/100"); // Background text
        addAssignments.setMaxWidth(assignmentsColumn.getPrefWidth());

        Text addMidtermLabel = new Text("Midterm:");
        TextField addMidterm = new TextField();
        addMidterm.setPromptText("Midterm/100"); // Background text
        addMidterm.setMaxWidth(midtermColumn.getPrefWidth());

        Text addFinalExamLabel = new Text("Final Exam:");
        TextField addFinalExam = new TextField();
        addFinalExam.setPromptText("Final Exam/100"); // Background text
        addFinalExam.setMaxWidth(finalExamColumn.getPrefWidth());

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                data.add(new StudentRecord(
                        addStudentNum.getText(), Double.parseDouble(addAssignments.getText()),
                        Double.parseDouble(addMidterm.getText()), Double.parseDouble(addFinalExam.getText())));
                addStudentNum.setText("");
                addAssignments.setText("");
                addMidterm.setText("");
                addFinalExam.setText("");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10); // Horizontal distance between each node
        gridPane.setVgap(10); // Vertical distance between each node
        gridPane.setPadding(new Insets(15, 25, 25, 15));

        gridPane.add(addStudentNumLabel, 0, 0);
        gridPane.add(addAssignmentLabel, 2, 0);
        gridPane.add(addMidtermLabel, 0, 1);
        gridPane.add(addFinalExamLabel, 2, 1);

        gridPane.add(addStudentNum, 1, 0);
        gridPane.add(addAssignments, 3, 0);
        gridPane.add(addMidterm, 1, 1);
        gridPane.add(addFinalExam, 3, 1);
        gridPane.add(addButton, 1, 4);

        vbox.getChildren().add(gridPane);

        // >> Create a custom scene <<
        Scene scene = new Scene(vbox,600,500); // size n x n

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 08 Solutions");

        // >> Display the stage <<
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void save(File file){
        // >> Check to see if a file needs to be created <<
        try {
            File fileObject = new File(file.getAbsolutePath());
            if (fileObject.createNewFile()) {
                System.out.println("File was created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // >> Write to the csv file <<
        try{
            FileWriter writer = new FileWriter(file);
            writer.write("SID, Assignments, Midterm, Final Exam");

            for (int i=0; i<data.size(); i++){
                writer.write(
                        "\n"+ data.get(i).getStudentID()+", "+data.get(i).getAssignments()+", "+data.get(i).getMidterm()+", "
                        +data.get(i).getFinalExam()
                );
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ObservableList<StudentRecord> load(File filePath) throws IOException{
        ObservableList<StudentRecord> data = FXCollections.observableArrayList();
        FileReader sourceFile = new FileReader(filePath);
        BufferedReader input = new BufferedReader(sourceFile);
        String line = input.readLine(); // skip a line
        String[] properties;
        while((line = input.readLine()) != null){
            properties = line.split(",");
            data.add(new StudentRecord(
                    properties[0], Double.parseDouble(properties[1]),
                    Double.parseDouble(properties[2]), Double.parseDouble(properties[3])));
        }
        return data;
    }
}
