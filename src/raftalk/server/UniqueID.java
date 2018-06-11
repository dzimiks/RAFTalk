package raftalk.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generise identifikacioni ID korisnika.
 * 
 * @author dzimiks
 */
public class UniqueID {

	private static final int RANGE = 100;
	
	private static List<Integer> ids = new ArrayList<Integer>();
	private static int index = 0;

	static {
		for (int i = 1; i <= RANGE; i++) 
			ids.add(i);
		
		Collections.shuffle(ids);
	}

	public static int getID() {
		if (index > ids.size() - 1)
			index = 0;
		
		return ids.get(index++);
	}
}