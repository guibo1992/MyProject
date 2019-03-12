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
	  <h1> 随访记录详情
	    <small> 随访管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 随访记录详情</li>
	  </ol>
	</section>
	
    <section class="content">
      <div class="row">
        <div class="col-md-8">
          <ul class="timeline">
            <#list recordList as fr>
            <li class="time-label">
              <span class="bg-red"> ${fr.followupDate?date} </span>
            </li>
            <li>
              <i class="fa fa-user <#if fr.followupStatus gt 0>bg-blue<#elseif fr.followupStatus lt 0>bg-maroon<#else>bg-purple</#if>"></i>
              <div class="timeline-item">
                <a name="edit" id="${fr.id}" class="time" href="#"><i class="fa fa-edit"></i></a>
                <div class="timeline-header">
                  <span class="margin-r-10"> 随访详情</span>
                </div>
                <div class="timeline-body">
                  <div class="row">
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                	<label>姓名：</label>
	                	<span>${followup.patientName!'无'}</span>
	                  </div>
                    </div>
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                    <label>标本号：</label>
	                    <span>${fr.specimenNo}</span>
	                  </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                    <label>随访主题：</label>
	                    <span>${fr.subject}</span>
	                  </div>
                    </div>
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                    <label>随访人：</label>
	                    <span>${fr.followuper}</span>
	                  </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                    <label>随访日期：</label>
	                    <span>${fr.followupDate?date}</span>
	                  </div>
                    </div>
                    <div class="col-xs-6">
                      <div class="form-group margin-bottom-none">
	                    <label>随访状态：</label>
	                    <#if fr.followupStatus gt 0>
		                <span  class="text-success margin-l-5">正常 </span >
		      	        <#elseif fr.followupStatus lt 0>
		      	        <span  class="text-danger margin-l-5">拒访 </span >
		      	        <#else>
		      	        <span  class="text-default margin-l-5">失访</span >
		      	        </#if>
	                  </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label>随访内容：</label>
                    <div>${fr.followupContent}</div>
                  </div>
                </div>
              </div>
            </li>
            </#list>
            <li>
              <i class="fa fa-clock-o bg-gray"></i>
            </li>
          </ul>
        </div>
      </div>
      <div class="clearfix"></div>
	</section>
	<section class="content">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="col-md-12"> <button type="button" id="back" class="btn btn-default"><i class="fa fa-reply"></i> 返回列表 </button> </div>
        </div>
      </div>
	</section>
	
	<div class="modal fade" id="followupRecordModal" role="dialog" aria-labelledby="followupRecordModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <form id="followupRecordForm" class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="followupRecordModalLabel">随访记录</h4>
	      </div>
		  <div class="modal-body">
	        <input type="hidden" name="id" class="form-control"/>
			<div class="col-md-12">
	          <div class="form-group">
	            <span for="specimenNo">样本编号</span>
	            <input type="text" name="specimenNo" id="specimenNo" class="form-control" disabled >
	            <input type="hidden" name="specimenNo" value="" class="form-control" >
	          </div>
          	  <div class="form-group">
	            <span for="subject">主题</span>
	            <input type="text" name="subject" id="subject" placeholder="随访主题" class="form-control" />
	          </div>
	          <div class="form-group">
	            <span for="followupDate">随访日期</span>
                <div class="input-group">
	              <input type="text" name="followupDate" id="followupDate" class="form-control" placeholder="随访日期" >
	              <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
	            </div>
	          </div>
	          <div class="form-group">
                <span for="followuper">随访者</span>
                <input type="text" name="followuper" id="followuper" placeholder="随访人姓名" class="form-control" />
              </div>
	          <div class="form-group">
                <span class="margin-r-10">随访状态: </span>
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
                <textarea name="followupContent" id="content" placeholder="随访详细内容" rows="6" class="form-control" role="data-remain" maxlength="250" ></textarea>
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
  <script src="/static/js/viewjs/followup-records.js"></script>
</imports>