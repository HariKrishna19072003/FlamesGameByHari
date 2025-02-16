import java.sql.*;
import java.util.*;

public class FlamesGame {
    private static final String URL = "jdbc:mysql://localhost:3306/flames_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Hari@197";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Your name: ");
            String name1 = formatName(scanner.nextLine());

            System.out.print("Enter Your Crush name: ");
            String name2 = formatName(scanner.nextLine());

            String result = calculateFlames(name1, name2);
            System.out.println("Relationship: " + result);

            saveResultToDatabase(name1, name2, result);
        }
    }

    private static String formatName(String name) {
        return name.toLowerCase().replaceAll("\\s", "");
    }

    private static String calculateFlames(String name1, String name2) {
        StringBuilder sb1 = new StringBuilder(name1);
        StringBuilder sb2 = new StringBuilder(name2);

        for (int i = 0; i < sb1.length(); i++) {
            char c = sb1.charAt(i);
            int index = sb2.indexOf(String.valueOf(c));
            if (index != -1) {
                sb1.deleteCharAt(i);
                sb2.deleteCharAt(index);
                i--;
            }
        }

        int count = sb1.length() + sb2.length();
        String flames = "FLAMES";

        while (flames.length() > 1) {
            int index = (count % flames.length()) - 1;
            if (index >= 0) {
                flames = flames.substring(index + 1) + flames.substring(0, index);
            } else {
                flames = flames.substring(0, flames.length() - 1);
            }
        }

        return getFlamesMeaning(flames.charAt(0));
    }

    private static String getFlamesMeaning(char letter) {
        return switch (letter) {
            case 'F' -> "Friends";
            case 'L' -> "Love";
            case 'A' -> "Affection";
            case 'M' -> "Marriage";
            case 'E' -> "Enemy";
            case 'S' -> "Siblings";
            default -> "Unknown";
        };
    }

    private static void saveResultToDatabase(String name1, String name2, String result) {
        String query = "INSERT INTO flames_results (name1, name2, result, timestamp) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name1);
            pstmt.setString(2, name2);
            pstmt.setString(3, result);
            pstmt.executeUpdate();
            System.out.println("Result saved to database!");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}