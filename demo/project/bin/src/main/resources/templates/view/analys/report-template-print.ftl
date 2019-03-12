<#setting classic_compatible=true>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	<title>分析报告模板打印测试</title>
  	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
  	<link href="/static/css/font-awesome.min.css" rel="stylesheet">
  	<link href="/static/css/pace-theme-minimal.min.css" rel="stylesheet">
  	<link href="/static/css/jquery-confirm.min.css" rel="stylesheet">
  	<link href="/static/css/animate.min.css" rel="stylesheet">
  	<link rel="stylesheet" href="/static/css/skin.css" />
  	<link rel="stylesheet" href="/static/css/style.css" />
  	<link rel="stylesheet" href="/static/css/formValidation.css" />
  	<!--[if lt IE 9]>
  	<script src="/static/js/html5shiv.min.js"></script>
  	<script src="/static/js/respond.min.js"></script>
  	<![endif]-->
  	<script> var _static = '${_static}'; </script>
  </head>
  <body>
  <div class="print container">
    ${printContent}
    <#-- 
    <div id="report-page" class="panel panel-default report">
      <div class="panel-body">
        <div class="report-heading">
          <img class="logo" src="/static/images/logo.png" />
          <h3 class="text-center">${_sys_config.hospNamess} 上海乐辰生物科技有限公司</h3>
          <h4 id="name" class="text-center">外周血染色体检查和核型分析报告</h4>
        </div>
        
        <div class="part">
          <div class="row">
            <div class="col-xs-4">
              <div>
                <label>姓 名：</label> 张某某
              </div>
              <div>
                <label>门诊号：</label> H20189832000231
              </div>
              <div>
                <label>送检样本：</label> 外周血
              </div>
            </div>
            <div class="col-xs-4">
              <div>
                <label>性 别：</label> 女
              </div>
              <div>
                <label>送检科室：</label> 辅助生殖科
              </div>
              <div>
                <label>采样日期：</label> 2018-06-24
              </div>
            </div>
            <div class="col-xs-4">
              <div>
                <label>年 龄：</label> 28岁
              </div>
              <div>
                <label>送检医生：</label> 李医生
              </div>
              <div>
                <label>样本号：</label> P2018061200001
              </div>
            </div>
          </div>
          
          <div class="item">
            <label>送检指征：</label>
            <span>既往有自然流产、死胎、死产、缺陷儿生育史；产检B超软指标或结构异常</span>
          </div>
          <div class="optional collapse in defaultDetectionMethod">
            <div class="item">
              <label>检测方法：</label>
              <span id="detectionMethod" data-default="此处显示具体一定权威性的检测方法，也可自定义详细的检测方法">此处显示具体一定权威性的检测方法，也可自定义详细的检测方法.</span>
            </div>
          </div>
        </div>
        
        <div class="part">
          <div class="item result">
            <label>检测结果：</label> 【46，X <span class="resultSex">Y</span><span class="resultSex collapse">█</span>】
          </div>
          
          <div class="result-img">
            <div class="kar">
              <img src="/static/images/0ad1f45b-257c-4cdc-a76f-de341c9da008.jpeg" >
              <div class="h collapse resultSex"></div>
            </div>
            <div class="met collapse in">
              <img src="/static/images/0aa08556-cda1-4615-9a7e-1421345f8081.jpeg" >
            </div>
          </div>
          
          <div id="interpretatin" class="optional collapse in">
            <div class="item">
              <label>结果解释：</label> 此处显示对分析结果的解释
            </div>
          </div>
        </div>
        
        <div class="part">
          <div id="reportConclusion" class="optional conclusion">
            <label>结论：</label> 此处显示对样本分析后得出的总结性的观点
          </div>
          <div class="optional collapse in reportRemarks">
            <div class="item  margin-bottom">
              <label>备注：</label> 
              <span id="remarksContent">此处显示对本份报告的备注信息</span>
            </div>
          </div>
          <div class="report-footer">
            <div class="row">
          	  <div class="col-xs-4">
          	    <label>检验人：</label> 
          	    <span>检验师</span>
          	  </div>
	    	  <div class="col-xs-4">
	    	    <label>审核人：</label>
	    	    <span id="masterAuditorId"></span>&nbsp;&nbsp;
	    	    <span id="deputyAuditorId"></span>
	    	  </div>
	    	  <div class="col-xs-4 text-center">
	    	    <label>日期：</label>
	    	    <span id="date">2018-01-25</span>
	    	  </div>
	        </div>
          </div>
        </div>
      </div>
    </div>
     -->
  </div>
  </body>
</html>