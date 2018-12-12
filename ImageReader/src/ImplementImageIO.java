import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {
	private int byteToInt(byte a[], int start, int end) {
		// (array[0] << 0) + (array[1] << 8) + (array[2] << 16) + (array[3] << 24)
		int re = 0;
		for (int i = 0; i <= end-start; ++i) {
			int bits = i * 8;
			re += (int)((a[start+i] & 0xff) << bits);
		}
		return re;
	}

	public Image myRead(String filepath) throws IOException {
		// TODO Auto-generated method stub
		File file = new File(filepath);
		Image image = null;
		try {
			FileInputStream fs = new FileInputStream(file);
			byte bmpHead[] = new byte[14];
			byte bmpInfo[] = new byte[40];
			
			fs.read(bmpHead, 0, 14);
			fs.read(bmpInfo, 0, 40);
			// 读入固定长度部分

			int bmpSize = byteToInt(bmpInfo, 20, 23);
			int biWidth = byteToInt(bmpInfo, 4, 7);	// 4 bytes, 4~7,宽度
			int biHeight = byteToInt(bmpInfo, 8, 11); // 4 bytes, 8~11,高度
			int biBitCount = byteToInt(bmpInfo, 14, 15); // 24位是真彩色
			
			
          //读取位图信息#28-29位，即bmpInfo#14-15位，判断位图是否为24位  
			
			if (biBitCount == 24) {
				int numOfEmptyByte = bmpSize / biHeight - 3 * biWidth;
				
				numOfEmptyByte = (numOfEmptyByte == 4 ? 0 : numOfEmptyByte);
				
				int pixels[] = new int [biWidth * biHeight];
				byte allBytes[] = new byte[bmpSize];
				
				fs.read(allBytes, 0, bmpSize);
				
				int tmpCount = 0;
				for (int i=biHeight-1; i>=0; i--) {
					for (int j=0; j<biWidth; j++) {
//						pixels[biWidth * i + j] = byteToInt(allBytes, tmpCount, tmpCount+3);
						pixels[ biWidth * i + j ] = 0xff << 24  
                              | (allBytes[tmpCount+2] & 0xff) << 16   
                              | (allBytes[tmpCount+1] & 0xff) << 8   
                              | (allBytes[tmpCount] & 0xff) ;
						tmpCount += 3;
					}
					tmpCount += numOfEmptyByte;
				}
				
				image = Toolkit.getDefaultToolkit().createImage(
						(ImageProducer) new MemoryImageSource(
								biWidth, biHeight, pixels, 0, biWidth));
			}
			
			fs.close();	
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (Image) null;
	}

	public Image myWrite(Image image, String filepath) throws IOException {
		// TODO Auto-generated method stub
		try {
			File file = new File(filepath + "BMP");
			BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graph = buffer.createGraphics();
			graph.drawImage(image, 0, 0, null);
			graph.dispose();
			ImageIO.write(buffer, "bmp", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}
