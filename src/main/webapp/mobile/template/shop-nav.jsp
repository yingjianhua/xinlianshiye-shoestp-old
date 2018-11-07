<%--
顶部导航栏
  Created by IntelliJ IDEA.
  User: lijie@shoestp.cn
  Date: 2018/11/7
  Time: 11:12
--%>
<%@ page pageEncoding="UTF-8" %>
<div class="nav-bar bg-white">
    <ul class="flexible text-center">
        <li>
            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}"><s:text name="home"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}"> <s:text name="order_line.spec"/></a>
        </li>
        <%--<c:if test="${supView.prmAuthrity == true}">--%>
        <%--<li class="">--%>
        <%--<a href="/home/usr_UsrSupplier_gtSupGroup?pkey=${supView.pkey}"><s:text name="group_order.activity" /></a>--%>
        <%--</li>--%>
        <%--</c:if>--%>
        <li>
            <a href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}"><s:text name="freight_line.brief"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrSupplier_gtSupContact?pkey=${supView.pkey}"> <s:text name="contactmenu"/></a>
        </li>
    </ul>
</div>
<script>
    if (window.location.href.indexOf('usr_UsrSupplier_gtSupIndex') != -1) {
        $("ul.flexible.text-center li:eq(0)").addClass('active')
    }
    if (window.location.href.indexOf('usr_UsrSupplier_gtSupPro') != -1) {
        $("ul.flexible.text-center li:eq(1)").addClass('active')

    }
    if (window.location.href.indexOf('usr_UsrSupplier_gtSupInfo') != -1) {
        $("ul.flexible.text-center li:eq(2)").addClass('active')

    }
    if (window.location.href.indexOf('usr_UsrSupplier_gtSupContact') != -1) {
        $("ul.flexible.text-center li:eq(3)").addClass('active')
    }


</script>
