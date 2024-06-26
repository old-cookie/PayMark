package vtc.oldcookie.paymark;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import vtc.oldcookie.paymark.db.AccountBean;

public class AccountBeanTest {
    private AccountBean accountBean;

    @Before
    public void setUp() {
        accountBean = new AccountBean(1, "Test", 1, "Test Comment", 100.0f, "12:00", 2024, 1, 1, 1);
    }

    @Test
    public void testId() {
        assertEquals(1, accountBean.getId());
    }

    @Test
    public void testTypename() {
        assertEquals("Test", accountBean.getTypename());
    }

    @Test
    public void testSImageId() {
        assertEquals(1, accountBean.getsImageId());
    }

    @Test
    public void testComment() {
        assertEquals("Test Comment", accountBean.getComment());
    }

    @Test
    public void testMoney() {
        assertEquals(100.0f, accountBean.getMoney(), 0.0f);
    }

    @Test
    public void testTime() {
        assertEquals("12:00", accountBean.getTime());
    }

    @Test
    public void testYear() {
        assertEquals(2024, accountBean.getYear());
    }

    @Test
    public void testMonth() {
        assertEquals(1, accountBean.getMonth());
    }

    @Test
    public void testDay() {
        assertEquals(1, accountBean.getDay());
    }

    @Test
    public void testKind() {
        assertEquals(1, accountBean.getKind());
    }
}