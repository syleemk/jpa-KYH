package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    //셀프조인가능
    // ToOne은 레이지로 바꿔줘야해
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    //셀프조인가능
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", // 중간테이블 이름
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), //내가 조인하는 얘
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID") //반대쪽이 잡아주는얘
    )
    private List<Item> items = new ArrayList<>();
}
