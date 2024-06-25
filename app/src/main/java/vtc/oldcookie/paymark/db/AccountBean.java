package vtc.oldcookie.paymark.db;

/**
 * Class representing an account.
 */
public class AccountBean {
    int id;
    String typename;
    int sImageId;
    String comment;
    float money;
    String time;
    int year;
    int month;
    int day;
    int kind;

    /**
     * Default constructor for AccountBean.
     */
    public AccountBean() {
    }

    /**
     * Constructor for AccountBean.
     *
     * @param id       the id of the account
     * @param typename the type name of the account
     * @param sImageId the image id of the account
     * @param comment  the comment of the account
     * @param money    the money of the account
     * @param time     the time of the account
     * @param year     the year of the account
     * @param month    the month of the account
     * @param day      the day of the account
     * @param kind     the kind of the account
     */
    public AccountBean(int id, String typename, int sImageId, String comment, float money, String time, int year, int month, int day, int kind) {
        this.id = id;
        this.typename = typename;
        this.sImageId = sImageId;
        this.comment = comment;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }

    /**
     * Gets the id of the account.
     *
     * @return the id of the account
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the account.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the type name of the account.
     *
     * @return the type name of the account
     */
    public String getTypename() {
        return typename;
    }

    /**
     * Sets the type name of the account.
     *
     * @param typename the type name to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * Gets the image id of the account.
     *
     * @return the image id of the account
     */
    public int getsImageId() {
        return sImageId;
    }

    /**
     * Sets the image id of the account.
     *
     * @param sImageId the image id to set
     */
    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    /**
     * Gets the comment of the account.
     *
     * @return the comment of the account
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the account.
     *
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the money of the account.
     *
     * @return the money of the account
     */
    public float getMoney() {
        return money;
    }

    /**
     * Sets the money of the account.
     *
     * @param money the money to set
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     * Gets the time of the account.
     *
     * @return the time of the account
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the account.
     *
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the year of the account.
     *
     * @return the year of the account
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the account.
     *
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the month of the account.
     *
     * @return the month of the account
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month of the account.
     *
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets the day of the account.
     *
     * @return the day of the account
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of the account.
     *
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets the kind of the account.
     *
     * @return the kind of the account
     */
    public int getKind() {
        return kind;
    }

    /**
     * Sets the kind of the account.
     *
     * @param kind the kind to set
     */
    public void setKind(int kind) {
        this.kind = kind;
    }
}