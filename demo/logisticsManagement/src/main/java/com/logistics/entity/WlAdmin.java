package com.logistics.entity;

public class WlAdmin {
    
    private Integer wlId;

    private String wlName;

    private String wlPwd;

    private Integer roleid;
    
    
    //真实姓名
    private String truename;
    
    private String rodeName;
    //角色职位
    private String name;
    
    private String pName;
    
    
    
    public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRodeName() {
		return rodeName;
	}

	public void setRodeName(String rodeName) {
		this.rodeName = rodeName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getWlId() {
        return wlId;
    }

    public void setWlId(Integer wlId) {
        this.wlId = wlId;
    }
    
    public String getWlName() {
        return wlName;
    }

    public void setWlName(String wlName) {
        this.wlName = wlName == null ? null : wlName.trim();
    }

    public String getWlPwd() {
        return wlPwd;
    }

    public void setWlPwd(String wlPwd) {
        this.wlPwd = wlPwd == null ? null : wlPwd.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}