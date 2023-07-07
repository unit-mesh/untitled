package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.dto.CreateBlogDto;
import cc.unitmesh.untitled.demo.entity.BlogPost;
import cc.unitmesh.untitled.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public BlogPost createBlog(CreateBlogDto blogDto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogDto.getTitle());
        blogPost.setContent(blogDto.getContent());
        blogPost.setAuthor(blogDto.getAuthor());
        return blogRepository.save(blogPost);
    }

    public List<BlogPost> getAllBlogPosts() {
        // todo
        return new ArrayList();
    }
}
