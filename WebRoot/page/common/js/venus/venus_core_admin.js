var $windowModel; // 窗体的样例
var WINDOW_ID_PREFIX = "popWin";
var gbl_window_zIndex = 1100;
// 页面加载完以后，初始化各种方法
$(function() {
	var $body = $("body");
	$windowModel = $("#windowModelDiv");
    
    // 初始化弹出框
	init_ui_dialog($body);
	
	//自定义手机号码校验规则
	jQuery.validator.addMethod("isMobile", function(value, element) {       
	     var length = value.length;   
	    var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
	   return this.optional(element) || (length == 11 && mobile.test(value));       
	}, "请正确填写您的手机号码");    
    
});

/**
 * 初始化弹出框
 * @param contextObj 上下文对象(jquery对象)
 */
function init_ui_dialog($contextObj) {
	
	// 初始化动作
	$contextObj.click(function(event) {
		var $this = $(event.target).closest("a");
	    if($this.attr("openMode")=="logout")
	    {
	    	return;
	    }
	    
		if ($this.attr("openMode")=="rightPage") {
			_openRightPage($this);
			// 防止页面跳转
			return false;
		}
		
		// 以dialog方式打开
		if ($this.attr("openMode") == "dialog") {
			gbl_closeWindow_callBack = function() {
			};
			_open_dialog($this);
			return false;
		} 
		
	});
}


//在右侧打开连接页面
function _openRightPage($this)
{
    //切换样式
	if(($this.attr("optMenu")) == "true")
	{
		changeMenuStyle($this);
	}
    _rightLoad($this.attr("href"),null);
	
}

//切换选中menu样式
function changeMenuStyle($thisMenu)
{
    $('li').has('a').removeClass('active');
    
    //匹配当前选中的a节点邻近的第一个li节点
    $thisMenu.closest('li').addClass('active');
    
    $("#span_menuOneLevel").html($thisMenu.attr("parentMenu"));
    $("#span_menuTwoLevel").html($thisMenu.attr("presentMenu"));
    
}


/**
*将url执行后返回的信息加载到loadPosition参数指定的位置
*/
function selfLoad(url, data, loadPosition) {
	var l_data = {};
	if (data != null && data != "") {
		l_data = data;
	}
	// 加载内容
	var $rightBody = $(loadPosition);
	showLoadingMsg();
	$rightBody.load(url, l_data, function(result, status) {
		if (status == "success") {
			_selfshowPageNum(loadPosition);
			lui_showPlaceholder();
			setSelectStatus();
			removeLoadingMsg();
			return false;
		} else {
			bootbox.alert("您的请求操作有异常", function(alertResult) {
				location.reload();
			});
			return false;
		}
	});
}

/**
*将url执行后返回的信息加载到loadPosition参数指定的位置,不支持分页
*/
function selfLoadInfo(url, data, loadPosition) {
	var l_data = {};
	if (data != null && data != "") {
		l_data = data;
	}
	// 加载内容
	var $rightBody = jQuery(loadPosition);
	showLoadingMsg();
	$rightBody.load(url, l_data, function(result, status) {
		if (status == "success") {
			lui_showPlaceholder();
			removeLoadingMsg();
			return false;
		} else {
			bootbox.alert("您的请求操作有异常", function(alertResult) {
				location.reload();
			});
			return false;
		}
	});
}

function _rightLoad(url,data)
{
	selfLoad(url,data,"#vf_loadRight");
}

//使用ajax方式提交表单,并刷新(或转向)指定的列表页面
function ajaxSubmitAndRefresh(formId,refreshUrl)
{
	showLoadingMsg();//加载等待信息
	$formObj = $("#"+formId);
	// 加载内容
	var $rightBody = $('#vf_loadRight');
	$rightBody.load($formObj.attr("action"), $formObj.serializeArray(), function(result,status){
		if (result == "OPT_SUCCESS") {
			if(refreshUrl!="" && refreshUrl!=null)
			{
				removeLoadingMsg();
				//刷新(或转向)
				_rightLoad(refreshUrl,null);
				_showGritter();
			}
			return false;
		}else if(result == "OPT_ERROR") {
			bootbox.alert("操作失败，<br>请检查相关数据信息是否正确.",function(result) {
				
			});
			return false;
		}else{
			removeLoadingMsg();
			return false;
		}
	});
}


