import javafx.scene.image.Image;

/**
 * The Rook class represents a rook chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Rook extends Piece {

    /**
     * Constructor for Rook.
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public Rook(String type, String colour, int x, int y, boolean red) {
        super(type, colour, x, y, red);
        this.type = "rook";

        setPieceImage();
    }

    /**
     * Moves the piece.
     * @param x the x-coordiante
     * @param y the y-coordinate
     * @return boolean
     */
    public boolean move(int x, int y) {
        if (this.x != x && this.y != y) {
            return false;
        }
        if (this.x == x) {
            int lowY = Math.min(this.y, y);
            int highY = Math.max(this.y, y);

            for (int i = lowY + 1; i < highY; i++)    {
                if (ChessGUI.tiles[x][i].getPiece() != null)   {
                    return false;
                }
            }
        } else if (this.y == y) {
            int lowX = Math.min(this.x, x);
            int highX = Math.min(this.x, x);

            for (int i = lowX + 1; i < highX; i++)  {
                if (ChessGUI.tiles[i][y].getPiece() != null)    {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sets the rook's image.
     */
    public void setPieceImage() {
        Image rookImg = null;

        if (colour.equalsIgnoreCase("red_left"))   {
            rookImg = new Image(ChessGUI.class.
                    getResourceAsStream("rook1_left.png"));
        } else if (colour.equalsIgnoreCase("red_right"))    {
            rookImg = new Image(ChessGUI.class.
                    getResourceAsStream("rook1_right.png"));
        } else if (colour.equalsIgnoreCase("blue_left"))   {
            rookImg = new Image(ChessGUI.class.
                    getResourceAsStream("rook2_left.png"));
        } else {
            rookImg = new Image(ChessGUI.class.
                    getResourceAsStream("rook2_right.png"));
        }

        setImage(rookImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);
        
    }

}
