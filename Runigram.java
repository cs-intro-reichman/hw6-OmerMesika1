import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				image[i][j] = new Color (in.readInt(), in.readInt(), in.readInt());
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for(int i =0;i<image.length;i++) {
			for(int j=0;j<image[0].length;j++) {
				print(image[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		if (image == null || image.length == 0 || image[0].length == 0) return new Color[0][0];
		int width = image[0].length;
		int height = image.length;
		Color[][] horiki = new Color[height][width];
		for(int i=0;i<height;i++) {
			for(int j= 0; j<width;j++) {
				horiki[i][width-j-1] = image[i][j];
			}
		}
		return horiki;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		if (image == null || image.length == 0 || image[0].length == 0) return new Color[0][0];
		int width = image[0].length;
		int height = image.length;
		Color[][] vet = new Color[height][width];
		for(int i=0;i<height;i++) {
			for(int j= 0; j<width;j++) {
				vet[height-1-i][j] = image[i][j];
			}
		}
		return vet;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		double red = pixel.getRed()*0.299;
		double green = pixel.getGreen() * 0.587;
		double blue = pixel.getBlue()*0.114;
		int lum = (int) (red + green + blue);
		Color luma = new Color(lum,lum,lum);
		return luma;	
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int width = image[0].length;
		int height = image.length;
		Color[][] gr = new Color[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				gr[i][j] = luminance(image[i][j]);
			}
		}
		return gr;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		Color[][] sc = new Color[height][width];
		double wRatio = (double) image[0].length/width;
		double hRatio = (double) image.length/height;
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				sc[i][j] = image[(int)(i*hRatio)][(int)(j*wRatio)];
			}
		}
		return sc;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int red = (int) ((c1.getRed() * alpha) + ((1-(alpha))*c2.getRed()));
		int green = (int) ((c1.getGreen() * alpha) + ((1-alpha)*c2.getGreen()));
		int blue = (int) ((c1.getBlue() * alpha) + ((1-alpha)*c2.getBlue()));
		return new Color(red,green,blue);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		int width = image1[0].length;
		int height = image1.length;
		Color[][] bl = new Color[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				Color ch1 = image1[i][j];
				Color ch2 = image2[i][j];
				bl[i][j] = blend(ch1, ch2, alpha);
			}
		}
		return bl;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int width = source[0].length;
		int height = source.length;
		target = scaled(target, width, height);
		setCanvas(source);
		int epsilon = 0;
		int steps = 100/n;
		while(epsilon<100) {
			display(blend(target, source, epsilon));
			epsilon+=steps;
		}
		display(target);
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

