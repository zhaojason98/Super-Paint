/*
 * Name: Jason Zhao
 * Date: April 20, 2016
 * Description: Draw Panel for a paint program and mouse events to draw 3 defined shape
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.SwingUtilities;

public class DrawPanel extends JPanel
{
    //Create instance variables
    private int currentShapeType; //Create current shape type
    private float strokeLength; //Create stroke length as a floating point value
    private float dashLength; //Create dash length as a floating point value
    private MyShape currentShapeObject; //create current shape object of MyShape
    private MyShape drawShape; //create shape to draw of MyShape
    private Color currentShapeColor; //create current shape colour
    private Color secondShapeColor; //create second shape colour
    private Boolean currentShapeFilled; //Create boolean value for filled
    private Boolean currentGradient; //Create boolean value for if gradient
    private Boolean ifLoad; //boolean to see if image is loaded
    private Boolean isDashed; //create boolean value for if dashed
    private JLabel statusLabel; //create status label
    private LinkedList<MyShape> shapeList; //Create shape list
    private LinkedList<MyShape> removedList; //Create undo shape list
    private LinkedList<String> prefList; //Create preferences value
    private GradientPaint gradient;  //Create gradient compoinent
    private BufferedImage paintImage;
    
    //Shape colour constants
    private final Color colors[] = { Color.BLACK, Color.BLUE, Color.CYAN, 
        Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, 
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, 
        Color.YELLOW };
    
    
    //Draw Panel constructor class
    public DrawPanel(JLabel statusLabel){
        //define variables
        prefList = new LinkedList<String>();
        this.statusLabel = statusLabel;
        shapeList = new LinkedList<MyShape>();
        removedList = new LinkedList<MyShape>();
        drawShape = null;
        setBackground(Color.WHITE);
        MouseClickHandler handler = new MouseClickHandler(); 
        addMouseListener( handler );
        addMouseMotionListener( handler );
        ifLoad = false;
        //Try to define files from a file
        try{
            //Load preferences file
            try (BufferedReader readPref = new BufferedReader(new FileReader("Preferences.log"))) {
                //Put each preference value into a LinkedList
                String prefString;
                while ((prefString = readPref.readLine()) != null) {
                    prefList.addFront(prefString);
                }
                //Declare each variable based on preference file
                currentShapeType = (Integer.parseInt(prefList.indexValue(7))) + 1;
                currentShapeFilled = Boolean.parseBoolean(prefList.indexValue(6));
                currentGradient = Boolean.parseBoolean(prefList.indexValue(5));
                
                currentShapeColor = colors[Integer.parseInt(prefList.indexValue(4))];
                secondShapeColor = colors[Integer.parseInt(prefList.indexValue(3))];
                
                strokeLength = Float.parseFloat(prefList.indexValue(2));
                dashLength = Float.parseFloat(prefList.indexValue(1));
                isDashed = Boolean.parseBoolean(prefList.indexValue(0));
            }
            //If no preference file found, set default values
            catch (FileNotFoundException e){
                currentShapeType = 1;
                currentShapeFilled = false;
                currentGradient = false;
                currentShapeColor = Color.BLACK;
                secondShapeColor = Color.BLACK;
                strokeLength = 2;
                dashLength = 10;
                isDashed = false;
                
            }
        }
        //File Exception
        catch (IOException ioException){}
        paintImage = new BufferedImage(1500,1000,BufferedImage.TYPE_INT_ARGB);
    }
    
// for each element in the array, draw the shape
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        int shapeSize = shapeList.size();
        // draw the shapes
        if (ifLoad){
            g.drawImage(paintImage,0,0,null);
        }
        if (currentShapeObject != null){
            currentShapeObject.draw(g);
        }
        if (shapeSize != 0){
            for (int j = shapeSize-1;j>=0;j--){
                
                drawShape = shapeList.indexValue(j);
                drawShape.draw(g);
            }
            if (ifLoad){
             for (int i=shapeSize-1;i>=0;i--){
                g = paintImage.createGraphics(); 
                g.drawImage(paintImage,0,0,null);
            }
            }
        }
        g.dispose();
    } // end method paintComponent
    
    //Save image
    public void save(String fileName) throws IOException{
        int shapeSize = shapeList.size();
            for (int i=shapeSize-1;i>=0;i--){
                Graphics g = paintImage.createGraphics(); 
                g.drawImage(paintImage,0,0,null);
                drawShape = shapeList.indexValue(i);
                drawShape.draw( g );
                g.dispose();
            }
        ImageIO.write(paintImage,"PNG",new File(fileName + ".png"));
    }
    
    //Load Image
    public void load(String fileName) throws IOException{
        paintImage = ImageIO.read(new File(fileName));
        ifLoad = true;
        repaint();
    }
    //Mutator method to set currentShapeType
    public void set_currentShapeType(int shapeType){
        currentShapeType = shapeType;
    }
    
    //Mutator method to set currentShapeColor
    public void set_currentShapeColor(Color shapeColor){
        currentShapeColor = shapeColor;
    }
    
    //Mutator method to set secondShapeColor
    public void set_secondShapeColor(Color shapeColor){
        secondShapeColor = shapeColor;
    }
    
    //Mutator method to set if currentShapeFilled
    public void set_currentShapeFilled(boolean shapeFilled){
        currentShapeFilled = shapeFilled;
    }
    
    //Mutator method to set if shape is gradiant
    public void set_currentGradiance (boolean ifGradiant){
        currentGradient = ifGradiant;
    }
    
    //Mutator method to set stroke length
    public void set_strokeLength(float strokeLength){
        this.strokeLength = strokeLength;
    }
    
    //Mutator to set if dashed
    public void set_isDashed(boolean isDashed){
        this.isDashed = isDashed;
    }
    
    //Mutator to set dash length
    public void set_dashLength(float dashLength){
        this.dashLength = dashLength;
    }
    //method to clear last shape
    public void clearLastShape(){
        if (shapeList.first() != null){
            removedList.addFront (shapeList.first());
            shapeList.removeFront();
        }
        repaint();
    }//end method clearLastShape
    
    //Method to redo last shape
    public void redoLastShape(){
        if (removedList.first() != null){
            shapeList.addFront(removedList.first());
            removedList.removeFront();
        }
        repaint();
    }//end method redoLastShape
    
    //method to clear entire panel
    public void clearDrawing(){
        if (!shapeList.isEmpty()){
            shapeList.makeEmpty();
        }
        if (!removedList.isEmpty()){
            removedList.makeEmpty();
        }
        repaint();
    }//end method clearDrawing
    
    //inner class to handle mouse events
    private class MouseClickHandler extends MouseAdapter{
        
        //position variables
        //handle mouse event pressed
        public void mousePressed(MouseEvent event){
            int xPos = event.getX();
            int yPos = event.getY();
            if (currentShapeType == 1){
                currentShapeObject = new MyLine(xPos,yPos,xPos,yPos,currentShapeColor,secondShapeColor,currentGradient,strokeLength,isDashed,dashLength);
            }
            else if (currentShapeType ==2){
                currentShapeObject = new MyRectangle(xPos,yPos,xPos,yPos,currentShapeColor,secondShapeColor,currentGradient,currentShapeFilled,strokeLength,isDashed,dashLength);
            }
            else if (currentShapeType == 3){
                currentShapeObject = new MyOval(xPos,yPos,xPos,yPos,currentShapeColor,secondShapeColor,currentGradient,currentShapeFilled,strokeLength,isDashed,dashLength);
            }
        }//end mousePressed method
        
        //handle mouse event release
        public void mouseReleased(MouseEvent event){
            currentShapeObject.setEndX(event.getX());
            currentShapeObject.setEndY(event.getY());
            shapeList.addFront(currentShapeObject);
            removedList.removeFront();
            currentShapeObject = null;
            repaint();
        }//end mouseReleased method
        
        //handle mouse event moved
        public void mouseMoved(MouseEvent event){
            statusLabel.setText(String.format("Mouse at [%d,%d]",event.getX(),event.getY()));
        }//end mouseMoved method
        
        //handle mouse event dragged
        public void mouseDragged(MouseEvent event){
            currentShapeObject.setEndX(event.getX());
            currentShapeObject.setEndY(event.getY());
            statusLabel.setText(String.format("Mouse at [%d,%d]",event.getX(),event.getY()));
            repaint();
        }//end mouseDragged method 
    }
} // end class DrawPanel