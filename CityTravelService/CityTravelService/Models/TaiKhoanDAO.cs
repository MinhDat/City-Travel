using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CityTravelService.Models
{
    public class TaiKhoanDAO : DataProvider
    {
        public List<TaiKhoan> getDsTaiKhoan()
        {
            connect();
            string query = "SELECT * FROM TAIKHOAN";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<TaiKhoan> arr = new List<TaiKhoan>();
            foreach (Object o in ls)
            {
                arr.Add((TaiKhoan)o);
            }

            disconnect();
            return arr;
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            TaiKhoan tk = new TaiKhoan();
            tk.Email = dt.Rows[i]["Email"].ToString();
            tk.PassWord = dt.Rows[i]["MatKhau"].ToString();
            tk.LastName = dt.Rows[i]["Ho"].ToString();
            tk.FirtName = dt.Rows[i]["Ten"].ToString();
            tk.Phone = dt.Rows[i]["SDT"].ToString();
            tk.Sex = (int)dt.Rows[i]["GioiTinh"];
            tk.Birth = (DateTime)dt.Rows[i]["NgaySinh"];
            tk.Address = dt.Rows[i]["DiaChi"].ToString();
            tk.Picture = dt.Rows[i]["Hinh"].ToString();

            return (object)tk;
        }

        public void insertTaiKhoan(TaiKhoan tk)
        {
            connect();
            string insertCommand = "INSERT INTO TAIKHOAN VALUES('" +
                tk.Email + "', '" +
                tk.PassWord + "', N'" +
                tk.LastName + "', N'" +
                tk.FirtName + "', N'" +
                tk.Phone + "', " +
                tk.Sex + ", '" + 
                tk.Birth.Year + "-" + tk.Birth.Month + "-" + tk.Birth.Day + "', N'" +
                tk.Address + "', '" +
                tk.Picture + "')";
            executeNonQuery(insertCommand);
            disconnect();
        }

        public void deleteTaiKhoan (string Email)
        {
            connect();
            string deleteCommand = "DELETE FROM TAIKHOAN WHERE Email = '" + Email + "'";
            executeNonQuery(deleteCommand);
            disconnect();
        }
    }
}