<!-- 顶部导航条  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="nav-bar bg-white">
	<ul class="flexible text-center">
		<li class="">
			<a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supplier.pkey}"> HOME </a>
		</li>
		<li class="active">
			<a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supplier.pkey}">	PRODUCTS </a>
		</li>
		<li class="">
        <a href="/home/usr_UsrSupplier_gtSupGroup?pkey=${supplier.pkey}"> JOINT BUY </a>
    </li>
		<li class="">
			<a href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supplier.pkey}"> PROFILE </a>
		</li>
		<li class="">
			<a href="/home/usr_UsrSupplier_gtSupContact?pkey=${supplier.pkey}"> CONTACT </a>
		</li>
	</ul>
</div>
<!-- 顶部导航条 - end -->
