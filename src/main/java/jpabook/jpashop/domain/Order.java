package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "ORDERS") // db에 order by라고 order가 예약어로 걸려있는 경우가 있어서 orders로 매핑
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID") //외래키
    private Member member; //외래키 가진쪽이 연관관계 주인

    //이렇게는 많이 씀
    //주문서 기준 어떤 주문 목록있는지 많이 찾으니까
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //연관관계의 주인 넣어준다
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //주문을 생성할 때, 배송정보도 같이 생성하겠다는 의미, 라이프 사이클을 맞추겟다는 의미
    //Order를 생성해서 delievery를 넣을 때, order를 영속화하면 자동으로 delievery도 저장이됨
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIEVERY_ID")
    private Delievery delievery;

    //연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
