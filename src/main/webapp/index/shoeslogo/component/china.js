	Vue.component('china', {
		props:["data"],
	  template: '<div :style="{height:\'283px\',width:\'100%\',margin:\'0 auto\'}" id="china"></div>',
	  mounted(){
		  this.renderConfigure();
	  },
	  methods:{
		  renderConfigure(){
	    		var self = this;
		        var myChart = echarts.init(document.getElementById("china")); //这里是为了获得容器所在位置    
		        var option = {
			            tooltip: {
			                trigger: 'item',
			                formatter: '{b}-{c}'
			            },
			            visualMap: {  
			                left: 'right',
			                min: 0,
			                max: 1,   
			                inRange: {
			                	color: ['#10A0D4', '#9DE292', '#3E7AB8', '#FAC940', '#FE7701']
			                },
			                text:['High','Low'],           // 文本，默认为数值文本
			                calculable: true
		                    
		                },  
			            series: [
			                {
			                    type: 'map',
			                    mapType: 'china', // 自定义扩展图表类型
			                    zoom: 1.2,   //这里是关键，一定要放在 series中
			                    itemStyle:{
			                        //normal:{label:{show:true}},
			                        emphasis:{label:{show:true}}
			                    },
			                    data:self.data,
			                }
			            ]
			        };
		        myChart.setOption(option);
	    	},
	  }
	})