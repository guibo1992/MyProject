<head>
  <title>用户管理</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/jquery.fileupload.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 用户列表
	    <small> 系统管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 用户列表</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
	        <#-- 
			<div class="form-group active-warning">
			  <div id="status" class="btn-group" data-toggle="buttons">
			    <label class="btn btn-primary active">
			      <input type="radio" name="status" value="1" autocomplete="off"> 正常
			    </label>
			    <label class="btn btn-primary">
			      <input type="radio" name="status" value="0" autocomplete="off"> 失效
			    </label>
			  </div>
			</div>
			 -->
			<div class="form-group">
			  <button class="btn btn-success" id="addUser"><i class="fa fa-plus"></i> 添加用户</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="userModal" role="dialog" aria-labelledby="userModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <form id="userForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="userModalLabel">用户详情</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control" value="" />
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="username" class="control-label col-md-3">帐号:</label>
                  <div class="col-md-8">
                    <input type="text" id="username" name="username" placeholder="登录账号" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-md-3">权限:</label>
                  <div class="col-md-8">
	                <div class="checkbox checkbox-inline">
                      <input id="type1" name="type" type="radio" checked value="1">
                      <label for="type1"> 录入</label>
                    </div>
                    <div class="checkbox checkbox-inline">
                      <input id="type2" name="type" type="radio" value="3">
                      <label for="type2"> 分析</label>
                    </div>
                    <div class="checkbox checkbox-inline">
                      <input id="type6" name="type" type="radio" value="5">
                      <label for="type6"> 审核</label>
                    </div>
                    <#-- 
                    <div class="checkbox checkbox-inline">
                      <input id="type9" name="type" type="radio" value="9">
                      <label for="type9"> 管理员</label>
                    </div>
                    -->
                  </div>
	            </div>
                <div class="form-group">
                  <label for="name" class="control-label col-md-3">姓名:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="name" placeholder="真实姓名" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="image" class="control-label col-md-3">签名图:</label>
                  <div class="col-md-8 inputGroupContainer">
				    <div id="sign_img" class="fileinput-button sign-image">
				      <img class="placeholder img-rounded" options="size=98x32&bgcolor=#296081&fsize=14&fweight=normal&ffamily=Helvetica&color=#fff&text=上传签名图" onerror="this.src=placeholder.getData({size: '98x32', bgcolor: '#3c8dbc', color: '#fff', fsize: '14', text:'上传签名图'})">
			          <input id="uploadImage" type="file" title="上传签名" accept="image/png, image/jpeg" />
			        </div>
			        <p class="small text-muted margin-top-5">签名图,支持png、jpg/jpeg等图片格式.</p>
			        <input type="hidden" name="signImg" class="form-control" value="" data-value="">
                  </div>
                </div>
                <div class="form-group">
                  <label for="dept" class="control-label col-md-3">科室:</label>
                  <div class="col-md-8">
                    <input type="text" name="dept" id="dept" placeholder="科室名称" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="phone" class="control-label col-md-3">手机:</label>
                  <div class="col-md-8">
                    <input type="text" name="phone" id="phone" placeholder="手机号码" class="form-control" />
                  </div>
                </div>
                
                <div id="resetPwd" class="hide">
                  <div class="form-group">
                    <label class="control-label col-md-3">&nbsp;</label>
                    <div class="col-md-8">
                      <div class="checkbox checkbox-primary">
                        <input type="checkbox" id="resetpassword" />
                        <label for="resetpassword">重置密码</label>
                      </div>
                    </div>
                  </div>
                
                  <div class="form-group password-control hide">
		            <label class="col-sm-3 control-label" for="passwd">新密码：</label>
		            <div class="col-sm-8">
		              <input type="text" name="password" placeholder="以字母开头，可包含字母、数字、下划线的组合的6-18位字符" id="passwd" class="form-control">
		            </div>
		          </div>
		          <div class="form-group password-control hide">
		            <label class="col-sm-3 control-label" for="confirmPasswd">确认密码：</label>
		            <div class="col-sm-8">
		              <input type="text" name="repassword" placeholder="密码确认" id="confirmPasswd" class="form-control">
		            </div>
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
  <script src="/static/js/fileupload/jquery.ui.widget.min.js"></script>
  <script src="/static/js/fileupload/load-image.all.min.js"></script>
  <script src="/static/js/fileupload/canvas-to-blob.min.js"></script>
  <script src="/static/js/fileupload/jquery.blueimp-gallery.min.js"></script>
  <script src="/static/js/fileupload/jquery.iframe-transport.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-process.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-image.min.js"></script>
  <script src="/static/js/fileupload/jquery.fileupload-validate.min.js"></script>
  <script src="/static/js/placeholder.js"></script>
  <script src="/static/js/viewjs/user.js"></script>
</imports>