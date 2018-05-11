package cs1302.p2;

import cs1302.effects.Artsy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * This class implements the Artsy interface and uses its methods to apply effects to Images.
 */

public class MyArtsy implements Artsy {
/**
	 * Given two Images, this method returns a new Image, the contents of which
	 * is composed of the source images alternating horizontally and vertically at the specified 
	 * size, in pixels. This causes the images to appear woven together in a checkered fashion. 
	 * 
	 * @param src1 the first Image
	 * @param src2 the second Image
	 * @param size is the , in pixels, of the stripes that alternate.
	 * @return a Image with the horizontal stripes effect (variable one)
	 */

    @Override
    public Image doCheckers(Image src1, Image src2, int size) { //doCheckers
        int inc = size; //variables
        int inc2 = size;
        int numOfSquares = (int) Math.ceil(300.0/size);
        int width1 = (int) src1.getWidth();
        int height1 = (int) src1.getHeight();
        WritableImage one = new WritableImage(width1, height1);
        PixelReader pr1 = src1.getPixelReader();
        PixelReader pr2 = src2.getPixelReader();
        PixelWriter pw = one.getPixelWriter();
        int startingPoint = 0;
        int startingPoint2 = 0;

        for (int x = 0; x < numOfSquares; x++) { //for loop to read through the images and write to the new image
            for (int y = 0; y < numOfSquares; y++) {
                if (x%2 == 0 && y%2 == 0) {
                    for (int i = startingPoint; i < inc; i++) {
                        for (int l = startingPoint2; l < inc2; l++) {
                            pw.setArgb(i, l, pr1.getArgb(i, l));
                        }
                    }
                }
                else if (x%2 != 0 && y%2 == 0) {
                    for (int i = startingPoint; i < inc; i++) {
                        for (int l = startingPoint2; l < inc2; l++) {
                            pw.setArgb(i, l, pr2.getArgb(i, l));
                        }
                    }
                }
                else if (x%2 == 0 && y%2 != 0 ) {
                    for (int i = startingPoint; i < inc; i++) {
                        for (int l = startingPoint2; l < inc2; l++) {
                            pw.setArgb(i, l, pr2.getArgb(i, l));
                        }
                    }
                }
                else {
                    for (int i = startingPoint; i < inc; i++) {
                        for (int l = startingPoint2; l < inc2; l++) {
                            pw.setArgb(i, l, pr1.getArgb(i, l));
                        }
                    }
                }               
                
                startingPoint = startingPoint + size; 
                inc = inc + size; 
            }
            startingPoint = 0;
            inc = size;
            startingPoint2 = startingPoint2 + size;
            inc2 = inc2 +size;
        }
        return one; //return to driver
    } // doCheckers
    
    /**
	 * Given two Images, this method returns a new Image, the contents of which
	 * is composed of the source images alternating horizontally at the specified pixel height. 
	 * Conditions are checked to see which picture should be used and where it should be written on the new image
	 * @param src1 the first Image
	 * @param src2 the second Image
	 * @param height the height, in pixels, of the horizontal stripes.
	 * @return a Image with the horizontal stripes effect (variable one)
	 */

