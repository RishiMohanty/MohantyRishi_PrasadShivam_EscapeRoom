public class EscapeRoom
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
        System.out.println("pick up all the prizes.\n");

        GameGUI game = new GameGUI();
        game.createBoard();

        int stepSize = 60;
        int score = 0;
        String[] validCommands = UserInput.getCommandList();

        boolean play = true;
        boolean endedByEndCommand = false;

        while (play)
        {
            System.out.print(">");
            String cmd = UserInput.getValidInput(validCommands).toLowerCase();
            int[] moveDelta = UserInput.getMoveDelta(cmd, stepSize);
            int moveScore = 0;

            if (cmd.equals("quit") || cmd.equals("q"))
            {
                play = false;
            }
            else if (cmd.equals("help") || cmd.equals("?"))
            {
                UserInput.printCommands(validCommands);
            }
            else if (cmd.equals("pickup") || cmd.equals("p"))
            {
                score += game.pickupPrize();
            }
            else if (cmd.equals("replay"))
            {
                System.out.println("Replaying. Your score=" + score + " steps=" + game.getSteps());
                score = 0;
                game.replay();
                System.out.println("Game has been reset.");
            }
            else if (cmd.equals("check") || cmd.equals("c"))
            {
                System.out.print("Which direction? (r/l/u/d) ");
                String dir = UserInput.getValidInput(new String[] {"right", "left", "up", "down", "r", "l", "u", "d"}).toLowerCase();
                int[] delta = UserInput.getDirectionDelta(dir);
                boolean hasTrap = game.isTrap(delta[0], delta[1]);
                if (hasTrap)
                {
                    System.out.println("Trap detected in that direction.");
                }
                else
                {
                    System.out.println("No trap detected in that direction.");
                }
            }
            else if (cmd.equals("spring") || cmd.equals("s"))
            {
                System.out.print("Which direction to spring? (r/l/u/d) ");
                String dir = UserInput.getValidInput(new String[] {"right", "left", "up", "down", "r", "l", "u", "d"}).toLowerCase();
                int[] delta = UserInput.getDirectionDelta(dir);
                if (game.isTrap(delta[0], delta[1]))
                {
                    score += game.springTrap(delta[0], delta[1]);
                }
                else
                {
                    System.out.println("No trap to spring there. Penalty applied.");
                    score -= 5;
                }
            }
            else if (cmd.equals("score"))
            {
                System.out.println("Current score=" + score + " steps=" + game.getSteps());
            }
            else if (cmd.equals("end"))
            {
                score += game.endGame();
                endedByEndCommand = true;
                play = false;
            }
            else if (moveDelta[0] != 0 || moveDelta[1] != 0)
            {
                moveScore = game.movePlayer(moveDelta[0], moveDelta[1]);
                score++;
                score += moveScore;
                if (game.hasReachedEnd())
                {
                    score += game.endGame();
                    endedByEndCommand = true;
                    play = false;
                }
            }
            else
            {
                System.out.println("Invalid command");
                score -= 1;
            }
        }

        if (!endedByEndCommand)
        {
            score += game.endGame();
        }

        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
    }
}

