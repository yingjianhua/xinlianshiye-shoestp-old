<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <link href="/home/static/themes/default/mobile/css/catalog.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/catalog.js"></script>
    </head>
    <body>
		<%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div class="catalog_search">
                <div class="search clean">
                    <form action="/home/pdt_PdtProduct" method="get">
                        <input type="search" value="" name="Keyword" placeholder="" class="text fl">
                        <input type="submit" value="" class="fr sub">
                    </form>
                </div>
            </div>
            <c:forEach items="${catMap}" varStatus="status" var="m">
	            <div class="catelog_list">

	                <div class="cate1 clean">
	                    <a href="/home/pdt_PdtProduct?cated=${m.key.pkey}" class="fr all">
	                        <!-- All --> 
	                        <s:text name="all" />
	                        &gt;
	                    </a>
	                    ${m.key.name}
	                </div>

	                <div class="cate2 clean">
	                	<c:forEach items="${m.value}" varStatus="status" var="s">
		                    <div class="item fl">
		                        <a href="/home/pdt_PdtProduct?cated=${s.pkey}" class="name">
		                            ${s.name}

		                        </a>
		                    </div>
	                    </c:forEach>
	                </div>

	            </div>
            </c:forEach>



        </div>

        <footer>
            <div id="prolist_mask_footer">
            </div>
            <div class="footer_top clean">
            </div>
            <ul class="footer_list ui_border_b clean" style="display:none;">
                <li class="fl" style="border-right:1px solid #ddd;">
                    <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                        <span class="list_left">
                            <!-- Sign In -->
                            <s:text name="sign_in" />
                        </span>
                        <span class="list_right">
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                </li>
            </ul>
            <nav>
            </nav>
            <section class="font_col border_col copyright">
                Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
                浙公网安备 33030402000493号
            </section>
        </footer>
        <%@ include file="/mobile/template/foot_menu.jsp" %>
    </body>
</html>
