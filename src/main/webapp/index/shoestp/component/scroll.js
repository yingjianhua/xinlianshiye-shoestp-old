Vue.component('scroll',{
	data(){
		return {
			timer:{},
		}
	},
	props:{
		number:{
			type:Number,
		},
		numberArray:{
			type:Array,
			default:[],
		},
		numberMap:{
			type:Array,
			default:[],
		},
		id:{
			type:String,
		},
		time:{
			type:Number,
			default:3000
		},
		value:{
			type:Number,
		},
		
	},
	template:'<ul class="num" :id="id">'+
					'<li v-for="(key,index) in numberMap" :key="index">'+
			  			'<div></div>'+
			  			'<i class="img_number" :class="[key]"></i>'+
			  		'</li>'+
			  	'</ul>',
	mounted(){
		this.animates();
	},
	watch: {
		number:{
			handler(newValue, oldValue){
				var num = this.number.toString();
				this.numberArray = num.split(''); 
				this.getNumClass(this.numberArray);
			},
		}
	    
	},
	methods:{
		animates:function(){
			var num = this.number.toString();
			this.numberArray = num.split(''); 
			this.getNumClass(this.numberArray);
		},
		getAllInquiry:function(val){
			this.$emit('allinquiry', val);
		},
		getAllMoney:function(val){
			this.$emit('allmoney', val);
		},
		getRegister:function(val){
			this.$emit('register', val);
		},
		getNumClass:function(arrays){
			var self = this;
			self.numberMap = [];
				for(var i=0;i<arrays.length;i++){
					var num = arrays[i];
					var icon;
					switch(Number(num)){
						case 0:
							icon = 'zero';
							break;
						case 1:
							icon = 'one';
							break;
						case 2:
							icon = 'two';
							break;
						case 3:
							icon = 'three';
							break;
						case 4:
							icon = 'four';
							break;
						case 5:
							icon = 'five';
							break;
						case 6:
							icon = 'six';
							break;
						case 7:
							icon = 'seven';
							break;
						case 8:
							icon = 'eight';
							break;
						case 9:
							icon = 'nine';
							break;
					}
					self.numberMap.push(icon);
				}
		},
	},
//	destroyed(){
//		var self = this;
//		clearInterval(self.timer[self.id]);
//	}
})
