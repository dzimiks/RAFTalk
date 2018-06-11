package raftalk.listeners;

/**
 * Skup koriscenih listenera.
 * 
 * @author dzimiks
 */
public class ActionManager {

	private SendListener sendListener;
	private CloseConnectionListener closeConnectionListener;
	
	public ActionManager() {
		this.sendListener = new SendListener();
		this.closeConnectionListener = new CloseConnectionListener();
	}

	public SendListener getSendListener() {
		return sendListener;
	}

	public CloseConnectionListener getCloseConnectionListener() {
		return closeConnectionListener;
	}
}