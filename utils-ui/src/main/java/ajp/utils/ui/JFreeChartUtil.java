package ajp.utils.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Andres Jimenez Penalver
 */
public class JFreeChartUtil {

    /**
     * Devuelve el diagrama de lineas creado con los argumentos pasados
     * 
     * @param title
     * @param xAxisTitle
     * @param yAxisTitle
     * @param seriesNames
     * @param categoryNames
     * @param categoryData
     * @param orientation
     * @param showLegend
     * @param tooltips
     * @param urls
     */
    static public JFreeChart createLineChart(
        String title,
        String xAxisTitle,
        String yAxisTitle,
        String[] seriesNames,
        String[] categoryNames,
        float[][] categoryData,
        PlotOrientation orientation,
        boolean showLegend,
        boolean tooltips,
        boolean urls) {
        DefaultCategoryDataset categoryDataset =
            new DefaultCategoryDataset();
        for (int i = 0; i < seriesNames.length; i++) {
            for (int j = 0; j < categoryNames.length; j++) {
                categoryDataset.addValue(
                    categoryData[i][j],
                    seriesNames[i],
                    categoryNames[j]);
            }
        }

        JFreeChart chart =
            ChartFactory.createLineChart(
                title,
                xAxisTitle,
                yAxisTitle,
                categoryDataset,
                orientation,
                showLegend,
                tooltips,
                urls);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE);   
        return chart;
    }

    /**
     * Devuelve el diagrama de barras creado con los argumentos pasados
     * 
     * @param title
     * @param xAxisTitle
     * @param yAxisTitle
     * @param seriesNames
     * @param categoryNames
     * @param categoryData
     * @param orientation
     * @param showLegend
     * @param tooltips
     * @param urls
     * @return diagrama de barras 2D
     */
    static public JFreeChart createBarChart2D(
        String title,
        String xAxisTitle,
        String yAxisTitle,
        String[] seriesNames,
        String[] categoryNames,
        Number[][] categoryData,
        PlotOrientation orientation,
        boolean showLegend,
        boolean tooltips,
        boolean urls) {
        DefaultCategoryDataset categoryDataset =
            new DefaultCategoryDataset();
        for (int i = 0; i < seriesNames.length; i++) {
            for (int j = 0; j < categoryNames.length; j++) {
                categoryDataset.addValue(
                    categoryData[i][j],
                    seriesNames[i],
                    categoryNames[j]);
            }
        }

        JFreeChart chart =
            ChartFactory.createBarChart(
                title,
                xAxisTitle,
                yAxisTitle,
                categoryDataset,
                orientation,
                showLegend,
                tooltips,
                urls);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE);  
        plot.setOutlinePaint(null);
        if (showLegend) {
        	LegendTitle legendTitle = chart.getLegend();
        	legendTitle.setPosition(RectangleEdge.RIGHT);
        }
        return chart;
    }

    /**
     * Devuelve el diagrama de barras creado con los argumentos pasados
     * 
     * @param title
     * @param xAxisTitle
     * @param yAxisTitle
     * @param seriesNames
     * @param categoryNames
     * @param categoryData
     * @param orientation
     * @param showLegend
     * @param tooltips
     * @param urls
     * @return diagrama de barras 3D
     */
    static public JFreeChart createBarChart3D(
        String title,
        String xAxisTitle,
        String yAxisTitle,
        String[] seriesNames,
        String[] categoryNames,
        Number[][] categoryData,
        PlotOrientation orientation,
        boolean showLegend,
        boolean tooltips,
        boolean urls) {
        DefaultCategoryDataset categoryDataset =
            new DefaultCategoryDataset();
        for (int i = 0; i < seriesNames.length; i++) {
            for (int j = 0; j < categoryNames.length; j++) {
                categoryDataset.addValue(
                    categoryData[i][j],
                    seriesNames[i],
                    categoryNames[j]);
            }
        }

        JFreeChart chart =
            ChartFactory.createBarChart(
                title,
                xAxisTitle,
                yAxisTitle,
                categoryDataset,
                orientation,
                showLegend,
                tooltips,
                urls);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE);        
        return chart;
    }

    /**
     * Devuelve el diagrama de tarta creado con los argumentos pasados
     * 
     * @param title
     * @param keys
     * @param values
     * @param legend
     * @param tooltips
     * @param urls
     * @return diagrama de tarta 2D
     */
    static public JFreeChart createPieChart2D(
        String title,
        String[] keys,
        Number[] values,
        boolean legend,
        boolean tooltips,
        boolean urls) {
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        for (int i = 0; i < keys.length; i++) {
            pieDataSet.setValue(keys[i], values[i]);
        }
        JFreeChart chart =
            ChartFactory.createPieChart(
                title,
                pieDataSet,
                legend,
                tooltips,
                urls);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE);   
        plot.setOutlinePaint(null);
        LegendTitle legendTitle = chart.getLegend();
        legendTitle.setPosition(RectangleEdge.RIGHT);
        return chart;
    }

    /**
     * Devuelve el diagrama de tarta 3D creado con los argumentos pasados
     * 
     * @param title
     * @param keys
     * @param values
     * @param legend
     * @param tooltips
     * @param urls
     * @return diagrama de tarta 3D
     */
    static public JFreeChart createPieChart3D(
        String title,
        String[] keys,
        Number[] values,
        boolean legend,
        boolean tooltips,
        boolean urls) {
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        for (int i = 0; i < keys.length; i++) {
            pieDataSet.setValue(keys[i], values[i]);
        }
        JFreeChart chart =
            ChartFactory.createPieChart3D(
                title,
                pieDataSet,
                legend,
                tooltips,
                urls);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE);  
        plot.setOutlinePaint(null);
        LegendTitle legendTitle = chart.getLegend();
        legendTitle.setPosition(RectangleEdge.RIGHT); 
        return chart;
    }

    static public JFreeChart createHalfRingChart(
            String title,
            String[] keys,
            Number[] values,
            boolean legend,
            boolean tooltips,
            boolean urls) {
            DefaultPieDataset pieDataSet = new DefaultPieDataset();
            for (int i = 0; i < keys.length; i++) {
                pieDataSet.setValue(keys[i], values[i]);
            }
            
            JFreeChart chart =
                ChartFactory.createRingChart(
                    title,
                    pieDataSet,
                    legend,
                    tooltips,
                    urls);
                        
            RingPlot plot = ((RingPlot) chart.getPlot());
            plot.setBackgroundPaint( Color.WHITE);
            plot.setOutlinePaint(null);
            plot.setSectionDepth(0.5);
            LegendTitle legendTitle = chart.getLegend();
            legendTitle.setPosition(RectangleEdge.RIGHT);            
            return chart;
        }   
    
    static public JFreeChart createRingChart(
            String title,
            String[] keys,
            Number[] values,
            boolean legend,
            boolean tooltips,
            boolean urls) {
            DefaultPieDataset pieDataSet = new DefaultPieDataset();
            for (int i = 0; i < keys.length; i++) {
                pieDataSet.setValue(keys[i], values[i]);
            }
            JFreeChart chart =
                ChartFactory.createRingChart(
                    title,
                    pieDataSet,
                    legend,
                    tooltips,
                    urls);
            
            RingPlot plot = ((RingPlot) chart.getPlot());
            plot.setBackgroundPaint( Color.WHITE);
            plot.setOutlinePaint(null);
            plot.setSectionDepth(0.5);
            LegendTitle legendTitle = chart.getLegend();
            legendTitle.setPosition(RectangleEdge.RIGHT);            
            return chart;
        }    
    
    /**
     * Devuelve la imagen del diagrama chart 
     * 
     * @param chart diagrama
     * @param width ancho de la imagen
     * @param height alto de la imagen
     * @return la imagen del diagrama
     */
    static public BufferedImage getBufferedImage(
        JFreeChart chart,
        int width,
        int height) {
        BufferedImage image = chart.createBufferedImage(width, height);
        return image;
    }

}