package jpabook.jpashop.api.controller;

import jpabook.jpashop.api.dto.MemberRequest;
import jpabook.jpashop.api.dto.MemberResponse;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/v1/members")
    public MemberResponse saveMember(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new MemberResponse(id);
    }

    @PostMapping("/v2/members")
    public MemberResponse saveMemberV2(@RequestBody MemberRequest request){
        Member member = request.toEntity();
        Long id = memberService.join(member);
        return new MemberResponse(id);
    }
}
