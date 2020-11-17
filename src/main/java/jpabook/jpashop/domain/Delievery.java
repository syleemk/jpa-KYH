package jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delievery extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "DELIEVERY_ID")
    private Long id;

    @Embedded
    private Address address;
    private DelieveryStatus delieveryStatus;

    @OneToOne(mappedBy = "delievery", fetch = LAZY)
    private Order order;
}
