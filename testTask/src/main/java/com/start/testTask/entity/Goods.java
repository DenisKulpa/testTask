package com.start.testTask.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="goods")
public class Goods {

    @Id
    @Column(name = "goods_id")
    @SequenceGenerator(name = "goods_seq", sequenceName = "goods_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "goods")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(orders, goods.orders);
    }

    @Override
    public int hashCode() {

        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;

    }
}
