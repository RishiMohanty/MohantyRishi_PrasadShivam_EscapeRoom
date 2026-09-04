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
    "pickup", "p", "quit", "q", "end", "replay", "help", "?", "spring", "s", "check", "c", "score"};
  
    // set up game
    boolean play = true;
    boolean endedByEndCommand = false;
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
      // check for a trap in a given direction (no score change)
      else if (cmd.equals("check") || cmd.equals("c"))
      {
        System.out.print("Which direction? (r/l/u/d) ");
        String dir = UserInput.getValidInput(new String[]{"right","left","up","down","r","l","u","d"}).toLowerCase();
        int tx = 0; int ty = 0;
        if (dir.equals("right") || dir.equals("r")) tx = m;
        else if (dir.equals("left") || dir.equals("l")) tx = -m;
        else if (dir.equals("up") || dir.equals("u")) ty = -m;
        else if (dir.equals("down") || dir.equals("d")) ty = m;
        boolean hasTrap = game.isTrap(tx, ty);
        if (hasTrap)
          System.out.println("Trap detected in that direction.");
        else
          System.out.println("No trap detected in that direction.");
      }
      // attempt to spring a trap in a given direction; penalty if none exists
      else if (cmd.equals("spring") || cmd.equals("s"))
      {
        System.out.print("Which direction to spring? (r/l/u/d) ");
        String dir = UserInput.getValidInput(new String[]{"right","left","up","down","r","l","u","d"}).toLowerCase();
        int tx = 0; int ty = 0;
        if (dir.equals("right") || dir.equals("r")) tx = m;
        else if (dir.equals("left") || dir.equals("l")) tx = -m;
        else if (dir.equals("up") || dir.equals("u")) ty = -m;
        else if (dir.equals("down") || dir.equals("d")) ty = m;
        if (game.isTrap(tx, ty))
          score += game.springTrap(tx, ty);
        else
        {
          System.out.println("No trap to spring there. Penalty applied.");
          score -= 5; // penalty for attempting to spring a non-existent trap
        }
      }
      // show current score
      else if (cmd.equals("score"))
      {
        System.out.println("Current score=" + score + " steps=" + game.getSteps());
      }
      // end the game early and evaluate win/penalty immediately
      else if (cmd.equals("end"))
      {
        score += game.endGame();
        endedByEndCommand = true;
        play = false;
      }
      // jump movements (move two spaces)
      else if (cmd.equals("jump") || cmd.equals("jr") || cmd.equals("jumpright"))
      {
        incrx = m * 2;
        moveScore = game.movePlayer(incrx, incry);
        score++; // increment player's score after every move
        score += moveScore;
      }
      else if (cmd.equals("jumpleft") || cmd.equals("jl"))
      {
        incrx = -m * 2;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("jumpup") || cmd.equals("ju"))
      {
        incry = -m * 2;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("jumpdown") || cmd.equals("jd"))
      {
        incry = m * 2;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      // basic single-space movements
      else if (cmd.equals("right") || cmd.equals("r"))
      {
        incrx = m;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("left") || cmd.equals("l"))
      {
        incrx = -m;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("up") || cmd.equals("u"))
      {
        incry = -m;
        moveScore = game.movePlayer(incrx, incry);
        score++;
        score += moveScore;
      }
      else if (cmd.equals("down") || cmd.equals("d"))
      {
        incry = m;
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

  

    if (!endedByEndCommand)
      score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        