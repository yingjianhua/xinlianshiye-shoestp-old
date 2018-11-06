/*
Powered shoestp
*/

var lang_obj={
		
		Name_cannot:"姓名不能為空",
		The_amount_cannot:"金額不能為空",
	orders:{
		'status':{
			0: "待付款",
			1:"等待确认付款",
			2:"付款错误",
			3:"等待发货",
			4:"已发货",
			5:"完成订单",
			6:"已取消订单",
			7:"已删除",
			'choose_payment':'選擇付款方式',
			'order_total':'訂單總數'
		}
	},
	page : {
		prev: "上一頁",
		next: "下一頁"
	},
	
	
	
	
	
	
	global:{
		/*询盘*/
		'already':'本產品已存在詢價袋',
		'inqadd':'成功添加到您的詢盤！',
		'continues':'繼續旅遊',
		'inquery':'現在詢價',
		'vcode':'驗證碼',
		/*询盘结束*/
		'add':'添加',
		'edit':'編輯',
		'sure':'確定',
		'submit':'提交',
		'confirm':'確認',
		'cancel':'取消',
		'copy_confirm':'進入產品複製模式，繼續嗎？',
		'copy_model_confirm':'進入内容複製模式，繼續嗎？',
		'del_confirm':'删除後無法恢復，繼續嗎？',
		'reset_confirm':'確認恢復默認設置，繼續嗎？',
		'used_confirm':'準備打開批次處理模式，繼續嗎？',
		'close_confirm':'準備關閉批次處理模式，繼續嗎？',
		'my_order_confirm':'準備打開排序模式，繼續嗎？',
		'sold_in_confirm':'準備打開銷售模式，繼續嗎？',
		'sold_out_confirm':'準備關閉銷售模式，繼續嗎？',
		'data_posting':'提交的數據…',
		'loading':'加載。。。',
		'sync':'數據正在同步…',
		'confirm_password_error':'新密碼與確認密碼不匹配，請重新輸入！',
		'del_dat_select':'請選擇要删除的產品',
		'used_dat_select':'請選擇要啟動的產品',
		'close_dat_select':'請選擇要關閉的產品',
		'dat_select':'請選擇所選產品',
		'correct_message':'請填寫正確的數據。',
		'n_y':['不','是'],
		'open':'展開',
		'pack_up':'折疊',
		'del':'删除',
		'picture':'圖片',
		'picture_name':'圖片名稱',
		'set_error':'安裝失敗未知錯誤！',
		'vcode':'驗證碼',
		'unknown_mistake':'未知的錯誤'
	},
	format:{
		'mobilephone':'請正確填寫電話號碼！',
		'telephone':'請填寫電話號碼！',
		'fax':'請正確填寫傳真號碼！',
		'email':'請正確填寫電子郵箱地址！',
		'length':'長度不正確！您需要填寫%num%數位。'
	},
	signIn:{
		'title':'登錄',
		'error_note':'不正確的電子郵箱地址或密碼。請再試一次。<BR>請在輸入密碼前確認CAP鎖已關閉。',
		'email':'電子郵箱',
		'password':'密碼',
		'forgot':'忘記了你的<a href="/home/usr_UsrPurchase_sendEmail" class="forgot">密碼</a>？',
		'stay_note':'保持登錄<span>保護您的隱私 - 在您完成後退出。</ span>',
		'sign_in':'登錄',
		'join_fee':'註冊',
		'verification':'驗證碼錯誤',
		'original_password_wrong':'原密碼錯誤',
		'Register_Success':'註冊成功！',
		'Username_Exists':'此用戶名已存在',
		'Old_Password_Empty':'原密碼不能為空！',
		'New_Password_Empty':'新密碼不能為空！',
		'Confirm_Password_Empty':'確認密碼不能為空！',
	},
	newsletter:{
		'success':'添加訂閱成功！',
		'exists':'此郵箱已存在訂閱！'
	},
	language:{
		'zh-cn':'中文',
		'en':'英語',
		'jp':'日語',
		'de':'德語',
		'fr':'法語',
		'es':'西班牙語',
		'ru':'俄語',
		'pt':'葡萄牙語',
		'zh_tw':'繁體中文'
	},
	manage:{
		frame:{
			'time':'時間',
			'status':'狀態',
			'support':['企業QQ', '工作時間', '電話號碼', '微信號', '上級申訴人', '網站過期時間'],
			'data_backup':'資料備份',
			'backup':['資料庫', '資料夾'],
			'file_upload':'選擇文件'
		},
		account:{
			"log_in":"系統正在嘗試登錄，請稍候…",
			"log_in_ok":"成功登入，頁面跳轉，請稍候…",
			"order_quantity":"網站訂購",
			"page_view":"現場訪問",
			"success_cache":"緩存已成功清除！",
			"percentage":"百分比",
			"picture_tips":"你已經上傳了超過XXX的圖片，不能上傳了！",
			"password_tips":"重新密碼不匹配，請重新輸入！"
		},
		set:{
			"select_once_language":"請選擇至少一種語言版本",
			"print_0":"列印報關單",
			"print_1":"列印發票",
			"platform_ary":{'SignIn':'登錄','Pixel':'統計'},
		},
		module:{
			"sure_module":"你一定要選擇這種款式嗎？",
			"name":"名字",
			"url":"鏈接地址",
			"target":"新窗口",
		},
		counrtry:{
			"state":"省",
		},
		photo:{
			"empty_temp":"臨時資料夾已成功清除！",
			"move_success":"成功移除",
			"picture_upload":"圖片上傳"
		},
		shipping:{
			"area_config":"區域配寘",
		},
		products:{
			"category_tips":"請選擇產品類別",
			"qty":"數量",
			"price":"價格",
			"mark_up":"價格上漲",
			"discount":"優惠",
			"group_attr":"組合性質",
			"wholesale_discount_notes":"當前網站選擇的產品内容的價格是單價，批發價格的計算將以折扣管道計算。",
			"prefix_tips":"產品編號首碼不能為空！",
			"sync_change_account":"轉換帳戶…"
		},
		sales:{
			"seckill":"秒殺",
			"promotion":"促銷",
			"proName":"產品名",
			"proPrice":"產品價格",
			"proNumber":"產品編號",
			"proBiref":"簡介",
			"start_time":"啟動時間",
			"duration":"持續時間",
			"hour":"小時",
			"tuan_price":"團購價格",
			"buyer_count":"購買",
			"total_count":"限制",
			"promotion_price":"總促銷價",
			"seckill_price":"價格飆升",
			"qty":"數量",
			"remainder_qty":"殘留量",
			"max_qty":"購買上限",
			"reverseAssociate":"逆相關（當沒有組合時，該組合將同時顯示給相關產品）",
			"mainArea":"產品已經存在於主要產品領域",
			"relatedArea":"產品已經存在於捆綁產品區域",
			"holidayNotice":'將右側的產品清單拖動到<b class="fc_red">"假日範本產品區域"</b>',
			"seckillNotice":'將右側的產品清單拖動到<b class="fc_red">"隔離產品區"</b>',
			"tuanNotice":'將右側的產品清單拖動到<b class="fc_red">"團購產品區域"</b>',
			"relatedNotice":'將右側的產品清單拖動到<b class="fc_red">"主要產品區域"</b>和<b class="fc_red">"捆綁產品區域"</b>。',
			"beyondNumber":"不得超過最高限量編號：",
			"condition":"消費狀況",
			"discount":"優惠",
			"money":"錢",
			"condition_tips":"此優惠券只能在購物金額達到設定值時使用；如果設定0，則對使用條件沒有限制。",
			"discount_tips":"請輸入折扣百分比；20%相當於80%折扣，80%相當於20%折扣。",
			"money_tips":"請在8到15位數之間輸入優惠券代碼。",
			"check_choose":"未選中商品"
		},
		email:{
			"load_error":"模板數據讀取失敗，請稍後再試！",
			"not_model":"沒有選擇模板！",
			"not_user":"沒有會員！",
			"not_content":"沒有內容！"
		},
		mta:{
			"traffic_time":['最近30天', '今天', '昨天', '最近7天', '最近15天'],
			"statistics_name":['遊客', '瀏覽量']
		}
	},
	products:{
		'warning_number':'購買少於或超過庫存MOQ',
		'warning_MOQ':'最小購買數量%num %',
		'warning_Max':'最大購買數量%num %',
		'warning_stock':'高於庫存%num %',
		'warning_attribute':'請選擇參數',
		'select_country':'--請選擇你的國家——',
		'sign_in':'請先登錄！',
		'free_shipping':'免費送貨',
		'no_optional':'不可選',
		'no_up_carriage':'{0}未上架',
		'product_deleted':'產品{0}已被刪除'
	},
	cart:{
		'processing':'處理，請稍候…',
		'processing_str':'處理',
		'checked_error':'請選擇至少一個產品',
		'attribute_error':'在產品資訊中有錯誤，請删除此產品，然後重新購買。',
		'address_error':'请输入您的装运地址或从您先前输入的地址中选择一个。确保信息，请单击完成后保存。',
		'shipping_error':'請選擇遞送方法。',
		'payment_error':'請選擇付款方式。',
		'product_error':'你的購物車是空的。',
		'low_error':'你的總產品還沒有達到網站的最低消費價值',
		'stock_error':'{0}庫存不足，不能正確創建訂單，調整採購數量。',
		'prod_stock_error':'{0}庫存不足。',
		'no_delivery':'對不起的！無法投遞到這個國家！',
		'additem_0':'該產品成功地添加到购物车！',
		'additem_1':'<b class="FontColor">%num%</b>購物車中的物品。小計：',
		'return_shopping':'继续逛逛',
		'proceed_checkout':'進行結算',
		'arrival_info_0':'已順利提交業務，產品覆蓋將立即通知客戶。',
		'arrival_info_1':'請登入申請到達通知。',
		'arrival_info_2':'本產品已申請到達通知！',
		'use_shipping_address':'使用此送貨地址',
		'coupon_title':'輸入您的優惠券代碼',
		'coupon_tips':'優惠券代碼"<strong>%coupon%</strong>"是有效的！你方的折扣是',
		'coupon_code':'優惠碼',
		'apply':'應用',
		'select_y_country':'選擇你的國家',
		'remove':'刪除',
		'batch_remove_select':'請選擇要删除的項目！',
		'batch_remove_success':'產品删除成功！',
		'batch_remove_error':'產品删除失敗！',
		'continue_str':'繼續結帳',
		'total_amout':'總量',
		'insurance':'向您的訂單新增運輸保險',
		'coupon_tips_to':'優惠券代碼 %coupon% 無效。它要麼不存在，要麼尚未被激活，要麼已經過期。',
		'coupon_tips_th':'優惠券代碼<strong></strong> 無效。它要麼不存在，要麼尚未被激活，要麼已經過期。',
		'coupon_price_tips':"產品的總金額低於優惠券的消費條件，系統自動取消現在使用的優惠券。",
		'shipping_method_tips':'請選擇一個裝運方法！',
		'cart_tips':'1項加到車上',
		'plz_sel_para':'請選擇參數',
		'del_confirm':'你確定要删除它嗎？',
		"paypal_tips":"請選擇與PayPal帳戶地址資訊相同的國家。",
		'tooLess':'{0}的數量太少，需要大於{1}',
		'Delete_Success':'已成功刪除',
		'Are_You_Sure':'您確定要刪除所選項嗎？',
		'FailToRemove':'無法刪除',
		'NotOne':'不能小於1',
		'ColorAndSize':'請選擇顏色與尺寸',
	},
	seckill:{
		'loading_tips':'沒有更多的產品',
		'no_products':'不匹配任何產品',
		'end':'完成了',
		'activity_belongs_begin':'{0}屬於即將開始的活動',
		'activity_belongs_end':'{0}所屬活動已結束'
	},
	user:{
		'send_email_ture':'一封電子郵件已經成功發送給您。請輸入您的電子郵箱地址并查看驗證電子郵箱',
		'send_email_false':'對不起，由於系統問題，郵件發送失敗。請稍後再試。',
		'favorite_success':'成功添加到我的收藏！',
		'favorite_saved':'本產品深受好評！',
		'go_to_view':'去查看',
		'order_cancel':'確實要删除此掛起的訂單嗎？',
		'sure':'你確定嗎？',
		'delete_shipping':'你確定要删除這個地址嗎？',
		'reg_error':{
			'PleaseEnter':'請輸入您的%field%.',
			'Email':'請輸入電子郵寄地址。',
			'EmailFormat':"您輸入的電子郵件無效。請檢查您的電子郵件，然後再試一次。",
			'PWDConfirm':'請重新輸入新密碼。',
			'PWDNotMatch':'您的密碼不匹配，請再試一次。',
			'PWDLENGTH':'密碼為8-15位數字和字母'
		},
		'address_tips':{
			'PleaseEnter':'請輸入您的%field%.',
			'email':'請輸入電子郵寄地址。',
			'email_format':'輸入的電子郵件與確認的電子郵件值不匹配。',
			'firstname':'請輸入您的名字。',
			'firstname_length':'你的名字必須包含至少2個字符。',
			'lastname':'請輸入您的姓氏。',
			'lastname_length':'你的名字必須包含至少2個字符。',
			'address':'請輸入發貨地址。',
			'address_length':'您的寄件人地址應該至少有5個字符長。',
			'city':'請進入您的都市。',
			'city_length':'你的城市應該至少有3個字符長。',
			'country':'請選擇您的目的地國家/地區。',
			'state':'請選擇你的州/省/地區。',
			'taxcode':'對不起，您的 %str% 是必需的。',
			'taxcode_length':'您的 %str% 必須包含至少%TaskL%的數位。',
			'tariff':'對不起，您的 %str% 數值是必需的。',
			'tariff_length':'您的 %str% 數必須包含至少12個數位。',
			'zip':'請輸入郵編/郵遞區號。',
			'zip_length':'您的郵編/郵政編碼至少應長4位',
			'phone':'請輸入您的電話號碼。',
			'phone_format':'請輸入有效的電話號碼。',
			'phone_length':'你的電話號碼必須至少有7個字符。',
			'Please_add_item':'請添加商品',
			'Input_AMT':'請輸入付款金額',
		}
	},
	
	'goods_info':{
		'Pleaselogin':'請登錄',
		'Successfulinclusion':'收錄成功',
		'product_out':'該商品已售罄',
		'Please_purchased':'請輸入購買數量',
		'Added_successfully':'添加成功',
		'Purchase_success':'購買成功',
		'No_Spec':'該產品尚未發布規格。',
		'Successful_Payment':'成功付款！',
	},
	
	'my_inquiry_publish':{
		'submitsuccess':'提交成功',
		'completeinformation':'請填寫完整的信息',
		'thecompatibletitle':'標題太長，請填寫兼容的標題',
		'thecorrectEmail':'請填寫正確的電子郵件',
		'thecorrectquantity':'請填寫正確的數量',
		'goodsListerr':'得到貨物清單錯誤',
		'deletethisgoods':'你確定要刪除這些商品嗎?',
		'Pleaseselect_a_goods':'請選擇商品或上傳一些圖片讓供應商知道你想要什麼',
		'I18N_No':'I18N 暫無'
	},
	'addressfrom':{
		'Inserted_successfully':'添加成功',
		'Successfully_modified':'成功修改',
		'Enter_Query':'進入查詢',
		'Merchants_Settled':'商家入駐',
		'Merchant_Side':'商家端',
		'Please_Select_The_Shipping_Address':'請選擇送貨地址',
		'Please_Select_The_Billing_Address':'請先設置帳單郵寄地址。',
		'Submit_Order_Successfully':'提交訂單成功',
		'No_Province':'省級記錄不存在',
		'Has_Residence':'您已申請入駐，不能重複申請！',
	},
	'Purchase_Center_Store':{
		'GuangZhou':'廣州採購點',
		'Spain':'西班牙採購點',
		'Chile':'智利採購點',
		'Romania':'羅馬尼亞採購點',
		'Hungary':'匈牙利採購點',
		'America':'美國採購點',
		'Canada':'加拿大採購點',
		'Russia':'俄羅斯採購點',
		'Belarus':'白俄羅斯採購點',
		'Ukraine':'烏克蘭採購點',
		'Poland':'波蘭採購點',
		'Germany':'德國採購點',
		'Czekh':'捷克採購點',
		'England':'英國採購點',
		'Ireland':'愛爾蘭採購點',
		'Holland':'荷蘭採購點',
		'France':'法國採購點',
		'Finland':'芬蘭採購點',
		'Sweden':'瑞典採購點',
		'Norway':'挪威採購點',
		'Denmark':'丹麥採購點',
		'Korea':'韓國採購點',
		'Japan':'日本採購點',
		'Philippines':'菲律賓採購點',
		'Singapore':'新加坡採購點',
		'Israel':'以色列採購點',
		'Turkey':'土耳其採購點',
		'Australia':'澳大利亞採購點',
		'New_Zealand':'新西蘭採購點',
		'South_Africa':'南非採購點',
	},
	supplier_entry_hint:{
		companyname:"公司名稱不能為空!",
		suppliercategory:"供應商分類不能為空!",
		companyaddress:"公司所在地不能為空!",
		city:"城市不能為空!",
		companyaddressinfo:"公司詳細地址不能為空!",
		email:"電子郵箱不能為空!",
		noemail:"非法電子郵箱格式!",
		fund:"註冊資金不能為空!",
		code:"社會信用代碼不能為空!",
		legalperson:"法人姓名不能為空!",
		businesslicense:"營業執照有效期不能為空!",
		businesslicenseformat:"營業執照有效期格式不正確,正確格式為：2010/01/01",
		entity:"聯繫人不能為空!",
		phone:"手機號碼不能為空!",
		accountopeningbank:"結算開戶行不能為空!",
		bankaccount:"銀行賬號不能為空!",
		openingbankname:"開戶銀行支行名稱不能為空!",
		openingbankaddress:"開戶銀行支行所在地不能為空!",
		Legalperson_idnumber_passportnumber:"法人身份證/護照號碼不能為空!",
		Operationdirector_idnumber_passportnumber:"運營負責人身份證/護照號碼不能為空!",
		Enterprisecertificate:"需要上傳企業憑證!",
		legalperson_photo_front:"需要上傳法人身份證/護照正面!",
		legalperson_photo_reverseside:"需要上傳法人身份證/護照反面照!",
		Operationdirecto_photo_front:"需要上傳運營負責人身份證/護照正面!",
		Operationdirecto_reverseside:"需要上傳運營負責人身份證/護照反面!"
	},
	mobile:{
		Size:'尺寸:',
		Color:'顏色:',
		Order_Number:'訂單號.',
		Double:'雙',
		Total_Price:'總價:',
		Shop:'商店:',
		No_Data:'沒有數據',
		Collection:'收藏',
		Add_To_Shopping_Cart:'添加到購物車',
		Whole:'全部',
		Collection1:'收藏成功',
		Collection2:'取消收藏',
		Collection3:'收藏失敗',
		Go_To_Set:'去設置',
		Set_Later:'稍後設置',
		Tips:'提示',
		Out_Of_Stock:'缺貨',
		Inventory_Shortage:'庫存不足，請重新填寫',
		
		Confirm_receipt:"確認收貨嗎？",
		Successful_receipt:"收貨成功",
		First_page:"已是第一頁",
		The_last_page:"已是最後一頁",
		Acquisition_failed:"獲取失敗",
		Failed_to_send:"發送失敗",
		Successful_recovery:"恢復成功",
		all_reviews:"所有評論",
		
	},
	cart3:{
		name:'姓名',
		Contents:'備註',
		SentMoney:'匯款',
		Number:'參考編號',
		Currency:'貨幣',
	},
	home:{
		brneficiary:'受益人',
		bankaddress:'銀行地址',
		swiftcode:'SWIFT代碼',
		beneficiarybank:'受益銀行',
		beneficiaryaccount:'受益人帳戶',
		depos_it_bank_subbranch_ascription:'結算開戶人',
		account_name:'銀行賬戶',
		depos_it_bank_subbranch:'開戶銀行支行名稱',
		bank_account:'開戶銀行支行所在地',
		
		
		
		
	},
	supplier:{
		products:'產品',
		productsStyle:'加工方式'
	}
};