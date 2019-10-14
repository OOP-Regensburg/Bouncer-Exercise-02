import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;

public class Schatzsuche extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Pool");
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("Schatzsuche");
    }
}
