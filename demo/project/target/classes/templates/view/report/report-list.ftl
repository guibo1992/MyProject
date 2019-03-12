<head>
  <title>分析报告分析报告列表</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/process.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 分析报告列表
	    <small> 报告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 报告管理</li>
	    <li class="active"> 分析报告</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="startDate" class="form-control" autocomplete="off" placeholder="开始时间" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="endDate" class="form-control" autocomplete="off" placeholder="结束时间" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group" style="width:160px">
	          <select id="type" class="form-control chosen-select" data-placeholder="标本类型">
	            <option value=""></option>
              	<option value="">全部 </option>
                <#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
	        <div class="form-group active-default margin-l-5">
			  <div id="reportStatus" class="btn-group" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="WAIT_AUDIT" autocomplete="off"> 审核中
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="AUDIT_REJECT" autocomplete="off"> 已驳回
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="AUDIT_PASSED" autocomplete="off"> 已审核
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="" autocomplete="off"> 全部
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
  <script src="/static/js/viewjs/report-list.js"></script>
</imports>