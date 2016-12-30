import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MyLine extends MyShape
{
    
    // constructor without input values
    public MyLine() {
        super(); //calls parent constructor
    }//End MyLine Constructor
    
    //constructor with input values
    public MyLine(int x1, int y1, int x2, int y2, Color myColor, Color secondColor, boolean ifGradient, float strokeLength, boolean isDashed, float dashLength) {
        super(x1,y1,x2,y2,myColor,secondColor,ifGradient,strokeLength,isDashed,dashLength); //calls parent constructor with parameters
    }//End MyLine Constructor
    
    // Actually draws the line
    public void draw( Graphics g )
    {
        //Draw Gradient shape
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(super.getStrokeLength()));
        //Set dash strokes
        if (super.getIsDashed()){
            float dashedLength = (float) (super.getDashLength());
            g2d.setStroke(new BasicStroke(super.getStrokeLength(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {dashedLength,dashedLength},0.0f));
        }
        if (super.getIsGradient()){
            
            Color startColor = super.getColor();
            Color endColor = super.getSecondColor();
            int startX = super.getXValue();
            int startY = super.getYValue();
            int endX = super.getXValueEnd();
            int endY = super.getYValueEnd();
            
            GradientPaint gradient = new GradientPaint(startX,startY,startColor,endX,endY,endColor);
            g2d.setPaint(gradient);
            g2d.drawLine(startX,startY,endX,endY);
        }
        else{
            g2d.setColor( super.getColor() ); //set color
            g2d.drawLine( super.getXValue(), super.getYValue(),super.getXValueEnd(), super.getYValueEnd() ); //draws line
        }
    } // end method draw
} // end class MyLine