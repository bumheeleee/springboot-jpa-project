package jpabook.jpashop.api.controller;

import jpabook.jpashop.api.dto.MemberSaveRequest;
import jpabook.jpashop.api.dto.MemberResponse;
import jpabook.jpashop.api.dto.UpdateMemberRequest;
import jpabook.jpashop.api.dto.UpdateMemberResponse;
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
    public MemberResponse saveMemberV2(@RequestBody MemberSaveRequest request){
        Member member = request.toEntity();
        Long id = memberService.join(member);
        return new MemberResponse(id);
    }

    @PutMapping("v1/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable("id") Long id,
            @RequestBody UpdateMemberRequest updateMemberRequest
    ){
        memberService.update(id, updateMemberRequest.getName());
        Member updatedMember = memberService.findOne(id);
        return new UpdateMemberResponse(updatedMember.getId(), updatedMember.getName());
    }
}
