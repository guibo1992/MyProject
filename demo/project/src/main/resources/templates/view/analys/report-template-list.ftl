<head>
  <title>分析报告模板</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 分析报告模板
	    <small> 报告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 报告管理</li>
	    <li class="active"> 分析报告模板</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group" style="width:160px">
	          <select id="type" class="form-control chosen-select" data-placeholder="类型">
              	<option value="">全部</option>
              	<#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
	        <div class="form-group margin-l-5">
			  <div id="status" class="btn-group active-primary" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="1" autocomplete="off"> 正常
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="0" autocomplete="off"> 失效
			    </label>
			  </div>
			</div>
			<div class="form-group">
			  <button class="btn btn-success col-md-offset-1" id="addTemplate"> <i class="fa fa-plus"></i> 新建报告模板</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
  </div>
</body>

<imports>
  <script src="/static/js/bootstrap-table.min.js"></script>
  <script src="/static/js/bootstrap-table-zh-CN.min.js"></script>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/viewjs/report-template-list.js"></script>
</imports>