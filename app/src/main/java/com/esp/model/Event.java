package com.esp.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by eduardo on 9/6/16.
 */
public class Event {

    private int userId;
    private String title;
    private Bitmap eventPhoto;
    private String message;
    private String longitude;
    private String latitude;
    private String address;
    private String timestamp;
    private String comments;
    private List<Integer> userIds;
    private String email;
    private List<String> linkPhotoVid;

    public Event(int userId, String title, Bitmap eventPhoto, String message, String longitude,
                 String latitude, String address, String timestamp, String comments, List<Integer> userIds,
                 String email, List<String> linkPhotoVid){
        this.userId = userId;
        this.title = title;
        this.eventPhoto = eventPhoto;
        this.message = message;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.timestamp = timestamp;
        this.comments = comments;
        this.userIds = userIds;
        this.email = email;
        this.linkPhotoVid = linkPhotoVid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(Bitmap eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getLinkPhotoVid() {
        return linkPhotoVid;
    }

    public void setLinkPhotoVid(List<String> linkPhotoVid) {
        this.linkPhotoVid = linkPhotoVid;
    }

}
