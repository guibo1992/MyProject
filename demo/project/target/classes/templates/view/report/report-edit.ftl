<head>
  <title>分析报告</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 分析报告
	    <small> 报告管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 报告管理</li>
	    <li class="active"> 分析报告</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="reportForm" class="box box-success report">
	    <div class="box-body">
	      <div class="pad">
	        <div class="form-group">
              <label>报告名称</label>
              <div class="row">
                <div class="col-md-10">
	          	  <input type="text" id="name" name="name" class="form-control input-lg" value="${report.name}" placeholder="报告名称" />
	          	  <input type="hidden" name="id" value="${report.id}" />
	          	  <input type="hidden" name="templateId" value="${report.templateId}" />
	          	  <input type="hidden" name="typeId" value="${report.typeId}" />
	          	  <input type="hidden" name="patientId" value="${report.patientId}" />
	            </div>
	          </div>
	        </div>
	    
	        <div class="form-group">
        	  <div class="row">
                <div class="col-xs-4">
                  <div>
                    <label>姓 名：</label> ${report.patientName}
                  </div>
                  <div>
                    <label>门诊号：</label> ${report.hisId}
                  </div>
                  <div>
                    <label>送检样本：</label> ${report.inspectionType}
                  </div>
                </div>
                <div class="col-xs-4">
                  <div>
                    <label>性 别：</label> ${report.patientSex}
                  </div>
                  <div>
                    <label>送检科室：</label> ${report.inspectionDept}
                  </div>
                  <div>
                    <label>采样日期：</label> ${report.specimenDate}
                  </div>
                </div>
                <div class="col-xs-4">
                  <div>
                    <label>年 龄：</label> <#if report.patientAge?has_content>${report.patientAge}岁</#if>
                  </div>
                  <div>
                    <label>送检医生：</label> ${report.inspectionPhysician}
                  </div>
                  <div>
                    <label>样本号：</label> ${report.specimenNo}
                  </div>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <label>送检指征：</label> ${report.clinicalInfo}
            </div>
            
            <#if report.template.hideDetectionMethod>
            <div class="form-group">
              <label>检测方法</label>
              <div class="row">
                <div class="col-md-10">
	              <textarea name="detectionMethod" rows="4" class="form-control" placeholder="检测方法" role="data-remain" maxlength="200">${report.detectionMethod}</textarea>
                </div>
              </div>
            </div>
            </#if>
       
	        <div class="form-group">
	           <label>检测结果：</label> ${report.analysisResult}
	        </div>
	        <div class="form-group">
	          <div class="row">
                <div class="col-md-6"> 
                  <img src="${_static + specimen.analysedKarImg}" />
                </div>
                <div class="col-md-6">
                  <img src="${_static + specimen.analysedMetImg}" />
                </div>
              </div>
	        </div>
	        <#-- 
	        <div class="form-group">
	          <label>异常结果</label>
	          <textarea name="resultInterpretation" rows="3" class="form-control" placeholder="分析结果解释" role="data-remain" maxlength="200"></textarea>
	          <a href="#" class="btn btn-success btn-sm"><i class="fa fa-list"></i> 选择结果解释</a>
            </div>
             -->
	        <div class="form-group">
	          <label>结果解释</label>
	          <div class="row">
                <div class="col-md-10">
	              <textarea name="resultInterpretation" rows="3" class="form-control" placeholder="分析结果解释" role="data-remain" maxlength="200">${report.resultInterpretation}</textarea>
	              <a href="#" class="btn btn-success btn-sm" data-toggle="modal" data-target="#interpretModal"><i class="fa fa-list"></i> 可选择结果解释</a>
	            </div>
	          </div>
            </div>
	        
	    	<div class="form-group">
	          <label>结论</label>
	          <div class="row">
                <div class="col-md-10">
		          <textarea name="reportConclusion" rows="4" class="form-control" placeholder="报告结论" role="data-remain" maxlength="200">${report.reportConclusion}</textarea>
		          <a href="#" class="btn btn-success btn-sm" data-toggle="modal" data-target="#conclusionModal"><i class="fa fa-list"></i> 可选择报告结论</a>
		        </div>
		      </div>
            </div>
            
            <#if !template.hideRemarks>
            <div class="form-group">
	          <label>备注</label>
	          <div class="row">
                <div class="col-md-10">
	          	  <textarea name="remarks" rows="4" class="form-control" placeholder="报告备注信息" role="data-remain" maxlength="200">${report.defaultRemarks}</textarea>
	          	</div>
		      </div>
            </div>
            </#if>
          </div>
        </div>
        
        <div class="box-body">
	      <div class="pad">
	        <div class="row">
          	  <div class="col-xs-4">
          	    <label>检验者：</label> ${report.laboratorian}
          	  </div>
	    	  <div class="col-xs-4">
	    	    <label>审核者：</label> ${report.masterAuditor} &nbsp;&nbsp; ${report.deputyAuditor}
	    	    <input type="hidden"  name="masterAuditor" value="${report.masterAuditor}" />
	    	    <input type="hidden"  name="deputyAuditor" value="${report.deputyAuditor}" />
	    	  </div>
	    	  <div class="col-xs-4 text-center">
	    	    <label>报告日期：</label>
	    	    <span >${report.reportDate}</span>
	    	    <#-- <input type="hidden" name="reportDate" value="${report.reportDate}" /> -->
	    	  </div>
	        </div>
          </div>
        </div>
        
        <div class="box-footer">
          <#-- <button type="button" class="btn btn-default btn-lg margin-bottom margin-r-5"><i class="fa fa-search"></i> 报告预览</button> -->
          <button type="submit" class="btn btn-warning btn-lg margin-bottom margin-l-5"><i class="fa fa-save"></i> 重新提交报告</button>
        </div>
	  </form>
	</section>
	
	<div class="modal fade" id="interpretModal" role="dialog" aria-labelledby="interpretModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="interpretModalLabel">选择分析结果解释</h4>
	      </div>
		  <div class="modal-body">
		    <table id="interpret-table"></table>
		    <div class="clearfix"></div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="conclusionModal" role="dialog" aria-labelledby="conclusionModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="conclusionModalLabel">选择报告结论</h4>
	      </div>
		  <div class="modal-body">
		    <table id="conclusion-table"></table>
		    <div class="clearfix"></div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确定</button>
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
  <script src="/static/js/bootstrap-datepicker.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/viewjs/report.js"></script>
</imports>