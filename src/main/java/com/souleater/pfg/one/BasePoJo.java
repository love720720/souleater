package com.souleater.pfg.one;

/** 
 * 
 * @author love720720@163.com
 * @date 2017年5月2日 上午11:29:43
 */
public class BasePoJo {

	private String name;
	private String price;
	
	public BasePoJo(){  
	}
	
	public BasePoJo(String name, String price){  
        this.name = name;
        this.price = price;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}