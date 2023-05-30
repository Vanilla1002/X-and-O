/*My second java project,I made a tic-tac-toe game for 2 player,or 1 player against "AI" I made
I didn't focus the design of the game, or the input system, instead I used the console as output method,and as input,
the player need to chose 2 numbers that 1 calling for row, and the second for a column,
the board potions looks like this: 1,1 │1,2│ 1,3
                                   ————╀———╀————    also, there is 2 winner check methods, the first 1 ("is winner") is
                                   2,1 │2,2│ 2,3    basic, checking all the board if there is 3 same symbols in same row
                                   ————╀———╀————    the second is faster,checking each move if the symbols effects the
                                   3,1 │3,2│ 3,3    statement, which means checks only if the new char makes a winner.
*/

import java.util.Scanner;
public class Main {
    static char[][] board={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    static Scanner scan= new Scanner(System.in);
    public static void main(String[] args) {
        int theGame;
        int asist=0;
        String yesOrNo;

        System.out.println("Hello, welcome to my tic-tac-toe");
        do {
            System.out.println("do u want game for 2 player or 1 player");
            do {
                System.out.println("Pls answer with 1 or 2");
                theGame = scan.nextInt();
            } while (!(theGame == 1 || theGame == 2));
            if (theGame == 2) {
                twoPlayersGame();}
            else
                playAgainstAi();
            System.out.println("do u want another game?");
                do {
                    if (asist>0)
                        System.out.println("Pls answer with yes or no");
                    asist++;
                    yesOrNo = scan.nextLine();
                    board= new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
                } while (!(yesOrNo.equals("yes") || yesOrNo.equals("no") || yesOrNo.equals("Yes") || yesOrNo.equals("No")));
        }while (yesOrNo.equals("yes") || yesOrNo.equals("Yes"));
    }
    public static void twoPlayersGame(){
        int whosWinner = 4;
        int x;
        int y;
        int countTurn = 0;
        Scanner scan= new Scanner(System.in);
        printSet(board);
        while (whosWinner==4) {
            do {
                do {
                    System.out.println("enter the number of the row");
                    y = scan.nextInt();
                    if (y > 3 || y <= 0)
                        System.out.println("pls enter a number between 1-3");
                } while (!(y <= 3 && y > 0));
                do {
                    System.out.println("enter the number of the column");
                    x = scan.nextInt();
                    if (x > 3 || x <= 0)
                        System.out.println("pls enter a number between 1-3");
                } while (!(x <= 3 && x > 0));
                if (board[y-1][x-1]!=' ')
                    System.out.println("this place already taken, pick other place pls");
            }while (!(board[y - 1][x - 1]==' '));


            if (countTurn % 2 == 0) {
                board[y - 1][x - 1] = 'O';
                printSet(board);
                countTurn++;
                System.out.println("player 2 turn:");
            }
            else {
                board[y - 1][x - 1] = 'X';
                printSet(board);
                countTurn++;
                System.out.println("player 1 turn:");

            }
            whosWinner=fasterWinner(y-1,x-1,board);
        }
        winnersName(whosWinner);
    }//full game progress for 2 players 
    public static void playAgainstAi(){
        int whosWinner = 4;
        int x;
        int y;
        int x1;
        int y1;
        int countTurn = 0;
        int[] aiMove1;
        int[] aiMove2;
        char pcPick;
        char playerPick;
        System.out.println("O is starting,X second, what do u want to play?");
        do {
            System.out.println("pls pick X or O");
            playerPick = scan.next().charAt(0);
            if (playerPick == 'X') {
                countTurn = 1;
                pcPick = 'O';
            }else
                pcPick='X';
        }while (!(playerPick=='O'||playerPick=='X'||playerPick=='0'));

        printSet(board);
        while (whosWinner==4) {
            if (countTurn % 2==0){
                do {
                    do {
                        System.out.println("enter the number of the row");
                        y = scan.nextInt();
                        if (y > 3 || y <= 0)
                            System.out.println("pls enter a number between 1-3");
                    } while (!(y <= 3 && y > 0));
                    do {
                        System.out.println("enter the number of the column");
                        x = scan.nextInt();
                        if (x > 3 || x <= 0)
                            System.out.println("pls enter a number between 1-3");
                    } while (!(x <= 3 && x > 0));
                    if (board[y - 1][x - 1] != ' ')
                        System.out.println("this place already taken, pick other place pls");
                }while (!(board[y - 1][x - 1]==' '));
                board[y - 1][x - 1] = playerPick;
                printSet(board);
                countTurn++;
                System.out.println("player 2 turn:");
                y1= y;
                x1= x;
            }
            else {
                if (pcPick=='X') {
                     aiMove1 = isThereWinningMove(2, board);
                     aiMove2 = isThereWinningMove(1, board);
                }
                else {
                     aiMove1 = isThereWinningMove(1, board);
                     aiMove2 = isThereWinningMove(2, board);
                }
                if (aiMove1[0] !=-1) {
                    board[aiMove1[0]][aiMove1[1]] = pcPick;
                    y1=aiMove1[0]+1;
                    x1=aiMove1[1]+1;

                }
                else if (aiMove2[0] !=-1) {
                        board[aiMove2[0]][aiMove2[1]] = pcPick;
                        y1=aiMove2[0]+1;
                        x1=aiMove2[1]+1;
                    }
                else{
                    do {
                        x1=(int)(Math.random() * 3)+1;
                        y1=(int)(Math.random() * 3)+1;
                    }while (!(board[y1 - 1][x1 - 1]==' '));
                    board[y1-1][x1-1]=pcPick;
                }
                printSet(board);
                countTurn++;
                System.out.println("player 1 turn:");
            }
            whosWinner=fasterWinner(y1-1,x1-1,board);
        }
        winnersName(whosWinner);
    }//full game progress for playing against pc
    public static int isWinner(){
        for (int i=0;i<3;i++){
            if (((board[i][0]==board[i][1])&&(board[i][1]==board[i][2]))&&(board[i][0]!=' ')) {
                if (board[i][0] == 'O') {
                    return 1;
                }
                return 2;
            }
            if (((board[0][i]==board[1][i])&&(board[1][i]==board[2][i]))&&(board[0][i]!=' ')){
                if (board[0][i] == 'O') {
                    return 1;
                }
                return 2;
            }
        }
        if (((board[0][0]==board[1][1])&&(board[1][1]==board[2][2])||((board[0][2])==board[1][1])&&(board[1][1]==board[2][0]))&&(board[1][1]!=' ')){
            if (board[1][1] == 'O') {
                return 1;
            }
            return 2;
        }
        if(!isIn(' ',board))
            return 3;
        return 4;

    }//a winner method that checks the full board
    public static int fasterWinner(int y,int x,char[][]arrayB){//1=O,2=X
        if ((arrayB[0][x]==arrayB[2][x])&&(arrayB[2][x]==arrayB[1][x])&&(arrayB[1][x]!=' ')){
            if (arrayB[y][x] == 'O') {
                return 1;
            }
            return 2;
        }
        if ((arrayB[y][0]==arrayB[y][2])&&(arrayB[y][2]==arrayB[y][1])&&(arrayB[y][1]!=' ')){
            if (arrayB[y][x] == 'O') {
                return 1;
            }
            return 2;
        }
        if (x==y){
            if((arrayB[0][0]==arrayB[1][1])&&(arrayB[1][1]==arrayB[2][2])&&(arrayB[1][1]!=' ')){
                if (arrayB[y][x] == 'O') {
                    return 1;
                }
                return 2;
            }
        }
        if(x+y==2){
            if((arrayB[2][0]==arrayB[1][1])&&(arrayB[1][1]==arrayB[0][2])&&(arrayB[1][1]!=' ')){
                if (arrayB[1][1] == 'O') {
                    return 1;
                }
                return 2;
            }
        }
        if(!isIn(' ',board))
            return 3;
        return 4;
    }//a winner method that checks the new entity on the board
    public static int[] isThereWinningMove (int numberOfWinner,char[][] arrayBoard) {
        char theSymbol;
        if (numberOfWinner==2)
            theSymbol='X';
        else
            theSymbol='O';
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (arrayBoard[i][j]==' '){
                    arrayBoard[i][j]=theSymbol;
                    int i1 = fasterWinner(i, j, arrayBoard);
                    arrayBoard[i][j]=' ';
                    if (i1 ==numberOfWinner)
                        return new int[]{i,j};
                }
            }
        }
        return new int[]{-1};
    }//an AI method

    public static void winnersName(int x){
        switch (x) {
            case 1 -> System.out.println("O wins");
            case 2 -> System.out.println("X wins");
            case 3 -> System.out.println("its a draw!");
            default -> System.out.println("error");
        }

    }//calling for winners name
    static boolean isIn(char aChar, char[][] arr) {
        for (char[] i : arr) {
            for (char j : i) {
                if (aChar == j) {
                    return true;
                }
            }
        }
        return false;
    }//helps check if ' ' are in the board (for calling a draw)
    public static void printSet(char[][] arr) {
        int x=0;
        for (char[] subArray : arr) {
            for (int i = 0; i < subArray.length; i++) {
                System.out.print(subArray[i]);
                if (i < subArray.length - 1) {
                    System.out.print("│");
                }
            }
            System.out.println();
            if (x<arr.length-1)
                System.out.println("—╀—╀—");
            x++;
        }
    }//helps print the board
    
}