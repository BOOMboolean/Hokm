package Game;

import java.sql.*;
import Server.*;
import GUI.*;
public class Database {
    public void addRow(String roundWinner) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hokm", "root", "");
            PreparedStatement pst = conn.prepareStatement("INSERT INTO score VALUES(default,?)"); //Round is automatically incremented by database
            pst.setString(1, roundWinner);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hokm", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM score");

            System.out.println("  Round  |   Winner");
            while (rs.next()) {
                System.out.print("    " + rs.getInt("Round") + "    |");
                System.out.println("   " + rs.getString("Winner"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
