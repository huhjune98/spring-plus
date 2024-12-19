package com.example.demo;

import com.example.demo.entity.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemEntityTest {

    @Test
    public void testItemEntityConstructor() {
        // given
        Item item = new Item("Item1", "Description", null, null);

        // when
        String status = item.getStatus();

        // then
        assertNotNull(status, "status는 null이 아니어야 한다.");
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }

    @Test
    public void testItemStatusNotNullConstraint() {
        // given
        Item item = new Item("Item2", "Description", null, null);

        // when
        item.setStatus(null);

        // then
        // status는 'nullable = false' 이므로 null일 수 없으므로 예외가 발생해야 한다.
        assertThrows(NullPointerException.class, item::getStatus, "status는 null일 수 없습니다.");
    }

    @Test
    public void testDynamicInsert() {
        // given
        Item item = new Item("Item3", "Description", null, null);

        // when
        String status = item.getStatus();

        // then
        // @DynamicInsert가 동작하여 기본값 'PENDING'이 설정되어야 한다.
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }
}
