package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll",query = "SELECT v FROM Video v")
public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="VideoId")
	private int videoId;

	@Column(name="Active")
	private int active;

	@Column(name="Description",columnDefinition = "VARCHAR(500)")
	private String description;

	@Column(name="Poster")
	private String poster;

	@Column(name="Title",columnDefinition = "VARCHAR(500)")
	private String title;
	
	@Column(name="View")
	private int views;
	
	@Column(name = "videoURL")
	private String video_file;
	
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;

	public Video() {
		
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoid) {
		this.videoId = videoid;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getVideo_file() {
		return video_file;
	}

	public void setVideo_file(String video_file) {
		this.video_file = video_file;
	}
	
	
	
	
	
}
