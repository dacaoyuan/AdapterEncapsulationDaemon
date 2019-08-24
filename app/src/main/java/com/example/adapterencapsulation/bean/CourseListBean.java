package com.example.adapterencapsulation.bean;

/**
 * @Author: YuanPeikai
 * @CreateDate: 2019/8/23 17:18
 * @Description:
 */
public class CourseListBean {

    private String coverImgUrl;
    private int putId;
    private int studyPersonCount;
    private int videoLength;
    private String videoName;
    private int item_type;

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public int getPutId() {
        return putId;
    }

    public void setPutId(int putId) {
        this.putId = putId;
    }

    public int getStudyPersonCount() {
        return studyPersonCount;
    }

    public void setStudyPersonCount(int studyPersonCount) {
        this.studyPersonCount = studyPersonCount;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
