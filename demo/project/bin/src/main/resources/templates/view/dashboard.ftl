<head>
  <title>Index</title>
</head>
<body>
  <div class="content-wrapper">
    <section class="content-header">
      <h1> 功能大厅 <small>系统功能</small></h1>
      <ol class="breadcrumb">
        <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
        <li class="active">Here</li>
      </ol>
    </section>
    
    <section class="content">
      <div class="row">
        <div class="col-lg-9">
	      <div class="row">
	        <div class="col-md-4 col-xs-6">
	          <div class="small-box bg-aqua">
	            <div class="inner">
	              <h3>标本列表</h3>
	              <p>Specimen List</p>
	            </div>
	            <div class="icon">
	              <i class="fa fa-user"></i>
	            </div>
	            <a href="/specimen/list.html" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	        
	        <div class="col-md-4 col-xs-6">
	          <div class="small-box bg-orange">
	            <div class="inner">
	              <h3>标本录入</h3>
	              <p>Specimen Edit</p>
	            </div>
	            <div class="icon">
	              <i class="fa fa-edit"></i>
	            </div>
	            <a href="/specimen/add.html" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-md-4 col-xs-6">
	          <div class="small-box bg-green">
	            <div class="inner">
	              <h3>快速打印</h3>
	              <p>Quick Print</p>
	            </div>
	            <div class="icon">
	              <i class="fa fa-print"></i>
	            </div>
	            <a href="/slide/print/fast-list.html" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	        <div class="col-md-4 col-xs-6">
	          <div class="small-box bg-yellow">
	            <div class="inner">
	              <h3>打印记录</h3>
	              <p>Print Logs</p>
	            </div>
	            <div class="icon">
	              <i class="fa fa-th-list"></i>
	            </div>
	            <a href="/slide/print/records-list.html" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	      </div>
          <div class="row">
          <#if _active_user.isAdmin>
	        <div class="col-md-4 col-xs-6">
	          <div class="small-box bg-teal">
	            <div class="inner">
	              <h3>系统配置</h3>
	              <p>System Config</p>
	            </div>
	            <div class="icon"> <i class="fa fa-cogs"></i>  </div>
	            <a href="/sys/config.html" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
          </#if>
          </div>
        </div>
      </div>
	</section>
  </div>
</body>
