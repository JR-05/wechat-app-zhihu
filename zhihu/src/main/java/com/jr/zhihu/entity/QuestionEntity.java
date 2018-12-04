package com.jr.zhihu.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "question")
@Entity
public class QuestionEntity {
    @Id
    @Column(name = "question_id")
    private Integer questionnId;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "author_name", length = 15)
    private String authorName;

    @Column(name = "authod_image_uri", length = 150)
    private String authorImageUri;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "voteup_aount")
    private Integer voteupAount;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "timestamp")
    private Date createdTime;

    public QuestionEntity() {
    }

    public QuestionEntity(Integer questionnId, String title, String content, String authorName, String authorImageUri, Integer commentCount, Integer voteupAount, Date createdTime) {
        this.questionnId = questionnId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.authorImageUri = authorImageUri;
        this.commentCount = commentCount;
        this.voteupAount = voteupAount;
        this.createdTime = createdTime;
    }

    public Integer getQuestionnId() {
        return questionnId;
    }

    public void setQuestionnId(Integer questionnId) {
        this.questionnId = questionnId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImageUri() {
        return authorImageUri;
    }

    public void setAuthorImageUri(String authorImageUri) {
        this.authorImageUri = authorImageUri;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getVoteupAount() {
        return voteupAount;
    }

    public void setVoteupAount(Integer voteupAount) {
        this.voteupAount = voteupAount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionnId=" + questionnId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorImageUri='" + authorImageUri + '\'' +
                ", commentCount=" + commentCount +
                ", voteupAount=" + voteupAount +
                ", createdTime=" + createdTime +
                '}';
    }
}
