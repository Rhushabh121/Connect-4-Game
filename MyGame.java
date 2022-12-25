// CLASS: MyGame.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
// it take inputs from User and also from AI then it check if any
//player won the game after taking every inputs so it is kind of loop
// which ends if we force to stop it or any player won the game
//-----------------------------------------
import java.util.Random;

public class MyGame implements GameLogic{
    private Random random = new Random();
    private HumanPlayer human;
    private AIPlayer aiPlayer;
    private int size;
    private Status currentPlayer;
    private int countMoves;
    public void runTheGame()
    {
        human = new HumanPlayer();
        aiPlayer = new AIPlayer();
        size = random.nextInt(7)+6;
        human.setInfo(size,this);
        aiPlayer.setInfo(size,this);
        int firstMove = random.nextInt(2)+1;
        if(firstMove == 1){
            currentPlayer = Status.ONE;
            human.lastMove(-1);
        }
        else {
            currentPlayer = Status.TWO;
            aiPlayer.lastMove(-1);
        }
        //aiPlayer.
    }
    public void setAnswer (int col)
    {
        if(countMoves <= (size*size)) {
            if (currentPlayer == Status.ONE) {
                if (!human.isWinner(col)) {
                    currentPlayer = Status.TWO;
                    countMoves++;
                    aiPlayer.lastMove(col);
                }
                else if(human.isWinner(col))
                {
                     human.gameOver(currentPlayer);
                }
            }
            else if (currentPlayer == Status.TWO) {
                if (!aiPlayer.isWinner(col)) {
                    currentPlayer = Status.ONE;
                    countMoves++;
                    human.lastMove(col);
                }
                else if(aiPlayer.isWinner(col))
                {
                    aiPlayer.gameOver(currentPlayer);
                }
            }

        }
        else {
            currentPlayer = Status.NEITHER;
            aiPlayer.gameOver(currentPlayer);
        }
    }
}
