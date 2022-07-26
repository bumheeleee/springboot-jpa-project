package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입 기능
     * 중복 회원 제거
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("동일한 회원이 이미 존재합니다.");
        }
    }

    /**
     * 회원전체 조회 기능
     * @Transactional(readOnly = true),조회하는 부분에서 JPA가 최적화를 해줌
     */
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * @Transactional(readOnly = true),조회하는 부분에서 JPA가 최적화를 해줌
     */
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
