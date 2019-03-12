<head>
  <title>标本管理</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>

<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 新增标本
	    <small> 标本管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li><a href="/specimen/list.html"> 标本管理</a></li>
	    <li class="active"> 新增标本</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="specimenForm" class="box box-success">
	    <div class="box-header with-border"> 
	      <h3 class="box-title col-md-12"> 标本基本信息</h3> 
	    </div>
	    
	    <div class="box-body">
	      <div class="row">
	        <div class="col-md-10">
	        
	          <div class="row">
				<div class="col-md-6">
		      	  <div class="form-group">
	            	<label>标本类型</label>
	                <select id="typehead" name="typeId" class="chosen-select" data-placeholder="请选择标本类型" data-fv-notempty="true" data-fv-notempty-message="标本类型不能为空" >
	                  
	                  <#list typeList as type>
			          	<option value="${type.typeHead}">${type.typeHead} ${type.name}</option>
			          </#list>
	                </select>	                
	              </div>
	            </div>  
	            
	            <div class="col-md-6">
	              <div class="form-group">
	            	<label>&nbsp;</label>
	                <div class="checkbox checkbox-danger">
		       	      <input id="autoSave" type="checkbox" value="true" disabled>
		              <label for="autoSave"> 自动保存 </label>
		      	    </div>
	              </div>
	            </div>
	          </div>
	          <div class="row">
              	<div class="col-md-6">
              	  <div class="form-group">
		            <label for="hisId">门诊号</label>
		            <input type="text" name="hisId" class="form-control" id="hisId" placeholder="HIS ID"/>
		          </div>
              	</div>
              </div>
	          <div class="row">
	            <div class="col-md-6">
		          <div class="form-group">
	            	<label for="patient.name">姓名</label>
		            <input type="text" name="patient.name" class="form-control" id="patient.name" placeholder="患者的姓名" />
		          </div>
              	</div>
	      		<div class="col-md-6">
			      <div class="form-group">
		            <label for="patient.sex">性别</label>t
		            <div class="pad-top-3">
		              <div class="radio radio-success radio-inline margin-r-10">
                   	    <input id="male" name="patient.sex" type="radio" value="M">
                        <label for="male"> 男 </label>
                  	  </div>
                  	  <div class="radio radio-success radio-inline margin-r-10">
                   	    <input id="female" name="patient.sex" type="radio" value="F">
                        <label for="female"> 女  </label>
                  	  </div>
                  	  <div class="radio radio-success radio-inline">
                   	    <input id="other" name="patient.sex" type="radio" value="O">
                        <label for="other"> 其他  </label>
                  	  </div>
                  	</div>
		          </div>
		        </div>
	      	  </div>
	          <div class="row">
	            <div class="col-md-6">
		          <div class="form-group">
	            	<label for="patient.idcard">身份证</label>
		            <input type="text" name="patient.idcard" class="form-control" id="name" placeholder="患者的身份证" />
		          </div>
	            </div>
	            <div class="col-md-6">
		          <div class="form-group">
	            	<label for="patient.birthday">出生日期 </label>
                	<div class="input-group date">
				  	  <input type="text" class="form-control" name="patient.birthdate" id="patient.birthdate" placeholder="患者出生日期"  autocomplete="off" />
				  	  <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
					</div>
				  </div>
				</div>
			  </div>
			  <div class="row">
	            <div class="col-md-6">
	              <div class="form-group">
		        	<label for="patient.phone">联系电话</label>
		            <input type="text" name="patient.phone" class="form-control" placeholder="电话号码" />
		          </div>
		        </div>
	            <div class="col-md-6">
	              <div class="form-group">
		        	<label for="patient.phone">邮箱</label>
				    <input type="text" name="patient.email" class="form-control" placeholder="E-mail" />
				  </div>
				</div>
			  </div>
			  <div class="row">
			    <div class="col-md-6">
		          <div class="form-group">
	            	<label for="inspectionPhysician">送检医生</label>
		            <input type="text" name="inspectionPhysician" class="form-control" id="inspectionPhysician" placeholder="送检姓名" />
		          </div>
		        </div>
		        <div class="col-md-6">
		          <div class="form-group">
	            	<label for="inspectionDept">送检科室</label>
		            <input type="text" name="inspectionDept" class="form-control" id="inspectionDept" placeholder="送检科室" />
		          </div>
		        </div>
		        <#-- 
                <div class="col-md-6">
				  <div class="form-group">
		            <label for="patient.payStatus">缴费情况</label>
		            <div class="pad-top-3">
		              <div class="radio radio-warning radio-inline margin-r-10">
                   	    <input id="paid" name="patient.payStatus" type="radio" value="1">
                        <label for="paid"> 已缴 </label>
                  	  </div>
                  	  <div class="radio radio-warning radio-inline margin-r-10">
                   	    <input id="pay" name="patient.payStatus" type="radio" value="2">
                        <label for="pay"> 未缴 </label>
                  	  </div>
                  	</div>
		          </div>
		        </div>
		         -->
		      </div>
		      
		      <div class="form-group" id="clinicalInfos">
	            <label>临床信息/送检指征</label>
			    <div class="row">
	              <div class="col-md-6" id="info">
	              	<#list list as type>	          
		              	<div class="checkbox checkbox-primary margin-bottom-3">
	                      <label for="c_1"> ${type.information} </label>
	              	    </div>
	              	    <div class="clearfix"></div>
	              	</#list>
	              <#-- 
	            	<div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" value="既往有染色体异常儿生育史">
                      <label for="c_1"> 既往有染色体异常儿生育史 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_2" name="clinicalInfoList[1].clinicalInfo" type="checkbox" value="既往有自然流产、死胎、死产、缺陷儿生育史">
                      <label for="c_2"> 既往有自然流产、死胎、死产、缺陷儿生育史</label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_3" name="clinicalInfoList[2].clinicalInfo" type="checkbox" value="孕早期致畸因子接触史">
                      <label for="c_3"> 孕早期致畸因子接触史 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_4" name="clinicalInfoList[3].clinicalInfo" type="checkbox" value="产前唐氏复查高危异常">
                      <label for="c_4"> 产前唐氏复查高危异常 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_5" name="clinicalInfoList[4].clinicalInfo" type="checkbox" value="夫妇一方为染色体平衡易位或倒位携带者"  data-toggle="collapse" data-target="#clinicalInfo4" aria-expanded="false" >
                      <label for="c_5"> 夫妇一方为染色体平衡易位或倒位携带者 </label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo4">
                      <input type="text" name="clinicalInfoList[4].clinicalRemarks" class="form-control" placeholder="核型说明"/>
                    </div>
                    <div class="clearfix"></div>
                    
                    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_6" name="clinicalInfoList[5].clinicalInfo" type="checkbox" value="产前无创DNA检测结果异常"  data-toggle="collapse" data-target="#clinicalInfo5" aria-expanded="false" >
                      <label for="c_6"> 产前无创DNA检测结果异常 </label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo5">
                      <input type="text" name="clinicalInfoList[5].clinicalRemarks" class="form-control" placeholder="DNA检测结果说明"/>
                    </div>
                    <div class="clearfix"></div>
	              </div>
	              
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_7" name="clinicalInfoList[6].clinicalInfo" type="checkbox" value="35岁以上高龄孕妇">
                      <label for="c_7"> 35岁以上高龄孕妇 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_8" name="clinicalInfoList[7].clinicalInfo" type="checkbox" value="产前B超羊水过多或过少">
                      <label for="c_8"> 产前B超羊水过多或过少 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_9" name="clinicalInfoList[8].clinicalInfo" type="checkbox" value="家族有单基因病遗传病史"  data-toggle="collapse" data-target="#clinicalInfo8">
                      <label for="c_9"> 家族有单基因病遗传病史 </label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo8">
                      <input type="text" name="clinicalInfoList[8].clinicalRemarks" class="form-control" placeholder="疾病类型说明"/>
                    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_10" name="clinicalInfoList[9].clinicalInfo" type="checkbox" value="产检B超软指标或结构异常">
                      <label for="c_10"> 产检B超软指标或结构异常 </label>
              	    </div>
              	    <div class="clearfix"></div>
              	    
              	    <div class="checkbox checkbox-primary margin-bottom-3">
               	      <input id="c_other" name="clinicalInfoList[10].clinicalInfo" type="checkbox" value="" data-toggle="collapse" data-target="#clinicalInfo10">
                      <label for="c_other"> 其它</label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo10">
                      <input type="text" name="clinicalInfoList[10].clinicalRemarks" class="form-control" placeholder="其它临床信息"/>
                    </div>
              		<div class="clearfix"></div>
              		 -->
              	  </div>
              	</div>
		      </div>
		         <#-- 
	          <div class="form-group">
	            <label>临床信息/送检指征</label>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" value="既往有染色体异常儿生育史">
                      <label for="c_1"> 既往有染色体异常儿生育史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_2" name="clinicalInfoList[1].clinicalInfo" type="checkbox" value="既往有自然流产、死胎、死产、缺陷儿生育史">
                      <label for="c_2"> 既往有自然流产、死胎、死产、缺陷儿生育史</label>
              	    </div>
              	  </div>
              	</div> 
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_3" name="clinicalInfoList[2].clinicalInfo" type="checkbox" value="孕早期致畸因子接触史">
                      <label for="c_3"> 孕早期致畸因子接触史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_4" name="clinicalInfoList[3].clinicalInfo" type="checkbox" value="产前唐氏复查高危异常">
                      <label for="c_4"> 产前唐氏复查高危异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_5" name="clinicalInfoList[4].clinicalInfo" type="checkbox" value="夫妇一方为染色体平衡易位或倒位携带者"  data-toggle="collapse" data-target="#clinicalInfo4" aria-expanded="false" >
                      <label for="c_5"> 夫妇一方为染色体平衡易位或倒位携带者 </label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo4">
                    <input type="text" name="clinicalInfoList[4].clinicalRemarks" class="form-control" placeholder="核型说明"/>
                    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_6" name="clinicalInfoList[5].clinicalInfo" type="checkbox" value="产前无创DNA检测结果异常"  data-toggle="collapse" data-target="#clinicalInfo5" aria-expanded="false" >
                      <label for="c_6"> 产前无创DNA检测结果异常 </label>
              	    </div>
              	    <div class="collapse" id="clinicalInfo5">
                    <input type="text" name="clinicalInfoList[5].clinicalRemarks" class="form-control" placeholder="检测结果说明"/>
                    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_7" name="clinicalInfoList[6].clinicalInfo" type="checkbox" value="35岁以上高龄孕妇">
                      <label for="c_7"> 35岁以上高龄孕妇 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_8" name="clinicalInfoList[7].clinicalInfo" type="checkbox" value="产前B超羊水过多或过少">
                      <label for="c_8"> 产前B超羊水过多或过少 </label>
              	    </div>
              	  </div>
	            </div>
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_9" name="clinicalInfoList[8].clinicalInfo" type="checkbox" value="家族有单基因病遗传病史">
                      <label for="c_9"> 家族有单基因病遗传病史 </label>
              	    </div>
                    <input type="text" name="clinicalInfoList[8].clinicalRemarks" class="form-control" placeholder="疾病类型说明"/>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_10" name="clinicalInfoList[9].clinicalInfo" type="checkbox" value="产检B超软指标或结构异常">
                      <label for="c_10"> 产检B超软指标或结构异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_other" name="clinicalInfoList[10].clinicalInfo" type="checkbox" value="">
                      <label for="c_other"> 其它</label>
              	    </div>
                    <input type="text" name="clinicalInfoList[10].clinicalRemarks" class="form-control" placeholder="其它临床信息"/>
              	  </div>
	            </div>
	          </div>
	           -->
	          
	          <div class="form-group">
	            <label for="remarks">备注</label>
	            <div class="row">
              	  <div class="col-lg-6">
              	    <textarea name="remarks" id="remarks" class="form-control" rows="3"></textarea>
              	  </div>
              	</div>
	          </div>
	          
	          <br/>
	          <div class="form-group has-error">
	            <div class="row">
	              <div class="col-md-4">
        	    	<button  class="btn btn-primary" type="submit"><i class="fa fa-save"></i> 保存标本信息</button>
        	      </div>
        	      <div class="col-md-8">
        	        <a href="/config/serial/rule.html" class="help-block pull-left"><#if unconfigMessage?has_content>${unconfigMessage}</#if></a>
        	      </div>
        	    </div>
	          </div>
		    </div>
		  </div>
		</div>
	  </form>
	</section>
  </div>
</body>

<imports>
  <script src="/static/js/bootstrap-datepicker.min.js"></script>
  <script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
  <script src="/static/js/chosen.jquery.min.js"></script> 
  <script src="/static/js/viewjs/specimen-add.js"></script>
</imports>
