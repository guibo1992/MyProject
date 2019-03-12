<head>
  <title>公告列表</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/fileinput.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 公告列表
	    <small> 公告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 公告列表</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			 <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="startDate" class="form-control" placeholder="开始时间" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="endDate" class="form-control" placeholder="结束时间" >
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
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/fileupload/fileinput.min.js"></script>
  <script src="/static/js/viewjs/message-all.js"></script>
</imports>