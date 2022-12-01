package jpabook.jpashop.api.dto;

import jpabook.jpashop.domain.Member;
import lombok.Getter;

@Getter
public class MemberRequest {

    private String name;

    public Member toEntity() {
        return new Member(this.getName());
    }
}
