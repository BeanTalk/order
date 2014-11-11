$(function(){
        var $slider=$('.slider');
        var $slider_ul=$('.slider-img');
        var $btn=$('.slider-btn');
        var $btn_a=$btn.find('a')
        var v_width=$slider.width();
        
        var page=1;
        
        var timer=null;
        var btnClass=null;

        var page_count=$slider_ul.find('li').length;//謚願ｿ吩ｸｪ蛟ｼ襍狗ｻ吝ｰ丞怕轤ｹ逧・ｸｪ謨ｰ
        
        var slider_cir="<li class='selected' href='#'><a></a></li>";
        for(var i=1;i<page_count;i++){
                //蜉ｨ諤∵ｷｻ蜉蟆丞怕轤ｹ
                slider_cir+="<li><a href='#'></a></li>";
                }
        $('.slider-circle').append(slider_cir);
        
        var cirLeft=$('.slider-circle').width()*(-0.5);
        $('.slider-circle').css({'marginLeft':cirLeft});
        
        $slider_ul.width(page_count*v_width);
        
        function move(obj,classname){
                //謇句勘蜿願・蜉ｨ謦ｭ謾ｾ
        if(!$slider_ul.is(':animated')){
                if(classname=='prevBtn'){
                        if(page==1){
                                        $slider_ul.animate({left:-v_width*(page_count-1)});
                                        page=page_count; 
                                        cirMove();
                        }
                        else{
                                        $slider_ul.animate({left:'+='+v_width},"slow");
                                        page--;
                                        cirMove();
                        }        
                }
                else{
                        if(page==page_count){
                                        $slider_ul.animate({left:0});
                                        page=1;
                                        cirMove();
                                }
                        else{
                                        $slider_ul.animate({left:'-='+v_width},"slow");
                                        page++;
                                        cirMove();
                                }
                        }
                }
        }
        
        function cirMove(){
                //譽豬却age逧・ｼ・御ｽｿ蠖灘燕逧аage荳市elected逧・ｰ丞怕轤ｹ荳閾ｴ
                $('.slider-circle li').eq(page-1).addClass('selected')
                                                                                                .siblings().removeClass('selected');
        }
        
        $slider.mouseover(function(){
                $btn.css({'display':'block'});
                clearInterval(timer);
                                }).mouseout(function(){
                $btn.css({'display':'none'});                
                clearInterval(timer);
                timer=setInterval(move,3000);
                                }).trigger("mouseout");//豼豢ｻ閾ｪ蜉ｨ謦ｭ謾ｾ

        $btn_a.mouseover(function(){
                //螳樒鴫騾乗・貂仙序・碁仆豁｢蜀呈ｳ｡
                        $(this).animate({opacity:0.6},'fast');
                        $btn.css({'display':'block'});
                         return false;
                }).mouseleave(function(){
                        $(this).animate({opacity:0.3},'fast');
                        $btn.css({'display':'none'});
                         return false;
                }).click(function(){
                        //謇句勘轤ｹ蜃ｻ貂・勁隶｡譌ｶ蝎ｨ
                        btnClass=this.className;
                        clearInterval(timer);
                        timer=setInterval(move,3000);
                        move($(this),this.className);
                });
                
        $('.slider-circle li').live('click',function(){
                        var index=$('.slider-circle li').index(this);
                        $slider_ul.animate({left:-v_width*index},'slow');
                        page=index+1;
                        cirMove();
                });
});