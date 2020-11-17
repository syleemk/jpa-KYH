package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Embedded
    private Address address;

    //사실 멤버가 오더즈 갖고있는거 좋은 설계아님
    //쿼리입장에서 시작하더라도 주문에 이미 memberId있으니
    //주문부터 시작해서 memberId로 검색해야지
    //멤버부터 시작해서 getorders하는 건 좋지않음
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();// jpa나 hibernate에서는 관례처럼 사용함,  nullpointerException방지 등 좋은 점 있음
}
