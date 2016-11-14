using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

/// <summary>
/// Summary description for WebService
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class WebService : System.Web.Services.WebService {
    QLDangNhapDataContext db;
    public WebService () {
        db = new QLDangNhapDataContext();
        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]
    public string HelloWorld() {
        return "Hello World";
    }

    [WebMethod]
    public int KiemTraDangNhap(string ten, string matkhau)
    {
        int kiemtra = -1;
        var truyvan = from tk in db.TAIKHOANs
                      where tk.TenDN == ten && tk.MatKhau == matkhau
                      select tk;
        if(truyvan.Count() > 0)
        {
            kiemtra = 1;
        }
        return kiemtra;
    }

    [WebMethod]
    public List<TAIKHOAN> ListTaiKhoan()
    {
        List<TAIKHOAN> list = db.TAIKHOANs.ToList();
        //foreach(TAIKHOAN tk in list)
        //{
        //    tk.TenDN = null;
        //}
        return list;
    }
}
