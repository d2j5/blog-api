package com.example.blog.controller;

import com.example.blog.exception.BlogPostNotFoundException;
import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping
    public ResponseEntity<?> createBlogPost(@Valid @RequestBody BlogPost blogPost, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(result));
        }
        BlogPost createdPost = blogPostService.createBlogPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        return ResponseEntity.ok(blogPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BlogPost>> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            EntityModel<BlogPost> resource = EntityModel.of(blogPost.get());
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BlogPostController.class)
                    .getBlogPostById(id)).withSelfRel());

            return ResponseEntity.ok(resource);
        } else {
            throw new BlogPostNotFoundException("Blog post not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlogPost(@PathVariable Long id, @Valid @RequestBody BlogPost blogPost,
                                            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(result));
        }
        try {
            BlogPost updatedPost = blogPostService.updateBlogPost(id, blogPost);
            return ResponseEntity.ok(updatedPost);
        } catch (BlogPostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable Long id) {
        try {
            boolean isDeleted = blogPostService.deleteBlogPostById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Blog post deleted successfully");
            } else {
                // This should not be reached as the exception will be thrown for non-existent blog posts
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog post not found with id: " + id);
            }
        } catch (BlogPostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    private Map<String, String> getValidationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseEntity<String> handleBlogPostNotFoundException(BlogPostNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

