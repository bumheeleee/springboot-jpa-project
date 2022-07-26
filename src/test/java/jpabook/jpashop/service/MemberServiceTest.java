package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    /**
     * 테스트 요구사항
     * 1. H2 메모리 데이터 베이스를 사용한다 (O)
     * 2. 회원가입을 성공해야 한다. (O)
     * 3. 회원가입 할 때 같은 이름이 있으면 예외가 발생해야 한다. (O)
     */

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long joinId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(joinId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        memberService.join(member2);    //동일한 이름으로 회원가입을 했기 때문에 예외가 발생해야됨!

        //then
        fail("여기코드가 실행되면 안됨~");
    }

}