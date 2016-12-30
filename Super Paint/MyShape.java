import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

abstract class MyShape extends JPanel {
    private int x1; //First x-coordinate of shape
    private int y1; //first y-coordinate of shape
    private int x2; //ending x-coordinate
    private int y2; //ending y-coordinate
    private Color shapeColor; //shapes color
    private Color secondShapeColor; //secondary color
    private Boolean isGradient; //if shape is gradient
    private Boolean isDashed;
    private float dashLength;
    private float strokeLength; //stroke length
    
    //Constructor method if no value specified
    public MyShape() {
        x1 = 0; //Set starting X point to 0
        y1 = 0; //Set Starting Y point to 0
        x2 = 0; //Set ending X point to 0
        y2 = 0; //Set ending Y point to 0
        shapeColor = Color.BLACK; //Set color to black
        secondShapeColor = Color.BLACK; //Set secondary color to black
        isGradient = false; //Set gradience to false
        isDashed = false; //Set dashed to false
        dashLength = 10; // Set no dash length
        strokeLength = 2; //Set stroke length to 2
    }//End MyShape Constructor
    
    //Constructor method for specified values
    public MyShape(int x1, int y1,int x2,int y2, Color shapeColor,Color secondShapeColor,Boolean isGradient, float strokeLength,boolean isDashed,float dashLength) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shapeColor = shapeColor;
        this.secondShapeColor = secondShapeColor;
        this.isGradient = isGradient;
        this.strokeLength = strokeLength;
        this.isDashed = isDashed;
        this.dashLength = dashLength;
    }//End MyShape Constructor
    
    //Mutator method to set starting x-value
    protected void setXValue (int x1) {
        this.x1 = x1;
    }//End setXValue
    
    //Mutator method to set starting y-value
    protected void setYValue (int y1) {
        this.y1 = y1;
    }//End setYValue
    
    //Mutator method to set ending X point
    protected void setEndX(int x2) {
        this.x2 = x2;
    }//end setEndX
    
    //Mutator method to set ending Y point
    protected void setEndY(int y2) {
        this.y2 = y2;
    }//end setEndY
    //Mutator method to set shape color
    protected void setColor (Color shapeColor) {
        this.shapeColor = shapeColor;
    }//End setColor
    
    protected void setSecondColor (Color shapeColor){
        secondShapeColor = shapeColor;
    }
    
    //Mutator method to set if shape is gradient
    protected void setIsGradient (Boolean isGradient){
        this.isGradient = isGradient;
    }
    
    //Mutator method to set stroke length
    protected void setStrokeLength(float strokeLength){
        this.strokeLength = strokeLength;
    }
    
    //Mutator to set if dashed
    protected void setIsDashed(boolean isDashed){
        this.isDashed = isDashed;
    }
    
    //Mutator method to set dash length
    protected void setDashLength(float dashLength){
        this.dashLength = dashLength;
    }
    //Accessor method to get starting x-point
    protected int getXValue() {
        return x1;
    }//End getXValue
    
    //Accessor method to get ending x-point
    protected int getXValueEnd() {
        return x2;
    }//End getXValue
    
    //Accessor method to get ending y-point
    protected int getYValueEnd() {
        return y2;
    }//end getYValueEnd
    
    //Accessor method to get starting y-point
    protected int getYValue() {
        return y1;
    }//End getYValue
    
    //Accessor method to get color
    protected Color getColor () {
        return shapeColor;
    }//End getColor
    
    protected Color getSecondColor(){
        return secondShapeColor;
    }
    //Accessor method to get if gradient
    protected Boolean getIsGradient(){
        return isGradient;
    }
    
    //Accessor method to get stroke length
    protected float getStrokeLength(){
        return strokeLength;
    }
    
    //Accesor method to get if dashed
    protected boolean getIsDashed(){
        return isDashed;
    }
    
    //Accessor method to get dash length
    protected float getDashLength(){
        return dashLength;
    }
    
    //Abstract method to draw shape
    public abstract void draw(Graphics g);
}