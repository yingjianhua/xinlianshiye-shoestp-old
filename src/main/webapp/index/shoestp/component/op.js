	Vue.component('pie3', {
	  props:["id","browse_data","browse_name","field","title"],
	  template: '<div :id="id" style="height:100%;width:100%;"></div>',
	  mounted(){
		  this.renderOperationalData();
	  },
	  methods:{
		  renderOperationalData(){
	    		var self = this;
	    		var operationChart = echarts.init(document.getElementById(self.id));
	    		/*var _zr = operationChart.getZr();
				 _zr.add(new echarts.graphic.Image({
				     style: {            
				   x: _zr.getWidth() / 2.4,
				   y: _zr.getHeight() / 2.1,
				   color: '#666', 
				   image: './images/logo-small.png',
				   textAlign: 'center', 
				   textFont : 'bold 20px verdana'
				   }}  
				    ));	   */ 	
	    		var dataStyle = {
	    			    normal: {
	    			        label: {show:false},
	    			        labelLine: {show:false}
	    			    }
	    			};
	    			var placeHolderStyle = {
	    			    normal : {
	    			        color: 'rgba(0,0,0,0)',
	    			        label: {show:false},
	    			        labelLine: {show:false}
	    			    },
	    			    emphasis : {
	    			        color: 'rgba(0,0,0,0)'
	    			    }
	    			};
	    		option =  {
	    			backgroundColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: 'rgb(21,27,62)'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: 'rgb(41,53,105)' 
                  }], false),
	    			color:['#83AAF0','#FF7F50','#87CEFA','#DA70D6','#32CD32'],
//	    			    title: {
//	    			        text:self.title[0],
//	    			        subtext:'',
//	    			        x:'10px',
//	    			        y:'5px',
//	    			        itemGap: 20,
//	    			        textStyle : {
//					            color:'#ccc',
//	    			        }
//	    			    },
	    			    tooltip : {
	    			        show: true,
	    			        formatter: "{a} <br/>{b} :{d}%"
	    			    },
//	    			    toolbox: {
//	    			        show : true,
//	    			        feature : {
//	    			            mark : {show: true},
//	    			            //dataView : {show: true, readOnly: false},
//	    			            magicType : {
//	    			                show: true, 
//	    			                type: ['pie', 'funnel'],
//	    			                option: {
//	    			                    funnel: {
//	    			                        x: '25%',
//	    			                        width: '50%',
//	    			                        funnelAlign: 'center',
//	    			                        max: 1548
//	    			                    }
//	    			                }
//	    			            },
//	    			            restore : {show: true},
//	    			            saveAsImage : {show: true}
//	    			        }
//	    			    },
	    			    legend: {
	    			        x:'center',
		    		        y :'168px',
	    			        itemGap:12,
	    			        itemWidth:15,
	    			        data:[{name:self.browse_name[0],
	    			        	icon:'rectangle',
	    			        	textStyle:{
	    			        		fontSize:8,
	    			        		fontWeight:'bolder',
	    			        		color:'#fff'}
	    			        	},{
	    			        		name:self.browse_name[1],
	    			        		icon:'rectangle',
	    			        		textStyle:{
	    			        			fontSize:8,
	    			        			fontWeight:'bolder',
	    			        			color:'#fff'}
	    			        		},{
	    			        			name:self.browse_name[2],
	    			        			icon:'rectangle',
	    			        			textStyle:{
	    			        				fontSize:8,
	    			        				fontWeight:'bolder',
	    			        				color:'#fff'}
	    			        			}
	    			        		]
	    			    },
	    			    series : [
	    			        {
	    			            name:self.field[0],
	    			            type:'pie',
	    			            clockWise:false,
	    			            radius : [32+15, 38.4+18],
	    			            center:['50%','40%'],
	    			            itemStyle : dataStyle,
	    			            data:[
	    			                {
	    			                    value:self.browse_data[0],
	    			                    name:self.browse_name[0]
	    			                },
	    			                {
	    			                    value:32,
	    			                    name:'invisible',
	    			                    itemStyle : placeHolderStyle
	    			                }
	    			            ]
	    			        },
	    			        {
	    			            name:self.field[0],
	    			            type:'pie',
	    			            clockWise:false,
	    			            radius : [25.6+12, 32+15],
	    			            center:['50%','40%'],
	    			            itemStyle : dataStyle,
	    			            data:[
	    			                {
	    			                    value:self.browse_data[1], 
	    			                    name:self.browse_name[1]
	    			                },
	    			                {
	    			                    value:71,
	    			                    name:'invisible',
	    			                    itemStyle : placeHolderStyle
	    			                }
	    			            ]
	    			        },
	    			        {
	    			            name:self.field[0],
	    			            type:'pie',
	    			            clockWise:false,
	    			            radius : [19.2+9, 25.6+12],
	    			            center:['50%','40%'],
	    			            itemStyle : dataStyle,
	    			            data:[
	    			                {
	    			                    value:self.browse_data[2], 
	    			                    name:self.browse_name[2]
	    			                },
	    			                {
	    			                    value:97,
	    			                    name:'invisible',
	    			                    itemStyle : placeHolderStyle
	    			                }
	    			            ]
	    			        }
	    			    ]
	    			};
	    		operationChart.setOption(option);
	    	},
	  }
	})