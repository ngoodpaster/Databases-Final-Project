package greenfield;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

//controller
public class SystemController {
	SystemWindow systemWindow; //view
	JDBCConnection jdbcConnection; //model
	
	public SystemController(){
		//declare database connection class (model)
		jdbcConnection = new JDBCConnection();
		
		//declare system window (view)
		systemWindow = new SystemWindow(jdbcConnection);
		
		//login frame
		final JFrame frame = new JFrame("Login");
		frame.setLocationRelativeTo(null);
		final JButton btnLogin = new JButton("Click to login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDBCLoginDialogue loginDlg = new JDBCLoginDialogue(frame, jdbcConnection);
				loginDlg.setVisible(true);
				// if logon successfully
				if (loginDlg.isSucceeded()) {
						btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
						systemWindow.setBlocked(true);
						frame.setVisible(false);
				}
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(btnLogin);
		frame.setVisible(true);
		
		systemWindow.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent windowEvent){
				jdbcConnection.closeJDBCConnection();
			}
		});
	}
	
}
