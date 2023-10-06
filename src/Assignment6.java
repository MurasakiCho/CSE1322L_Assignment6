/*
Class: CSE 1322L
Section: WJ1
Term: Summer 2022
Instructor: Maneesha Kumari Penmetsa
Name: Briana O'Neal
Lab#: Assignment 6
*/

import java.util.Scanner;
class ColumnFull extends Exception{
    ColumnFull(){}
    ColumnFull(String message){
        super(message);
    }
}

class ConnectFour{
    private char[][] board = new char[6][7];
    private String turn;
    private char token;

    ConnectFour(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = ' ';
            }
        }
        turn = "Red";
        token = 'R';
    }

    public String getTurn() {
        return turn;
    }

    public char getToken() {
        return token;
    }

    public void nextTurn(){
        if(turn.equals("Red")){
            turn = "Yellow";
            token = 'Y';
        }
        else if(turn.equals("Yellow")){
            turn = "Red";
            token = 'R';
        }
    }
    public int nextAvailablePosition(int p){
        for(int i = 5; i >= 0; i--){
            if(board[i][p] == ' '){
                return i;
            }
        }
        return -1;
    }
    public void dropPiece(int p, char t) throws ColumnFull{
        if(nextAvailablePosition(p) == -1){
            throw new ColumnFull("That column is full try again");
        }
        else{
            board[nextAvailablePosition(p)][p] = t;
            nextTurn();
        }
    }
    @Override
    public String toString(){
        String to_return = "  0   1   2   3   4   5   6  ";
        for(int i = 0; i < 6; i++){
            to_return+= "\n-----------------------------\n";
            to_return+= "| ";
            for(int j = 0; j < 7; j++){
                to_return+=board[i][j]+" | ";
            }
        }
        to_return+="\n-----------------------------\n";
        return to_return;
    }
}

public class Assignment6 {
    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        Scanner scan = new Scanner(System.in);

        while(true){
            try {
                System.out.println(connectFour);
                System.out.println("Which column would " + connectFour.getTurn() + " like to go in (9 to quit)");
                int choice = scan.nextInt();
                if(choice == 9){System.exit(0);}

                connectFour.dropPiece(choice, connectFour.getToken());

            }
            catch (ColumnFull c) {
                System.out.println(c.getMessage());
            }
        }
    }
}
