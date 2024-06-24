package vtc.oldcookie.paymark.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for handling float numbers.
 * LAU Cho Cheuk
 */
public class FloatUtils {
    /**
     * Divides two float numbers and rounds the result to 4 decimal places.
     *
     * @param v1 The dividend.
     * @param v2 The divisor.
     * @return The result of the division rounded to 4 decimal places.
     */
    public static float div(float v1, float v2) {
        return new BigDecimal(v1 / v2).setScale(4, RoundingMode.HALF_UP).floatValue();
    }
}