    @Override
    public Image doHorizontalStripes(Image src1, Image src2, int height) { //doHorizontalStripes
      int pixelWidth = height;  //variables
      int inc = pixelWidth;
      int numOfStripes = (int) Math.ceil(300.0/pixelWidth);
      int num = 0;
      int width1 = (int) src1.getWidth();
      int height1 = (int) src1.getHeight();
      WritableImage one = new WritableImage(width1, height1);
      PixelReader pr1 = src1.getPixelReader();
      PixelReader pr2 = src2.getPixelReader();
      PixelWriter pw = one.getPixelWriter();
      int startingPoint = 0;
      
      for(int n = 0; n < numOfStripes; ++n){  //for loop that reads through both images and writes to new image
        if( (n%2) != 0 && n!= (numOfStripes-1)){
          for(int x=0; x<width1; ++x){
            for(int y=startingPoint; y<inc; ++y){
              int argb = pr1.getArgb(x,y);
              pw.setArgb(x,y,argb); 
            }
          }
        }
        if((n%2) == 0 && n!= (numOfStripes-1)){   
          for(int i=0; i<width1; ++i){
            for(int l=startingPoint; l<inc; ++l){
              int argb1 = pr2.getArgb(i,l);
              pw.setArgb(i,l,argb1);
            }
          }
        }
        if((n%2) != 0 && n == (numOfStripes-1)){
          for(int a=0; a<width1; ++a){
            for(int b=startingPoint; b<height1; ++b){
              int argb3 = pr1.getArgb(a,b);
              pw.setArgb(a,b,argb3); 
            }
          }
       }
       if((n%2) == 0 && n == (numOfStripes-1)){
        for(int c=0; c<width1; ++c){
            for(int d=startingPoint; d<height1; ++d){
              int argb4 = pr2.getArgb(c,d);
              pw.setArgb(c,d,argb4);
            }
          }
        }
      startingPoint = startingPoint + pixelWidth;
      inc = inc + pixelWidth;
      }       
	    return one; //return to Driver
    } // doHorizontalStripes
    /**
	 * Given a Image, this method returns a new Image which contains
	 * the source image rotated by a certain number of degrees entered by the user. 
   * 
 	 * @param src     the source Image
	 * @param degrees the degrees to rotate the image, in degrees (not radians)
	 * @return a new Image containing a rotated version of the source image (varibale rotatedImage)
	 */

    @Override
    public Image doRotate(Image src, double degrees) { //doRotate
        ImageView iv = new ImageView(); //varibles
        iv.setImage(src);
        iv.setFitWidth(300);
        iv.setFitHeight(300);
        iv.setRotate(degrees);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Rectangle2D r2d = new Rectangle2D(0,0,300,300);
        params.setViewport(r2d);
        Image rotatedImage = iv.snapshot(params, null);
        return rotatedImage; //return to Driver
    } // doRotate

/**
	 * Given two Images, this method returns a new Image, the contents of which
	 * is composed of the source images alternating vertically at the specified pixel width.
	 * 
	 * @param src1 the first Image
	 * @param src2 the second Image
	 * @param width the width, in pixels, of the vertical stripes.
	 * @return a Image with the vertical stripes effect (variable one)
	 */
   
   
    @Override
    public Image doVerticalStripes(Image src1, Image src2, int width) { //doVerticalStripes
        int inc = width; //variables
        int numOfStripes = (int) Math.ceil(300.0/width);
        int width1 = (int) src1.getWidth();
        int height1 = (int) src1.getHeight();
        WritableImage one = new WritableImage(width1, height1);
        PixelReader pr1 = src1.getPixelReader();
        PixelReader pr2 = src2.getPixelReader();
        PixelWriter pw = one.getPixelWriter();
        int startingPoint = 0;

        for(int n = 0; n < numOfStripes; ++n){ //for loop that reads through both images and writes to new image
            if( (n%2) != 0 && n!= (numOfStripes-1)){
              for(int x=0; x<width1; ++x){
                for(int y=startingPoint; y<inc; ++y){
                  int argb = pr1.getArgb(y,x);
                  pw.setArgb(y,x,argb); 
                }
              }
            }
            if((n%2) == 0 && n!= (numOfStripes-1)){   
              for(int i=0; i<width1; ++i){
                for(int l=startingPoint; l<inc; ++l){
                  int argb1 = pr2.getArgb(l,i);
                  pw.setArgb(l,i,argb1);
                }
              }
            }
            if((n%2) != 0 && n == (numOfStripes-1)){
              for(int a=0; a<width1; ++a){
                for(int b=startingPoint; b<height1; ++b){
                  int argb3 = pr1.getArgb(b,a);
                  pw.setArgb(b,a,argb3); 
                }
              }
           }
           if((n%2) == 0 && n == (numOfStripes-1)){
            for(int c=0; c<width1; ++c){
                for(int d=startingPoint; d<height1; ++d){
                  int argb4 = pr2.getArgb(d,c);
                  pw.setArgb(d,c,argb4);
                }
              }
            }
          startingPoint = startingPoint + width;
          inc = inc + width;
        }       
        return one; //return to Driver
    } // doVerticalStripes

} // MyArtsy


