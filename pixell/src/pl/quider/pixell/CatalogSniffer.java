package pl.quider.pixell;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class is used to read all elements in directory.
 * 
 * @author akozlowski
 * 
 */
public class CatalogSniffer implements Runnable {

	private Pixell parent;
	private File directory;

	/**
	 * 
	 * @param pixell
	 * @param selectedFile
	 */
	public CatalogSniffer(Pixell pixell, File selectedFile) {
		this.parent = pixell;
		this.directory = selectedFile;
	}

	@Override
	public void run() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (File f : directory.listFiles()) {
			if (!f.isDirectory() ) {
				String ext = f.getName().substring(f.getName().indexOf("."));
				if(!f.getName().endsWith(ext)) continue;
				Runnable command = new PictureViewer(f, parent);
				executor.execute(command);
//				executor.
			} else {
				Runnable catalogSniffer = new CatalogSniffer(parent, f);
				Thread t = new Thread(catalogSniffer);
				t.start();
			}
		}

	}

	/**
	 * 
	 * @author akozlowski
	 * 
	 */
	class PictureViewer implements Runnable {

		private File file;
		private Pixell pixell;

		/**
		 * 
		 * @param file
		 * @param pixell
		 */
		public PictureViewer(File file, Pixell pixell) {
			this.file = file;
			this.pixell = pixell;
		}

		@Override
		public void run() {
			try {
				@SuppressWarnings("resource")
				Picture p = new Picture(this.file.getPath());
				Color averageColor = Picture.getAverageColor(p.getBi());
				pixell.addPictureToMap(averageColor, p.getPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}