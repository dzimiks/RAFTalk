package raftalk.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import raftalk.view.Login;

/**
 * Listener za login korisnika.
 * Proverava da li je korisnik ispravno uneo podatke.
 * 
 * @author dzimiks
 */
public class LoginButtonListener implements ActionListener {

	private Login login;
	
	public LoginButtonListener(Login login) {
		this.login = login;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!login.getTxtName().equals("") &&
			!login.getTxtIPAddress().equals("") &&
			!login.getTxtPort().equals("")) {
			String name = login.getTxtName();
			String address = login.getTxtIPAddress();
			int port = Integer.parseInt(login.getTxtPort());
			name = name.replace(" ", "_");
			
			System.out.println("===============");
			System.out.println("User @" + name);
			System.out.println("Address: " + address);
			System.out.println("Port: " + port);
			System.out.println("===============");
			
			login.login(name, address, port);
		}
		else {
			JOptionPane.showMessageDialog(login, "Don't leave empty fields!", "Login Error", JOptionPane.ERROR_MESSAGE);
			login.setTxtName("");
			login.setTxtPort("");
		}
	}
}