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
		hasInterval:{
			type:Number,
			default:1
		},
		time:{
			type:Number,
			default:3000,
		}
	},
	template:'<ul class="num" :id="id">'+
					'<li v-for="(key,index) in numberMap" :key="index">'+
			  			'<div></div>'+
			  			'<i class="img_number" :class="[key]"></i>'+
			  		'</li>'+
			  	'</ul>',
	mounted(){
		var num = this.number.toString();
		this.numberArray = num.split(''); 
		this.getNumClass(this.numberArray);
		if(this.hasInterval == 1){
			this.animates();
		}
	},
	watch: {
		number:{
			handler(newValue, oldValue){
				var num = this.number.toString();
				this.numberArray = num.split(''); 
				this.getNumClass(this.numberArray);
			},
		},
//		time:{
//			handler(newValue, oldValue){
//				var self = this;
//				self.time = newValue;
//				clearInterval(self.timer[self.id]);
//				self.animates();
//			},
//		}
	    
	},
	methods:{
		animates:function(){
			//var self = this;
//			self.timer[self.id] = setInterval(function(){
//				if(self.id == 'money_count'){
//					
//				}
//				if(self.id == 'browse_count'){
//					
//				}
//			},self.time);
		},
		getBrowse:function(obj){
			this.$emit('allbrowse', obj);
		},
		getAllMoney:function(obj){
			this.$emit('allmoney', obj);
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
		
		}
	},
	destroyed(){
		var self = this;
		clearInterval(self.timer[self.id]);
	}
})
