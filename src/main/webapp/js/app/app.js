'use strict';

var App = {};
(function(factory){
    if(typeof define === 'function' && (define.amd || define.cmd)){
        define(['WeiXinUtil'], factory);
	}else if(typeof module !== 'undefined' && typeof exports === 'object'){
    	module.exports = factory(require('WeiXinUtil'));
    }else{
        factory(this.WeiXinUtil);
    }
}).call(this, function(WeiXinUtil){
	
	var _arguments = {
			cache: false,
			debug: false,
			printLog: true,
			shareTimeline: {
				title: '测试分享到朋友圈',
				link: 'http:///gaowei.nat100.top/souleater/images/app/share.jpg',
				imgUrl: 'http:///gaowei.nat100.top/souleater/images/app/share.jpg',
				trigger: function(){
					alert('开始分享');
				},
				success: function(){
					alert('分享成功');
				},
				cancel: function(){
					alert('取消分享');
				}
			},
			shareAppMessage: {
		    	title: '测试分享给朋友',
		    	desc: '测试分享给朋友',
		    	link: 'http:///gaowei.nat100.top/souleater/images/app/share.jpg',
		    	imgUrl: 'http:///gaowei.nat100.top/souleater/images/app/share.jpg',
		    	trigger: function(){
					alert('开始分享');
				},
				success: function(){
					alert('分享成功');
				},
				cancel: function(){
					alert('取消分享');
				}
		    }
	}
	
	WeiXinUtil.init(_arguments);
	
	var exports = {
    }
    
	App = exports;
    return App;
});