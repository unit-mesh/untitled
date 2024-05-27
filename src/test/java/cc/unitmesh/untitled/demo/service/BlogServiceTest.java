package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.entity.BlogPost;
import cc.unitmesh.untitled.demo.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BlogServiceTest {

    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @Test
    public void should_update_blog() {
        // given
        BlogPost existingBlog = new BlogPost("Existing Title", "Existing Content", "Existing Author");
        existingBlog.setId(1L);
        BlogPost newBlog = new BlogPost("New Title", "New Content", "New Author");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(existingBlog));
        when(blogRepository.save(any(BlogPost.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        BlogPost updatedBlog = blogService.updateBlog(1L, newBlog);

        // then
        assertEquals("New Title", updatedBlog.getTitle());
        assertEquals("New Content", updatedBlog.getContent());
    }

    @Test
    public void should_return_null_when_blog_not_found() {
        // given
        BlogPost newBlog = new BlogPost("New Title", "New Content", "New Author");
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        BlogPost updatedBlog = blogService.updateBlog(1L, newBlog);

        // then
        assertNull(updatedBlog);
    }
}