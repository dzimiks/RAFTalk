package raftalk.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import raftalk.view.ChatView;

/**
 * Listener kojim obavestavamo da se konekcija treba ugasiti.
 * 
 * @author dzimiks
 */
public class CloseConnectionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String plainText = "exit";
		
		try {
			ChatView.getInstance().getClient().getOut().println(plainText);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ChatView.getInstance().getMessage().setText("");
	}
}