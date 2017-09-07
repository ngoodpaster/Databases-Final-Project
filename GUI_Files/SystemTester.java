package greenfield;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

public class SystemTester {

	static SystemController systemController;

	public static void main(String[] args) {
		systemController = new SystemController();
	}
}
