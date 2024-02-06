package com.ll.queryDSL.post;


import com.ll.queryDSL.PostingForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String main(Model model){
        List<Post> postList = postService.getList();
        model.addAttribute("postList", postList);
        return "main";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    @GetMapping("/posting")
    public String posting(PostingForm postingForm) {
        return "posting";
    }

    @PostMapping("/save")
    public String save(@Valid PostingForm postingForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "posting";
        }
        postService.save(postingForm.getSubject(), postingForm.getContent());
        return "redirect:/list";
    }

}
