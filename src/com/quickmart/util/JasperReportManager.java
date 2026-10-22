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

    private static void launchReport(String reportPath) {
        try {
            Connection con = DBConnection.getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(
                JasperReportManager.class.getResourceAsStream(reportPath)
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), con);
            JasperViewer.viewReport(jasperPrint, false);
            AppLogger.info("Generated Jasper report: " + reportPath);
        } catch (Exception e) {
            AppLogger.error("Failed to generate Jasper report: " + reportPath, e);
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage());
        }
    }

    public static void generateSalesReport() {
        launchReport("/reports/sales_report.jrxml");
    }

    public static void generateLowStockReport() {
        launchReport("/reports/low_stock_report.jrxml");
    }

    public static void generateDailySummaryReport() {
        launchReport("/reports/daily_summary_report.jrxml");
    }
}