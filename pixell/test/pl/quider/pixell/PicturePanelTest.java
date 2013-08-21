/**
 * 
 */
package pl.quider.pixell;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

import javax.imageio.ImageTypeSpecifier;

import org.junit.Before;
import org.junit.Test;

import sun.awt.image.SurfaceManager.ImageAccessor;

/**
 * @author Adrian
 *
 */
public class PicturePanelTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link pl.quider.pixell.PicturePanel#loadPicture(java.awt.image.BufferedImage)}.
	 */
	@Test
	public void testLoadPicture() {
		BufferedImage bufferedImage = new BufferedImage(100, 100, Image.SCALE_DEFAULT IndexColorModel.BITMASK);
		
	}

	/**
	 * Test method for {@link pl.quider.pixell.PicturePanel#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintGraphics() {
		fail("Not yet implemented");
	}

}
