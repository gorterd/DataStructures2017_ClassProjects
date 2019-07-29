// Interface for AI or Human player for SticksGame.

interface Player
{
   int move(int numSticks, boolean notAuto);
   void startGame();
   void endGame(boolean win, boolean notAuto);
}