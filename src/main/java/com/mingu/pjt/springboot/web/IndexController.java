package com.mingu.pjt.springboot.web;

import com.mingu.pjt.springboot.config.auth.LoginUser;
import com.mingu.pjt.springboot.config.auth.dto.SessionUser;
import com.mingu.pjt.springboot.domain.user.User;
import com.mingu.pjt.springboot.service.posts.PostsService;
import com.mingu.pjt.springboot.web.dto.PostsResponseDto;
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

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) { model.addAttribute("userName", user.getName()); }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() { return "posts-save"; }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
