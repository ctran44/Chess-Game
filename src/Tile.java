
import javafx.scene.shape.Rectangle;

/**
 * The Tile class represents the tiles on a chess board.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Tile extends Rectangle {
    
    /**
     * The chess piece.
     */
    private Piece piece;
    
    /**
     * The tile size.
     */
    private static final int TILE_SIZE = 100;
    
    /**
     * The x-coordinate.
     */
    private int x;
    
    /**
     * The y-coordinate.
     */
    private int y;
    
    

    /**
     * Constructor for Tile.
     * @param x the x-Coordinate
     * @param y the y-Coordinate
     */
    public Tile(int x, int y)    {
        this.x = x;
        this.y = y;
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        
        relocate(x * TILE_SIZE, y * TILE_SIZE);     
        
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
     * Checks if there is a piece.
     * @return boolean
     */
    public boolean hasPiece()   {
        return piece != null;
    }

    /**
     * Gets the piece.
     * @return Piece
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Sets the piece.
     * @param piece the chess piece
     */
    public void setPiece(Piece piece)  {
        this.piece = piece;
    }
    

}
