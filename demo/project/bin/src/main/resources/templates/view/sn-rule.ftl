<head>
  <title>编码规则</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 标本编码生成规则
	    <small> 系统设置</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 标本编号规则</li>
	  </ol>
	</section>
	
	<section class="content">
	  <form id="ruleForm" method="post">
	    <input type="hidden" name="id" value="${rule.id}" />
	    
	    <div class="row">
	      <div class="col-lg-12">
	    	<div class="box box-success">
		      <div class="box-header with-border">
		        <h3 class="box-title col-md-12"><i class="fa fa-paperclip"></i> 编号生成规则</h3>
		      </div>
		      <div class="box-body">
	        	<div class="col-md-12">
		          <div class="form-group">
		            <label>前缀</label>
		            <div class="row">
		              <div class="col-md-5">
	                    <div class="checkbox checkbox-danger">
               	          <input id="isTypeHead" name="isTypeHead" <#if rule.isTypeHead>checked</#if> type="checkbox" value="1">
                          <label for="isTypeHead"> 使用标本类型表示符号 </label>
              	        </div>
              	        <#-- 
              	        <div id="typeList">
              	          <select name="typeList" class="chosen-select" multiple data-placeholder="选择需要的类型项" style="display:none;">
			                <option value="P">P -- PB 外周血 </option>
	                  	    <option value="M">M -- BM 骨髓标本</option>
	                  	    <option value="A">A -- AF 羊水标本</option>
	                  	    <option value="B">B -- CB 脐带血</option>
	                  	    <option value="C">C -- CV 绒毛</option>
			              </select>
			            </div>
			             -->
              	      </div>
              	      <div class="col-md-7">
              	        <p class="pad-top-5 text-muted">编号中是否使用类型表示符号, 如：<span class="text-danger blod">P</span>2017..., <span class="text-danger blod">B</span>2018..</p>
              	      </div>
              	    </div>
                  </div>
		          <div class="form-group">
		            <label>日期/时间格式</label>
		            <div class="row">
		              <div class="col-md-5">
			            <div class="radio radio-danger radio-inline margin-r-10">
	               	      <input id="yy" name="timeFormat" type="radio" <#if rule.timeFormat == 'yy'>checked</#if> value="yy">
	                      <label for="yy"> YY </label>
	              	    </div>
	              	    <div class="radio radio-danger radio-inline margin-r-10">
	               	      <input id="yyyy" name="timeFormat" type="radio" <#if rule.timeFormat == 'yyyy'>checked</#if> value="yyyy">
	                      <label for="yyyy"> YYYY </label>
	              	    </div>
	              	    
	              	    <div class="radio radio-danger radio-inline margin-r-10">
	               	      <input id="yyMM" name="timeFormat" type="radio" <#if rule.timeFormat == 'yyMM'>checked</#if> value="yyMM">
	                      <label for="yyMM"> YYMM </label>
	              	    </div>
	              	    <div class="radio radio-danger radio-inline">
	               	      <input id="yyyyMM" name="timeFormat" type="radio" <#if rule.timeFormat == 'yyyyMM'>checked</#if> value="yyyyMM">
	                      <label for="yyyyMM"> YYYYMM </label>
	              	    </div>
              	      </div>
              	      <div class="col-md-7">
              	        <p class="pad-top-5 text-muted">编号中日期部份格式, 如18, 2018, 1805, 201805</p> 
              	      </div>
              	    </div>
		          </div>
		          <div class="form-group">
		            <label>序列号长度</label>
		            <div class="row">
		              <div class="col-md-5">
		                <div class="radio radio-primary radio-inline margin-r-10">
		                  <input type="radio" name="snLength" id="four" <#if rule.snLength == 4>checked</#if> value="4">
		                  <label for="four"> 4位长度 </lable>
		                </div>
		                <div class="radio radio-primary radio-inline margin-r-10">
		                  <input type="radio" name="snLength" id="five" <#if rule.snLength == 5>checked</#if> value="5">
		                  <label for="five"> 5位长度 </lable>
		                </div>
		                <div class="radio radio-primary radio-inline margin-r-10">
		                  <input type="radio" name="snLength" id="six" <#if rule.snLength == 6>checked</#if> value="6">
		                  <label for="six"> 6位长度 </lable>
		                </div>
              	      </div>
              	      <div class="col-md-7">
              	        <p class="pad-top-5 text-muted">指定序列号位数(4-6位)，序列号从0001到999999</p>
              	      </div>
              	    </div>
		          </div>
		          <div class="form-group">
		            <label >单/双线</label>
		            <div class="row">
		              <div class="col-md-5">
			            <div class="radio radio-success radio-inline margin-r-10">
	               	      <input id="lineA" name="lineType" type="radio" <#if rule.lineType == 'A'>checked</#if> value="A">
	                      <label for="lineA"> A线 </label>
	              	    </div>
	              	    <div class="radio radio-success radio-inline">
	               	      <input id="lineB" name="lineType" type="radio" <#if rule.lineType == 'B'>checked</#if> value="B">
	                      <label for="lineB"> AB线 </label>
	              	    </div>
              	      </div>
              	      <div class="col-md-7">
              	        <p class="pad-top-5 text-muted">A线: 单份打印, AB线: 双份打印</p>
              	      </div>
              	    </div>
		          </div>
		          
		          <div class="form-group">
		            <label for="linePrintCount">默认玻片数量</label>
		            <div class="row">
		              <div class="col-md-5">
		                <#--
		                <div class="margin-btm-5">
		                  <div class="checkbox checkbox-inline">
	               	        <input id="noLinePrintCharIndex" name="isLinePrintCharIndex" type="radio" <#if !rule.isLinePrintCharIndex>checked</#if> value="true">
	                        <label for="noLinePrintCharIndex"> 用数量表示 </label>
	              	      </div>
		                  <div class="checkbox checkbox-inline">
	               	        <input id="isLinePrintCharIndex" name="isLinePrintCharIndex" type="radio" <#if rule.isLinePrintCharIndex>checked</#if> value="true">
	                        <label for="isLinePrintCharIndex"> 用字母表示 </label>
	              	      </div>
		                </div>
		                 -->
		                <div class="row">
		                  <div class="col-md-6">
		            		
		            		<select class="chosen-select" name="linePrintCount" id="linePrintCount">
		            		  <option data-type="number" <#if rule.linePrintCount == 1>selected</#if> value="1">1 片</option>
		            		  <option data-type="number" <#if rule.linePrintCount == 2>selected</#if> value="2">2 片</option>
		            		  <option data-type="number" <#if rule.linePrintCount == 3>selected</#if> value="3">3 片</option>
		            		  <option data-type="number" <#if rule.linePrintCount == 4>selected</#if> value="4">4 片</option>
		            		  <option data-type="number" <#if rule.linePrintCount == 5>selected</#if> value="5">5 片</option>
		            		  <option data-type="number" <#if rule.linePrintCount == 6>selected</#if> value="6">6 片</option>
		            		  <#-- 
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 1>selected</#if> value="1">a</option>
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 2>selected</#if> value="2">b</option>
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 3>selected</#if> value="3">c</option>
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 4>selected</#if> value="4">d</option>
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 5>selected</#if> value="5">e</option>
		            		  <option class="hide" data-type="char" <#if rule.linePrintCount == 6>selected</#if> value="6">f</option>
		            		   -->
		            		</select>
		            	  </div>
		            	</div>
		              </div>
		              <div class="col-md-7">
		                <p class="pad-top-5 text-muted">每份标本打印玻片的默认数量, 使用1,2,3,4,5,6 或a,b,c,d,e,f来表示</p>
              	      </div>
		            </div>
		          </div>
		        </div>
		      </div>
		      
		      <div class="box-footer">
	            <div class="col-md-10">
	              <button type="submit" class="btn btn-success" type="button"><i class="fa fa-save"></i> 保存设置</button>
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
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/viewjs/sn-rule.js"></script>
</imports>