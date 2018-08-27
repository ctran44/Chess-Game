import javafx.scene.image.Image;

/**
 * The Knight class represent a knight chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Knight extends Piece {

    /**
     * Constructor for Knight.
     * 
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public Knight(String type, String colour, int x, int y, boolean red) {
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
        if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2) {
            return true;
        } else if (Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1)  {
            return true;
        }
        return false;
    }

    /**
     * Sets the knight's image.
     */
    public void setPieceImage() {
        Image knightImg = null;

        if (colour.equalsIgnoreCase("red_left"))   {
            knightImg = new Image(ChessGUI.class.
                    getResourceAsStream("knight1_left.png"));
        } else if (colour.equalsIgnoreCase("red_right"))    {
            knightImg = new Image(ChessGUI.class.
                    getResourceAsStream("knight1_right.png"));
        } else if (colour.equalsIgnoreCase("blue_left"))   {
            knightImg = new Image(ChessGUI.class.
                    getResourceAsStream("knight2_left.png"));
        } else {
            knightImg = new Image(ChessGUI.class.
                    getResourceAsStream("knight2_right.png"));
        }

        setImage(knightImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);

    }


}
