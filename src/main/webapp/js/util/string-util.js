'use strict';

/**
 * 字符串操作相关js
 */
var StringUtil = {};
(function(factory){
    if(typeof define === 'function' && (define.amd || define.cmd)){
        define([], factory);
	}else if(typeof module !== 'undefined' && typeof exports === 'object'){
    	module.exports = factory();
    }else{
        factory();
    }
}).call(this, function(){
	
	/**
	 * 判断字符串是否为null、为空
	 * 为null、空时候返回true
	 * 否则返回fasle
	 * 
	 * input:需要判断的字符串
	 */
	function isBlank(input){
		if(input == null || input.length <= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * isBlank取反
	 * 
	 * input:需要判断的字符串
	 */
	function isNotBlank(input){
		return !isBlank(input);
	}
	
    var exports = {
    	isBlank: isBlank,
    	isNotBlank: isNotBlank
    }
    
    StringUtil = exports;
    return StringUtil;
});