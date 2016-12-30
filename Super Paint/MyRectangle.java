import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

public class MyRectangle extends MyBoundedShape
{
    
    // constructor without input values
    public MyRectangle() {
        super(); //calls parent constructor
    }//End MyRectangle Constructor
    
    //construcctor with input values
    public MyRectangle( int x1, int y1, int x2, int y2, Color color,Color secondColor, boolean isGradient,boolean isFilled,float strokeLength, boolean isDashed,float dashLength )
    {
        super(x1,y1,x2,y2,color,secondColor,isGradient,isFilled,strokeLength,isDashed,dashLength); //calls parent constructor with values
    } // end MyRectrangle Constructor
    
    // Draws the rectangle
    public void draw( Graphics g )
    {
        //Set Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        //Set Stroke Length
        g2d.setStroke(new BasicStroke(super.getStrokeLength()));
        //Change Stroke Dash
        if (super.getIsDashed()){
            float dashedLength = (float) (super.getDashLength());
            g2d.setStroke(new BasicStroke(super.getStrokeLength(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {dashedLength,dashedLength},0.0f));
        }
        //Draw Gradient Shape
        if (super.getIsGradient()){
            //Set gradiance variable
            Color startColor = super.getColor();
            Color endColor = super.getSecondColor();
            int startX = super.getXValue();
            int startY = super.getYValue();
            int endX = super.getXValueEnd();
            int endY = super.getYValueEnd();
            //Draw Gradient SHape
            GradientPaint gradient = new GradientPaint(startX,startY,startColor,endX,endY,endColor);
            g2d.setPaint(gradient);
            g2d.drawRect( super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()) );
            if (super.getIsFilled()){
                g2d.fillRect( super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()));
            }
        }
        //Draw regular shape
        else{
            g2d.setColor( super.getColor() ); //set color of shape
            g2d.drawRect( super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()) ); //draws shape
            if (super.getIsFilled()){ //fills shape
                g2d.fillRect( super.getUpperXValue(),super.getUpperYValue(),Math.abs(super.getLowerXValue()-super.getUpperXValue()),Math.abs(super.getLowerYValue()-super.getUpperYValue()));
            }
        }
    } // end method draw
} // end class MyLine