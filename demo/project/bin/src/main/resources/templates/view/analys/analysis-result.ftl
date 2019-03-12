<head>
  <title>核型分析结果</title>
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 核型分析结果
	    <small> 核型分析</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li> 核型分析</li>
	    <li class="active"> 核型分析结果</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="reportForm" class="box box-success">
	    <div class="box-header with-border"> 
	      <h3 class="box-title col-md-12"> 核型分析结果</h3> 
	    </div>
	    <div class="box-body">
	      <div class="pad">
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
          </div>
	    </div>
	    
	    <div class="box-header with-border"> 
	      <h3 class="box-title col-md-12"> 分析结果</h3> 
	    </div>
	    <div class="box-body">
	      <div class="pad">
	        <div class="form-group">
	           <label>检测结果：</label> ${specimen.analysisResult}
	           <input type="hidden" name="analysisResult" value="${specimen.analysisResult}" />
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
	      </div>
	    </div>
	    
        
        <div class="box-body">
	      <div class="pad">
	        <div class="row">
          	  <div class="col-xs-4">
          	    <label>检验人：</label> ${specimen.laboratorian}
          	    <input type="hidden" name="laboratorian" value="${specimen.laboratorian}" />
          	  </div>
	    	  <div class="col-xs-4 text-center">
	    	    <label>日期：</label>
	    	    <span id="date" name="reportDate">2018-01-25</span>
	    	    <input type="hidden"  name="reportDate" value="2018-01-25" />
	    	  </div>
	        </div>
          </div>
        </div>
        
        <div class="box-footer">
          <a href="/karyotype/analysis/list.html" class="btn btn-default btn-lg margin-bottom"><i class="fa fa-reply"></i> 返 回</a>
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