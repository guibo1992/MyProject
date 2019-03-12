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
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 报告管理</li>
	    <li class="active"> 分析报告</li>
	  </ol>
	</section>
	
	<section class="content report-example">
	  <form id="reportForm" class="box box-success pad">
	    <div class="box-header with-border"> 
	      <h3 class="box-title"> 报告生成</h3> 
	    </div>
	    <div class="box-body">
	      <div class="form-group margin-bottom-none">
            <label>报告名称</label>
            <div class="row">
              <div class="col-md-8">
	          	<input type="text" id="name" name="name" class="form-control input-lg" value="${template.name}" placeholder="报告名称" />
	          	<input type="hidden" name="templateId" value="${template.id}" />
	          	<input type="hidden" name="typeId" value="${template.typeId}" />
	          	<input type="hidden" name="patientId" value="${specimen.patientId}" />
	          </div>
	        </div>
	      </div>
	    </div>
	    
	    <div class="box-header with-border"> 
	      <h3 class="box-title"> 标本信息</h3> 
	    </div>
	    <div class="box-body">
	      <div class="form-group">
        	<div class="row">
              <div class="col-xs-4">
                <div>
                  <label>姓 名：</label> ${specimen.patient.name}
                  <input type="hidden" name="patientName" value="${specimen.patient.name}" />
                </div>
                <div>
                  <label>门诊号：</label> ${specimen.hisId}
                  <input type="hidden" name="hisId" value="${specimen.hisId}" />
                </div>
                <div>
                  <label>送检样本：</label> ${specimen.type.name}
                  <input type="hidden" name="inspectionType" value="${specimen.type.name}" />
                </div>
              </div>
              <div class="col-xs-4">
                <div>
                  <label>性 别：</label> <#if specimen.patient.sex == 'M'>男<#elseif specimen.patient.sex == 'F'>女<#else>其他</#if>
                  <input type="hidden" name="patientSex" value="<#if specimen.patient.sex == 'M'>男<#elseif specimen.patient.sex == 'F'>女<#else>其他</#if>" />
                </div>
                <div>
                  <label>送检科室：</label> ${specimen.inspectionDept}
                  <input type="hidden" name="inspectionDept" value="${specimen.inspectionDept}" />
                </div>
                <div>
                  <label>采样日期：</label> ${specimen.gmtCreate?date}
                  <input type="hidden" name="specimenDate" value="${specimen.gmtCreate?date}" />
                </div>
              </div>
              <div class="col-xs-4">
                <div>
                  <label>年 龄：</label> <#if specimen.patient.birthdate?has_content>${specimen.patient.age}岁</#if>
                  <input type="hidden" name="patientAge" value="${specimen.patient.age}" />
                </div>
                <div>
                  <label>送检医生：</label> ${specimen.inspectionPhysician}
                  <input type="hidden" name="inspectionPhysician" value="${specimen.inspectionPhysician}" />
                </div>
                <div>
                  <label>样本号：</label> ${specimen.specimenNo}
                  <input type="hidden" name="specimenNo" value="${specimen.specimenNo}" />
                </div>
              </div>
            </div>
          </div>
            
          <div class="form-group">
            <label>送检指征：</label> ${specimen.clinicalInfo}
            <input type="hidden" name="clinicalInfo" value="${specimen.clinicalInfo}" />
          </div>
            
          <#if !template.hideDetectionMethod>
          <div class="form-group margin-bottom-none">
            <label>检测方法</label>
	        <textarea name="detectionMethod" rows="2" class="form-control" placeholder="检测方法" role="data-remain" maxlength="200">${template.defaultDetectionMethod}</textarea>
          </div>
          </#if>
	    </div>
	    
	    <div class="box-header with-border"> 
	      <h3 class="box-title"> 分析结果</h3> 
	    </div>
	    <div class="box-body">
	      <div class="margin-bottom-none">
	        <div class="form-group">
	           <label>检测结果：</label> ${specimen.analysisResult?replace('XY', 'X?')}
	           <input type="hidden" name="analysisResult" value="${specimen.analysisResult?replace('XY', 'X?')}" />
	        </div>
	        <div class="form-group">
	          <#-- 
	          <div class="row">
                <div class="col-md-6"> 
                  <img src="${_static + specimen.analysisKarImg}" />
                  <div class="h <#if report.template.hideResultSex>collapse</#if>"></div>
                </div>
                <div class="col-md-6">
                  <img src="${_static + specimen.analysisMetImg}" />
                </div>
              </div>
               -->
               
              <div class="result-img">
              <div class="kar">
                <img src="${_static + specimen.analysisKarImg}" />
                <div class="h collapse <#if template.hideResultSex>in</#if>"></div>
              </div>
              
              <div class="met <#if template.hideResultMetImg>collapse</#if>">
                <img src="${_static + specimen.analysisMetImg}" />
                <input type="hidden" name="analysisMetImg" value="${specimen.analysisMetImg}" />
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
	        <div class="form-group margin-bottom-none">
	          <label>结果解释</label>
	          <textarea name="resultInterpretation" rows="3" class="form-control" placeholder="分析结果解释" role="data-remain" maxlength="200"></textarea>
	          <a href="#" class="btn btn-success btn-sm" data-toggle="modal" data-target="#interpretModal"><i class="fa fa-list"></i> 可选择结果解释</a>
            </div>
	      </div>
	    </div>
	    
	    <div class="box-body">
	   	  <div class="form-group margin-bottom-none">
	        <label>结论</label>
	        <textarea name="reportConclusion" rows="3" class="form-control" placeholder="报告结论" role="data-remain" maxlength="200"></textarea>
	        <a href="#" class="btn btn-success btn-sm" data-toggle="modal" data-target="#conclusionModal"><i class="fa fa-list"></i> 可选择报告结论</a>
          </div>
            
          <#if !template.hideRemarks>
          <div class="form-group">
	        <label>备注</label>
	        <textarea name="remarks" rows="3" class="form-control" placeholder="报告备注信息" role="data-remain" maxlength="200">${template.defaultRemarks}</textarea>
          </div>
          </#if>
        </div>
        
        <div class="box-body">
	      <div class="row">
          	<div class="col-xs-4">
          	  <label>检验人：</label> ${laboratorian}
          	  <input type="hidden" name="laboratorian" value="${laboratorian}" />
          	</div>
	    	<div class="col-xs-4">
	    	  <label>审核人：</label> ${masterAuditor} &nbsp;&nbsp; ${deputyAuditor}
	    	  <input type="hidden"  name="masterAuditorId" value="${template.masterAuditorId}" />
	    	  <input type="hidden"  name="masterAuditorId" value="${template.deputyAuditorId}" />
	    	  <input type="hidden"  name="masterAuditor" value="${masterAuditor}" />
	    	  <input type="hidden"  name="deputyAuditor" value="${deputyAuditor}" />
	    	</div>
	    	<div class="col-xs-4 text-center">
	    	  <label>日期：</label>
	    	  <span id="reportDate">${.now?string('yyyy-MM-dd')}</span>
	    	  <input type="hidden"  name="reportDate" value="${.now?string('yyyy-MM-dd')}" />
	    	</div>
	      </div>
        </div>
        
        <div class="box-footer">
          <#-- <button type="button" class="btn btn-default btn-lg margin-bottom margin-r-5"><i class="fa fa-search"></i> 报告预览</button> -->
          <button type="submit" class="btn btn-primary btn-lg margin-bottom margin-l-5"><i class="fa fa-save"></i> 提交报告</button>
        </div>
	  </form>
	</section>
	
	
	
	<#-- 
	<div class="modal fade" id="conclusionModal" role="dialog" aria-labelledby="conclusionModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content form-horizontal">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="conclusionModalLabel">选择报告结论</h4>
	      </div>
		  <div class="modal-body">
		    <table id="conclusion-table"></table>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	 -->
	
	<div class="modal fade" id="interpretModal" role="dialog" aria-labelledby="interpretModalLabel" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content ">
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
	    <div class="modal-content ">
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