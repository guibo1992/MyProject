<head>
  <title>分析报告结论</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 报告结论
	    <small> 报告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 报告结论</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	      	<div class="form-group" style="width:160px">
	          <select id="type" class="form-control chosen-select col-md-12" data-placeholder="类型">
              	<option value="">全部</option>
              	<#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
			<div class="form-group margin-l-5">
			  <button class="btn btn-success" id="add"> <i class="fa fa-plus"></i> 添加报告结论</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="conclusionModal" role="dialog" aria-labelledby="conclusionModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="conclusionForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="conclusionModalLabel">报告结论</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control" value="" />
	        
			<div class="row"> 
		      <div class="col-md-12">
                <div class="form-group">
                  <label for="typeId" class="control-label col-md-3">类型：</label>
                  <div class="col-md-8">
                    <select id="typeId" name="typeId" class="form-control chosen-select" data-placeholder="类型">
		              <option value=""></option>
		              <#list typeList as type>
                	  <option value="${type.id}">${type.typeHead} ${type.name}</option>
                	  </#list>
		           	</select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="title" class="control-label col-md-3">标题：</label>
                  <div class="col-md-8">
                    <input type="text" name="title" id="title" placeholder="标题,50个字符以内" class="form-control" maxlength="50" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="conclusion" class="control-label col-md-3">结论：</label>
                  <div class="col-md-8">
                    <textarea name="conclusion" id="conclusion" rows="6" placeholder="结论" class="form-control" maxlength="250" role="data-remain"></textarea>
                  </div>
                </div>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-save"></i> 保存</button>
	      </div>
	    </form>
	  </div>
	</div>
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
  <script src="/static/js/viewjs/report-conclusion.js"></script>
</imports>