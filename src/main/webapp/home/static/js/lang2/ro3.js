var global {
	updatedby=updated_by:'更新員',
	updatetime=updatedtime=updated_time:'更新時間',
	updatedby=created_by:'建檔員',
	createtime=created_time:'建檔時間',
	0:'停用',
	name:'顯示',
	users_pkey=purchase:'採購商',
	name:'啟用標誌',
	page_type_text=intro=seo_description_en:'簡述',
	lang:'語言標識',
	tag:'一天',
	height:'高',
	width:'寬',
	section:'區間',
	brief:'簡介',
	spec:'產品規格',
	weight_max:'最大重量/體積/件數',
	weight_min:'最小重量/體積/件數',
	weight_price:'首重價格',
	aggravate_price:'加重價格',
	weight_section:'首重區間',
	aggravate_section:'續重區間',
	extra_price:'附加費用',
	free_price:'免運費價格',
	name:'重量計算方式選擇',
	2:'件數',
	1:'體積',
	name:'使用接口',
	company:'快遞公司',
	adphotolink=url:'廣告鏈接',
}
var obj{
		usr_valid:{
				wrongerr:'【{0}】輸入有誤，請重新輸入',
				toobig:'【{0}】輸入的值過大，不能超過100',
				toosmall:'【{0}】輸入的值過小，不能小於0',
				usercopy:'用戶名重複',
				wrongpassword:'密碼輸入有誤，請檢查后重新輸入!',
		},

		
		
		supplier_roleact:{
			 menu:'系統菜單',
		 },

		 
		 
		 supplier:{
			 status:{
				0:'未審核',
				1:'已審核',
			 },
			 apprby:'審核員',
			 apprtime:'審核時間',
			 registeredcapital:'註冊資金',
			 isauth:{
				 0:'未認證',
				 1:'已認證',
				 name:'供應商認證',
			 }
			 seotitleen:'店鋪關鍵字',
			 authtime:'認證時間',
			 mainsalesarea:'主銷售區',
			 businesslicensebegintime:'營業執照開始時間',
			 businesslicenseendtime:'營業執照到期時間',
			 businesslicenseissecular:{
				 name:'是否長期',
			 },
			 certphoto:'資質證書',
			 idcardfrontphoto:'身份證正面',
			 idcardbackphoto:'身份證反面',
			 coopcertphoto:'合作憑證',
			 idcard:'法人身份證號碼',
			 settlementbank:'結算開戶行',
			 bankaccount:'銀行賬號',
			 bankbranch:'銀行開戶行',
			 contactsidcardfrontphoto:'運營負責人身份證正面',
			 contactsidcardbackphoto:'運營負責人身份證反面',
			 businesstyp:'商業模式',
			 developer:'開發商',
			 annualsales:'年銷售額',
			 top3markets:'TOP3市場',
			 materials:'材料',
			 headpic:'頭像',
			 department:'部門',
			 jobtitle:'職位名稱',
			 ispro:{
				 name:'供應商首頁產品展示',
			 },
			 signbackgd:'店招背景',
			 adphoto:'廣告圖',
			 adphotomobile:'移動端廣告圖',
			 companyphoto:'企業圖片',
			 companyphotolink:'企業圖片鏈接',
			 contactpagediy:'聯繫頁個性裝修',
			 homepagediymobile:'首頁個性裝修(移動)',
			 productpagediymobile:'產品頁個性裝修(移動)',
			 contactpagediymobile:'聯繫頁個性裝修(移動)',
			 tracecode:'跟蹤代碼',
			 website:'網址',
			 tongjipwd:'第三方統計密碼',
			 updatetime:'更新時間',
		 },

		 
		 
		 purchase_line:{
			 isdefault:,{
				 name:'默認地址',
			 }
			 addrsstype:{
				 0:'收貨地址',
				 1:'賬單地址',
				 name:"收貨地址類型",
			 },
		 },

		 
		 
		 purchase:{
			 reg_time:'註冊時間',
		 },

		 
		 
		 product_category:{
			 seo_keyword_en:'關鍵字',
		 },

		 
		 
		 messages:{
			 reciver:'收件人',
			 status:{
				 0:'未讀',
				 1:'已讀',
				 name:'閱讀狀態'
			 },
			 type:{
				 0:'用戶消息',
				 name:'消息類別',
			 },
			 send_time:'發送時間',
			 read_time:'閱讀時間',
			 reply_time:'回復時間',
		 },

		 
		 
		 member_level:{
			 discount:'會員折扣',
			 level_up:'升級條件', 
		 },

		 
		 
		 favorites:{
			 show_state:{
				 name:'顯示狀態',
			 },
			 add_time:'加入時間',
		 },

		 
		 
		 consult_relation:{
			 have_new_msg:{
				 name:'新消息',
			 },
		 },

		 
		 
		 consult_message:{
			 send_time:'留言時間',
			 relation:'詢盤關聯',
			 p_2_s:{
				name:'是採購商留言',
			 },
		 },

		 
		 
		 consult:{
			 have_new_msg:{
				name:'有新消息', 
			 },
			 count:'剩餘搶單次數',
			 quantity:'商品數量',
			 
		 },

		 
		 
			 access:{
				 controller:'控制器',
			 },

			 
			 
			 prm_valid:{
				 later:'【{0}】不能晚於【{1}】',
				 early:'【{0}】不能早於當前時間',
			 },

			 
			 
			 group_purchase_line:{
				bought_count:'已訂購數量',
			 },

			 
			 
			 group_purchase:{
				 title:'活動標題',
				 start_time:'開始時間',
				 end_time:'結束時間',
				 status:{
					 0:'未開始',
					 1:'即將開始',
					 2:'進行中',
					 3:'即將結束',
					 4:'已結束',
					 name:'活動狀態',
				 },
				 pre_time:{
					 3:'提前三天',
					 7:'提前7天',
					 15:'提前十五天',
					 30:'提前一個月',
					 name:'提前預告',
				 },
			 },

			 
			 
			 group_order_line:{
				 group_order:'團購訂單',
				 
			 },

			 
			 
			 group_order:{
				 amt_total:'訂購總價',
				 
			 },

			 
			 
			 plt_Valid_Payand_Rate:{
				 compareErr:'【{0}】不能大於或者等於【{1}】！',
				 numErr:'【{0}】不能小於0',
				 strErr:'【{0}】不能有數字',
			 },

			 
			 
			 province:{
				 short_name:'省份縮寫',
			 },

			 
			 
			 pay:{
				 mode:{
					 5:'線下支付',
					 10:'TT支付',
				 },
				 extra_costs:'附加費用',
				 paysetting:'銀行有關支付信息	',
			 },

			 
			 
			 countryfreight:{
				 region:'商家配送區域',
			 },

			 
			 
			 country:{
				 zone:'區號',
			 },

			 
			 
			 config:{
				 variable:'變量名',
				 value:'變量值',
			 },
			
			 
			 
			 
			 
			 
			 

			 
			 
		ad:{
			 ad_position:{
				 name:'展示位置',
				 0:'首頁輪播圖',
				 5:'首頁左側上部',
				 6:'首頁左側下部',
				 10:'首頁中間',
				 15:'產品分類廣告',
				 20:'供應商頭部',
			 },
			 display_type:{
				0:'漸顯',
				1:'上滾動',
				2:'下滾動',
			 },
			 enabled:{
				 name:'啟用標識',
			 },
		 },

		 
		 
		 ad_line:{
			 main:'廣告管理',
			 name:'廣告名稱',
			 brief:'廣告簡介',
			 image:'廣告圖片',
			 
			 sort:'排序',
			 main_img:{
				 name:'是否主圖',
			 },
		 },

		 
		 
		 article:{
			 rewrite_url:'自定義路徑',
			 detail:'詳細描述',
		 },

		 
		 
		 article_category:{
			 rootid:'文章類型',
			 
		 },

		 
		 
		 magazine:{
			 cycle:'週期',
			 content:'內容',
		 },

		 
		 
		 validate:{
			 langerror:'【{0}】不符合要求不可操作！{1}',
			 notempty:'【{0}】字段不能為空',
			 msg:'【{0}】字段不符合要求{1}',
			 numbererr:'【{0}】必須為正整數',
			 uniqueerr:'記錄【{0}】已存在，不可操作！',
		 },

		 
		 
		 history:{
			 operator:'操作人',
		 },

		 
		 
		 order:{
			 express_num:'運單號',
			 delivery:'發貨方式',
			 pag_remarks:'發貨備註',
			 odr_remarks:'訂單備註',
			 type:{
				 0:'普通訂單',
				 1:'聯合採購訂單',
			 },
			 odr_cancel:{
				 0:'缺貨',
				 1:'不是有效訂單',
				 2:'買家要求',
				 3:'其他原因',
			 },
			 pay_type:'支付設置',
		 },
		 
		 
		 
		 attr_line:{
			 main:'產品屬性',
		 },
		 
		 
		 
		 cat:{
			 category_up:'隸屬分類', 
		 },
		 
		 
		 
		 comment:{
			 product_satisfaction:{
				 name:'滿意度',
			 },
			 othoer_satisfaction:'綜合評價',
			 useful_number:'讚同數',
			 unuseful_number:'反對數',
		 },
		 
		 
		 
		 product:{
			 is_verify:'產品審核',
			 verify_by:'審核人員',
			 cur_price:'商城價格',
			 max_oq:'最大購買量',
			 sales:'產品銷量',
			 warn_stock:'警告庫存',
			 norm_attr:'商品屬性',
			 size_attr:'規格屬性',
			 color_attr:'顏色屬性',
			 stock_out:{
				 name:'脫銷狀態',
				 0:'脫銷',
			 },
			 state:{
				 name:'銷售狀態',
				 0:'上架',
				 1:'下架',
			 },
			 sold_in_time:'定時上架',
			 sold_time_b:'上架時間(開始)',
			 sold_time_e:'上架時間(結束)',
			 is_default_review:'默認評論',
			 favorite_count:'收藏數',
			 source_product:'原始產品',
			 product_type:{
				 name:'滿意度',
				 1:'普通產品',
				 2:'聯合採購產品',
			 },
			 seo_keyword_en:'關鍵詞',
			 length:'長',
			 tabname_1:'選擇卡 1 標題',
			 tab_1:'選擇卡 1 內容',
			 tabname_2:'選擇卡 2 標題',
			 tab_2:'選擇卡 2 內容',
			 tabname_3:'選擇卡 3 標題',
			 tab_3:'選擇卡 3 內容',
			 update_time:'更新時間',
		 },
		 
		 
		 
			spec:{
				size:'產品尺寸',
				sku:'組合屬性',
				price:'出場碼',
				markup:'加價',
				store_count:'庫存數量',
			},
}