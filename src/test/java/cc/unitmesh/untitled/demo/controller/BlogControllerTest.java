package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.dto.CreateBlogRequest;
import cc.unitmesh.untitled.demo.dto.CreateBlogResponse;
import cc.unitmesh.untitled.demo.entity.Author;
import cc.unitmesh.untitled.demo.entity.BlogPost;
import cc.unitmesh.untitled.demo.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(BlogController.class)
public class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BlogService blogService;

    @Test
    public void should_get_blog_when_valid_id_provided() throws Exception {
        // given
        Long blogId = 1L;
        BlogPost expectedBlog = new BlogPost("Test Title", "Test Content", "Test Author");
        expectedBlog.setId(blogId);

        when(blogService.getBlogById(blogId)).thenReturn(expectedBlog);

        // when
        var result = mockMvc.perform(get("/blog/{id}", blogId))
                .andExpect(status().isOk())
                .andReturn();

        // then
        BlogPost actualBlog = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                BlogPost.class
        );
        assertThat(actualBlog.getId()).isEqualTo(expectedBlog.getId());
        assertThat(actualBlog.getTitle()).isEqualTo(expectedBlog.getTitle());
        assertThat(actualBlog.getContent()).isEqualTo(expectedBlog.getContent());
        assertThat(actualBlog.getAuthor()).isEqualTo(expectedBlog.getAuthor());
    }

    @Test
    public void should_create_blog_when_valid_request_provided() throws Exception {
        // given
        CreateBlogRequest request = new CreateBlogRequest();
        request.setTitle("New Title");
        request.setContent("New Content");
        request.setAuthor(new Author("New Author"));

        BlogPost savedBlog = new BlogPost("New Title", "New Content", "New Author");
        savedBlog.setId(1L);

        when(blogService.createBlog(any(BlogPost.class))).thenReturn(savedBlog);

        // when
        var result = mockMvc.perform(post("/blog/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // then
        BlogPost response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                BlogPost.class
        );
        assertThat(response.getId()).isEqualTo(savedBlog.getId());
        assertThat(response.getTitle()).isEqualTo(savedBlog.getTitle());
        assertThat(response.getContent()).isEqualTo(savedBlog.getContent());
        assertThat(response.getAuthor()).isEqualTo(savedBlog.getAuthor());
    }

    @Test
    public void should_delete_blog_when_valid_id_provided() throws Exception {
        // given
        Long blogId = 1L;

        // when & then
        mockMvc.perform(delete("/blog/{id}", blogId))
                .andExpect(status().isOk());
    }
}
