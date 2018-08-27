

import java.io.Serializable;

import javafx.scene.image.ImageView;

/**
 * The Piece abstract class represents chess pieces on a board.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public abstract class Piece extends ImageView implements Serializable    {
    
    /**
     * The type of the piece.
     */
    String type;
    
    /**
     * The colour.
     */
    String colour;
    
    /**
     * The x-coordinate of the piece location.
     */
    int x;
    
    /**
     * The y-coordinate of the piece location.
     */
    int y;
    
    /**
     * Represents if the piece is the red team or not.
     */
    boolean red;
    
    /**
     * Consturctor for Piece.
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red team
     */
    public Piece(String type, String colour, int x, int y, boolean red)  {
        this.type = type;
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.red = red;
    }
    
    /**
     * Gets the x coordinate.
     * @return int
     */
    public int getCol()   {
        return x;
    }
    
    /**
     * Gets the y coordinate.
     * @return int
     */
    public int getRow()  {
        return y;
    }
    
    /**
     * Sets the x coordinate.
     * @param x the x-coordinate
     */
    public void setCol(int x) {
        this.x = x;
    }
    
    /**
     * Sets the y coordinate.
     * @param y the y-coordinate
     */
    public void setRow(int y)   {
        this.y = y;
    }
    
    /**
     * Moves the pieces.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return boolean
     */
    public abstract boolean move(int x, int y);
    
    /**
     * Sets the piece's image.
     */
    public abstract void setPieceImage();
    
    
}
