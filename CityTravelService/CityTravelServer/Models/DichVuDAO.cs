using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CityTravelService.Models
{
    public class DichVuDAO : DataProvider
    {
        public List<DichVu> getDsDichVu()
        {
            connect();
            string query = "SELECT * FROM DICHVU";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<DichVu> arr = new List<DichVu>();
            foreach(Object o in ls) {
                arr.Add((DichVu)o);
            }

            return arr;
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            DichVu dv = new DichVu();
            dv.ID = (int)dt.Rows[i]["MaDichVu"];
            dv.Name = dt.Rows[i]["TenDichVu"].ToString();
            dv.Hinh = dt.Rows[i]["Hinh"].ToString();

            return (object)dv;
        }

        ////Lấy 1 dịch vụ
        //public DichVu getDichVu(int id)
        //{
        //    connect();
        //    //string deleteCommand = "DELETE FROM DICHVU WHERE MaDichVu=" + id;
        //    string query = string.Format("SELECT * FROM DICHVU WHERE [MaDichVu]= '{0}'", id);
        //    executeNonQuery(query);
        //    disconnect();
        //}

        public void insertDichVu(DichVu dv)
        {
            connect();
            string insertCommand = "INSERT INTO DICHVU VALUES(N'"+
                dv.Name+"', '"+
                dv.Hinh+"')";
            executeNonQuery(insertCommand);
            disconnect();
        }

        public void deleteDichVu(int id)
        {
            connect();
            //string deleteCommand = "DELETE FROM DICHVU WHERE MaDichVu=" + id;
            string deleteCommand = string.Format("DELETE FROM DICHVU WHERE [MaDichVu]= '{0}'",id);
            executeNonQuery(deleteCommand);
            disconnect();
        }
    }
}