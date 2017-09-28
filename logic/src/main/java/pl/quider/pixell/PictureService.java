package pl.quider.pixell;

import pl.quider.pixell.eventArgs.ColorFoundEventArgs;
import pl.quider.pixell.eventArgs.ImageMatchToColorEventArgs;
import pl.quider.pixell.events.OnColorFoundEvent;
import pl.quider.pixell.events.OnImageMatchToColorEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PictureService implements OnColorFoundEvent, OnImageMatchToColorEvent {
    public List<Consumer<ColorFoundEventArgs>> colorFoundListeners;
    private List<Consumer<ImageMatchToColorEventArgs>> imageMatchToColorListeners;

    public MosaicColorPicture findColorMap(MainPicture mainPicture){
        return null;
    }

    /**
     * Method search images which can match average color passed in argument.
     * finds color on mosaic
     * @param c
     */
    public List<Picture> findTileColorOnMosaic(Color c, MosaicColorPicture colorPicture){
        ArrayList<Point> result = new ArrayList<Point>();
//        Set<Map.Entry<Point,Color>> entrySet = colorMap.entrySet();
//        Iterator<Map.Entry<Point, Color>> iterator = entrySet.iterator();
//        while(iterator.hasNext()){
//            Map.Entry<Point, Color> next = iterator.next();
//            if(next.getValue().equals(c) || next.getValue().equals(c.brighter()) || next.getValue().equals(c.darker())){
//                result.add(next.getKey());
//                return result;
//            } else {
//                Color value = next.getValue();
//                int blue = c.getBlue() - value.getBlue();
//                int red = c.getRed() - value.getRed();
//                int green = c.getGreen() - value.getGreen();
//                if (-COLORDT > blue || blue > COLORDT){
//                    continue;
//                }
//                if(-COLORDT > red || red > COLORDT){
//                    continue;
//                }
//                if (-COLORDT > green || green > COLORDT){
//                    continue;
//                }
//                Point key = next.getKey();
//                result.add(key);
//				colorMap.remove(key);
//            }
//        }
//        for (Point point : result) {
//            colorMap.remove(point);
//        }
//        return result;
        return null;
    }

    /**
     * This method is used to calculate average color each of little square on big picture
     * @author akozlowski
     *
     */
    protected Boolean countTileAverageImage( BufferedImage bi) throws Exception {
        int h = bi.getHeight();
        int w = bi.getWidth();
        float lenFactor = 0.23f;//todo: zmienic to
        int wMini = (int) (w * lenFactor); // szerokosc miniaturowego zdjecia
        int hMini = h / (h / wMini);// wysokosc miniturowego zdjecia

        int x = 0;
        int y = 0;
        while (y < h && y + hMini < bi.getHeight()) {
            while (x < w && (x + wMini) < bi.getWidth()) {
                BufferedImage image = bi.getSubimage(x, y, wMini, hMini);
//                Color color = Picture.getAverageColor(image);
                Graphics2D graphics = (Graphics2D) image.getGraphics();
//                graphics.setColor(color);
                colorFoundListeners.parallelStream().forEach(listener->listener.accept((new ColorFoundEventArgs())));
//                colorMap.put(new Point(x,y, wMini, hMini), color);
                graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
                x += wMini;
            }
            x=0;
            y+=hMini;
        }
        return true;
    }

    @Override
    public void addColorFoundListener(Consumer<ColorFoundEventArgs> consumer) {
        this.colorFoundListeners.add(consumer);
    }

    @Override
    public void removeColorFoundListener(Consumer<ColorFoundEventArgs> consumer) {
        this.colorFoundListeners.remove(consumer);
    }

    @Override
    public void addMatchToColorListner(Consumer<ImageMatchToColorEventArgs> consumer) {
        this.imageMatchToColorListeners.add(consumer);
    }

    @Override
    public void removeMatchToColorListener(Consumer<ImageMatchToColorEventArgs> consumer) {
        this.imageMatchToColorListeners.remove(consumer);
    }

}
