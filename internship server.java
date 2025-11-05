package server;
import java.io.*;
import java.net.*;
import java.sql.*;
import database.DBConnection;

public class InternshipServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("🚀 Internship Server Started on port 5000...");

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected...");
                new ClientHandler(s).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
Connection conn;

    ClientHandler(Socket s) {
        this.socket = s;
        conn = DBConnection.getConnection();
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String email = in.readLine();
            String password = in.readLine();

            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM students WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("Login Success! Welcome " + rs.getString("name"));
            } else {
                out.println("Login Failed! Invalid credentials.");
            }

            socket.close();
 } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
