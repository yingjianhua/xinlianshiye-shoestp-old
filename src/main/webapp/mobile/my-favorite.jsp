<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <script type="text/javascript">
        $(function () {
            user_obj.user_fav()
        });
    </script>

    <style type="text/css">
        .crumb {
            background: #f4f4f4;
        }

        .user_favorite {
            padding: 0;
        }

        .detail_prolist {
            margin: 0;
        }

        .detail_prolist .item2 {
            position: relative;
            box-sizing: border-box;
            padding: 0;
            float: left;
            background: #e8e8e8;
            width: 46%;
            margin: 2%;
            border: 1px solid #e8e8e8;
        }

        .detail_prolist .item2 input {
            position: absolute;
            top: 0.5rem;
            right: 0.5rem;
        }

        .detail_prolist .item2 .info .name {
            box-sizing: border-box;
            padding: 0 0.5rem;
            height: 1.4375rem;
            overflow: hidden;
            margin-top: 0.5rem;
            font-size: 0.625rem;
        }

        .detail_prolist .item2 .info .price {
            padding: 0 0.5rem;
            font-size: 0.75rem;
            color: #ff6420;
            margin-top: 0.3125rem;
        }

        .detail_prolist .item2 .xmg-bottom {
            height: 1.75rem;
            padding: 0 0.5rem;
            margin-top: 0.8125rem;
        }

        .detail_prolist .item2 .xmg-bottom .addcart, .detail_prolist .item2 .xmg-bottom .del {
            height: 1.34375rem;
            line-height: 1.375rem;
            float: left;
            background: #b22727;
            padding: 0 0.375rem;
            border-radius: 0.1875rem;
            color: #fff;
            font-size: 0.6875rem;
            width: 30%;
            text-align: center;
            overflow: hidden;
        }

        .detail_prolist .item2 .xmg-bottom .addcart {
            background: #043d81;
            float: right;
            width: 50%;
        }

        .detail_prolist .item2 .xmg-bottom .addcart img {
            display: inline-block;
            width: 0.8rem;
            height: 0.8rem;
            margin-bottom: -0.1rem;
        }

        .xmg-myfav-nav {
            position: fixed;
            left: 0;
            bottom: 3.125rem;
            height: 2.5rem;
            padding: 0.3125rem 2%;
            width: 96%;
            background: #fff;
            z-index: 997;
        }

        .xmg-myfav-nav .xmg-common .xmg-btn {
            float: left;
            height: 1.53125rem;
            line-height: 1.53125rem;
            padding: 0 1.1875rem;
            color: #fff;
            border-radius: 0.1875rem;
            margin-left: 0.46875rem;
            background: #b9b9b9;
        }

        .xmg-myfav-nav .xmg-common .xmg-alladdtocart {
            background: #b22727;
        }

        .xmg-myfav-nav .xmg-common .xmg-btn img {
            display: inline-block;
            width: 0.8rem;
            height: 0.8rem;
            margin-bottom: -0.1rem;
        }

        .xmg-myfav-nav .xmg-common input {
            float: left;
            margin: 0.4rem 0.625rem 0 0;
        }

        .xmg-myfav-navtab {
            width: 96%;
            padding: 0 2%;
            height: 1.875rem;
            margin: 0.78125rem 0 0.1rem 0;
            line-height: 1.875rem;
        }

        .xmg-myfav-navtab .xmg-btn {
            float: right;
            height: 1.875rem;
            line-height: 1.875rem;
            padding: 0 0.625rem;
            border: 1px solid #e5e5e5;
            border-radius: 2px;
        }

        .suppx_cate222 {
            margin-left: 2%;
        }

        .suppx_cate222 .cate {
            position: relative;
            z-index: 99;
        }

        .suppx_cate222 .cate .t {
            border: 1px solid #e5e5e5;
            background: url(../home/static/themes/default/mobile/images/catar.png) no-repeat right center/0.9rem;
            border-radius: 2px;
            padding: 0 1.12rem 0 0.5rem;
            font-size: 12px;
            color: #666;
            white-space: nowrap;
            height: 1.8125rem;
        }

        .suppx_cate222 .cate .ct {
            display: none;
            position: absolute;
            top: 2rem;
            right: 0;
        }

        .suppx_cate222 .cate .ct .mnm {
            background: #fff;
            border-radius: 5px;
            position: relative;
            padding: 6px 10px;
            box-shadow: 0 0 5px #CCC;
        }

        .suppx_cate222 .cate .ct .mnm .i {
            line-height: 1.5rem;
            font-size: 12px;
            white-space: nowrap;
            color: #666;
            font-weight: normal;
        }

        .xmg-myf-tc {
            display: none;
        }

        .xmg-myf-tc .xmg-hsbg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: #000;
            opacity: 0.5;
            z-index: 998;
        }

        .xmg-myf-tc .xmg-box {
            position: fixed;
            top: 50%;
            left: 50%;
            width: 13.625rem;
            height: 7.8125rem;
            background: #fff;
            z-index: 999;
            margin: -3.90625rem 0 0 -6.8125rem;
            border-radius: 0.3125rem;
            overflow: hidden;
        }

        .xmg-myf-tc .xmg-box2 {
            width: 90%;
            height: 84%;
            margin: 5%;
            box-sizing: border-box;
            border: 1px solid #dcdcdc;
            border-radius: 0.3125rem;
        }

        .xmg-myf-tc .xmg-box h1 {
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            font-size: 0.75rem;
            height: 3.4375rem;
            /*line-height: 3.4375rem;*/
            color: #666;
            font-weight: bold;
            text-align: center;
            margin-top: 0.5625rem;
        }

        .xmg-myf-tc .xmg-box .xmg-bottom {
            height: 1.40625rem;
            line-height: 1.40625rem;
            text-align: center;
        }

        .xmg-myf-tc .xmg-box .xmg-bottom a {
            display: inline-block;
            width: 2.0625rem;
            height: 1.40625rem;
            line-height: 1.40625rem;
            border-radius: 0.15625rem;
            text-align: center;
            color: #fff;
            font-size: 0.75rem;
            font-weight: bold;
            margin: 0 0.25rem;
            background: #a4a4a4;
        }

        .xmg-myf-tc .xmg-box .xmg-bottom a.ok {
            background: #000;
        }

        .detail_prolist .img img{
        	width: 100%;
			height: 9rem;
			object-fit: cover;
        }
    </style>
