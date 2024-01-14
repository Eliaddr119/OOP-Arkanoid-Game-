package Ass6.GameManagement;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param counterStart the counter start
     */
    public Counter(int counterStart) {
        this.count = counterStart;
    }

    /**
     * Increase.
     * Increases the counter by number
     *
     * @param number the number that is needed to be added to the counter
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease.
     * Decreases the counter by number
     *
     * @param number the number that is needed to be subtracted from the counter
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets value.
     *
     * @return the value of the counter
     */
    public int getValue() {
        return this.count;
    }
}
