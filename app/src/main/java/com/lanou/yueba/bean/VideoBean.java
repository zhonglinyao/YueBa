package com.lanou.yueba.bean;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p/>
 * Created by 程洪运 on 16/10/24.
 */
public class VideoBean {

    private long userId;
    private String channelName;
    private String avatar;
    private String channelIntro;
    private long videoId;
    private String title;
    private String link;
    private String linkMp4;
    private String cover;
    private String intro;
    private String tag;
    private int duration;
    private int playCount;
    private int playCountReal;
    private boolean hasFavor;
    private long uploadTime;
    private String setName;
    private int setNum;
    private boolean newest;
    private int downloadable;
    private int isLock;
    private int previewDuration;
    private int unlockSeed;
    private Object unlockCount;

    @Override
    public String toString() {
        return "VideoBean{" +
                "avatar='" + avatar + '\'' +
                ", userId=" + userId +
                ", channelName='" + channelName + '\'' +
                ", channelIntro='" + channelIntro + '\'' +
                ", videoId=" + videoId +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", linkMp4='" + linkMp4 + '\'' +
                ", cover='" + cover + '\'' +
                ", intro='" + intro + '\'' +
                ", tag='" + tag + '\'' +
                ", duration=" + duration +
                ", playCount=" + playCount +
                ", playCountReal=" + playCountReal +
                ", hasFavor=" + hasFavor +
                ", uploadTime=" + uploadTime +
                ", setName='" + setName + '\'' +
                ", setNum=" + setNum +
                ", newest=" + newest +
                ", downloadable=" + downloadable +
                ", isLock=" + isLock +
                ", previewDuration=" + previewDuration +
                ", unlockSeed=" + unlockSeed +
                ", unlockCount=" + unlockCount +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getChannelIntro() {
        return channelIntro;
    }

    public void setChannelIntro(String channelIntro) {
        this.channelIntro = channelIntro;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkMp4() {
        return linkMp4;
    }

    public void setLinkMp4(String linkMp4) {
        this.linkMp4 = linkMp4;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getPlayCountReal() {
        return playCountReal;
    }

    public void setPlayCountReal(int playCountReal) {
        this.playCountReal = playCountReal;
    }

    public boolean isHasFavor() {
        return hasFavor;
    }

    public void setHasFavor(boolean hasFavor) {
        this.hasFavor = hasFavor;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getSetNum() {
        return setNum;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }

    public boolean isNewest() {
        return newest;
    }

    public void setNewest(boolean newest) {
        this.newest = newest;
    }

    public int getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(int downloadable) {
        this.downloadable = downloadable;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public int getPreviewDuration() {
        return previewDuration;
    }

    public void setPreviewDuration(int previewDuration) {
        this.previewDuration = previewDuration;
    }

    public int getUnlockSeed() {
        return unlockSeed;
    }

    public void setUnlockSeed(int unlockSeed) {
        this.unlockSeed = unlockSeed;
    }

    public Object getUnlockCount() {
        return unlockCount;
    }

    public void setUnlockCount(Object unlockCount) {
        this.unlockCount = unlockCount;
    }
}
