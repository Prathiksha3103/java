package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class StudentPortalUI extends JFrame {
    JTextField emailField;
    JPasswordField passwordField;
    JTextArea outputArea;

    public StudentPortalUI() {
        setTitle("Student Internship Portal");
        setSize(400, 300);
        setLayout(new FlowLayout());

        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        add(loginBtn);

        outputArea = new JTextArea(5, 30);
        add(outputArea);

        loginBtn.addActionListener(e -> loginAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}

    private void loginAction() {
        try {
            Socket s = new Socket("localhost", 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            out.println(emailField.getText());
            out.println(new String(passwordField.getPassword()));

            String response = in.readLine();
            outputArea.setText(response);
            s.close();
        } catch (Exception ex) {
            outputArea.setText("Error connecting to server.");
        }
    }

    public static void main(String[] args) {
        new StudentPortalUI();
    }
}
