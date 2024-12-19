package com.example.demo;


import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemEntityTest {

    @Test
    public void testItemConstructorWithStatus() {
        // Given
        String expectedName = "Item1";
        String expectedDescription = "Description of Item1";
        User mockUser = new User();
        User mockManager = new User();

        // When
        Item item = new Item(expectedName, expectedDescription, mockManager, mockUser);

        // Then
        assertEquals("PENDING", item.getStatus(), "status should be 'PENDING' by default");
    }

    @Test
    public void testItemEntityConstructor() {
        //given
        Item item = new Item("Item1", "Description", null, null);

        //when
        String status = item.getStatus();

        //then
        assertNotNull(status, "status는 null이 아니어야 한다.");
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }

    @Test
    public void testSetStatusThrowsExceptionWhenNull() {
        // Given
        Item item = new Item("Item2", "Description of Item2", null, null);

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            item.setStatus(null); // null을 넣으면 예외가 발생해야 함
        });

        assertEquals("status는 null일 수 없습니다.", exception.getMessage(), "예외 메시지가 일치해야 함.");
    }

    @Test
    public void testDynamicInsert() {
        //given
        Item item = new Item("Item3", "Description", null, null);

        //when
        String status = item.getStatus();

        //then
        // @DynamicInsert가 동작하여 기본값 'PENDING'이 설정되어야 한다.
        assertEquals("PENDING", status, "status는 기본값 'PENDING'이어야 한다.");
    }

    @Test
    public void testStatusUpdate() {
        // Given
        Item item = new Item("Item3", "Description of Item3", null, null);

        // When
        item.setStatus("COMPLETED");

        // Then
        assertEquals("COMPLETED", item.getStatus(), "status should be updated to 'COMPLETED'");
    }

    @Test
    public void testSetStatusThrowsExceptionWhenNullUpdate() {
        // Given
        Item item = new Item("Item4", "Description of Item4", null, null);

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            item.setStatus(null); // null을 넣으면 예외가 발생해야 함
        });

        assertEquals("status는 null일 수 없습니다.", exception.getMessage(), "예외 메시지가 일치해야 함.");
    }
}
