package panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
	private Image image;
	/**
	 * Create the panel.
	 */
	public BackgroundPanel()
	{
		setLayout(null);
		setOpaque(false);
	}
	public BackgroundPanel(Image image)
	{
		this();
		this.image = image;
	}
	protected void paintComponent(Graphics g)
	{
		if(image!=null)
		{
			g.drawImage(image, 0, 0, null);
		}
		super.paintComponent(g);
	}
}
