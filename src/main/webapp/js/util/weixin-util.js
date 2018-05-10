'use strict';

/**
 * 微信相关操作js
 */
var WeiXinUtil = {};
(function(factory){
    if(typeof define === 'function' && (define.amd || define.cmd)){
        define(['JWeiXin','PathUtil'], factory);
	}else if(typeof module !== 'undefined' && typeof exports === 'object'){
    	module.exports = factory(require('JWeiXin'), require('PathUtil'));
    }else{
        factory(this.wx,this.PathUtil);
    }
}).call(this, function(wx,PathUtil){
	
	/**
	 * 微信内部参数
	 */
	var _argumentsPrivate = {
			cache: true,//缓存
			debug: false,//调试模式
			printLog: false,//日志打印
			appId: null,
			timestamp: null,
			nonceStr: null,
			signature: null,
			// 支持的api列表
			jsApiList: [
						 'onMenuShareTimeline',// 分享到朋友圈
						 'onMenuShareAppMessage',// 分享给朋友
						 'hideMenuItems',// 批量隐藏功能按钮
						 'showMenuItems',// 批量显示功能按钮
						 'closeWindow'// 关闭当前网页窗口
			            ],
			// 需要隐藏的功能按钮
			hideMenuItems: [
			                  // 基本类
			                 'menuItem:exposeArticle',// 举报
			                 'menuItem:setFont',// 调整字体
			                 'menuItem:dayMode',// 日间模式
			                 'menuItem:nightMode',// 夜间模式
			                 'menuItem:refresh',// 刷新
			                 'menuItem:profile',// 查看公众号（已添加）
			                 'menuItem:addContact',// 查看公众号（未添加）
			                  // 传播类
			                 'menuItem:share:appMessage',// 发送给朋友
							 'menuItem:share:timeline',// 分享到朋友圈
			                 'menuItem:share:qq',// 分享到QQ
			                 'menuItem:share:weiboApp',// 分享到Weibo
			                 'menuItem:favorite',// 收藏
			                 'menuItem:share:facebook',// 分享到FB
			                 'menuItem:share:QZone',// 分享到QQ空间
			                  // 保护类
			                 'menuItem:editTag',// 编辑标签
			                 'menuItem:delete',// 删除
			                 'menuItem:copyUrl',// 复制链接
			                 'menuItem:originPage',// 原网页
			                 'menuItem:readMode',// 阅读模式
			                 'menuItem:openWithQQBrowser',// 在QQ浏览器中打开
			                 'menuItem:openWithSafari',// 在Safari中打开
			                 'menuItem:share:email',// 邮件
			                 'menuItem:share:brand'// 一些特殊公众号
			                ],
			// 需要显示的功能按钮
		    showMenuItems: [
		                     'menuItem:share:appMessage',// 发送给朋友
		                     'menuItem:share:timeline'// 分享到朋友圈
		                    ],
		    //分享给朋友
		    shareAppMessage: {
		    	title: null,
		    	desc: null,
		    	link: null,
		    	imgUrl: null,
		    	type: null,
		    	dataUrl: null,
		    	trigger: function(){},
		    	success: function(){},
		    	cancel: function(){}
		    },  
            //分享到朋友圈
		    shareTimeline: {
		    	title: null,
		    	link: null,
		    	imgUrl: null,
		    	trigger: function(){},
		    	success: function(){},
		    	cancel: function(){}
		    }
	};
	
	/**
	 * 初始化内部参数
	 */
	function initArguments(_arguments){
		if(!_arguments){
			return;
		}
		for(var key in _arguments){
			var hasKey = _argumentsPrivate.hasOwnProperty(key);
			if(hasKey){
				_argumentsPrivate[key] = _arguments[key];
			}
		}
	}
	
	/**
	 * 初始化微信
	 */
	function initWeiXin() {
		if(_argumentsPrivate.appId && _argumentsPrivate.timestamp && _argumentsPrivate.nonceStr && _argumentsPrivate.signature){
			config();
			return;
		}
		var url = PathUtil.rootPath() + '/app/ajss?t=' + (Math.random() * 100);
		var data = {};
		var xhr = new XMLHttpRequest();
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(data);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 2){
				//请求已接收
			}
			if(xhr.readyState == 3){
				//请求处理中
			}
			if(xhr.readyState == 4){
				//请求已完成，且响应已就绪
				if(xhr.status == 200 || xhr.status == 304) {
					var result = JSON.parse(xhr.responseText);
					_argumentsPrivate.appId = result.appId;
					_argumentsPrivate.timestamp = result.timestamp;
					_argumentsPrivate.nonceStr = result.nonceStr;
					_argumentsPrivate.signature = result.signature;
					//设置缓存
					setCache();

					config();
					return;
				}
				console.error('请求响应失败:' + _argumentsPrivate.signature);
			}
		}
	}
	
	/**
	 * 微信入口
	 */
	function init(_arguments) {
		//初始化参数
		initArguments(_arguments);
		//初始化缓存
		initCache();
		//初始化微信
		initWeiXin();
	}
	
	/**
	 * config接口注入权限验证配置
	 */
	function config(){
		wx.config({
			// 开启调试模式,调用的所有api的返回值会在客户端alert出来
			// 若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			debug: _argumentsPrivate.debug,
			// 必填，公众号的唯一标识
			appId: _argumentsPrivate.appId,
			// 必填，生成签名的时间戳
			timestamp: _argumentsPrivate.timestamp,
			// 必填，生成签名的随机串
			nonceStr: _argumentsPrivate.nonceStr,
			// 必填，签名
			signature: _argumentsPrivate.signature,
			// 必填，需要使用的JS接口列表
			jsApiList: _argumentsPrivate.jsApiList
		});
		ready();
	}
	
	/**
	 * ready接口处理成功验证
	 * config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后
	 * config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行
	 * 对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中
	 */
	function ready(){
		wx.ready(function(){
			checkJsApi();
			hideMenuItems();
			showMenuItems();
			onMenuShareAppMessage();
			onMenuShareTimeline();
		});
	}
	
	/**
	 * 批量隐藏功能按钮
	 */
	function hideMenuItems(){
		wx.hideMenuItems({
			menuList: _argumentsPrivate.hideMenuItems
		});

	}
	
	/**
	 * 批量显示功能按钮
	 */
	function showMenuItems(){
		wx.showMenuItems({
			menuList: _argumentsPrivate.showMenuItems
		});
	}
	
	/**
	 * 判断当前客户端版本是否支持指定JS接口
	 * 以键值对的形式返回，可用的api值true，不可用为false
	 * 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	 */
	function checkJsApi(){
		wx.checkJsApi({
			jsApiList: _argumentsPrivate.jsApiList,
			success: function(response) {
				var json = response.checkResult;
				for(apiName in json){
					var result = json[apiName];
					if(!result){
						console.error('当前不支持的api:' + apiName);
					}
				}
			}
		});
	}
	
	/**
	 * 获取“分享给朋友”按钮点击状态及自定义分享内容接口
	 */
	function onMenuShareAppMessage(){
		var shareAppMessage = _argumentsPrivate.shareAppMessage;
		wx.onMenuShareAppMessage({
			// 分享标题
			title: shareAppMessage.title,
			// 分享描述
			desc: shareAppMessage.desc,
			// 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			link: shareAppMessage.link,
			// 分享图标
			imgUrl: shareAppMessage.imgUrl,
			// 分享类型,music、video或link，不填默认为link
			type: shareAppMessage.type,
			// 如果type是music或video，则要提供数据链接，默认为空
			dataUrl: shareAppMessage.dataUrl,
			trigger: function(){
				shareAppMessage.trigger();
		    },
			success: function(){
				shareAppMessage.success();
			},
			cancel: function(){
				shareAppMessage.cancel();
			},
			fail: function(response) {
				alert(JSON.stringify(response));
	        }
		});
	}
	
	/**
	 * 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
	 */
	function onMenuShareTimeline(){
		var shareTimeline = _argumentsPrivate.shareTimeline;
		wx.onMenuShareTimeline({
			// 分享标题
			title: shareTimeline.title,
			// 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			link: shareTimeline.link,
			// 分享图标
			imgUrl: shareTimeline.imgUrl,
			trigger: function(){
				shareTimeline.trigger();
		    },
		    success: function(){
				shareTimeline.success();
			},
			cancel: function(){
				shareTimeline.cancel();
			},
			fail: function(response) {
				alert(JSON.stringify(response));
	        }
		});
	}
	
	/**
	 * error接口处理失败验证
	 * config信息验证失败会执行error函数，如签名过期导致验证失败
	 * 具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名
	 */
	wx.error(function(res){
		//清空缓存
		removeCache();
		alert(JSON.stringify(res));
	});
	
	/**
	 * 初始化缓存数据
	 */
	function initCache(){
		removeCache();
		
		if(_argumentsPrivate.cache){
			_argumentsPrivate.appId = sessionStorage.getItem('appId');
			_argumentsPrivate.timestamp = sessionStorage.getItem('timestamp');
			_argumentsPrivate.nonceStr = sessionStorage.getItem('nonceStr');
			_argumentsPrivate.signature = sessionStorage.getItem('signature');
			
			if(_argumentsPrivate.printLog){
				console.info('缓存读取->>appId:' + _argumentsPrivate.appId);
				console.info('缓存读取->>timestamp:' + _argumentsPrivate.timestamp);
				console.info('缓存读取->>nonceStr:' + _argumentsPrivate.nonceStr);
				console.info('缓存读取->>signature:' + _argumentsPrivate.signature);
			}
		}
	}
	
	/**
	 * 设置缓存
	 */
	function setCache(){
		if(_argumentsPrivate.cache){
			sessionStorage.setItem('appId', _argumentsPrivate.appId);
			sessionStorage.setItem('timestamp', _argumentsPrivate.timestamp);
			sessionStorage.setItem('nonceStr', _argumentsPrivate.nonceStr);
			sessionStorage.setItem('signature', _argumentsPrivate.signature);
		}
		
		if(_argumentsPrivate.printLog){
			console.info('服务端加载->>appId:' + _argumentsPrivate.appId);
			console.info('服务端加载->>timestamp:' + _argumentsPrivate.timestamp);
			console.info('服务端加载->>nonceStr:' + _argumentsPrivate.nonceStr);
			console.info('服务端加载->>signature:' + _argumentsPrivate.signature);
		}
	}
	
	/**
	 * 清空缓存
	 */
	function removeCache(){
		sessionStorage.removeItem('appId');
		sessionStorage.removeItem('timestamp');
		sessionStorage.removeItem('nonceStr');
		sessionStorage.removeItem('signature');
	}
	
    var exports = {
		init: init
    }
    
    WeiXinUtil = exports;
    return WeiXinUtil;
});