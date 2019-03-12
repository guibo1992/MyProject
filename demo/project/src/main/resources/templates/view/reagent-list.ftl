<head>
  <title>试剂管理</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <#-- <link href="/static/css/jquery.autocompleter.css" rel="stylesheet"> -->
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 试剂列表
	    <small> 试剂管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 试剂列表</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			<div class="form-group">
			  <button class="btn btn-success" id="addReagent"> <i class="fa fa-plus"></i> 添加试剂</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="reagentModal" role="dialog" aria-labelledby="reagentModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <form id="reagentForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="reagentModalLabel">试剂信息</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control"/>
	       
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="name" class="control-label col-md-3">名称:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="name" placeholder="试剂名称" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="qtyUnit" class="control-label col-md-3">单位:</label>
                  <div class="col-md-8">
                    <input type="text" name="qtyUnit" id="qtyUnit" placeholder="数量单位" class="form-control" />
                  </div>
                </div>
		        <div class="form-group">
                  <label for="type" class="control-label col-md-3">类型:</label>
                  <div class="col-md-8">
                    <input type="text" id="type" name="type" placeholder="试剂类型" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="spec" class="control-label col-md-3">规格:</label>
                  <div class="col-md-8">
                    <input type="text" name="spec" id="spec" placeholder="试剂规格" rows="5" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="warnStock" class="control-label col-md-3">预警库存:</label>
                  <div class="col-md-8">
		            <input type="text" name="warnStock" id="warnStock" class="form-control" placeholder="预警库存数" >
                  </div>
                </div>
                <div class="form-group">
                  <label for="storeCondition" class="control-label col-md-3">存储方式:</label>
                  <div class="col-md-8">
                    <input type="text" name="storeCondition" id="storeCondition" placeholder="试剂存储要求条件" class="form-control" />
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="remark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="remark" placeholder="备注" rows="3" class="form-control" role="data-remain" maxlength="200"></textarea>
                  </div>
                </div>
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
  <#-- <script src="/static/js/jquery.autocompleter.min.js"></script> -->
  <script src="https://cdn.bootcss.com/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
  <script src="/static/js/viewjs/reagent.js"></script>
</imports>