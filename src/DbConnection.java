import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
   String url = "jdbc:mysql://localhost:3306/librarysystem";
   String user = "root";
   String password = "";

    public boolean validateUser(String userName, String userPassword) {
        boolean check = false;

        try {
            Connection con = DriverManager.getConnection(url, user, this.password);

            String query = "SELECT * FROM member WHERE userName = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }
    public boolean saveUserInput(String userName, String password, String email, int age) {
        boolean check = false;
        try (Connection con = DriverManager.getConnection(url, user, this.password)) {
            String query = "INSERT INTO member (userName, password, email, age) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.setString(3, email);
                pstmt.setInt(4, age);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    check = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return check;
    }
    boolean canBorrowMoreBooks(String userName) throws SQLException {
        String query = "SELECT COUNT(*) FROM borrowed_books WHERE userName = ?";
        try (Connection conn = DriverManager.getConnection(url, user, this.password)) {
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count < 5;
                }
            }
        }
        return false;
    }
    boolean storeBorrowedBook(String userName, String bookTitle) throws SQLException {
        String query = "INSERT INTO borrowed_books (userName, book_title) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, this.password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            stmt.setString(2, bookTitle);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    boolean returnBooks(String userName, String bookTitle){
        String query = "DELETE FROM borrowed_books WHERE userName = ? AND book_title = ?";
        try(Connection conn = DriverManager.getConnection(url, user, this.password)){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, bookTitle);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<String> getBorrowedBooks(String userName) {
        ArrayList<String> borrowedBooks = new ArrayList<>();
        String query = "SELECT book_title FROM borrowed_books WHERE userName = ?";
        try (Connection conn = DriverManager.getConnection(url, user, this.password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrowedBooks.add(rs.getString("book_title"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }
}