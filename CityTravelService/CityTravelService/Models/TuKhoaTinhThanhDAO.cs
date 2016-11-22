using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CityTravelService.Models
{
    public class TuKhoaTinhThanhDAO: DataProvider
    {
        public List<TuKhoaTinhThanh> getDsTuKhoaTinhThanh()
        {
            connect();
            string query = "SELECT * FROM TUKHOATINHTHANH";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<TuKhoaTinhThanh> arr = new List<TuKhoaTinhThanh>();
            foreach (Object o in ls)
            {
                arr.Add((TuKhoaTinhThanh)o);
            }
            disconnect();
            return arr;
        }

        public int getMaTinhThanh(string tukhoa)
        {
            try
            {
                connect();
                string query = "SELECT * FROM TUKHOATINHTHANH WHERE TuKhoaTinhThanh = '" + tukhoa + "'";
                adapter = new SqlDataAdapter(query, connection);
                DataSet dataset = new DataSet();
                adapter.Fill(dataset);
                ArrayList ls = ConvertDataSetToArrayList(dataset);
                List<TuKhoaTinhThanh> arr = new List<TuKhoaTinhThanh>();
                foreach (Object o in ls)
                {
                    arr.Add((TuKhoaTinhThanh)o);
                }

                disconnect();
                return arr[0].MaTinhThanh;
            }
            catch (Exception e)
            {
                return -1;
            }
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            TuKhoaTinhThanh tk = new TuKhoaTinhThanh();
            tk.MaTuKhoaTinhThanh = dt.Rows[i].IsNull("MaTuKhoaTinhThanh") == true ? 0 : (int)dt.Rows[i]["MaTuKhoaTinhThanh"];
            tk.TuKhoaTinhThanh1 = dt.Rows[i]["TuKhoaTinhThanh"].ToString();
            tk.MaTinhThanh = dt.Rows[i].IsNull("MaTinhThanh") == true ? 0 : (int)dt.Rows[i]["MaTinhThanh"];

            return (object)tk;
        }

        public bool updateTuKhoaTinhThanh(TuKhoaTinhThanh tk)
        {
            try
            {
                connect();
                string updateCommand = "UPDATE TUKHOATINHTHANH SET TuKhoaTinhThanh = '" + tk.TuKhoaTinhThanh1 +
                    "', MaTinhThanh = " + tk.MaTinhThanh + " WHERE MaTuKhoaTinhThanh = " + tk.MaTuKhoaTinhThanh;
                executeNonQuery(updateCommand);
                disconnect();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        public void deleteTuKhoaTinhThanh(int id)
        {
            connect();
            string deleteCommand = "DELETE FROM TUKHOATINHTHANH WHERE MaTuKhoaTinhThanh = " + id;
            executeNonQuery(deleteCommand);
            disconnect();
        }

    }
}