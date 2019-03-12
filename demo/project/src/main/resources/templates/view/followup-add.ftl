<head>
  <title>随访记录</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <#-- <link href="/static/css/jquery.autocompleter.css" rel="stylesheet"> -->
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 新增随访记录
	    <small> 随访管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"><a href="/followup/list.htm"> 随访记录</a></li>
	    <li class="active"> 新增随访记录</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="followupForm">
	    <div class="box box-success">
	      <div class="box-header">
	  		<h2 class="box-title col-md-12">随访记录详情</h2>
	  	  </div>
	      <div class="box-body">
	        <div class="col-lg-8 col-md-10">
	          <div class="row">
	            <div class="col-md-6">
		          <div class="form-group">
		            <span for="specimenNo">样本编号</span>
		            <input type="text" name="specimenNo" id="specimenNo" autocomplete="off" class="form-control" placeholder="样本编号" >
		          </div>
		        </div>
	          	<div class="col-md-6">
	          	  <div class="form-group">
		            <span for="subject">主题</span>
		            <input type="text" name="subject" id="subject" placeholder="随访主题" class="form-control" />
		          </div>
		        </div>
		      </div>
		      <div class="row">
	            <div class="col-md-6">
		          <div class="form-group">
		            <span for="followupDate">随访日期</span>
	                <div class="input-group">
		              <input type="text" name="followupDate" id="followupDate" class="form-control" placeholder="随访日期" >
		              <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
		            </div>
		          </div>
		        </div>  
		        <div class="col-md-6">
		          <div class="form-group">
	                <span for="followuper">随访者</span>
	                <input type="text" name="followuper" id="followuper" placeholder="随访人姓名" class="form-control" />
	              </div>
	            </div>
	          </div>
	          <div class="form-group">
                <span class="margin-r-10">随访状态</span>
                <div class="radio radio-primary radio-inline margin-r-10">
       	          <input id="normal" name="followupStatus" type="radio" checked value="1">
                  <label for="normal"> 正常 </label>
      	        </div>
      	        <div class="radio radio-warning radio-inline margin-r-10">
       	          <input id="lost" name="followupStatus" type="radio" value="0">
                  <label for="lost"> 失访  </label>
      	        </div>
      	        <div class="radio radio-danger radio-inline">
       	          <input id="reject" name="followupStatus" type="radio" value="-1">
                  <label for="reject"> 拒访  </label>
      	        </div>
	          </div>  
              <div class="form-group">
                <span for="content">随访内容</span>
                <textarea name="followupContent" id="content" placeholder="随访详细内容" rows="8" class="form-control" role="data-remain" maxlength="250" ></textarea>
              </div>
		    </div>
		  </div>
		  
		  <div class="box-footer">
		    <div class="col-lg-8 col-md-10">
		      <button type="button" id="back" class="btn btn-default"> 返回 </button> 
		      <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 保存 </button> 
		    </div>
		  </div>
		</div>
	  </form>
	</section>
  </div>
</body>

<imports>
  <script src="/static/js/jquery.form.min.js"></script>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/viewjs/followup-add.js"></script>
</imports>