import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The ChessGUI class represents a chess board gui.
 * 
 * @author Cindy Tran, set B
 * @version 1.0
 */
public class ChessGUI extends Application {

    /**
     * The width of the tile.
     */
    private static final int WIDTH = 8;

    /**
     * The height of the tile.
     */
    private static final int HEIGHT = 8;
    
    /**
     * The chess checker board.
     */
    public static Tile[][] tiles = new Tile[WIDTH][HEIGHT];

    /**
     * The chess board.
     */
    private GridPane grid;
    
    /**
     * The menu with buttons.
     */
    private GridPane menu;

    /**
     * The current piece being selected.
     */
    private Piece currentPiece;
    
    /**
     * Represents which player's turn it currently is.
     */
    private Text playerTurn;
    

    /**
     * Keeps track of whose turn it is.
     * 0, the default value means the game has not started yet.
     * 1 means red's turn.
     * 2 means blue's turn.
     */
    private int turn = 0;

    /**
     * Sets the board contents.
     * @return GridPane
     */
    private GridPane setBoard() {
        grid = new GridPane(); 

        for (int y = 0; y < HEIGHT; y++)    {
            for (int x = 0; x < WIDTH; x++) {
                tiles[x][y] = new Tile(x, y);

                if ((y + x) % 2 == 0)   {
                    tiles[x][y].setFill(Color.WHITE);
                } else {
                    tiles[x][y].setFill(Color.PINK);
                }

                grid.add(tiles[x][y], x, y);
                

            }
        }

        grid.setOnMouseClicked(this::processClicked);
        

        addRed();
        addBlue();

        return grid;
    }

    /**
     * Selects a piece based on the player's turn.
     * @param event the mouse event.
     */
    public void processClicked(MouseEvent event)    {

        EventTarget clickTarget = event.getTarget();
        //Checking if there nothing selected.
        if (currentPiece == null)   {
            int srcX = -1;
            int srcY = -1;
            /**
             * Checking if it is a piece. Gets the x and y of the currently
             * clicked spot, so we can get the piece from the tile at
             * this location.
             */
            if (Piece.class.isInstance(clickTarget))   {
                System.out.println(clickTarget.getClass().getSimpleName());
                currentPiece = (Piece) clickTarget;
                srcX = currentPiece.getCol();
                srcY = currentPiece.getRow();


            } else if (Tile.class.isInstance(clickTarget)) {
                /**
                 * Checking if a tile was clicked. Gets the x and y of 
                 * the currently clicked tile, so we can get the piece 
                 * from the tile at this location.
                 */
                Tile myTile = (Tile) clickTarget;
                srcX = myTile.getCol();
                srcY = myTile.getRow();
            }

            if (srcX != -1 || srcY != -1)   {
                /**
                 * Gets the piece from the tile being clicked and 
                 * assigns it to the currently selecting piece.
                 */
                currentPiece = tiles[srcX][srcY].getPiece();
                if (currentPiece != null && currentPiece.red && turn == 2)   {
                    currentPiece = null;
                } else if (currentPiece != null && currentPiece.red 
                        && (turn == 0 || turn == 1))   {
                    turn = 1;
                    currentPiece.setEffect(new DropShadow(20, Color.BLACK));
                } else if (currentPiece != null && !currentPiece.red 
                        && (turn == 0 || turn == 2)) {
                    turn = 2;
                    currentPiece.setEffect(new DropShadow(20, Color.BLACK));
                }
            }


        } else {
            /**
             * Gets the x and y of the second click location.
             */
            int destX = -1;
            int destY = -1;
            if (clickTarget.getClass() == Tile.class)   {
                Tile myTile = (Tile) clickTarget;
                destX = myTile.getCol();
                destY = myTile.getRow();
            } else if (Piece.class.isInstance(clickTarget)) {
                Piece myPiece = (Piece) clickTarget;
                destX = myPiece.getCol();
                destY = myPiece.getRow();

            }

            if (destX != -1 || destY != -1) {
                /**
                 * Checks if the first and second piece being 
                 * selected are allies or not.
                 */
                if (tiles[destX][destY].getPiece() != null 
                        && tiles[destX][destY].getPiece().red 
                        == currentPiece.red)   {
                    currentPiece.setEffect(null);
                    currentPiece = tiles[destX][destY].getPiece();
                    currentPiece.setEffect(new DropShadow(20, Color.BLACK));
                } else {
                    if (Pawn.class.isInstance(currentPiece))    {
                        if (tiles[destX][destY].getPiece() != null) {
                            ((Pawn) currentPiece).setHasEnemy(true);
                        } else  {
                            ((Pawn) currentPiece).setHasEnemy(false);
                        }
                    }
                    /**
                     * Checking if the piece can move to the second 
                     * clicked spot. 
                     * It switches the turn value to the opposing team.
                     */
                    if (currentPiece.move(destX, destY))    {
                        if (turn == 1)  {
                            turn = 2;
                            playerTurn.setText("Blue");
                            playerTurn.setFill(Color.BLUE);
                        } else  {
                            turn = 1;
                            playerTurn.setText("Red");
                            playerTurn.setFill(Color.RED);
                        }
                        grid.getChildren().remove(currentPiece);
                        grid.add(currentPiece, destX, destY);
                        grid.getChildren().remove(tiles[destX][destY]
                                .getPiece());
                        tiles[currentPiece.getCol()][currentPiece.getRow()]
                                .setPiece(null);
                        tiles[destX][destY].setPiece(currentPiece);
                        currentPiece.setCol(destX);
                        currentPiece.setRow(destY);
                        currentPiece.setEffect(null);
                        currentPiece = null;
                    }

                }
            }


        }

    }
    
