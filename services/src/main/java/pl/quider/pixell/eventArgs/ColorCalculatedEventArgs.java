package pl.quider.pixell.eventArgs;

import pl.quider.pixell.model.Picture;

import java.awt.*;

public class ColorCalculatedEventArgs extends EventArgs {
    private Color calculatedColor;
    private Picture currentPicture;

    public ColorCalculatedEventArgs(Object sender, Color calculatedColor, Picture currentPicture) {
        super(sender);
        this.calculatedColor = calculatedColor;
        this.currentPicture = currentPicture;
    }

    public Color getCalculatedColor() {
        return calculatedColor;
    }

    public Picture getCurrentPicture() {
        return currentPicture;
    }
}
