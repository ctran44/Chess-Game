import javafx.scene.image.Image;

/**
 * The King class represents a king chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class King extends Piece {

    /**
     * Constructor for King.
     * @param type the type of piece
     * @param colour the colour
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public King(String type, String colour, int x, int y, boolean red) {
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
        if (Math.abs(this.x - x) == 1 || Math.abs(this.y - y) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Sets the king's image.
     */
    public void setPieceImage() {
        final Image kingImg;

        if (colour.equalsIgnoreCase("red"))   {
            kingImg = new Image(ChessGUI.class.
                    getResourceAsStream("king1.png"));
        } else {
            kingImg = new Image(ChessGUI.class.
                    getResourceAsStream("king2.png"));
        }

        setImage(kingImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);

    }

}
