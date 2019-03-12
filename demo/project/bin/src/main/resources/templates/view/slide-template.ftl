<head>
  <title>打印模板</title>
  <link href="/static/css/chosen.css" rel="stylesheet">
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 打印模板
	    <small> 系统管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.html"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 打印模板</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="box box-success">
        <div class="box-header with-border">
          <h3 class="box-title col-md-12"> 模板详情</h3>
        </div>
        <div class="box-body">
          <div class="row">
            <div class="col-md-4">
              <input type="hidden" id="id" value="${template.id}" />
          	  <input type="hidden" id="tmplName" value="${template.name}" class="form-control" />
          	</div>
          </div>
        </div>
        <div class="box-body">
          <span class="pad-top-3 margin-r-10 pull-left">打印内容:</span>
          <div class="checkbox checkbox-primary checkbox-inline margin-r-10">
       	    <input id="hosp" name="item" type="checkbox" <#list template.itemList as i><#if i.type == 'hosp'>checked</#if></#list> value="_hosp">
            <label for="hosp"> 医院 </label>
      	  </div>
      	  <div class="checkbox checkbox-primary checkbox-inline margin-r-10">
       	    <input id="name" name="item" type="checkbox" <#list template.itemList as i><#if i.type == 'pn'>checked</#if></#list> value="_name">
            <label for="name"> 姓名</label>
      	  </div>
      	  <div class="checkbox checkbox-primary checkbox-inline margin-r-10">
       	    <input id="date" name="item" type="checkbox" <#list template.itemList as i><#if i.type == 'date'>checked</#if></#list> value="_date">
            <label for="date"> 日期 </label>
      	  </div>
      	  <div class="checkbox checkbox-primary checkbox-inline margin-r-10">
       	    <input id="SN" name="item" type="checkbox" <#list template.itemList as i><#if i.type == 'sn'>checked</#if></#list> value="_number">
            <label for="SN"> 标本编号 </label>
      	  </div>
      	  <div class="checkbox checkbox-primary checkbox-inline margin-r-10">
       	    <input id="QR" name="item" type="checkbox" <#list template.itemList as i><#if i.type == 'qr'>checked</#if></#list> value="_qr">
            <label for="QR"> 二维码 </label>
      	  </div>
      	  <div id="_items" class="hide">
      	    <div data-id="_hosp" class="item" data-type="hosp" style="position: absolute; top: 35px; left: 15px; min-width: 150px; font-size: 52px;">${(_sysConfig.hospAliase)!'医院名称'}</div>
      	    <div data-id="_name" class="item" data-type="pn" style="position: absolute; top: 145px; left: 15px; min-width: 150px; font-size: 52px;">病人姓名</div>
      	    <div data-id="_number" class="item" data-type="sn" style="position: absolute; top: 315px; left: 15px; min-width: 450px; font-size: 58px;">标本编号</div>
      	    <div data-id="_date" class="item" data-type="date" style="position: absolute; top: 248px; left: 15px; min-width: 278px; font-size: 46px;">${.now?string('YYYYMMdd')}</div>
      	    <div data-id="_qr" class="item qrcode" data-type="qr" style="position: absolute; top: 20px; right: 20px; width: 222px; height: 222px; font-size: 72px;"></div>
      	  </div>
        </div>
        <div class="box-body">
	      <div class="tmpl">
		    <div class="tmpl-control">
		      <div class="form-group">
		        <label>位置</label>
		        <div class="row">
            	  <div class="col-md-6">
		        	<input type="text" id="pointX" class="form-control" min="0" max="500" placeholder="X轴 " />
		       	  </div>
		       	  <div class="col-md-6">
		        	<input type="number" id="pointY" class="form-control" min="0" max="400"  placeholder="Y轴 " />
		       	  </div>
		       	</div>
		      </div>
		      <div class="form-group">
		        <label>尺寸</label>
		        <div class="row">
            	  <div class="col-md-6">
		        	<input type="number" id="width" class="form-control" placeholder="宽度" />
		       	  </div>
		       	  <div class="col-md-6">
		        	<input type="number" id="height" class="form-control" placeholder="高度" />
		       	  </div>
		       	</div>
		      </div>
		      <div class="form-group">
		        <label>字体</label>
		        <div class="row">
            	  <div class="col-md-6">
		        	<select id="fontSize" class="form-control chosen-select" data-placeholder="选择字休">
		        	  <option value=""></option>
		        	  <option value="58px">大号</option>
		        	  <option value="52px">中号</option>
		        	  <option value="46px">小号</option>
		        	</select>
		       	  </div>
		       	</div>
		      </div>
		      <div class="form-group">
		        <label>内容</label>
		        <input type="text" id="text" class="form-control" placeholder="条目内容" />
		      </div>
		    </div>
		    <div class="tmpl-box">${template.htmlContent}</div>
		  </div>
		  
		  <canvas id="canvascode"></canvas> 
	    </div>
	    <div class="box-footer">
	      <button type="button" class="btn btn-danger print"><i class="fa fa-print"></i> Test Print</button>
	      <button type="button" id="save" class="btn btn-primary"><i class="fa fa-save"></i> 保存</button>
	    </div>
	  </div>
	</section>
  </div>
  
</body>

<imports>
  <script src="/static/js/chosen.jquery.min.js"></script>
  <script src="/static/js/formValidation.min.js"></script>
  <script src="/static/js/jquery.qrcode.min.js"></script>
  <script src="/static/js/drag.js"></script>
  <script src="/static/js/JsBarcode.min.js"></script>
  <script src="/static/js/viewjs/slide-template.js"></script>
</imports>