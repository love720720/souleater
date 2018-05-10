package com.souleater.util;

import com.souleater.init.Constants;

import java.util.ArrayList;
import java.util.List;

/** 
 * 分页相关工具类
 * @author love720720@163.com
 * @date 2017年6月14日 下午3:23:27
 */
public class PageUtils {

	public final static int COUNT_DEFAULT = 20;
	public final static int PAGENUM_DEFAULT = 9;
	public final static int PAGEINDEX_DEFAULT = 1;
	
	public static int index;//下标 从第几个开始
	public static int totalCount;//总条数
	public static int totalPageCount;//总页数
	public static int count;//当前页显示条数
	public static int pageIndex;//当前页
	public static int firstIndex;//开始页
    public static int lastIndex;//结束页
    public static String fromAction;//分页地址
    public static List<Integer> pageNums = new ArrayList<Integer>();//页码
    
    /**
     * 分页
     * @param totalCount 总条数
     */
    public static void init(int totalCount){
    	init(totalCount, COUNT_DEFAULT, PAGEINDEX_DEFAULT, PAGENUM_DEFAULT, Constants.EMPTY);
    }
    
    /**
     * 分页
     * @param totalCount 总条数
     * @param count 每页显示多少条数
     */
    public static void init(int totalCount,int count){
    	init(totalCount, count, PAGEINDEX_DEFAULT, PAGENUM_DEFAULT, Constants.EMPTY);
    }
    /**
     * 分页
     * @param totalCount 总条数
     * @param count 每页显示多少条数
     * @param pageIndex 当前页
     */
    public static void init(int totalCount,int count,int pageIndex){
    	init(totalCount, count, PAGEINDEX_DEFAULT, PAGENUM_DEFAULT, Constants.EMPTY);
    }
    
    /**
     * 分页
     * @param totalCount 总条数
     * @param count 每页显示多少条数
     * @param pageIndex 当前页
     * @param pageNum 显示页码 默认分页为9个页码
     * @param fromAction 跳转action
     */
	public static void init(int totalCount,int count,int pageIndex,int pageNum,String fromAction){
		pageIndex = pageIndex <= 0 ? PAGEINDEX_DEFAULT : pageIndex;
		pageNum = pageNum <= 0 ? PAGENUM_DEFAULT : pageNum;
		PageUtils.pageIndex = pageIndex;
		PageUtils.count = count;
		PageUtils.index = ((pageIndex - 1) * count);
		PageUtils.totalCount = totalCount;
		PageUtils.fromAction = fromAction;
		setTotalCount(totalCount);
		int totalPageCount = PageUtils.totalPageCount;
		int firstIndex = (pageIndex <= pageNum / 2 + 1 ? 1 : (pageIndex-pageNum / 2));
		int lastIndex = (firstIndex + pageNum-1 >= totalPageCount ? totalPageCount : firstIndex + pageNum - 1);
		int temp = lastIndex - firstIndex + 1;
		temp = temp == pageNum ? 0 : pageNum - temp;
		temp = temp < 0 ? 0 : temp;
		firstIndex = firstIndex - temp;
		firstIndex = firstIndex <= 0 ? 1 : firstIndex;
		lastIndex = lastIndex > totalPageCount ? totalPageCount : lastIndex;
		firstIndex = firstIndex > lastIndex ? lastIndex : firstIndex;
		PageUtils.firstIndex = firstIndex;
		PageUtils.lastIndex = lastIndex;
	}
    
	public static List<Integer> getPageNums() {
		for (int i = PageUtils.firstIndex; i <= PageUtils.lastIndex; i++) {
			PageUtils.pageNums.add(i);
		}
		return PageUtils.pageNums;
	}

	private static void setTotalCount(int totalCount) {
		if(PageUtils.count > 0) {
			PageUtils.totalPageCount = (totalCount / PageUtils.count);
			if(totalCount % PageUtils.count != 0) {
				PageUtils.totalPageCount ++;
			}
		}
	}
}
