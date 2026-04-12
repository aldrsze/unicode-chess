package com.aldrsze;

import java.io.*;

public class UnicodeChess {
    public static void main(String[] args) throws InterruptedException {
        Console con = System.console();

        // UNICODES - BOARD
        String cls = "\033[H\033[2J";
        String hor = "\u2500";
        String ver = "\u2502";
        String tsec1 = "\u251c";
        String tsec2 = "\u2524";
        String tsecTop = "\u252c";
        String tsecBottom = "\u2534";
        String plussec = "\u253c";

        // CHESS PIECES
        String wKing = "\u2654";
        String wQueen = "\u2655";
        String wRook = "\u2656";
        String wBishop = "\u2657";
        String wKnight = "\u2658";
        String wPawn = "\u2659";

        String bKing = "\u265a";
        String bQueen = "\u265b";
        String bRook = "\u265c";
        String bBishop = "\u265d";
        String bKnight = "\u265e";
        String bPawn = "\u265F";

        String[][] boardPiece = new String[8][8];
        for(int r=0; r<8; r++) {
            for(int c=0; c<8; c++) {
                boardPiece[r][c] = " "; 
            }
        }

        boardPiece[0][0] = wRook;   boardPiece[0][7] = wRook;
        boardPiece[0][1] = wKnight; boardPiece[0][6] = wKnight;
        boardPiece[0][2] = wBishop; boardPiece[0][5] = wBishop;
        boardPiece[0][3] = wQueen;  boardPiece[0][4] = wKing;

        for(int c=0; c<8; c++) {
            boardPiece[1][c] = wPawn;
        }

        boardPiece[7][0] = bRook;   boardPiece[7][7] = bRook;
        boardPiece[7][1] = bKnight; boardPiece[7][6] = bKnight;
        boardPiece[7][2] = bBishop; boardPiece[7][5] = bBishop;
        boardPiece[7][3] = bQueen;  boardPiece[7][4] = bKing;

        for(int c=0; c<8; c++) {
            boardPiece[6][c] = bPawn;
        }  

        while (true){
            con.printf(cls); System.out.flush();

            // Board Rendering
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    int screenRow = 2 + (r * 2);
                    int screenCol = 8 + (c * 5);
                    con.printf("\033[%d;%dH%s", screenRow, screenCol, boardPiece[r][c]);
                }
            }
            
            // Horizontal lines
            for (int col = 6; col <= 45; col++) {
                for (int row = 1; row <= 17; row += 2) con.printf("\033[%d;%dH%s", row, col, hor);
            }

            // Vertical lines
            for (int row = 1; row <= 17; row++) {
                for (int col = 6; col <= 46; col += 5) con.printf("\033[%d;%dH%s", row, col, ver);
            }
            
            for(int row = 3; row <=17; row+=2){

                con.printf("\033[%d;%dH%s", row, 6, tsec1);

                con.printf("\033[%d;%dH%s", row, 46, tsec2);

                for(int col = 11; col <=41; col+=5){

                    con.printf("\033[%d;%dH%s", row, col, plussec);

                } // PLUS SECTION

            } // T SEC / Intersections
            
            for(int col = 6; col <=41; col+=5){

                con.printf("\033[%d;%dH%s", 1, col, tsecTop);
                con.printf("\033[%d;%dH%s", 17, col, tsecBottom);

            } // T SEC TOP/BOTTOM
                
            // CORNERS
            con.printf("\033[%d;%dH\u250c", 1, 6);
            con.printf("\033[%d;%dH\u2510", 1, 46);
            con.printf("\033[%d;%dH\u2514", 17, 6);
            con.printf("\033[%d;%dH\u2518", 17, 46);

            for(int row = 2; row <= 16; row+=2){
                con.printf("\033[%d;%dH%d", row, 3, 9 - (row/2));
            }
            
            char label = 'a';
            for(int col = 9; col <= 46; col+=5){
                con.printf("\033[%d;%dH%c", 18, col, label);
                label++;
            }

            // Next line - bottom of board
            con.printf("\033[%d;%dH", 20, 1);

            String c = con.readLine("Enter Move (e.g. e2-e4) or 'exit': ");

            if (c.equalsIgnoreCase("exit")){
                break;
            }

            if (c.length() >= 5) {
                try {
                    int fromCol = c.charAt(0) - 'a';
                    int fromRow = 8 - Character.getNumericValue(c.charAt(1));
                    int toCol = c.charAt(3) - 'a';
                    int toRow = 8 - Character.getNumericValue(c.charAt(4));

                    boardPiece[toRow][toCol] = boardPiece[fromRow][fromCol];
                    boardPiece[fromRow][fromCol] = " ";
                } catch (Exception e) { 
                    // IGNORE INVALID INPUT 
                }
            }
            

        } // end while

    } // END MAIN
} // END CLASS
