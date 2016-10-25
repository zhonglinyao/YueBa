package com.lanou.yueba.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class NewsBean {
    private String message;

    private DataBean data;
    private String code;
    private int success;

    @Override
    public String toString() {
        return "NewsBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public static class DataBean {
        private int total;

        private List<ItemsBean> items;

        @Override
        public String toString() {
            return "DataBean{" +
                    "items=" + items +
                    ", total=" + total +
                    '}';
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String summary;
            private String createTime;
            private String publishTime;
            private int clickCount;
            private int replyCount;
            private String tag;
            private String categoryId;
            private String noteId;
            private String pic;
            private String url;
            private String authorId;
            private String categoryName;
            private String author;
            private String sectionId;
            private String title;
            private int shortTitle;
            private String isTop;

            private List<PicListBean> picList;

            @Override
            public String toString() {
                return "ItemsBean{" +
                        "author='" + author + '\'' +
                        ", summary='" + summary + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", publishTime='" + publishTime + '\'' +
                        ", clickCount=" + clickCount +
                        ", replyCount=" + replyCount +
                        ", tag='" + tag + '\'' +
                        ", categoryId='" + categoryId + '\'' +
                        ", noteId='" + noteId + '\'' +
                        ", pic='" + pic + '\'' +
                        ", url='" + url + '\'' +
                        ", authorId='" + authorId + '\'' +
                        ", categoryName='" + categoryName + '\'' +
                        ", sectionId='" + sectionId + '\'' +
                        ", title='" + title + '\'' +
                        ", shortTitle=" + shortTitle +
                        ", isTop='" + isTop + '\'' +
                        ", picList=" + picList +
                        '}';
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public int getClickCount() {
                return clickCount;
            }

            public void setClickCount(int clickCount) {
                this.clickCount = clickCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getNoteId() {
                return noteId;
            }

            public void setNoteId(String noteId) {
                this.noteId = noteId;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAuthorId() {
                return authorId;
            }

            public void setAuthorId(String authorId) {
                this.authorId = authorId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getSectionId() {
                return sectionId;
            }

            public void setSectionId(String sectionId) {
                this.sectionId = sectionId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(int shortTitle) {
                this.shortTitle = shortTitle;
            }

            public String getIsTop() {
                return isTop;
            }

            public void setIsTop(String isTop) {
                this.isTop = isTop;
            }

            public List<PicListBean> getPicList() {
                return picList;
            }

            public void setPicList(List<PicListBean> picList) {
                this.picList = picList;
            }

            public static class PicListBean {
                private String picUrl;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }
            }
        }
    }
}
