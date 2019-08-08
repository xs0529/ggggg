// +----------------------------------------------------------------------
// | Tplay [ WE ONLY DO WHAT IS NECESSARY ]
// +----------------------------------------------------------------------
// | Copyright (c) 2018 http://vaeyo.pengyichen.cn All rights reserved.
// +----------------------------------------------------------------------
// | Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
// +----------------------------------------------------------------------
// | Author: 听雨 < 389625819@qq.com >
// +----------------------------------------------------------------------
layui.define(['layer','element'], function(exports){ 
	var layer = layui.layer,$ = layui.$,element = layui.element;

  	var obj = {
    	addTab: function(e){
    		var elem = $(".vaeyo-tab .layui-tab-title li[lay-id="+e.id+"]");
    		if(elem.length>0){
    			element.tabChange(e.elem, e.id);
    			return false;
    		}
    		$("[vaeyo-loading]").show();
      		element.tabAdd(e.elem, {
			  title: e.title
			  ,content: '<iframe id="'+e.id+'" name="'+e.title+'" src="'+e.content+'" frameborder="0" align="left" width="100%" height="100%" scrolling="yes"></iframe>'
			  ,id: e.id
			});
			element.tabChange(e.elem, e.id);
            var frm = document.getElementById(e.id);  
            $(frm).load(function(){ 
                setTimeout(function(){
                    $("[vaeyo-loading]").hide();
                },500) 
                $('#'+e.id).contents().find("[vaeyo_tab]").on('click', function(){
                    self.addTab({
                        elem:"vaeyo-tab",
                        title:$(this).attr('vae-title'),
                        content:$(this).attr('vae-src'),
                        id:$(this).attr('vae-id')
                    })
                    self.recordTab();
                }) 
            });
    	},
    	changeTab: function(e){
    		if(e == 0){
    			$(".vaeyo-menulist-2").children("i").attr("class","layui-icon layui-icon-right");
				$(".vaeyo-menulist-3:visible").slideUp("slow");
				return false;
    		}
    		var elem = $(".vaeyo-menulist ul li[vae-id="+e+"]");
            var theme = layui.data('vaeyoAdmin_theme').color ? layui.data('vaeyoAdmin_theme').color[1] : "#000";
    		$(".vaeyo-menulist-3 ul li").css('color',theme);
            elem.css('color',"#009688");
            elem.siblings(".vaeyo-menulist-3:visible").slideUp("slow");
            elem.siblings(".vaeyo-menulist-2").children("i").attr("class","layui-icon layui-icon-right");
    		elem.parents(".vaeyo-menulist-3").show("show").siblings(".vaeyo-menulist-3:visible").slideUp("slow");
			elem.parents(".vaeyo-menulist-3").siblings(".vaeyo-menulist-2").children("i").attr("class","layui-icon layui-icon-right");
			elem.parents(".vaeyo-menulist-3").prev(".vaeyo-menulist-2").children("i").attr("class","layui-icon layui-icon-down");
    	},
        recordTab: function(e){
            var tab = $('[vaeyo-tab-content]').find("iframe");
            var thatIframe = $(".layui-tab-item.layui-show").find("iframe")[0];
            var tabs = [];
            var thatObj = {
                id:thatIframe.id,
                name:thatIframe.name,
                src:thatIframe.src 
            }
            tabs.push(thatObj);
            for (var i = tab.length - 1; i >= 0; i--) {
                if(tab[i].id != 0 && tab[i].id != thatIframe.id){
                    var obj = {
                        id:tab[i].id,
                        name:tab[i].name,
                        src:tab[i].src 
                    }
                    tabs.push(obj);
                }
            }
            layui.sessionData('vaeyoAdmin_tab', {
                key: 'vaeyoTab'
                ,value: tabs
            });
        }
  	};
  
    exports('vaeyo', obj);
});    