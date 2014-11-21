$(document).ready(function() {
	
	//行点击样式
	$(".table_pro_body tr >td:not(:last-child)").click(function() {
			if($(this).parent().hasClass('selected')){
			$(this).parent()
			 .removeClass('selected')
			 .find(':checkbox').attr('checked',false);
		}else{
			$(this).parent()
			.addClass('selected')
			.find(':checkbox').attr('checked',true);
		}
	});
 
	//奇偶数行不同颜色
	$(".table_pro_body tr:odd").addClass("odd");
	$(".table_pro_body tr:even").addClass("even");
	$(".addressBar").hide();
   
});
