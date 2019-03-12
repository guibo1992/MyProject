<#setting classic_compatible=true>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	<title><sitemesh:write property='title'/></title>
  	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
  	<link href="/static/css/font-awesome.min.css" rel="stylesheet">
  	<link href="/static/css/pace-theme-minimal.min.css" rel="stylesheet">
  	<link href="/static/css/jquery-confirm.min.css" rel="stylesheet">
  	<link href="/static/css/animate.min.css" rel="stylesheet">
  	<sitemesh:write property='head'/>
  	<link rel="stylesheet" href="/static/css/skin.css" />
  	<link rel="stylesheet" href="/static/css/style.css" />
  	<link rel="stylesheet" href="/static/css/formValidation.css" />
  	<!--[if lt IE 9]>
  	<script src="/static/js/html5shiv.min.js"></script>
  	<script src="/static/js/respond.min.js"></script>
  	<![endif]-->
  	<script> var _static = '${_static}'; </script>
  </head>
  <body class="skin sidebar-mini wysihtml5-supported">
    <div class="wrapper">
     
	  <header class="main-header">
	    <a class="logo">
	      <span class="logo-mini"><b class="small">Chrom</b></span>
    	  <span class="logo-lg small">细胞遗传管理系统</span>
	    </a>
	    <nav class="navbar navbar-static-top" role="navigation">
	      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	        <span class="sr-only">Toggle navigation</span>
	      </a>
		
	      <div class="navbar-custom-menu pad-right-10">
	        <ul class="nav navbar-nav">
              
              <li class="dropdown messages-menu">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
              	  <span class="label label-success">${messageList?size}</span>
            	</a>
            	<ul class="dropdown-menu">
              	  <li class="header">您有 ${messageList?size} 条消息待查看</li>
              	  <li>
               	    <ul class="menu">
               	    <#list messageList as msg>
                  	  <li>
                    	<a href="/message/${msg.id}/view.html">
                      	  <h4>
                        	${msg.title}
                        	<small><i class="fa fa-clock-o"></i> ${msg.releaseDate?date} </small>
                      	  </h4>
                      	  <p>${msg.brief}</p>
                    	</a>
	                  </li>
	                </#list>
	                </ul>
	              </li>
	              <li class="footer"><a href="/message/all.html">查看全部消息</a></li>
	            </ul>
	          </li>
            
	          <li class="dropdown notifications-menu">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	           	  <i class="fa fa-user"></i>
	              <span class="hidden-xs"> ${_active_user.username}</span>
	              <span class="caret"> </span>
	            </a>
	            <ul class="dropdown-menu animated fadeIn">
	              <li class="header">user manager</li>
	              <li>
	                <ul class="menu">
	                  <li> <a href="/user/profile" class="text-muted" disabled> <i class="fa fa-info-circle"></i> 我的资料 </a> </li>
	                  <li> <a href="#" data-toggle="modal" data-target="#resetPasswdModal"> <i class="fa fa-edit"></i> 修改密码 </a> </li>
	                </ul>
	              </li>
	            </ul>
	          </li>
	          <li class="messages-menu"> <a id="logout" href="#"> <i class="fa fa-power-off"></i> <span>安全退出</span> </a> </li>
	        </ul>
	      </div>
	    </nav>
	  </header>
	
	  <section>
	    <aside class="main-sidebar">
	      <section class="sidebar">
	        
	        <div class="user-panel">
	          <div class="image"> <img src="<#if _sysConfig.logoUrl?has_content>${_static + _sysConfig.logoUrl}<#else>/static/images/logo.png</#if>"> </div>
	        </div>
	         
	        <div class="sidebar-nav" id="_menu_container">
	          <ul class="sidebar-menu metismenu">
	            <li class="header"><i class="fa fa-navicon"></i> 导航</li>
	            <li class="<#if _active_menu == "/index.html">active</#if>">
	              <a href="/index.html"> <i class="fa fa-home"></i> <span class="pointer">首页大厅</span> </a>
	    	    </li>
	    	    
	    	    <li class="<#if _active_menu?matches(r'^(/slide/print/).+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-print"></i> <span>打印管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == '/slide/print/fast-list.html'>active</#if>">
	                  <a href="/slide/print/fast-list.html"> 
	                    <i class="fa fa-caret-right"></i>  
	                    <span class="pointer">快速打印</span> 
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == '/slide/print/records-list.html'>active</#if>">
	                  <a href="/slide/print/records-list.html"> 
	                    <i class="fa fa-caret-right"></i>  
	                    <span class="pointer">打印记录</span> 
	                  </a>
	    	        </li>
                  </ul>
	    	    </li>
	            
	    	    <li class="<#if _active_menu?matches(r'^(/specimen/)(?!print).+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-file-excel-o"></i> <span>标本管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="animated fadeIn">
	                <li class="<#if _active_menu == "/specimen/type/list.html">active</#if>">
		              <a href="/specimen/type/list.html"> <i class="fa fa-caret-right"></i> <span>标本类型</span> </a>
		    	    </li>
		    	    <li class="<#if _active_menu?matches(r"/specimen/(list|\d+).html")>active</#if>">
	                  <a href="/specimen/list.html"> <i class="fa fa-caret-right"></i> <span>标本列表</span> </a>
	                </li>
		    	    <li class="<#if _active_menu == "/specimen/add.html">active</#if>">
	                  <a href="/specimen/add.html"> <i class="fa fa-caret-right"></i> <span>标本录入</span> </a>
	                </li>
	              	<li class="<#if _active_menu == "/specimen/culture/list.html">active</#if>">
		              <a href="/specimen/culture/list.html"><i class="fa fa-caret-right"></i> <span class="pointer">标本流程</span> </a>
		    	  	</li>
	              </ul>
		    	</li>
	    	    
	    	    <#if _active_user.type gt 1>
	    	    <li class="<#if _active_menu?matches(r'^(/karyotype/analysis|/analysis/report|/report)(?!/audit)/.+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-file-pdf-o"></i> <span>分析报告</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu?matches(r'^(/karyotype/analysis)/.+\.html$')>active</#if>">
                      <a href="/karyotype/analysis/list.html">
                        <i class="fa fa-caret-right"></i> <span>核型分析</span>
                      </a>
                    </li>
                    <li class="<#if _active_menu?matches(r"/analysis/report/(list|\d+).html")>active</#if>">
                      <a href="/analysis/report/list.html">
                        <i class="fa fa-caret-right"></i> <span>分析报告</span>
                      </a>
                    </li>
                    
                    <li class="<#if _active_menu?matches(r"/report/template/.+\.html")>active</#if>">
                      <a href="/report/template/list.html">
                        <i class="fa fa-caret-right"></i> <span>报告模板</span>
                      </a>
                    </li>
                  
                    <li class="<#if _active_menu == '/report/interpretation/list.html'>active</#if>">
                      <a href="/report/interpretation/list.html">
                        <i class="fa fa-caret-right"></i> <span>解释模板</span>
                      </a>
                    </li>
                    <li class="<#if _active_menu == '/report/conclusion/list.html'>active</#if>">
                      <a href="/report/conclusion/list.html"> <i class="fa fa-caret-right"></i> <span>结论模板</span> </a>
                    </li>
                    <#-- 
                  	<li class="<#if _active_menu == '/report/anomaly/list.html'>active</#if>">
                      <a href="/report/anomaly/list.html">
                        <i class="fa fa-caret-right"></i> <span>异常模板</span>
                      </a>
                    </li>
                     -->
                  </ul>
	            </li>
	            </#if>
	            
	            <#if _active_user.type gt 3>
	            <li class="<#if _active_menu?matches(r'^(/report/audit)/.+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-gavel"></i> <span>报告审核</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
	                <li class="<#if _active_menu?matches(r"/report/audit/(list|\d+).html")>active</#if>">
                      <a href="/report/audit/list.html">
                        <i class="fa fa-caret-right"></i> <span>审核列表</span>
                      </a>
                    </li>
                    
                    <li class="<#if _active_menu?matches(r"/report/audit/records/(list|\d+).html")>active</#if>">
                      <a href="/report/audit/records/list.html">
                        <i class="fa fa-caret-right"></i> <span>审核记录</span>
                      </a>
                    </li>
                  </ul>
                </li>
                </#if>
	            
	            <#if _active_user.type gt 3> 
	            <li class="<#if _active_menu?contains("/followup/")>active</#if>">
	              <a href="#">
	                <i class="fa fa-pencil"></i> <span>随访管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == "/followup/list.html">active</#if>">
	                  <a href="/followup/list.html">
	                    <i class="fa fa-caret-right"></i> <span>随访记录</span>
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == "/followup/record/add.html">active</#if>">
	                  <a href="/followup/record/add.html">
	                    <i class="fa fa-caret-right"></i> <span>新增随访记录</span>
	                  </a>
	    	        </li>
                  </ul>
	            </li>
	            
	            <li class="<#if _active_menu?contains("/work/summary/")>active</#if>">
	              <a href="#">
	                <i class="fa fa-line-chart"></i> <span>统计分析</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == "/work/summary/list.html">active</#if>">
	                  <a href="/work/summary/list.html">
	                    <i class="fa fa-caret-right"></i> <span>工作统计</span>
	                  </a>
	    	        </li>
	    	        <#-- 
	    	        <li class="<#if _active_menu == "/statistics/result.html">active</#if>">
	                  <a href="/statistics/result.html">
	                    <i class="fa fa-caret-right"></i> <span>分析结果统计</span>
	                  </a>
	    	        </li>
	    	         -->
                  </ul>
	            </li>
	            </#if> 
	            
	            <#if _active_user.type gt 1> 
	            <li class="<#if _active_menu?contains("/reagent/")>active</#if>">
	              <a href="#">
	                <i class="fa fa-flask"></i> <span>试剂管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == "/reagent/list.html">active</#if>">
	                  <a href="/reagent/list.html">
	                    <i class="fa fa-caret-right"></i> <span>试剂列表</span>
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == "/reagent/stock/list.html">active</#if>">
	                  <a href="/reagent/stock/list.html">
	                    <i class="fa fa-caret-right"></i> <span>试剂库存</span>
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == "/reagent/stock/in/list.html">active</#if>">
	                  <a href="/reagent/stock/in/list.html">
	                    <i class="fa fa-caret-right"></i> <span>入库记录</span>
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == "/reagent/stock/out/list.html">active</#if>">
	                  <a href="/reagent/stock/out/list.html">
	                    <i class="fa fa-caret-right"></i> <span>领用记录</span>
	                  </a>
	    	        </li>
                  </ul>
	            </li>
	    	    </#if> 
	    	    
	    	    <#if _active_user.type gt 3> 
	    	    <li class="<#if _active_menu?matches(r'^(/message)/.+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-cogs"></i> <span>公告管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == "/message/list.html">active</#if>">
	                  <a href="/message/list.html">
	                    <i class="fa fa-caret-right"></i> <span>公告列表</span>
	                  </a>
	    	        </li>
	    	        <li class="<#if _active_menu == "/message/add.html">active</#if>">
	                  <a href="/message/add.html">
	                    <i class="fa fa-caret-right"></i> <span>发布公告</span>
	                  </a>
	    	        </li>
                  </ul>
	    	    </li>
	    	    </#if>
	    	    
	    	    <li class="<#if _active_menu == '/technical/document/list.html'>active</#if>">
	              <a href="/technical/document/list.html">
	                <i class="fa fa-file"></i> <span>技术文档</span>
	              </a>
	    	    </li>
	    	    
	    	    <#if _active_user.type gte 1 >
	    	    <li class="<#if _active_menu?matches(r'^(/share)/.+\.html$')>active</#if>">
	              <a href="/technical/support.html">
	                <i class="fa fa-wrench"></i> <span>技术支持</span>
	              </a>
	    	    </li>
	    	    </#if>
	    	    
	    	    <#if _active_user.type gt 3> 
	    	    <li class="<#if _active_menu == "/user/list.html">active</#if>">
                  <a href="/user/list.html"> <i class="fa fa-user"></i> <span class="pointer">用户管理</span> </a>
    	        </li>
	    	    
	    	    <li class="<#if _active_menu?matches(r'^(/config|/serial/number|/slide)(?!/print)/.+\.html$')>active</#if>">
	              <a href="#">
	                <i class="fa fa-cogs"></i> <span>系统设置</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
                  <ul class="animated fadeIn">
                    <li class="<#if _active_menu == "/config/setting.html">active</#if>">
	                  <a href="/config/setting.html">
	                    <i class="fa fa-caret-right"></i> <span>基础信息</span>
	                  </a>
	    	        </li>
                    
	    	        <li class="<#if _active_menu == "/serial/number/rule.html">active</#if>">
	                  <a href="/serial/number/rule.html">
	                    <i class="fa fa-caret-right"></i> <span>标本编码</span>
	                  </a>
	    	        </li>
	    	        
	    	        <li class="<#if _active_menu == "/slide/template.html">active</#if>">
	                  <a href="/slide/template.html">
	                    <i class="fa fa-caret-right"></i> <span>标本打印模板</span>
	                  </a>
	    	        </li>
                  </ul>
	    	    </li>
	    	    </#if>
	    	    
	      	  </ul>
	        </div>
	      </section>
	    </aside>
	  </section>
	  <#-- content body -->
      <sitemesh:write property='body'/>
      <#-- ./end content body -->
    </div>
    
    <div class="modal fade" id="resetPasswdModal" role="dialog" aria-labelledby="resetPasswdModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="resetPasswdModalLabel">重设密码</h4>
	      </div>
		  <div class="modal-body">
		    <form id="resetPasswdForm" method="post" class="form-horizontal">
	          <div class="form-group">
	            <label class="col-sm-3 control-label" for="origPassword">原密码：</label>
	            <div class="col-sm-8">
	              <input type="password" name="origPassword" id="origPassword" placeholder="原密码" class="form-control">
	            </div>
	          </div>
	          <div class="form-group">
	            <label class="col-sm-3 control-label" for="password">新密码：</label>
	            <div class="col-sm-8">
	              <input type="password" name="password" placeholder="登录密码 6-18位字母、数字、'_'的组合字符" id="password" class="form-control">
	            </div>
	          </div>
	          <div class="form-group">
	            <label class="col-sm-3 control-label" for="confirmPassword">确认密码：</label>
	            <div class="col-sm-8">
	              <input type="password" name="repassword" placeholder="密码确认" id="confirmPassword" class="form-control">
	            </div>
	          </div>
	          <div class="form-group">
	            <div class="col-sm-offset-3 col-sm-10">
	              <button class="btn btn-default" data-dismiss="modal" type="button"> Cancel</button>
	              <button type="submit" class="btn btn-primary" type="button">Submit</button>
	            </div>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer"></div>
	    </div>
	  </div>
	</div>
    
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/jquery-ui.min.js"></script>
    <script src="/static/js/fastclick.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/pace.min.js"></script>
    <script src="/static/js/metisMenu.min.js"></script>
    <script src="/static/js/jquery.slimscroll.min.js"></script>
    <script src="/static/js/jquery-confirm.min.js"></script>
    <script src="/static/js/jquery.form.min.js"></script>
    <script src="/static/js/formValidation.min.js"></script>
	<script src="/static/js/app.js"></script>
    <sitemesh:write property='imports'/>
  </body>
</html>
