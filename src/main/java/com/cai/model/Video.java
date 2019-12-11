package com.cai.model;

import java.util.ArrayList;
import java.util.List;

public class Video {
    private int videoId;
    private String videoName;
    private String videoUserName;

    public String getVideoUserName() {
        return videoUserName;
    }

    public void setVideoUserName(String videoUserName) {
        this.videoUserName = videoUserName;
    }

    public Video(){

    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public void setVideoId(int videoId) {

        this.videoId = videoId;
    }


    public String getVideoName() {
        return videoName;
    }

    public int getVideoId() {
        return videoId;
    }


    public static List<Video> getVideos(int page,int size,List<Video> videos){
//        List<Video> videos = new ArrayList<>();
        int start = (page - 1)*size;
        int end  = Math.min(videos.size(), page * size);
        return videos.subList(start,end);
    }
}
