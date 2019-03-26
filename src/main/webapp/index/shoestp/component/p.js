	Vue.component('pie2', {
	  props:["flow_source","source_name_array","field","title","id"],
	  template: '<div :id="id" style="height:100%;"></div>',
	  mounted(){
		  this.renderOperationalData();
	  },
	  methods:{
		  renderOperationalData(){
	    		var self = this;
	    		var operationChart = echarts.init(document.getElementById(self.id));
	    		var _zr = operationChart.getZr();
	    		
				 /*_zr.add(new echarts.graphic.Text({
				     style: {   
					   x: _zr.getWidth() / 2.0,
					   y: _zr.getHeight() / 2.2,
					   text: '搜索引擎',
					   textAlign: 'center', 
					   textFont : 'bold 12px verdana'
					 }}  
				 ));	   
				    console.log(_zr)*/
	    		var flowSourceColorArray=['#4489FF','#5A67D7','#06DA83','#6DCFF6'];
	    		var flowSourceNameArray=self.source_name_array;
	    		option = {
	    			backgroundColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: 'rgb(21,27,62)'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: 'rgb(41,53,105)' 
                  }], false),
	    			color:['#83AAF0','#FF7F50','#87CEFA','#DA70D6','#32CD32'],
	    			    tooltip : {
	    			        trigger: 'item',
	    			        formatter: "{b} : {d}%"
	    			    },
//	    			    title : {
//	    			        text:self.title,
//	    			        x:'10px',
//	    			        y:'5px',
//	    			        textStyle : {
//					            color:'#ccc',
//	    			        }
//	    			    },
	    			    legend: {
	    			        x:'center',
	    			        y :'168px',
	    			        itemWidth:15,
	    			        data:[{name:flowSourceNameArray[0],icon:'rectangle',textStyle:{
	    			        		fontSize:8,
	    			        		fontWeight:'bolder',
	    			        		color:'#fff'}},{name:flowSourceNameArray[1],icon:'rectangle',textStyle:{
	    			        		fontSize:8,
	    			        		fontWeight:'bolder',
	    			        		color:'#fff'}},{name:flowSourceNameArray[2],icon:'rectangle',textStyle:{
	    			        		fontSize:8,
	    			        		fontWeight:'bolder',
	    			        		color:'#fff'}},{name:flowSourceNameArray[3],icon:'rectangle',textStyle:{
	    			        		fontSize:8,
	    			        		fontWeight:'bolder',
	    			        		color:'#fff'}}]
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
	    			    calculable : true,
	    			    graphic:{
	    			    	type:'text',
	    			    	style:{
	    			    		text:self.title,
	    			    		x: _zr.getWidth() / 2.4,
        						y: _zr.getHeight() / 2.8,
        						fill:'#fff',
        						font : 'bold 12px verdana'
	    			    	}
	    			    },
	    			    series : [
	    			        {
	    			            name:self.field,
	    			            type:'pie',
	    			            radius : ['50%', '60%'],
	    			            center:['50%','40%'],
	    			            itemStyle : {
	    			                normal : {
	    			                    label : {
	    			                        show : false
	    			                    },
	    			                    labelLine : {
	    			                        show : false
	    			                    }
	    			                }
	    			            },
	    			            data:[
	    			                {value:self.flow_source[0], name:flowSourceNameArray[0]},
	    			                {value:self.flow_source[1], name:flowSourceNameArray[1]},
	    			                {value:self.flow_source[2], name:flowSourceNameArray[2]},
	    			                {value:self.flow_source[3], name:flowSourceNameArray[3]},
	    			                {value:self.flow_source[4], name:flowSourceNameArray[4]}
	    			            ]
	    			        }
	    			    ]
	    			};
	    		operationChart.setOption(option);
	    	},
	  }
	})