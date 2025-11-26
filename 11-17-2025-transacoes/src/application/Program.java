package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false); // as operações não serão confirmadas automaticamente

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

           /* int x = 1;
            if (x < 2 ) {
                throw new SQLException("Simulated error");
            }*/

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit(); // "salvar"

            System.out.println("rows1 = " + rows1);
            System.out.println("rows2 = " + rows2);

        }catch (SQLException e){
            try {
                conn.rollback(); // "desfazer'
                throw new DbException("Transaction Rolled back! caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to roll back: " + ex.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
