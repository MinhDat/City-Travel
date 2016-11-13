using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using CityTravelService.Entity;

namespace CityTravelService.Models
{
    public class DichVuDAO : DataProvider
    {
        public List<DICHVU> getDsDichVu()
        {
            connect();
            string query = "SELECT * FROM DICHVU";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<DICHVU> arr = new List<DICHVU>();
            foreach(Object o in ls) {
                arr.Add((DICHVU)o);
            }

            return arr;
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            DICHVU dv = new DICHVU();
            dv.MaDichVu = (int)dt.Rows[i]["MaDichVu"];
            dv.TenDichVu = dt.Rows[i]["TenDichVu"].ToString();

            return (object)dv;
        }
    }
}