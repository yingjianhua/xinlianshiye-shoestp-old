/*
Powered shoestp
*/

var lang_obj={
		
		Name_cannot: "Name cannot be empty",
		The_amount_cannot: "The amount cannot be empty",
	orders:{
		'status':{
			'0':'WAIT',
			'1':'WAITCONFIRM',
			'2':'ERROR',
			'3':'WAITDELIVER',
			'4':'DELIVER',
			'5':'COMPLETE',
			'6':'CANCEL',
			'7':'DELETED',
			'choose_payment':'Choose payment method',
			'order_total':'Order Total'
		},
	},
	page:{
		prev: "prev",
		next: "next"
	},
	
	
	
	/*
	Powered shoestp
	*/
		global:{
			/*询盘*/
			'already':'This product already exists Inquiry Bag!',
			'inqadd':'Successfully added to your inquiry!',
			'continues':'Continue to stroll',
			'inquery':'Inquiry Now',
			'vcode':'Verification code',
			/*询盘结束*/
			'add':'Add',
			'edit':'Edit',
			'sure':'Sure',
			'submit':'Submit',
			'confirm':'Confirm',
			'cancel':'Cancel',
			'copy_confirm':'Enter the product replication mode, continue?',
			'copy_model_confirm':'Enter the attribute replication mode, continue?',
			'del_confirm':'Irretrievable after deletion, continue?',
			'reset_confirm':'Confirm to restore the default settings, continue?',
			'used_confirm':'Ready to turn on the batch mode, continue?',
			'close_confirm':'Ready to turn off the batch mode, continue?',
			'my_order_confirm':'Ready to turn on the sorting mode, continue?',
			'sold_in_confirm':'Ready to turn on the sales mode, continue?',
			'sold_out_confirm':'Ready to turn on the off sales model, continue?',
			'data_posting':'Data being submitted...',
			'loading':'Loading...',
			'sync':'Data being synchronization...',
			'confirm_password_error':'The new password does not match the confirmation password, please re-enter!',
			'del_dat_select':'Please select the item to be deleted',
			'used_dat_select':'Please select the item to be started',
			'close_dat_select':'Please select the item to be closed',
			'dat_select':'Please select the chosen item',
			'correct_message':'Please fill in the correct data',
			'n_y':['No','Yes'],
			'open':'Unfold',
			'pack_up':'Fold',
			'del':'Delete',
			'picture':'Picture',
			'picture_name':'Picture Name',
			'set_error':'Setup failed unknown mistakes!',
			'vcode':'Verification code',
			'unknown_mistake':'unknown mistake',
		},
		format:{
			'mobilephone':'Please fill in the phone number correctly!',
			'telephone':'Please fill in the telephone number!',
			'fax':'Please fill in the fax number correctly!',
			'email':'Please fill in the email address correctly!',
			'length':'Incorrect length! You need to fill in %num% digits.'
		},
		signIn:{
			'title':'Sign In',
			'error_note':'Incorrect email address or password. Please try again.<br> Make sure the Caps Lock is off before you enter password.',
			'email':'Email',
			'password':'Password',
			'forgot':'Forgot your <a href="/home/usr_UsrPurchase_sendEmail" class="forgot">password</a>?',
			'stay_note':'Stay signed in <span>Protect your privacy - sign out when you\'re done.</span>',
			'sign_in':'Sign in',
			'join_fee':'Join Free',
			'verification':'Verification code error',
			'original_password_wrong':'The original password is wrong',
			'Register_Success':'registration success!',
			'Username_Exists':'Username already exists',
			'Old_Password_Empty':'The original password cannot be empty!',
			'New_Password_Empty':'New password cannot be empty!',
			'Confirm_Password_Empty':'confirm password can not be blank!',
		},
		newsletter:{
			'success':'Added to subscribe successful!',
			'exists':'This mailbox already exists subscription!'
		},
		language:{
			'zh-cn':'Chinese',
			'en':'English ',
			'jp':'Japanese',
			'de':'German',
			'fr':'French',
			'es':'Spanish',
			'ru':'Russian',
			'pt':'Portuguese',
			'zh_tw':'Traditional Chinese'
		},
		manage:{
			frame:{
				'time':'Time',
				'status':'Status',
				'support':['Enterprise QQ', 'Working Hours', 'Telephone', 'We Chat', 'Superior Complainant', 'Website Expiration Time'],
				'data_backup':'Data Backup',
				'backup':['Database', 'Folder'],
				'file_upload':'SELECT FILES'
			},
			account:{
				"log_in":"The system is trying to log in, please wait...",
				"log_in_ok":"Successful login, the page jumps, please wait...",
				"order_quantity":"Website Order",
				"page_view":"Site Visits",
				"success_cache":"Cache has been cleared successfully!",
				"percentage":"Percentage",
				"picture_tips":"You have uploaded more than xxx pictures and cannot uploaded anymore!",
				"password_tips":"Re-password do not match, please re-enter!"
			},
			set:{
				"select_once_language":"Please select at least one language version",
				"print_0":"Print the customs declaration",
				"print_1":"Print invoice",
				"platform_ary":{'SignIn':'Login','Pixel':'Statistics'},
			},
			module:{
				"sure_module":"Are you sure to choose this style?",
				"name":"Name",
				"url":"Links address",
				"target":"New window",
			},
			counrtry:{
				"state":"Province",
			},
			photo:{
				"empty_temp":"Temporary folder has been cleared successfully!",
				"move_success":"Removed successfully",
				"picture_upload":"Picture Upload"
			},
			shipping:{
				"area_config":"Area Config",
			},
			products:{
				"category_tips":"Please select the product category",
				"qty":"Quantity",
				"price":"Price",
				"mark_up":"Price increased",
				"discount":"Discount",
				"group_attr":"Combination Properties",
				"wholesale_discount_notes":"The price of the product attribute selected by the current station is the unit price, the calculation of the wholesale price will be calculated in a discounted way",
				"prefix_tips":"The prefix of product number can’t be empty!",
				"sync_change_account":"Switching account..."
			},
			sales:{
				"seckill":"Seckilling",
				"promotion":"Promotion",
				"proName":"Product Name",
				"proPrice":"Product Price",
				"proNumber":"Product Number",
				"proBiref":"Brief introduction",
				"start_time":"Starting time",
				"duration":"Duration",
				"hour":"Hours",
				"tuan_price":"Group-buying price",
				"buyer_count":"Purchases",
				"total_count":"Restrictions",
				"promotion_price":"Total promotion price",
				"seckill_price":"Price spike",
				"qty":"Quantity",
				"remainder_qty":"Residual quantity",
				"max_qty":"Purchase Ceiling",
				"reverseAssociate":"Inversely correlated (This combination will be displayed simultaneously for the associated products when no combination is set)",
				"mainArea":"The product already exists in the main products area",
				"relatedArea":"The product already exists in the bundled products area",
				"holidayNotice":'Drag the product list on the right side to <b class="fc_red">"holiday-template products area"</b>',
				"seckillNotice":'Drag the product list on the right side to <b class="fc_red">"seckilling products area"</b>',
				"tuanNotice":'Drag the product list on the right side to <b class="fc_red">"group-buying products area"</b>',
				"relatedNotice":'Drag the product list on the right side to <b class="fc_red">"main products area"</b> and <b class="fc_red">"bundled products area"</b>',
				"beyondNumber":"Exceed the maximum limit number: ",
				"condition":"Consumption Conditions",
				"discount":"Discount",
				"money":"Money",
				"condition_tips":"This coupon can only be used when the shopping amount reaches the set value; there will be no restrictions on the usage conditions if you set 0",
				"discount_tips":"Please enter the percentage of discount; 20% is equivalent to 80% off, 80% is equivalent to 20% off",
				"money_tips":"Please enter the coupon code between 8 to 15 digits",
				"check_choose":"Unchecked item"
			},
			email:{
				"load_error":"Template data reading failed, please try again later!",
				"not_model":"No template is chosen!",
				"not_user":"No Member!",
				"not_content":"No Content!"
			},
			mta:{
				"traffic_time":['Last 30 days', 'Today', 'Yesterday', 'Last 7 days', 'Last 15 days'],
				"statistics_name":['Visitors', 'Pageviews']
			},
		},
		products:{
			'warning_number':'Purchase fewer than or more than the stock MOQ',
			'warning_MOQ':'Low from the MOQ',
			'warning_Max':'The maximum number of purchases %num%',
			'warning_stock':'Higher than the inventory %num%',
			'warning_attribute':'Please select parameters',
			'select_country':'--Please select your country--',
			'sign_in':'Please sign in first!',
			'free_shipping':'Free Shipping',
			'no_optional':'No optional',
			'no_up_carriage':'{0} not on the shelf',
			'product_deleted':'Product {0} has been deleted',
		},
		cart:{
			'processing':'Processing, please wait...',
			'processing_str':'Processing',
			'checked_error':'Please select at least one item',
			'attribute_error':'There was an error in the product information, please remove this product and then re purchase.',
			'address_error':'Please enter your shipping address or choose one from your previously entered address. Ensure information, please click save after completed.',
			'shipping_error':'Please choose a delivery method.',
			'payment_error':'Please choose a payment method.',
			'product_error':'Your shopping cart is empty.',
			'low_error':'Your total product has not yet reached the lowest consumption value of the website',
			'stock_error':'{0} Insufficient stock, unable to create an order correctly, adjust the purchase quantity.',
			'prod_stock_error':'{0} Insufficient inventory ',
			'no_delivery':'Sorry! No delivery to this Country/Region!',
			'additem_0':'This product was successfully added to cart!',
			'additem_1':'<b class="FontColor">%num%</b> Item(s) in your cart. Subtotal: ',
			'return_shopping':'Return to Shopping',
			'proceed_checkout':'continue shopping',
			'arrival_info_0':'Has been successfully submitted to the business, the products cover will immediately notify the customer.',
			'arrival_info_1':'Please log in to apply for arrival notice.',
			'arrival_info_2':'This product has applied for the arrival notice!',
			'use_shipping_address':'Use This Shipping Address',
			'coupon_title':'Enter your coupon code',
			'coupon_tips':'The coupon code "<strong>%coupon%</strong>" is valid!<br /> Your discount is',
			'coupon_code':'Coupon Code',
			'apply':'Apply',
			'select_y_country':'Select you country',
			'remove':'Remove',
			'batch_remove_select':'Please select the item(s) you want to delete!',
			'batch_remove_success':'Product(s) deleted successfully!',
			'batch_remove_error':'Product(s) deletion failed!',
			'continue_str':'Continue Checkout',
			'total_amout':'Total Amout',
			'insurance':'Add Shipping Insurance to your order',
			'coupon_tips_to':'The coupon code %coupon% is invalid. It either does not exist, has not been activated yet, or has expired.',
			'coupon_tips_th':'The coupon code <strong></strong> is invalid. It either does not exist, has not been activated yet, or has expired.',
			'coupon_price_tips':"The total amount of the product is lower than the coupon's consumption conditions, the system automatically cancel the coupons now used.",
			'shipping_method_tips':'Please select a shipping method!',
			'cart_tips':'1 item added to cart',
			'plz_sel_para':'Please select parameters',
			'del_confirm':'Are you sure to delete it?',
			"paypal_tips":"Please select the same country as the PayPal account address information.",
			"tooLess":"The number of {0} is too small and needs to be greater than {1}",
			'Delete_Success':'successfully deleted',
			'Are_You_Sure':'Are you sure you want to delete the selected item?',
			'FailToRemove':'Fail to delete',
			'NotOne':'Cannot be less than 1',
			'ColorAndSize':'Please choose color and size',
		},
		seckill:{
			'loading_tips':'There is no more products',
			'no_products':'Not match any products',
			'end':'Finished',
			'activity_belongs_begin':'{0} belongs to activity about to begin',
			'activity_belongs_end':'{0} belongs to activity has ended'
		},
		user:{
			'send_email_ture':'An email has been sent to you successfully. Please enter your email address and check the verification email.',
			'send_email_false':'Sorry, the email was sent in failure because of system problem. Please try again later.',
			'favorite_success':'Added to the favorites successfully!',
			'favorite_saved':'This product has been favorited!',
			'go_to_view':'Go to view',
			'order_cancel':'Are you sure you want to delete this pending order?',
			'sure':'Are you sure?',
			'delete_shipping':'Are you 100% sure you want to delete this address?',
			'reg_error':{
				'PleaseEnter':'Please enter your %field%.',
				'Email':'Please enter an email address.',
				'EmailFormat':"The email you've entered is invalid. Please check your email and try again.",
				'PWDConfirm':'Please re-enter your new password.',
				'PWDNotMatch':'Your passwords do not match, please try again.',
				'PWDLENGTH':'Password is 8-15 digits and letters'
			},
			'address_tips':{
				'PleaseEnter':'Please enter your %field%.',
				'email':'Please enter your Email.',
				'email_format':'Email entered doesn\'t match with confirm Email value.',
				'firstname':'Please enter your first name.',
				'firstname_length':'Your First name must contain a minimum of 2 characters.',
				'lastname':'Please enter your last name.',
				'lastname_length':'Your First name must contain a minimum of 2 characters.',
				'address':'Please enter shipping address.',
				'address_length':'Your shipping address should be at least 5 characters long.',
				'city':'Please enter your city.',
				'city_length':'Your city should be at least 3 characters long.',
				'country':'Please select your destination country/region.',
				'state':'Please select your state/province/region.',
				'taxcode':'Sorry, your %str% is required.',
				'taxcode_length':'Your %str% must contain a minimum of %taxlen% numbers.',
				'tariff':'Sorry, your %str% number is required.',
				'tariff_length':'Your %str% number must contain a minimum of 12 numbers.',
				'zip':'Please enter a ZIP / postal code.',
				'zip_length':'Your ZIP / postal code should be at least 4 digits long.',
				'phone':'Please enter your phone number.',
				'phone_format':'Please enter a valid phone number.',
				'phone_length':'Your phone number must be at least 7 digits.',
				'Please_add_item':'Please add item',
				'Input_AMT':'Please enter the payment amount',
			},
		},
		
		'goods_info':{
			'Pleaselogin':'please sign in',
			'Successfulinclusion':'Successful inclusion',
			'product_out':'This item is sold out',
			'Please_purchased':'Please enter the quantity purchased',
			'Added_successfully':'Added successfully',
			'Purchase_success':'Purchase success',
			'No_Spec':'This product has not been released specification.',
			'Successful_Payment':'Successful payment!',
		},
		
		'my_inquiry_publish':{
			'submitsuccess':'Submitted successfully',
			'completeinformation':'Please complete the information',
			'thecompatibletitle':'The title is too long, please fill in the compatible title',
			'thecorrectEmail':'Please fill in the correct email',
			'thecorrectquantity':'Please fill in the correct quantity',
			'goodsListerr':'Get the goods list error',
			'deletethisgoods':'Are you sure you want to delete these items?',
			'Pleaseselect_a_goods':'Please select an item or upload some pictures to let the supplier know what you want',
			'I18N_No':'I18N No'
		},
		'addressfrom':{
			'Inserted_successfully':'Inserted successfully',
			'Successfully_modified':'Successfully modified',
			'Enter_Query':'Enter the query',
			'Merchants_Settled':'Merchants settled',
			'Merchant_Side':'Merchant side',
			'Please_Select_The_Shipping_Address':'Please select the shipping address',
			'Submit_Order_Successfully':'Submit order successfully',
			'writeComment_Nocomment':'You need to succeed in purchasing this product can comment.',
			'Please_Select_The_Billing_Address':'Please set the billing address first.',
			'writeComment_Commented':'You have reviewed the commodities.',
		},
		'Purchase_Center_Store':{
			'GuangZhou':'GuangZhou',
			'Spain':'Spain',
			'Chile':'Chile',
			'Romania':'Romania',
			'Hungary':'Hungary',
			'America':'America',
			'Canada':'Canada',
			'Russia':'Russia',
			'Belarus':'Belarus',
			'Ukraine':'Ukraine',
			'Poland':'Poland',
			'Germany':'Germany',
			'Czekh':'Czekh',
			'England':'England',
			'Ireland':'Ireland',
			'Holland':'Holland',
			'France':'France',
			'Finland':'Finland',
			'Sweden':'Sweden',
			'Norway':'Norway',
			'Denmark':'Denmark',
			'Korea':'Korea',
			'Japan':'Japan',
			'Philippines':'Philippines',
			'Singapore':'Singapore',
			'Israel':'Israel',
			'Turkey':'Turkey',
			'Australia':'Australia',
			'New_Zealand':'New Zealand',
			'South_Africa':'South Africa',
		},
		supplier_entry_hint:{
			companyname:"Company name can't be empty!",
			suppliercategory:"Supplier classification cannot be empty!",
			companyaddress:"The company location cannot be empty!",
			city:"The city can't be empty!",
			companyaddressinfo:"Company detailed address can not be empty!",
			email:"E-mail cannot be empty!",
			noemail:"Illegal email format!",
			fund:"Registration funds cannot be empty!",
			code:" Social credit code can't be empty!",
			legalperson:"Corporate name cannot be empty!",
			businesslicense:" The business license cannot be valid!",
			businesslicenseformat:" The validity period of the business license is incorrect. The correct format is: 2010/01/01",
			entity:"Contact can't be empty!",
			phone:"Phone number can not be blank!",
			accountopeningbank:"Settlement account bank can not be empty!",
			bankaccount:"Bank account number cannot be empty!",
			openingbankname:" The bank branch name cannot be empty!",
			openingbankaddress:" The location of the bank branch can not be empty!",
			Legalperson_idnumber_passportnumber:"Corporate ID card/passport number cannot be empty!",
			Operationdirector_idnumber_passportnumber:"The person in charge ID/passport number cannot be empty!",
			Enterprisecertificate:" need to upload business credentials!",
			legalperson_photo_front:"You need to upload a legal ID card / passport front!",
			legalperson_photo_reverseside:"Need to upload legal ID card / passport reverse photo!",
			Operationdirecto_photo_front:"Requires uploading the person in charge of the operation ID/passport front!",
			Operationdirecto_reverseside:" need to upload the operation responsible person ID / passport reverse!"
		},
		mobile:{
			Size:'Size:',
			Color:'Color:',
			Order_Number:'Order Number.',
			Double:'Double',
			Total_Price:'Total Price:',
			Shop:'Store:',
			No_Data:'No Data',
			Collection:'Like',
			Add_To_Shopping_Cart:'Add To Shopping Cart',
			Whole:'All',
			Collection1:'Successful collection',
			Collection2:'Cancel collection',
			Collection3:'Collection failed',
			Go_To_Set:'Go To Set',
			Set_Later:'Set Later',
			Tips:'Tips',
			Out_Of_Stock:'Out Of Stock',
			Inventory_Shortage:'Inventory shortage, please re-fill',
			
			Confirm_receipt: "Confirm receipt?",
			Successful_receipt: "Received successfully",
			First_page: "It is already the first page",
			The_last_page: "It is the last page",
			Acquisition_failed: "Get failed",
			Failed_to_send: "Send failed",
			Successful_recovery: "Recovery is successful",
			all_reviews: "All comments",
		},
		cart3:{
			name:'Name',
			Contents:'Remarks',
			SentMoney:'Money Transfer',
			Number:'Reference Number',
			Currency:'Currency',
		},
		home:{
			brneficiary:'Beneficiary',
			bankaddress:'Bank address',
			swiftcode:'Swift Code',
			beneficiarybank:'Beneficial bank',
			beneficiaryaccount:'Beneficiary account',
			depos_it_bank_subbranch_ascription:'Settlement account holder',
			account_name:'Bank Account',
			depos_it_bank_subbranch:'Bank branch name',
			bank_account:'Bank branch branch location',
		},
		supplier:{
			products:'product',
			productsStyle:'Processing methods'
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
};