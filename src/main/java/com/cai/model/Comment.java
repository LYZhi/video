package com.cai.model;

import java.util.List;

public class Comment {
    private int commentId;
    private String comment;
    private String commentUserName;



    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public static List<Comment> getComments(int page, int size, List<Comment> Comments){
//        List<Video> videos = new ArrayList<>();
        int start = (page - 1)*size;
        int end  = Math.min(Comments.size(), page * size);
        return Comments.subList(start,end);
    }
}
