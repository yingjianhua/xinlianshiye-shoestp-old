/*
Powered shoestp
*/

var lang_obj={
		
		Name_cannot:'名前は空ではありません',
		The_amount_cannot:'金額は空ではありません',
	orders:{
		'status':{
			'0':'待つ',
			'1':'裁量',
			'2':'エラー',
			'3':'ウェイトディリバー',
			'4':'配達',
			'5':'コンプリート',
			'6':'キャンセル',
			'7':'削除された',
			'choose_payment':'お支払い方法を選択',
			'order_total':'注文合計'
		}
	},
	page:{
		prev:"前の",
		next:"次へ"
	},
	
	
	
	/*
	 * Powered shoestp
	 */
		global:{
			/* 询盘 */
			'already':'この商品は既にお問い合わせバッグです！',
			'inqadd':'お問い合わせに成功しました！',
			'continues':'散歩を続ける',
			'inquery':'今すぐお問い合わせ',
			'vcode':'検証コード',
			/* 询盘结束 */
			'add':'追加',
			'edit':'編集',
			'sure':'確か',
			'submit':'提出する',
			'confirm':'確認',
			'cancel':'キャンセル',
			'copy_confirm':'製品複製モードを入力して、続行しますか？',
			'copy_model_confirm':'属性複製モードに入り、続行しますか？',
			'del_confirm':'削除後に回復できない、続行しますか？',
			'reset_confirm':'デフォルト設定を復元することを確認して、続行しますか？',
			'used_confirm':'バッチモードをオンにする準備ができていますか？',
			'close_confirm':'バッチモードをオフにする準備ができていますか？',
			'my_order_confirm':'ソートモードをオンにする準備ができました、続行しますか？',
			'sold_in_confirm':'販売モードをオンにする準備ができました、続行しますか？',
			'sold_out_confirm':'オフセールスモデルをオンにする準備はできていますか？',
			'data_posting':'データが送信されています...',
			'loading':'読み込んでいます...',
			'sync':'同期中のデータ...',
			'confirm_password_error':'新しいパスワードが確認パスワードと一致しません。再入力してください！',
			'del_dat_select':'削除するアイテムを選択してください',
			'used_dat_select':'開始する項目を選択してください',
			'close_dat_select':'閉じるアイテムを選択してください',
			'dat_select':'選択した項目を選択してください',
			'correct_message':'正しいデータを入力してください',
			'n_y':['いいえ','はい'],
			'open':'展開する',
			'pack_up':'Fold',
			'del':'折りたたむ',
			'picture':'画像',
			'picture_name':'ピクチャ名',
			'set_error':'セットアップで不明なミスが失敗しました！',
			'vcode':'確認コード',
			'unknown_mistake':'未知の間違い'
		},
		format:{
			'mobilephone':'電話番号を正しく入力してください！',
			'telephone':'電話番号を記入してください！',
			'fax':'正しくファックス番号を記入してください！',
			'email':'メールアドレスを正しく記入してください！',
			'length':'長さが正しくありません！ %num% 桁を入力する必要があります。'
		},
		signIn:{
			'title':'ログイン',
			'error_note':'メールアドレスまたはパスワードが正しくありません。 もう一度お試しください。<br>パスワードを入力する前にCaps Lockがオフになっていることを確認してください。',
			'email':'Eメール',
			'password':'パスワード',
			'forgot':'あなたの<a href="/home/usr_UsrPurchase_sendEmail" class="forgot">パスワード</a>を忘れましたか？',
			'stay_note':'あなたのプライバシーを守るために<span>ログインしてください。終了したら終了してください。 </ span>',
			'sign_in':'ログイン',
			'join_fee':'無料で参加',
			'verification':'確認コードエラー',
			'original_password_wrong':'元のパスワードが間違っています',
			'Register_Success':'成功した登録！',
			'Username_Exists':'ユーザー名は既に存在します',
			'Old_Password_Empty':'元のパスワードは空にできません！',
			'New_Password_Empty':'新しいパスワードは空にできません！',
			'Confirm_Password_Empty':'パスワードを空白にすることはできません！',
		},
		newsletter:{
			'success':'成功した購読に追加されました！',
			'exists':'このメールボックスは既に登録済みです！'
		},
		language:{
			'zh-cn':'中国語',
			'en':'英語',
			'jp':'日本語',
			'de':'ドイツ語',
			'fr':'フランス語',
			'es':'スペイン語',
			'ru':'ロシア語',
			'pt':'ポルトガル語',
			'zh_tw':'繁体字中国語',
            'hu':'フン'
		},
		manage:{
			frame:{
				'time':'時間',
				'status':'ステータス',
				'support':['企業QQ', '営業時間', '電話', 'チャット', '上級訴状', 'ウェブサイト有効期限'],
				'data_backup':'データバックアップ',
				'backup':['データベース', 'フォルダ'],
				'file_upload':'ファイルを選択'
			},
			account:{
				"log_in":"システムはログインしようとしています、お待ちください...",
				"log_in_ok":"ログインに成功しました。ページがジャンプしました。お待ちください...",
				"order_quantity":"ウェブサイト注文",
				"page_view":"サイト訪問",
				"success_cache":"キャッシュは正常にクリアされました！",
				"percentage":"パーセンテージ",
				"picture_tips":"あなたはxxx以上の画像をアップロードしており、もうアップロードできません！",
				"password_tips":"Re-passwordが一致しません。再入力してください！"
			},
			set:{
				"select_once_language":"少なくとも1つの言語バージョンを選択してください",
				"print_0":"税関申告を印刷する",
				"print_1":"請求書印刷",
				"platform_ary":{'サインイン':'ログイン','ピクセル':'統計'},
			},
			module:{
				"sure_module":"このスタイルを選択してもよろしいですか？",
				"name":"名",
				"url":"リンク先アドレス",
				"target":"新しいウィンドウ",
			},
			counrtry:{
				"state":"州",
			},
			photo:{
				"empty_temp":"一時フォルダが正常に消去されました！",
				"move_success":"正常に削除されました",
				"picture_upload":"画像アップロード"
			},
			shipping:{
				"area_config":"エリア設定",
			},
			products:{
				"category_tips":"商品カテゴリを選択してください",
				"qty":"クォンティティー",
				"price":"価格",
				"mark_up":"価格が上昇した",
				"discount":"割引",
				"group_attr":"組み合わせプロパティ",
				"wholesale_discount_notes":"現在の駅で選択された商品属性の価格は単価であり、卸売価格の計算は割引方法で計算されます",
				"prefix_tips":"製品番号の接頭辞は空ではありません！",
				"sync_change_account":"アカウントの切り替え中..."
			},
			sales:{
				"seckill":"Seckilling",
				"promotion":"プロモーション",
				"proName":"製品名",
				"proPrice":"製品価格",
				"proNumber":"製品番号",
				"proBiref":"簡単な紹介",
				"start_time":"開始時間",
				"duration":"期間",
				"hour":"時間",
				"tuan_price":"グループ購入価格",
				"buyer_count":"購入",
				"total_count":"制限事項",
				"promotion_price":"総プロモーション価格",
				"seckill_price":"価格スパイク",
				"qty":"クォンティティー",
				"remainder_qty":"残量",
				"max_qty":"天井の購入",
				"reverseAssociate":"逆相関（この組み合わせは、組み合わせが設定されていない場合、関連する製品に対して同時に表示されます）",
				"mainArea":"製品は主製品領域に既に存在しています",
				"relatedArea":"製品は既にバンドルされた製品領域に存在します",
				"holidayNotice":'右側の商品リストを<b class = "fc_red"> "休日テンプレート製品領域" </ b>にドラッグすると',
				"seckillNotice":'右側の商品リストを<b class = "fc_red"> "製品のセカイニングエリア" </ b>にドラッグすると',
				"tuanNotice":'右側の商品リストを<b class = "fc_red"> "グループ購入商品領域" </ b>にドラッグすると',
				"relatedNotice":'右側の商品リストを<b class = "fc_red"> "主要商品領域" </ b>および<b class = "fc_red"> "同梱商品領域" </ b>',
				"beyondNumber":"最大制限数を超えています:",
				"condition":"消費条件",
				"discount":"割引",
				"money":"お金",
				"condition_tips":"このクーポンは、ショッピング金額が設定された値に達した場合にのみ使用できます.0を設定すると、使用条件に制限はありません。",
				"discount_tips":"割引率を入力してください:20%は80%オフに相当し、80%は20%オフに相当します",
				"money_tips":"8〜15桁のクーポンコードを入力してください",
				"check_choose":"未チェックの項目"
			},
			email:{
				"load_error":"テンプレートデータの読み込みに失敗しました。しばらくしてからもう一度お試しください！",
				"not_model":"テンプレートが選択されていません！",
				"not_user":"メンバーなし！",
				"not_content":"コンテンツなし！"
			},
			mta:{
				"traffic_time":['過去30日間', '今日', '昨日', '過去7日間', '過去15日間'],
				"statistics_name":['訪問者', 'ページビュー']
			}
		},
		products:{
			'warning_number':'在庫MOQよりも少ない数量を購入する',
			'warning_MOQ':'MOQから低い',
			'warning_Max':'最大購入数 %num%',
			'warning_stock':'在庫 %num% より高い',
			'warning_attribute':'パラメータを選択してください',
			'select_country':' - あなたの国を選択してください - ',
			'sign_in':'まずログインしてください！',
			'free_shipping':'送料無料',
			'no_optional':'オプションなし',
			'no_up_carriage':'{0}棚に載っていない',
			'product_deleted':'製品{0}が削除されました'
		},
		cart:{
			'processing':'処理中です。お待​​ちください...',
			'processing_str':'処理中',
			'checked_error':'少なくとも1つのアイテムを選択してください',
			'attribute_error':'商品情報にエラーがありました。この商品を削除してから購入してください。',
			'address_error':'配送先住所を入力するか、以前に入力した住所から1つを選択してください。情報を確認し、完了後に保存をクリックしてください。 ',
			'shipping_error':'配送方法を選択してください。',
			'payment_error':'お支払い方法を選択してください。',
			'product_error':'あなたのショッピングカートは空です。',
			'low_error':'あなたのトータルプロダクトはまだウェブサイトの最低消費値に達していません',
			'stock_error':'{0}在庫が不十分で、注文を正しく作成できない場合は、購入数量を調整してください。',
			'prod_stock_error':'{0}在庫が不十分です',
			'no_delivery':'申し訳ありません！この国/地域に配送できません！',
			'additem_0':'この商品はカートに追加されました！',
			'additem_1':'<b class = "FontColor">%num%</ b>カート内のアイテム。小計:',
			'return_shopping':'ショッピングを続ける',
			'proceed_checkout':'チェックアウトに進む',
			'arrival_info_0':'ビジ​​ネスに正常に提出され、製品のカバーはすぐに顧客に通知されます。',
			'arrival_info_1':'到着通知を申請するにはログインしてください。',
			'arrival_info_2':'この商品は到着通知に適用されました！',
			'use_shipping_address':'この配送先住所を使用する',
			'coupon_title':'あなたのクーポンコードを入力してください',
			'coupon_tips':'クーポンコード "<strong>%coupon%</ strong>"は有効です！<br />割引は、',
			'coupon_code':'クーポンコード',
			'apply':'適用',
			'select_y_country':'あなたの国を選択',
			'remove':'削除',
			'batch_remove_select':'削除したいアイテムを選択してください！',
			'batch_remove_success':'製品が正常に削除されました！',
			'batch_remove_error':'製品の削除に失敗しました！',
			'continue_str':'チェックアウトを続けます',
			'total_amout':'合計金額',
			'insurance':'あなたの注文に送料保険を追加する',
			'coupon_tips_to':'クーポンコード %coupon% は無効です。それは存在しないか、まだ活動化されていないか、または期限が切れています。',
			'coupon_tips_th':'クーポンコード<strong> </ strong>が無効です。それは存在しないか、まだ活動化されていないか、または期限が切れています。',
			'coupon_price_tips':"商品の合計金額がクーポンの消費状況よりも低い場合、システムは現在使用されているクーポンを自動的にキャンセルします。",
			'shipping_method_tips':'配送方法を選択してください！',
			'cart_tips':'カートに1アイテム追加',
			'plz_sel_para':'パラメータを選択してください',
			'del_confirm':'あなたはそれを削除してもよろしいですか？',
			"paypal_tips":"PayPalアカウントのアドレス情報と同じ国を選択してください。",
			'tooLess':'{0}の数が少なすぎ、{1}より大きくする必要があります。',
			'Delete_Success':'削除されました',
			'Are_You_Sure':'選択したアイテムを削除してもよろしいですか？',
			'FailToRemove':'削除できません',
			'NotOne':'1未満にできません',
			'ColorAndSize':'色とサイズを選択してください',
		},
		seckill:{
			'loading_tips':'商品はもうありません',
			'no_products':'商品と一致しません',
			'end':'終わった',
			'activity_belongs_begin':'{0}は開始しようとしているアクティビティに属しています',
			'activity_belongs_end':'{0}アクティビティが終了しました'
		},
		user:{
			'send_email_ture':'電子メールが正常に送信されました。 あなたのメールアドレスを入力し、確認メールを確認してください。',
			'send_email_false':'申し訳ありませんが、電子メールはシステム障害のために失敗して送信されました。 後でもう一度お試しください。',
			'favorite_success':'お気に入りに追加されました！',
			'favorite_saved':'この商品はお気に入りです！',
			'go_to_view':'表示に移動',
			'order_cancel':'この保留中の注文を削除してもよろしいですか？',
			'sure':'あなたは本当ですか？',
			'delete_shipping':'このアドレスを削除してもよろしいですか？',
			'reg_error':{
				'PleaseEnter':'あなたの %field% を入力してください。',
				'Email':'メールアドレスを入力してください。',
				'EmailFormat':"入力したメールは無効です。メールを確認してからもう一度お試しください。",
				'PWDConfirm':'新しいパスワードを再入力してください。',
				'PWDNotMatch':'パスワードが一致しません。もう一度お試しください。',
				'PWDLENGTH':'パスワードは8〜15桁と文字です'
			},
			'address_tips':{
				'PleaseEnter':'あなたの %field% を入力してください。',
				'email':'あなたのメールアドレスを入力してください。',
				'email_format':'入力したメールは、メールの確認値と一致しません。',
				'firstname':'あなたの名を入力してください。',
				'firstname_length':'あなたのファーストネームは2文字以上でなければなりません。',
				'lastname':'あなたの姓を入力してください。',
				'lastname_length':'あなたの名字は2文字以上でなければなりません。',
				'address':'住所を入力してください。',
				'address_length':'配送先住所が5文字以上である必要があります。',
				'city':'あなたの都市を入力してください。',
				'city_length':'あなたの都市は3文字以上でなければなりません。',
				'country':'目的の国/地域を選択してください。',
				'state':'あなたの州/都道府県を選択してください。',
				'taxcode':'あなたの %str% は必須です。',
				'taxcode_length':'あなたの %str% は最低 %TaskL% の数字を含んでいなければなりません。',
				'tariff':'申し訳ありません、あなたの %str% 番号は必須です。',
				'tariff_length':' %str% numberには最低12個の数字が必要です。',
				'zip':'郵便番号を入力してください。',
				'zip_length':'あなたのZIP /郵便番号は少なくとも4桁でなければなりません。',
				'phone':'あなたの電話番号を入力してください。',
				'phone_format':'有効な電話番号を入力してください。',
				'phone_length':'あなたの電話番号は7桁以上でなければなりません。',
				'Please_add_item':'アイテムを追加してください',
				'Input_AMT':'お支払い金額を入力してください',
			}
		},
		'goods_info':{
			'Pleaselogin':'ログインしてください',
			'Successfulinclusion':'組み込みに成功',
			'product_out':'この商品は売り切れです',
			'Please_purchased':'購入数量を入力してください',
			'Added_successfully':'追加されました',
			'Purchase_success':'購入成功',
			'No_Spec':'この製品はリリースされていません。',
			'Successful_Payment':'成功した支払い！',
		},
		
		'my_inquiry_publish':{
			'submitsuccess':'提出済み',
			'completeinformation':'情報を記入してください',
			'thecompatibletitle':'タイトルが長すぎます。互換性のあるタイトルを記入してください',
			'thecorrectEmail':'正しいメールアドレスを入力してください',
			'thecorrectquantity':'正しい数量を入力してください',
			'goodsListerr':'商品リストエラーを取得する',
			'deletethisgoods':'これらのアイテムを削除してもよろしいですか？',
			'Pleaseselect_a_goods':'あなたが望むものをサプライヤに知らせるために、アイテムを選択するか写真をアップロードしてください',
			'I18N_No':'I18Nいいえ'
		},
		'addressfrom':{
			'Inserted_successfully':'正常に挿入されました',
			'Successfully_modified':'正常に変更されました',
			'Enter_Query':'クエリを入力',
			'Merchants_Settled':'商人が落ち着いた',
			'Merchant_Side':'マーチャント側',
			'Please_Select_The_Shipping_Address':'配送先住所を選択してください',
			'Please_Select_The_Billing_Address':'最初に請求先住所を設定してください。',
			'Submit_Order_Successfully':'注文を正常に送信',
			'No_Province':'省レコードは存在しない',
			'Has_Residence':'あなたは居住のために申請した、再度申請することはできません！',
		},
		'Purchase_Center_Store':{
			'GuangZhou':'広州購買ポイント',
			'Spain':'スペイン語購入ポイント',
			'Chile':'チリの購入ポイント',
			'Romania':'ルーマニアの購入ポイント',
			'Hungary':'ハンガリーの購入ポイント',
			'America':'米国の購買ポイント',
			'Canada':'カナダの購入ポイント',
			'Russia':'ロシアの調達ポイント',
			'Belarus':'ベラルーシ購買ポイント',
			'Ukraine':'ウクライナの調達ポイント',
			'Poland':'ポーランドの購入ポイント',
			'Germany':'ドイツの購買ポイント',
			'Czekh':'チェコ調達ポイント',
			'England':'英国の購買ポイント',
			'Ireland':'アイルランドの購入ポイント',
			'Holland':'オランダの購入ポイント',
			'France':'フランスの購買ポイント',
			'Finland':'フィンランドの購入ポイント',
			'Sweden':'スウェーデンの購入ポイント',
			'Norway':'ノルウェーの購入ポイント',
			'Denmark':'デンマークの購入ポイント',
			'Korea':'韓国の購買ポイント',
			'Japan':'日本の購買ポイント',
			'Philippines':'フィリピンの購買ポイント',
			'Singapore':'シンガポール購買ポイント',
			'Israel':'イスラエルの購買ポイント',
			'Turkey':'トルコ購買ポイント',
			'Australia':'オーストラリアの購入ポイント',
			'New_Zealand':'ニュージーランドの購入ポイント',
			'South_Africa':'南アフリカの購買ポイント',
		},
		supplier_entry_hint:{
			companyname:"会社名は空ではありません！",
			suppliercategory:"Supplier分類は空ではありません！",
			companyaddress:"会社の場所は空ではありません！",
			city:"都市は空ではありません！",
			companyaddressinfo:"Company詳細なアドレスは空にすることはできません！",
			email:"Eメールは空にできません！",
			noemail:"不法な電子メール形式！",
			fund:"登録資金は空ではありません！",
			code:"ソーシャルクレジットコードは空にできません！",
			legalperson:"会社名は空にすることはできません！",
			businesslicense:"ビジネスライセンスは有効ではありません！",
			businesslicenseformat:"ビジネスライセンスの有効期間が間違っています正しい形式は2010/01 / 01",
			entity:"Contactを空にすることはできません！",
			phone:"モバイル番号は空にできません！",
			accountopeningbank:"決済口座の銀行は空ではありません！",
			bankaccount:"銀行口座番号は空ではありません！",
			openingbankname:"銀行支店名は空にできません！",
			openingbankaddress:"銀行支店の場所を空にすることはできません！",
			Legalperson_idnumber_passportnumber:"Corporate IDカード/パスポート番号は空にすることはできません！",
			Operationdirector_idnumber_passportnumber:"担当者ID /パスポート番号は空欄にはできません！",
			Enterprisecertificate:"ビジネス資格情報をアップロードする必要があります！",
			legalperson_photo_front:"法的身分証明書/パスポートをアップロードする必要があります！",
			legalperson_photo_reverseside:"正当なIDカード/パスポート逆写真をアップロードする必要があります！",
			Operationdirecto_photo_front:"操作ID /パスポートの担当者をアップロードする必要があります！",
			Operationdirecto_reverseside:"担当者ID /パスポートを逆にアップロードする必要があります"
		},
		mobile:{
			Size:'サイズ:',
			Color:'色:',
			Order_Number:'注文番号.',
			Double:'ダブル',
			Total_Price:'合計金額:',
			Shop:'ストア:',
			No_Data:'データなし',
			Collection:'コレクション',
			Add_To_Shopping_Cart:'ショッピングカートに入れる',
			Whole:'すべて',
			Collection1:'コレクションの成功',
			Collection2:'コレクションをキャンセルする',
			Collection3:'コレクションに失敗しました',
			Go_To_Set:'設定する',
			Set_Later:'後で設定する',
			Tips:'ヒント',
			Out_Of_Stock:'在庫切れ',
			Inventory_Shortage:'在庫不足、再充填してください',
			
			Confirm_receipt:"領収書を確認しますか？",
			Successful_receipt:"正常に受信しました",
			First_page:"既に最初のページです",
			The_last_page:"最後のページです",
			Acquisition_failed: "失敗する",
			Failed_to_send: "失敗しました",
			Successful_recovery:"復旧は成功しました",
			all_reviews:"すべてのコメント",
		},
		cart3:{
			name:'名前',
			Contents:'備考',
			SentMoney:'送金',
			Number:'参照番号',
			Currency:'通貨',
		},
		home:{
			brneficiary:'受益者',
			bankaddress:'銀行の住所',
			swiftcode:'SWIFTコード',
			beneficiarybank:'有益な銀行',
			beneficiaryaccount:'受取人口座',
			depos_it_bank_subbranch_ascription:'決済口座保有者',
			account_name:'銀行口座',
			depos_it_bank_subbranch:'銀行支店名',
			bank_account:'支店支店の位置',
		},
		supplier:{
			products:'製品',
			productsStyle:'処理方法'
		}
};