<head>
  <title>试剂领用记录</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<script  type="text/javascript">
	$(function{
		alert("aaa")
			
		});
	});
</script>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 试剂领用记录
	    <small> 试剂管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 试剂领用记录</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			<div class="form-group">
	          <div class="input-group">
	            <input type="text" id="startDate" class="form-control" placeholder="查询开始时间" autocomplete="off">
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="endDate" class="form-control" placeholder="查询结束时间" autocomplete="off">
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
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
  <script src="https://cdn.bootcss.com/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
  <script src="/static/js/viewjs/reagent-stock-out.js"></script>
</imports>