package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumLimit extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent e) {
		int keychar=e.getKeyChar();
		if(!(keychar>=KeyEvent.VK_0&&keychar<=KeyEvent.VK_9||keychar==KeyEvent.VK_PERIOD)) {
			e.consume();
		}
	}

}
