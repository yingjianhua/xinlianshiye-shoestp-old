Vue.component('order',{
	props:{
		orders:{
			type:Array,
			default:[],
		},
		id:{
			type:String,
		},
		time:{
			type:Number,
			default:3000,
		},
		vis:{
			type:Number,
			default: 2
		},
		scroll_num:{
			type:Number,
			default:1
		},
		width:{
			type:String,
			default:'238px'
		}
	},
	template:'<div :id="id" :key="id"><ul class="order_info">'+
			  	'<li v-for="(key,index) in orders">'+
			  		'<p class="order_info_detail" :class="[index%2==0?\'add\':\'even\']">'+
			  			'<span class="country">{{key.from}}</span>'+
			  			'<span class="name" style="width:80px;">{{key.name}}</span>'+
			  			'<span class="money" :style="{\'width\':width}">{{key.type}}&nbsp;&nbsp;&nbsp;{{key.money}}</span>'+
//			  			'<span class="state">{{key.state}}</span>'+
			  		'</p>'+
			  	'</li>'+
			  '</ul></div>',
	mounted(){
		if(this.orders && this.orders.length){
			this.changeStyle();
		}
	// setInterval(function(){self.doscroll();},self.time);
	},
	watch:{
		orders(){
			this.changeStyle();
		},
		time:{
			handler:function(newValue, oldValue){
				var self = this;
				if(newValue != 3000){
					self.time = newValue;
					console.log(self.time)
					self.$nextTick(()=>{
						jQuery("#"+self.id).slide({mainCell:"ul.order_info",interTime:self.time,autoPage:true,effect:"topLoop",autoPlay:true,vis:this.vis});
					})
				}
				
			},
			deep:true,
		}
	},
	methods:{
		changeStyle:function(){
			var self = this;
			this.$nextTick(()=>{
				jQuery("#"+this.id).slide({mainCell:"ul.order_info",autoPage:true,scroll:self.scroll_num,interTime:self.time,effect:"topLoop",autoPlay:true,vis:self.vis});
			})
		}
		// doscroll:function (){
		// 	var self = this;
		// 	var $parent = $("#"+self.id);
		// 	var $first = $parent.find('li:first');
		// 	var height = $first.height();
		// 	self.getNewObj()
		// 	$first.animate({
		// 	height: 0   //或者改成： marginTop: -height + 'px'
		// 	}, 300, function() {// 动画结束后，把它插到最后，形成无缝
		// 	$first.css('height', height).appendTo($parent);
		// // $first.css('marginTop', 0).appendTo($parent);
    // });
		// },
		// getNewObj:function(){
		// 	var name = ['asdas','sadasd','asdasd','asdss'];
		// 	var money = [1231,12312,123,123]
		// 	var state = ['完成付款','等待付款','刚刚付款'];
		// 	var from = ['来自美国加州','来自美国加州','来自美国加州','来自美国加州'];
		// 	var type = ['付款订单','确认订单'];
		// 	var newObj = {};
		// 	newObj.from = from[Math.floor(Math.random() * from.length + 1)-1];
		// 	newObj.money = money[Math.floor(Math.random() * money.length + 1)-1];
		// 	newObj.state = state[Math.floor(Math.random() * state.length + 1)-1];
		// 	newObj.name = name[Math.floor(Math.random() * name.length + 1)-1];
		// 	newObj.type = type[Math.floor(Math.random() * type.length + 1)-1];
		// 	this.$emit('newOrder', newObj);
		// }

	}
})
