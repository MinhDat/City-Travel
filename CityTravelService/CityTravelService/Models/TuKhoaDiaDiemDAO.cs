using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CityTravelService.Models
{
    public class TuKhoaDiaDiemDAO: DataProvider
    {
        public List<TuKhoaDiaDiem> getDsTuKhoaDiaDiem()
        {
            connect();
            string query = "SELECT * FROM TUKHOADIADIEM";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<TuKhoaDiaDiem> arr = new List<TuKhoaDiaDiem>();
            foreach (Object o in ls)
            {
                arr.Add((TuKhoaDiaDiem)o);
            }
            disconnect();
            return arr;
        }

        public int getMaDiaDiem(string tukhoa)
        {
            try
            {
                connect();
                string query = "SELECT * FROM TUKHOADIADIEM WHERE TuKhoaTenDiaDiem = '" + tukhoa + "'";
                adapter = new SqlDataAdapter(query, connection);
                DataSet dataset = new DataSet();
                adapter.Fill(dataset);
                ArrayList ls = ConvertDataSetToArrayList(dataset);
                List<TuKhoaDiaDiem> arr = new List<TuKhoaDiaDiem>();
                foreach (Object o in ls)
                {
                    arr.Add((TuKhoaDiaDiem)o);
                }

                disconnect();
                return arr[0].MaTenDiaDiem;
            }
            catch (Exception e)
            {
                return -1;
            }
        }

        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            TuKhoaDiaDiem tk = new TuKhoaDiaDiem();
            tk.MaTuKhoaTenDiaDiem = dt.Rows[i].IsNull("MaTuKhoaTenDiaDiem") == true ? 0 : (int)dt.Rows[i]["MaTuKhoaTenDiaDiem"];
            tk.TuKhoaTenDiaDiem = dt.Rows[i]["TuKhoaTenDiaDiem"].ToString();
            tk.MaTenDiaDiem = dt.Rows[i].IsNull("MaTenDiaDiem") == true ? 0 : (int)dt.Rows[i]["MaTenDiaDiem"];

            return (object)tk;
        }

        public bool updateTuKhoaDiaDiem(TuKhoaDiaDiem tk)
        {
            try
            {
                connect();
                string updateCommand = "UPDATE TUKHOADIADIEM SET TuKhoaTenDiaDiem = '" + tk.TuKhoaTenDiaDiem +
                    "', MaTenDiaDiem = " + tk.MaTenDiaDiem + " WHERE MaTuKhoaTenDiaDiem = " + tk.MaTuKhoaTenDiaDiem;
                executeNonQuery(updateCommand);
                disconnect();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        public void deleteTuKhoaDiaDiem(int id)
        {
            connect();
            string deleteCommand = "DELETE FROM TUKHOADIADIEM WHERE MaTuKhoaTenDiaDiem = " + id;
            executeNonQuery(deleteCommand);
            disconnect();
        }

    }
}
}