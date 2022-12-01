package jpabook.jpashop.api.dto;

import jpabook.jpashop.domain.Member;
import lombok.Data;

@Data
public class MemberSaveRequest {

    private String name;

    public Member toEntity() {
        return new Member(this.getName());
    }
}
