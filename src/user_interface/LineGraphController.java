package user_interface;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LineGraphController {
    private final int MAX_DATA_POINTS = 30;
    private final int CHART_WIDTH = 200;
    private final int CHART_HEIGHT = 100;

    private int xSeriesData = 0;
    private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    private XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
    private ExecutorService executor;
    private ConcurrentLinkedQueue<Number> dataQ1 = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Number> dataQ2 = new ConcurrentLinkedQueue<>();

    private NumberAxis xAxis;
    
    public void initGraphSettings(Group g, ResourceBundle rb, double xPos, double yPos) {

        xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 10);
        xAxis = setAxisSettings(xAxis);
        NumberAxis yAxis = new NumberAxis();
        
    	LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis){
            @Override
            protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {
            }
        };

        chart = setLineChartSettings(chart, rb);
        displayLineChart(g, chart, xPos, yPos);
        runLineGraph();
    }
    
    public void runLineGraph(){
        executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        AddToQueue addToQueue = new AddToQueue();
        executor.execute(addToQueue);
        prepareTimeline();
    }
    
    private void prepareTimeline() {
        // Every frame to take any data from queue and add to chart
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                addDataToSeries();
            }
        }.start();
    }
    
    private class AddToQueue implements Runnable {
        public void run() {
            try {
                // add a item of random data to queue
                dataQ1.add(Math.random());
                dataQ2.add(Math.random());

                Thread.sleep(500);
                executor.execute(this);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void addDataToSeries() {
        for (int i = 0; i < 20; i++) { //-- add 20 numbers to the plot+
            if (dataQ1.isEmpty()) break;
            series1.getData().add(new XYChart.Data<>(xSeriesData++, dataQ1.remove()));
            series2.getData().add(new XYChart.Data<>(xSeriesData++, dataQ2.remove()));
        }
        // remove points to keep us at no more than MAX_DATA_POINTS
        if (series1.getData().size() > MAX_DATA_POINTS) {
            series1.getData().remove(0, series1.getData().size() - MAX_DATA_POINTS);
        }
        if (series2.getData().size() > MAX_DATA_POINTS) {
            series2.getData().remove(0, series2.getData().size() - MAX_DATA_POINTS);
        }
        xAxis.setLowerBound(xSeriesData - MAX_DATA_POINTS);
        xAxis.setUpperBound(xSeriesData - 1);
    }
    
    public NumberAxis setAxisSettings(NumberAxis axis){
        axis.setForceZeroInRange(false);
        axis.setAutoRanging(false);
        axis.setTickLabelsVisible(false);
        axis.setTickMarkVisible(false);
        axis.setMinorTickVisible(false);
        return axis;
    }
    
    public LineChart<Number, Number> setLineChartSettings(LineChart<Number, Number> lc, 
    		ResourceBundle rb){
        lc.setAnimated(false);
        lc.setTitle(rb.getString("ChartTitle"));
        lc.setHorizontalGridLinesVisible(true);
        series1.setName("Series 1");
        series2.setName("Series 2");
        return lc;
    }
    
    public void displayLineChart(Group g, LineChart<Number, Number> lc, double xPos, double yPos){
    	lc.getData().addAll(series1, series2);
    	lc.setPrefWidth(CHART_WIDTH);
    	lc.setPrefHeight(CHART_HEIGHT);
    	lc.setLayoutX(xPos);
    	lc.setLayoutY(yPos);
        g.getChildren().add(lc);
    }

}
