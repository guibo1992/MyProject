<head>
  <title>试剂管理</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 试剂库存
	    <small> 试剂管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 试剂库存</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <#-- 
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group">
	          <button class="btn btn-success" id="stockIn">试剂入库</button>
	        </div>
	        <div class="form-group">
	          <button class="btn btn-success" id="stockOut">试剂领用</button>
	        </div>
	      </div>
	       -->
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="reagentInModal" role="dialog" aria-labelledby="reagentInModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="reagentInForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="reagentInModalLabel">试剂入库</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="reagentId" value="" class="form-control" />
	        <input type="hidden" name="inOutType" value="1" />
	       
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="inBatchNo" class="control-label col-md-3">批次:</label>
                  <div class="col-md-8">
                    <input type="text" name="batchNo" id="inBatchNo" placeholder="入库批次" class="form-control" />
                  </div>
                </div>
		        <div class="form-group">
                  <label for="in-name" class="control-label col-md-3">试剂:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="in-name" placeholder="试剂名称" class="form-control" disabled/>
                  </div>
                </div>
		        <div class="form-group">
                  <label for="in-quantity" class="control-label col-md-3">数量:</label>
                  <div class="col-md-8">
                    <input type="text" id="in-quantity" name="quantity" placeholder="入库数量" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="in-remark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="in-remark" placeholder="备注" rows="3" class="form-control" role="data-remain" maxlength="200"></textarea>
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
	
	<div class="modal fade" id="reagentOutModal" role="dialog" aria-labelledby="reagentOutModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="reagentOutForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="reagentOutModalLabel">试剂领用</h4>
	      </div>
		  <div class="modal-body">
		  	<input type="hidden" name="reagentId" value="" class="form-control"/>
	       
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="out-name" class="control-label col-md-3">试剂:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="out-name" placeholder="试剂名称" class="form-control" disabled/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="stocks" class="control-label col-md-3">库存:</label>
                  <div class="col-md-8">
                    <input type="text" name="stocks" id="stocks" class="form-control" disabled/>
                    <input type="hidden" name="warnStock" value="" class="form-control"/>
                  </div>
                </div>
		        <div class="form-group">
                  <label for="out-quantity" class="control-label col-md-3">数量:</label>
                  <div class="col-md-8">
                    <input type="text" id="out-quantity" name="quantity" placeholder="领用数量,不能超过库存量" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="out-remark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="out-remark" placeholder="备注" rows="3" class="form-control" role="data-remain" maxlength="200"></textarea>
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
  <script src="https://cdn.bootcss.com/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
  <script src="/static/js/viewjs/reagent-stock.js"></script>
</imports>