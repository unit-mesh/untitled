package cc.unitmesh.untitled.demo.domain;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Long blogId;

    // Getters and Setters
}