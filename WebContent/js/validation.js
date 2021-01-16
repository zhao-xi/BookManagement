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
 * 检查是否为数字
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