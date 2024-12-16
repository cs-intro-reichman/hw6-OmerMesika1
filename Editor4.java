import java.awt.Color;

public class Editor4 {
    public static void main (String[] args) {
        // String source = args[0];
        // Color[][] c1 = Runigram.read(source);
        // int steps = Integer.parseInt(args[1]);
        // Runigram.morph(c1, Runigram.grayScaled(c1), steps);
        //
        String source = "ironman.ppm";
        Color[][] c1 = Runigram.read(source);
        int steps = 100;
        Runigram.morph(c1, Runigram.grayScaled(c1), steps);
    }
}