</head>

<body>

<%@ include file="/mobile/template/header.jsp" %>
<div class="wrapper">

    <div id="user">
        <div class="crumb clean">
            <a href="/">
                        <span class="icon_crumb_home">
						</span>
            </a>
            <em>
                <i>
                </i>
            </em>
            <a href="/home/usr_UsrPurchase_userIndex">
                <!-- My Account -->
                <s:text name="my_account" />
            </a>
            <em>
                <i>
                </i>
            </em>
            <a href="/home/usr_UsrFavorites_myfavorite">
                <!-- My Favorites -->
                <s:text name="my_fav" />
            </a>
        </div>


        <div class="xmg-myfav-navtab">
            <h2 class="fl"><!-- My Favorite --><s:text name="my_fav" />:</h2>

            <div class="fl suppx_cate222">
                <div class="cate fl" id="scren_cate">
                    <div class="t">
                        <!-- all_products -->
                        <s:text name="all_products" />
                    </div>
                    <div class="ct">
                        <div class="mnm">
                            <div class="i" cateid="-1">
                                <!-- All product -->
                                <s:text name="all_products" />
                            </div>
                            <c:forEach items="${catList}" var="cat">
                                <div class="i" cateid="${cat.pkey}">
                                        ${cat.name}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <a href="/home/usr_UsrFavorites_myRecycle" class="xmg-btn"><!-- Recycle Bin --><s:text name="Recycle_Bin" /></a>

        </div>

        <div class="user_favorite">
            <div class="detail_prolist">
            </div>
        </div>
        <div class="blank15">
        </div>
    </div>
</div>


