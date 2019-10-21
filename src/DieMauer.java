import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


public class DieMauer extends BouncerApp {

    /**
     * Bouncer repariert die zerbrochenen Ziegel in der Wand mit den mitgelieferten
     * Ziegelhaufen  auf der linken Seite der Welt
     */
    @Override
    public void bounce() {
        loadMap("Wall");
        moveToWall();
        checkWall();
    }

    /**
     * Bouncer bewegt sich zur Wand
     * Vorbedingung: Bouncer steht am westlichen, unteren Ende der Karte, nach Osten ausgerichtet.
     * Nachbedingung: Bouncer steht beim ersten Ziegel am Fuß der Mauer, nach Osten ausgerichtet.
     */
    private void moveToWall() {
        while (!bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer beginnt, nach kaputten Ziegeln zu suchen.
     */
    private void checkWall() {
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            repairWall();
        }
    }

    /**
     * Bouncer überprüft jeden Block in einer Reihe und geht dann zur nächsten Reihe über.
     */
    private void repairWall() {
        while (bouncer.canMoveForward()) {
            checkBlock();
        }
        moveToNextLane();
    }

    /**
     * Bouncer bewegt sich von einer Ziegelreihe zur nächsten.
     */
    private void moveToNextLane() {
        turnAround();
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
        turnRight();
        bouncer.move();
        turnRight();
        bouncer.move();
    }

    /**
     * Bouncer prüft, ob der Block, auf dem er steht, kaputt ist und repariert ihn, wenn
     * nötig, sonst macht er weiter.
     */
    private void checkBlock() {
        if (bouncer.isOnFieldWithColor(FieldColor.RED)) {
            repairBlock();
        } else {
            bouncer.move();
        }
    }

    /**
     * Bouncer repariert einen Block, indem er einen neuen holt und den alten ersetzt.
     */
    private void repairBlock() {
        getNewBlock();
        returnToWall();
        bouncer.paintField(FieldColor.GREEN);
    }

    /**
     * Bouncer holt einen neuen Block vom Stapel.
     */
    private void getNewBlock() {
        returnToPile();
        getNextBrick();
    }

    /**
     * Bouncer geht zum Blockhaufen und markiert seinen Weg mit einer blauen Spur.
     * Vorbedingung: Bouncer steht auf dem kaputten Block
     * Nachbedingung: Bouncer steht am unteren Ende des Stapels von Ersatzblöcken, nach Norden ausgerichtet.
     */
    private void returnToPile() {
        turnAround();
        bouncer.move();
        while (bouncer.canMoveForward()) {
            if (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
                bouncer.move();
            } else {
                bouncer.paintField(FieldColor.BLUE);
                bouncer.move();
            }
        }
        bouncer.turnLeft();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
    }

    /**
     * Bouncer nimmt den Ziegel von der Oberseite des Stapels
     * Vorbedingung: Bouncer steht am unteren Ende des Stapels von Ersatzblöcken, nach Norden ausgerichtet.
     * Nachbedingung: Bouncer steht auf dem Stapel, nach Süden ausgerichtet.
     */
    private void getNextBrick() {
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
        turnAround();
        bouncer.move();
        bouncer.clearFieldColor();
    }

    /**
     * Bouncer kehrt zu dem kaputten Teil in der Wand zurück.
     * Vorbedingung: Bouncer steht auf dem Ersatzteile-Stapel, nach Süden ausgerichtet.
     * Nachbedingung: Bouncer steht auf dem kaputten Stein in der Wand, von dem er kam.
     */
    private void returnToWall() {
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
        while (!bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.move();
        }
        turnRight();
        while (bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.clearFieldColor();
            bouncer.move();
        }
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
    }

    /**
     * dreht Bouncer nach rechts.
     */
    private void turnRight() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    /**
     * Bringt Bouncer dazu, sich umzudrehen.
     */
    private void turnAround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("DieMauer");
    }
}