//刷新(或转向)指定的列表页面
function ajaxRefresh(hrefUrl,refreshUrl)
{
	ajaxTreeRefresh(hrefUrl,refreshUrl,{});
}

//刷新(或转向)指定的列表页面,param为提交的参数
function ajaxTreeRefresh(hrefUrl,refreshUrl,param)
{
	// 发送ajax请求，
	$.post(hrefUrl, param, function(result) {
		if (result == "OPT_SUCCESS") {
			if(refreshUrl!="" && refreshUrl!=null){
				//刷新(或转向)
				_rightLoad(refreshUrl,null);
				_showGritter();
			}
			return false;
		}else {
			bootbox.alert("操作失败，<br>请检查相关数据信息是否正确.",function(result) {
				
			});
			return false;
		}
	});
}

//刷新(或转向)指定的列表页面
/**
function ajaxRefresh_oldddd(formId,refreshUrl)
{
	//alert($("#"+formId).serializeArray());
	$formObj = $("#"+formId);
	// 发送ajax请求，
	$.post($formObj.attr("action"), $formObj.serializeArray(), function(result) {
		
		if (result == "OPT_SUCCESS") {
			if(refreshUrl!="" && refreshUrl!=null)
			{
				//刷新(或转向)
				_rightLoad(refreshUrl,null);
				_showGritter();
			}
			return false;
		}else {
			bootbox.alert("操作失败，<br>请检查相关数据信息是否正确.",function(result) {
				
			});
			return false;
		}
	});
}
**/

//右下方的操作消息提醒
function _showGritter()
{
	$.gritter.add({
		title: '提示',
		text: '操作成功',
		sticky: false,
		time:1200,
		class_name: 'gritter_hl_bottom_right gritter-light' 
	});

	return false;
}
//执行分页的相关操作,自定义显示分页的位置
function _selfshowPageNum(loadPosition)
{
	var _currentPage = 1;
	var _totalPages = 1;
	var _totalRows = 1;

	$table_pageNum = $("#table_pageNum");
	if(typeof($table_pageNum.html()) != "undefined"&&$table_pageNum.html() != undefined)
	//if($table_pageNum.html() != "undefined")
	{
		$formObj = $table_pageNum.closest("form");
		
		_currentPage = $table_pageNum.attr("currentPage");
		_totalRows = $table_pageNum.attr("totalRows");
		_totalPages = $table_pageNum.attr("totalPages");
		_sizePage = $table_pageNum.attr("sizePage");
		
		selfshowPageSelect(_totalRows,_totalPages,_sizePage,$formObj,loadPosition);
		
		var options = {
	    		bootstrapMajorVersion:3,
	    		currentPage:_currentPage,
	    		totalPages: _totalPages,
	    		sizePage:_sizePage,
	    		numberOfPages:1,
	    		onPageClicked: function(e,originalEvent,type,page){
	    			selfLoad($formObj.attr("action")+"/"+page+"-"+_sizePage,$("div[id='div_condition_search'] input,select").serializeArray(),loadPosition);
	            }
	    		};
		$table_pageNum.bootstrapPaginator(options);
	}
}

//每页显示 n 条，共 m 页；下拉可直接执行,自定义显示分页的位置
function selfshowPageSelect(totalRows,totalPages, sizePage, formObj,loadPosition)
{
	var _tempPageSelect = $("<span>&nbsp;共 "+totalRows+" 行&nbsp;&nbsp;每页显示 <select id='lui_pageSelected'>" +
							"<option value='10'>10</option>" +
							"<option value='20'>20</option>" +
							"<option value='40'>40</option>" +
							"<option value='60'>60</option>" +
							"<option value='80'>80</option>" +
							"<option value='100'>100</option>" +
						  "</select> 行&nbsp;&nbsp;共 "+totalPages+" 页&nbsp;&nbsp;&nbsp;&nbsp;</span>");
	$("#table_pageNum").before(_tempPageSelect);
	_tempPageSelect.val(sizePage);
	_tempPageSelect.on("change",function()
	{
		var tempSizePage = _tempPageSelect.find("option:selected").val();
		selfLoad($formObj.attr("action")+"/1-" + tempSizePage,$("div[id='div_condition_search'] input,select").serializeArray(),loadPosition);
	});
}


/**
 * 执行右侧列表的条件查询，加载到指定位置的
 */
