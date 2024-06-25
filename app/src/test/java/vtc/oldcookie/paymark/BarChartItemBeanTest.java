package vtc.oldcookie.paymark;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import vtc.oldcookie.paymark.db.BarChartItemBean;

public class BarChartItemBeanTest {
    private BarChartItemBean barChartItemBean;

    @Before
    public void setUp() {
        barChartItemBean = new BarChartItemBean(2024, 1, 1, 100.0f);
    }

    @Test
    public void testGetYear() {
        assertEquals(2024, barChartItemBean.getYear());
    }

    @Test
    public void testGetMonth() {
        assertEquals(1, barChartItemBean.getMonth());
    }

    @Test
    public void testGetDay() {
        assertEquals(1, barChartItemBean.getDay());
    }

    @Test
    public void testGetSummoney() {
        assertEquals(100.0f, barChartItemBean.getSummoney(), 0.0f);
    }
}