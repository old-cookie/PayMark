package vtc.oldcookie.paymark;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import vtc.oldcookie.paymark.db.TypeBean;

public class TypeBeanTest {
    private TypeBean typeBean;

    @Before
    public void setUp() {
        typeBean = new TypeBean(1, "Food", 101, 201, 0);
    }

    @Test
    public void testGetId() {
        assertEquals(1, typeBean.getId());
    }

    @Test
    public void testGetTypename() {
        assertEquals("Food", typeBean.getTypename());
    }

    @Test
    public void testGetImageId() {
        assertEquals(101, typeBean.getImageId());
    }

    @Test
    public void testGetSimageId() {
        assertEquals(201, typeBean.getSimageId());
    }

    @Test
    public void testGetKind() {
        assertEquals(0, typeBean.getKind());
    }
}