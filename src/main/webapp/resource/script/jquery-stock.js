(function($) {
	$.extend($.fn, {

		stock : function(options) {

			var self = this, $shop = $('.stock'), $num = $('#storeInBagCount');
			var S = {
				init : function() {
					$(self).data('click', true).live('click', this.addStock);
				},

				addStock : function(e) {
					e.stopPropagation();

					var $target = $(e.target), id = $target.attr('id'), dis = $target
							.data('click'), x = $target.offset().left + 30, y = $target
							.offset().top
							+ 10, X = $shop.offset().left + $shop.width() / 2
							- $target.width() / 2 + 10, Y = $shop.offset().top;

					// click the float
					if (dis) {
						$.post('/order/order/stock/addProductToStock/' + id,
								function(data) {
									if (data.msg == "had") {
										alert("对不起，您已经将该产品添加到备货栏中");
									}
									if (data.msg == "sccuess") {
										if ($('#floatOrder').length <= 0) {
											$('body')
													.append('<div id="floatOrder" width="25" height="25"></div>');
										};
										var $obj = $('#floatOrder');
										$obj.css({
													'left' : x,
													'top' : y
												}).animate({
													'left' : X,
													'top' : Y - 80
												}, 500, function() {
													$obj.stop(false, false)
															.animate(
																	{
																		'top' : Y
																				- 20,
																		'opacity' : 0
																	}, 500,
																	function() {
																		$obj
																				.fadeOut(
																						100,
																						function() {
																							$obj
																									.remove();
																							$target
																									.data(
																											'click',
																											false)
																									.addClass('gray')
																									.removeClass('green');
																						});
																	});
												});
										var num = Number($num.text());
										$num.text(num + 1);
									}
								});
					};
				}
			};
			S.init();
		}
	});
})(jQuery);
