﻿#pragma warning disable 1591
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Linq;
using System.Data.Linq.Mapping;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;



[global::System.Data.Linq.Mapping.DatabaseAttribute(Name="QLDangNhap")]
public partial class QLDangNhapDataContext : System.Data.Linq.DataContext
{
	
	private static System.Data.Linq.Mapping.MappingSource mappingSource = new AttributeMappingSource();
	
  #region Extensibility Method Definitions
  partial void OnCreated();
  partial void InsertTAIKHOAN(TAIKHOAN instance);
  partial void UpdateTAIKHOAN(TAIKHOAN instance);
  partial void DeleteTAIKHOAN(TAIKHOAN instance);
  #endregion
	
	public QLDangNhapDataContext() : 
			base(global::System.Configuration.ConfigurationManager.ConnectionStrings["QLDangNhapConnectionString"].ConnectionString, mappingSource)
	{
		OnCreated();
	}
	
	public QLDangNhapDataContext(string connection) : 
			base(connection, mappingSource)
	{
		OnCreated();
	}
	
	public QLDangNhapDataContext(System.Data.IDbConnection connection) : 
			base(connection, mappingSource)
	{
		OnCreated();
	}
	
	public QLDangNhapDataContext(string connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
			base(connection, mappingSource)
	{
		OnCreated();
	}
	
	public QLDangNhapDataContext(System.Data.IDbConnection connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
			base(connection, mappingSource)
	{
		OnCreated();
	}
	
	public System.Data.Linq.Table<TAIKHOAN> TAIKHOANs
	{
		get
		{
			return this.GetTable<TAIKHOAN>();
		}
	}
}

[global::System.Data.Linq.Mapping.TableAttribute(Name="dbo.TAIKHOAN")]
public partial class TAIKHOAN : INotifyPropertyChanging, INotifyPropertyChanged
{
	
	private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
	
	private string _TenDN;
	
	private string _MatKhau;
	
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnTenDNChanging(string value);
    partial void OnTenDNChanged();
    partial void OnMatKhauChanging(string value);
    partial void OnMatKhauChanged();
    #endregion
	
	public TAIKHOAN()
	{
		OnCreated();
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_TenDN", DbType="NChar(20) NOT NULL", CanBeNull=false, IsPrimaryKey=true)]
	public string TenDN
	{
		get
		{
			return this._TenDN;
		}
		set
		{
			if ((this._TenDN != value))
			{
				this.OnTenDNChanging(value);
				this.SendPropertyChanging();
				this._TenDN = value;
				this.SendPropertyChanged("TenDN");
				this.OnTenDNChanged();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_MatKhau", DbType="NChar(10)")]
	public string MatKhau
	{
		get
		{
			return this._MatKhau;
		}
		set
		{
			if ((this._MatKhau != value))
			{
				this.OnMatKhauChanging(value);
				this.SendPropertyChanging();
				this._MatKhau = value;
				this.SendPropertyChanged("MatKhau");
				this.OnMatKhauChanged();
			}
		}
	}
	
	public event PropertyChangingEventHandler PropertyChanging;
	
	public event PropertyChangedEventHandler PropertyChanged;
	
	protected virtual void SendPropertyChanging()
	{
		if ((this.PropertyChanging != null))
		{
			this.PropertyChanging(this, emptyChangingEventArgs);
		}
	}
	
	protected virtual void SendPropertyChanged(String propertyName)
	{
		if ((this.PropertyChanged != null))
		{
			this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
		}
	}
}
#pragma warning restore 1591