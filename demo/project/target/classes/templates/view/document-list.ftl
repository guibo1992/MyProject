<head>
  <title>文档管理</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/jquery.fileupload.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 技术文档
	    <small> 文档管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 文档管理</li>
	    <li class="active"> 技术文档</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			<div class="form-group">
			  <button class="btn btn-success" data-toggle="modal" data-target="#documentModal"> <i class="fa fa-plus"></i> 上传文档</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="documentModal" role="dialog" aria-labelledby="documentModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="documentModalLabel">上传文档</h4>
	      </div>
		  <div class="modal-body">
		    <div class="form-group">
	          <div class="fileinput-button btn btn-success">
                <span><i class="fa fa-upload"></i> 选择上传文档</span>
              	<input id="uploadImage" type="file" title="上传文档" />
              </div>
              <div class="progress mb-2 mt-1 collapse" style="height: 3px;margin-top:5px;">
	             <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
	          </div>
	        </div>
		    <div class="form-group">
	          <div class="row">
	            <div class="col-md-6">
	              <input type="text" id="name" class="form-control" readonly />
	              <input type="hidden" id="file" value="" class="form-control" />
	          	  <input type="hidden" id="type" value="" class="form-control" />
	              <input type="hidden" id="filesize" value="" class="form-control" />
	            </div>
	            <div class="col-md-6">
	          	  <div class="checkbox checkbox-danger">
	       	        <input id="status" type="checkbox" value="false">
	                <label for="status"> 不可删除 </label>
	      	  	  </div>
	      	    </div>
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
  <script src="/static/js/fileupload/jquery.ui.widget.min.js"></script>
  <script src="/static/js/fileupload/jquery.iframe-transport.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-process.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-validate.min.js"></script>
  <script src="/static/js/viewjs/document.js"></script>
</imports>