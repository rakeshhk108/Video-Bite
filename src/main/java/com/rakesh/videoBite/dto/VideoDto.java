package com.rakesh.videoBite.dto;

import com.rakesh.videoBite.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private VideoStatus videoStatus;
    private String videoUrl;
    private String tumbnail;
    private Integer likeCount;
    private Integer disLikeCount;

    private Integer viewCount;


}
