	Vue.component('pie', {
	  props:["browse_data","browse_name","field","title"],
	  template: '<div id="pie" style="height:100%;width:100%;"></div>',
	  mounted(){
		  this.renderOperationalData();
	  },
	  methods:{
		  renderOperationalData(){
	    		var self = this;
	    		var operationChart = echarts.init(document.getElementById('pie'));
	    		/*var _zr = operationChart.getZr();
				 _zr.add(new echarts.graphic.Image({
				     style: {            
				   x: _zr.getWidth() / 2.4,
				   y: _zr.getHeight() / 2.1,
				   image: './images/logo-small.png',
				   }}  
				    ));	    */
	    		option = {
	    			backgroundColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: 'rgb(21,27,62)'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: 'rgb(41,53,105)' 
                  }], false),
	    			color:['#83AAF0','#FF7F50','#87CEFA','#DA70D6','#32CD32'],
//	    			    title : {
//	    			        text:self.title,
//	    			        subtext: '',
//	    			        x:'10px',
//	    			        y:'5px',
//	    			        textStyle:{//图例文字的样式
//					            color:'#ccc',
//					        }
//	    			    },
	    			    tooltip : {
	    			        trigger: 'item',
	    			        formatter: "{b} : {d}%"
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
	    			    grid: {show:'true',borderWidth:'0'},
	    			    legend: {
	    			        x :'center',
	    			        y :'168px',
	    			        itemWidth:15,
	    			        formatter:function (name) {
	    			            return name;
	    			        },
	    			        data:[
	    			            {
	    			            name:self.browse_name[0],
	    			            textStyle:{
	    			                fontSize:8,
	    			                fontWeight:'bolder',
	    			                color:'#cccccc'
	    			            },
	    			            icon:'rectangle'
	    			            }
	    			            ,
	    			            {
		    			            name:self.browse_name[1],
		    			            textStyle:{
		    			            fontSize:8,
		    			            fontWeight:'bolder',
		    			            color:'#cccccc'
		    			            },
		    			            icon:'rectangle'
		    			        }
	    			        ,
    			            { 
	    			            name:self.browse_name[2],
	    			            textStyle:{
	    			                fontSize:8,
	    			                fontWeight:'bolder',
	    			                color:'#cccccc'
    			             },
    			            icon:'rectangle'
    			             }
	    			        ,
    			            {
	    			        	name:self.browse_name[3],
	    			        	textStyle:{
    			                fontSize:8,
    			                fontWeight:'bolder',
    			                color:'#cccccc'
    			            },
    			            icon:'rectangle'
    			        }],
	    			    },
	    			    calculable : false,
	    			    series : (function (){
	    			        var series = [];
	    			        for (var i = 0; i < 6; i++) {
	    			            series.push({
	    			                name:self.field[0],
	    			                type:'pie',
	    			                center:['50%','40%'],
	    			                itemStyle : {normal : {
	    			                    label : {show : i>4,fontSize:5},
	    			                    labelLine : {show : i>4, length:12}
	    			                }},
	    			                radius : [i * 4 + 42, i * 4 + 43],
	    			                data:[
	    			                    {value:self.browse_data[0] , name:self.browse_name[0]},
	    			                    {value:self.browse_data[1],  name:self.browse_name[1]},
	    			                    {value:self.browse_data[2],  name:self.browse_name[2]},
	    			                    {value:self.browse_data[3],  name:self.browse_name[3]},
	    			                ]
	    			            })
	    			        }
//	    			        series[0].markPoint = {
//	    			            symbol:'emptyCircle',
//	    			            symbolSize:series[0].radius[0],
//	    			            effect:{show:true,scaleSize:12,color:'rgba(250,225,50,0.8)',shadowBlur:10,period:30},
//	    			            data:[{x:'50%',y:'50%'}]
//	    			        };
	    			        return series;
	    			    })()
	    			};
//	    			setTimeout(function (){
//	    			    var _ZR = operationChart.getZr();
//	    			    console.log(_ZR)
//	    			    //var TextShape = require('zrender/shape/Text');
//	    			    // 补充千层饼
//	    			    _ZR.addText(new echarts.graphic.Text({
//	    			        style : {
//	    			            x : _ZR.getWidth() / 2,
//	    			            y : _ZR.getHeight() / 2,
//	    			            color: '#666',
//	    			            text : '恶梦的过去',
//	    			            textAlign : 'center'
//	    			        }
//	    			    }));
//	    			    _ZR.addText(new echarts.graphic.Text({
//	    			        style : {
//	    			            x : _ZR.getWidth() / 2 + 200,
//	    			            y : _ZR.getHeight() / 2,
//	    			            brushType:'fill',
//	    			            color: 'orange',
//	    			            text : '美好的未来',
//	    			            textAlign : 'left',
//	    			            textFont:'normal 20px 微软雅黑'
//	    			        }
//	    			    }));
//	    			    _ZR.refresh();
//	    			}, 2000);	    		
	    		operationChart.setOption(option);
	    	},
	  }
	})