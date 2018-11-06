	Vue.component('operation', {
		/* data(){
			return{
				business_data:{
		        	//x_axis:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		        	//browse_data:[2.0, 5, 7.0, 23, 25, 76, 135, 162, 32, 20, 6, 3],
		        	//visit_data:[2, 5, 9, 26, 28,70, 175, 182, 48, 18, 6, 2],
		        },
			}
		}, */
		props:["x_axis","browse_data","visit_data","field","width","id"],
	  template: '<div :id="id" :style="{width:width+\'px\'}" style="height:100%;background-color:#fff;"></div>',
	  mounted(){
		  this.renderOperationalData();
	  },
	  methods:{
		  renderOperationalData(){
	    		var self = this;
	    		var operationChart = echarts.init(document.getElementById(self.id));
	    		operationOption = {
	    			backgroundColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: 'rgb(34,17,52)'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: 'rgb(49,36,80)' 
                  }], false),
				
//	    			title:{
//	    				text:'2017年浏览量及访问量数据统计',
//	    				x:'center',
//	    				y:'bottom',
//	    				textStyle:{
//	    					color:'#fff',
//	    					fontSize:'12px',
//	    				}
//	    			},
	    		    tooltip: {
	    		        trigger: 'axis',
	    		        axisPointer: {
	    		            type: 'cross',
	    		            crossStyle: {
	    		                color: '#999'
	    		            }
	    		        }
	    		    },
	    		    legend: {
	    		        data:self.field,
	    		        //orient:'vertical',
	    		        x:'center',
	    		        y:'20px',
	    		        textStyle: {  
                            color: '#fff'          //legend字体颜色 
                        }
	    		    },
	    		    xAxis: [
	    		        {
	    		            type: 'category',
	    		            data: self.x_axis,
	    		            axisPointer: {
	    		                type: 'shadow',
	    		            },
	    		            axisLine:{
	    		            	lineStyle:{
	    		            		type:'solid',
	    		            		color:'rgb(39,100,159)',
	    		            	}
	    		            },
	    		            axisLabel: {
	    		                formatter: '{value}',
	    		                textStyle: {
                                    color: '#fff',
                                }
	    		            },
	    		            // 控制网格线是否显示
                        splitLine: {
                                show: true, 
                                //  改变轴线颜色
                                lineStyle: {
                                    // 使用深浅的间隔色
                                    color:'rgb(64,74,120)',
                                }                            
                        },
	    		        }
	    		    ],
	    		    grid: {
						left: '3%',
						right: '4%',
						bottom:30,
						containLabel: true,
						y2: 8
					},
	    		    yAxis: [
	    		        {
	    		            type: 'value',
	    		            name: '',
	    		            min: 0,
	    		            max: 200,
	    		            interval: 50,
	    		            axisLine:{
	    		            	lineStyle:{
	    		            		type:'solid',
	    		            		color:'rgb(39,100,159)',
	    		            	}
	    		            },
	    		            axisLabel: {
	    		                formatter: '{value}',
	    		                textStyle: {
                                    color: '#fff',
                                }
	    		            },
	    		            splitLine: {
                                show: true, 
                                //  改变轴线颜色
                                lineStyle: {
                                    // 使用深浅的间隔色
                                    color:'rgb(64,74,120)',
                                }                            
                        },
	    		        }
	    		    ],
	    		    series: [
	    		        {
	    		            name:self.field[0],
	    		            type:'bar',
	    		            data:self.browse_data,
	    		            itemStyle:{
                              normal:{
                            	  //barBorderRadius:[5,5,0, 0],
                                  color:'rgba(180,69,81,1)',
                              },
                              emphasis:{
                            	  //barBorderRadius:[5,5,0, 0],
                              	  color:'rgba(180,69,81,1)',
                              }
                          },
	    		        },
	    		        {
	    		            name:self.field[1],
	    		            type:'bar',
	    		            data:self.visit_data,
	    		            itemStyle:{
	    		            	normal:{
	    		            		//barBorderRadius:[5,5,0, 0],
                                  color:'rgba(28,145,197,1)',
                              },
                              emphasis:{
                            	  //barBorderRadius:[5,5,0, 0],
                              	  color:'rgba(28,145,197,1)',
                              }
                          },
	    		        }
	    		    ],
	    		};

	    		operationChart.setOption(operationOption);
	    	},
	  }
	})