<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sdmenu.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="echarts/echarts.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
<script type="text/javascript" language="javascript">
	$(function(){
		var datetime=new Date();
		var sendTime=datetime.toLocaleDateString();
		$.post("getAllCount2",{"sendTime":sendTime},function(data){
			var names = [];
			
			for(var i=0;i<data.length;i++){	
				names.push(data[i].name);
				writeBmp(names,data);				
			}
			
						
		},"json");
	});
	//封装
	function writeBmp(names,data){
		require([
		    'echarts',
		    'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		],function(ec){
		    // 基于准备好的dom，初始化echarts图表
		    var myChart = ec.init(document.getElementById('main'));
		    
		    var option = {
		    	    title : {
		    	        text: '分理处金额统计图',
		    	        subtext: '纯属虚构',
		    	        x:'center'
		    	    },
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	    },
		    	    legend: {
		    	        orient : 'vertical',
		    	        x : 'left',
		    	        data:names
		    	    },
		    	    toolbox: {
		    	        show : true,
		    	        feature : {
		    	            mark : {show: true},
		    	            dataView : {show: true, readOnly: false},
		    	            magicType : {
		    	                show: true, 
		    	                type: ['pie', 'funnel'],
		    	                option: {
		    	                    funnel: {
		    	                        x: '25%',
		    	                        width: '50%',
		    	                        funnelAlign: 'left',
		    	                        max: 1548
		    	                    }
		    	                }
		    	            },
		    	            restore : {show: true},
		    	            saveAsImage : {show: true}
		    	        }
		    	    },
		    	    calculable : true,
		    	    series : [
		    	        {
		    	            name:'访问来源',
		    	            type:'pie',
		    	            radius : '55%',
		    	            center: ['50%', '60%'],
		    	            data:data
		    	        }
		    	    ]
		    	};
		    	                    
		    	                                                    
		    
		    // 为echarts对象加载数据 
		    myChart.setOption(option); 
		});
	}
		
</script>
</head>
<body>
<div class="right"  id="mainFrame">
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">统计</a> <span class="divider">/</span>
  分理处排行
</ul>
<div class="title_right"><strong>分理处发货排行榜</strong></div>
</br>
     </div>     
     </div>


<div id="main" style="height: 400px; width: 600px;"></div>

</body>
</html>