'use strict';

/**
 * 路径操作相关js
 */
var PathUtil = {};
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
	 * 获取项目跟路径
	 */
	function rootPath(){
		var pathName = window.document.location.pathname;
		pathName = pathName.substr(1);
		var index = pathName.indexOf('/');
		var webName = pathName.substring(0, index);
		return window.location.protocol + '//' + window.location.host + '/' + webName;
	}
	
    var exports = {
		rootPath: rootPath
    }
    
    PathUtil = exports;
    return PathUtil;
});