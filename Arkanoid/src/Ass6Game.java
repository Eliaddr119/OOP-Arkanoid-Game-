/// Eliad Drori 211871439
import Ass6.GameManagement.DirectHit;
import Ass6.GameManagement.GameFlow;
import Ass6.GameManagement.Green3;
import Ass6.GameManagement.LevelInformation;
import Ass6.GameManagement.WideEasy;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass6Game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     * Creates a new game object and calls its initialize and run methods.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levelList = new ArrayList<>();
        if (args.length == 0 || args[0].equals("${args}")) {
            levelList.add(new DirectHit());
            levelList.add(new WideEasy());
            levelList.add(new Green3());
        }
        for (String arg : args) {
            if (arg.equals("1")) {
                levelList.add(new DirectHit());
            } else if (arg.equals("2")) {
                levelList.add(new WideEasy());
            } else if (arg.equals("3")) {
                levelList.add(new Green3());
            }
        }
        GameFlow flow = new GameFlow();
        flow.runLevels(levelList);
    }
}
