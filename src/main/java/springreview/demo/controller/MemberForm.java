package springreview.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import springreview.demo.domain.Member;

/*
    웹 등록 화면에서 데이터를 전달 받을 폼 객체
 */
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
