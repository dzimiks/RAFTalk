package raftalk.view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import raftalk.client.Client;
import raftalk.listeners.LoginButtonListener;

/**
 * GUI za prikaz prozora za login korisnika.
 * 
 * @author dzimiks
 */
@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPanel;
	private JButton btnLogin;
	
	private JTextField txtName;
	private JTextField txtIPAddress;
	private JTextField txtPort;
	
	private JLabel lblName;
	private JLabel lblIPAddress;
	private JLabel lblPort;
	
	private static Client client;
	
	public Login() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (ClassNotFoundException | InstantiationException | 
			   IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		
		ImagePanel avatar = new ImagePanel(new ImageIcon("images/login-avatar.png").getImage());
		avatar.setBounds(85, 50, 170, 170);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(30, 250, 160, 25);
		
		txtName = new JTextField();
		txtName.setBounds(130, 250, 160, 25);
		
		lblIPAddress = new JLabel("IP Address:");
		lblIPAddress.setBounds(30, 290, 160, 25);
		
		txtIPAddress = new JTextField();
		txtIPAddress.setText("localhost");
		txtIPAddress.setBounds(130, 290, 160, 25);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(30, 330, 160, 25);
		
		txtPort = new JTextField();
		txtPort.setBounds(130, 330, 160, 25);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(130, 400, 100, 25);
		btnLogin.addActionListener(new LoginButtonListener(this));
		
		contentPanel.add(avatar);
		contentPanel.add(lblName);
		contentPanel.add(txtName);
		contentPanel.add(lblIPAddress);
		contentPanel.add(txtIPAddress);
		contentPanel.add(lblPort);
		contentPanel.add(txtPort);
		contentPanel.add(btnLogin);
		
		try {
            this.setIconImage(ImageIO.read(new File("images/raf-logo.png")));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setTitle("RAFTalk - Login");
		setSize(350, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void login(String name, String address, int port) {
		if (hostAvailabilityCheck()) {
			if (authenticate(port)) {
				dispose();
				client = new Client(name, address, port);
				ChatView.getInstance().setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid port. Try with port: 3000!", "Login Error", JOptionPane.ERROR_MESSAGE);
	            txtPort.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Server is not running!", "Login Error", JOptionPane.ERROR_MESSAGE);
            txtPort.setText("");
		}
	}
	
//	Radi bez validacije
//	private void login(String name, String address, int port) {
//		dispose();
//		client = new Client(name, address, port);
//		ChatView.getInstance().setVisible(true);
//	}
	
	public static boolean hostAvailabilityCheck() { 
	    try (Socket s = new Socket("localhost", 3000)) {
	        return true;
	    }
	    catch (IOException ex) {
	    
	    }
	    
	    return false;
	}
	
	public static boolean authenticate(int port) {
        return port == 3000;
    }

	public static Client getClient() {
		return client;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public String getTxtName() {
		return txtName.getText();
	}

	public void setTxtName(String txtName) {
		this.txtName.setText(txtName);
	}

	public String getTxtIPAddress() {
		return txtIPAddress.getText();
	}

	public void setTxtIPAddress(String txtIPAddress) {
		this.txtIPAddress.setText(txtIPAddress);
	}

	public String getTxtPort() {
		return txtPort.getText();
	}

	public void setTxtPort(String txtPort) {
		this.txtPort.setText(txtPort);
	}
}