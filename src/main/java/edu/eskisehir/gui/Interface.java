package edu.eskisehir.gui;

import edu.eskisehir.solution.*;
import edu.eskisehir.utils.LinkedList;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Interface extends javax.swing.JFrame {
    public LinkedList<LinkedList<Double>> datasets = new LinkedList<>();
    public LinkedList<Double> demandDataset = new LinkedList<>();
    public LinkedList<Double> forecastDataset = new LinkedList<>();

    /**
     * Creates new form Interface
     *
     */
    public Interface() {
        initComponents();
        setConsole(console); // setting JTextArea console as default output type of the program
        btnCalculate.setEnabled(false);
        btnSet.setEnabled(false);
        getDatasets();
        setDatasetChooser();
        centerTable(demandTable);
        forecastTable.setEnabled(false);
    }

    private void setDatasetChooser() {
        datasetChooser.removeAllItems();
        if (this.datasets.size() == 1) {
            btnDelete.setEnabled(false);
            System.out.println("There's only one dataset left, therefore you cannot delete the remaining dataset.");
        }
        if (!btnDelete.isEnabled() && this.datasets.size() > 1) {
            btnDelete.setEnabled(true);
        }
        for (int i = 0; i < this.datasets.size(); i++) {
            datasetChooser.addItem(String.format("Dataset %s", i + 1));
        }
    }

    private void getDatasets() {
        File file = new File("database.bin");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                this.datasets = (LinkedList<LinkedList<Double>>) in.readObject();
            } catch (Exception exception) {
                System.out.println("\nAn error occurred during process...");
            }
        } else {
            System.out.println("\nDatabase file not found creating default datasets and saving them.");
            LinkedList<Double> dataset1 = new LinkedList<>();
            dataset1.add(300d);
            dataset1.add(350d);
            dataset1.add(330d);
            dataset1.add(340d);
            dataset1.add(390d);
            dataset1.add(430d);
            dataset1.add(480d);
            dataset1.add(460d);
            dataset1.add(490d);
            dataset1.add(510d);
            dataset1.add(550d);
            dataset1.add(560d);
            dataset1.add(550d);
            dataset1.add(590d);
            dataset1.add(600d);
            dataset1.add(610d);
            dataset1.add(630d);
            dataset1.add(620d);
            dataset1.add(680d);
            dataset1.add(690d);
            dataset1.add(710d);
            dataset1.add(730d);
            dataset1.add(740d);
            dataset1.add(770d);

            LinkedList<Double> dataset2 = new LinkedList<>();
            dataset2.add(200d);
            dataset2.add(300d);
            dataset2.add(250d);
            dataset2.add(600d);
            dataset2.add(650d);
            dataset2.add(670d);
            dataset2.add(400d);
            dataset2.add(440d);
            dataset2.add(430d);
            dataset2.add(900d);
            dataset2.add(980d);
            dataset2.add(990d);
            dataset2.add(300d);
            dataset2.add(370d);
            dataset2.add(380d);
            dataset2.add(710d);
            dataset2.add(730d);
            dataset2.add(790d);
            dataset2.add(450d);
            dataset2.add(480d);
            dataset2.add(490d);
            dataset2.add(930d);
            dataset2.add(960d);
            dataset2.add(980d);

            this.datasets.add(dataset1);
            this.datasets.add(dataset2);
            return;
        }
        System.out.println("\nDatabase file has been found and loaded successfully.");
    }

    private void saveDatasets() { // database.bin saving
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.bin"))) {
            LinkedList<LinkedList<Double>> datasetsDB = datasets;
            out.writeObject(datasetsDB);
            out.reset();
        } catch (IOException exception) {
            System.out.println("An error occurred during saving databases process...");
        }
        System.out.println("Datasets have been saved.");
    }

    private void setConsole(JTextArea console) { // all of the outputs will appear into JTextArea
        TextAreaOutputStream taos = new TextAreaOutputStream(console, 60);
        PrintStream ps = new PrintStream(taos);
        System.setOut(ps);
        System.setErr(ps);
    }

    private void centerTable(JTable table) { // centering values of cells to make tables easy to understand
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Integer.class, centerRenderer);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        btnCalculate = new javax.swing.JButton();
        datasetChooser = new javax.swing.JComboBox<>();
        btnSet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        demandTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        forecastTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Forecaster");
        setMaximumSize(new java.awt.Dimension(750, 557));
        setMinimumSize(new java.awt.Dimension(750, 557));
        setPreferredSize(new java.awt.Dimension(750, 557));
        setResizable(false);

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        console.setText("You can select datasets by selecting on dropbox menu then also you can change the values of dataset \nthat you've chosen by changing the table and then pressing set button. \nAfterwards you can calculate both 4 forecasting methods using the dataset.\nYou can also enter 20+30+50 input for 10 day perioding for a month.");
        jScrollPane3.setViewportView(console);

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateDataset(evt);
            }
        });

        datasetChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dataset 1", "Dataset 2" }));

        btnSet.setText("Set");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDataset(evt);
            }
        });

        demandTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Year 1", null, null, null, null, null, null, null, null, null, null, null, null},
                {"Year 2", null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Period", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(demandTable);
        if (demandTable.getColumnModel().getColumnCount() > 0) {
            demandTable.getColumnModel().getColumn(0).setResizable(false);
            demandTable.getColumnModel().getColumn(1).setResizable(false);
            demandTable.getColumnModel().getColumn(2).setResizable(false);
            demandTable.getColumnModel().getColumn(3).setResizable(false);
            demandTable.getColumnModel().getColumn(4).setResizable(false);
            demandTable.getColumnModel().getColumn(5).setResizable(false);
            demandTable.getColumnModel().getColumn(6).setResizable(false);
            demandTable.getColumnModel().getColumn(7).setResizable(false);
            demandTable.getColumnModel().getColumn(8).setResizable(false);
            demandTable.getColumnModel().getColumn(9).setResizable(false);
            demandTable.getColumnModel().getColumn(10).setResizable(false);
            demandTable.getColumnModel().getColumn(11).setResizable(false);
            demandTable.getColumnModel().getColumn(12).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Forecaster");

        forecastTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Year 1", null, null, null, null, null, null, null, null, null, null, null, null},
                {"Year 2", null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Period", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(forecastTable);
        if (forecastTable.getColumnModel().getColumnCount() > 0) {
            forecastTable.getColumnModel().getColumn(1).setResizable(false);
            forecastTable.getColumnModel().getColumn(2).setResizable(false);
            forecastTable.getColumnModel().getColumn(4).setResizable(false);
            forecastTable.getColumnModel().getColumn(7).setResizable(false);
            forecastTable.getColumnModel().getColumn(11).setResizable(false);
        }

        jLabel2.setText("Values of forecast:");

        jLabel3.setText("Values of dataset:");

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDataset(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewDataset(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDataset(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDataset(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datasetChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLoad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave)
                                .addGap(9, 9, 9)
                                .addComponent(btnSet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCalculate)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datasetChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSet)
                    .addComponent(btnCalculate)
                    .addComponent(btnLoad)
                    .addComponent(btnNew)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cleanTable(JTable table) {
        for (int i = 0; i < 24; i++) {
            table.setValueAt(0, i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11);
        }
    }

    private void loadDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDataset
        // TODO add your handling code here:
        cleanTable(demandTable);
        int index = datasetChooser.getSelectedIndex();
        LinkedList<Double> dataset = datasets.get(index);
        for (int i = 0; i < dataset.size(); i++) {
            if (dataset.get(i) != null) {
                demandTable.setValueAt(Integer.toString(dataset.get(i).intValue()), i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11);
            }
        }
        this.demandDataset = dataset;
        System.out.println(String.format("Dataset %s has been loaded.", ++index));
    }//GEN-LAST:event_loadDataset

    private void setDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDataset
        // TODO add your handling code here:
        int index = datasetChooser.getSelectedIndex();
        LinkedList<Double> dataset = datasets.get(index);
        LinkedList<Double> tempDataset = new LinkedList<>();
        for (int i = 0; i < dataset.size(); i++) {
            Double value = Double.valueOf((String) demandTable.getValueAt(i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11));
            tempDataset.add(value);
        }
        datasets.set(index, dataset);
        btnLoad.setEnabled(false);
        btnSave.setEnabled(false);
        demandTable.setEnabled(false);
        datasetChooser.setEnabled(false);
        btnNew.setEnabled(false);
        btnDelete.setEnabled(false);
        btnCalculate.setEnabled(true);
        saveDatasets();
        System.out.println(String.format("Dataset %s has been saved from the table and selected to be calculated.", ++index));
    }//GEN-LAST:event_setDataset

    private void createNewDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewDataset
        // TODO add your handling code here:
        LinkedList<Double> list = new LinkedList<>();
        if (this.demandDataset.size() < 1) {
            System.out.println("You must load a dataset first to save it.");
            return;
        }
        for (int i = 0; i < 24; i++) {
            Double value = Double.valueOf((String) demandTable.getValueAt(i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11));
            list.add(value);
        }
        System.out.println("New dataset from demand dataset has been created.");
        this.datasets.add(list);
        setDatasetChooser();
    }//GEN-LAST:event_createNewDataset

    private void deleteDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDataset
        // TODO add your handling code here:
        int index = datasetChooser.getSelectedIndex();
        datasets.remove(index);
        setDatasetChooser();
        cleanTable(demandTable);
        btnSave.setEnabled(true);
        this.demandDataset = new LinkedList<>();
        System.out.println(String.format("Dataset %s has been removed", ++index));
    }//GEN-LAST:event_deleteDataset

    private void calculateDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateDataset
        // TODO add your handling code here:
        Solution DRA = new DRASolution(demandDataset);
        DRA.solve();
        Solution DES = new DESSolution(demandDataset);
        DES.solve();
        Solution ES = new ESSolution(demandDataset);
        ES.solve();
        Solution RA = new RASolution(demandDataset);
        RA.solve();
        LinkedList<Solution> allSolutions = new LinkedList<>();
        allSolutions.add(DES);
        allSolutions.add(DRA);
        allSolutions.add(ES);
        allSolutions.add(RA);
        printResultsAndBestDataset(allSolutions);

    }//GEN-LAST:event_calculateDataset


    private void printResultsAndBestDataset(LinkedList<Solution> solutions) {
        double bestMSE = Double.MAX_VALUE;
        Solution bestSolution = null;
        for (int i = 0; i < solutions.size(); i++) {
            Solution solution = solutions.get(i);
            double MSE = solution.getMSE();
            System.out.println(solution.getName() + " got: " + MSE + " MSE value");
            if (MSE < bestMSE) {
                bestMSE = MSE;
                forecastDataset = solution.getForecastedDataset();
                bestSolution = solution;
            }
        }
        System.out.println("Best solution for this dataset is: " + bestSolution.getName());
        loadForecastedDataset();
    }

    private void loadForecastedDataset() {
        for (int i = 0; i < forecastDataset.size(); i++) {
            if (forecastDataset.get(i) != null) {
                forecastTable.setValueAt(Integer.toString(forecastDataset.get(i).intValue()), i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11);
            }
        }
    }

    private void saveDataset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDataset
        // TODO add your handling code here:
        int index = datasetChooser.getSelectedIndex();
        LinkedList<Double> dataset = datasets.get(index);
        if (this.demandDataset.size() < 1) {
            System.out.println("You must load a dataset first to save it.");
            return;
        }
        for (int i = 0; i < dataset.size(); i++) {
            String strValue = (String) demandTable.getValueAt(i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11);
            strValue.trim();
            if (strValue.isEmpty()) {
                System.out.println("You have to load a dataset first!");
                return;
            }
            Double value = 0d;
            if (strValue.contains("+")) {
                String[] strNumbers = strValue.split("\\+");
                for (int j = 0; j < strNumbers.length; j++) {
                    value += Integer.valueOf(strNumbers[j]).doubleValue();
                }
                demandTable.setValueAt(Integer.toString(value.intValue()), i < 12 ? 0 : 1, i < 12 ? i + 1 : i - 11);
            } else {
                value = Double.valueOf(strValue);
            }
            dataset.set(i, value);
        }
        datasets.set(index, dataset);
        btnSet.setEnabled(true);
        saveDatasets();
        System.out.println(String.format("Current datatable has been saved into dataset %s.", ++index));
    }//GEN-LAST:event_saveDataset

    public int[] getRowAt(int row) {
        int count = 0;
        int colNumber = demandTable.getColumnCount();
        int[] result = new int[colNumber];

        for (int i = 1; i < colNumber; i++) {
            result[i] = (int) (demandTable.getModel().getValueAt(row, i) == null ? 0 : demandTable.getModel().getValueAt(row, i));
            if (demandTable.getModel().getValueAt(row, i) != null) count++;
        }
        return count != 0 ? result : null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSet;
    private javax.swing.JTextArea console;
    private javax.swing.JComboBox<String> datasetChooser;
    private javax.swing.JTable demandTable;
    private javax.swing.JTable forecastTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
