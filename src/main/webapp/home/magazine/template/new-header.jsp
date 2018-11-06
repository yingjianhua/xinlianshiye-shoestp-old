<%@ page pageEncoding="UTF-8"%>
<div class="new_header w_1200">
        <div class="wide clean">
            <h1 class="pic_box logo fl">
                <a href="/">
                    <img src="/home/static/images/85bb6f7a6c.png" alt="SHOES TRADE PORT" style="width: auto;height: auto;">
                </a>
                <img src="/home/static/images/logo2.png" style="margin-top:21px; margin-left:5px;width: auto;height: auto;">
            </h1>
            <ul class="new_nav_item fl" style="overflow: visible;">
                <li class="item fl">
                    <a href="/" class="ia"><s:text name="Global.Home" /></a>
                </li>
                <li class="item fl">
                    <a href="/home/pdt_PdtProduct" class="ia"><s:text name="Global.Product" /></a>
                </li>
                <li class="item fl">
                    <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-romania.html" class="ia"><s:text name="romanianOfflineExhibition"/></a>
                </li>
                <li class="item fl">
                    <a href="/home/pdt_PdtProduct?orderfld=New" class="ia"><s:text name="newArrival"/></a>
                </li>
            </ul>
            <div class="fr n_searico"></div>
            <div class="n_search">
                <form action="/home/pdt_PdtProduct" method="get" class="form">
                    <input type="text" class="text fl" placeholder="What are you looking for ..." name="Keyword" notnull="" value="">
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
