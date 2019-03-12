  <head>
  	<title>分析报告模板</title>
  	<link href="/static/css/chosen.css" rel="stylesheet">
  
    <link href="/static/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="content-wrapper" style="background-color:#ffffff;">
	  <section class="content-header">
	    <h1> 分析报告模板
	      <small> 报告管理</small>
	    </h1>
	    <ol class="breadcrumb">
	      <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	      <li> 报告管理</li>
	      <li class="active"> 分析报告模板</li>
	    </ol>
	  </section>
	  
	  <section class="content">
	    <div class="row">
	      <div class="col-lg-4">
	        <div class="panel panel-default">
	          <div class="panel-heading">
	            <h3 class="panel-title">模板设置</h3>
	          </div>
	          <form id="templateForm" class="panel-body">
		        <div class="form-group">
	              <label>模板类型</label>
	        	  <select name="typeId" id="typeId" class="form-control chosen-select" data-placeholder="请选择模板类型" data-fv-notempty="true" data-fv-notempty-message="模板类型不能为空">
	          	    <option value=""></option>
	          	    <#list typeList as type>
	                <option <#if template.typeId == type.id>selected</#if> value="${type.id}">${type.typeHead} ${type.name}</option>
	                </#list>
	        	  </select>
	            </div>
	            <div class="form-group">
                  <label>模板名称</label>
		          <input type="text" name="name" class="form-control" value="${template.name}" placeholder="模板名称" data-fv-notempty="true" data-fv-notempty-message="模板名称不能为空" maxlength="50" rule="content" data-text="#name"/>
		        </div>
		        
		        <label>报告模板可选项内容</label>
		        <div class="form-group">
		          <div class="checkbox checkbox-primary">
			    	<input type="checkbox" id="hideDetectionMethod" name="hideDetectionMethod" <#if template.hideDetectionMethod>checked</#if> value="true" data-toggle="collapse" data-target=".defaultDetectionMethod">
			    	<label for="hideDetectionMethod">隐藏检测方法</label>
			  	  </div>
			  	  <div class="collapse defaultDetectionMethod <#if !template.hideDetectionMethod>in</#if>">
		            <textarea name="defaultDetectionMethod" rows="4" class="form-control" rule="content" data-text="#detectionMethod" placeholder="样本检测方法">${template.defaultDetectionMethod!'样本检测方法'}</textarea>
		          </div>
		        </div>
		        
		        <div class="form-group">
		          <div class="checkbox checkbox-primary">
			    	<input type="checkbox" name="hideResultMetImg" id="hideResultMetImg" <#if template.hideResultMetImg>checked</#if> value="true" data-toggle="collapse" data-target=".met">
			    	<label for="hideResultMetImg">隐藏分析结果原图</label>
			  	  </div>
		        </div>
		        
		        <div class="form-group">
		          <div class="checkbox checkbox-primary">
			    	<input type="checkbox" name="hideResultSex" id="hideResultSex" <#if template.hideResultSex>checked</#if> value="true" rule="toggle" data-target=".resultSex"  aria-expanded="true" >
			    	<label for="hideResultSex">隐藏分析结果中性别信息</label>
			  	  </div>
		        </div>
		        
		        <div class="form-group">
		          <div class="checkbox checkbox-primary">
			    	<input type="checkbox" name="hideRemarks" id="hideRemarks" <#if template.hideRemarks>checked</#if> value="true" data-toggle="collapse" data-target=".reportRemarks">
			    	<label for="hideRemarks">隐藏备注信息</label>
			  	  </div>
		        </div>
		        <div class="form-group collapse reportRemarks <#if !template.hideRemarks>in</#if>">
		          <textarea name="defaultRemarks" rows="4" rule="content" data-text="#remarksContent" class="form-control" placeholder="默认报告备注信息">${template.defaultDetectionMethod!'分析报告的备注信息'}</textarea>
		        </div>
		        
	          	<div class="form-group">
				  <label>审核人</label>
		          <div class="row" id="auditors">
		            <div class="col-md-6">
	            	  <select name="masterAuditorId" class="chosen-select" data-placeholder="选择审核人" data-fv-notempty="true" data-fv-notempty-message="审核人不能为空">
	            	    <option value=""></option>
	            	    <#list userList as ad>
	            	    <option <#if template.masterAuditorId == ad.id>selected</#if> value="${ad.id}">${ad.name}</option>
	            	    </#list>
	            	  </select>
	                </div>
		            <div class="col-md-6">
		              <select name="deputyAuditorId" class="chosen-select" data-placeholder="选择副审人" >
		                <option value=""></option>
	            	    <#list userList as ad>
	            	    <option <#if template.deputyAuditorId == ad.id>selected</#if> value="${ad.id}">${ad.name}</option>
	            	    </#list>
		              </select>
		        	</div>
		       	  </div>
		       	</div>
		       	
		       	<br/>
		       	<div class="form-group">
		       	  <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 保存模板</button>
		       	  <button type="button" id="print" class="btn btn-default pull-right"><i class="fa fa-print"></i> Test Print</button>
		       	</div>
	          </form>
	        </div>
	      </div>
	      <div class="col-lg-8">
	        <div id="report-page" class="panel panel-default report-example">
	          <div class="panel-body">
	            <div class="report-heading">
	              <img class="logo" src="/static/images/logo.png">
	              <h3 class="text-center">上海乐辰生物科技有限公司</h3>
	              <h4 id="name" name="name" class="text-center">外周血染色体检查和核型分析报告</h4>
	            </div>
	            
	            <div class="part">
	              <div class="row">
	                <div class="col-xs-4">
	                  <div>
	                    <label>姓 名：</label> 
	                    <span name="patientName">张某某</span>
	                  </div>
	                  <div>
	                    <label>门诊号：</label> 
	                    <span name="hisId">H20189832000231</span>
	                  </div>
	                  <div>
	                    <label>送检样本：</label> 
	                    <span name="inspectionType">外周血</span>
	                  </div>
	                </div>
	                <div class="col-xs-4">
	                  <div>
	                    <label>性 别：</label> 
	                    <span name="patientSex">女</span>
	                  </div>
	                  <div>
	                    <label>送检科室：</label> 
	                    <span name="inspectionDept">辅助生殖科</span>
	                  </div>
	                  <div>
	                    <label>采样日期：</label> 
	                    <span name="specimenDate">2018-06-24</span>
	                  </div>
	                </div>
	                <div class="col-xs-4">
	                  <div>
	                    <label>年 龄：</label> 
	                    <span name="patientAge">28岁</span>
	                  </div>
	                  <div>
	                    <label>送检医生：</label> 
	                    <span name="inspectionPhysician">李医生</span>
	                  </div>
	                  <div>
	                    <label>样本号：</label> 
	                    <span name="specimenNo">P2018061200001</span>
	                  </div>
	                </div>
	              </div>
	              
	              <div class="item">
	                <label class="pull-left">送检指征：</label>
	                <div name="clinicalInfo" class="margin-l-text-5">既往有自然流产、死胎、死产、缺陷儿生育史；产检B超软指标或结构异常</div>
	              </div>
	              <div class="optional collapse defaultDetectionMethod <#if !template.hideDetectionMethod>in</#if>">
	                <div class="item">
                      <label class="pull-left">检测方法：</label>
                      <div id="detectionMethod" name="detectionMethod" class="margin-l-text-5">此处显示具体一定权威性的检测方法，也可自定义详细的检测方法.</div>
                    </div>
	              </div>
	            </div>
	            
	            <div class="part">
	              <div class="item result">
	                <label>检测结果：</label> 
	                <span name="analysisResult" class="resultSex"> 46，XY</span>
	                <span name="analysisResult" class="resultSex collapse">46，X?</span>
	              </div>
	              
	              <div class="result-img">
	                <div class="kar">
	                  <img name="analysedKarImg" src="/static/images/0ad1f45b-257c-4cdc-a76f-de341c9da008.jpeg" data-value="/home/sinochrome/0ad1f45b-257c-4cdc-a76f-de341c9da008.jpeg"  />
	                  <div class="h collapse resultSex <#if template.hideResultSex>in</#if>"></div>
	                </div>
	                <div class="met collapse in">
	                  <img name="analysedMetImg" src="/static/images/0aa08556-cda1-4615-9a7e-1421345f8081.jpeg" data-value="/home/sinochrome/0aa08556-cda1-4615-9a7e-1421345f8081.jpeg" />
	                </div>
	              </div>
	              
	              <div id="interpretatin" class="optional collapse in">
	                <div class="item">
	                  <label class="pull-left">结果解释：</label>
	                  <div name="resultInterpretation" class="margin-l-text-5">此处显示对分析结果的解释</div>
	                </div>
	              </div>
	            </div>
	            
	            <div class="part">
	              <div class="item">
	                <label class="pull-left">结论：</label> 
	                <div name="reportConclusion" class="margin-l-text-3">此处显示对样本分析后得出的总结性的观点</div>
	              </div>
	              <div class="optional collapse reportRemarks <#if !template.hideRemarks>in</#if>">
	                <div class="item">
	                  <label class="pull-left">备注：</label> 
	                  <div id="remarksContent" name="remarks" class="margin-l-text-3">此处显示对本份报告的备注信息</div>
	                </div>
	              </div>
	              <div class="report-footer">
	                <div class="row">
	              	  <div class="col-xs-4">
	              	    <label>检验人：</label> 
	              	    <span name="laboratorian">检验师</span>
	              	  </div>
			    	  <div class="col-xs-4">
			    	    <label>审核人：</label>
			    	    <span id="masterAuditorId" name="masterAuditor"></span>&nbsp;&nbsp;
			    	    <span id="deputyAuditorId" name="deputyAuditor"></span>
			    	  </div>
			    	  <div class="col-xs-4 text-center">
			    	    <label>日期：</label>
			    	    <span id="date" name="reportDate">2018-01-25</span>
			    	  </div>
			        </div>
	              </div>
	            </div>
	            
	          </div>
	        </div>
	      </div>
	    </div>
	  </section>
    </div>	
  </body>
  <imports>
  <script src="/static/js/jquery.form.min.js"></script>
  <script src="/static/js/bootstrap-table.min.js"></script>
  <script src="/static/js/bootstrap-table-zh-CN.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/moment.min.js"></script>
  <script src="/static/js/viewjs/report-template.js"></script>
  </imports>