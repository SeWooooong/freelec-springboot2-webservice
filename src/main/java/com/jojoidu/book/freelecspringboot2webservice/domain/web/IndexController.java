package com.jojoidu.book.freelecspringboot2webservice.domain.web;

import com.jojoidu.book.freelecspringboot2webservice.config.auth.LoginUser;
import com.jojoidu.book.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoidu.book.freelecspringboot2webservice.domain.posts.PostsService;
import com.jojoidu.book.freelecspringboot2webservice.domain.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/jun")
    public String jun(Model model){
        model.addAttribute("videoUrl","https://heronmovie.s3.ap-northeast-2.amazonaws.com/%EC%A4%80%EC%84%9D%EC%9D%B4+%EC%98%81%EC%83%81.mp4");
        return "jun";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