function condition_search_self(refreshUrl,loadPosition){
	// 加载内容
	var $rightBody = $(loadPosition);
	$rightBody.load(refreshUrl, $("div[id='div_condition_search'] input,select").serializeArray(), function(){
		_selfshowPageNum(loadPosition);
	});
}
/**
 * 执行条件查询
 */
function condition_search(refreshUrl)
{
	condition_search_self(refreshUrl,"#vf_loadRight");
}


/**
 * 在body中加入等待的消息
 */
function showLoadingMsg() {
	
	var $body = $("body");
	var _bodyHeight = $body.height();
	
	if(_bodyHeight < 800)
	{
		_bodyHeight = 800;
	}
	
	$("<div class=\"mask\"></div>").css({zIndex:90}).appendTo($body).css({
		height:_bodyHeight,
		width:$body.width()
	}).show();

	$("<div id=\"lui_spin_div\" class=\"maskMsg\"></div>").html("加载中...").appendTo($body).css({
		display:"none",zIndex:8000,
		left:$body.width()/2-$("div.maskMsg").width()/2,
		top:300}).show();
	
	var opts = {
			  lines: 12, 
			  length: 0,
			  width: 8, 
			  radius: 25,
			  color: 'blue', 
			  speed: 1, 
			  trail: 80, 
			  shadow: true 
			};
	var target = document.getElementById('lui_spin_div');
	var spinner = new Spinner(opts).spin(target);
}

/**
 * 取消body中等待的消息
 */
function removeLoadingMsg() {
	var $body = $("body");
	var $masgMsg = $body.find("div.maskMsg");
	var $mask = $body.find("div.mask"); 
	$masgMsg.fadeOut();
	$mask.fadeOut();

	$masgMsg.remove();
	$mask.remove();
	
	$mask = null;
	$masgMsg = null;
	$body = null;
}

/**
 * 兼容IE8、9 ,显示 placeholder
 */
function lui_showPlaceholder()
{
	//兼容IE8、9
	$('input, textarea, select').placeholder();
}
  
/**
 * table列表中带有复选框的 全选/取消 操作
 * allcheck:表头th对象，可以直接通过checkboxSelectAll(this)调用该方法
 */
function checkboxSelectAll(allcheck){
	if(allcheck.checked){
		$("td input:checkbox").each(function(){
			$(this).prop("checked",true);
		});
	}else{
		$("td input:checkbox").each(function(){
			$(this).prop("checked",false);
		});
	}
}

/**
 * 得到选中的checkbox的id值，array方式返回
 */
function getSelCheckboxToArray()
{
	var _arrayObj = "[";
	var that = $('table th input:checkbox');
	that.closest('table').find('tr > td:first-child input:checkbox')
	.each(function(idx){
		//alert($(this).attr("checked"));
		if($(this).attr("checked")=="checked")
	    {
			if(_arrayObj == "[")
			{
				_arrayObj = _arrayObj + $(this).attr("id");
			}else
			{
				_arrayObj = _arrayObj +","+ $(this).attr("id");
			}
	    }
	});
	_arrayObj = _arrayObj + "]";
	alert(_arrayObj);
}

/**
 * 设置checkbox的 选中/取消 状态
 */
function setSelectStatus()
{
	$('table td input:checkbox').bind("click",function()
	{
		//alert($(this).attr("checked"));
		if($(this).attr("checked")=="checked")
		{
			$(this).removeAttr("checked");
		}
		else
		{
			$(this).attr("checked","checked");
		}
	});
}



/**
 * 打开dialog弹出层窗口
 * @param $objClickTarget
 */
function _open_dialog($objClickTarget) {
	
	// 系统模块名称
	var moduleName = $objClickTarget.attr("moduleName");
	
	// 系统功能名称
	var functionName = $objClickTarget.attr("functionName");
	if (functionName.length > 20) {
		functionName = functionName.substring(0, 20)+"...";
	}
	
	// 窗体的宽度
	var width = $objClickTarget.attr("width");
	
	// 窗体的高度
	var height = $objClickTarget.attr("height");
	
	// 窗口需要打开的URL
	var url = $objClickTarget.attr("href");
	
	// 是否使用iframe
	var isIframe = $objClickTarget.attr("isIframe");
	
	// 是否以模态的方式打开
	var isModel = $objClickTarget.attr("modal");
	
	// 是否隐藏关闭按钮
	var hideCloseBtn = false;//$objClickTarget.attr("hideCloseBtn");

	// 关闭的时候的回调函数
	var closeCallBack = $objClickTarget.attr("closeCallBack");
	
	// 窗口数据
	var data = $objClickTarget.attr("data");

	// 打开窗口
	_open_dialog_action_callBack(moduleName, functionName, width, height, url, isIframe, isModel, hideCloseBtn, closeCallBack,data);

	$objClickTarget = null;
}

