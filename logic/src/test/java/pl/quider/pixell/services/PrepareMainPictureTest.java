package pl.quider.pixell.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.quider.pixell.model.MainPicture;

@RunWith(MockitoJUnitRunner.class)
public class PrepareMainPictureTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void call() throws Exception {
        MainPicture mainPicture = new MainPicture("C:/Users/adria/Pictures/Camera Roll/20170708_163020.jpg");
        PrepareMainPicture prepareMainPicture = new PrepareMainPicture(mainPicture);
        MainPicture call = prepareMainPicture.call();

    }
}