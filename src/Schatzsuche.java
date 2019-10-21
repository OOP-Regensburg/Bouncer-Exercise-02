import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


public class Schatzsuche extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Pool");
        enterPool();
        diveIn();
        clearPool();
        diveOut();
    }

    /**
     * Bouncer geht zum See.
     * Vorbedingung: Bouncer steht auf der Westseite der Karte, am Wasserrand, nach Osten ausgerichtet.
     * Nachbedingung: Bouncer steht über dem ersten Wasserfeld, nach Osten ausgerichtet.
     */
    private void enterPool() {
        while (bouncer.canNotMoveRight()) {
            bouncer.move();
        }
    }

    /**

     * Bouncer taucht auf den Grund des Sees.
     * Vorbedingung: Bouncer steht über dem ersten Wasserfeld, nach Osten ausgerichtet.
     * Nachbedingung: Bouncer steht am Grund des Sees, nach Osten ausgerichtet.
     */
    private void diveIn() {
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    /**
     * Bouncer verlässt den See am östlichen Ende der Karte.
     * Vorbedingung: Bouncer steht im Südosten im See, unter Wasser, nach Osten gerichtet.
     * Nachbedingung: Bouncer steht am östlichen Ende der Karte am Wasserrand, nach Osten gerichtet.
     */
    private void diveOut() {
        bouncer.turnLeft();
        while (bouncer.canNotMoveRight()) {
            bouncer.move();
        }
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
    }

    /**
     * Bouncer räumt den Pool von den Schätzen frei.
    * Vorbedingung: Bouncer steht am Grund des Sees im Westen, nach Osten ausgerichtet.
    * Nachbedingung: Bouncer steht im Südosten im See, unter Wasser, nach Osten gerichtet.
     */
    private void clearPool() {
        salvageLoot();
        while (bouncer.canMoveForward()) {
            bouncer.move();
            salvageLoot();
        }
    }

    /**
     * Bouncer sucht nach Schätzen und bringt sie an die Oberfläche.
     * Vorbedingung: Bouncer steht auf einem Schatzfeld, nach Osten ausgerichtet.
     * Nachbedingung: Bouncer steht auf dem gleichen Feld (ohne Schatz), nach Osten ausgerichtet.
     */
    private void salvageLoot() {
        if (bouncer.isOnFieldWithColor(FieldColor.RED)) {
            bouncer.paintField(FieldColor.BLUE);
            returnToSurface();
            bouncer.paintField(FieldColor.RED);
            returnToGround();
        }
    }

    /**
     * Bouncer taucht bis zur Oberfläche des Sees.
     * Vorbedingung: Bouncer steht auf dem Grund des Sees, nach Osten ausgerichtet.
     * Nachbedingung: Bouncer ist ein Feld über dem Wasser, nach Norden ausgerichtet.
     */
    private void returnToSurface() {
        bouncer.turnLeft();
        while (bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer kehrt auf den Grund des Sees zurück.
     * Vorbedingung: Bouncer ist ein Feld über dem Wasser, nach Norden ausgerichtet.
     * Nachbedingung: Bouncer befindet sich auf dem Grund des Sees, nach Osten ausgerichtet.
     */
    private void returnToGround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    // dreht Bouncer nach rechts
    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            bouncer.turnLeft();
        }
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("Schatzsuche");
    }
}
