package com.souleater.web.poJo;

/**
 *
 * @author love720720@163.com
 * @date 2017年5月25日 上午11:03:39
 */
public class WebUserPoJo {

	private int id;
	private String name;
	private String userName;
	private String password;
	private String createTime;
	private String lastModiyfTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModiyfTime() {
		return lastModiyfTime;
	}

	public void setLastModiyfTime(String lastModiyfTime) {
		this.lastModiyfTime = lastModiyfTime;
	}
}