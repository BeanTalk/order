$.post('/order/order/buycard/get/productconut/', function(data) {
	$('#productInBagCount').text(data);
});

//时间插件
$(".datepicker").datepicker({dateFormat: 'yy-mm-dd'});

//全选与全不选
var isCheckAll=true;
$("#all_checkbox").click(function(){
	if(isCheckAll){
		$("input[type=checkbox]").prop("checked", true);
		isCheckAll=false;	
	}else{
		$("input[type=checkbox]").prop("checked", false);
		isCheckAll=true;
	}
});

// 选择用户订单
var isUserOrderCheckAll=true;
$(".userOrderClass").click(function(){
	if(isUserOrderCheckAll){
		var checked_id = "check_prodordid_" + $(this).val();
		$("input[type=checkbox][id^="+checked_id+"]").prop("checked",true);
		isUserOrderCheckAll=false;	
	}else{
		var checked_id = "check_prodordid_" + $(this).val();
		$("input[type=checkbox][id^="+checked_id+"]").prop("checked",false);
		isUserOrderCheckAll=true;
	}
});