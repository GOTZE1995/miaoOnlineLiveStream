package com.miao.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 直播间实体
 * 
 * @author Jupiter
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "room")
public class Room implements Serializable {
	// 房间id
	private Integer id;
	// 房间名
	private String roomName;
	// 房间描述
	private String memo;
	// 创建日期
	private Date beginDate;
	// 房间状态
	private String status;
	// 房间所属人电话
	private String phone;
	// 该房间url
	private String url;
	// 房间与用户关系，一对一
	private User user;

	public static String ROOM_STATUS_VAILD = "1";
	public static String ROOM_STATUS_INVAILD = "0";

	/**
	 * 主键引用user的主键
	 * @return
	 */
	@Id
	@GenericGenerator(name = "foreignkey", strategy = "foreign", parameters = @Parameter(value = "user", name = "property"))
	@GeneratedValue(generator = "foreignkey")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Room() {
		super();
	}

}
