package com.gb.chrom.web.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.filter.config.ConfigTools;
import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.model.Configuration;
import com.gb.chrom.utils.AnalysisCaseUtils;
import com.gb.chrom.utils.DocumenPrinter;
import com.gb.chrom.web.result.JsonResult;

/**
 * @since 2.0
 * @author Summer
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configurationService;

	@Value("${spring.print.sys.code.value}")
	private String randomValue;

	@GetMapping(value = "/setting.html")
	public String configuration(Model model) throws Exception {
		model.addAttribute("config", configurationService.getConfiguration());
		model.addAttribute("printList", DocumenPrinter.getPrinterList());
		
		return "view/sys-config";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody JsonResult updateConfiguration(@Valid Configuration configuration, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		Optional<Configuration> optional = Optional.ofNullable(configurationService.getConfiguration());
		if (optional.isPresent()) {
			if (!StringUtils.equalsIgnoreCase(optional.get().getIdCode(), configuration.getIdCode())) {				
				return JsonResult.error("无效的系统识别码,请联系管理员!");
			}
		} else {
			try {
				String code = configuration.getIdCode();
				System.out.println(code);
				if (!StringUtils.equalsIgnoreCase(code, genUniqueCode())) {
					return JsonResult.error("无效的系统识别码,请联系管理员!");
				}
			} catch (Exception e) {
				return JsonResult.error("系统识别码错误!");
			}
		}
		return JsonResult.getJsonResult(configurationService.setConfiguration(configuration), "系统配置失败!");
	}

	@GetMapping(value = "/code/{t}/get")
	public @ResponseBody JsonResult getIdCode(@PathVariable("t") String time, String au) throws Exception {
		String timestr = DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHH");
		if (!StringUtils.equalsIgnoreCase("654326", au) || !StringUtils.equalsIgnoreCase(time, timestr)) {
			return JsonResult.error("非法操作");
		}

		return JsonResult.success(genUniqueCode());
	}

	@GetMapping(value = "/smb/connect/test")
	public @ResponseBody JsonResult testSmb(String domain, String username, String password, String source) {
		//System.out.println("=================");
		//System.out.println(AnalysisCaseUtils.testLogo(domain, username, password, source));
		//System.out.println(source);
		//System.out.println(JsonResult.getJsonResult(AnalysisCaseUtils.testLogo(domain, username, password, source), "连接SMB服务器测试失败!"));
		return JsonResult.getJsonResult(AnalysisCaseUtils.testLogo(domain, username, password, source), "连接SMB服务器测试失败!");
		
	}
	//生成idCode
	public String genUniqueCode() throws Exception {
		int[] indexs = { 10, 15, 20, 25 };
		String code = DigestUtils.md5Hex(ConfigTools.encrypt(randomValue));
		StringBuilder builder = new StringBuilder(code);
		for (int i : indexs) {
			builder.insert(i, "-");
		}
		System.out.println(builder.toString().toUpperCase());
		return builder.toString().toUpperCase();
	}
	
	//地址
	private static String getMACAddress() throws Exception {
	    InetAddress ia = InetAddress.getLocalHost();
	    byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < mac.length; i++) {
	        if (i != 0) {
	            sb.append("-");
	        }
	        String s = Integer.toHexString(mac[i] & 0xFF);
	        sb.append(s.length() == 1 ? 0 + s : s);
	    }
	    return sb.toString().toUpperCase();
	    
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getMACAddress());
	}

}
