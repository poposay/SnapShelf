package com.say.popo.popoalbum.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "ai_comment")
public class AIComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@Column(length = 50)
	private String type;
	
	private Integer emotional_level;
	
	@Column(length = 500, nullable = false)
	private String content;
	
	@Column(length = 20)
	private String target_time;
	
	@Column(nullable = false)
	private LocalDateTime created_at;
	
	@PrePersist
	protected void onCreate() {
		this.created_at = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEmotional_level() {
		return emotional_level;
	}

	public void setEmotional_level(Integer emotional_level) {
		this.emotional_level = emotional_level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTarget_time() {
		return target_time;
	}

	public void setTarget_time(String target_time) {
		this.target_time = target_time;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	

}
