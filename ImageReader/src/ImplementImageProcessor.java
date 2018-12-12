import java.awt.Image;
import java.awt.Toolkit;  
import java.awt.image.FilteredImageSource;  
import java.awt.image.RGBImageFilter; 

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {
	public Image showChanelB(Image image) {
		Filter filter = new Filter(Filter.BLUE);
		FilteredImageSource fis = new FilteredImageSource(image.getSource(), filter); 
		Image filteredImage = Toolkit.getDefaultToolkit().createImage(fis);
		return filteredImage;
	}

	public Image showChanelG(Image image) {
		Filter filter = new Filter(Filter.GREEN);
		FilteredImageSource fis = new FilteredImageSource(image.getSource(), filter); 
		Image filteredImage = Toolkit.getDefaultToolkit().createImage(fis);
		return filteredImage;
	}

	public Image showChanelR(Image image) {
		Filter filter = new Filter(Filter.RED);
		FilteredImageSource fis = new FilteredImageSource(image.getSource(), filter); 
		Image filteredImage = Toolkit.getDefaultToolkit().createImage(fis);
		return filteredImage;
	}

	public Image showGray(Image image) {
		Filter filter = new Filter(Filter.GRAY);
		FilteredImageSource fis = new FilteredImageSource(image.getSource(), filter); 
		Image filteredImage = Toolkit.getDefaultToolkit().createImage(fis);
		return filteredImage;
	}

}

class Filter extends RGBImageFilter {
    public static int RED = 1;
    public static int GREEN = 2;
    public static int BLUE = 3;
    public static int GRAY = 4;
	private int mode;
	public Filter(int m) {
        // The filter's operation does not depend on the
        // pixel's location, so IndexColorModels can be
        // filtered directly.
		mode = m;
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        if (mode == RED) {
        	return (rgb & 0xffff0000);
        } else if (mode == GREEN) {
        	return (rgb & 0xff00ff00);
        } else if (mode == BLUE) {
        	return (rgb & 0xff0000ff);
        } else {
        	int g = (int)( ((rgb & 0x00ff0000) >> 16)*0.299 + ((rgb & 0x0000ff00) >> 8 )*0.587  
                    + ((rgb & 0x000000ff))*0.114 );  
            return (rgb & 0xff000000) + (g << 16) + (g << 8) + g; 
        }
    }
}