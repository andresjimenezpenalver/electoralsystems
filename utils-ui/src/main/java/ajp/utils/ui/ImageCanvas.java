package ajp.utils.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Andres Jimenez Penalver
 */
public class ImageCanvas extends Canvas {

	private static final long serialVersionUID = -962894404332223305L;
	
    private BufferedImage image;

    public ImageCanvas(BufferedImage image) {
        this.image = image;
    }

	@Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    
}
