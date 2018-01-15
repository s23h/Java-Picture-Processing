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
                    out.setPixel((w-j-1), i, color);
                }
            }
        }

      if (c.equals("V")){
        for (int i=0; i<h; i++){
          for (int j=0; j<w; j++) {
            Color color = picture.getPixel(j, i);
            out.setPixel(j, (h-i-1), color);
          }
        }
      }

      picture = out;


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
        } else if (angle ==180) {
            rotate(90);
            rotate(90);
        } else if (angle ==270) {
            rotate(180);
            rotate(90);
        }

        picture = out;
    }

    public static void blend(Picture[] pics) {
      int w = smallestWidth(pics);
      int h = smallestHeight(pics);
      int n = pics.length;
      Picture out = Utils.createPicture(w, h);
      for (int i=0;i<w;i++) {
        for (int j=0;j<h;j++) {
          int red = 0;
          int green = 0;
          int blue = 0;
          for (int k=0; k<n; k++) {
            red += pics[k].getPixel(i, j).getRed();
            green += pics[k].getPixel(i, j).getGreen();
            blue += pics[k].getPixel(i, j).getBlue();
          }
          Color color = new Color(red/n ,green/n,blue/n);
          out.setPixel(i, j, color );
        }
      }

      picture = out;

    }

    private static int smallestWidth(Picture[] pics) {
      int w = 0;
      for (int i=0; i<pics.length;i++) {
        if (pics[i].getWidth() > 0) {
          w = pics[i].getWidth();
        }
      } return w;
    }

    private static int smallestHeight(Picture[] pics) {
      int h = 0;
      for (int i=0; i<pics.length;i++) {
        if (pics[i].getHeight() > 0) {
          h = pics[i].getHeight();
        }
      } return h;
  }

  public static void blur() {
    int h = picture.getHeight();
    int w = picture.getWidth();
    Picture out = picture;

    for (int i=1; i<h-1; i++) {
      for (int j=1; j<w-1; j++) {
        int red=0;
        int green=0;
        int blue=0;
        for (int k=i-1; k<i+2; k++) {
          for (int l=j-1; l<j+2;l++) {
            red += picture.getPixel(l, k).getRed();
            green+= picture.getPixel(l, k).getGreen();
            blue += picture.getPixel(l, k).getBlue();
          }
        }
        Color color = new Color(red/9, green/9, blue/9);
        out.setPixel(j, i, color);
      }
    }

    picture = out;




  }





}
