/**
 * 表单校验用javascript
 */

function switchValid(onOff, errSelector, message) {
	if(!onOff) {
		$(errSelector).text(message);
	} else {
		$(errSelector).text("");
	}
}

/**
 * 检查是否为空
 */
function checkEmpty(input, errSelector) {
	var val = $(input).val();
	if($.trim(val) == "") {
		switchValid(false, errSelector, "请输入信息");
		return false;
	} else {
		switchValid(true, errSelector, "");
		return true;
	}
}

/**
 * 检查是否为整数
 */
function checkInteger(input, errSelector) {
	var val = $(input).val();
	if(parseInt(val, 10) != val) {
		switchValid(false, errSelector, "请输入整数");
		return false;
	} else {
		switchValid(true, errSelector, "");
		return true;
	}
}

/**
 * 检查上传文件
 */
function checkFile(input, errSelector) {
	if(!checkEmpty(input, errSelector)) {
		return false;
	}
	
	var val = $(input).val();
	if(val.length < 4) {
		switchValid(false, errSelector, "请选择有效的图片");
		return false;
	}
	var suffix = val.substring(val.length - 3);
	if(suffix == "jpg" || suffix == "png" || suffix == "gif") {
		switchValid(true, errSelector, "");
		return true;
	} else {
		switchValid(false, errSelector, "请选择有效的图片");
	}
}