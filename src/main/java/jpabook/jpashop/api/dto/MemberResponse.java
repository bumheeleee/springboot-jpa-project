package jpabook.jpashop.api.dto;

import lombok.Data;

@Data
public class MemberResponse {
    private Long id;

    public MemberResponse(Long id) {
        this.id = id;
    }
}
