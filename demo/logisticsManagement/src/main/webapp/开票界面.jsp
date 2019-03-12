<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晶科物流管理系统</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/css.css" />
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sdmenu.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
</head>
<body>
<div class="header">
	 <div class="logo"><img  src="img/logo.png" /></div>
     
				<div class="header-right">
                <i class="icon-question-sign icon-white"></i> <a href="#">帮助</a> <i class="icon-off icon-white"></i> <a id="modal-973558" href="#modal-container-973558" role="button" data-toggle="modal">注销</a> <i class="icon-user icon-white"></i> <a href="#">开票员的工作平台</a> <i class="icon-envelope icon-white"></i> <a href="#">发送短信</a>
                <div id="modal-container-973558" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:300px; margin-left:-150px; top:30%">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">
						注销系统
					</h3>
				</div>
				<div class="modal-body">
					<p>
						您确定要注销退出系统吗？
					</p>
				</div>
				<div class="modal-footer">
					 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <a class="btn btn-primary" style="line-height:20px;" href="登录.html" >确定退出</a>
				</div>
			</div>
				</div>
</div>
<!-- 顶部 -->     
            
<div id="middle">
     <div class="left">
     
     <script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>

<div id="my_menu" class="sdmenu">
	<div >
		<span>业务处理</span>
		<a href="查询页面.jsp">电脑开票</a>
		<a href="分理处发货排行榜.jsp">票据补录</a>
		<a href="开票界面.jsp">票单复核</a>
		<a href="客户投诉.jsp">货物配载</a>
		<a href="线路管理.jsp">发车清单</a>
		<a href="#">到货确认</a>
	</div>
	<div class="collapsed">
		<span>统计</span>
		<a href="#">统计</a>
	</div>
	<div class="collapsed">
		<span>资金结算</span>
		 <a href="#">提货登记</a>
		 <a href="#">收款核销</a>
		 <a href="#">现金转账</a>
		 <a href="#">现付结算</a>
		 <a href="#">返款结算</a>
		 <a href="#">提付结算</a>
		 <a href="#">浦发打卡</a>
		 <a href="#">建行打卡</a>
		 <a href="#">其他打卡</a>
		 <a href="#">原返提货</a>



	</div>
    
 	<div class="collapsed">
		<span>票据查询</span>
		<a href="#">票据查询</a>
		<a href="#">跟踪查询</a>
		<a href="#">车次查询</a>

	</div>
 	<div class="collapsed">
		<span>数据统计</span>
		   <a href="#">放款统计</a>
		   <a href="#">综合统计</a>
		   <a href="#">司机提成</a>
		   <a href="#">收款详单</a>
		   <a href="#">每日汇总</a>
		   <a href="#">车辆运费</a>
		   <a href="#">原返收款明细</a>
		   <a href="#">现付确认详单</a>
		   <a href="#">回单结算明细</a>
		   <a href="#">返款确认明细</a>
		   <a href="#">提付结算明细</a>
		   <a href="#">分公司排行</a>
		   <a href="#">浦发转帐统计</a>
		   <a href="#">发货排行</a>
		   <a href="#">建行转帐统计</a>
		   <a href="#">其他转帐统计</a>
		   <a href="#">分理处排行</a>
		   <a href="#">分公司月运费对比</a>
		   <a href="#">分理处月运费对比</a>
		   <a href="#">线路货款未到排行</a>
		   <a href="#">分公司入库报表</a>
		   <a href="#">分公司收款日报</a>
		   <a href="#">分公司当日库存</a>
		   <a href="#">分公司往日库存</a>
		   <a href="#">专线现付统计</a>
		   <a href="#">专线分理处统计</a>
		   <a href="#">分理处专线统计</a>
		   <a href="#">货款回款率统计</a>
		   <a href="#">线路运量</a>

	</div>
 	<div class="collapsed">
		<span>单据管理</span>
		<a href="#">作废挂失</a>
		<a href="#">取消修改</a>
		<a href="#">修改清单</a>
		<a href="#">退单管理</a>
		<a href="#">转帐差错处理</a>
		<a href="#">运单修改查询</a>
		<a href="#">复核异常处理</a>
		<a href="#">运单修改</a>
		<a href="#">异常处理</a>
		<a href="#">单据备注</a>

	</div>
 	<div class="collapsed">
		<span>系统管理</span>
		 <a href="#">线路管理</a>
		   <a href="#">手续费设置</a>
		   <a href="#">更改密码</a>
		   <a href="#">系统日志</a>
		   <a href="#">帐号管理</a>
		   <a href="#">复核设置</a>
		   <a href="#">部门管理</a>
		   <a href="#">角色管理</a>
		   <a href="#">用户管理</a>
		   <a href="#">短信设置</a>
		   <a href="#">短信发送</a>
		   <a href="#">分理处分成设置</a>
		   <a href="#">省价格设置</a>
		   <a href="#">市价格设置</a>
		   <a href="#">县价格设置</a>

	</div>
 	<div class="collapsed">
		<span>回单管理</span>
		<a href="#">回单结算</a>
		<a href="#">回单查询</a>
		<a href="#">回单接收</a>

	</div>
 	<div class="collapsed">
		<span>财务管理</span>
		<a href="#">分公司日报</a>
		<a href="#">科目管理</a>
		<a href="#">费用录入</a>
		<a href="#">费用统计</a>
		<a href="#">当日对账</a>
		<a href="#">往日对账</a>
		<a href="#">利润分成</a>

	</div>
    
    <div class="collapsed">
    <span>车辆管理</span>
   <a href="#">基本资料管理</a>
    <a href="#">维修管理</a>
    <a href="#">加油管理</a>
    <a href="#">保养记录</a>
    <a href="#">派车管理</a>
    <a href="#">派车记录</a>
    <a href="#">包车分账</a>
    <a href="#">月分账</a>
    <a href="#">事故业务</a>
    <a href="#">保险业务</a>
    <a href="#">司机档案</a>
    <a href="#">行驶证</a>
    <a href="#">营运证</a>

    </div>
 	<div class="collapsed">
		<span>中转处理</span>
		<a href="#">中转操作</a>
		<a href="#">中转单修改</a>
		<a href="#">当日中转明细</a>

	</div>
 	<div class="collapsed">
		<span>客户管理</span>
		<a href="#">客户投诉</a>
		<a href="#">投诉处理</a>
		<a href="#">投诉查询</a>
		<a href="#">客户关怀</a>
		<a href="#">积分查询</a>
		<a href="#">分理处排行</a>
		<a href="#">客户管理</a>
		<a href="#">理赔申请</a>
		<a href="#">理赔审核</a>
		<a href="#">理赔结算</a>
		<a href="#">理赔查询</a>

	</div>
 	<div class="collapsed">
		<span>行政办公</span>
		<a href="#">文件管理</a>
		<a href="#">在线留言</a>
		<a href="#">人事管理</a>
		<a href="#">公告管理</a>
		<a href="#">罚款登记</a>
		<a href="#">滚动字幕</a>

	</div>
 	<div class="collapsed">
		<span>工资及包车费管理</span>
		<a href="#">工资管理</a>
		<a href="#">包车费管理</a>
		<a href="#">房租管理</a>
		<a href="#">货场费管理</a>



	</div>
  	<div class="collapsed">
		<span>领导决策</span>