/**
 * 打开窗体window
 * @param $objClickTarget 被点击的a标签
 */
function _open_dialog_action_callBack(moduleName, functionName, width, height, url, isIframe, isModel, hideCloseBtn, callBackFunc,data) {
	
	

	var $body = $("body");
	var left = ($body.width() - width) / 2;
	var top = ($body.height() - height) / 2 + 100;

	// 获取窗体的模板代码，并设置其中的属性
	var $objTargetWin = $windowModel.clone().appendTo($body);
	
	$objTargetWin.draggable({
		//handle:$objTargetWin.find("div.row")
	});
	
	// 设定页面内容(当前位置)
	$objTargetWin.find("span.title_pre").html(moduleName + "&nbsp;&gt;");
	$objTargetWin.find("span.title_cur").html(functionName);
	
	var $objContentDiv = $objTargetWin.find("div.content");
	var _bodyHeight = $body.height();
	if(_bodyHeight < 800)
	{
		_bodyHeight = 800;
	}
	
	// 如果需要显示模态对话框
	if (isModel != "false") {
		
		// 首先判断当前是否有已经打开的dialog，如果有，则将dialog的遮罩层zIndex+1
		var $maskDiv = $("div.windowMask");
		if ($maskDiv.length > 0) {

			// 调整全局的zindex
			gbl_window_zIndex += 1;
			$maskDiv.css({zIndex:gbl_window_zIndex});
		} else {
			$("<div class=\"windowMask\"></div>").css({zIndex:gbl_window_zIndex}).appendTo($body).css({
				height:_bodyHeight,
				width:$body.width()
			}).show();
		}
	}
	
	// 是否使用iframe
	if (isIframe == "true") {
		var appendHTML = '<iframe src="'+url+'" height="'+height+'" width="'+(width-15)+'" frameborder="0" scrolling="auto"></iframe>';
		//$(appendHTML).appendTo($objContentDiv);
		
		// 打开窗体
		gbl_window_zIndex += 1;
		var wi = WINDOW_ID_PREFIX+gbl_window_zIndex;
		if(data == null || data == ""){
			$objTargetWin.find("div.window_close").attr("data","{'winId':'"+wi+"'}");
		}else{
			$objTargetWin.find("div.window_close").attr("data","{'winId':'"+wi+"',"+data+"}");
		}
		$objTargetWin.appendTo($("body")).show().height(height).width(width)
			.css({position:"absolute", left:left, top:top, "z-index" : gbl_window_zIndex}).attr("id", wi);
		$objTargetWin.find("div.content").html(appendHTML);
		$objTargetWin.find("div.window_close").click(function(event) {
			eval(callBackFunc);
			data = event.target.data;
			data = eval("("+data+")");
			closeMoreWindow(data);
			//closeCurrentWindow();
		});
			
		// 清空内容占用
		$objContentDiv = null;
		$objTargetWin = null;
		$body = null;
	} else {

		// 打开窗体
		gbl_window_zIndex += 1;
		var wi = WINDOW_ID_PREFIX+gbl_window_zIndex;
		if(data == null || data == ""){
			$objTargetWin.find("div.window_close").attr("data","{'winId':'"+wi+"'}");
		}else{
			$objTargetWin.find("div.window_close").attr("data","{'winId':'"+wi+"',"+data+"}");
		}
		$objTargetWin.appendTo($("body")).hide().height(height).width(width)
			.css({position:"absolute", left:left, top:top, "z-index" : gbl_window_zIndex}).attr("id", wi);
		
		$objContentDiv.load(url, function() {

			$objContentDiv.height(height-44).css({"overflow-y":"auto"});
			
			$objTargetWin.appendTo($("body")).show()
			
			// 是否需要隐藏关闭按钮
			if (hideCloseBtn == "true") {
				$objTargetWin.find("div.window_close").hide();
			} 

//			// 打开窗体
//			$objTargetWin.appendTo($("body")).show().height(height).width(width)
//				.css({position:"absolute", left:left, top:top, "z-index" : gbl_window_zIndex}).attr("id", WINDOW_ID_PREFIX+gbl_window_zIndex);

			

			initSysUICompentent($objTargetWin);

			$objTargetWin.find("div.window_close").click(function(event) {
				eval(callBackFunc);
				data = event.target.data;
				data = eval("("+data+")");
				closeMoreWindow(data);
				//closeCurrentWindow();
			});

			$objTargetWin.focus();

			// 清空内容占用
			$objContentDiv = null;
			$objTargetWin = null;
			$body = null;
		});
	}
}

