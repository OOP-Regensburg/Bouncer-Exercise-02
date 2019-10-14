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
     * Bouncer goes to the lake
     * Pre-Condition: Bouncer is standing on the west side of the map, on the waterfront, facing east
     * Post-Condition: Bouncer is standing above the first field of water, facing east.
     */
    private void enterPool() {
        while (bouncer.canNotMoveRight()) {
            bouncer.move();
        }
    }

    /**
     * Bouncer dives to the bottom of the lake.
     * Pre-Condition: Bouncer is standing above the first field of water, facing east.
     * Post-Condition: Bouncer stands at the bottom west of the lake facing east.
     */
    private void diveIn() {
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    /**
     * Bouncer goes out of the lake to the east end of the map
     * Pre-Condition: Bouncer is standing at the south-east in the lake, under the water, facing east.
     * Post-Condition: Bouncer stands on the east end of the map on the waterfront facing east.
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
     * Bouncer clears the pool from the treasures
     * Pre-Condition: Bouncer stands at the bottom west of the lake facing east.
     * Post-Condition: Bouncer is standing at the south-east in the lake, under the water, facing east.
     */
    private void clearPool() {
        salvageLoot();
        while (bouncer.canMoveForward()) {
            bouncer.move();
            salvageLoot();
        }
    }

    /**
     * Bouncer checks for treasures and brings them to the surface.
     * Pre-Condition: Bouncer stands on a treasure-field facing east.
     * Post-Condition: Bouncer stands on the same field (without treasure) facing east.
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
     * Bouncer dives up to the suface of the lake
     * Pre-Condition: Bouncer stands at the bottom of the lake facing east.
     * Post-Condition: Bouncer is one field above the water facing north.
     */
    private void returnToSurface() {
        bouncer.turnLeft();
        while (bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer returns to the bottom of the lake
     * Pre-Condition: Bouncer is one field above the water facing north.
     * Post-Condition: Bouncer is at the bottom of the lake facing east.
     */
    private void returnToGround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    // makes Bouncer turn right.
    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            bouncer.turnLeft();
        }
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("Schatzsuche");
    }
}
