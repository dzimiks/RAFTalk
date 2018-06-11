package raftalk.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import raftalk.view.ChatView;
import security.CipherEncryption;

/**
 * Listener kojim prosledjujemo klijentu enkriptovanu poruku.
 * 
 * @author dzimiks
 */
public class SendListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = ChatView.getInstance().getClient().getName();
		String message = ChatView.getInstance().getMessage().getText();
		String plainText = "@" + name + ": " + message;
		
		try {
			ChatView.getInstance().getClient().getOut().println(CipherEncryption.encrypt(plainText));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ChatView.getInstance().getMessage().setText("");
	}
}