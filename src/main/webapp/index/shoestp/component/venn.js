	Vue.component('sex', {
	  template: '<div id="sex" style="height:100%;background-color:#000;"></div>',
	  mounted(){
		  this.renderBusinessOppData();
	  },
	  methods:{
			renderBusinessOppData(){
			var self = this;
			var operationChart = echarts.init(document.getElementById('sex'));
			option = {
				    title : {
				        text: '男女比例',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{b}: {c}"
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : false,
				    series : [
				        {
				            name:'访问',
				            type:'venn',
				            itemStyle: {
				                normal: {
				                    label: {
				                        show: true,
				                        textStyle: {
				                            fontFamily: 'Arial, Verdana, sans-serif',
				                            fontSize: 16,
				                            fontStyle: 'italic',
				                            fontWeight: 'bolder'
				                        }
				                    },
				                    labelLine: {
				                        show: false,
				                        length: 10,
				                        lineStyle: {
				                            // color: 各异,
				                            width: 1,
				                            type: 'solid'
				                        }
				                    }
				                },
				                emphasis: {
				                    color: '#cc99cc',
				                    borderWidth: 3,
				                    borderColor: '#996699'
				                }
				            },
				            data:[
				                {value:100, name:'访问'},
				                {value:50, name:'咨询'},
				                {value:20, name:'公共'}
				            ]
				        }
				    ]
				};
	    		operationChart.setOption(option);
			}
	  }
	})