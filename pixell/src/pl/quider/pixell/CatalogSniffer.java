package pl.quider.pixell;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
	private LinkedList<File> queue;

	/**
	 * 
	 * @param pixell
	 * @param selectedFile
	 */
	public CatalogSniffer(Pixell pixell, File selectedFile) {
		this.parent = pixell;
		this.directory = selectedFile;
		queue = new LinkedList<File>();
		queue.add(directory);
	}

	@Override
	public void run() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		File f = null;
		while ((f = queue.removeFirst()) != null) {
			for (File element : f.listFiles()) {
				if (!element.isDirectory()) {
					String ext = element.getName().substring(element.getName().indexOf("."));
					if (!element.getName().endsWith(ext))
						continue;
					Runnable command = new PictureViewer(element, parent);
					executor.execute(command);
				} else {
					queue.add(element);
				}
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