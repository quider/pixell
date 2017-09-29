package pl.quider.pixell.services;

import pl.quider.pixell.Register;
import pl.quider.pixell.eventArgs.ImageFileFoundEventArgs;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * Class is used to read all elements in directory.
 *
 * @author akozlowski
 */
public class CatalogSniffer implements Callable<List<File>> {

    private File directory;
    private List<Consumer<ImageFileFoundEventArgs>> imageFileFoundListeners = new ArrayList<>();

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
    public List<File> call() throws Exception {
        LinkedList<File> queue = new LinkedList<File>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int files = 0;
        for (File element : this.directory.listFiles()) {
            if (!element.isDirectory()) {
                String ext = element.getName().substring(element.getName().indexOf("."));
                String[] allowedExtensions = Register.getInstance().getProperty("catalog.sniffer.extensions", ".jpg,").split(",");
                if (!Arrays.stream(allowedExtensions).anyMatch(allowedExtension -> allowedExtension.equals(ext))) {
                    continue;
                }
                files++;
                queue.add(element);
                this.imageFileFoundListeners.stream().forEach(l -> l.accept(new ImageFileFoundEventArgs(element)));
            } else {
                Future<List<File>> submit = executorService.submit(() -> {
                    try {
                        CatalogSniffer catalogSniffer = new CatalogSniffer(element);
                        return catalogSniffer.call();
                    } catch (NotDirectoryException e) {
                        e.printStackTrace();
                        return new LinkedList<File>();
                    }
                });
                queue.addAll(submit.get());
            }
        }
        return queue;
    }

    public void addImageFoundListener(Consumer<ImageFileFoundEventArgs> imageFileFoundListener) {
        this.imageFileFoundListeners.add(imageFileFoundListener);
    }

    public void removeImageFoundListener(Consumer imageFileFoundListener) {
        this.imageFileFoundListeners.remove(imageFileFoundListener);
    }
}