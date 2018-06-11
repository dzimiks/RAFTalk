package raftalk.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import raftalk.view.ChatView;

/**
 * Listener kojim gasimo konekciju i zatvaramo prozor aplikacije.
 * 
 * @author milossmi
 */
public class ChatViewWindowListener extends WindowAdapter {

	private ChatView view;
	
	public ChatViewWindowListener(ChatView view) {
		this.view = view;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		view.getActionManager().getCloseConnectionListener().actionPerformed(null);
		System.exit(0);
	}
}