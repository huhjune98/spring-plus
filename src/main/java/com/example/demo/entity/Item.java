package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Getter
@DynamicInsert
// TODO: 6. Dynamic Insert
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'PENDING'")
    private String status;

    public Item(String name, String description, User manager, User owner) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.owner = owner;
        this.status = "PENDING";
    }

    @PrePersist
    private void setDefaultStatus() {
        if (this.status == null) {
            this.status = "PENDING";  // 기본값 설정
        }
    }

    public Item() {
        this.status = "PENDING";
    }

    //itemEntityTest에서 사용하기 위해서, 삭제가능
    public void setStatus(String status) {
        if (status == null) {
            throw new NullPointerException("status는 null일 수 없습니다.");
        }
        this.status = status;    }
}
