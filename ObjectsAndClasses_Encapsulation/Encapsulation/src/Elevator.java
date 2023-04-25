public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    private void moveDown() {
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
    }

    private void moveUp() {
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
    }

    public boolean move(int floor) {
        if (floor > maxFloor || floor < minFloor) {
            System.out.println("Введен неверный номер этажа");
            return false;
        }
        while (currentFloor != floor) {
            if (currentFloor < floor) {
                moveUp();
            }
            if (currentFloor > floor) {
                moveDown();
            }
            System.out.println("Текущий этаж: " + currentFloor);
        }
        return true;
    }
}
