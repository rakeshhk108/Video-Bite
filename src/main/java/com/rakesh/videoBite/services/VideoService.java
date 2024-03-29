package com.rakesh.videoBite.services;


import com.rakesh.videoBite.dto.CommentDto;
import com.rakesh.videoBite.dto.UploadVideoResponse;
import com.rakesh.videoBite.dto.VideoDto;
import com.rakesh.videoBite.model.Comment;
import com.rakesh.videoBite.model.Video;
import com.rakesh.videoBite.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {



    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    private final UserService userService;


    public VideoDto editVideo(VideoDto videoDto) {
        // find the video of the given id

        var video = getVideoById(videoDto.getId());

       //Update the video using the video dto
        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setVideoUrl(videoDto.getVideoUrl());
        video.setVideoStatus(videoDto.getVideoStatus());
        video.setTumbnail(videoDto.getTumbnail());
        video.setTags(videoDto.getTags());

        //save the video back to the database
        videoRepository.save(video);

        return videoDto;
    }


    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){

        //use aws s3 service to save the video
        String url = s3Service.uploadfile(multipartFile);

        Video video = new Video();
        video.setVideoUrl(url);
        // save the data of the video in database
        videoRepository.save(video);

        //make a uploadVideoResponse object

        return new UploadVideoResponse(url , video.getId() );
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        var video = getVideoById(videoId);

        String thumbnailUrl = s3Service.uploadfile(file);

        video.setTumbnail(thumbnailUrl);

        videoRepository.save(video);

        return thumbnailUrl;
    }

    Video getVideoById(String videoId){
        return videoRepository.findById(videoId).orElseThrow(()-> new IllegalArgumentException("There is no video with the following id"));
    }

    public VideoDto getVideoDetails(String videoId) {
        Video savedVideo = getVideoById(videoId);

        increaseVideoCount(savedVideo);
        userService.addVideoToHistory(videoId);

        return mapToVideoDto(savedVideo);

    }

    private void increaseVideoCount(Video video) {
        video.incrementViewCount();
        videoRepository.save(video);
    }

    public VideoDto setLikeVideo(String videoId) {
        //get the video by id
        Video savedVideo = getVideoById(videoId);

        //Increment the Like Count
        //IF user already Liked the video, then decrement the like count
        //likes - 0, dislike - 0
        //likes - 1 , dislike - 0
        //likes - 0 , dislike - 0

        //likes - 0 , dislike - 1
        //likes - 1 , dislike - 0

        //if the user already disliked the video, then increment like count and decrement dislike count

        if(userService.ifLikedVideos(videoId))
        {
            savedVideo.decrementLikes();
            userService.removeFormLikeVideo(videoId);
        }
        else if(userService.ifDisLikedVideos(videoId))
        {
            savedVideo.decrementDisLikes();
            userService.removeFromDislikeVideos(videoId);
            savedVideo.incrementLikes();
            userService.addToLikedVideos(videoId);
        }
        else {
            savedVideo.incrementLikes();
            userService.addToLikedVideos(videoId);
        }

        videoRepository.save(savedVideo);

        return mapToVideoDto(savedVideo);


    }




    public VideoDto setDisLikeVideo(String videoId) {
        Video savedVideo = getVideoById(videoId);

        //Increment the Like Count
        //IF user already Liked the video, then decrement the like count
        //likes - 0, dislike - 0
        //likes - 1 , dislike - 0
        //likes - 0 , dislike - 0

        //likes - 0 , dislike - 1
        //likes - 1 , dislike - 0

        //if the user already disliked the video, then increment like count and decrement dislike count

        if(userService.ifDisLikedVideos(videoId))
        {
            savedVideo.decrementDisLikes();
            userService.removeFromDislikeVideos(videoId);
        }
        else if(userService.ifLikedVideos(videoId))
        {
            savedVideo.decrementLikes();
            userService.removeFormLikeVideo(videoId);
            savedVideo.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        }
        else {
            savedVideo.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        }

        videoRepository.save(savedVideo);

        return  mapToVideoDto(savedVideo);



    }

    private static VideoDto mapToVideoDto(Video savedVideo) {
        VideoDto videoDto = new VideoDto();
        videoDto.setId(savedVideo.getId());
        videoDto.setTitle(savedVideo.getTitle());
        videoDto.setDescription(savedVideo.getDescription());
        videoDto.setTags(savedVideo.getTags());
        videoDto.setVideoStatus(savedVideo.getVideoStatus());
        videoDto.setVideoUrl(savedVideo.getVideoUrl());
        videoDto.setTumbnail(savedVideo.getTumbnail());
        videoDto.setLikeCount(savedVideo.getLikes().get());
        videoDto.setDisLikeCount(savedVideo.getDisLikes().get());
        videoDto.setViewCount(savedVideo.getViewCount().get());
        return videoDto;
    }

    public void addComment(String videoId, CommentDto commentDto) {
        //get video by id
        Video video = getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDto.getCommentText());
        comment.setAuthouId(commentDto.getAuthorId());
        video.addComent(comment);
        videoRepository.save(video);
    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();

        return commentList.stream()
                .map(this::mapToCommentDto)
                .collect(Collectors.toList());

    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(commentDto.getAuthorId());

        return commentDto;
    }

    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(VideoService::mapToVideoDto).toList();
    }


}
