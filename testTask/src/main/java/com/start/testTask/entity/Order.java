package com.start.testTask.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person personId;

    @ManyToMany
    @JoinTable(
            name = "order_list",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "goods_id", referencedColumnName = "goods_id"))
    private Set<Goods> goods;

    public Long getId() {
        return id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
//                Objects.equals(personId, order.personId) &&
                Objects.equals(goods, order.goods);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, personId, goods);
    }
}
