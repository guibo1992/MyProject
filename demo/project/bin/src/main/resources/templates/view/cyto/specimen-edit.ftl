<head>
  <title>标本管理</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
  <link href="/static/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 编辑标本
	    <small> 标本管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li><a href="/specimen/list.htm"> 标本管理</a></li>
	    <li class="active"> 编辑标本</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="specimenForm" class="box box-success">
	    <input type="hidden" name="id" value="${specimen.id}" />
	    <input type="hidden" name="typeId" value="${specimen.typeId}" />
	    
	    <div class="box-header with-border"> 
	      <h3 class="box-title col-md-12"> 标本基本信息</h3> 
	    </div>
	    <div class="box-body">
	      <div class="row">
	        <div class="col-md-10">
	        
		      <div class="form-group">
	            <label>标本类型</label>
			    <div class="row">
			      <div class="col-md-6">
	                <select name="type" class="chosen-select" data-placeholder="请选择标本类型" disabled data-fv-notempty="true" data-fv-notempty-message="标本类型不能为空">
	                  <option value=""></option>
	                  <#list typeList as type>
			          <option <#if specimen.typeId == type.id>selected</#if> value="${type.id}">${type.typeHead} ${type.name}</option>
			          </#list>
	                </select>
	              </div>
	            </div>
	          </div>
	          <div class="row">
              	<div class="col-md-6">
              	  <div class="form-group">
		            <label for="hisId">门诊号</label>
		            <input type="text" name="hisId" value="${specimen.hisId}" class="form-control" id="hisId" placeholder="HIS ID"/>
		          </div>
              	</div>
              	<div class="col-md-6">
              	  <div class="form-group">
		            <label for="hisId">标本编号</label>
		            <input type="text" value="${specimen.specimenNo}" class="form-control" readonly/>
		          </div>
              	</div>
              </div>
	          <div class="row">
	            <div class="col-md-6">
		          <div class="form-group">
	            	<label for="patient.name">姓名</label>
		            <input type="text" name="patient.name" value="${specimen.patient.name}" class="form-control" id="patient.name" placeholder="患者的姓名" />
		          </div>
              	</div>
	      		<div class="col-md-6">
			      <div class="form-group">
		            <label for="patient.sex">性别</label>
		            <div class="pad-top-3">
		              <div class="radio radio-success radio-inline margin-r-10">
                   	    <input id="male" name="patient.sex" type="radio" <#if specimen.patient.sex == 'M'>checked</#if> value="M">
                        <label for="male"> 男 </label>
                  	  </div>
                  	  <div class="radio radio-success radio-inline margin-r-10">
                   	    <input id="female" name="patient.sex" type="radio" <#if specimen.patient.sex == 'F'>checked</#if> value="F">
                        <label for="female"> 女  </label>
                  	  </div>
                  	  <div class="radio radio-success radio-inline">
                   	    <input id="other" name="patient.sex" type="radio" <#if specimen.patient.sex == 'X'>checked</#if> value="X">
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
		            <input type="text" name="patient.idcard" value="${specimen.patient.idcard}" class="form-control" id="name" placeholder="患者的身份证" />
		          </div>
	            </div>
	            <div class="col-md-6">
		          <div class="form-group">
	            	<label for="patient.birthdate">出生日期 </label>
                	<div class="input-group date">
				  	  <input type="text" class="form-control" name="patient.birthdate" id="patient.birthdate" value="${specimen.patient.birthdate}" placeholder="患者出生日期" />
				  	  <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
					</div>
				  </div>
				</div>
			  </div>
			  <div class="row">
	            <div class="col-md-6">
	              <div class="form-group">
		        	<label for="patient.phone">联系电话</label>
		            <input type="text" name="patient.phone" value="${specimen.patient.phone}" class="form-control" placeholder="电话号码" />
		          </div>
		        </div>
	            <div class="col-md-6">
	              <div class="form-group">
		        	<label for="patient.phone">邮箱</label>
				    <input type="text" name="patient.email" value="${specimen.patient.email}" class="form-control" placeholder="E-mail" />
				  </div>
				</div>
			  </div>
			  <div class="row">
		        <div class="col-md-6">
		          <div class="form-group">
	            	<label for="inspectionPhysician">送检医生</label>
		            <input type="text" name="inspectionPhysician" value="${specimen.inspectionPhysician}" class="form-control" id="inspectionPhysician" placeholder="送检姓名" />
		          </div>
		        </div>
		        <div class="col-md-6">
		          <div class="form-group">
	            	<label for="inspectionDept">送检科室</label>
		            <input type="text" name="inspectionDept" value="${specimen.inspectionDept}" class="form-control" id="inspectionDept" placeholder="送检科室" />
		          </div>
		        </div>
		        <#-- 
                <div class="col-md-6">
				  <div class="form-group">
		            <label for="payStatus">缴费情况</label>
		            <div>
		              <div class="radio radio-warning radio-inline margin-r-10">
                   	    <input id="paid" name="payStatus" type="radio" <#if specimen.payStatus>checked</#if> value="1">
                        <label for="paid"> 已缴 </label>
                  	  </div>
                  	  <div class="radio radio-warning radio-inline margin-r-10">
                   	    <input id="pay" name="payStatus" type="radio" <#if !specimen.payStatus>checked</#if> value="0">
                        <label for="pay"> 未缴 </label>
                  	  </div>
                  	</div>
		          </div>
		        </div>
		         -->
		      </div>
		      
		      <#-- 
	          <div class="form-group">
	            <label>临床信息/送检指征</label>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_1" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('既往有染色体异常儿生育史')>checked</#if> value="既往有染色体异常儿生育史">
                      <label for="c_1"> 既往有染色体异常儿生育史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_2" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('既往有自然流产、死胎、死产、缺陷儿生育史')>checked</#if> value="既往有自然流产、死胎、死产、缺陷儿生育史">
                      <label for="c_2"> 既往有自然流产、死胎、死产、缺陷儿生育史</label>
              	    </div>
              	  </div>
              	</div> 
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_3" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('既往有自然流产、死胎、死产、缺陷儿生育史')>checked</#if> value="孕早期致畸因子接触史">
                      <label for="c_3"> 孕早期致畸因子接触史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_4" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('既往有自然流产、死胎、死产、缺陷儿生育史')>checked</#if> value="产前唐氏复查高危异常">
                      <label for="c_4"> 产前唐氏复查高危异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_5" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('既往有自然流产、死胎、死产、缺陷儿生育史')>checked</#if> value="夫妇一方为染色体平衡易位或倒位携带者">
                      <label for="c_5"> 夫妇一方为染色体平衡易位或倒位携带者 </label>
              	    </div>
                    <input type="text" name="karyotypeAnomaly" class="form-control" value="${specimen.karyotypeAnomaly}" placeholder="核型说明"/>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_6" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('产前无创DNA检测结果异常')>checked</#if> value="产前无创DNA检测结果异常">
                      <label for="c_6"> 产前无创DNA检测结果异常 </label>
              	    </div>
                    <input type="text" name="dnaAnomaly" class="form-control" value="${specimen.dnaAnomaly}" placeholder="检测结果说明"/>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_7" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('35岁以上高龄孕妇')>checked</#if> value="35岁以上高龄孕妇">
                      <label for="c_7"> 35岁以上高龄孕妇 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_8" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('产前B超羊水过多或过少')>checked</#if> value="产前B超羊水过多或过少">
                      <label for="c_8"> 产前B超羊水过多或过少 </label>
              	    </div>
              	  </div>
	            </div>
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_9" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('家族有单基因病遗传病史')>checked</#if> value="家族有单基因病遗传病史">
                      <label for="c_9"> 家族有单基因病遗传病史 </label>
              	    </div>
                    <input type="text" name="geneticDisease" class="form-control" value="${specimen.geneticDisease}" placeholder="疾病类型说明"/>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_10" data-name="clinicalInfo" type="checkbox" <#if specimen.clinicalInfo?contains('产检B超软指标或结构异常')>checked</#if> value="产检B超软指标或结构异常">
                      <label for="c_10"> 产检B超软指标或结构异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-lg-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_other" type="checkbox" <#if specimen.otherClinicalInfo?has_content>checked</#if> value="其它">
                      <label for="c_other"> 其它</label>
              	    </div>
                    <input type="text" name="otherClinicalInfo" value="${specimen.otherClinicalInfo}" class="form-control" placeholder="其它临床信息"/>
              	  </div>
	            </div>
	          </div>
	          -->
	          
	          <div class="form-group">
	            <label>临床信息/送检指征</label>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_1" name="clinicalInfoList[0].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["既往有染色体异常儿生育史"]??>checked</#if> value="既往有染色体异常儿生育史">
                      <label for="c_1"> 既往有染色体异常儿生育史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_2" name="clinicalInfoList[1].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["既往有自然流产、死胎、死产、缺陷儿生育史"]??>checked</#if> value="既往有自然流产、死胎、死产、缺陷儿生育史">
                      <label for="c_2"> 既往有自然流产、死胎、死产、缺陷儿生育史</label>
              	    </div>
              	  </div>
              	</div> 
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_3" name="clinicalInfoList[2].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["孕早期致畸因子接触史"]??>checked</#if> value="孕早期致畸因子接触史">
                      <label for="c_3"> 孕早期致畸因子接触史 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_4" name="clinicalInfoList[3].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["产前唐氏复查高危异常"]??>checked</#if> value="产前唐氏复查高危异常">
                      <label for="c_4"> 产前唐氏复查高危异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_5" name="clinicalInfoList[4].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["夫妇一方为染色体平衡易位或倒位携带者"]??>checked</#if> value="夫妇一方为染色体平衡易位或倒位携带者">
                      <label for="c_5"> 夫妇一方为染色体平衡易位或倒位携带者 </label>
              	    </div>
                    <input type="text" name="clinicalInfoList[4].clinicalRemarks" value="${specimen.clinicalInfoMap["夫妇一方为染色体平衡易位或倒位携带者"].clinicalRemarks}" class="form-control" placeholder="核型说明"/>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_6" name="clinicalInfoList[5].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["产前无创DNA检测结果异常"]??>checked</#if> value="产前无创DNA检测结果异常">
                      <label for="c_6"> 产前无创DNA检测结果异常 </label>
              	    </div>
                    <input type="text" name="clinicalInfoList[5].clinicalRemarks" value="${specimen.clinicalInfoMap["产前无创DNA检测结果异常"].clinicalRemarks}" class="form-control" placeholder="检测结果说明"/>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_7" name="clinicalInfoList[6].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["35岁以上高龄孕妇"]??>checked</#if> value="35岁以上高龄孕妇">
                      <label for="c_7"> 35岁以上高龄孕妇 </label>
              	    </div>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_8" name="clinicalInfoList[7].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["产前B超羊水过多或过少"]??>checked</#if> value="产前B超羊水过多或过少">
                      <label for="c_8"> 产前B超羊水过多或过少 </label>
              	    </div>
              	  </div>
	            </div>
              	<div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_9" name="clinicalInfoList[8].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["家族有单基因病遗传病史"]??>checked</#if> value="家族有单基因病遗传病史">
                      <label for="c_9"> 家族有单基因病遗传病史 </label>
              	    </div>
                    <input type="text" name="clinicalInfoList[8].clinicalRemarks" value="${specimen.clinicalInfoMap["家族有单基因病遗传病史"].clinicalRemarks}" class="form-control" placeholder="疾病类型说明"/>
              	  </div>
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_10" name="clinicalInfoList[9].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap["产检B超软指标或结构异常"]??>checked</#if> value="产检B超软指标或结构异常">
                      <label for="c_10"> 产检B超软指标或结构异常 </label>
              	    </div>
              	  </div>
	            </div>
	            <div class="row">
              	  <div class="col-md-6">
              	    <div class="checkbox checkbox-primary">
               	      <input id="c_other" name="clinicalInfoList[10].clinicalInfo" type="checkbox" <#if specimen.clinicalInfoMap[""]??>checked</#if> value="">
                      <label for="c_other"> 其它</label>
              	    </div>
                    <input type="text" name="clinicalInfoList[10].clinicalRemarks" value="${specimen.clinicalInfoMap[""].clinicalRemarks}" class="form-control" placeholder="其它临床信息"/>
              	  </div>
	            </div>
	          </div>
	          
	          <#if specimen.analysisStatus>
	          <div class="form-group">
	            <label for="result">分析结果</label>
	            <p>${specimen.analysisResult}</p>
	            <div class="row">
	              <div class="col-sm-6">
	                <img src="${_static + specimen.analysisKarImg}" />
	              </div>
	              <div class="col-sm-6">
	                <img src="${_static + specimen.analysisMetImg}" />
	              </div>
	            </div>
	          </div>
	          </#if>
	          
	          <div class="form-group">
	            <label for="remarks">备注</label>
	            <div class="row">
              	  <div class="col-lg-6">
              	    <textarea name="remarks" id="remarks" class="form-control" rows="3">${specimen.remarks}</textarea>
              	  </div>
              	</div>
	          </div>
	          
	          <br/>
	          <div class="form-group has-error">
	            <button  class="btn btn-primary" type="submit"><i class="fa fa-save"></i> 保存标本信息</button>
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
  <script src="/static/js/viewjs/specimen-edit.js"></script>
</imports>