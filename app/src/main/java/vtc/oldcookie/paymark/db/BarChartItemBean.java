package vtc.oldcookie.paymark.db;

/**
 * Class representing an item in a bar chart.
 */
public class BarChartItemBean {
    private final int year;
    private final int month;
    private final int day;
    private final float summoney;

    /**
     * Constructor for BarChartItemBean.
     *
     * @param year     the year of the item
     * @param month    the month of the item
     * @param day      the day of the item
     * @param summoney the sum of money for the item
     */
    public BarChartItemBean(int year, int month, int day, float summoney) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.summoney = summoney;
    }

    /**
     * Gets the year of the item.
     *
     * @return the year of the item
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month of the item.
     *
     * @return the month of the item
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the item.
     *
     * @return the day of the item
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the sum of money for the item.
     *
     * @return the sum of money for the item
     */
    public float getSummoney() {
        return summoney;
    }
}