package cis350.games;

import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AchievementsViewController implements Observer {

    private ArrayList<Achievement> achievementsList;

    public AchievementsViewController() {
        this.achievementsList = new ArrayList<>();
    }

    public void update(Observable o, Object arg) {
        System.out.println("update() called");
        Achievement a;
        if (arg instanceof Achievement) {
            a = (Achievement)arg;

            if (!this.achievementsList.contains(a)) {
                this.achievementsList.add(a);
            }
        }
        System.out.println(this.achievementsList);
    }

    @FXML private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
}
