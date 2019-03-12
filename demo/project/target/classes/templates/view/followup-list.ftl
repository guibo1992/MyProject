<head>
  <title>随访记录</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <#-- <link href="/static/css/jquery.autocompleter.css" rel="stylesheet"> -->
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 随访记录
	    <small> 随访管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 随访记录</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
	    <div class="box-body">
	      <div id="toolbar" class="form-inline" role="form">
			<div class="form-group">
	          <div class="input-group">
	            <input type="text" id="startDate" class="form-control" autocomplete="off" placeholder="查询开始日期" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        <div class="form-group">
	          <div class="input-group">
	            <input type="text" id="endDate" class="form-control" autocomplete="off" placeholder="查询结束日期" >
	            <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	          </div>
	        </div>
	        
			<div class="form-group">
			  <div id="status" class="btn-group active-primary" data-toggle="buttons">
			    <label class="btn btn-default active">
			      <input type="radio" name="status" value="" autocomplete="off"> 全部
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="0" autocomplete="off"> 失访
			    </label>
			    <label class="btn btn-default">
			      <input type="radio" name="status" value="-1" autocomplete="off"> 拒访
			    </label>
			  </div>
			</div>
			<div class="form-group">
			  <button class="btn btn-success col-md-offset-1" id="addFollowup"> <i class="fa fa-plus"></i> 添加随访记录</button>
			</div>
	      </div>
	      <table id="table"></table>
	    </div>
	  </div>
	</section>
	
	<div class="modal fade" id="followupModal" role="dialog" aria-labelledby="followupModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <form id="followupForm" class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="followupModalLabel">随访记录</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control"/>
	       
			<div class="row"> 
		      <div class="col-md-12">
		        <div class="form-group">
                  <label for="name" class="control-label col-md-3">随访名称:</label>
                  <div class="col-md-8">
                    <input type="text" name="name" id="name" placeholder="随访名称" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="specimenSN" class="control-label col-md-3">样本编号:</label>
                  <div class="col-md-8">
                    <input type="text" name="specimenSN" id="specimenSN" placeholder="样本编号" class="form-control" />
                  </div>
                </div>
		        <div class="form-group">
                  <label for="targetName" class="control-label col-md-3">随访对象:</label>
                  <div class="col-md-8">
                    <input type="text" id="targetName" name="targetName" placeholder="随访对象姓名" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3">随访状态:</label>
                  <div class="col-md-8">
	                <div class="radio radio-success radio-inline margin-r-10">
               	      <input id="normal" name="followupStatus" type="radio" value="1">
                      <label for="normal"> 正常 </label>
              	    </div>
              	    <div class="radio radio-success radio-inline margin-r-10">
               	      <input id="lost" name="followupStatus" type="radio" value="0">
                      <label for="lost"> 失访  </label>
              	    </div>
              	    <div class="radio radio-success radio-inline">
               	      <input id="reject" name="followupStatus" type="radio" value="-1">
                      <label for="reject"> 拒访  </label>
              	    </div>
              	  </div>
                </div>
                <div class="form-group">
                  <label for="followupDate" class="control-label col-md-3">随访日期:</label>
                  <div class="col-md-8">
                    <div class="input-group">
		              <input type="text" name="followupDate" id="followupDate" class="form-control" placeholder="随访日期" >
		              <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
		            </div>
                  </div>
                </div>
                <div class="form-group">
                  <label for="content" class="control-label col-md-3">随访内容:</label>
                  <div class="col-md-8">
                    <textarea name="followupContent" id="content" placeholder="随访详细内容" rows="5" class="form-control"></textarea>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="followuper" class="control-label col-md-3">随访人:</label>
                  <div class="col-md-8">
                    <input type="text" name="followuper" id="followuper" placeholder="随访人姓名" class="form-control" />
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
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/viewjs/followup-list.js"></script>
</imports>