import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

abstract class MyBoundedShape extends MyShape {
    //instance variables
    private boolean isFilled; //if shape is filled
    
    //Constructor with no parameters (no specified values)
    public MyBoundedShape() {
        super();
        this.isFilled = false;
    }//End MyBounded Shape Constructor
    
    //Constructor with specified values
    public MyBoundedShape(int x1,int y1, int x2, int y2, Color myColor, Color secondColor,boolean isGradient,boolean isFilled,float strokeLength,boolean isDashed,float dashLength) {
        super(x1,y1,x2,y2,myColor,secondColor,isGradient,strokeLength,isDashed,dashLength);
        this.isFilled = isFilled;
    }//End MyBounded Shape Constructor
    
    //Mutator method to set upper-left x-value
    protected int getUpperXValue() {
        return Math.min(super.getXValue(), super.getXValueEnd());
    }//end setUpperX
    
    //Mutator method to set upper-left y-value
    protected int getUpperYValue() {
        return Math.min(super.getYValue(), super.getYValueEnd());
    }//end setUpperY
    
    //Mutator method to set upper-left x-value
    protected int getLowerXValue() {
        return Math.max(super.getXValue(), super.getXValueEnd());
    }//end setUpperX
    
    //Mutator method to set upper-left y-value
    protected int getLowerYValue() {
        return Math.max(super.getYValue(), super.getYValueEnd());
    }//end setUpperY
    
    
    //Mutator to set width of shape
   protected void calcBoundedWidth() {
        if (super.getXValueEnd() > 500 - super.getXValue())
            super.setEndX(500- super.getXValue());
    }//end setBoundedWidth
    
    //Mutator method to set height of shape
   protected void calcBoundedHeight() {
        if (super.getYValueEnd() > 500-super.getYValue())
            super.setEndY(500 - super.getYValue());
    }
    
    //Mutator method to set if shape is filled
    protected void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    //Accessor method to return upper-left x-value
    protected int getUpperX() {
        return super.getXValue();
    }//End getUpperX
    
    //Accessor method to return upper-left y-value
    protected int getUpperY() {
        return super.getYValue();
    }//End getUpperY
    
    //Accessor method to return the width of the shape
    protected int getShapeWidth() {
        return super.getXValueEnd();
    }//end getShapeWidth
    
    //Accessor method to return the height of the shape
    protected int getShapeHeight() {
        return super.getYValueEnd();
    }//End getShapeHeight    
    
    //Accessor method to return if shape is filled
    protected boolean getIsFilled() {
        return isFilled;
    }//end getIsFilled
    
    //abstract method to draw shape
    public abstract void draw(Graphics g);
}//end myBoundedShape Class