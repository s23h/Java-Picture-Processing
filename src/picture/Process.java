package picture;

public class Process {
    private static Picture picture;

    public static void setPicture(String load) {
        picture = Utils.loadPicture(load);
    }

    public static void save(String s) {
        Utils.savePicture(picture, s);
    }

    public static void invert() {
        int h = picture.getHeight();
        int w = picture.getWidth();
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++) {
                Color color = picture.getPixel(j, i);
                color.setRed(255-color.getRed());
                color.setBlue(255-color.getBlue());
                color.setGreen(255-color.getGreen());
                picture.setPixel(j, i, color);
            }
        }

    }

    public static void grayscale() {
        int h = picture.getHeight();
        int w = picture.getWidth();
        for (int i=0; i<h; i++) {
            for (int j = 0; j < w; j++) {
                Color color = picture.getPixel(j, i);
                int avg = (color.getRed() + color.getBlue() + color.getGreen()) / 3;
                color.setRed(avg);
                color.setBlue(avg);
                color.setGreen(avg);
                picture.setPixel(j, i, color);
            }
        }

    }

    public static void flip (String c) {
        int h = picture.getHeight();
        int w = picture.getWidth();
        Picture out = Utils.createPicture(w, h);
        if (c.equals("H")){
            for (int i=0; i<h; i++){
                for (int j=0; j<w; j++) {
                    Color color = picture.getPixel(j, i);
                    out.setPixel((w-j), i, color);
                }
            }
        }
    }

    public static void rotate(int angle) {
        int h = picture.getHeight();
        int w = picture.getWidth();
        Picture out = Utils.createPicture(h, w);
        if (angle ==90) {
            for (int i=0; i<h; i++) {
                for (int j = 0; j < w; j++) {
                    Color color = picture.getPixel(j, i);
                    out.setPixel((h - 1 - i), j, color);
                }
            }
        }

        if (angle ==180) {
            for (int i=0; i<h; i++) {
                for (int j = 0; j < w; j++) {
                    Color color = picture.getPixel(j, i);
                    out.setPixel((h - 1 - i), j, color);
                }
            }
        }




        picture = out;
    }





}
