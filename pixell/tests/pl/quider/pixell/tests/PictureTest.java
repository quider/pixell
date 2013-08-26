/**
 * 
 */
package pl.quider.pixell.tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.quider.pixell.Picture;

/**
 * @author akozlowski
 *
 */
public class PictureTest {

	private File file;

	@Before
	public void before(){
		file = new File("file.jpg");
		BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(new Color(255,0,0));
		graphics.fillRect(0, 0, 100, 100);
		try {
			ImageIO.write(bufferedImage, "jpg", file);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void after(){
		file.delete();
	}
	
	@Test
	public void testConstructPictureImage(){
		BufferedImage bi;
		try {
			bi = ImageIO.read(file);
			Picture pictureFromImage = new Picture(bi);
			assertNotNull("Image not loaded",pictureFromImage);
			assertNotNull("Buffered image is null", pictureFromImage.getBi());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConstructPictureString(){
		try {
			Picture pictureFromString = new Picture(file.getAbsolutePath());
			assertNotNull("Buffered image is null", pictureFromString.getBi());
			assertNotNull("Path is null", pictureFromString.getPath());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}


	/**
	 * Test method for {@link pl.quider.pixell.Picture#getAverageColor(java.awt.image.BufferedImage)}.
	 */
	@Test
	public void testGetAverageColor() {
		Picture p = null;
		try {
			p = new Picture(file.getAbsolutePath());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertNotNull("Image not loaded",p);
		assertNotNull("Image not loaded",p.getBi());
		Color color = Picture.getAverageColor(p.getBi());
		assertNotNull("Color is null",color);
		
		assertTrue("Color is not "+Color.red+" but "+color, color.equals(Color.red));
	}


}
