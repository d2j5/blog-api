package com.example.blog;

import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Blog implements CommandLineRunner {
    private final BlogPostService blogPostService;

    @Autowired
    public Blog(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @Override
    public void run(String... args) throws Exception {
        BlogPost blog1 = new BlogPost();
        blog1.setTitle("Introduction to Java Programming");
        blog1.setContent("Java is a powerful and versatile programming language. It is widely used in the software development industry for building a variety of applications.");
        blog1.setAuthor("Javier");
        blogPostService.createBlogPost(blog1);

        BlogPost blog2 = new BlogPost();
        blog2.setTitle("Tips for Effective Time Management");
        blog2.setContent("Time management is essential for productivity and success. Here are some tips to help you manage your time effectively: prioritize tasks, set clear goals, eliminate distractions, and take regular breaks.");
        blog2.setAuthor("Alex");
        blogPostService.createBlogPost(blog2);

        BlogPost blog3 = new BlogPost();
        blog3.setTitle("The Benefits of Regular Exercise");
        blog3.setContent("Regular exercise has numerous benefits for both physical and mental health. It helps improve cardiovascular fitness, boost mood, reduce stress, and increase overall well-being.");
        blog3.setAuthor("Samantha");
        blogPostService.createBlogPost(blog3);
    }

    public void addBlogPost(BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
    }

    public void removeBlogPost(BlogPost blogPost) {
        blogPostService.deleteBlogPostById(blogPost.getId());
    }
}
