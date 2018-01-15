package picture;

public class Main {

  public static void main(String[] args) {
   if (args[0].equals("invert")) {
       Process p = new Process();
       p.setPicture(args[1]);
       p.invert();
       p.save(args[2]);
   }

   if (args[0].equals("grayscale")) {
          Process p = new Process();
          p.setPicture(args[1]);
          p.grayscale();
          p.save(args[2]);
   }

   if (args[0].equals("rotate")) {
          Process p = new Process();
          p.setPicture(args[2]);
          p.rotate(Integer.parseInt(args[1]));
          p.save(args[3]);
   }


  }
}
