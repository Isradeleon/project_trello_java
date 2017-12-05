/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import models.Proyecto;
import java.util.Locale;
import models.Tarea;
import models.Usuario;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cesargustavo
 */
public class ViewDashboard extends javax.swing.JPanel {
    DefaultTableModel modelo; 
    Proyecto proj;
    Tarea task;
    Usuario usr;
    ResultSet results;
    
    ChartPanel pieProjects;
    ChartPanel pieTasks;
    JFreeChart chart;
    JFreeChart chartT;
    
    DefaultPieDataset ds;
    DefaultPieDataset dsT;
    PiePlot plot;
    /**
     * Creates new form General
     */
    public ViewDashboard() {
        initComponents();
        proj = new Proyecto();
        task = new Tarea();
        usr = new Usuario();
        ds = new DefaultPieDataset();
        dsT = new DefaultPieDataset();
    }
    
    public final void generatePieProjects(){
        results = proj.getAllPie();
        try{
            ds.clear();
            while(results.next()){
                switch(results.getInt("status")){
                    case 1:
                        ds.setValue("Pendientes", results.getInt("cuenta"));
                    break;
                    
                    case 2:
                        ds.setValue("Cancelados", results.getInt("cuenta"));
                    break;
                    
                    case 3:
                        ds.setValue("Terminados", results.getInt("cuenta"));
                    break;
                }
            }
            chart = ChartFactory.createPieChart("Status proyectos", ds, true, true, Locale.ITALY);
            pieProjects = new ChartPanel(chart);
            panelGraficas.add(pieProjects,BorderLayout.CENTER);
            panelGraficas.validate();
            pieProjects.setSize((this.getSize().width-50) / 2, this.getSize().height / 2);
            pieProjects.setLocation(10,0);
            plot = (PiePlot) chart.getPlot();
            plot.setSectionPaint("Pendientes", Color.orange.darker());
            plot.setSectionPaint("Cancelados", Color.red.darker());
            plot.setSectionPaint("Terminados", Color.green.darker());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public final void generatePieTasks(){
        results = task.getAllPie();
        try{
            dsT.clear();
            while(results.next()){
                switch(results.getInt("status")){
                    case 1:
                        dsT.setValue("Pendientes", results.getInt("cuenta"));
                    break;
                    
                    case 2:
                        dsT.setValue("Canceladas", results.getInt("cuenta"));
                    break;
                    
                    case 3:
                        dsT.setValue("Terminadas", results.getInt("cuenta"));
                    break;
                }
            }
            chartT = ChartFactory.createPieChart("Status tareas", dsT, true, true, Locale.ITALY);
            pieTasks = new ChartPanel(chartT);
            panelGraficas.add(pieTasks,BorderLayout.CENTER);
            panelGraficas.validate();
            pieTasks.setSize((this.getSize().width-50) / 2, this.getSize().height / 2);
            pieTasks.setLocation(pieProjects.getSize().width + 20,0);
            plot = (PiePlot) chartT.getPlot();
            plot.setSectionPaint("Pendientes", Color.orange);
            plot.setSectionPaint("Canceladas", Color.red);
            plot.setSectionPaint("Terminadas", Color.green);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public final void actualizarDashboard(){
        panelGraficas.removeAll();
        results = proj.getAll();
        try{
            if (results.first()) {
                this.generatePieProjects();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        results = task.getAll();
        try{
            if (results.first()) {
                this.generatePieTasks();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        results = usr.getCount();
        try{
            if (results.first()) {
                this.txt_users.setText("Usuarios registrados: "+results.getString("cuenta"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        results = task.getCount();
        try{
            if (results.first()) {
                this.txt_tasks.setText("Tareas en total: "+results.getString("cuenta"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        results = proj.getCount();
        try{
            if (results.first()) {
                this.txt_projects.setText("Proyectos en total: "+results.getString("cuenta"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        /*ds.addValue(60, "ISRAEL", "DE LEON");
        ds.addValue(70, "CESAR", "MARTINEZ");
        ds.addValue(30, "INGRID", "FRAIRE");
        ds.addValue(66, "RUBY", "DELGADO");
        ds.addValue(99, "IRVING", "PANCHO");
        */
 //     JFreeChart jfc = ChartFactory.createBarChart3D("TITULO", "X", "Y", ds, PlotOrientation.VERTICAL, true, true, true);
        //JFreeChart jfc = ChartFactory.createPieChart("TITULO", ds, true, true, Locale.ITALY);
        
        /*JFreeChart chart = ChartFactory.createPieChart("Proyectos", ds, true, true, Locale.ITALY);
        pieProjects = new ChartPanel(chart);
        ChartPanel myChart2 = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);
        */
        //panelGraficas.setLayout(new BorderLayout());
/*        panelGraficas.add(myChart,BorderLayout.CENTER);
        panelGraficas.add(myChart2,BorderLayout.CENTER);
        
        panelGraficas.validate();
        myChart.setSize(this.getSize().width / 3, this.getSize().height / 2);
        myChart2.setSize(this.getSize().width / 3, this.getSize().height / 2);
        myChart2.setLocation(myChart.getSize().width + 20, 0);
        System.out.println(String.valueOf(this.getSize().width));
*/        

//ChartPanel f = new ChartPanel(jfc);
        
        //f.setSize(panelGraficas.getSize().width,panelGraficas.getSize().height);
        
        //f.setVisible(true);
        //panelGraficas.removeAll();
        //panelGraficas.add(f,BorderLayout.CENTER);
        //panelGraficas.revalidate();
        //panelGraficas.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGeneral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelGraficas = new javax.swing.JPanel();
        txt_projects = new javax.swing.JLabel();
        txt_tasks = new javax.swing.JLabel();
        txt_users = new javax.swing.JLabel();

        PanelGeneral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Dashboard");

        javax.swing.GroupLayout panelGraficasLayout = new javax.swing.GroupLayout(panelGraficas);
        panelGraficas.setLayout(panelGraficasLayout);
        panelGraficasLayout.setHorizontalGroup(
            panelGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficasLayout.setVerticalGroup(
            panelGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );

        txt_projects.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_projects.setText("jLabel2");

        txt_tasks.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_tasks.setText("jLabel2");

        txt_users.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_users.setText("jLabel2");

        javax.swing.GroupLayout PanelGeneralLayout = new javax.swing.GroupLayout(PanelGeneral);
        PanelGeneral.setLayout(PanelGeneralLayout);
        PanelGeneralLayout.setHorizontalGroup(
            PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGraficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelGeneralLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tasks)
                            .addComponent(txt_projects)
                            .addComponent(txt_users))))
                .addContainerGap(452, Short.MAX_VALUE))
        );
        PanelGeneralLayout.setVerticalGroup(
            PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_projects)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tasks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_users)
                .addGap(18, 18, 18)
                .addComponent(panelGraficas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGeneral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelGraficas;
    private javax.swing.JLabel txt_projects;
    private javax.swing.JLabel txt_tasks;
    private javax.swing.JLabel txt_users;
    // End of variables declaration//GEN-END:variables
}