<div class="xmg-myfav-nav">
    <div class="xmg-common fr">
        <input type="checkbox" name="ckAll" value="全选/反选" onclick="checkAll()" id="all"/>
        <div class="xmg-btn xmg-btn-clear"><!-- Clear --><s:text name="clear" /></div>
        <div class="xmg-btn xmg-alladdtocart"><img
                src="../home/static/themes/default/mobile/images/myfavorite-carttb.png" alt=""/> <!-- Add to Cart --><s:text name="inq" />
        </div>
    </div>
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
                           <!--  Sign In -->
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
    <div style="height: 4rem;"></div>
</footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
<!--弹出1-->
<div class="xmg-myf-tc xmg-myf-tc111">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <div class="xmg-box2">
            <h1><!-- Are you sure move to Recycled? --><s:text name="toRecycled" /></h1>
            <div class="xmg-bottom">
                <a href="javascript:;" class="ok"><s:text name="yes" /></a>
                <a href="javascript:;" class="no"><s:text name="no" /></a>
            </div>
        </div>
    </div>
</div>

<!--弹出2-->
<div class="xmg-myf-tc xmg-myf-tc222">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <div class="xmg-box2">
            <h1><!-- Are you sure clear the favorites? --><s:text name="del_ask" /><br/> </h1>
            <div class="xmg-bottom">
                <a href="javascript:void(0);" class="ok" style="width:4rem"><s:text name="Global.Yes" /></a>
                <a href="javascript:void(0);" class="no" style="width:4rem"><s:text name="Global.No" /></a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var renderList = function (data) {
        var div = '';
        $.each(data, function (i, val) {
            //显示普通商品
            div += '<div class="item2 clean ui_border_b">' +
                '<input type="checkbox" name="ckOne" data="' + val.pdtPkey + '"  catId="' + val.id + '"onclick="checkOne()"/>' +
                '<div class="img">' +
                '<a href="/home/pdt_PdtProduct_gtProductsInfo?id=' + val.pdtPkey + '">' +
                '<img src="${envConfig.imageBaseUrl}' + val.img + '" alt="' + val.name + '">' +
                '</a>' +
                '</div>' +
                '<div class="info fav_info clean">' +
                '<div class="name">' +
                '<a href="/home/pdt_PdtProduct_gtProductsInfo?id=' + val.pdtPkey + '">' +
                val.name +
                '</a>' +
                '</div>' +
                '<div class="price" id="price_' + val.pdtPkey + '">${env.currency.symbols}' +
                val.amt +
                '</div>' +
                '<div class="xmg-bottom">' +
                '<div class="del" onclick="recycleThis(' + val.id + ')"><s:text name="cart.remove"/></div>' +
                '<div class="addcart" onclick="addcart(' + val.id + ')" > <s:text name="my-inquiry-publish.View_Inquiry" /></div>' +
                '</div>' +
                '</div>' +
                '</div>';
        });
        $(".detail_prolist").html(div);
    }
    var loadlist = function (data) {
        $.ajax({
            url: '/home/usr_UsrFavorites_myfavoriteAjax',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                renderList(data.result)
            }
        })
    }
    window.onload = function () {

        var ckAll = document.getElementById("all");
        ckAll.checked = false;
        loadlist()
        $("div.xmg-btn.xmg-alladdtocart").on("click", function () {
            addcart(chooseChecked())
        })
        $('#scren_cate .t').click(function (e) {
            e.stopPropagation();
            $('#scren_cate .ct').toggle(0);
        });
        $('#scren_cate .ct .i').click(function (e) {
            var cateid = parseInt($(this).attr('cateid'));
            var name = $(this).html();
            $('#scren_cate .t').html(name);
            $('#scren_cate .ct').css('display', 'none');
            $.get('/home/usr_UsrFavorites_myfavoriteAjax', {'category': cateid}, function (data) {
                renderList(data.result)
            });
        });
    }

    function recycleThis(id) {
      layer.open({
       content: '<s:text name="del_ask" />'
       ,btn: ['<s:text name="Global.Yes" />', '<s:text name="Global.No" />']
       ,yes: function(index){
          $.ajax({
              url: '/home/usr_UsrFavorites_recycleFavorite',
              type: 'post',
              data: {"pkey": id},
              dataType: 'json',
              success: function (data) {
                  loadlist()
                  // location.reload();
              }
          })
          layer.close(index);
       }
     });
    }

    function getPic(str) {
        return str.split(",")[0];
    }

    function checkAll() {   //全选 和反选  以及 单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        for (var i = 0; i < ckOnes.length; i++) {
            ckOnes[i].checked = all.checked;//把全选框的状态循环赋给所有的单选框！！！！！！
        }
    }


    function checkOne() {//单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        //定义一个变量，统计状态为选中的单选框的个数
        var count = 0;
        for (var i = 0; i < ckOnes.length; i++) {//循环各个单选
            var ckOne = ckOnes[i];
            //判断选中的
            if (ckOne.checked == true) {//循环得到单选的状态，如果单选状态为true的话，就让count++
                count++;//这里得到了到底有几个单选框被选中
            }
        }
        if (count == ckOnes.length) {//判断count  如果count的数量等于单选框的数量的话，也就是说每一个都选中了的话，就让全选框状态为true
            all.checked = true;
        } else {
            all.checked = false;//否则的话 全选框状态为 不选
        }
    }

    function chooseChecked() {
        var t = $("div.item2.clean.ui_border_b input[name='ckOne']");
        var postDate = [];
        $.each(t, function (i, v) {
            if ($(v).prop("checked")) {
                postDate.push($(v).attr("catId"))
            }
        })
        if (postDate.length > 0)
            return postDate.toString()
        throw "Error ";
    }



    function addcart(id) {
        var pkeys = chooseChecked();
        var result = pkeys.split(",");
        for (var i = 0; i < result.length; i++) {
            console.log(result[i])
            $.ajax({
                url: '/home/pdt_PdtConsultPdtList_add',
                type: 'post',
                data: {"product": result[i]},
                dataType: 'json',
                success: function (data) {
                    if (data.success == true) {
                        layer.msg('<s:text name="my-inquiry-publish.View_Inquiry"/>', {icon: 1});
                    } else {
                        layer.msg(getMessage(data.msg), {icon: 2, time: 2000});
                    }
                }
            })
        }
/*        $.ajax({
            url: '/home/usr_UsrFavorites_addSinglePdt',
            type: 'post',
            data: {favoritesPkeys: id},
            dataType: 'json',
            success: function (data) {
                if (data.success === true) {
                    layer.open({
                        content: "I18N 加入成功"
                        , skin: 'msg'
                        , time: 2
                    });
                } else {
                    layer.open({
                        content: data.msg
                        , skin: 'msg'
                        , time: 2
                    });
                }
            }
        })*/
    }

    $(".ok").on("click", function () {
        var pkeys = chooseChecked();
        if (pkeys == "" || typeof(pkeys) == "undefined") {
            layer.open({
                content: "未选择商品"
                , skin: 'msg'
                , time: 2
            });
            return;
        }
        $.ajax({
            url: '/home/usr_UsrFavorites_recycleFavorite',
            type: 'post',
            data: {"pkeys": pkeys},
            dataType: 'json',
            success: function (data) {
                if (data.ret === 1) {
                    loadlist()
                } else {
                    layer.open({
                        content: data.msg
                        , skin: 'msg'
                        , time: 2
                    });
                }
                $('.xmg-myf-tc222').hide();
            }
        })
    })


    $('.detail_prolist .item2 .xmg-bottom .del').click(function () {
        $('.xmg-myf-tc111').show();
    })
    $('.xmg-myf-tc111 .xmg-box .xmg-bottom a.no').click(function () {
        $('.xmg-myf-tc111').hide();
    })

    $('.xmg-myfav-nav .xmg-common .xmg-btn-clear').click(function () {
        // 没选中商品时,不触发
        var pkeys = chooseChecked();
        if (pkeys == "" || typeof(pkeys) == "undefined") {
            return;
        }
        $('.xmg-myf-tc222').show();
    })
    $('.xmg-myf-tc222 .xmg-box .xmg-bottom a.no').click(function () {
        $('.xmg-myf-tc222').hide();
    })
</script>

</body>
</html>
