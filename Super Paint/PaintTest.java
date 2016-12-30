// Testing BorderLayoutFrame.
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class PaintTest
{
   public static void main( String args[] )
   { 
      DrawFrame paintFrame = new DrawFrame(); 
      paintFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      paintFrame.setSize( 1500, 1000 ); // set frame size
      paintFrame.setVisible( true ); // display frame
   } // end main
} // end class BorderLayoutDemo