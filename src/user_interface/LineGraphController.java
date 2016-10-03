package user_interface;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LineGraphController {
    private final int MAX_DATA_POINTS = 30;
    private final int CHART_WIDTH = 200;
    private final int CHART_HEIGHT = 100;

    private LineChart<Number, Number> lcChart;
    private int xSeriesData = 0;
    private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    private XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
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
        lcChart = chart;

        chart.getData().addAll(series1, series2);
        chart = setLineChartSettings(chart, rb);
        displayLineChart(g, chart, xPos, yPos);
    }
    
    public void addDataToSeries(int valOne, int valTwo, int total) {
        dataQ1.add((double)valOne/total);
        dataQ2.add((double)valTwo/total);
    	
        for (int i = 0; i < 20; i++) {
            if (dataQ1.isEmpty()) break;
            series1.getData().add(new XYChart.Data<>(xSeriesData++, dataQ1.remove()));
            series2.getData().add(new XYChart.Data<>(xSeriesData++, dataQ2.remove()));
        }
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
        series1.setName(rb.getString("TypeOneTitle"));
        series2.setName(rb.getString("TypeTwoTitle"));
        return lc;
    }
    
    public void displayLineChart(Group g, LineChart<Number, Number> lc, double xPos, double yPos){
    	g.getChildren().remove(lc);
    	lc.setPrefWidth(CHART_WIDTH);
    	lc.setPrefHeight(CHART_HEIGHT);
    	lc.setLayoutX(xPos);
    	lc.setLayoutY(yPos);
        g.getChildren().add(lc);
    }
    
    public LineChart<Number, Number> getChart(){
    	return lcChart;
    }

}
