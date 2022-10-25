package StegStudentCode;

import java.awt.*;
import java.util.ArrayList;

public class Steganography {

    public static void main(String[] args) {
        /*To test clearLow() */
        Picture beach= new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/beach.jpg");
//        beach.explore();
//        Picture copy= testClearLow(beach);
//        copy.explore();

        /*To test setLow() */
        Picture beach2= new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/beach.jpg");
//        beach2.explore();
        Picture copy2= testSetLow(beach2, Color.PINK);
//        copy2.explore();

        /*To test revealPicture() */
        Picture copy3= revealPicture(copy2);
//        copy3.explore();

//        Picture source = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/flower1.jpg");
//        Picture secret = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/flower2.jpg");
//        if(canHide(source, secret)) {
//            Picture hidden = hidePicture(source, secret);
//            Picture revealed = revealPicture(hidden);
//            source.explore();
//            secret.explore();
//            hidden.explore();
//            revealed.explore();
//        }

//        Picture robot = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/robot.jpg");
//        Picture flower1 = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/flower1.jpg");
//        beach.explore();
//
//        Picture hidden1= hidePicture(beach, robot, 65, 208);
//        Picture hidden2= hidePicture(hidden1, flower1, 280, 110);
//        hidden2.explore();
//
//        Picture unhidden= revealPicture(hidden2);
//        unhidden.explore();

//        Picture swan = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/swan.jpg");
//        Picture swan2 = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/swan.jpg");
//        System.out.println("Swan and swan2 are the same: " +
//                isSame(swan, swan2));
//        swan = testClearLow(swan);
//        System.out.println("Swan and swan2 are the same (after clearLow run on swan): "
//                + isSame(swan, swan2));

//        Picture arch = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/arch.jpg");
//        Picture koala = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/koala.jpg") ;
//        Picture robot1 = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/robot.jpg");
//        Picture arch2 = hidePicture(arch, robot1, 65, 102);
//        ArrayList<Point> pointList = findDifferences(arch, arch2) ;
//        System.out.println("PointList after comparing two identical s pictures " +
//                "has a size of " + pointList.size());
//        pointList = findDifferences(arch, koala);
//        System.out.println("PointList after comparing two different sized pictur t es " +
//                "has a size of " + pointList.size());
//        pointList = findDifferences(arch, arch2);
//        System.out.println("Pointlist after hiding a picture has a siz m e of"
//                + pointList.size());
//        arch.show();
//        arch2.show();

        Picture hall = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/femaleLionAndHall.jpg");
        Picture robot2 = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/robot.jpg");
        Picture flower2 = new Picture("/home/jahnavi/AP Com Sci A/AP Labs/Picture_Lab/src/StegStudentCode/flower1.jpg");
        Picture hall2 = hidePicture(hall, robot2, 50, 300);
        Picture hall3 = hidePicture(hall2, flower2, 115, 275);
        hall3.explore();
        if(!isSame(hall, hall3))
        {
            Picture hall4 = showDifferentArea(hall, findDifferences(hall, hall3));
            hall4.show();
            Picture unhiddenHall3 = revealPicture(hall3);
            unhiddenHall3.show();
        }
    }

    /**
     * clears the lower (rightmost) to bits in a pixel.
     * @param p pixel to alter
     */
    public static void clearLow (Pixel p) {
        Color c= p.getColor(); // take rgb from the *color* of the pixel, not from the pixel itself
        int r= (c.getRed() / 4) * 4;
        int g= (c.getGreen() / 4) * 4;
        int b= (c.getBlue() / 4) * 4;

//        p.setRed(r);      this stuff is weird b/c you are not working with the color's rgb but rather with the pixel's rgb
//        p.setGreen(g);
//        p.setBlue(b);

        Color color= new Color(r, g, b);
        p.setColor(color);
    }

    public static Picture testClearLow(Picture p) {
        Picture p2= new Picture(p);
        for (int y = 0; y < p2.getHeight(); y++) {
            for (int x = 0; x < p2.getWidth(); x++) {
                Pixel pix= p2.getPixel(x, y);
                clearLow(pix);
            }
        }

        /*
        for (Pixel[] pixels : p2.getPixels2D() )
            for (Pixel pixel : pixel)
                cleaLow(pixel);
         */

        return p2;
    }

    public static void setLow(Pixel p, Color c) {
        clearLow(p);
        Color col= p.getColor(); // just do the same thing from clearLow() here too. get the rgb from the *color* of the pixel, not from the pixel itself
        int red= col.getRed() + (c.getRed() / 64);
        int green= col.getGreen() + (c.getGreen() / 64);
        int blue= col.getBlue() + (c.getBlue() / 64);

//        int red2= c.getRed() / 4 * 4;
//        int green2= c.getGreen() / 4 * 4;
//        int blue2= c.getBlue() / 4 * 4;
//
//
//        p.setRed(red2 + red);
//        p.setGreen(green2 + green);
//        p.setBlue(blue2 + blue);

//        p.setRed(p.getRed() + red);
//        p.setGreen(p.getGreen() + green);
//        p.setBlue(p.getBlue() + blue);

        p.setColor(new Color(red, green, blue)); // again, don't access the pixel's re, green and blue, but just create a new color for it
    }

