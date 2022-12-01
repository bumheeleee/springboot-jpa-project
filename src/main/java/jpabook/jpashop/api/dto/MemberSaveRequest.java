package jpabook.jpashop.api.dto;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.Data;

@Data
public class MemberSaveRequest {

    private String name;

    private String city;

    private String street;

    private String zipcode;

    public Member toEntity() {
        return new Member(this.name, new Address(this.city, this.street, this.zipcode));
    }


}
