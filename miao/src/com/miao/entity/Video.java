package com.miao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 视频表
 * @author
 *
 */
@Entity
@Table(name = "video", catalog = "miao")
public class Video implements java.io.Serializable {


	private Integer id;           //标识
	private Videostate videostate;//状态  （外键）
	private String name;          //视频名
	private String intro;         //视频简介
	private Timestamp edittime;   //视频编辑时间
	private Integer islive;		  //判断视频的一个标志
	private String url;           //处理后视频url
	private String oriurl;        //上传视频url
	private String thumbnailurl;  //缩略图url

	public Video() {
	}

	public Video(Videostate videostate, String name,
			String intro, Timestamp edittime, Integer islive, String url,
			String oriurl, String thumbnailurl) {
		this.videostate = videostate;
		this.name = name;
		this.intro = intro;
		this.edittime = edittime;
		this.islive = islive;
		this.url = url;
		this.oriurl = oriurl;
		this.thumbnailurl = thumbnailurl;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "videostateid")
	public Videostate getVideostate() {
		return this.videostate;
	}

	public void setVideostate(Videostate videostate) {
		this.videostate = videostate;
	}


	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "intro", length = 8192)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "edittime", length = 19)
	public Timestamp getEdittime() {
		return this.edittime;
	}

	public void setEdittime(Timestamp edittime) {
		this.edittime = edittime;
	}

	@Column(name = "islive")
	public Integer getIslive() {
		return this.islive;
	}

	public void setIslive(Integer islive) {
		this.islive = islive;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "oriurl")
	public String getOriurl() {
		return this.oriurl;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	@Column(name = "thumbnailurl")
	public String getThumbnailurl() {
		return this.thumbnailurl;
	}

	public void setThumbnailurl(String thumbnailurl) {
		this.thumbnailurl = thumbnailurl;
	}

}