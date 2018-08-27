import javafx.scene.image.Image;

/**
 * The Queen class represents a queen chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Queen extends Piece    {

    /**
     * Constructor for Queen.
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public Queen(String type, String colour, int x, int y, boolean red) {
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
        if (((this.x != x && this.y != y) 
                && (Math.abs(this.x - x) != Math.abs(this.y - y)) 
                || (Math.abs(this.x - x) == 0 
                && Math.abs(this.y - y) == 0)))    {
            return false;
        }
        int lowX = Math.min(this.x, x);
        int highX = Math.max(this.x, x);
        int lowY = Math.min(this.y, y);
        int highY = Math.max(this.y, y);
        if (Math.abs(this.x - x) == Math.abs(this.y - y))   {
            for (int i = lowX + 1, j = lowY + 1;
                    i < highX && j < highY;
                    i++, j++)   {
                if (ChessGUI.tiles[i][j].getPiece() != null)   {
                    return false;
                }
            }
            return true;
        } else if (this.y == y)    {
            for (int i = lowX + 1; i < highX; i++)  {
                if (ChessGUI.tiles[i][y].getPiece() != null)    {
                    return false;
                }
            }
            return true;
        } else if (this.x == x)    {
            for (int i = lowY + 1; i < highY; i++)  {
                if (ChessGUI.tiles[x][i].getPiece() != null)   {
                    return false;
                }
            }
            return true;
        }

        return true;
    }

    /**
     * Sets the queen's image.
     */
    public void setPieceImage() {
        final Image queenImg;

        if (colour.equalsIgnoreCase("red"))   {
            queenImg = new Image(ChessGUI.class.
                    getResourceAsStream("queen1.png"));
        } else {
            queenImg = new Image(ChessGUI.class.
                    getResourceAsStream("queen2.png"));
        }

        setImage(queenImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);
        
    }

}
