package com.razorthink.powermock.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "users" )
public class User implements Serializable {

	private static final long serialVersionUID = 7867849331546739213L;

	private Integer id;

	private String firstName;

	private String lastName;

	private Boolean isActive;

	private String email;

	private String phone;

	private String password;

	private Integer companyId;

	private Boolean isFirstLogin;

	private Calendar addedDttm;

	private String salt;

	public User()
	{
		super();
	}

	public User( Integer id )
	{
		super();
		this.id = id;
	}

	public User( String firstName, String lastName, String email, String phone, String password, String salt,
			Integer companyId )
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.companyId = companyId;
		this.salt = salt;
		this.addedDttm = Calendar.getInstance();
		this.isActive = true;
		this.isFirstLogin = true;
	}

	@Id
	@Column( name = "user_id", unique = true, nullable = false )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	@Column( name = "user_fname" )
	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	@Column( name = "user_lname" )
	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	@Column( name = "user_active" )
	public Boolean getIsActive()
	{
		return this.isActive;
	}

	public void setIsActive( Boolean isActive )
	{
		this.isActive = isActive;
	}

	@Column( name = "user_email" )
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	@Column( name = "user_phone" )
	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	@Column( name = "user_password" )
	@JsonIgnore
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	@Column( name = "user_is_firstime" )
	public Boolean getIsFirstLogin()
	{
		return this.isFirstLogin;
	}

	public void setIsFirstLogin( Boolean isFirstLogin )
	{
		this.isFirstLogin = isFirstLogin;
	}

	@Column( name = "user_added_dttm" )
	public Calendar getAddedDttm()
	{
		return this.addedDttm;
	}

	public void setAddedDttm( Calendar addedDttm )
	{
		this.addedDttm = addedDttm;
	}

	@Column( name = "user_salt" )
	@JsonIgnore
	public String getSalt()
	{
		return salt;
	}

	public void setSalt( String salt )
	{
		this.salt = salt;
	}

	@Column( name = "user_frn_company_id" )
	@JsonIgnore
	public Integer getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId( Integer companyId )
	{
		this.companyId = companyId;
	}

}