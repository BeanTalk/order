(function($){
	$.extend($.fn,{

		shoping:function(options){
			var self=this,
				$shop=$('.shopping'),
				$num=$('.fontred');
			
			var S={
				init:function(){
					$(self).data('click',true).live('click',this.addShoping);
				},
				addShoping:function(e){
					e.stopPropagation();
					
					var $target=$(e.target),
						id=$target.attr('id'),
						dis=$target.data('click'),
					    x = $target.offset().left + 30,
						y = $target.offset().top + 10,
						X = $shop.offset().left+$shop.width()/2-$target.width()/2+10,
						Y = $shop.offset().top;
					
					// click the float
					if(dis){
						if ($('#floatOrder').length <= 0) {
							$('body').append('<div id="floatOrder"><img src="http://localhost/pro1.jpg" width="50" height="50" /></div');
						};
						var $obj=$('#floatOrder');
						if(!$obj.is(':animated')){
							$obj.css({'left': x,'top': y}).animate({'left': X,'top': Y-80}, 500, function() {
								$obj.stop(false, false).animate({'top': Y-20,'opacity':0}, 500, function(){
									$obj.fadeOut(300,function(){
										$obj.remove();	
										$target.data('click',false).addClass('dis-click');
										var	num=Number($num.text());
										$num.text(num+1);
									});
								});
							});	
						};
					};
					
					//
				}
			};
			S.init(); 
		}
	});	
})(jQuery);
