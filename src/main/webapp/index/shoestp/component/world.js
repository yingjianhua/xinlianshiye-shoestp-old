	Vue.component('world', {
		//props:["type","worldMapping","data"],
		props:{
			type:{
				type:String,
			},
			worldMapping:{
				type:Array,
			},
			data:{
				type:Array,
			},
			id:{
				type:String,
			},
			size:{
				type:Number,
			},
			width:{
				type:Number,
			},
			height:{
				type:Number,
			},
			dataImages:
			{
				type:Array, 
			},
			dataColor:
			{
				type:Array
				
			}
		},
	  template: '<div :style="{width:width+\'px\',height:height+\'px\'}"  style="margin:0 auto;" :id="id"></div>',
	  mounted(){
		  this.renderConfigure();
	  },
	  methods:{
		  renderConfigure(){
	    		var self = this;


				var geoCoordMap;
		               if(self.type == 'world'){
			               	geoCoordMap = {
			               			'俄罗斯':[105.318756,61.52401],
					                   '西班牙':[-3.74922,40.46366700000001],
					                   '意大利':[12.56738,41.87194],
					                   '罗马尼亚':[24.96676,45.943161],
					                   '匈牙利':[19.503304,47.162494],

			               };
		               }else{
			               	geoCoordMap = {
					           	'浙江':[120.200356,30.251585],
					           	'广东':[113.24483,23.1539],
					           	'四川':[104.077487,30.653129],
					           	'福建':[119.305896,26.080901]
					           }
		               }
		               var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }

    return res;
};


		        var myChart = echarts.init(document.getElementById(self.id)); //这里是为了获得容器所在位置
		        var option = {
			            /*tooltip: {
			                trigger: 'item',
			                formatter: function(a,b,c){
			                	var name = '';
			                	try{
			                		name = a.data.name;
			                		return name + " : " + Math.floor(a.data.value* 100)/100  + "%";
			                	}catch(e){
			                		return '该地暂无数据';
			                	}

			                }
			            },*/
			            visualMap: {
			            	show:false,
			                left: 'right',
			                min: 99,
			                max: 600,	
			                inRange: {
			                	 color: ['#b52acc','#71fd21','#54f5ff','#fac942','#ff5160','#232F65'],
											},
			                text:['High','Low'],           // 文本，默认为数值文本
			                calculable: true

		                },
		                geo: {
					        map:self.type,
					        label: {
					            emphasis: {
					                show: false
					            }
					        },
					        roam: false,
					        itemStyle: {
					            normal: {
					                areaColor: '#050927',
					                borderColor: '#111'
					            },
					            emphasis: {
					                areaColor: '#212F67'
					            }
					        }
					    },
							series :(function (){
								var series = [];
								var dataName=[];
								dataName=convertData(self.data);
								for (var i = 0; i <self.data.length; i++) {
									series.push({
										name:i,
										type: 'scatter',
										symbolOffset:[0,'-70%'],
										// symbol:self.dataImages[i],
										symbol:"path://M536.217 90.69c-168.027 0-302.524 134.497-302.524 302.524 0 120.961 87.056 215.095 181.191 336.055 32.786 45.949 58.866 91.9 92.023 150.268 1.74 3.105 3.974 5.961 6.582 8.321 5.588 5.091 12.047 9.438 22.726 9.438v0c11.425 0 18.007-4.844 23.844-10.432 1.986-1.862 3.726-4.098 5.217-6.334 33.158-52.409 59.362-98.481 92.273-144.556 94.135-127.666 181.191-221.801 181.191-342.761 0-168.027-134.497-302.524-302.524-302.524v0zM536.217 494.055c-53.774 0-100.842-40.362-100.842-100.842s40.362-100.842 100.842-100.842c53.774 0 100.842 40.362 100.842 100.842 0 53.774-40.362 100.842-100.842 100.842v0z;",
										coordinateSystem: 'geo',
										data:[dataName[i]],
										symbolSize: function (val) {
											return val[2] /10;
											// return [val[2] /16,val[2] /12];
										},
										symbolKeepAspect :true,
										label: {
											normal: {
												formatter: '{b}',
												position: 'right',
												show: false
											},
											emphasis: {
												show: true
											}
										},
										itemStyle: {
											// normal: {
											// 	color: '#ddb926',
											// 	opacity : 1
											// },
											shadowColor :'#f60',
											opacity : 1,
											borderColor :"rgba(0,0,0,.25)"
										},
										lineStyle:{
											color: "#f60"
										},
										zlevel: 11

									},
						        {
						            name: 'Top '+i,
						            type: 'effectScatter',
						            symbol:'circle',
						            coordinateSystem: 'geo',
						            data:[dataName[i]],
						            symbolSize: function (val) {
						                return val[2] / 10;
						            },
						            showEffectOn: 'render',
						            rippleEffect: {
						                brushType: 'fill'
						            },
						            hoverAnimation: true,
						            label: {
						                normal: {
						                    formatter: '{b}',
						                    position: 'right',
						                    show: true
						                }
						            },
						            itemStyle: {
						                normal: {
						                    color:[],
						                    shadowBlur: 10,
						                    shadowColor: '#333'
						                }
						            },
						            zlevel: i
							    			      })
							    			}
							    	return series;
	    	})()
			        };
		        myChart.setOption(option);
	    	},
	  }
	})
