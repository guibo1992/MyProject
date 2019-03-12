<head>
  <title>数据统计</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 数据统计
	    <small> 统计分析</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 数据统计</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	      	<div class="form-group">
	      	  <select class="chosen-select" name="" id="year" data-placeholder="年份">
	      	    <#list 2018..2010 as y>
	      	    <option value="${y}">${y}年</option>
	      	    </#list>
	      	  </select>
	      	</div>
	      	<div class="form-group">
	      	  <select class="chosen-select" id="quarter" data-placeholder="季度">
	      	    <option value=""></option>
	      	    <option value="">全季度</option>
	      	    <#list 1..4 as y>
	      	    <option value="${y}">第${y}季度</option>
	      	    </#list>
	      	  </select>
	      	</div>
	      	<div class="form-group">
	      	  <select class="chosen-select" id="month" data-placeholder="月份">
	      	    <option value=""></option>
	      	    <option value="">全年</option>
	      	    <#list 0..11 as y>
	      	    <option value="${y}">${y + 1}月份</option>
	      	    </#list>
	      	  </select>
	      	</div>
	      </div>
	      <table id="table"></table>
	    </div>
	    
	    <div class="box-body">  
	    
	      <div class="row" >
	        <div class="col-lg-6" >
	          <div id="analysisCount" style="width: 100%; height: 400px;"></div>
	        </div>
	        <div class="col-lg-6" >
	          <div id="reportCount" style="width: 100%; height: 400px;"></div>
	        </div>
	        <div class="col-lg-6" >
	          <div id="reportRejectCount" style="width: 100%; height: 400px;"></div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>

  </div>
</body>
<imports>
  <script src="/static/js/jquery.form.min.js"></script>
  <script src="/static/js/bootstrap-table.min.js"></script>
  <script src="/static/js/bootstrap-table-zh-CN.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/echarts.common.min.js"></script>
  <script src="/static/js/viewjs/work-statistics.js"></script>
</imports>