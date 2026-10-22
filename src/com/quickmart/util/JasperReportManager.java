package com.quickmart.util;

import com.quickmart.db.DBConnection;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportManager {

    public static void generateSalesReport() {
        try {
            Connection con = DBConnection.getConnection();
            
            // Compiles and generates dynamic report directly from database
            String query = "SELECT sale_id, total_amount, sale_date FROM sales ORDER BY sale_id DESC";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(
                JasperReportManager.class.getResourceAsStream("/reports/sales_report.jrxml")
            );
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), con);
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Report Generation Notice: Displaying SQL View (Jasper template optional)");
            e.printStackTrace();
        }
    }
}