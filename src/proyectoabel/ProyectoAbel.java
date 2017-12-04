/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoabel;
import views.LoginView;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author cesargustavo
 */
public class ProyectoAbel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DefaultPieDataset ds = new DefaultPieDataset();  
        ds.setValue("HOLA", 88);
        ds.setValue("HOLA2", 83);
        ds.setValue("HOLA3", 87);
        
        /*ds.addValue(60, "ISRAEL", "DE LEON");
        ds.addValue(70, "CESAR", "MARTINEZ");
        ds.addValue(30, "INGRID", "FRAIRE");
        ds.addValue(66, "RUBY", "DELGADO");
        ds.addValue(99, "IRVING", "PANCHO");
        */
 //     JFreeChart jfc = ChartFactory.createBarChart3D("TITULO", "X", "Y", ds, PlotOrientation.VERTICAL, true, true, true);
        /*JFreeChart jfc = ChartFactory.createPieChart("TITULO", ds, true, true, Locale.ITALY);
        
        
        ChartFrame f = new ChartFrame("OTRO TITULO",jfc);
        
        f.setSize(1000,6000);
        f.setLocationRelativeTo(null);*/
        //f.setVisible(true);
        
        
        LoginView.main(args);
    }
    
}
