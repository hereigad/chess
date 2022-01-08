package game;

import chess.*;

public class Game {
    public Piece[][] board;
    public Player player;

    public Game(Player player) {
        this.player = player;
        board = new Piece[8][8];
        board[0][0] = new Rook(new int[]{0,0}, player.getSide());
        board[1][0] = new Knight(new int[]{1,0}, player.getSide());
        board[2][0] = new Bishop(new int[]{2,0}, player.getSide());
        board[5][0] = new Bishop(new int[]{5,0}, player.getSide());
        board[6][0] = new Knight(new int[]{6,0}, player.getSide());
        board[7][0] = new Rook(new int[]{7,0}, player.getSide());
        board[0][7] = new Rook(new int[]{0,7}, !player.getSide());
        board[1][7] = new Knight(new int[]{1,7}, !player.getSide());
        board[2][7] = new Bishop(new int[]{2,7}, !player.getSide());
        board[5][7] = new Bishop(new int[]{5,7}, !player.getSide());
        board[6][7] = new Knight(new int[]{6,7}, !player.getSide());
        board[7][7] = new Rook(new int[]{7,7}, !player.getSide());
        if(player.getSide()) {
            board[3][0] = new Queen(new int[]{3,0}, player.getSide());
            board[4][0] = new King(new int[]{4,0}, player.getSide());

            board[3][7] = new Queen(new int[]{3,7}, !player.getSide());
            board[4][7] = new King(new int[]{4,7}, !player.getSide());
        } else {
            board[3][0] = new King(new int[]{3,0}, player.getSide());
            board[4][0] = new Queen(new int[]{4,0}, player.getSide());

            board[3][7] = new King(new int[]{3,7}, !player.getSide());
            board[4][7] = new Queen(new int[]{4,7}, !player.getSide());
        }
        for(int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(new int[]{i,1}, player.getSide());
            board[i][6] = new Pawn(new int[]{i,6}, !player.getSide());
            for(int j = 2; j < 6; j++) {
                board[i][j] = null;
            }
        }
    }
}
