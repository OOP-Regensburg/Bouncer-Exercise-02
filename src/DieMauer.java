import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


public class DieMauer extends BouncerApp {

    /**
     * Bouncer repairs the broken bricks in the wall, using the provided
     * brick-pile on the left of the world
     */
    @Override
    public void bounce() {
        loadMap("Wall");
        moveToWall();
        checkWall();
    }

    /**
     * Bouncer moves to the wall Pre-Condition: Bouncer stands at the
     * western-bottom end of the map facing east. Post-Condition: Bouncer stands
     * at the first brick at the bottom of the wall facing east.
     */
    private void moveToWall() {
        while (!bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer starts checking for broken bricks.
     */
    private void checkWall() {
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            repairWall();
        }
    }

    /**
     * Bouncer checks each block in a row and then moves on to the next row.
     */
    private void repairWall() {
        while (bouncer.canMoveForward()) {
            checkBlock();
        }
        moveToNextLane();
    }

    /**
     * Bouncer moves from one lane of bricks to the next
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
     * Bouncer checks if the block he is standing on is broken and repairs it if
     * needed, otherwise he moves on.
     */
    private void checkBlock() {
        if (bouncer.isOnFieldWithColor(FieldColor.RED)) {
            repairBlock();
        } else {
            bouncer.move();
        }
    }

    /**
     * Bouncer repairs a block by getting a new one and replacing the old.
     */
    private void repairBlock() {
        getNewBlock();
        returnToWall();
        bouncer.paintField(FieldColor.GREEN);
    }

    /**
     * Bouncer gets a new block from the pile
     */
    private void getNewBlock() {
        returnToPile();
        getNextBrick();
    }

    /**
     * Bouncer goes to the pile of blocks, marking his way with a trail of blue
     * fields Pre-Condition: Bouncer stands on the broken block Post-Condition:
     * Bouncer stands at the bottom of the pile of replacement-blocks facing
     * north
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
     * Bouncer takes the Brick from the top of the pile Pre-Condition: Bouncer
     * stands at the bottom of the pile of replacement-blocks facing north
     * Post-Condition: Bouncer stands on top of the pile facing south.
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
     * Bouncer returns to the broken part in the wall Pre-Condition: Bouncer
     * stands on top of the pile facing south Post-Condition: Bounder stands on
     * the broken brick in the wall he came from.
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
     * Makes Bouncer turn right
     */
    private void turnRight() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    /**
     * Makes Bouncer turn around
     */
    private void turnAround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("DieMauer");
    }
}