    public static Picture testSetLow(Picture p, Color c) {
        Picture newPic= new Picture(p);
        Pixel[][] pixels= newPic.getPixels2D();
        for(int i= 0; i < pixels.length; i++){
            for (int j = 0; j < pixels[i].length; j++) {

                setLow(pixels[i][j], c);
            }
        }

        return newPic;
    }

    /**
     * Sets highest two bits of each pixel's colors
     * to the lowest two bits of each pixel's colors
     * @param hidden hidden picture to conceal
     * @return concealed picture??
     */
    public static Picture revealPicture(Picture hidden) {
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for (int r = 0; r < pixels.length; r++ ) {
            for (int c= 0; c < pixels[r].length; c++) {
                Color col= source[r][c].getColor();
                Pixel p= pixels[r][c];
                int red= col.getRed() % 4 * 64;
                int green= col.getGreen() % 4 * 64;
                int blue= col.getBlue() % 4 * 64;

                p.setColor(new Color(red, green, blue));
            }
        }

        return copy;
    }

    /**
     * Determines whether secret can be hidden in source, which is
     * true if source and secret are the same dimensions
     * @param source picture to hide secret in, cannot be null
     * @param secret picture to hide, cannot be null
     * @return if you can hide secret in source
     */
    public static boolean canHide(Picture source, Picture secret) {
        return source.getHeight() >= secret.getHeight() && source.getWidth() >= secret.getWidth();
    }

    public static Picture hidePicture(Picture source, Picture secret) {
        Picture newPic= new Picture(source);
        Pixel[][] pixels= newPic.getPixels2D();
        for(int i= 0; i < pixels.length; i++){
            for (int j = 0; j < pixels[i].length; j++) {
                Color secretC= secret.getPixel(j, i).getColor();
                setLow(pixels[i][j], secretC);
            }
        }

        return newPic;
    }

    public static Picture hidePicture(Picture source, Picture secret, int startRow, int startCol) {
        if(!canHide(source, secret)) {
            return new Picture();
        }
        Picture newPic= new Picture(source);
        Pixel[][] pixels= newPic.getPixels2D();
        Pixel[][] pixels2= secret.getPixels2D();
        for(int i= 0; i < pixels2.length; i++){
            for (int j= 0; j < pixels2[i].length; j++) {
                Color secretC= pixels2[i][j].getColor();
                setLow(pixels[i+startRow][j+startCol], secretC);
            }
        }
        return newPic;
    }

    public static boolean isSame (Picture p1, Picture p2) {
        if(p1.getWidth() != p2.getWidth() || p1.getHeight() != p2.getHeight()){
            return false;
        }

        Pixel[][] pix1= p1.getPixels2D();
        Pixel[][] pix2= p2.getPixels2D();

        boolean hasDiff= false;
        for (int i = 0; i < p1.getHeight(); i++) {
            for (int j = 0; j < p1.getWidth(); j++) {
                if(!pix1[i][j].getColor().equals(pix2[i][j].getColor())){
                    hasDiff= true;
                }
            }
        }

        return !hasDiff;
//        return p1.equals(p2);
    }

    public static ArrayList<Point> findDifferences(Picture p1,Picture p2) {
        ArrayList<Point> differences= new ArrayList<>();
        Pixel[][] pix1= p1.getPixels2D();
        Pixel[][] pix2= p2.getPixels2D();
        if(p1.getHeight() != p2.getHeight() || p1.getWidth() != p2.getWidth()) {
            return differences;
        }
        for(int row= 0; row < pix1.length; row++) {
            for (int col = 0; col < pix1[row].length; col++) {
                if(!pix1[row][col].getColor().equals(pix2[row][col].getColor())){
                    differences.add(new Point(row,col));
                }
            }
        }

        return differences;
    }

    public static Picture showDifferentArea (Picture a, ArrayList<Point> differences) {
        Picture copy= new Picture(a);
        Pixel[][] pixels = copy.getPixels2D();
        Point topLeft= new Point(differences.get(0));
        Point bottomRight= new Point(differences.get(differences.size()-1));

        int x1= (int)topLeft.getX();
        int y1= (int)topLeft.getY();
        int x2= (int)bottomRight.getX();
        int y2= (int) bottomRight.getY();

        for(int i= y1; i < y2; i++) {
            pixels[x1][i].setColor(Color.RED);
            pixels[x2][i].setColor(Color.RED);
        }

        for(int i= x1; i < x2; i++) {
            pixels[i][y1].setColor(Color.RED);
            pixels[i][y2].setColor(Color.RED);
        }


        return copy;
    }
}
