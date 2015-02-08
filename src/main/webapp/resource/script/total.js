/** ***计算总价和总数***************************************************** */

function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
    	return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  

$(document).ready(function() {

	$("input[type=checkbox][name=productIds]").each(function(index) {
		$("input[type=checkbox][name=productIds]").attr('checked', false);
		var multiplier = $(this).parent().parent()
				.find("td.price-per-products").text();
		var numpro = $(this).parent().parent().find("td.num-products")
				.children("input").val();
		var rowTotal = numpro * multiplier;
		$(this).parent().parent().find("td.num-products").attr("rowtotle",
				rowTotal);
		$(this).parent().parent().find("td.num-products")
				.attr("numpro", numpro);
	});

	$("input[type=checkbox][name=productIds]").each(function() {
				$(this).click(function(e) {
							if ($(this).is(":checked")) {
								$(this).parents("tr").attr("ischecked", "1");
							} else {
								$(this).parents("tr").attr("ischecked", "0");
							}
							calcTotalProducts();
						});

			});
	$(".num-products-input").on('input', function(e) {
		var $this = $(this);

		var numpro = $this.val();
		var multiplier = $this.parent().parent().find("td.price-per-products")
				.text();

		if (!isNaN(numpro) && numpro >= 0) {

			var rowTotal = numpro * multiplier;

			$this.parent().parent().find("td.num-products").attr("rowtotle",
					rowTotal);
			$this.parent().parent().find("td.num-products").attr("numpro",
					numpro);
			calcTotalProducts();
		} else {
			alert('请输入正整数');
			numpro = 1;
			$this.val(1);
		}
		$this.focus(); // 判断失败不允许其失去焦点。
	});

	$("#checkboxAll").click(function(e) {
				if ($(this).is(":checked")) {
					$(".table_pro_body tr").each(function(index, element) {
								$(this).attr("ischecked", "1");
							});
				} else {
					$(".table_pro_body tr").each(function(index, element) {
								$(this).attr("ischecked", "0");
							});
				}
				calcTotalProducts();
			})

	calcTotalProducts();
});

function calcTotalProducts() {
	
	var totalProducts = 0;
	var totalNumProducts = 0;

	$(".table_pro_body tr").each(function(index, element) {
		if ($(this).attr("ischecked") == '1') {
			var thisValue = $(this).find("td.num-products").attr("rowtotle");
			var thisNum = $(this).find("td.num-products").attr("numpro");
			totalProducts += parseFloat(thisValue);
			totalNumProducts += parseInt(thisNum);
			}
		});
	$("#total-products").text(toDecimal(totalProducts));
	$("#total-num-products").text(totalNumProducts);
}
