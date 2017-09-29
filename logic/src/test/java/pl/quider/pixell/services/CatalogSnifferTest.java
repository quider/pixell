package pl.quider.pixell.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class CatalogSnifferTest {

    CatalogSniffer catalogSniffer;

    @Before
    public void init() {
        try {
            catalogSniffer = spy(new CatalogSniffer(new File("C:\\Users\\adria\\Pictures\\Camera Roll")));
        } catch (NotDirectoryException e) {
            fail("Catalog snifer creation failed");
        }
    }

    @Test
    public void call() throws Exception {
        final int[] count = {0};
        catalogSniffer.addImageFoundListener((e) -> {
            assertNotNull(e);
            assertNotNull(e.getFile());
            System.out.println(e.getFile().getName());
            count[0]++;
        });
        System.out.println(count[0]);
        List<File> call = catalogSniffer.call();
        assertEquals(count[0], call.size());
        assertNotNull(call);
    }
}