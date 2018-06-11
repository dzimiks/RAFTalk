package raftalk.client;

import java.awt.Color;
import java.io.IOException;

import raftalk.view.ChatView;
import security.CipherEncryption;

/**
 * Thread klasa za klijenta.
 * 
 * @author dzimiks
 */
public class ClientThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				String text = ChatView.getInstance().getClient().getIn().readLine();
				String decrypted = null;
				
				if (text != "" && text != null) {
					decrypted = CipherEncryption.decrypt(text);
				}

				if (decrypted.equals("exit")) {
					ChatView.getInstance().getChatArea().append(ChatView.getInstance().getClient().getName() + " is disconnected!");
					ChatView.getInstance().getChatArea().setCaretPosition(ChatView.getInstance().getChatArea().getDocument().getLength());
					break;
				}
				
				if (decrypted != "" && !decrypted.equals("") && decrypted != null) {
					Color color = ChatView.getInstance().getClient().getColor();
					ChatView.getInstance().getChatArea().setForeground(color);
					ChatView.getInstance().getChatArea().append(decrypted + "\n");
					ChatView.getInstance().getChatArea().setCaretPosition(ChatView.getInstance().getChatArea().getDocument().getLength());
				}
			}
			catch (IOException e) {
//				e.printStackTrace();
			}
			catch (Exception e1) {
//				e1.printStackTrace();
			}
		}
		
		try {
			ChatView.getInstance().getClient().getSocket().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}