package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.dto.CreateBlogRequest;
import cc.unitmesh.untitled.demo.entity.BlogPost;
import cc.unitmesh.untitled.demo.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Test
    public void should_create_blog() throws Exception {
        // given
        BlogPost blogPost = new BlogPost("Test Title", "Test Content", "Test Author");
        blogPost.setId(1L);
        when(blogService.createBlog(any(BlogPost.class))).thenReturn(blogPost);

        // when-then
        mockMvc.perform(MockMvcRequestBuilders.post("/blog/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Title\",\"content\":\"Test Content\",\"author\":{\"name\":\"Test Author\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test Content")))
                .andExpect(jsonPath("$.author", is("Test Author")));
    }
}