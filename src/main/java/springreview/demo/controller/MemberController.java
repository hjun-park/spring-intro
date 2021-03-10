package springreview.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springreview.demo.domain.Member;
import springreview.demo.service.MemberService;

import java.util.List;

/*
    2-2) 회원 등록
 */

// 회원 컨트롤러가 서비스와 리포지토리를 사용할 수 있게 의존관계 준비
// 스프링 빈에 자동으로 등록하게 하려면 아래의 어노테이션으로 객체를 등록한다.
// @Component / @Controller / @Repository / @Service
//@Controller
public class MemberController {

    private final MemberService memberService;

    // Autowired는 스프링이 알아서 스프링 컨테이너에 자동으로 주입해준다.
    // 의존관계를 외부에서 넣어주는 걸 DI ( Dependency Injection )
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원 컨트롤러에서 회원을 실제 등록하는 기능
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    // 회원 컨트롤러에서 조회 기능
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}

