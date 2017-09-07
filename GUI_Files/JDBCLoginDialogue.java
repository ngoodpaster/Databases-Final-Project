package greenfield;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class JDBCLoginDialogue extends JDialog {
	JDBCConnection con;
	BasicInputField username, password;
	JPanel buttonPanel, inputPanel;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
    public JDBCLoginDialogue(Frame parent, JDBCConnection con) {
        super(parent, "Login", true);
        inputPanel = new JPanel(new GridLayout(3,1));
        this.con = con;
    
 
        username = new BasicInputField("Username: ");
        inputPanel.add(username);
        
        
        password = new BasicInputField("Password: ");
        inputPanel.add(password);
        
        inputPanel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (con.establishJDBCConnection(getUsername(), getPassword())){
                    JOptionPane.showMessageDialog(JDBCLoginDialogue.this,
                            "Hi " + getUsername() + "! You're logged in to your Oracle Database.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(JDBCLoginDialogue.this,
                        "Invalid username/password",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                		username.field.setText("");
                		password.field.setText("");
                		succeeded = false;
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);
 
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return username.field.getText().trim();
    }
 
    public String getPassword() {
        return password.field.getText().toString();
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
}
