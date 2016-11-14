using CityTravelService.Models;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;


namespace CityTravelServer.Models
{
    public class BinhLuanDAO : DataProvider
    {
        public List<BinhLuan> getDsBinhLuan()
        {
            connect();
            string query = "SELECT * FROM BINHLUAN";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<BinhLuan> arr = new List<BinhLuan>();
            foreach (Object o in ls)
            {
                arr.Add((BinhLuan)o);
            }
            disconnect();
            return arr;
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            BinhLuan bl = new BinhLuan();
            bl.MaBinhLuan = dt.Rows[i]["MaBinhLuan"].ToString();
            bl.MaTaiKhoan = dt.Rows[i]["MaTaiKhoan"].ToString();
            bl.NoiDung = dt.Rows[i]["NoiDung"].ToString();
            bl.ThoiGian = (DateTime)dt.Rows[i]["ThoiGian"];

            return (object)bl;
        }

        public void insertBinhLuan(BinhLuan bl) 
        {
            connect();
            string insertCommand = "INSERT INTO BINHLUAN VALUES('" +
                bl.MaBinhLuan + "', '" +
                bl.MaTaiKhoan + "', N'" +
                bl.NoiDung + "', " +
                bl.ThoiGian.Year + "-" + bl.ThoiGian.Month + "-" + bl.ThoiGian.Day + ")";
            executeNonQuery(insertCommand);
            disconnect();
        }

        public void deleteBinhLuan(string id)
        {
            connect();
            string deleteCommand = "DELETE FROM BINHLUAN WHERE MaBinhLuan = '" + id + "'";
            executeNonQuery(deleteCommand);
            disconnect();
        }
    }
}