package jpabook.jpashop.api.controller;

import jpabook.jpashop.api.dto.*;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/v1/members")
    public MemberResponse saveMemberV2(@RequestBody MemberSaveRequest request){
        Member member = request.toEntity();
        Long id = memberService.join(member);
        Member savedMember = memberService.findOne(id);
        return new MemberResponse(savedMember);
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

    @GetMapping("v1/members")
    public Result members(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberResponse> collect = findMembers.stream().map(member ->
                new MemberResponse(member)
        ).collect(Collectors.toList());

        return new Result(collect);
    }
}
