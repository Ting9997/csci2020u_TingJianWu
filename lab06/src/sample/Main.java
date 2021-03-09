package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        double[] avgHousingPricesByYear = {247381.0, 264171.4, 287715.3, 294736.1, 308431.4,322635.9,340253.0,363153.7};
        double[] avgCommercialPricesByYear = {1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6, 1472362.0, 1583521.9, 1613246.3};

        Pane pane = new Pane();
        Rectangle redRec1 = new Rectangle();
        Rectangle blueRec1 = new Rectangle();

        double yAxisBlue = 0.0;
        double yAxisRed = 0.0;
        int gap = -100;
        int factor = 2800;

        for (int i=0; i<avgCommercialPricesByYear.length; i++){
            yAxisBlue = (avgCommercialPricesByYear[i] / factor)-(avgCommercialPricesByYear[0] / factor);
            yAxisRed = (avgHousingPricesByYear[i] / 2000) - (avgHousingPricesByYear[0] / 2000);
            gap+=75;

            // >> Rectangle Properties <<
            redRec1 = new Rectangle();
            redRec1.setX(50+gap);
            redRec1.setY(577 - yAxisRed);
            redRec1.setWidth(25);
            redRec1.setHeight(avgHousingPricesByYear[i] / 2000);
            redRec1.setStroke(Color.TRANSPARENT);
            redRec1.setFill(Color.valueOf("#ff0000"));

            blueRec1 = new Rectangle();
            blueRec1.setX(75+gap);
            blueRec1.setY(300 - yAxisBlue);
            blueRec1.setWidth(25);
            blueRec1.setHeight(avgCommercialPricesByYear[i] / factor);
            blueRec1.setStroke(Color.TRANSPARENT);
            blueRec1.setFill(Color.valueOf("#001aff"));

            pane.getChildren().add(redRec1);
            pane.getChildren().add(blueRec1);
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
        int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
        Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

        // >> Creating an Arc object <<
        Arc arc = new Arc();
        int factor1 = 3;
        double degree = 0;

        // >> Arc Properties <<
        arc.setCenterX(1000.0); // Remains constant
        arc.setCenterY(400); // Remains constant
        arc.setRadiusX(200);
        arc.setRadiusY(200);
        arc.setStartAngle(0);
        arc.setLength(purchasesByAgeGroup[0]/23); // Determines the degree of the Arc
        arc.setFill(pieColours[0]);
        arc.setType(ArcType.ROUND);
        pane.getChildren().add(arc);

        // >> Setting the properties of the arc <<
        for (int i=pieColours.length-1; i>=1; i--){
            arc = new Arc();

            // >> Arc Properties <<
            arc.setCenterX(1000.0); // Remains constant
            arc.setCenterY(400); // Remains constant
            arc.setRadiusX(200);
            arc.setRadiusY(200);
            degree -= purchasesByAgeGroup[i]/32;
            arc.setStartAngle(degree);
            arc.setLength(purchasesByAgeGroup[i]/32); // Determines the degree of the Arc
            arc.setFill(pieColours[i]);
            arc.setType(ArcType.ROUND);

            pane.getChildren().add(arc);
        }

        Scene scene = new Scene(pane, 1500, 800, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 06");

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
