package picture;

public class Process {

  private static Picture picture;

  Process(String file) {
    picture = Utils.loadPicture(file);
  }

  public Process() {
    this.picture = null;
  }

  static void save(String s) {
    Utils.savePicture(picture, s);
  }

  static void invert() {
    int h = picture.getHeight();
    int w = picture.getWidth();
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        Color color = picture.getPixel(i, j);
        color.setRed(255 - color.getRed());
        color.setBlue(255 - color.getBlue());
        color.setGreen(255 - color.getGreen());
        picture.setPixel(i, j, color);
      }
    }

  }

  static void grayscale() {
    int h = picture.getHeight();
    int w = picture.getWidth();
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        Color color = picture.getPixel(i, j);
        int avg = (color.getRed() + color.getBlue() + color.getGreen()) / 3;
        color.setRed(avg);
        color.setBlue(avg);
        color.setGreen(avg);
        picture.setPixel(i, j, color);
      }
    }

  }

  static void flip(String c) {
    int h = picture.getHeight();
    int w = picture.getWidth();
    Picture out = Utils.createPicture(w, h);
    if (c.equals("H")) {
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          Color color = picture.getPixel(i, j);
          out.setPixel((w - i - 1), j, color);
        }
      }
    }

    if (c.equals("V")) {
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          Color color = picture.getPixel(i, j);
          out.setPixel(i, (h - j - 1), color);
        }
      }
    }

    picture = out;


  }

  static void rotate(int angle) {
    int h = picture.getHeight();
    int w = picture.getWidth();
    Picture out = Utils.createPicture(h, w);
    if (angle == 90) {
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          Color color = picture.getPixel(i, j);
          out.setPixel((h - 1 - j), i, color);
        }
      }

      picture = out;

    } else if (angle == 180) {
      rotate(90);
      rotate(90);
    } else if (angle == 270) {
      rotate(180);
      rotate(90);
    }

  }

  static void blend(Picture[] pics) {
    int w = smallestWidth(pics);
    int h = smallestHeight(pics);
    int n = pics.length;
    picture = Utils.createPicture(w, h);
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        int red = 0, green = 0, blue = 0;
        for (int k = 0; k < n; k++) {
          red += pics[k].getPixel(i, j).getRed();
          green += pics[k].getPixel(i, j).getGreen();
          blue += pics[k].getPixel(i, j).getBlue();
        }
        Color color = new Color(red / n, green / n, blue / n);
        picture.setPixel(i, j, color);
      }
    }

  }

  static int smallestWidth(Picture[] pics) {
    int w = 0;
    for (int i = 0; i < pics.length; i++) {
      if (pics[i].getWidth() > w) {
        w = pics[i].getWidth();
      }
    }
    return w;
  }

  static int smallestHeight(Picture[] pics) {
    int h = 0;
    for (int i = 0; i < pics.length; i++) {
      if (pics[i].getHeight() > h) {
        h = pics[i].getHeight();
      }
    }
    return h;
  }

  static void blur() {
    int h = picture.getHeight();
    int w = picture.getWidth();
    Picture out = Utils.createPicture(w, h);

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {

        if ((i == 0 || i == h - 1) || (j == 0 || j == w - 1)) {
          out.setPixel(j, i, picture.getPixel(j, i));
        } else {

          int red = 0, green = 0, blue = 0;

          for (int k = i - 1; k < i + 2; k++) {
            for (int l = j - 1; l < j + 2; l++) {
              red += picture.getPixel(l, k).getRed();
              green += picture.getPixel(l, k).getGreen();
              blue += picture.getPixel(l, k).getBlue();
            }
          }

          Color color = new Color(red / 9, green / 9, blue / 9);
          out.setPixel(j, i, color);
        }
      }
    }
    picture = out;
  }

  static void mosaic(Picture[] pics, int tileSize) {
    int w = smallestWidth(pics) - (smallestWidth(pics) % tileSize);
    int h = smallestHeight(pics) - (smallestHeight(pics) % tileSize);
    int k = 0, d = 1;
    picture = Utils.createPicture(w, h);
    for (int x = 0; x < w; x += tileSize) {
      for (int y = 0; y < h; y += tileSize) {
        for (int i = 0; i < tileSize; i++) {
          for (int j = 0; j < tileSize; j++) {
            picture.setPixel(x + i, y + j, pics[k].getPixel(x + i, y + j));
          }
        }

        k += d;

        if (k == (pics.length - 1)) {
          d = -1;
        } else if (k == 0) {
          d = 1;
        }

      }

      k += d;

      if (k == (pics.length - 1)) {
        d = -1;
      } else if (k == 0) {
        d = 1;
      }

    }

  }

  static void fade(Picture[] pics, float weight) {
    int w = smallestWidth(pics)  ;
    int h = smallestHeight(pics) ;
    Picture out = Utils.createPicture(w, h);
    for (int i=0; i<w; i++) {
      for (int j=0; j<h; j++) {
        Color c1 = pics[0].getPixel(i, j);
        Color c2 = pics[1].getPixel(i, j);
        int red = (int) (c1.getRed()*weight+c2.getRed()*(1-weight));
        int green = (int) (c1.getGreen()*weight+c2.getGreen()*(1-weight));
        int blue = (int) (c1.getBlue()*weight+c2.getBlue()*(1-weight));
        Color color =  new Color(red, green, blue);
        out.setPixel(i,j, color);
      }

    }

    picture = out;

  }





}
