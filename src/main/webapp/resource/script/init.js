$(document).ready(function() {

	// 行点击样式
	$(".table_pro_body tr >td:not(:last-child)").click(function() {
		if ($(this).parent().hasClass('selected')) {
			$(this).parent().removeClass('selected').find(':checkbox').attr(
					'checked', false);
		} else {
			$(this).parent().addClass('selected').find(':checkbox').attr(
					'checked', true);
		}
	});

	// 奇偶数行不同颜色
	$(".table_pro_body tr:odd").addClass("odd");
	$(".table_pro_body tr:even").addClass("even");
	$(".addressBar").hide();

	// search button
	$("#search_btn").click(function() {
		var search_context = $("#search_context").val();
		if ($.trim($("#search_context").val()) == '') {
			alert("请输入需要的产品号或产品名称");
			return;
		}
		window.location.href = '/order/views/search/productlist?searchContext='
				+ search_context;
	});

	$("#groupId").change(function() {
		$.getJSON("/order/order/user/userlist", {
					groupId : $(this).val()
				}, function(data) {
					$("#userId").empty()
							.append("<option value=\"\">请选择</option>");
					if(data !== null && data !== ""){
						$.each(data, function(i) {
							if(data[i].name !== "undefined"){
								$("#userId").append("<option value=\""
									+ data[i].id + "\">" + data[i].name
									+ "</option>");
							}
						});
					}
				});
	});
	
	$(".hidden_class").hide();
	
});
