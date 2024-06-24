package vtc.oldcookie.paymark.db;

/**
 * Class representing a type of item.
 * LAU Cho Cheuk
 */
public class TypeBean {
    int id;
    String typename;
    int imageId;
    int simageId;
    int kind;

    /**
     * Constructor for TypeBean.
     *
     * @param id       the id of the type
     * @param typename the name of the type
     * @param imageId  the image id of the type
     * @param simageId the secondary image id of the type
     * @param kind     the kind of the type
     */
    public TypeBean(int id, String typename, int imageId, int simageId, int kind) {
        this.id = id;
        this.typename = typename;
        this.imageId = imageId;
        this.simageId = simageId;
        this.kind = kind;
    }

    /**
     * Gets the id of the type.
     *
     * @return the id of the type
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the type.
     *
     * @return the name of the type
     */
    public String getTypename() {
        return typename;
    }

    /**
     * Gets the image id of the type.
     *
     * @return the image id of the type
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Gets the secondary image id of the type.
     *
     * @return the secondary image id of the type
     */
    public int getSimageId() {
        return simageId;
    }

    /**
     * Gets the kind of the type.
     *
     * @return the kind of the type
     */
    public int getKind() {
        return kind;
    }
}