//关闭多个dialog中的一个
function closeMoreWindow(data) {
    if(data != undefined)
    {
       var winId = data.winId;
	   $("#"+winId).remove();
    }else{
       // 关闭当前的窗口
	   $("#"+WINDOW_ID_PREFIX+gbl_window_zIndex).remove();
    }
	
	
	//关闭下层遮罩
	var $openedWin = $("div.window_frame:visible");
	if ($openedWin.length > 0) {
		gbl_window_zIndex -= 2;
		$("div.windowMask").css({"z-index":gbl_window_zIndex});
	} else {
		$("div.windowMask").remove();
	}
}

function closeDivWindow() {
    
    // 关闭当前的窗口
    $("#"+WINDOW_ID_PREFIX+gbl_window_zIndex).remove();
    
	//关闭下层遮罩
	var $openedWin = $("div.window_frame:visible");
	if ($openedWin.length > 0) {
		gbl_window_zIndex -= 2;
		$("div.windowMask").css({"z-index":gbl_window_zIndex});
	} else {
		$("div.windowMask").remove();
	}
}


/**
 * 初始化系统UI组件
 *    1：鼠标经过的时候的样式改变
 *    2：获取焦点的时候的样式改变
 * 初始化对象：
 *    1：文本框
 *    2：文本区域
 * @param $contextObj 需要初始化的对象（窗体，tab）
 */
function initSysUICompentent($contextObj) {
	
	if (!$contextObj) {
		return;
	}
	
	var $inputs = $contextObj.find("input[type=text]");
	
	if ($inputs.length > 0) {
		$inputs.each(function(index, elem) {
			var $elem = $(elem);
			var $containerDiv = $elem.closest("div.input_wrap");
			$elem.mouseover(function() {
				$containerDiv.addClass("input_wrap_hover");
			}).mouseout(function(){
				$containerDiv.removeClass("input_wrap_hover");
			}).focus(function() {
				$containerDiv.addClass("input_wrap_focus");
			}).blur(function() {
				$containerDiv.removeClass("input_wrap_focus");
			});
		});
	}

	var $textArea = $contextObj.find("textarea");
	
	if ($textArea.length > 0) {
		$textArea.each(function(index, elem) {
			var $elem = $(elem);
			var $containerDiv = $elem.closest("table.form_textarea");
			$elem.mouseover(function() {
				$containerDiv.addClass("form_textarea_hover");
			}).mouseout(function(){
				$containerDiv.removeClass("form_textarea_hover");
			}).focus(function() {
				$containerDiv.addClass("form_textarea_focus");
			}).blur(function() {
				$containerDiv.removeClass("form_textarea_focus");
			});
		});
	}
	
	// 在form中点击回车，执行提交
	$contextObj.find("form").keydown(function(event) {
		if ($(this).attr("autoSubmitFlg") == "false" || event.keyCode != 13) {
		} else {
			var $thisForm = $(this);
			// 如果是在窗体中点击回车
			try {
				
				if ($thisForm.attr("onsubmit") != undefined) {
					$thisForm.submit();
				} else {
				 	var $currentWindowBody = getCurrentWindowBody();
				 	if ($currentWindowBody != undefined) {
				 		getCurrentWindowBody().load($thisForm.attr("action"),
							$thisForm.find("input[value!=''], select[value!='']").serializeArray(), function() {
								initSysUICompentent($currentWindowBody);		
							});
				 	} else {
				 		$thisForm.submit();
				 	}
				}
				
			} catch (e) {
				$thisForm.submit();
			}
		}
	});
}