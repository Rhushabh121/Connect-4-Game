// CLASS: AIPlayer.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
// this class is for the AI player which counter the moves played by humanPlayer
// and also plays his moves for wins
//-----------------------------------------
import java.util.Arrays;
import java.util.Random;

public class AIPlayer implements Player{
    private Random random = new Random();
    private Status[][] board;
    private GameLogic game;
    private HumanPlayer var = new HumanPlayer();
    private int currentCol;
    private int boardSize;
    private int countMoves;


    public void setInfo(int size, GameLogic gl)
    {
        game = new MyGame();
        countMoves = 0;
        currentCol = 0;
        boardSize = size-1;
        game = gl;
        board = new Status[size][size];
        for (Status[] s : board) {
            Arrays.fill(s, Status.NEITHER);
        }
    }
    public void lastMove(int lastCol)
    {
        if(lastCol == -1)
        {
            currentCol = random.nextInt(board.length/2-1);
            board[boardSize][currentCol] = Status.TWO;
            countMoves++;
            game.setAnswer(currentCol);
        }
        else {
            insertHuman(lastCol);
            setAnswer(lastCol);
        }
    }
    private void insertHuman(int lastCol)
    {
        int i = boardSize;
        while(board[i][lastCol] != Status.NEITHER)
        {
            if(i>0)
                i--;
        }
        board[i][lastCol] = Status.ONE;
    }
    private void setAnswer(int col)
    {
        int row=0;
        while(board[row][col] != Status.ONE){
            if(row< boardSize)
                row++;
        }
        //    Defence
        if(checkHorizontalMoves(row, col))
        {
            if(board[row][++col] == Status.NEITHER)
            {
                board[row][col] = Status.TWO;
                game.setAnswer(col);
            }
        }
        else if(checkVerticalMoves(row,col))
        {
            row--;
            if(board[row][col] == Status.NEITHER)
            {
                col++;
                board[row][col] = Status.TWO;
                game.setAnswer(col);
            }
        }
        else if(checkLDMoves(row,col))
        {
            if(board[row][++col] == Status.NEITHER)
            {
                col++;
                board[row][col] = Status.TWO;
                game.setAnswer(col);
            }
        }
        else if(checkRDMoves(row,col))
        {
            if(board[row][++col] == Status.NEITHER)
            {
                col++;
                board[row][col] = Status.TWO;
                game.setAnswer(col);
            }
        }
        // Offence(Attack)
        else if(countMoves >= 0)
        {
            if(countMoves==0)
            {
                currentCol = random.nextInt(boardSize);
                if(board[boardSize][currentCol] == Status.NEITHER)
                {
                    countMoves++;
                    board[boardSize][currentCol] = Status.TWO;
                    game.setAnswer(currentCol);
                }
                else
                {
                    currentCol++;
                    board[boardSize][currentCol] = Status.TWO;
                    game.setAnswer(currentCol);
                }
            }
            else if(isHorizontalEmpty())
            {
                game.setAnswer(currentCol);
            }
            else if(isVerticalEmpty())
            {
                game.setAnswer(currentCol);
            }
            else if(isRdEmpty())
            {
                game.setAnswer(currentCol);

            }
            else if(isLdEmpty())
            {
                game.setAnswer(currentCol);
            }
            else
            {
                search();
            }
        }
    }

//..................................................
//                      Offencive move (Attack)
// ..................................................
    private void search()
    {
        for (int i = boardSize; i >=0 ; i--) {
            for (int j = 0; j <=boardSize; j++) {
                if(board[i][j] == Status.NEITHER)
                {
                    currentCol=j;
                    board[i][j] = Status.TWO;
                    game.setAnswer(j);
                }
            }
        }
    }
    private boolean isHorizontalEmpty()
    {
        int i;
        for (i = 0; i <=boardSize; i++) {
            if(board[i][currentCol] == Status.TWO)
            {
                if(currentCol <boardSize) {
                    if (board[i][++currentCol] == Status.NEITHER) {
                        board[i][currentCol] = Status.TWO;
                        return true;
                    }
                    else if (board[i][--currentCol] == Status.NEITHER) {
                        //--currentCol;
                        board[i][currentCol] = Status.TWO;
                        return true;
                    }

                }
            }

        }
        return false;
    }
    private boolean isVerticalEmpty()
    {
        int row;
        for (row = 0; row <=boardSize; row++) {
            if(board[row][currentCol] == Status.TWO)
            {
                row--;
                board[row][currentCol] = Status.TWO;
                return true;
            }

        }
        return false;
    }
    private boolean isRdEmpty()
    {
        int i;
        for (i = 0; i <=boardSize; i++) {
            if(board[i][currentCol] == Status.TWO)
            {
                i--;
                if(currentCol < boardSize) {
                    currentCol++;
                    board[i][currentCol] = Status.TWO;
                    return true;
                }
            }

        }
        return false;
    }
    private boolean isLdEmpty()
    {
        int i;
        for (i = 0; i <=boardSize; i++) {
            if(board[i][currentCol] == Status.TWO)
            {
                i--;
                if(currentCol < boardSize) {
                    currentCol--;
                    board[i][currentCol] = Status.TWO;
                    return true;
                }
            }

        }
        return false;
    }
//..................................................
//                      Defence
// ..................................................
    private boolean checkHorizontalMoves(int row, int col)
    {
        int count = 0;
        int check = col+1;
        if(board[row][col] == Status.NEITHER) {
            for (int i = col; i >= 0; i--) {
                if (board[row][i] == Status.ONE) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
                if (board[row][i] == Status.TWO || board[row][i] == Status.NEITHER) {
                    return false;
                }
            }
        }
       return false;
    }
    private boolean checkVerticalMoves(int row, int col)
    {
        int count = 0;
        int check = row-1;
        if(board[check][col] == Status.NEITHER)
        for (int i = row; i <=boardSize; i++) {
            if(board[i][col] == Status.ONE)
            {
                count++;
                if(count ==3)
                {
                    return true;
                }
            }
            if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }
    private boolean checkLDMoves(int row, int col)
    {
        int count = 1;
        for (int i = row; i <=boardSize; i++) {

            if(board[i][col] == Status.ONE)
            {
                count++;
                if(col >0)
                    col--;
                if(count ==3)
                {
                    return true;
                }
            }
            if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }
    private boolean checkRDMoves(int row, int col)
    {
        int count = 1;
        for (int i = row; i <=boardSize; i++) {

            if(board[i][col] == Status.ONE)
            {
                count++;
                if(col <boardSize)
                    col++;
                if(count ==3)
                {
                    return true;
                }
            }
            if(board[row][i] == Status.TWO || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }


//..................................................
//                      Check Winner
// ..................................................

    public boolean isWinner(int col)
    {
        for (int i = 0; i <=boardSize ; i++) {
            if(board[i][col] == Status.TWO)
            {
                if(checkHorizontal(i,col) || checkVertical(i,col)|| checkRD(i,col) ||checkLD(i,col))//
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
            if(board[row][i] == Status.TWO)
            {
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.ONE || board[row][i] == Status.NEITHER)
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
            if(board[i][col] == Status.TWO)
            {
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.ONE || board[row][i] == Status.NEITHER)
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
            if(board[row][i] == Status.TWO)
            {
                if(row == boardSize && count == 0)
                    return  false;
                else if(row < boardSize)
                    row++;
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.ONE || board[row][i] == Status.NEITHER)
            {
                return false;
            }
        }
        return false;
    }
    private boolean checkRD(int row, int col)
    {
        int count = 0;
        for (int i = col; i <= boardSize ; i++) {
            if(board[row][i] == Status.TWO)
            {
                if(row == boardSize && count == 0)
                    return  false;
                else if(row < boardSize)
                    row++;
                count++;
                if(count == 4)
                {
                    return true;
                }
            }
            else if(board[row][i] == Status.ONE || board[row][i] == Status.NEITHER)
            {
                return false;
            }

        }
        return false;
    }

    public void gameOver(Status winner)
    {
        var.gameOver(winner);
    }

}
