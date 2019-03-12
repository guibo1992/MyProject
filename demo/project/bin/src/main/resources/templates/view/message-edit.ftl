<head>
  <title>公告详情</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/jquery.fileupload.min.css" rel="stylesheet">
  <link href="/static/css/summernote.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 公告详情
	    <small> 公告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 公告详情</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-header with-border">
	       <h3 class="box-title col-md-12"> 公告详情</h3>
	    </div>
	    <div class="box-body">
	      <div class="col-md-10">
	        <form id="messageForm" class="form-horizontal pad" method="post">
	          <input type="hidden" name="id" value="${message.id}" >
		      
	          <div class="form-group">
	            <label for="title">公告标题</label>
	            <input type="text" name="title" value="${message.title}" class="form-control input-lg" placeholder="公告标题, 50个字以内" maxlength="50">
	          </div>
	          
	          <div class="form-group">
	            <label for="content">公告内容</label>
	            <textarea name="content" id="summernote" class="form-control" placeholder="">${message.content}</textarea>
	          </div>
	          
	          <div class="form-group">
	          	<table class="table">
	          	  <thead>
			        <tr><th>文件名</th> <th>类型</th> <th>大小</th> <th></th> </tr>
			      </thead>
	          	  <tbody id="attachContainer">
	          	  <#list message.attachList as attach>
	          	    <tr>
	          	      <td name="name" data-file="${attach.file}">${attach.name}</td>
    		 		  <td name="type">${attach.type}</td>
    				  <td name="filesize" data-val="${attach.filesize}">${attach.filesize}KB</td>
    			 	  <td><a href="#" name="delFile" class="btn btn-sm btn-danger"><i class="fa fa-remove"></i> 删除</a></td>
	          	    </tr>
	          	  </#list>
	          	  </tbody>
	          	</table>
	          </div>
	          <div class="form-group">
	            <div>
	          	  <div class="fileinput-button btn btn-success">
              	    <span>上传附件</span>
              	    <input id="uploadImage" type="file" title="上传公告附件"  multiple/>
            	  </div>
            	  <span class="text-muted small margin-btm-5">公告附件文件, 支持pdf,word,image等格式文件</span>
            	</div>
            	<div class="progress mb-2 mt-1 collapse" style="height: 3px;margin-top:5px;">
	             <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
	            </div>
	          </div>
	          
	          <div class="form-group"></div>
	          <div class="form-group">
        	    <button  class="btn btn-primary btn-lg" type="submit"><i class="fa fa-save"></i> 发布公告</button>
	          </div>
	        </div>
	      </form>
	    </div>
	  </div>
	</section>
  </div>
</body>
<imports>
  <script src="/static/js/fileupload/jquery.ui.widget.min.js"></script>
  <script src="/static/js/fileupload/jquery.iframe-transport.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-process.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-validate.min.js"></script>
  <script src="/static/js/summernote.min.js"></script>
  <script src="/static/js/summernote-zh-CN.min.js"></script>
  <script src="/static/js/viewjs/message.js"></script>
</imports>