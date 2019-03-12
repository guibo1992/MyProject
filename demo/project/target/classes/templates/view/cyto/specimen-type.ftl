<head>
  <title>标本管理</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 标本类型
	    <small> 标本管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 标本类型</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			<div class="form-group">
			  <div id="status" class="btn-group active-primary" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="1" autocomplete="off"> 正常
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="0" autocomplete="off"> 无效
			    </label>
			  </div>
			</div>
			<div class="form-group margin-l-5">
			  <button class="btn btn-success" id="add"> <i class="fa fa-plus"></i> 新建标本类型</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="typeModal" role="dialog" aria-labelledby="typeModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="typeForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="typeModalLabel">标本类型</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control" value="" />
	        
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="typeHead" class="control-label col-md-3">表示符号:</label>
                  <div class="col-md-8">
                    <input type="text" name="typeHead" id="typeHead" placeholder="表示符号" class="form-control uppercase" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-md-3">名称:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="name" placeholder="类型名称" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="caseTemplate" class="control-label col-md-3">分析模板:</label>
                  <div class="col-md-8">
                    <input type="text" name="caseTemplate" id="caseTemplate" placeholder="分析Case模板" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="remark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="remark" rows="4" placeholder="标本类型备注,可选填" class="form-control" maxlength="100" role="data-remain"></textarea>
                  </div>
                </div>
                <div class="checkbox checkbox-primary margin-bottom-3">
	               	<input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" value="姓名">
	                <label for="c_1">病例号</label>
	            </div>
	            <div class="checkbox checkbox-primary margin-bottom-3">
	               	<input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" value="日期">
	                <label for="c_1">日期</label>
	            </div>
	            <div class="checkbox checkbox-primary margin-bottom-3">
	               	<input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" value="姓名">
	                <label for="c_1">门诊号</label>
	            </div>
	            <div class="clearfix"></div>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> Cancel</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-save"></i> Save</button>
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
  <script src="/static/js/viewjs/specimen-type.js"></script>
</imports>