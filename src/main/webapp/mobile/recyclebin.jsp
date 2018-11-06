<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
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
            height: 2.4rem;
            line-height: 1.2rem;
            margin-top: 0.5rem;
            font-size: 0.625rem;
            overflow : hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
        }

        .detail_prolist .item2 .info .price {
            padding: 0 0.5rem;
            font-size: 0.75rem;
            color: #ff6420;
            margin-top: 0.3125rem;
        }

        .detail_prolist .item2 .xmg-bottom {
            height: 2rem;
            padding: 0 0.5rem;
            margin-top: 0.4125rem;
        }

        .detail_prolist .item2 .xmg-bottom .restore, .detail_prolist .item2 .xmg-bottom .del {
            height: 1.6rem;
            line-height: 1.68rem;
            float: left;
            background: #b22727;
            padding: 0 0.375rem;
            border-radius: 0.1875rem;
            color: #fff;
            font-size: 0.6875rem;
            /* width: 41%; */
            text-align: center;
            overflow: hidden;
            margin-right: 0.4rem;
        }
        .detail_prolist .item2 .xmg-bottom .restore, .detail_prolist .item2 .xmg-bottom .del:last-child{
          margin-right: 0;
        }

        .detail_prolist .item2 .xmg-bottom .restore {
            background: #043d81;
            /*margin-left: 4%;*/
        }


        .xmg-myfav-nav{
        	position: fixed;
         left:0;
         bottom:3.125rem;
         height:2rem;
         padding:0.6rem 2%;
         width:96%;
         background:#fff;
         z-index: 997;
        }
        .xmg-myfav-nav .xmg-common .xmg-btn{
        	float:left;
         height:1.6rem;
        line-height: 1.68rem;
         padding:0 1.1875rem;
         color:#fff;
         border-radius:0.1875rem;
         margin-left:0.46875rem;
         background:#b9b9b9;
        }
        .xmg-myfav-nav .xmg-common .xmg-alladdtocart{
        	background:#b22727;
        }
         .xmg-myfav-nav .xmg-common .xmg-btn img{
        	display:inline-block;
         width:0.8rem;
         height:0.8rem;
             margin-bottom: -0.1rem;
        }
        .xmg-myfav-nav .xmg-common input{
        	float:left;
        	margin:0.4rem 0.625rem  0 0;
        }

        .xmg-myf-tc{
        	display: none;
        }
         .xmg-myf-tc .xmg-hsbg{
             position:fixed;
             top:0;
             left:0;
             width:100%;
             height:100%;
             background:#000;
             opacity:0.5;
             z-index: 998;
        }
         .xmg-myf-tc .xmg-box{
             position:fixed;
             top:50%;
             left:50%;
             width:13.625rem;
             height:7.8125rem;
             background:#fff;
             z-index: 999;
             margin:-3.90625rem 0 0 -6.8125rem;
             border-radius:0.3125rem;
             overflow:hidden;
        }
         .xmg-myf-tc .xmg-box2{
             width:90%;
             height:84%;
             margin:5%;
             box-sizing:border-box;
             border:1px solid #dcdcdc;
             border-radius:0.3125rem;
        }
         .xmg-myf-tc .xmg-box h1{
         	width:100%;
         	    display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
             font-size:0.75rem;
             height: 3.4375rem;
             /*line-height: 3.4375rem;*/
             color:#666;
             font-weight:bold;
             text-align: center;
             margin-top:0.5625rem;
        }
         .xmg-myf-tc .xmg-box .xmg-bottom{
             height:1.40625rem;
             line-height: 1.40625rem;
             text-align: center;
        }
         .xmg-myf-tc .xmg-box .xmg-bottom a{
             display:inline-block;
             width:2.0625rem;
             height:1.40625rem;
             line-height: 1.40625rem;
             border-radius:0.15625rem;
             text-align: center;
             color:#fff;
             font-size:0.75rem;
             font-weight: bold;
             margin: 0 0.25rem;
             background:#a4a4a4;
        }
         .xmg-myf-tc .xmg-box .xmg-bottom a.ok{
         	background:#000;
         }

    </style>
</head>

<body>
        <%@ include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <script type="text/javascript">
                $(function() {
                    user_obj.user_fav()
                });
            </script>
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
                    <a href="/home/usr_UsrFavorites_myRecycle">
                        <!-- Recycle bin -->
                        <s:text name="Recycle_Bin" />
                    </a>
                </div>





                <div class="user_favorite">
                    <div class="detail_prolist"></div>
                </div>
                <div class="blank15">
                </div>
            </div>
        </div>



        <div class="xmg-myfav-nav">
        	<div class="xmg-common fr">
        		<input type="checkbox" name="ckAll"  value="全选/反选" onclick="checkAll()"  id="all"/>
        		<div class="xmg-btn xmg-btn-clear"><!-- Clear --><s:text name="clear" /></div>
        	</div>
        </div>



        <footer>
            <div id="prolist_mask_footer">
            </div>
            <div class="footer_top clean">
            </div>
            <ul class="footer_list ui_border_b clean" style="display:none;">
                <li class="fl" style="border-right:1px solid #ddd;">
                    <a href="https://www.shoestp.com/account/" class="clean">
                        <span class="list_left">
                           <!--  Sign In --><s:text name="sign_in" />

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
<!--弹出1-->
<div class="xmg-myf-tc xmg-myf-tc111">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <div class="xmg-box2">
            <h1><!-- The deleted items can --><s:text name="deleted" /><br/><!-- not be recovered！ --><s:text name="recovered" /></h1>
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
            <h1><!-- Are you sure clear the items in --><s:text name="disappearif" /><br/></h1>
            <div class="xmg-bottom">
                <a href="javascript:;" class="ok" style="width:4rem"><s:text name="Global.Yes" /></a>
                <a href="javascript:;" class="no" style="width:4rem"><s:text name="Global.No" /></a>
            </div>
        </div>
    </div>
