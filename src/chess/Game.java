package chess;

public class Game {
    public Piece[][] board;

    public Game(boolean side) {
        board = new Piece[8][8];
        board[0][0] = new Rook(new int[]{0,0});
        board[1][0] = new Knight(new int[]{1,0});
        board[2][0] = new Bishop(new int[]{2,0});
        board[5][0] = new Bishop(new int[]{5,0});
        board[6][0] = new Knight(new int[]{6,0});
        board[7][0] = new Rook(new int[]{7,0});
        board[0][7] = new Rook(new int[]{0,7});
        board[1][7] = new Knight(new int[]{1,7});
        board[2][7] = new Bishop(new int[]{2,7});
        board[5][7] = new Bishop(new int[]{5,7});
        board[6][7] = new Knight(new int[]{6,7});
        board[7][7] = new Rook(new int[]{7,7});
        if(side) {
            board[3][0] = new Queen(new int[]{3,0});
            board[4][0] = new King(new int[]{4,0});

            board[3][7] = new Queen(new int[]{3,7});
            board[4][7] = new King(new int[]{4,7});
        } else {
            board[3][0] = new King(new int[]{3,0});
            board[4][0] = new Queen(new int[]{4,0});

            board[3][7] = new King(new int[]{3,7});
            board[4][7] = new Queen(new int[]{4,7});
        }
        for(int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(new int[]{i,1});
            board[i][6] = new Pawn(new int[]{i,6});
            for(int j = 2; i < 6; i++) {
                board[i][j] = null;
            }
        }
    }
}
