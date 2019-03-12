<head>
  <title>快速打印</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 快速打印
	    <small> 打印管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 快速打印</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group">
	          <div class="input-group date" id="startDate">
	            <input type="text" class="form-control" placeholder="开始日期" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	          <div class="input-group date" id="endDate">
	            <input type="text" class="form-control" placeholder="结束日期" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group margin-r-10" style="width:150px;" >
	          <select id="type" class="form-control chosen-select col-md-12" data-placeholder="标本类型">
              	<option value="">全部</option>
              	<#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
	        <div class="form-group margin-l-5">
			  <button class="btn btn-success" id="print"> <i class="fa fa-print"></i> 快速打印</button>
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
  <script src="/static/js/viewjs/slide-print.js"></script>
</imports>