</div>

<!--弹出3-->
<div class="xmg-myf-tc xmg-myf-tc333">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <div class="xmg-box2">
            <h1><!-- The goods have been restored --><s:text name="beenrestored" /><br/><!-- to the favorites！ --><s:text name="thefavorites" /></h1>
            <div class="xmg-bottom">
                <a href="javascript:;" class="ok"><s:text name="yes" /></a>
                <a href="javascript:;" class="no"><s:text name="no" /></a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var list = function () {
        var ckAll = document.getElementById("all");
        ckAll.checked = false;

        $.ajax({
            url: '/home/usr_UsrFavorites_myRecycleAjax',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var div = '';
                $.each(data.result, function (i, val) {
                    div += '<div class="item2 clean ui_border_b">' +
                        '<input type="checkbox" name="ckOne" data="' + val.id + '"  onclick="checkOne()"/>' +
                        ' <div class="img">' +
                        '<a href="">' +
                        '<img src="${envConfig.imageBaseUrl}' + val.img + '" alt="' + val.name + '">' +
                        '</a>' +
                        '</div>' +
                        '<div class="info fav_info clean">' +
                        '<div class="name">' +
                        '<a href="">' +
                        val.name +
                        '</a>' +
                        '</div>' +
                        '<div class="price">${env.currency.symbols}.' +
                        val.amt +
                        '</div>' +
                        '<div class="xmg-bottom">' +
                        '<div class="del" onclick="deleteThis(' + val.id + ')"><s:text name="user.delete" /></div>' +
                        '<div class="restore" onclick="restore(' + val.id + ')"><s:text name="Restore" /></div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                })
                $(".detail_prolist").html(div);
            }
        })
    }
    window.onload = list()

    function restore(id) {
        $.ajax({
            url: '/home/usr_UsrFavorites_restoreFavorite',
            type: 'post',
            data: {"pkey": id},
            dataType: 'json',
            success: function (data) {
                if (data.ret ===1) {
                    list()
                } else {
                    console.log(data.msg);
                }
            }
        })
    }

    function deleteThis(id) {
        layer.open({
         content: '<s:text name="del_ask" />'
         ,btn: ['<s:text name="Global.Yes" />', '<s:text name="Global.No" />']
         ,yes: function(index){
           $.ajax({
               url: '/home/usr_UsrFavorites_delFavorite',
               type: 'post',
               data: {"pkey": id},
               dataType: 'json',
               success: function (data) {
                   if (data.ret == true) {
                       list()
                       $('html').tips_box('Delete Success!', 'success');
                     setTimeout(function () {location.reload();},2000);
                   } else {
                       $('html').tips_box('Delete Fail!', 'error');
                   }
               }
           })
           layer.close(index);
         }
       });
    }


    function chooseChecked() {
        var ckOnes = document.getElementsByName("ckOne");
        var pkeys = '';
        for (var i = 0; i < ckOnes.length; i++) {
            if (ckOnes[i].checked == true) {
                if (pkeys == '') {
                    pkeys += $(ckOnes[i]).attr("data");
                } else {
                    pkeys += "," + $(ckOnes[i]).attr("data");

                }
            }
        }
        return pkeys;
    }

    $(".ok").on("click", function () {
        var pkeys = chooseChecked();
        $.ajax({
            url: '/home/usr_UsrFavorites_delFavorite',
            type: 'post',
            data: {"pkeys": pkeys},
            dataType: 'json',
            success: function (data) {
                console.log(data)
                if (data.ret == 1) {
                    location.reload();
                    $('.xmg-myf-tc111').hide();
                } else {
                    layer.open({
                      content: data.msg
                      ,skin: 'msg'
                      ,time: 2
                    });
                    $('.xmg-myf-tc111').hide();
                }
            }
        })
    })

    function checkAll() {   //全选 和反选  以及 单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        for (var i = 0; i < ckOnes.length; i++) {
            ckOnes[i].checked = all.checked;//把全选框的状态循环赋给所有的单选框！！！！！！
        }
    }

    var checkElement = '';

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


    $('.detail_prolist .item2 .xmg-bottom .del').click(function () {
        $('.xmg-myf-tc111').show();
    })
    $('.xmg-myf-tc111 .xmg-box .xmg-bottom a.no').click(function () {
        $('.xmg-myf-tc111').hide();
    })

    $('.xmg-myfav-nav .xmg-common .xmg-btn-clear').click(function () {
    	 var pkeys = chooseChecked();
    	 console.log(pkeys)
    	 if(pkeys == ""){
    		 layer.open({content:lang_obj.cart.checked_error,skin:'msg'});
    	 }else{
    		 $('.xmg-myf-tc222').show();
    	 }
        
    })
    $('.xmg-myf-tc222 .xmg-box .xmg-bottom a.no').click(function () {
        $('.xmg-myf-tc222').hide();
    })

    $('.detail_prolist .item2 .xmg-bottom .restore').click(function () {
        $('.xmg-myf-tc333').show();
    })
    $('.xmg-myf-tc333 .xmg-box .xmg-bottom a.no').click(function () {
        $('.xmg-myf-tc333').hide();
    })
</script>

</body>
</html>
