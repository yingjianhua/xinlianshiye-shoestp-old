//第一页数据
		//总成交金额
		var _nav1={
			all_money:0,			//总成交金额//累计成交金额
			all_register:0,		//总注册量
			all_products:0,		//总商品数
			all_inquiry:0,		//总询盘量
			today_register:40,		//今日注册量
			today_browse:6780,		//今日浏览量
			today_success:1086504,	//今日成交金额
			customer_unit_price:271626,//客单价
			yesterday_all_money:1138,	//较昨日成交额
			yesterday_all_register:5,	//较昨日注册量
			yesterday_browse:0,		//较昨日浏览量

			visit_data:[15146,23628,42057,84115,134584,403751,541027,827771,1200267,1440321,0,0],//访问量--柱形图
			browse_data:[137828,305932,688732,1850526,1959538,10053403,6017297,10385210,14271179,14172757,0,0],//浏览量--柱形图
			terminal_browse_data:[0.6210,0.1664,0.1563,0.0563],//设备占比
			flow_source:[0.85,0.09,0.06],//流量来源
			prod_category:[41,34,25],//商品分类
			
			purchase:35,
			
			inquiry_data:[
				        	{
				        		name:'Romero',
				        		prod:'Romania Whol...',
				        		qty:'13056',
				        		amt:'$685,530.00'
				        	},
				        	{
				        		name:'Cosima',
				        		prod:'Kangfu Men\'S...',
				        		qty:'12860',
				        		amt:'$354,200.00'
				        	},
				        	{
				        		name:'Molnár',
				        		prod:'SE Women New...',
				        		qty:'11985',
				        		amt:'$58,345.00'
				        	},
				        	{
				        		name:'Cecilio',
				        		prod:'Aokang official...',
				        		qty:'11025',
				        		amt:'$522,352.00'
				        	},
				        	{
				        		name:'Jack',
				        		prod:'Kangfu Men\'S...',
				        		qty:'10235',
				        		amt:'$232,877.00'
				        	},
				        	{
				        		name:'Геннадий',
				        		prod:'Women\'s C...',
				        		qty:'9632',
				        		amt:'$582,345.00'
				        	},
				        	{
				        		name:'Amor',
				        		prod:'FOLLOW ME girls...',
				        		qty:'8945',
				        		amt:'$123,565.00'
				        	},
				        	{
				        		name:'Евгений',
				        		prod:'SE Women N...',
				        		qty:'8787',
				        		amt:'$223,345.00'
				        	},
				        	{
				        		name:'Amor',
				        		prod:'FOLLOW ME girls...',
				        		qty:'6766',
				        		amt:'$123,565.00'
				        	},
				        	{
				        		name:'Евгений',
				        		prod:'SE Women N...',
				        		qty:'5541',
				        		amt:'$223,345.00'
				        	},
				        ],

			order_info:[			//订单信息
				        	{
				        		from:'来自俄罗斯',
				        		name:'Антон',
				        		type:'付款订单',
				        		money:'$58,354.00',
				        		state:'等待付款',
				        	},
				        	// {
				        	// 	from:'来自罗马尼亚',
				        	// 	name:'Anastase',
				        	// 	type:'付款订单',
				        	// 	money:'32,154.00',
				        	// 	state:'等待付款',
				        	// },
				        	{
				        		from:'来自意大利',
				        		name:'Cecilio',
				        		type:'付款订单',
				        		money:'$55,321.00',
				        		state:'2018/10/18 12:21:12',
				        	},
				        	/*{
				        		from:'来自匈牙利',
				        		name:'Molnár',
				        		type:'付款订单',
				        		money:'24,512.00',
				        		state:'2018/10/17 15:14:28',
				        	},
				        	{
				        		from:'来自西班牙',
				        		name:'Romero',
				        		type:'付款订单',
				        		money:'26,124.00',
				        		state:'2018/10/15 09:11:36',
				        	},*/
				        ],

			order_sort:[
				        	{
				        		name:'Romania Wholes...',
				        		amt:'6,651'
				        	},
				        	{
				        		name:'Juna ladies dres...',
				        		amt:'6,648'
				        	},
				        	{
				        		name:'Kangfu Men\'s ',
				        		amt:'5,861'
				        	},
				        	{
				        		name:'FOLLOW ME girls...',
				        		amt:'5,695'
				        	},
				        	{
				        		name:'SE Women New ...',
				        		amt:'4,859'
				        	},
				        	{
				        		name:'SE Women N...',
				        		amt:'52,352'
				        	},
				        	{
				        		name:'Kangfu\'s...',
				        		amt:'44,264'
				        	},
				        	{
				        		name:'Kangfu\'s...',
				        		amt:'34,200'
				        	},
				        	
				        ],
				        order_list2:[
						        	{
						        		name:'Daniel',
						        		amt:'840,215'
						        	},
						        	{
						        		name:'Archibald',
						        		amt:'831,220'
						        	},
						        	{
						        		name:'Molnár',
						        		amt:'700,216'
						        	},
						        	{
						        		name:'Romero',
						        		amt:'655,530'
						        	},
						        	{
						        		name:'Cecilio',
						        		amt:'544,232'
						        	},
						        	{
						        		name:'Антон',
						        		amt:'523,352'
						        	},
						        	{
						        		name:'Геннадий',
						        		amt:'448,264'
						        	},
						        	{
						        		name:'Cosima',
						        		amt:'341,200'
						        	},{
						        		name:'Kenneth',
						        		amt:'284,200'
						        	},
						        	{
						        		name:'Archibald',
						        		amt:'253,200'
						        	},
						        	
						        ],

			register_data:[174,271,483,966,1546,4638,6215,9510,14620,16547,0,0],

	        //运营数据
	        operation_data:{
	        	x_axis:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	        	browse_data:[2.0, 5, 7.0, 23, 25, 76, 135, 162, 32, 20, 0, 0],
	        	visit_data:[2, 5, 9, 26, 28,70, 175, 182, 48, 18, 0, 0],
	        	field:['浏览量','访问量']
	        },

	        //流量终端数据
	        flow_data:{
	        	browse_data:["0.6210","0.1664","0.1563","0.0563"],
	        	browse_name:["PC端","IOS","Android","其他"],
	        	field:['访问来源'],
	        	title:["流量终端"],
	        },

	        //流量来源数据
	        source_data:{
				flow_source:["0.3","0.3","0.1","0.3"],
				source_name_array:["谷歌搜索","百度搜索","直接访问","其他"],
				title:['流量来源'],
				field:["谷歌搜索","百度搜索","直接访问","其他"]
	        },

	        //商品分类数据
	        prodCat_data:{
	        	browse_data:["41","34","25"],
	        	browse_name:["男鞋","女鞋","童鞋"],
	        	field:['销售量',],
	        	title:["商品分类"],
	        },
	        //中国地图数据
	        chinaDetail:[
		                  {name: '浙江',value: 500 },{name: '广东',value: 300 },
		                  {name: '四川',value: 200 },{name: '福建',value: 400 },
		              ],
		    //世界地图数据
	        worldDetail:[
							{name: '俄罗斯', value: 100},
			                {name: '西班牙', value: 200},
			                {name: '意大利', value: 300},
			                {name: '罗马尼亚', value:400},
			                {name: '匈牙利', value: 500}
			],
			//世界地图右侧展示数据
			countryData:[
	     		           {
	     		        	   name:'西班牙',
	     			        	value:'23.21%'
	     			           },
	     			           {
	     		        	   name:'意大利',
	     			        	value:'20.12%'
	     			           },
	     			           {
	     		        	   name:'罗马尼亚',
	     			        	value:'18.41%'
	     			           },
	     			           {
	     		        	   name:'匈牙利',
	     			        	value:'18.11%'
	     			           },{
	     		        	   name:'俄罗斯',
	     			        	value:'11.54%'
	     			           },
	     			           {
	     		        	   name:'其它',
	     			        	value:'8.61%'
	     			           },
	     			        ],
	     	//中国地图右侧展示数据
			chinaData:[
		   	           {
		           	   name:'浙江',
		   	        	value:'90%'
		   	           },
		   	           {
		           	   name:'广东',
		   	        	value:'3%'
		   	           },
		   	           {
		           	   name:'四川',
		   	        	value:'3%'
		   	           },
		   	           {
		           	   name:'福建',
		   	        	value:'4%'
		   	           },
		   	        ],
		   	//地图部分展示数据
	        dataMap:{
	        	title:'国家排行',
	        	type:'world',
	        	id:'world',
	        	data:[
					{name: '俄罗斯', value: 100},
	                {name: '西班牙', value: 500},
	                {name: '意大利', value: 400},
	                {name: '罗马尼亚', value:300},
	                {name: '匈牙利', value: 200}
	              ],
	              dataColor:['#FB505F','#54F5FF','#FAC942','#B52ACC','#FF5160'],
				dataImages:['image://./shoestp/images/dw1.png','image://./shoestp/images/dw2.png','image://./shoestp/images/dw3.png','image://./shoestp/images/dw4.png','image://./shoestp/images/dw5.png'],
	              showData:[
	     		           {
	     		        	   name:'西班牙',
	     			        	value:'23.21%'
	     			           },
	     			           {
	     		        	   name:'意大利',
	     			        	value:'20.12%'
	     			           },
	     			           {
	     		        	   name:'罗马尼亚',
	     			        	value:'18.41%'
	     			           },
	     			           {
	     		        	   name:'匈牙利',
	     			        	value:'18.11%'
	     			           },{
	     		        	   name:'俄罗斯',
	     			        	value:'11.54%'
	     			           },
	     			           {
	     		        	   name:'其它',
	     			        	value:'8.61%'
	     			           },
	     			        ]
	        },
	        
	        //设置
	        setting:{
	        	//总成交金额时间设置
	        	money_change_time:[10*60*1000,20*60*1000],
	        	//money_change_time:[1*1000,2*1000],
	        	//注册量时间设置
	        	register_change_time:[30*1000,60*1000],
	        	//register_change_time:[3*1000,6*1000],
	        	//浏览量时间设置
	        	browse_change_time:[1000,5000],
	        	//询盘量时间设置
	        	inquiry_change_time:[30*1000,60*1000],
	        	//inquiry_change_time:[3*1000,6*1000],
	        	//总商品量时间设置
	        	product_change_time:[10*60*1000,20*60*1000],
	        	//product_change_time:[2*1000,5*1000],
	        	//今日成交时间设置
	        	today_success_change_time:[8*60*1000,8*60*1000],
	        	//today_success_change_time:[3*1000,5*1000],
	        	//采购商时间设置
	        	purchase_change_time:[20*1000,60*1000],
	        	//purchase_change_time:[3000,3000],
	        	//供应商时间设置
	        	supplier_change_time:[5*60*1000,10*60*1000],
	        	//supplier_change_time:[5000,5000],
	        }

		}
