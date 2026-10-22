import com.quickmart.db.DBConnection;
import java.sql.Connection;

public class DBConnectionTest {

    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();
            if (con != null && !con.isClosed()) {
                System.out.println("UNIT TEST PASSED: Database Connection Successful!");
            } else {
                System.err.println("UNIT TEST FAILED: Connection is null or closed.");
            }
        } catch (Exception e) {
            System.err.println("UNIT TEST ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}