    /**
     * Creates a new game session.
     * @param event the mouse event
     */
    private void processNewGame(MouseEvent event)   {
        removePieces();
        addRed();
        addBlue();
    }
    
    /**
     * Saves the current game session.
     * @param event the mouse event
     */
    private void processSaveGame(MouseEvent event)  {
        ArrayList<Piece> piecelist = new ArrayList<Piece>();
        
        for (int i = 0; i < tiles.length; i++)  {
            for (int j = 0; j < tiles[i].length; j++)  {
                Piece p = tiles[i][j].getPiece();
                if (p != null)  {
                    piecelist.add(p);
                }

            }
        }
        
        try {
            FileOutputStream file = new FileOutputStream("./ChessGame.sav");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(piecelist);
            output.close();
            file.close();
           
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Loads the current game session.
     * @param event the mouse event
     */
    private void processLoadGame(MouseEvent event)  {
        ArrayList<Piece> piecelist;
        
        try {
            FileInputStream file = new FileInputStream("./ChessGame.sav");
            ObjectInputStream input = new ObjectInputStream(file);
            piecelist = (ArrayList<Piece>) input.readObject();
            input.close();
            file.close();
            System.out.println(piecelist);
            
            removePieces();
            
            for (Piece piece : piecelist) {
                tiles[piece.getCol()][piece.getRow()].setPiece(piece);
                grid.add(piece, piece.getCol(), piece.getRow());
                piece.setPieceImage();
                GridPane.setHalignment(piece, HPos.CENTER);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds the red pieces.
     */
    public void addRed()   {
        for (int i = 0; i < WIDTH; i++) {
            Piece pawn_red = new Pawn("pawn", "red", i, 1, true);
            tiles[i][1].setPiece(pawn_red);
            grid.add(pawn_red, i, 1);
            GridPane.setHalignment(pawn_red, HPos.CENTER);
        }

        Knight knight1_left = new Knight("knight1_left", "red_left", 1, 0, true);
        tiles[1][0].setPiece(knight1_left);
        grid.add(knight1_left, 1, 0);
        GridPane.setHalignment(knight1_left, HPos.CENTER);
        Knight knight1_right = new Knight("knight1_right", "red_right", 6, 0, true);
        tiles[6][0].setPiece(knight1_right);
        grid.add(knight1_right, 6, 0);
        GridPane.setHalignment(knight1_right, HPos.CENTER);

        Rook rook1_left = new Rook("rook1_left", "red_left", 0, 0, true);
        tiles[0][0].setPiece(rook1_left);
        grid.add(rook1_left, 0, 0);
        GridPane.setHalignment(rook1_left, HPos.CENTER);
        Rook rook1_right = new Rook("rook1_right", "red_right", 7, 0, true);
        tiles[7][0].setPiece(rook1_right);
        grid.add(rook1_right, 7, 0);
        GridPane.setHalignment(rook1_right, HPos.CENTER);

        Bishop bishop1_left = new Bishop("bishop1_left", "red_left", 2, 0, true);
        tiles[2][0].setPiece(bishop1_left);
        grid.add(bishop1_left, 2, 0);
        GridPane.setHalignment(bishop1_left, HPos.CENTER);
        Bishop bishop1_right = new Bishop("bishop1_right", "red_right", 5, 0, true);
        tiles[5][0].setPiece(bishop1_right);
        grid.add(bishop1_right, 5, 0);
        GridPane.setHalignment(bishop1_right, HPos.CENTER);

        Queen queen1 = new Queen("queen1", "red", 3, 0, true);
        tiles[3][0].setPiece(queen1);
        grid.add(queen1, 3, 0);
        GridPane.setHalignment(queen1, HPos.CENTER);

        King king1 = new King("king1", "red", 4, 0, true);
        tiles[4][0].setPiece(king1);
        grid.add(king1, 4, 0);
        GridPane.setHalignment(king1, HPos.CENTER);
    }

    /**
     * Adds the blue pieces.
     */
    public void addBlue()  {    
        for (int i = 0; i < WIDTH; i++) {
            Pawn pawn_blue = new Pawn("pawn", "blue", i, 6, false);
            tiles[i][6].setPiece(pawn_blue);
            grid.add(pawn_blue, i, 6);
            GridPane.setHalignment(pawn_blue, HPos.CENTER);
        }

        Knight knight2_left = new Knight("knight2_left", "blue_left", 1, 7, false);
        tiles[1][7].setPiece(knight2_left);
        grid.add(knight2_left, 1, 7);
        GridPane.setHalignment(knight2_left, HPos.CENTER);
        Knight knight2_right = new Knight("knight2_right", "blue_right", 6, 7, false);
        tiles[6][7].setPiece(knight2_right);
        grid.add(knight2_right, 6, 7);
        GridPane.setHalignment(knight2_right, HPos.CENTER);

        Rook rook2_left = new Rook("rook2_left", "blue_left", 0, 7, false);
        tiles[0][7].setPiece(rook2_left);
        grid.add(rook2_left, 0, 7);
        GridPane.setHalignment(rook2_left, HPos.CENTER);
        Rook rook2_right = new Rook("rook2_right", "blue_right", 7, 7, false);
        tiles[7][7].setPiece(rook2_right);
        grid.add(rook2_right, 7, 7);
        GridPane.setHalignment(rook2_right, HPos.CENTER);

        Bishop bishop2_left = new Bishop("bishop1_left", "blue_left", 2, 7, false);
        tiles[2][7].setPiece(bishop2_left);
        grid.add(bishop2_left, 2, 7);
        GridPane.setHalignment(bishop2_left, HPos.CENTER);
        Bishop bishop2_right = new Bishop("bishop2_right", "blue_right", 5, 7, false);
        tiles[5][7].setPiece(bishop2_right);
        grid.add(bishop2_right, 5, 7);
        GridPane.setHalignment(bishop2_right, HPos.CENTER);

        Queen queen2 = new Queen("queen2", "blue", 3, 7, false);
        tiles[3][7].setPiece(queen2);
        grid.add(queen2, 3, 7);
        GridPane.setHalignment(queen2, HPos.CENTER);

        King king2 = new King("king2", "blue", 4, 7, false);
        tiles[4][7].setPiece(king2);
        grid.add(king2, 4, 7);
        GridPane.setHalignment(king2, HPos.CENTER);
    }
    
    /**
     * Removes the pieces.
     */
    private void removePieces() {
        for (int i = 0; i < tiles.length; i++)  {
            for (int j = 0; j < tiles[i].length; j++)  {
                Piece p = tiles[i][j].getPiece();
                if (p != null)  {
                    tiles[i][j].setPiece(null);
                    grid.getChildren().remove(p);
                }

            }
        }
    }

    /**
     * Starts the Java FX stage.
     * @throws Exception the exception
     * @param primaryStage the stage
     */
    public void start(Stage primaryStage) throws Exception  {        
        final int appWidth = 980;
        final int appHeight = 900;
        
        setBoard();
        grid.setAlignment(Pos.CENTER);
        grid.setBorder(new Border(new BorderStroke(Color.MAGENTA, 
                BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
        menu = new GridPane();
        menu.setAlignment(Pos.CENTER);
        menu.setBorder(new Border(new BorderStroke(Color.PINK, 
                BorderStrokeStyle.DOTTED, null, BorderStroke.MEDIUM)));
        GridPane root = new GridPane();
        
        Text turnInfo = new Text("Player's Turn");
        turnInfo.setFill(Color.WHITE);
        playerTurn = new Text("Red");
        playerTurn.setFill(Color.RED);
        Button newButton = new Button("New Game");
        newButton.setOnMouseClicked(this::processNewGame);
        Button saveButton = new Button("Save Game");
        saveButton.setOnMouseClicked(this::processSaveGame);
        Button loadButton = new Button("Load Game");
        loadButton.setOnMouseClicked(this::processLoadGame);
        menu.add(turnInfo, 0, 0);
        menu.add(playerTurn, 0, 1);
        menu.add(newButton, 0, 2);
        menu.add(saveButton, 0, 3);
        menu.add(loadButton, 0, 4);
        menu.setPadding(new Insets(10));
        menu.setVgap(5);
        
        root.add(menu, 0, 1);
        root.add(grid, 1, 1, 1, 2);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
                null, null)));
        
        
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);


        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the program.
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);

    }

}
