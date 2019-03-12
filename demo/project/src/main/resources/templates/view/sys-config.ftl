<head>
  <title>系统设置</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/jquery.fileupload.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <script src="/static/js/placeholder.js"></script>
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 系统配置
	    <small> 系统管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 系统配置</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form method="post">
	    <div class="box box-success pad">
    	  <div class="box-header with-border">
	        <h3 class="box-title"><i class="fa fa-paperclip"></i> 系统配置</h3>
	      </div>
	      <div class="box-body">
	        <div class="row">
	      	  <div class="col-md-6">
		        <div class="form-group">
		          <label for="code">系统KEY</label>
		          <input type="text" name="idCode" id="code" value="${config.idCode}" class="form-control input-lg" placeholder="系统识别码" />
		        </div>
                <div class="form-group">
                  <label for="hospName">医院名称</label>
                  <input type="text" name="hospName" id="hospName" value="${config.hospName}" class="form-control input-lg" placeholder="医院名称" />
                </div>
                <div class="form-group">
                  <label for="hospAliase">医院简称</label>
                  <input type="text" name="hospAliase" id="hospAliase" value="${config.hospAliase}" class="form-control input-lg" placeholder="医院简称, 2-6个字符" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label>系统Logo</label>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="thumbnail fileinput-button">
                        <img name="logo" class="placeholder " src="<#if config.hospLogo?has_content>${_static + config.hospLogo}</#if>" options="size=238x204&fsize=26&bgcolor=#ccc&color=#ffffff&text=Logo图" onerror="this.src=placeholder.getData({size:'238x204',text:'Upload Logo',color:'#ccc',fsize:'26',bgcolor:'#5f666b'})"/>
                        <input type="file" id="uploadLogo" accept="image/png, image/jpeg" title="上传logo" />
                      </div>
                    </div>
                  </div>
                  <input type="hidden" name="hospLogo" class="form-control" value="${config.hospLogo}">
		        </div>
	          </div>
	        </div>
		  </div>
	      
	      <div class="box-body">
		    <div class="row">
	      	  <div class="col-md-6">   
	      	    <div class="box-header with-border">
		          <h3 class="box-title"><i class="fa fa-paperclip"></i> 样本分析服务配置</h3>
		        </div>
	        
	            <div class="form-group">
		          <label for="smbDomain">分析服务器(IP)</label>
		          <input type="text" name="smbDomain" id="smbDomain" value="${config.smbDomain}" class="form-control" placeholder="分析机地址" />
		        </div>
		        
		        <div class="form-group">
		          <label for="ntlmUsername">登录名</label>
		          <input type="text" name="ntlmUsername" id="ntlmUsername" value="${config.ntlmUsername}" class="form-control" placeholder="NTLM 登录用户名" />
		        </div>
		        
		        <div class="form-group">
		          <label for="ntlmPassword">登录密码</label>
		          <input type="text" name="ntlmPassword" id="ntlmPassword" value="${config.ntlmPassword}" class="form-control" placeholder="NTLM 登录密码" />
		        </div>
		        
		        <div class="form-group">
		          <label for="shareSource">共享目录</label>
		          <input type="text" name="shareSource" id="shareSource" value="${config.shareSource}" class="form-control" placeholder="共享目录" />
		        </div>
		        
		        <div class="form-group">
		          <button type="button" id="testNtlm" class="btn btn-success">测试连接</button>
		          <span id="test_result" class="fa-1-5 text-success col-md-offset-1 hide">Connect Success!</span>
		        </div>
          	  </div>
            
              <div class="col-md-6"> 
                <div class="box-header with-border">
	        	  <h3 class="box-title"><i class="fa fa-paperclip"></i> 默认打印设置</h3>
	      		</div>
	      		
	      		<div class="form-group">
		          <label for="shareSource ">载玻片打印机</label>
		          <select name="slidePrinter" class="chosen-select" data-placeholder="载玻片打印机">
      	            <option value=""></option>
      	            <#list printList as printer>
      	            <option <#if printer == config.slidePrinter>selected</#if> value="${printer}">${printer}</option>
      	            </#list>
      	          </select>
		        </div>
	      		<div class="form-group">
	      		  <div class="checkbox">
           	        <input type="checkbox" id="hasPrintBarcode" <#if config.barcodePrinter?has_content>checked</#if> value="true" data-toggle="collapse" data-target="#barcodePrinter" aria-expanded="false">
                    <label for="hasPrintBarcode" class="font-bold"> 标本标签打印 </label>
          	      </div>
		          <div id="barcodePrinter" class="collapse <#if config.barcodePrinter?has_content>in</#if>">
          	        <select name="barcodePrinter" class="chosen-select" data-placeholder="标本标签打印机">
          	          <option value=""></option>
          	          <#list printList as printer>
          	          <option <#if printer == config.barcodePrinter>selected</#if> value="${printer}">${printer}</option>
          	          </#list>
          	        </select>
			      </div>
		          
		        </div>
		        <div class="form-group">
		          <label for="shareSource">分析报告打印机</label>
		          <select name="reportPrinter" class="chosen-select" data-placeholder="分析报告打印机">
      	            <option value=""></option>
      	            <#list printList as printer>
      	            <option <#if printer == config.reportPrinter>selected</#if> value="${printer}">${printer}</option>
      	            </#list>
      	          </select>
		        </div>
	      		
              </div>
            </div>
          </div>
          
          <div class="box-footer">
	        <button type="submit" class="btn btn-primary btn-lg" type="button"><i class="fa fa-save"></i> 保存设置</button>
	      </div>
	    </div>
	  </form>
	</section>
  </div>
</body>

<imports>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/fileupload/jquery.ui.widget.min.js"></script>
  <script src="/static/js/fileupload/load-image.all.min.js"></script>
  <script src="/static/js/fileupload/canvas-to-blob.min.js"></script>
  <script src="/static/js/fileupload/jquery.blueimp-gallery.min.js"></script>
  <script src="/static/js/fileupload/jquery.iframe-transport.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-process.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-image.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-validate.min.js"></script>
  <script src="/static/js/viewjs/config.js"></script>
</imports>