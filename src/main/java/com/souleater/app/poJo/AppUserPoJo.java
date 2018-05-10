package com.souleater.app.poJo;

/**
 *
 * @author love720720@163.com
 * @date 2017年6月15日 上午11:00:36
 */
public class AppUserPoJo {

	public final static int STATUS_YGZ = 0;
	public final static int STATUS_WGZ = 1;
	
	private int id;
	private String openId;
	private int status;
	private String createTime;
	private String lastModifyTime;
	
	private int index;// 分页 从第几条数据开始获取
	private int count;// 分页 每页显示的条数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}