package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepo;

    public Blog saveBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepo.findByUserId(userId);
    }

    public Blog getBlogById(Long id) {
        return blogRepo.findById(id).orElse(null);
    }

    public Blog updateBlog(Long id, Blog blogDetails) {
        return blogRepo.findById(id).map(blog -> {
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            blog.setCategory(blogDetails.getCategory());
            blog.setPublished(blogDetails.isPublished());
            return blogRepo.save(blog);
        }).orElse(null);
    }

    public void deleteBlog(Long id) {
        blogRepo.deleteById(id);
    }

    public List<Blog> searchBlogs(String keyword) {
        return blogRepo.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Blog> getPublishedBlogs() {
        return blogRepo.findByPublishedTrue();
    }
}
