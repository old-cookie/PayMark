package vtc.oldcookie.paymark.db;

/**
 * Class representing an item in a chart.
 * LAU Cho Cheuk
 */
public class ChartItemBean {
    int sImageId;
    String type;
    float ratio;
    float totalMoney;

    /**
     * Constructor for ChartItemBean.
     *
     * @param sImageId   the image id of the item
     * @param type       the type of the item
     * @param ratio      the ratio of the item
     * @param totalMoney the total money of the item
     */
    public ChartItemBean(int sImageId, String type, float ratio, float totalMoney) {
        this.sImageId = sImageId;
        this.type = type;
        this.ratio = ratio;
        this.totalMoney = totalMoney;
    }

    /**
     * Gets the image id of the item.
     *
     * @return the image id of the item
     */
    public int getsImageId() {
        return sImageId;
    }

    /**
     * Gets the type of the item.
     *
     * @return the type of the item
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the ratio of the item.
     *
     * @return the ratio of the item
     */
    public float getRatio() {
        return ratio;
    }

    /**
     * Gets the total money of the item.
     *
     * @return the total money of the item
     */
    public float getTotalMoney() {
        return totalMoney;
    }
}