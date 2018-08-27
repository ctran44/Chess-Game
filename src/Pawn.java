
import javafx.scene.image.Image;



/**
 * The Pawn class represents a pawn chess piece.
 * 
 * @author Cindy Tran
 * @version 1.0
 */
public class Pawn extends Piece {

    /**
     * The first move for the pawn.
     */
    private boolean firstTurn = true;

    /**
     * Represents if there is an enemy in the path.
     */
    private boolean hasEnemy = false;


    /**
     * Constructor for Pawn.
     * 
     * @param type the type of chess piece.
     * @param colour the colour.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param red the red colour
     */
    public Pawn(String type, String colour, int x, int y, boolean red)   {    
        super(type, colour, x, y, red);

        setPieceImage();
    }
    
    /**
     * Sets the pawn's image.
     */
    public void setPieceImage()    {
        final Image pawnImg;

        if (colour.equalsIgnoreCase("red"))   {
            pawnImg = new Image(ChessGUI.class.
                    getResourceAsStream("pawn1.png"));
        } else {
            pawnImg = new Image(ChessGUI.class.
                    getResourceAsStream("pawn2.png"));
        }

        setImage(pawnImg);
        setFitHeight(100);
        setFitWidth(100);
        setPreserveRatio(true);

    }

    /**
     * Checks if there is an enemy in the path.
     * @return boolean
     */
    public boolean isHasEnemy() {
        return hasEnemy;
    }

    /**
     * Sets if there is an enemy in the path.
     * @param hasEnemy has an enemy
     */
    public void setHasEnemy(boolean hasEnemy)  {
        this.hasEnemy = hasEnemy;
    }

    /**
     * Moves the piece.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return boolean
     */
    public boolean move(int x, int y) {
        if (this.x == x && y - this.y == 1 && red) {
            firstTurn = false;
            return true;
        } else if (firstTurn && this.x == x && y - this.y == 2 && red)   {
            firstTurn = false;
            return true;
        } else if (Math.abs(x - this.x) == 1 && y - this.y == 1 
                && hasEnemy && red) {
            return true;
        } else if (this.x == x && y - this.y == -1 && !red) {
            firstTurn = false;
            return true;
        } else if (firstTurn && this.x == x && y - this.y == -2 
                && !red)   {
            firstTurn = false;
            return true;
        } else if (Math.abs(x - this.x) == 1 && y - this.y == -1 
                && hasEnemy && !red) {
            return true;
        }
        return false;


    }

}
