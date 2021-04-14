package org.dongho.b6.controller;

import lombok.extern.log4j.Log4j2;
import org.dongho.b6.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public void ex1(){
        log.info("ex1.........................");
    }

    @GetMapping("/ex2")
    public void ex2(Model model){
        // Long 값을 넣으면 Object가 출력
        List<SampleDTO> list = IntStream.rangeClosed(1,100).asLongStream()
                .mapToObj(i -> {
                             return SampleDTO.builder()
                    .sno(i)
                    .first("first"+i)
                    .last("last"+i)
                    .regTime(LocalDateTime.now())
                    .build();
        }).collect(Collectors.toList());

        model.addAttribute("list" , list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes){
        // Long 값을 넣으면 Object가 출력
        SampleDTO dto = SampleDTO.builder().sno(100L).first("first..100").last("last...100").regTime(LocalDateTime.now()).build();

       redirectAttributes.addFlashAttribute("result" , "success");
       redirectAttributes.addFlashAttribute("dto" , dto);

       return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3(){
        log.info("ex3");
    }

    @GetMapping("/testLayout")
    public void testLayout(){
        log.info("test layout................");
    }

}
