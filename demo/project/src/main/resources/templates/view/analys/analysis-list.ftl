<head>
  <title>核型分析</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/process.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 核型分析
	    <small> 分析报告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	     <li> 分析报告管理</li>
	    <li class="active"> 核型分析</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group margin-r-5" >
	          <select id="type" class="form-control chosen-select md" data-placeholder="标本类型">
	            <option value=""></option>
              	<option value="">全部 </option>
                <#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
	        <div class="form-group active-default">
			  <div id="status" class="btn-group" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="" autocomplete="off"> 全部
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="0" autocomplete="off"> 等待分析结果
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="1" autocomplete="off"> 分析已完成
			    </label>
			  </div>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="resultModal" role="dialog" aria-labelledby="resultModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="resultModalLabel">分析结果：</h4>
	      </div>
		  <div class="modal-body">
	        <div class="form-group fa-2">
	           
	          <div name="analysisResult"></div>
	        </div>
	        <div class="form-group">
	          <div class="row">
	            <div class="col-xs-6">
              	  <img name="analysisMetImg" class="thumbnail max-width-100" src="" />
	            </div>
	            <div class="col-xs-6">
              	  <img name="analysisKarImg" class="thumbnail max-width-100" src="" />
              	</div>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-success" data-dismiss="modal" type="button"><i class="fa fa-times"></i> 关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="templateModal" role="dialog" aria-labelledby="templateModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="templateModalLabel">选择报告模板</h4>
	      </div>
		  <div class="modal-body">
	        <div class="form-group">
              <label class="control-label col-md-3">报告模板: </label>
              <div class="col-md-8">
                <select id="templateId" class="chosen-select"></select>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-save"></i> 确定</button>
	      </div>
	    </div>
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
  <script src="/static/js/viewjs/karyotype-analysis.js"></script>
</imports>