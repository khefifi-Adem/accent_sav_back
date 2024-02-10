package com.accent.mahdia.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reset_token")
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="reset_token")
    private String resetToken;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public ResetToken() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResetToken(Long tokenId, String resetToken, User user, Date createdDate) {
        this.tokenId = tokenId;
        this.resetToken = resetToken;
        this.user = user;
        this.createdDate = createdDate;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
