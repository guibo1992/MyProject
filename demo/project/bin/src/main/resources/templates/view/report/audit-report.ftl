<head>
  <title>分析报告审核</title>
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 分析报告审核
	    <small> 审核管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 审核管理</li>
	    <li class="active"> 分析报告审核</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success report">
	    <div class="report-heading">
          <div class="form-group">
            <#if _sysConfig.hospLogo?has_content><img class="logo" src="/static/images/logo.png"><#-- <img class="logo" src="${_static +_sysConfig.hospLogo}"> --></#if>
            <h3 class="text-center">${_sysConfig.hospName}</h3>
            <h4 class="text-center">${report.name}</h4>
            <input type="hidden" id="reportId" value="${report.id}" />
            <input type="hidden" id="masterAuditorId" value="${report.masterAuditorId}" />
            <input type="hidden" id="deputyAuditorId" value="${report.deputyAuditorId}" />
          </div>
	    </div>
	    
	    <div class="box-body border-bottom">
	      <div class="pad">
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
              <label>检测方法：</label> ${report.detectionMethod}
            </div>
            </#if>
          </div>
	    </div>
	    
	    <div class="box-body border-bottom">
	      <div class="pad">
	        <div class="form-group">
	           <label>检测结果：</label> ${report.analysisResult}
	        </div>
	        <div class="form-group">
	          <div class="row">
                <div class="col-md-6"> 
                  <img src="${_static + report.analysisKarImg}" class="img-responsive" />
                </div>
                <div class="col-md-6">
                  <img src="${_static + report.analysisMetImg}" class="img-responsive" />
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
            <#if !report.template.hideResultInterpret>
	        <div class="form-group">
	          <label>结果解释：</label> ${report.resultInterpretation}
            </div>
	        </#if>
	      </div>
	    </div>
	    
	    <div class="box-body">
	      <div class="pad">
	    	<div class="form-group">
	          <label>结论：</label> ${report.reportConclusion}
            </div>
            
            <#if !template.hideRemarks>
            <div class="form-group">
	          <label>备注：</label> ${report.remarks}
            </div>
            </#if>
          </div>
        </div>
        
        <div class="box-body">
	      <div class="pad">
	        <div class="row">
          	  <div class="col-xs-4">
          	    <label>检验人：</label> ${report.laboratorian}
          	  </div>
	    	  <div class="col-xs-4">
	    	    <label>审核人：</label> ${report.masterAuditor} &nbsp;&nbsp; ${report.deputyAuditor}
	    	  </div>
	    	  <div class="col-xs-4 text-center">
	    	    <label>日期：</label> ${report.reportDate?date}
	    	  </div>
	        </div>
          </div>
        </div>
        
        <#if report.auditList?size gt 0>
        <div class="box-footer">
          <div class="row">
        	<p class="lead">审核记录</p>
        	<div class="col-xs-12 table-responsive">
          	  <table class="table table-striped">
            	<thead>
            	<tr>
              	  <th>时间</th>
              	  <th>审核人</th>
              	  <th>审核结果</th>
              	  <th>驳回原因</th>
            	</tr>
            	</thead>
            	<tbody>
            	<#list report.auditList as audit>
            	<tr>
              	  <td>${audit.auditTime?datetime}</td>
              	  <td>${audit.auditorName}</td>
              	  <td><#if audit.auditStatus><span class="label label-success">审核通过</span><#else><span class="label label-warning">审核驳回</span></#if></td>
              	  <td>${audit.rejectReason}</td>
            	</tr>
            	</#list>
                </tbody>
          	  </table>
        	</div>
      	  </div>
        </div>
        </#if>
        
        <div class="box-footer">
          <button id="auditPass" class="btn btn-primary margin-bottom"><i class="fa fa-check"></i> 审核通过</button>
          <button class="btn btn-danger margin-bottom margin-l-5" data-toggle="modal" data-target="#reasonModal"><i class="fa fa-times"></i> 审核驳回</button>
        </div>
	  </div>
	</section>
	
	<div class="modal fade" id="reasonModal" role="dialog" aria-labelledby="reasonModalLabel" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <form id="auditForm" class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="reasonModalLabel">审核驳回</h4>
	      </div>
		  <div class="modal-body">
            <div class="form-group">
		      <label for="remarks">驳回理由</label>
		  	  <textarea name="rejectReason" rows="4" class="form-control" placeholder="请输入审核被驳回的具体理由..." role="data-remain" maxlength="200"></textarea>		
		  	  <input type="hidden" name="auditStatus" value="false" />			  	  
        	</div>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-default" data-dismiss="modal" type="button"> 取消</button>
        	<button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> 确定</button>
	      </div>
	    </form>
	  </div>
	</div>
	
  </div>
</body>
<imports>
  <script src="/static/js/jquery.form.min.js"></script>
  <script src="/static/js/viewjs/report-audit.js"></script>
</imports>