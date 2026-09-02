/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?"};
  
    // set up game
    boolean play = true;
    while (play)
    {
      System.out.print(">");
      String cmd = UserInput.getValidInput(validCommands).toLowerCase();

      // basic movement increments
      int incrx = 0;
      int incry = 0;
      int moveScore = 0;

      // handle quit
      if (cmd.equals("quit") || cmd.equals("q"))
      {
        play = false; // end game loop
      }
      // help
      else if (cmd.equals("help") || cmd.equals("?"))
      {
        System.out.println("Valid commands:");
        for (String s : validCommands)
          System.out.print(s + " ");
        System.out.println();
      }
      // pickup prize
      else if (cmd.equals("pickup") || cmd.equals("p"))
      {
        score += game.pickupPrize(); // pickupPrize returns +/- value
      }
      // replay
      else if (cmd.equals("replay"))
      {
        System.out.println("Replaying. Your score=" + score + " steps=" + game.getSteps());
        score = 0; // reset score as required
        game.replay();
        System.out.println("Game has been reset.");
      }
      // jump movements (move two spaces)
      else if (cmd.equals("jump") || cmd.equals("jr") || cmd.equals("jumpright"))
      {
        incrx = m * 2;
        // check/spring trap ahead, award if present
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++; // increment player's score after every move
        score += moveScore;
      }
      else if (cmd.equals("jumpleft") || cmd.equals("jl"))
      {
        incrx = -m * 2;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("jumpup") || cmd.equals("ju"))
      {
        incry = -m * 2;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("jumpdown") || cmd.equals("jd"))
      {
        incry = m * 2;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      // basic single-space movements
      else if (cmd.equals("right") || cmd.equals("r"))
      {
        incrx = m;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("left") || cmd.equals("l"))
      {
        incrx = -m;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("up") || cmd.equals("u"))
      {
        incry = -m;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("down") || cmd.equals("d"))
      {
        incry = m;
        if (game.isTrap(incrx, incry))
          score += game.springTrap(incrx, incry);
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else
      {
        // invalid command: deduct points
        System.out.println("Invalid command");
        score -= 1;
      }
    
      
    }

  

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        