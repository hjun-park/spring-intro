package springreview.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1) 정적 컨텐츠 방식
    //   - 관련 컨트롤러를 찾지만 없어서 resource/static/에 있는 static html 정적 컨텐츠를 불러옴
    // http://localhost:8080/hello-static.html
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // hello.html
    }

    // 2) MVC와 Thymeleaf 템플릿 엔진
    //   - 요청이 들어오면 컨트롤러는 뷰리졸버에게 요청 (html), 그리고 Thymeleaf 템플릿 엔진으로 요청 처리
    // http://localhost:8080/hello-mvc?name=spring
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";    // hello-template.html
    }

    // 3) API
    // http://localhost:8080/hello-string?name=spring
    @GetMapping("hello-string")
    @ResponseBody   // ResponseBody를 사용하면 뷰 리졸버 사용하지 않는 대신에 Body에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // @ResponseBody 객체 반환
    @GetMapping("hello-api")
    @ResponseBody   // ResponseBody를 사용
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체를 반환하면 객체가 JSON으로 변환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /*
       @ResponseBody 사용 원리
        1) HTTP 요청이 들어옴 (http://localhost:8080/hello-api?name=spring)
        2) HelloController 에서 GetMapping을 받음
        3) @ResponseBody가 있는 것을 확인 -> 이 경우 viewResolver 대신에 HttpMessageConverter가 동작
        4) 객체가 json형식으로 반환되어 body에 실어져 서버로 전송
     */
}

