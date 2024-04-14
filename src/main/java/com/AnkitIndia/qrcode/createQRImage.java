package com.AnkitIndia.qrcode;

/*import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.google.zxing.common.BitMatrix;
import javax.imageio.ImageIO;*/

import java.io.File;  
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

public class createQRImage {
	
	public createQRImage(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException 
	{
		
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
		
		// TODO Auto-generated method stub
		
		 /*int matrixWidth = qrCodeText.getWidth();
	      BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
	      image.createGraphics();

	      Graphics2D graphics = (Graphics2D) image.getGraphics();
	      graphics.setColor(Color.WHITE);
	      graphics.fillRect(0, 0, matrixWidth, matrixWidth);
	      // Paint and save the image using the ByteMatrix
	      graphics.setColor(Color.BLACK);

	      for (int i = 0; i < matrixWidth; i++) {
	          for (int j = 0; j < matrixWidth; j++) {
	              if (qrCodeText.get(i, j)) {
	                  graphics.fillRect(i, j, 1, 1);
	              }
	          }
	      }
	      ImageIO.write(image, fileType, qrFile);*/
	  }

}
