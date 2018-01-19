package picture;

public class Main {

  public static void main(String[] args) {
   if (args[0].equals("invert")) {
       Process p = new Process(args[1]);
       p.invert();
       p.save(args[2]);
   }

   if (args[0].equals("grayscale")) {
          Process p = new Process(args[1]);
          p.grayscale();
          p.save(args[2]);
   }

   if (args[0].equals("rotate")) {
          Process p = new Process(args[2]);
          p.rotate(Integer.parseInt(args[1]));
          p.save(args[3]);
   }

   if (args[0].equals("flip")) {
      Process p = new Process(args[2]);
      p.flip(args[1]);
      p.save(args[3]);
    }


    if (args[0].equals("blend")) {
      Process p = new Process();
      Picture[] pics = new Picture[args.length-2];
      for (int i=0; i<args.length-2; i++) {
        pics[i] = Utils.loadPicture(args[i+1]);
      }
      p.blend(pics);
      p.save(args[args.length-1]);
    }

    if (args[0].equals("blur")) {
      Process p = new Process(args[1]);
      p.blur();
      p.save(args[2]);
    }

    if (args[0].equals("mosaic")) {
      Process p = new Process();
      Picture[] pics = new Picture[args.length-2];
      for (int i=0; i<args.length-2; i++) {
        pics[i] = Utils.loadPicture(args[i+2]);
      }
      p.mosaic(pics, Integer.parseInt(args[1]));
      p.save(args[args.length-1]);
    }

    // expected input pattern : "fade" 0.5 picLocation picLocation saveLocation
    // the weight determines the the balance in colour between the 1st and 2nd pic
    // A high value favours the 1st pic
    if (args[0].equals("fade")) {
     assert (args.length==5) : "Can only fade 2 pictures";
      Process p = new Process();
      Picture[] pics = new Picture[args.length-2];
      for (int i=0; i<args.length-2; i++) {
        pics[i] = Utils.loadPicture(args[i+2]);
      }
      p.fade(pics, Float.parseFloat(args[1]));
      p.save(args[args.length-1]);
    }











  }
}
