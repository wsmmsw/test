$(function() {
    App.init();

    var Index = (function() {
        var me = {};

        // 处理一级菜单点击
        me.handleMenuClick = function() {
            $('#page-sidebar-menu > li').click(function(e) {
                var menu = $('#page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');

                // 添加选中 打开的样式
                 //$(this).addClass('active');
            });
        };

        // 处理子菜单点击
        me.handleSubMenuClick = function() {
            $('#page-sidebar-menu>li>a').click(function(e) {
                e.preventDefault();
                var url = this.href;
                var tt =$.trim( $(this).text());
                //alert(tt);
                if (url != null && url != 'javascript:;') {
                	//console.log("urlis:" + url);
                    $.get(url,{"random":Math.random()}, function(data) {
                    	$('#index-page-title').text(tt);
                    	//console.log(data);
                        $('#main-content').html(data);
                        var breadcrumb = '<li><i class="fa fa-home"></i> '+tt+'<i class="fa fa-angle-right"></i></li>';
                        $('#breadcrumb').html(breadcrumb);
                    });
                }
            });
            $('#page-sidebar-menu>li>ul>li>a').click(function(e) {
                e.preventDefault();
                var url = this.href;
                var tt =$.trim( $(this).text());
                //alert(url);
                if (url != null && url != 'javascript:;') {
                    $.get(url,{"random":Math.random()}, function(data) {
                    	$('#index-page-title').text(tt);
                    	//console.log(data);
                        $('#main-content').html(data);
                        if(tt!='首页'){
	                        var breadcrumb = '<li><i class="fa fa-home"></i> 首页<i class="fa fa-angle-right"></i></li>'
	                        	+'<li> '+tt+'</li>';
	                        $('#breadcrumb').html(breadcrumb);
                        }
                    });
                }
            });
            if($('#page-sidebar-menu>li>ul>li>ul>li>a').length>0){
	            $('#page-sidebar-menu>li>ul>li>ul>li>a').click(function(e) {
	                e.preventDefault();
	                var url = this.href;
	                var tt =$.trim( $(this).text());
//	                var parentUrl=$(this).parent().parent().siblings('a').attr('href');
//	                alert(parentUrl);
	                var parentObj = $(this).parent().parent().siblings('a');
	                var parentText = $.trim(parentObj.text());
               // alert(parentText);
	                //alert(tt);
	                if (url != null && url != 'javascript:;') {
	                    $.get(url,{"random":Math.random()}, function(data) {
	                    	$('#index-page-title').text(tt);
	                        $('#main-content').html(data);
	                        if(tt!='首页'){
		                        var breadcrumb = '<li><i class="fa fa-home"><a href="#" onclick="doclickonbreadcrumb(\'btn-dashboard\')"></i> 首页</a>'
		                        	+'<i class="fa fa-angle-right"></i></li><li><a href="#" onclick="doclickonbreadcrumb(\'btn-product\')">'+parentText+'</a><i class="fa fa-angle-right"></i></li>'
		                        	+'<li> '+tt+'</li>';
		                       // alert(breadcrumb);
		                        $('#breadcrumb').html(breadcrumb);
	                        }
	                    });
	                }
	            });
            }
            if($('#page-sidebar-menu>li>ul>li>ul>li>ul>li>a').length>0){
	            $('#page-sidebar-menu>li>ul>li>ul>li>ul>li>a').click(function(e) {
	                e.preventDefault();
	                var url = this.href;
	                var tt =$.trim( $(this).text());
//	                var parentUrl=$(this).parent().parent().siblings('a').attr('href');
//	                alert(parentUrl);
	                var parentObj = $(this).parent().parent().parent().parent().siblings('a');
	                var parentText = $.trim(parentObj.text());
               // alert(parentText);
	                //alert(tt);
	                if (url != null && url != 'javascript:;') {
	                    $.get(url,{"random":Math.random()}, function(data) {
	                    	$('#index-page-title').text(tt);
	                        $('#main-content').html(data);
	                        if(tt!='首页'){
		                        var breadcrumb = '<li><i class="fa fa-home"><a href="#" onclick="doclickonbreadcrumb(\'btn-dashboard\')"></i> 首页</a>'
		                        	+'<i class="fa fa-angle-right"></i></li><li><a href="#" onclick="doclickonbreadcrumb(\'btn-product\')">'+parentText+'</a><i class="fa fa-angle-right"></i></li>'
		                        	+'<li> '+tt+'</li>';
		                       // alert(breadcrumb);
		                        $('#breadcrumb').html(breadcrumb);
	                        }
	                    });
	                }
	            });
            }
        };

        me.init = function() {
            me.handleMenuClick();
            me.handleSubMenuClick();
        };

        return me;
    })();

    Index.init();
    $.ajaxSetup({cache:false});
    $('#btn-dashboard').trigger("click");
});
function doclickonbreadcrumb(obj){
	//alert(obj);
	$('#'+obj).trigger("click");
}
