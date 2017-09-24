package pl.quider.pixell;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JTextField;

/**
 * Class is used to read all elements in directory.
 *
 * @author akozlowski
 */
public class CatalogSniffer implements Callable<List<Picture>> {

    private File directory;

    /**
     * @param directory
     */
    public CatalogSniffer(File directory) throws NotDirectoryException {
        if (directory.isDirectory())
            this.directory = directory;
        else
            throw new NotDirectoryException(directory.getName());
    }


    @Override
    public List<Picture> call() throws Exception {
        LinkedList<Picture> queue = new LinkedList<Picture>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        File f = null;
        int files = 0;
        for (File element : f.listFiles()) {
            if (!element.isDirectory()) {
                String ext = element.getName().substring(element.getName().indexOf("."));
                if (!element.getName().endsWith(ext))
                    continue;
                files++;
            } else {
                Future<List<Picture>> submit = executorService.submit(() -> {
                    try {
                        CatalogSniffer catalogSniffer = new CatalogSniffer(element);
                        return catalogSniffer.call();
                    } catch (NotDirectoryException e) {
                        e.printStackTrace();
                        return new LinkedList<Picture>();
                    }
                });
                queue.addAll(submit.get());
            }
        }
        return queue;
    }
}