package com.giveu.test.entity;

import java.io.Serializable;

/**
 * @title：图片信息
 * @author：xuan
 * @date：2018/10/10
 */
public class ImageInfo extends BaseEntity implements Serializable {
	private Long imageId;
	private Long userId;
	private String imagePath;

	public ImageInfo(){}

	public ImageInfo(Long imageId, Long userId, String imagePath) {
		this.imageId = imageId;
		this.userId = userId;
		this.imagePath = imagePath;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
