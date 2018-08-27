import javafx.scene.image.Image;

/**
 * The Bishop class represents a Bishop chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Bishop extends Piece   {

    /**
     * Constructor for Bishop.
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public Bishop(String type, String colour, int x, int y, boolean red) {
        super(type, colour, x, y, red);
        
        setPieceImage();
    }

    /**
     * Moves the piece.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return boolean
     */
    public boolean move(int x, int y) {
        if (Math.abs(this.x - x) == 0 || Math.abs(this.y - y) == 0)  {
            return false;
        }
        if (Math.abs(this.x - x) == Math.abs(this.y - y))   {
            int lowX = Math.min(this.x, x);
            int highX = Math.max(this.x, x);
            int lowY = Math.min(this.y, y);
            int highY = Math.max(this.y, y);
            
            for (int i = lowY + 1, j = lowX + 1;
                    i < highY && j < highX;
                    i++, j++)  {
                if (ChessGUI.tiles[j][i].getPiece() != null)   {
                    return false;
                }
            }
            return true;
        }
        return false;
        
    }

    /**
     * Sets the bishop's image.
     */
    public void setPieceImage() {
        Image bishopImg = null;

        if (colour.equalsIgnoreCase("red_left"))   {
            bishopImg = new Image(ChessGUI.class.
                    getResourceAsStream("bishop1_left.png"));
        } else if (colour.equalsIgnoreCase("red_right"))    {
            bishopImg = new Image(ChessGUI.class.
                    getResourceAsStream("bishop1_right.png"));
        } else if (colour.equalsIgnoreCase("blue_left"))   {
            bishopImg = new Image(ChessGUI.class.
                    getResourceAsStream("bishop2_left.png"));
        } else {
            bishopImg = new Image(ChessGUI.class.
                    getResourceAsStream("bishop2_right.png"));
        }

        setImage(bishopImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);
        
    }

}
