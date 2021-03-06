package com.wutue.pojo;

/**
 *  数据字典详情
 * @author liuyunlong
 *
 */
public class Dicdetail {

	public Dicdetail(){
		this.id= 0;
		this.value= "";
		this.text= "";
		this.dicid= 0;
		this.sortno= 0;
		this.status= 0;
		this.description= "";
	}
	/**
	 * ID
	 */
	private int id;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 文本描述
	 */
	private String text;

	/**
	 * 所属字典ID
	 */
	private int dicid;

	/**
	 * 排序号
	 */
	private int sortno;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 描述
	 */
	private String description;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}

	public int getDicid() {
		return dicid;
	}

	public void setDicid(int dicid) {
		this.dicid = dicid;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

}