package com.quickmart.gui;

  

    public class POSFrame extends javax.swing.JFrame {

        private double grandTotal = 0.0;

        public POSFrame() {
            initComponents();
            setupCartTable();
        }

        private void setupCartTable() {
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tblCart.getModel();
            dtm.setColumnIdentifiers(new String[]{"Product ID", "Barcode", "Name", "Price", "Qty", "Subtotal"});
            dtm.setRowCount(0);
        }

        private void addItemToCart() {
            String barcode = txtBarcode.getText().trim();
            int inputQty = Integer.parseInt(txtQty.getText().trim());

            if (barcode.isEmpty()) {
                return;
            }

            try {
                java.sql.Connection con = com.quickmart.db.DBConnection.getConnection();
                String sql = "SELECT * FROM products WHERE barcode = ?";
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, barcode);
                java.sql.ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("qty");

                    if (stock < inputQty) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Insufficient stock!");
                        return;
                    }

                    double subtotal = price * inputQty;
                    grandTotal += subtotal;

                    javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tblCart.getModel();
                    dtm.addRow(new Object[]{id, barcode, name, price, inputQty, subtotal});

                    lblTotal.setText("Total: " + grandTotal);
                    txtBarcode.setText("");
                    txtBarcode.requestFocus();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Product Not Found!");
                }
            } catch (Exception e) {
                com.quickmart.util.AppLogger.error("Failed to add item to cart", e);
                javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }

        @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        posPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnAddItem = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Scan Barcode:");

        jLabel2.setText("Qty:");

        txtQty.setText("1");
        txtQty.addActionListener(this::txtQtyActionPerformed);

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCart);

        lblTotal.setText("Total: 0.00");

        btnAddItem.setText("Add Item");
        btnAddItem.addActionListener(this::btnAddItemActionPerformed);

        btnCheckout.setText("Complete Sale");
        btnCheckout.addActionListener(this::btnCheckoutActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout posPanelLayout = new javax.swing.GroupLayout(posPanel);
        posPanel.setLayout(posPanelLayout);
        posPanelLayout.setHorizontalGroup(
            posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(posPanelLayout.createSequentialGroup()
                        .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(posPanelLayout.createSequentialGroup()
                                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblTotal)
                            .addGroup(posPanelLayout.createSequentialGroup()
                                .addComponent(btnAddItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCheckout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBack)))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );
        posPanelLayout.setVerticalGroup(
            posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(posPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddItem)
                    .addComponent(btnCheckout)
                    .addComponent(btnBack))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(posPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(posPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tblCart.getModel();
        if (dtm.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }

        try {
            java.sql.Connection con = com.quickmart.db.DBConnection.getConnection();
            con.setAutoCommit(false); // Begin transaction

            // Insert into sales table
            String saleSql = "INSERT INTO sales (user_id, total_amount) VALUES (1, ?)";
            java.sql.PreparedStatement pstSale = con.prepareStatement(saleSql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pstSale.setDouble(1, grandTotal);
            pstSale.executeUpdate();

            java.sql.ResultSet rsKeys = pstSale.getGeneratedKeys();
            int saleId = 0;
            if (rsKeys.next()) {
                saleId = rsKeys.getInt(1);
            }

            // Insert line items & update inventory stock
            String itemSql = "INSERT INTO sale_items (sale_id, product_id, qty, unit_price) VALUES (?, ?, ?, ?)";
            String updateStockSql = "UPDATE products SET qty = qty - ? WHERE product_id = ?";

            java.sql.PreparedStatement pstItem = con.prepareStatement(itemSql);
            java.sql.PreparedStatement pstStock = con.prepareStatement(updateStockSql);

            for (int i = 0; i < dtm.getRowCount(); i++) {
                int pId = Integer.parseInt(dtm.getValueAt(i, 0).toString());
                int qty = Integer.parseInt(dtm.getValueAt(i, 4).toString());
                double unitPrice = Double.parseDouble(dtm.getValueAt(i, 3).toString());

                pstItem.setInt(1, saleId);
                pstItem.setInt(2, pId);
                pstItem.setInt(3, qty);
                pstItem.setDouble(4, unitPrice);
                pstItem.executeUpdate();

                pstStock.setInt(1, qty);
                pstStock.setInt(2, pId);
                pstStock.executeUpdate();
            }

            con.commit(); // Commit transaction
            javax.swing.JOptionPane.showMessageDialog(this, "Sale Completed Successfully!");
            dtm.setRowCount(0);
            grandTotal = 0.0;
            lblTotal.setText("Total: 0.00");
        } catch (Exception e) {
            com.quickmart.util.AppLogger.error("Sales checkout transaction failed", e);
            javax.swing.JOptionPane.showMessageDialog(this, "Checkout Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new DashboardFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
            addItemToCart();
    }//GEN-LAST:event_btnAddItemActionPerformed

    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new POSFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel posPanel;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
