<#setting classic_compatible=true>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	<title>分析报告详情</title>
  	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
  	<link href="/static/css/font-awesome.min.css" rel="stylesheet">
  	<link href="/static/css/pace-theme-minimal.min.css" rel="stylesheet">
  	<link href="/static/css/jquery-confirm.min.css" rel="stylesheet">
  	<link href="/static/css/animate.min.css" rel="stylesheet">
  	<link rel="stylesheet" href="/static/css/skin.css" />
  	<link rel="stylesheet" href="/static/css/style.css" />
  	<!--[if lt IE 9]>
  	<script src="/static/js/html5shiv.min.js"></script>
  	<script src="/static/js/respond.min.js"></script>
  	<![endif]-->
  </head>
  <body class="skin sidebar-mini wysihtml5-supported">
    <div class="wrapper">

	  <div class="panel panel-default report-example report margin-top-30 margin-bottom-30">
	    <div class="panel-body">
	    
          <div class="report-heading">
            <#if _sysConfig.hospLogo?has_content><img class="logo" src="${_static + _sysConfig.hospLogo}"></#if>
            <h3 class="text-center">${_sysConfig.hospName}</h3>
            <h4 id="name" name="name" class="text-center">${report.name}</h4>
            <div class="clearfix"></div>
          </div>
        
          <div class="part">
            <div class="row">
              <div class="col-xs-4">
	            <#if report.template.patientName>
	              <div>
	                <label>姓 名：</label> ${report.patientName}
	              </div>
	            </#if>
	            <#if report.template.hisId>
                  <div>
                    <label>门诊号：</label> ${report.hisId}
                  </div>
                </#if>
                <#if report.template.inspectionType>
                  <div>
                    <label>标本类型：</label> ${report.inspectionType}
                  </div>
                </#if>
              </div>
              <div class="col-xs-4">
                <#if report.template.patientSex>
                  <div>
                    <label>性 别：</label> ${report.patientSex}
                  </div>
                </#if>
                <#if report.template.inspectionDept>
                  <div>
                    <label>送检科室：</label> ${report.inspectionDept}
                  </div>
                </#if>
                <#if report.template.specimenDate>
                  <div>
                    <label>送检日期：</label> ${report.specimenDate}
                  </div>
                </#if>
              </div>
              <div class="col-xs-4">
                <#if report.template.patientAge>
                  <div>
                    <label>年 龄：</label> <#if report.patientAge?has_content>${report.patientAge}岁</#if>
                  </div>
                </#if>
                <#if report.template.inspectionPhysician>
                  <div>
                    <label>送检医生：</label> ${report.inspectionPhysician}
                  </div>
                </#if>
                <#if report.template.specimenNo>
                  <div>
                    <label>样本号：</label> ${report.specimenNo}
                  </div>
                </#if>
              </div>
            </div>
          
            <#if report.template.clinicalInfo>
              <div class="item">
                <label class="pull-left">指征：</label> 
                <div class="margin-l-text-5">${report.clinicalInfo}&nbsp;</div>
              </div>
            </#if>
            
            <#if report.template.hideDetectionMethod>
              <div class="optional <#if report.template.hideDetectionMethod>collapse</#if>">
                <div class="item">
                  <label class="pull-left">检测方法：</label>
                  <div class="margin-l-text-5">${report.detectionMethod}.</div>
                </div>
              </div>
            </#if>
            <div class="clearfix"></div>
          </div>
        
          <div class="part">
            
          
            <div class="result-img">
              <div class="kar">
                <img src="${_static + report.analysisKarImg}">
              </div>
              
              <div class="met <#if report.template.hideResultMetImg>collapse</#if>">
                <img src="${_static + report.analysisMetImg}" />
              </div>
            </div>
            <div class="item result">
              <label class="pull-left">检测结果：</label> 
              <div class="margin-l-text-5"><#if report.template.hideResultSex>${report.analysisResult?replace('Y|y', '?')}<#else>${report.analysisResult}</#if></div>
            </div>
          
            <div id="interpretatin" class="optional  <#if report.template.hideResultInterpert>collapse</#if>">
              
            </div>
          </div>
        
          <div class="part">
            
            <div class="optional <#if template.hideRemarks>collapse</#if>">
              <div class="item">
                <label class="pull-left">备注：</label> 
                <div class="margin-l-text-3">${report.remarks}</div>\
              </div>
            </div>
            <br /><br /><br />
            <div class="report-footer">
              <div class="row">
          	    <div class="col-xs-4">
          	      <label>检验者：</label> ${report.laboratorian}
          	    </div>
	    	    <div class="col-xs-4">
	    	      <label>审核者：</label> ${report.masterAuditor} &nbsp;&nbsp; ${report.deputyAuditor}
	    	    </div>
	    	    <div class="col-xs-4 text-center">
	    	      <label>报告日期：</label> ${report.reportDate?date}
	    	    </div>
	          </div>
            </div>
          </div>
        </div>
	  </div>
	</section>
  </body>
  
  <script src="/static/js/jquery.min.js"></script>
  <script src="/static/js/fastclick.min.js"></script>
  <script src="/static/js/bootstrap.min.js"></script>
  <script src="/static/js/pace.min.js"></script>
  <script src="/static/js/jquery.slimscroll.min.js"></script>
</html>
