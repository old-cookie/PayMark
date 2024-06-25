package vtc.oldcookie.paymark;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import vtc.oldcookie.paymark.db.ChartItemBean;

public class ChartItemBeanTest {
    private ChartItemBean chartItemBean;

    @Before
    public void setUp() {
        chartItemBean = new ChartItemBean(1, "Test", 0.5f, 100.0f);
    }

    @Test
    public void testGetsImageId() {
        assertEquals(1, chartItemBean.getsImageId());
    }

    @Test
    public void testGetType() {
        assertEquals("Test", chartItemBean.getType());
    }

    @Test
    public void testGetRatio() {
        assertEquals(0.5f, chartItemBean.getRatio(), 0.0f);
    }

    @Test
    public void testGetTotalMoney() {
        assertEquals(100.0f, chartItemBean.getTotalMoney(), 0.0f);
    }
}