
function keepTwoDecimalFull(num) {
	 var result = parseFloat(num);
	 if (isNaN(result)) {
		 return null;
	 }
	 result = Math.round(num * 100) / 100;
	 var s_x = result.toString();
	 var pos_decimal = s_x.indexOf('.');
	 if (pos_decimal < 0) {
		 pos_decimal = s_x.length;
		 s_x += '.';
	 }
	 while (s_x.length <= pos_decimal + 2) {
		 s_x += '0';
	 }
	 return s_x;
}


$(function() {
	'use strict';
	
	var $table = $('#table');
	var analysisCountChart = echarts.init(document.getElementById('analysisCount'));
	var reportCountChart = echarts.init(document.getElementById('reportCount'));
	var reportRejectCountChart = echarts.init(document.getElementById('reportRejectCount'));
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 20
	});
	
	$('#toolbar .chosen-select').on('change', function() {
		$table.bootstrapTable('refresh');
		refreshData();
	});
	
	$('#quarter').on('change', function() {
		var val = $(this).val();
		
		if (!val || val == '') {
			$('#month > option').removeClass('hide');
			$('#month').trigger('chosen:updated');
			return false;
		}
		var min = parseInt(val * 3 - 2) - 1, max = val * 3;
		
		$('#month > option').each(function() {
			var month = $(this).attr('value');
			if (!month || month == '') {
				$(this).addClass('hide');
				return true;
			}
			if (month < min || month >= max) {
				$(this).addClass('hide');
			} else {
				$(this).removeClass('hide');
			}
		});
		$('#month').trigger('chosen:updated');
		
		refreshData();
	});
	
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			year: $('#year').val(),
			quarter: $('#quarter').val(),
			month: $('#month').val()
		}
	}
	
	var reportPassedRateFormatter = function(value, row, index) {
		var percent = keepTwoDecimalFull((row.reportedCount - row.reportRejectCount) * 100 / row.reportedCount);
		if (percent) {
			return  percent + '%';
		}
	}

	$table.bootstrapTable({
		url : '/work/summary/list',
		search : true,
		showRefresh : true,
		showToggle : false,
		showColumns : false,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		pageSize : 20,
		pageList: [20, 50],
		toolbar : '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'laboratorian.id',
			title : '#'
		}, {
			field : 'laboratorian.name',
			title : '分析员'
		}, {
			field : 'analysisSpecimenCount',
			title : '分析标本数'
		}, 
//		{
//			field : 'reportedEfficiency',
//			title : '工作效率'
//		}, 
		{
			field : 'reportedCount',
			title : '总报告数量'
		}, {
			field : 'reportRejectCount',
			title : '审核驳回数'
		}, {
			field : '',
			title : '报告通过率',
			formatter: reportPassedRateFormatter
		} ]
	});
	
	
	var option = {
	    title : { x:'center' },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	type: 'scroll',
	        orient: 'vertical',
	        left: 'left',
	        right: 10,
	        top: 20,
	        bottom: 20,
	    },
	    series : [{
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '50%'],
	    }]
	};
	
	
	var refreshData = function(){
		var legendData = [];
	    var seriesData = [];
	    
	    if (analysisCountChart)
    		analysisCountChart.clear();
    	if (reportCountChart)
    		reportCountChart.clear();
    	if(reportRejectCountChart) 
    		reportRejectCountChart.clear();
	    
	    $.get('/work/summary/analysis-count', {year: $('#year').val(), quarter: $('#quarter').val(), month: $('#month').val() }, function(data) {
	    	$.each(data, function(i, scs) {
	    		legendData.push(scs.laboratorian.name);
		    	seriesData.push({
		    		name: scs.laboratorian.name,
		    		value: scs.analysisSpecimenCount
		    	});
	    	});
	    	option.title.text = "分析工作量";
	    	option.title.subtext = "";
	    	option.tooltip.formatter = "{b} <br/>分析标本数: {c} ({d}%)"
	    	option.legend.data = legendData;
	    	option.series[0].name = '分析人员';
	    	option.series[0].data = seriesData;
	    	analysisCountChart.setOption(option);
	    	
	    	legendData = [];
	        seriesData = [];
	    	$.each(data, function(i, scs) {
	    		legendData.push(scs.laboratorian.name);
		    	seriesData.push({
		    		name: scs.laboratorian.name,
		    		value: scs.reportedCount
		    	});
	    	});
	    	option.title.text = "分析报告总数";
	    	option.title.subtext = "";
	    	option.tooltip.formatter = "{b} <br/>分析报告数: {c} ({d}%)"
	    	option.legend.data = legendData;
	    	option.series[0].data = seriesData;
	    	reportCountChart.setOption(option);
	    	
	    	legendData = [];
	        seriesData = [];
	    	$.each(data, function(i, scs) {
	    		legendData.push(scs.laboratorian.name);
		    	seriesData.push({
		    		name: scs.laboratorian.name,
		    		value: scs.reportRejectCount
		    	});
	    	});
	    	option.title.text = "分析报告驳回数";
	    	option.title.subtext = "";
	    	option.tooltip.formatter = "{b} <br/>报告驳回数: {c} ({d}%)"
	    	option.legend.data = legendData;
	    	option.series[0].data = seriesData;
	    	
	    	reportRejectCountChart.setOption(option);
	    	
		});
	}
    
    window.addEventListener("resize", function(){   
    	if (analysisCountChart)
    		analysisCountChart.resize();
    	if (reportCountChart)
    		reportCountChart.resize();
    	if(reportRejectCountChart) 
    		reportRejectCountChart.resize();
    });
    
    refreshData();
	
});