package NS_Labs;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.Map;

public class LineChart extends JFrame {

    public LineChart(String title, HashMap<Character, Double> playfair, HashMap<Character, Double> vigenere, HashMap<Character, Double> caesar,HashMap<Character, Double> plain_word_frequency){
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset(playfair, vigenere, caesar,plain_word_frequency);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Frequency Distribution of Characters", // Chart title
                "Alphabets", // X-Axis Label
                "Frequency", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

    }

    private DefaultCategoryDataset createDataset( HashMap<Character, Double> playfair, HashMap<Character, Double> vigenere, HashMap<Character, Double> caesar, HashMap<Character, Double> plain_word_frequency) {

        String series1 = "PlayFair";
        String series2 = "Vigenere";
        String series3 = "Caesar";
        String series4 = "Plain Text Frequency";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(Map.Entry<Character, Double> entry: playfair.entrySet()){
            dataset.addValue(entry.getValue(), series1, entry.getKey());
        }
        for(Map.Entry<Character, Double> entry: vigenere.entrySet()){
            dataset.addValue(entry.getValue(), series2, entry.getKey());
        }
        for(Map.Entry<Character, Double> entry: caesar.entrySet()){
            dataset.addValue(entry.getValue(), series3, entry.getKey());
        }
        for(Map.Entry<Character, Double> entry_word: plain_word_frequency.entrySet()){
            dataset.addValue(entry_word.getValue(), series4, entry_word.getKey());
        }

        return dataset;
    }
}
