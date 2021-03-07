package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        // 빈화면임에도 빈 객체를 들고간다. -> 벨리데이션으로 사용될 수도 있음.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        // @Valid : @NotEmpty와 같은 벨리데이션 처리
        // BindingResult : valid에서 걸리더라도 소스가 실행됨
        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; // 저장후에 리다이렉트 처리하는것이 다.
    }

    @GetMapping("members")
    public String list(Model model){
        // 예제가 단순해서 Entity사용을 한것. 보편적으로는 DTO통해서 뿌리는것이 좋다.
        // 또한, 화면에 뿌릴때(템플릿엔)는 어차피 서버단에서 도는것임. API설계시에는 절대! Entity를 반환해선 안된다.
        // 왜냐하면, Entity수정시 API스펙이 변경되기 때문이다.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
