<head>
  <title>标本培养列表</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/smart_wizard.css" rel="stylesheet">
  <link href="/static/css/smart_wizard_dots.css" rel="stylesheet">
  <link href="https://cdn.bootcss.com/webui-popover/2.1.15/jquery.webui-popover.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 标本培养列表 <small> 标本管理</small> </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li>标本管理</li>
	    <li class="active"> 培养列表</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <div class="form-group active-default">
			  <div id="status" class="btn-group" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="0" autocomplete="off"> 未完成
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="1" autocomplete="off"> 已完成
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="" autocomplete="off"> 全部
			    </label>
			  </div>
			</div>
	        <div class="form-group margin-l-5" style="width:160px">
	          <select id="type" class="form-control chosen-select" data-placeholder="选择标本类型">
              	<option value=""></option>
              	<#list typeList as type>
                <option value="${type.id}">${type.typeHead} ${type.name}</option>
                </#list>
           	  </select>
	        </div>
			<div class="form-group">
			  <button class="btn btn-success col-md-offset-1" data-toggle="modal" data-target="#batchModal">批量操作</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="cultureModal" role="dialog" aria-labelledby="cultureModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="processForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="cultureModalLabel">标本培养</h4>
	      </div>
		  <div class="modal-body">
		    <input type="hidden" name="specimenId" class="form-control"/>
	        <input type="hidden" name="processOrder" class="form-control"/>
	        <input type="hidden" name="processStep" class="form-control"/>
	        <input type="hidden" name="completedStatus" class="form-control"/>
	        
			<div class="row"> 
		      <div class="col-md-12">
                <div class="form-group">
                  <label for="operationTime" class="control-label col-md-3">日期:</label>
                  <div class="col-md-8 inputGroupContainer">
		            <div class="input-group date">
		              <input name="processTime" type="text" class="form-control" autocomplete="off" placeholder="日期" >
		              <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
		            </div>
		          </div>
		        </div>
                <div class="form-group">
                  <label for="remark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="remark" rows="3" placeholder="备注,可选填" class="form-control" maxlength="60" role="data-remain"></textarea>
                  </div>
                </div>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确定</button>
	      </div>
	    </form>
	  </div>
	</div>
	
	<div class="modal fade" id="batchModal" role="dialog" aria-labelledby="batchModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="batchForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="batchModalLabel">批量标本培养</h4>
	      </div>
		  <div class="modal-body">
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="typeId" class="control-label col-md-3">标本类型:</label>
                  <div class="col-md-8 inputGroupContainer">
                    <select id="typeId" name="typeId" class="chosen-select">
		            <#list typeList as type>
                	  <option value="${type.id}">${type.typeHead} ${type.name}</option>
                	</#list>
		           	</select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="step" class="control-label col-md-3">处理步骤:</label>
                  <div class="col-md-8 inputGroupContainer">
                    <select id="step" name="processOrder" class="chosen-select" data-placeholder="--请选择--">
		              <option value=""></option>
		              <option value="1">培养</option>
		          	  <option value="2">收获</option>
		          	  <option value="3">滴片</option>
		          	  <option value="4">显带</option>
		          	  <option value="5">扫片</option>
		           	</select>
		           	<input type="hidden" name="processStep" class="form-control" />
		           	<input type="hidden" name="completedStatus" value="0" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3">编号范围:</label>
                  <div class="col-md-8 inputGroupContainer">
                    <div class="row">
                  	  <div class="col-xs-6">
	                    <select id="startId" name="startId" class="chosen-select" data-placeholder="开始编号"></select>
			          </div>
		           	  <div class="col-xs-6">
			            <select id="endId" name="endId" class="form-control chosen-select" data-placeholder="结束编号"></select>
			          </div>
			        </div>
			        <input type="hidden" name="specimenNos" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="processTime" class="control-label col-md-3">日期:</label>
                  <div class="col-md-8 inputGroupContainer">
		            <div class="input-group date">
		              <input name="processTime" type="text" class="form-control" placeholder="操作日期" >
		              <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
		            </div>
		          </div>
		        </div>
		        
		        <div class="form-group">
                  <label for="batchRemark" class="control-label col-md-3">备注:</label>
                  <div class="col-md-8">
                    <textarea name="remark" id="batchRemark" rows="3" placeholder="备注,可选填" class="form-control" maxlength="60" role="data-remain"></textarea>
                  </div>
                </div>
              </div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确认</button>
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
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/jquery.smartWizard.min.js"></script>
  <script src="https://cdn.bootcss.com/webui-popover/2.1.15/jquery.webui-popover.min.js"></script>
  <script src="/static/js/viewjs/specimen-culture.js"></script>
</imports>