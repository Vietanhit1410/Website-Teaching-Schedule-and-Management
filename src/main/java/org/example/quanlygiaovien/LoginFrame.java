package org.example.quanlygiaovien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Đăng nhập hệ thống quản lý trà sữa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        // Màu chủ đạo sang trọng
        Color backgroundColor = new Color(245, 245, 250); // trắng xám
        Color buttonColor = new Color(0, 51, 102);        // xanh navy
        Color textColor = new Color(33, 37, 41);          // đen xám sang

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTitle = new JLabel("Chào mừng đến hệ thống");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(buttonColor);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        // Tên đăng nhập
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setForeground(textColor);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        // Mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(textColor);
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Button đăng nhập
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBackground(buttonColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        // Xử lý sự kiện
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());

                // Kiểm tra tạm thời
                if (username.equals("admin") && password.equals("123")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Đăng nhập thành công!");
                    dispose();
//                    new MainDashboardFrame(); // TODO: thay bằng dashboard thật
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Sai tài khoản hoặc mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
