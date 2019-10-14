import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;

public class DieMauer extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Wall");
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("DieMauer");
    }
}
