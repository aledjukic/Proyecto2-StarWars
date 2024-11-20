/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIClasses;

import EDD.Queue;
import MainClasses.Movie;
import javax.swing.JLabel;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class TvPanelUI extends javax.swing.JPanel {

    /**
     * Creates new form TvPanelUI
     */
    public TvPanelUI() {
        initComponents();
        this.getQueueUI1().getTitleQueueUI().setText("COLA DE PRIORIDAD: 1");
        this.getQueueUI2().getTitleQueueUI().setText("COLA DE PRIORIDAD: 2");
        this.getQueueUI3().getTitleQueueUI().setText("COLA DE PRIORIDAD: 3");
        this.getQueueUI4().getTitleQueueUI().setText("COLA DE REFUERZOS");
    }

    public void updateUIQueue(Queue queue1, Queue queue2, Queue queue3, Queue queue4) {
        this.queueUI1.updateQueueUI(queue1);
        this.queueUI2.updateQueueUI(queue2);
        this.queueUI3.updateQueueUI(queue3);
        this.queueUI4.updateQueueUI(queue4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        cartoonNetworkLogo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        queueUI1 = new GUIClasses.QueueUI();
        queueUI2 = new GUIClasses.QueueUI();
        queueUI3 = new GUIClasses.QueueUI();
        logo = new javax.swing.JLabel();
        queueUI4 = new GUIClasses.QueueUI();
        victoriesLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        cartoonNetworkLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartoonNetworkLogo.setText("cartoonNetworkLogo");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(queueUI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        jPanel1.add(queueUI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));
        jPanel1.add(queueUI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("TVLogo");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 100));
        jPanel1.add(queueUI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        victoriesLabel.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        victoriesLabel.setForeground(new java.awt.Color(0, 0, 0));
        victoriesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        victoriesLabel.setText("0");
        jPanel1.add(victoriesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 150, -1));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VICTORIAS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 150, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 740));
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel cartoonNetworkLogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private GUIClasses.QueueUI queueUI1;
    private GUIClasses.QueueUI queueUI2;
    private GUIClasses.QueueUI queueUI3;
    private GUIClasses.QueueUI queueUI4;
    private javax.swing.JLabel victoriesLabel;
    // End of variables declaration                   

    public JLabel getLogo() {
        return this.logo;
    }

    public QueueUI getQueueUI1() {
        return this.queueUI1;
    }

    public QueueUI getQueueUI2() {
        return this.queueUI2;
    }

    public QueueUI getQueueUI3() {
        return this.queueUI3;
    }

    public QueueUI getQueueUI4() {
        return this.queueUI4;
    }

    /**
     * @return the victoriesLabel
     */
    public javax.swing.JLabel getVictoriesLabel() {
        return victoriesLabel;
    }

}
