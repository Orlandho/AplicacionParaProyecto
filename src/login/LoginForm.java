package login;

import user.User;
import database.DatabaseManager;
import javax.swing.*;
import java.awt.event.*;
import MenuDinamico.FrmMenuDinamico;
import java.util.ArrayList;

public class LoginForm extends javax.swing.JFrame {

    private DatabaseManager database;

    public LoginForm() {
        setTitle("Inventory System - Agro Integral");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        database = new DatabaseManager();
        Ajuste.Ajustes.cambiarAIngles(this, null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        loginPanelHolder = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        userTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        decorationLabel2 = new javax.swing.JLabel();
        decorationLabel1 = new javax.swing.JLabel();
        companyLogoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventory System - Agro Integral");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        loginPanelHolder.setBackground(new java.awt.Color(126, 176, 175));
        loginPanelHolder.setLayout(null);

        loginPanel.setBackground(new java.awt.Color(0, 103, 102));
        loginPanel.setLayout(null);

        loginLabel.setFont(new java.awt.Font("SansSerif", 1, 18));
        loginLabel.setText("LOGIN");
        loginPanel.add(loginLabel);
        loginLabel.setBounds(33, 14, 65, 30);

        userTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        loginPanel.add(userTextField);
        userTextField.setBounds(102, 91, 220, 35);

        passwordTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        loginPanel.add(passwordTextField);
        passwordTextField.setBounds(102, 180, 220, 35);

        loginButton.setBackground(new java.awt.Color(51, 51, 51));
        loginButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginPanel.add(loginButton);
        loginButton.setBounds(19, 266, 303, 38);

        userLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuario.png")));
        loginPanel.add(userLabel);
        userLabel.setBounds(19, 79, 65, 47);

        passwordLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contraseña.png")));
        loginPanel.add(passwordLabel);
        passwordLabel.setBounds(19, 153, 65, 73);

        decorationLabel2.setFont(new java.awt.Font("SansSerif", 1, 13));
        decorationLabel2.setText("_________________");
        loginPanel.add(decorationLabel2);
        decorationLabel2.setBounds(200, 310, 130, 16);

        decorationLabel1.setFont(new java.awt.Font("SansSerif", 1, 13));
        decorationLabel1.setText("_________________");
        loginPanel.add(decorationLabel1);
        decorationLabel1.setBounds(20, 310, 130, 18);

        loginPanelHolder.add(loginPanel);
        loginPanel.setBounds(336, 19, 350, 400);

        companyLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoEmpresa removebg.png")));
        loginPanelHolder.add(companyLogoLabel);
        companyLogoLabel.setBounds(10, 100, 320, 220);

        getContentPane().add(loginPanelHolder);
        loginPanelHolder.setBounds(0, 0, 719, 472);

        setSize(new java.awt.Dimension(735, 481));
        setLocationRelativeTo(null);
    }

    private String forceCreatePassword(String oldPassword) {
        String[] options = {"OK"};
        JPanel newPasswordPanel = new JPanel();
        JLabel newPasswordLabel = new JLabel("More than 60 days have passed. Please create a new password:");
        JTextField newPasswordField = new JTextField(15);
        newPasswordPanel.add(newPasswordLabel);
        newPasswordPanel.add(newPasswordField);
        String newPassword;
        do {
            JOptionPane.showOptionDialog(this, newPasswordPanel, "Creating password", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            newPassword = newPasswordField.getText();
            if (!User.isValidPassword(newPasswordField.getText())) {
                JOptionPane.showMessageDialog(this, "Incorrect password format. Please try again.");
                continue;
            } else if (oldPassword.equals(newPassword)) {
                JOptionPane.showMessageDialog(this, "The new password must be different from the old one.");
                continue;
            }
            break;
        } while (true);
        return newPasswordField.getText();
    }

    private void interpretResponse(int response) {
        switch (response) {
            case DatabaseManager.INCORRECT_USER_OR_PASSWORD:
                JOptionPane.showMessageDialog(this, "Login attempt, Error: Incorrect user or password");
                break;
            case DatabaseManager.USER_BLOCKED:
                JOptionPane.showMessageDialog(this, "Login attempt, Error: User blocked");
                break;
            case DatabaseManager.MUST_CHANGE_PASSWORD:
                userTextField.setEnabled(false);
                passwordTextField.setEnabled(false);
                if (database.updatePassword(userTextField.getText(), passwordTextField.getText(), forceCreatePassword(passwordTextField.getText()))) {
                    JOptionPane.showMessageDialog(this, "The password was changed successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Could not change password");
                }
                userTextField.setEnabled(true);
                passwordTextField.setEnabled(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Login attempt, Error: undefined");
                break;
        }
    }

    public javax.swing.JButton getLoginButton() {
        return loginButton;
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String idNumber = userTextField.getText(), password = passwordTextField.getText(), error = "Login attempt canceled:\n";
        final int noPass = error.length();
        if (!User.isValidIdNumber(idNumber)) {
            error += "-The user must be a DNI or RUC.\n";
        }
        if (!User.isValidPassword(password)) {
            error += "-The password is too short or too long.\n";
        }
        if (error.length() > noPass) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        ArrayList<Object> dbResponse = database.tryLogin(idNumber, password);
        if ((int) dbResponse.get(0) == DatabaseManager.CAN_LOG_IN) {
            dispose();
            database.closeConnection();
            FrmMenuDinamico menu = new FrmMenuDinamico();
            menu.modificarSegunRol((User) dbResponse.get(1));
            menu.setVisible(true);
        } else {
            interpretResponse((int) dbResponse.get(0));
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "We will miss you ;)", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            database.closeConnection();
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel loginPanelHolder;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel decorationLabel1;
    private javax.swing.JLabel decorationLabel2;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel companyLogoLabel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField userTextField;
}