<a href="#">新增客户分析</a>
<a href="#">长期未发货分析</a>
<a href="#">同比业务分析</a>
<a href="#">环比业务分析</a>
<a href="#">超期预警</a>

	</div>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  
    
</div>

     </div>
     <div class="Switch"></div>
     <script type="text/javascript">
	$(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
	 
		});
});
</script>

     <div class="right"  id="mainFrame">
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">业务处理</a> <span class="divider">/</span>
  电脑开票
</ul>
   
   <div class="title_right"><strong>发货单填写</strong></div>  
<div style="width:900px;margin:auto;">
       <table class="table table-bordered">
         <tr>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">日期：</td>
     <td width="23%"><input type="text"  class="laydate-icon span1-1" id="Calendar" value="2015-08-25"  /></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">直达站：</td>
     <td width="23%"><input type="text" name="" id="" class="span1-1"  /></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">中转至：</td>
     <td><input type="text" name="" id="" class="span1-1"  /></td>
     </tr>
     <tr>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">卡号：</td>
     <td><input type="text" name="" id=""  class="span1-1" /></td>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">发货人：</td>
     <td><input type="text" name="" id=""  class="span1-1" /></td>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
     <td><input type="text" name="" id=""  class="span1-1" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">票号：</td>
       <td><input type="text" name="input" id="input"  class="span1-1" /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货人：</td>
       <td><input type="text" name="input2" id="input2"  class="span1-1" /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
       <td><input type="text" name="input3" id="input3" class="span1-1"  /></td>
     </tr>
       </table>
      <table class="table table-bordered">
        <tr>
           <td align="center" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>包装</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>数量</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>运费</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>重量</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>体积</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>结算方式</strong></td>
            
         </tr>
         <tr>
           <td align="center"><input type="text" name="input4" id="input4" class=" span2" /></td>
           <td align="center">
              
             <select name="select" id="select" class=" span1">
               <option>无</option>
             </select>
           </td>
           <td align="center"><input type="text" name="input5" id="input5" class=" span1 text-center" value="22" /></td>
           <td align="center"><input type="text" name="input6" id="input6" class=" span1 text-center" value="22" /></td>
           <td align="center"><input type="text" name="input7" id="input7" class=" span1 text-center" value="22" /></td>
           <td align="center"><input type="text" name="input8" id="input8" class=" span1 text-center" value="22" /></td>
           <td align="center">
           <select name="select" id="select"  class=" span1">
               <option>无</option>
             </select></td>
            
         </tr>
       </table>
      <table class="table table-bordered" >
        <tr>
          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">代收货款：</td>
          <td width="23%"><input type="text" name="input10" id="input10" class="span1-1"  /></td>
          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">中转费：</td>
          <td width="23%"><input type="text" name="input14" id="input14"  class="span1-1" /></td>
          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">保险金额：</td>
          <td><input type="text" name="input15" id="input15" class="span1-1"  /></td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">保费：</td>
          <td><input type="text" name="input11" id="input1-1" class="span1-1"  /></td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">送货费：</td>
          <td><input type="text" name="input12" id="input12"  class="span1-1" /></td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">送货方式：</td>
          <td><input type="text" name="input16" id="input16"  class="span1-1" /></td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">需要回单：</td>
          <td><input type="checkbox"> 打印标签</td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">返款：</td>
          <td><input type="text" name="input13" id="input13" class="span1-1"  /></td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">起站：</td>
          <td>
         
      <input type="radio"> 自提
   
      <input type="radio"> 送货
   
          
          </td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">分理站：</td>
          <td nowrap="nowrap"><select name="select2" id="select2" class=" span1-1">
            <option>郑州</option>
          </select>
          <input type="text" name="input13" id="input13" class="span1" />
          <select name="select2" id="select2" class=" span1">
            <option>无</option>
          </select></td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">标签数量：</td>
          <td colspan="3"><input type="text" name="input17" id="input17" class="span1" />
             
      <input type="checkbox"> 打印标签
     </td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">备注：</td>
          <td colspan="5" nowrap="nowrap"><input type="text" name="input9" id="input9" class="span10"  /></td>
          </tr>
      </table>
           
     <table  class="margin-bottom-20  table no-border" >
       <tr>
     	<td class="text-center"><input type="button" value="确定" class="btn btn-info  " style="width:80px;" /></td>
     </tr>
   </table>
      <div class="alert"> 
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      温馨提示：按“Enter”键进行切换；&nbsp;&nbsp;按“F10”保存；&nbsp;&nbsp;按“F5”代收货款；&nbsp;&nbsp;按“F6”返款； 
       </div>
   
   </div>  
     
 </div>     
     </div>
    </div>
    
<!-- 底部 -->
<div id="footer">版权所有：晶科客流 &copy; 2015&nbsp;&nbsp;&nbsp;&nbsp;服务热线：0371-88888888</div>
    
    

 <script>
!function(){
laydate.skin('molv');
laydate({elem: '#Calendar'});
}();
 
</script>
</body>
</html>