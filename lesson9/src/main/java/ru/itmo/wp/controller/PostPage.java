package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping(path = "/post/{id}")
    public String postPageGet(Model model, HttpSession httpSession, @PathVariable String id) {
        Post post;
        try {
            post = postService.findById(Long.parseLong(id));
        } catch (NumberFormatException ignored) {
            putMessage(httpSession, "Invalid post id");
            return "redirect:";
        }
        if (post == null) {
            putMessage(httpSession, "Post not found");
            return "redirect:";
        }
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @PostMapping(path = "/post/{id}")
    public String postPagePost(Model model, @Valid @ModelAttribute("comment") Comment comment,
                               BindingResult bindingResult, HttpSession httpSession, @PathVariable String id) {
        Post post;
        try {
            post = postService.findById(Long.parseLong(id));
        } catch (NumberFormatException ignored) {
            putMessage(httpSession, "Invalid post id");
            return "redirect:";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }
        if (post == null) {
            putMessage(httpSession, "Post not found");
            return "redirect:";
        }
        comment.setPost(post);
        comment.setUser(getUser(httpSession));
        post.getComments().add(comment);
        postService.save(post);
        putMessage(httpSession, "You add new comment");
        return "redirect:/post/" + id;
    }
}
