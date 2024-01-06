package face.recognisation;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class StudentData extends javax.swing.JFrame {

    Connection con = MyConnection.connect();
    private String name, dob, rollNum, attendence;
    private int userId;
    private String userName, userEmail;

    public StudentData(String ownerName, int ownerId, String ownerEmail) {
        initComponents();
        userId = ownerId;
        userName = ownerName;
        userEmail = ownerEmail;
        setTitle("Student Data");
        setDefaultCloseOperation(StudentData.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        data();
    }

    private StudentData() {
    }

    public void data() {
        try {
            PreparedStatement ps = con.prepareStatement("select * from students_data");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rollNum = String.valueOf(rs.getInt(1));
                name = rs.getString(2);
                dob = rs.getString(3);
                attendence = String.valueOf(rs.getInt(4));
                String tbData[] = {rollNum, name, dob, attendence};
                DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
                tblModel.addRow(tbData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Roll Number", "Name", "Date of Birth", "Attendence"
            }
        ));
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        table.setShowVerticalLines(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setHeaderValue("Roll Number");
            table.getColumnModel().getColumn(1).setHeaderValue("Name");
            table.getColumnModel().getColumn(2).setHeaderValue("Date of Birth");
            table.getColumnModel().getColumn(3).setHeaderValue("Attendence");
        }

        close.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        close.setText("CLOSE >>");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addComponent(close, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        dispose();
        new OwnerScreen(userName, userId, userEmail).setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    public void dataTable() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
