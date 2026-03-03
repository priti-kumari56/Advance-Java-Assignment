package com.springrest.demo.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

	private Long id;
	private String name;
	private String email;
	private String profileImage;
	private LocalDateTime createdAt;

	public UserResponseDTO() {

	}

	public UserResponseDTO(Long id, String name, String email, String profileImage, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.profileImage = profileImage;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [id=" + id + ", name=" + name + ", email=" + email + ", profileImage=" + profileImage
				+ ", createdAt=" + createdAt + "]";
	}

}