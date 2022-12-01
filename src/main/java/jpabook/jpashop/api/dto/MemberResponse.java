package jpabook.jpashop.api.dto;

import lombok.Getter;

@Getter
public class MemberResponse {
    private Long id;

    public MemberResponse(Long id) {
        this.id = id;
    }
}
