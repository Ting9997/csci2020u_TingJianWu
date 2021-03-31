package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Collections;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ArrayList<Float> google = downloadStockPrices("GOOG");
        ArrayList<Float> apple = downloadStockPrices("AAPL");

        Group root = drawLinePlot(google, apple);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Lab 09: Stock Performance");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ArrayList<Float> downloadStockPrices(String stockTicker){
        ArrayList<Float> data = new ArrayList<>();
        try{
            String sURL = "https://query1.finance.yahoo.com/v7/finance/download/"+stockTicker+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
            URL netURL = new URL(sURL);

            URLConnection connection = netURL.openConnection();
            connection.setDoOutput(false);  // Do not output to the URL
            connection.setDoInput(true);    // accept input from the URL

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = bufferedReader.readLine(); // Skip the first line

            while((line = bufferedReader.readLine()) != null){
                String[] columns = line.split(",");
                data.add(Float.parseFloat(columns[4]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public Group drawLinePlot(ArrayList<Float> data1, ArrayList<Float> data2){
        Group root = new Group();

        Canvas canvas = new Canvas();
        canvas.setWidth(900);
        canvas.setHeight(700);
        root.getChildren().add(canvas);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        double inset = 50;

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.strokeLine(inset, inset, inset, canvas.getHeight()-inset); // starting x coordinate, starting y coordinate, ending x coordinate, ending y coordinate
        graphicsContext.strokeLine(inset, canvas.getHeight()-inset, canvas.getWidth()-inset, canvas.getHeight()-inset);

        float max1 = Collections.max(data1);
        float max2 = Collections.max(data2);
        float largerValue = Math.max(max1, max2);

        plotLine(canvas, data1, largerValue, Color.RED);
        plotLine(canvas, data2, largerValue, Color.BLUE);
        return root;
    }

    public void plotLine(Canvas canvas, ArrayList<Float> data, float maxValue, Color color){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(color);

        double axisWidth = canvas.getWidth()-50*2;
        double axisHeight = canvas.getHeight()-50*2;
        double gap = axisWidth/(data.size()-1);

        for (int i=0; i<data.size()-1; i++){
            graphicsContext.strokeLine(50+(i*gap), axisHeight*(1-data.get(i)/maxValue)+50, 50+((i+1)*gap),axisHeight*(1-data.get(i+1)/maxValue)+50);
        }
    }

}
