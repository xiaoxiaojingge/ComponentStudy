package com.itjing.webmagic.entity;

import java.util.Date;

/**
 * @author lijing
 * @date 2022年05月26日 15:45
 * @description
 */
public class CmsContentPO {

	private String contentId;

	private String title;

	private String content;

	private Date releaseDate;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}