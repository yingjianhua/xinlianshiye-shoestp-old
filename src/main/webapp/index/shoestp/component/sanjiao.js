	Vue.component('age', {
	  props:["browse_data"],
	  template: '<div id="age" style="height:100%;background-color:#fff;"></div>',
	  mounted(){
		  this.renderOperationalData();
	  },
	  methods:{
		  renderOperationalData(){
	    		var self = this;
	    		var operationChart = echarts.init(document.getElementById('age'));
	    		option = {
	    			backgroundColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: 'rgb(21,27,62)'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: 'rgb(41,53,105)' 
                  }], false),
	    			    /*title: {
	    			        text: '年龄分析',
	    			        subtext: ''
	    			    },*/
	    			    tooltip: {
	    			        trigger: 'item',
	    			        formatter: function(a,b,c){
			                	return a.data.name + " : " + Math.floor(a.data.value* 100) + "%";
			                }
	    			    },
	    			    /*toolbox: {
	    			        feature: {
	    			            dataView: {readOnly: false},
	    			            restore: {},
	    			            saveAsImage: {}
	    			        }
	    			    },*/
	    			    legend: {
	    			        data: ['30岁~40岁','>40岁','<30岁'],
	    			        x:'center',
	    			        y:'bottom',
	    			        textStyle:{
    			                fontSize:8,
    			                fontWeight:'bolder',
    			                color:'#cccccc'
    			            },
	    			    },
	    			    calculable: true,
		    			    grid: {
							left: '3%',
							
							bottom:30,
							containLabel: true,
							y2: 8
						},
	    			    series: [
	    			             {
	    			                    name:'金字塔',
	    			                    type:'funnel',
	    			                    x : '0',
	    			                    y:'0',
	    			                    left:70,
	    			                    top:20,
	    			                    sort : 'ascending',
	    			                    itemStyle: {
	    			                    	normal : {
	    			                    	label : {show:false},
	    			                    	labelLine : {show : false},
	    			                    	}
	    			                    },
	    			                    data:[
	    			                          {value:0.6720, name:'30岁~40岁'},
								              {value:0.1872, name:'>40岁'},
								              {value:0.1408, name:'<30岁'},
	    			                    ]
	    			                }
	    			            ]
	    			};
	    		operationChart.setOption(option);
	    	},
	  }
	})