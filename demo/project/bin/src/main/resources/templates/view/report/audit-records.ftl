<head>
  <title>分析报告审核记录</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 分析报告审核记录
	    <small> 审核管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 审核管理</li>
	    <li class="active"> 分析报告审核记录</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group">
	          <div class="input-group date" id="startDate">
	            <input type="text" class="form-control" autocomplete="off" placeholder="审核开始时间" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group">
	          <div class="input-group date" id="endDate">
	            <input type="text" class="form-control" autocomplete="off" placeholder="审核结束时间" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group active-default margin-l-5">
			  <div id="auditStatus" class="btn-group" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="" autocomplete="off"> 全部
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="true" autocomplete="off"> 审核通过
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="false" autocomplete="off"> 审核驳回
			    </label>
			    
			  </div>
			</div>
	      </div>
	      <table id="table"></table>
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
  <script src="/static/js/viewjs/report-audit-records.js"></script>
</imports>