package com.pyo.airplaneService.model;

import java.sql.Date;

public class CustomerVO
{
    private String no;	// NO CHAR(7), -- pk
    private String name;	// NAME VARCHAR2(20) NOT NULL, -- 이름
    private Date birth;	// BIRTH VARCHAR2(20), -- 생일(ex)20020707)
    private String phone;	// PHONE VARCHAR2(15), -- uk 휴대폰번호
    private Date regist;	// REGIST DATE NOT NULL,   -- 등록날짜 SYSDATE 
    private int right;	// RIGHT NUMBER(1),    -- 권한
    private String id;	// ID VARCHAR2(20) NOT NULL,   -- uk 아이디
    private String pw;	// PW VARCHAR2(20) NOT NULL,   -- PW 패스워드
    private int cCount;	// COUNT NUMBER(3) DEFAULT 0   -- 예매 합계(booking테이블에서 insert시 계수.)
	
    public CustomerVO(){}
    
    public CustomerVO(int cCount)
    {
    	super();
    	this.cCount= cCount;
    }
    
	public CustomerVO(String no, String name, Date birth, String phone, String id, String pw)
	{
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.id = id;
		this.pw = pw;
	}
    
    
	public CustomerVO(String no, String name, Date birth, String phone, int right, String id, String pw)
	{
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.right = right;
		this.id = id;
		this.pw = pw;
	}
    
	public CustomerVO(String name, Date birth, String phone, String id, String pw)
	{
		super();
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.right = right;
		this.id = id;
		this.pw = pw;
	}
	
	public CustomerVO(String name, Date birth, String phone, String id)
	{
		super();
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.id = id;
	}

	public CustomerVO(String no, String name, Date birth, String phone, Date regist, int right, String id, String pw)
	{
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.regist = regist;
		this.right = right;
		this.id = id;
		this.pw = pw;
	}
    
	public CustomerVO(String no, String name, Date birth, String phone, Date regist, String id, String pw)
	{
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.regist = regist;
		this.id = id;
		this.pw = pw;
	}
	
	public CustomerVO(String no, String name, Date birth, String phone, Date regist, int right, String id, String pw, int cCount)
	{
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.regist = regist;
		this.right = right;
		this.id = id;
		this.pw = pw;
		this.cCount = cCount;
	}

	public String getNo()
	{
		return no;
	}

	public void setNo(String no)
	{
		this.no = no;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Date getRegist()
	{
		return regist;
	}

	public void setRegist(Date regist)
	{
		this.regist = regist;
	}

	public int getRight()
	{
		return right;
	}

	public void setRight(int right)
	{
		this.right = right;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}

	public int getCcount()
	{
		return cCount;
	}

	public void setCcount(int cCount)
	{
		this.cCount = cCount;
	}

	public static String getHeader() {
	    return String.format(
	        "%-10s | %-10s | %-15s | %-15s | %-15s | %-10s | %-10s",
	        "No", "Name", "Birth", "Phone", "Regist Date", "ID", "Password"
	    );
	}

	@Override
	public String toString() {
	    return String.format(
	        "%-10s | %-10s | %-15s | %-15s | %-15s | %-10s | %-10s",
	        no,
	        name,
	        birth,
	        phone,
	        regist,
	        id,
	        pw
	    );
	}
	
	public String countPrint()
	{
		return "현재 예매된 내역은 총 " + cCount + "건 입니다.";
	}
}