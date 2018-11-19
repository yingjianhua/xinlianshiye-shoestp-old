<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--谷歌统计代码 运营加的--%>
<script async src="https://www.googletagmanager.com/gtag/js?id=AW-783435725"></script>
<script>
  window.dataLayer = window.dataLayer || [];

  function gtag() {
    dataLayer.push(arguments);
  }

  gtag('js', new Date());

  gtag('config', 'AW-783435725');
</script>

<div class="new_header">
    <div class="wide clean">
        <h1 class="pic_box logo fl">
            <a href="/">
                <img src="/home/static/images/85bb6f7a6c.png" alt="SHOES TRADE PORT">
            </a>
            <img src="/home/static/images/logo2.png" style="margin-top:21px; margin-left:5px;">
        </h1>
        <ul class="new_nav_item fl" style="overflow: visible;">
            <li class="item fl">
                <a href="/" class="ia"><s:text name="Global.Home"/></a>
            </li>
            <li class="item fl">
                <a href="/home/pdt_PdtProduct" class="ia"><s:text name="Global.Product"/></a>
            </li>
            <li class="item fl">
                <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                   class="ia"><s:text name="romanianOfflineExhibition"/></a>
            </li>
            <!--                 <li class="item fl"> -->
            <%--                     <a href="/home/pdt_PdtProduct?orderfld=New" class="ia"><s:text name="Global.New_Product" /></a> --%>
            <!--                 </li> -->
            <li class="item fl">
                <a href="/home/pdt_PdtProduct?orderfld=New" class="ia"><s:text
                        name="newArrival"/></a>
            </li>
        </ul>
        <div class="fr n_searico"></div>
        <div class="n_search">
            <form action="/home/pdt_PdtProduct" method="get" class="form" style="margin: 0;">
                <input type="text" class="text fl"
                       placeholder="<s:text name="What_Are_You_Looking_For"/>" name="Keyword"
                       notnull="" value="">
                <input type="submit" class="button fr FontBgColor" value="">
                <div class="clear"></div>
            </form>
        </div>
        <!-- .n_search -->
        <script type="text/javascript">
          $(function () {

            $('.n_searico').click(function (e) {

              e.stopPropagation();

              $(this).css('display', 'none');

              $('.new_nav_item').css('display', 'none');

              $('.n_search').fadeIn();

            });

            $('.n_search').click(function (e) {

              e.stopPropagation();

            });

            $('body').click(function () {

              $('.n_search').css('display', 'none');

              $('.new_nav_item').fadeIn();

              $('.n_searico').fadeIn();

            });

          });
        </script>
    </div>
</div>
<!-- .new_header -->
