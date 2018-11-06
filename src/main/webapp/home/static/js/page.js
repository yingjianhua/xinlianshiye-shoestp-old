var pageRender = function(page, el, prev, next, select) {
	var html = "";
	el.empty();
	if(page.totalPage<2)
		return;
	html+='<li>'
		+'<font class="page_button prev">'
		+'	<em class="icon_page_prev"></em>'+lang_obj.page.prev+''
		+'</font>'
		+'</li>'
	if(page.totalPage<7) {
		for(var i=1;i<=page.totalPage;i++) {
			html+='<li>'
			if(i==page.currentPage)
				html+='	<font class="page_item_current">'+i+'</font>'
			else
				html+='	<font class="page_item">'+i+'</font>'	
			html+='</li>'
		}
	} else {
		if(page.currentPage>4) {
			html+='<li><font class="page_item">1</font></li>'
			html+='<li><font>...</font></li>'
		}
		var start = 0;
		var end = 0;
		if(page.currentPage<4) {
			start = 1;
			end = 7;
		} else if(page.totalPage-page.currentPage<3) {
			end = page.totalPage;
			start = end-6;
		} else {
			start = page.currentPage-3;
			end = page.currentPage+3;
		}
		for(var i=start;i<=end;i++) {
			html+='<li>'
			if(i==page.currentPage)
				html+='	<font class="page_item_current">'+i+'</font>'
			else
				html+='	<font class="page_item">'+i+'</font>'	
			html+='</li>'
		}
		if(page.currentPage<(page.totalPage-3)) {
			html+='<li><font>...</font></li>'
			html+='<li><font class="page_item">'+page.totalPage+'</font></li>'
		}
	}
	html+='<li class="page_last">'
		+'	<font class="page_button next">'+lang_obj.page.next+'<em class="icon_page_next"></em>'
		+'	</font>'
		+'</li>'
	$("#turn_page").append(html);
	var prev_btn = el.find("li .page_button.prev");
	var next_btn = el.find("li .page_button.next");
	var item_btn = el.find("li .page_item");
	if(page.currentPage == 1)
		prev_btn.addClass("page_noclick")
	if(page.currentPage == page.totalPage) {
		console.log("addClass")
		next_btn.addClass("page_noclick")
	}
	prev_btn.on("click", function() {
		prev(page);
	})
	next_btn.on("click", function() {
		next(page);
	})
	item_btn.on("click", function() {
		select(page, parseInt($(this).text()));
	})
}