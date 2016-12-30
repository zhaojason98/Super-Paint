import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MyOval extends MyBoundedShape
{
    
    // constructor without input values
    public MyOval() {
        super(); //calls parent constructor
    }//End MyOval constructor
    
    // constructor with input values
    public MyOval( int x1, int y1, int x2, int y2, Color color,Color secondColor,boolean ifGradient,boolean isFilled,float strokeLength,boolean isDashed,float dashLength )
    {
        super(x1,y1,x2,y2,color,secondColor,ifGradient,isFilled,strokeLength,isDashed,dashLength); //calls parent constructor with specified parameters
    } // end MyLine constructor    
    
    // Draws the oval
    public void draw( Graphics g )
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(super.getStrokeLength()));
        //Set dashed strokes
        if (super.getIsDashed()){
            float dashedLength = (float) (super.getDashLength());
            g2d.setStroke(new BasicStroke(super.getStrokeLength(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {dashedLength,dashedLength},0.0f));
        }
        //Draw Gradient shape
        if (super.getIsGradient()){
            //Set oval variables
            Color startColor = super.getColor();
            Color endColor = super.getSecondColor();
            int startX = super.getXValue();
            int startY = super.getYValue();
            int endX = super.getXValueEnd();
            int endY = super.getYValueEnd();
            //draw shape
            GradientPaint gradient = new GradientPaint(startX,startY,startColor,endX,endY,endColor);
            g2d.setPaint(gradient);
            g2d.drawOval(super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()));
            if (super.getIsFilled()){
                g2d.fillOval(super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()));
            }
        }
        //Draw non-gradient shape
        else{
            g.setColor( super.getColor() ); //set color
            g.drawOval(super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue())); //draw oval
            //fill oval
            if (super.getIsFilled()){
                g.fillOval(super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()));
            }
        }
    } // end method draw
} // end class MyLine