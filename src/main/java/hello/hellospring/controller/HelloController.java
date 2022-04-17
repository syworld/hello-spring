package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        /*
        * Controller에 리턴값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리한다.
        * spring boot template engine -> resources:templates/{ViewName}.tml
        * */
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        /*
        * @ResponseBody : HTTP body에 문자 내용을 직접 반환. HttpMessageConverter가 동작
        * 기본 문자처리: StringHttpMessageConverter
        * */
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        /*
        * 객체를 반환하면 객체가 JSON으로 변환됨
        * 기본 객체처리: MappingJackson2HttpMessageConverter
        * */
        return hello;
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) { this.name = name;
        } }

}
