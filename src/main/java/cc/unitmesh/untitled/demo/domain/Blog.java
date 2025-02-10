package cc.unitmesh.untitled.demo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String author;

    // Getters and Setters
}