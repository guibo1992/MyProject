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
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 公告详情</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    
	    <div class="box-body">
	      <h2 class="text-center">${message.title}</h2>
	      <div>
	        ${message.content}
	      </div>
	    </div>
	    
	    <div class="box-footer margin-bottom">
	      <#if message.attachList?? && message.attachList?size gt 0>  
	      <div class="box-header with-border">
            <h3 class="box-title">消息附件</h3>
          </div>
	      <div class="">
          	<table class="table">
          	  <thead>
		        <tr><th>文件名</th> <th>类型</th> <th>大小</th> <th></th> </tr>
		      </thead>
          	  <tbody id="attachContainer">
          	  <#list message.attachList as attach>
          	    <tr>
          	      <td>${attach.name}</td>
		 		  <td>${attach.type}</td>
				  <td>${attach.filesize}KB</td>
			 	  <td><a href="/transfer/file/download?file=${attach.file}&filename=${attach.name}" class="text-success"><i class="fa fa-download"></i> 下载</a></td>
          	    </tr>
          	  </#list>
          	  </tbody>
          	</table>
	      </div>
	    </#if>  
	    </div>
	    
	  </div>
	</section>
  </div>
</body>
