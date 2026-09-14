import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Field;

public class GameGUITest {
    public static void main(String[] args) throws Exception {
        GameGUI game = new GameGUI();

        game.x = 10;
        game.y = 15;

        Field playerLocField = GameGUI.class.getDeclaredField("playerLoc");
        playerLocField.setAccessible(true);
        playerLocField.set(game, new Point(10, 15));

        Field prizesField = GameGUI.class.getDeclaredField("prizes");
        prizesField.setAccessible(true);
        prizesField.set(game, new Rectangle[] { new Rectangle(30, 20, 15, 15) });

        int result = game.pickupPrize();
        System.out.println("pickupPrize() returned: " + result);

        if (result != 10) {
            System.exit(1);
        }
    }
}
