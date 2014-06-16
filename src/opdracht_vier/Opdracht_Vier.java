/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opdracht_vier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Nick
 */
public class Opdracht_Vier {

    protected static Statement stmt = null;
    protected static Statement stmt2 = null;
    protected static Connection conn = null;
    protected static ResultSet rs = null;
    protected static ResultSet rs2 = null;
    protected static boolean match;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Opdracht4", "jan", "school");

        stmt = conn.createStatement();

        Scanner sc = new Scanner(System.in);

        int keuze = 0;

        while (keuze != 9) {

            System.out.println("Wilt u (1) inloggen, of (2) de ingeschreven studenten van een klas bekijken? Kies 9 om te stoppen");

            keuze = sc.nextInt();

            switch (keuze) {
                case 1:
                    logIn();
                    break;
                case 2:
                    getStudents();
                    break;
                default:
                    break;
            }
        }

    }

    public static void logIn() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Voer uw ID in:");
        String id = sc.nextLine();
        System.out.println("Voer uw wachtwoord in:");
        String pw = sc.nextLine();
        boolean correct = false;

        rs = stmt.executeQuery("SELECT * FROM students WHERE studentnr = '" + id + "' AND wachtwoord = '" + pw + "'");

        while (rs.next()) {
            correct = true;
            for (int i = 1; i < 6; i++) {
                if (i != 5) {
                    System.out.print(rs.getString(i) + ", ");
                } else {
                    System.out.println(rs.getString(i));
                }
            }
        }
        if (!correct) {
            System.out.println("Onjuiste combinatie id/wachtwoord");
        }

    }

    public static void getStudents() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Voer de klas in:");
        String klas = sc.nextLine();

        rs = stmt.executeQuery("SELECT * FROM students WHERE ingeschreven = true AND klas = '" + klas + "'");
        while (rs.next()) {
            for (int i = 1; i < 6; i++) {
                if (i == 3) {
                    continue;
                }
                if (i != 5) {
                    System.out.print(rs.getString(i) + ", ");
                } else {
                    System.out.println(rs.getString(i));
                }
            }
        }

    }
}
