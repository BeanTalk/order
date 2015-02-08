$.post('/order/order/buycard/get/productconut/', function(data) {
	$('#productInBagCount').text(data);
});

$.post('/order/order/record/get/productconut/', function(data) {
	$('#recordInBagCount').text(data);
});

$.post('/order/order/stock/get/productconut/', function(data) {
	$('#storeInBagCount').text(data);
});

// 时间插件
$(".datepicker").datepicker({
	dateFormat : 'yy-mm-dd'
});

// 全选与全不选
$('#all_checkbox').change(function() {
	var checkboxes = $(this).closest('form').find(':checkbox');
	if ($(this).prop('checked')) {
		checkboxes.prop('checked', true);
	} else {
		checkboxes.prop('checked', false);
	}
});

// 选择用户订单
$(".userOrderClass").click(
		function() {
			var checked_id = "check_prodordid_" + $(this).val();
			var checkboxes = $(this).closest('.second_level').find(
					"input[type=checkbox][id^=" + checked_id + "_]");
			if ($(this).prop('checked')) {
				checkboxes.prop('checked', true);
			} else {
				checkboxes.prop('checked', false);
			}
		});

// 单击客户订单后，隐藏所有的产品订单
$(' .user_order_toggle_tr ').dblclick(function(event) {
	event.preventDefault();
	$(".tr_" + (this.id)).toggle('');
});

var isIntAndNotNull = function(str) {
	if (!isObj(str))
		return 'undefined';
	return !isNull(str) && isInt(str);
}

var isFloatAndNotNull = function(str) {
	if (!isObj(str))
		return 'undefined';
	if (isInt(str))
		return true;
	return !isNull(str) && isFloat(str);
}

var isObj = function(str) {
	if (str == null || typeof (str) == 'undefined')
		return false;
	return true;
}

var strTrim = function(str) {
	if (!isObj(str))
		return 'undefined';
	str = str.replace(/^\s+|\s+$/g, '');
	return str;
}

var isNull = function(str) {
	if (!isObj(str))
		return 'undefined';
	str = strTrim(str);
	if (str.length > 0)
		return false;
	return true;
}

var isInt = function(str) {
	var reg = /^(-|\+)?\d+$/;
	return reg.test(str);
}

var isFloat = function(str){  
	var reg = /^(-?\d+)(\.\d+)?$/;
	return reg.test(str);
} 

$('.float-input').change(function(){
	if(!isFloatAndNotNull($(this).val())){
		$(this).val(0);
	}
});

$('.int-input').change(function(){
	if(!isIntAndNotNull($(this).val())){
		$(this).val(0);
	}
});

$('.int-count-input').change(function(){
	if(!isIntAndNotNull($(this).val())){
		$(this).val(1);
	}
});
