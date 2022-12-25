// CLASS: HumanPlayer.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
// this class is for user who is playing the game he can see the moves which he
// is making also his opponent who is the computer(AI) so it call the textUi class
// for printing purpose and also for the asking user for the next move
//
//-----------------------------------------
import java.util.Arrays;

public class HumanPlayer implements Player, Human{
    private SwingGUI callMethod = new SwingGUI();
    private GameLogic game = new MyGame();
    private Status[][] board;
    private int boardSize;

//    public HumanPlayer(GameLogic game)
//    {
//        this.game = game;
//    }
    // human
    public void setAnswer(int col)
    {
        int i = boardSize-1;
        while (board[i][col] != Status.NEITHER) {
            if (i > 0) {
                i--;
            }
            else
                break;
        }
        board[i][col] = Status.TWO;
        game.setAnswer(col);
    }

    // player
    public void lastMove(int lastCol)
    {
        int i = boardSize-1;
        if(lastCol != -1) {
            while (board[i][lastCol] != Status.NEITHER) {
                if (i > 0) {
                    i--;
                }
                else
                    break;
            }
            board[i][lastCol] = Status.TWO;
            callMethod.lastMove(lastCol);
        }
        else
            callMethod.lastMove(lastCol);
    }
    public void gameOver(Status winner)
    {
        callMethod.gameOver(winner);
    }
    public void setInfo(int size, GameLogic gl)
    {
        board = new Status[size][size];
        for (Status[] s : board) {
            Arrays.fill(s, Status.NEITHER);
        }
        boardSize = size-1;
        game = gl;
        callMethod.setInfo(this,size);

    }

//..................................................
//                      Check Winner
// ..................................................

    public boolean isWinner(int col)
    {
        for (int i = 0; i <=boardSize ; i++) {
            if(board[i][col] == Status.TWO)
            {
                if(checkHorizontal(i,col) || checkVertical(i,col) || checkRD(i,col) ||checkLD(i,col))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkHorizontal(int row, int col)
    {
        int count = 0;
        for (int i = col; i >=0 ; i--) {
            if(board[row][i] == Status.ONE)
            {
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }

    private boolean checkVertical(int row, int col)
    {
        int count = 0;
        for (int i = row; i <=boardSize ; i++) {
            if(board[i][col] == Status.ONE)
            {
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }
    private boolean checkLD(int row, int col)
    {
        int count = 0;
        for (int i = col; i >=0 ; i--) {
            if(board[row][i] == Status.ONE)
            {
                row++;
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }
    private boolean checkRD(int row, int col)
    {
        int count = 0;
        for (int i = col; i <=boardSize ; i++) {
            if(board[row][i] == Status.ONE)
            {
                row++;
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }

}
