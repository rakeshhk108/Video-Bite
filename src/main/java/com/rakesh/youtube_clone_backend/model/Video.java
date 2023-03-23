package com.rakesh.youtube_clone_backend.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(value = "Video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer disLIkes;
    private Set<String> tags;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String videoUrl;
    private String tumbnail;
    private List<Comment> commentList;
}
