package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Address {

    /**
     * 유효 규칙을 공통으로 관리할 수 있음
     * column의 length에 대한 규칙 지정
     */
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    /**
     * 값 타입의 장점은 의미있는 비즈니스 메서드를 만들 수 있다는 점
     */
    public String fullAddress(){
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    /**
     * equals에서 필드에 접근할 때 getter사용해야함
     * 필드에 직접접근하게 되면 프록시일 때 계산이 안됨
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
