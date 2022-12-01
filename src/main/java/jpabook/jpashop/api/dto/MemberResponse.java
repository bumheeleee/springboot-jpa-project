package jpabook.jpashop.api.dto;

import jpabook.jpashop.domain.Member;
import lombok.Data;

@Data
public class MemberResponse {
    private String name;
    private String city;

    private String street;

    private String zipcode;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.city = member.getAddress().getCity();
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }
}
