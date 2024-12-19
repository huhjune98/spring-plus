package com.example.demo;


import com.example.demo.entity.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemEntityTest {

    @Test
    public void testItemEntityConstructor() {
        Item item = new Item("Item1", "Description", null, null);

        String status = item.getStatus();

        assertNotNull(status, "status는 null이 아니어야 한다.");
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }

    @Test
    public void testItemStatusNotNullConstraint() {
        Item item = new Item("Item2", "Description", null, null);

        item.setStatus(null);

        // status는 'nullable = false' 이므로 null일 수 없으므로 예외가 발생해야 한다.
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            item.setStatus(null);  // null을 넣으면 예외가 발생해야 함
        });

        assertEquals("status는 null일 수 없습니다.", exception.getMessage(), "예외 메시지가 일치해야 함.");
    }

    @Test
    public void testDynamicInsert() {
        Item item = new Item("Item3", "Description", null, null);

        String status = item.getStatus();

        // @DynamicInsert가 동작하여 기본값 'PENDING'이 설정되어야 한다.
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }
}
