import java.util.Scanner;

public class UserInput
{
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getValidInput(String[] validInputs)
    {
        String input;
        boolean valid;
        do
        {
            valid = false;
            input = getLine().trim().toLowerCase();
            for (String str : validInputs)
            {
                if (input.equals(str.toLowerCase()))
                {
                    valid = true;
                    break;
                }
            }
            if (!valid)
            {
                System.out.print("Invalid input. Please try again\n>");
            }
        }
        while (!valid);
        return input;
    }

    public static String getLine()
    {
        return SCANNER.nextLine();
    }

    public static String[] getCommandList()
    {
        return new String[]
        {
            "right", "left", "up", "down", "r", "l", "u", "d",
            "jump", "jr", "jumpright", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
            "pickup", "p", "quit", "q", "end", "replay", "help", "?", "spring", "s",
            "check", "c", "score"
        };
    }

    public static void printCommands(String[] validCommands)
    {
        System.out.println("Valid commands:");
        for (String command : validCommands)
        {
            System.out.print(command + " ");
        }
        System.out.println();
    }

    public static int[] getDirectionDelta(String direction)
    {
        int[] delta = new int[] {0, 0};
        String dir = direction.toLowerCase();

        if (dir.equals("right") || dir.equals("r"))
        {
            delta[0] = 60;
        }
        else if (dir.equals("left") || dir.equals("l"))
        {
            delta[0] = -60;
        }
        else if (dir.equals("up") || dir.equals("u"))
        {
            delta[1] = -60;
        }
        else if (dir.equals("down") || dir.equals("d"))
        {
            delta[1] = 60;
        }
        return delta;
    }

    public static int[] getMoveDelta(String command, int stepSize)
    {
        String cmd = command.toLowerCase();
        int[] delta = new int[] {0, 0};

        if (cmd.equals("jump") || cmd.equals("jr") || cmd.equals("jumpright"))
        {
            delta[0] = stepSize * 2;
        }
        else if (cmd.equals("jumpleft") || cmd.equals("jl"))
        {
            delta[0] = -stepSize * 2;
        }
        else if (cmd.equals("jumpup") || cmd.equals("ju"))
        {
            delta[1] = -stepSize * 2;
        }
        else if (cmd.equals("jumpdown") || cmd.equals("jd"))
        {
            delta[1] = stepSize * 2;
        }
        else if (cmd.equals("right") || cmd.equals("r"))
        {
            delta[0] = stepSize;
        }
        else if (cmd.equals("left") || cmd.equals("l"))
        {
            delta[0] = -stepSize;
        }
        else if (cmd.equals("up") || cmd.equals("u"))
        {
            delta[1] = -stepSize;
        }
        else if (cmd.equals("down") || cmd.equals("d"))
        {
            delta[1] = stepSize;
        }

        return delta;
    }
}