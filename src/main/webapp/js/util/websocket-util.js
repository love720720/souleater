'use strict';

/**
 * WebSocket相关操作js
 */
var WebSocketUtil = {};
(function(factory) {
    if(typeof define === 'function' && (define.amd || define.cmd)){
        define([], factory);
	}else if(typeof module !== 'undefined' && typeof exports === 'object'){
    	module.exports = factory();
    }else{
        factory();
    }
}).call(this, function() {
	
	// WebSocket对象
	var webSocket;
	
	var _defautP = {
		url: '',
		// 连接建立的回调方法
		onOpen: function() {
			console.info('WebSoket链接成功');
		},
		onClose: function() {
			console.info('WebSoket链接关闭');
		},
		// 接收到消息的回调方法
		onMessage: function() {
			console.info('WebSoket推送成功');
		},
		// 连接异常的回调方法
		onError: function() {
			console.error('WebSoket链接异常');
		}
	}
	
	/**
	 * 初始化内部参数
	 */
	function initArguments(_defaut) {
		if(!_defaut){
			return;
		}
		for(var key in _defaut){
			var hasKey = _defautP.hasOwnProperty(key);
			if(hasKey){
				_defautP[key] = _defaut[key];
			}
		}
	}
	
    /**
     * 初始化WebSocket
     */
	function initWebSocket() {
		if (_defautP.url.length <= 0) {
			console.error('请设置WebSoket url');
			return;
		}
	    if ('WebSocket' in window) {
	    	webSocket = new WebSocket(_defautP.url);
	    } else if ('MozWebSocket' in window) {
	    	webSocket = new MozWebSocket(_defautP.url);
	    } else {
	    	// 这里暂时不做处理
	    	// webSocket = new SockJS(_defautP.url);
	    	webSocket.error();
	    }
	    
	    webSocket.onopen = function() {
	    	_defautP.onOpen();
	    }
	    
	    webSocket.onclose = function() {
	    	_defautP.onClose();
	    }
	    webSocket.onerror = function() {
	    	_defautP.onError();
	    }
	    
	    webSocket.onmessage = function(event) {
	    	_defautP.onMessage(event);
	    }
	}
	
	/**
	 * 消息发送
	 */
	function send(message) {
		if (!webSocket) {
			console.error('请使用 ws.init(_default)初始化WebSocket');
			return;
		}
		if (webSocket.readyState != 1) {
			console.error('WebSocket链接已关闭');
			return;
		}
		webSocket.send(message);
	}
	
	/**
	 * WebSocket链接关闭
	 */
	function close() {
		if (webSocket) {
			// 防止页面加载了js而没有初始化WebSocket
			webSocket.close();
		}
	}
	
	/**
	 * 页面关闭执行WebSocket链接关闭
	 */
	window.onbeforeunload = function() {
		close();
	}
	
	/**
	 * 入口函数
	 */
	function init(_default) {
		initArguments(_default);
		initWebSocket();
	}
	
    var exports = {
    	init: init,
    	send: send,
    	close: close
    }
    
    WebSocketUtil = exports;
    return WebSocketUtil;
});