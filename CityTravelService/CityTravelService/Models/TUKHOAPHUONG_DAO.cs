using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CityTravelService.Models
{
    public class TUKHOAPHUONG_DAO : DataProvider
    {
        public List<TuKhoaPhuong> getDsTuKhoaPhuong()
        {
            connect();
            string query = "select * from TUKHOAPHUONG";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<TuKhoaPhuong> listDD = new List<TuKhoaPhuong>();
            foreach (Object o in ls)
            {
                listDD.Add((TuKhoaPhuong)o);
            }
            disconnect();
            return listDD;
        }

        public int getMaPhuong(string TuKhoaPhuong)
        {
            connect();
            string query = "SELECT * FROM TUKHOAPHUONG WHERE TuKhoaPhuong = N'" + TuKhoaPhuong + "'";
            adapter = new SqlDataAdapter(query, connection);
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            ArrayList ls = ConvertDataSetToArrayList(dataset);
            List<TuKhoaPhuong> arr = new List<TuKhoaPhuong>();
            foreach (Object o in ls)
            {
                arr.Add((TuKhoaPhuong)o);
            }
            if (arr.Count() == 0)
            {
                disconnect();
                return 0;
            }
            else
            {
                TuKhoaPhuong p = new TuKhoaPhuong();
                p = arr[0];
                disconnect();
                return p.MaPhuong;
            }
            
        }
        public bool insertTuKhoaPhuong(TuKhoaPhuong tkp)
        {
            try
            {
                connect();
                string insertCommand = "INSERT INTO TUKHOAPHUONG VALUES( N'" +
                    tkp.TuKhoaPhuong1 + "'," + tkp.MaPhuong + ")";
                executeNonQuery(insertCommand);
                disconnect();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }

        }

        public bool updateTuKhoaPhuong(int matukhoaphuong, string tukhoaphuong)
        {
            try
            {
                connect();
                string updatecommand = "update TUKHOAPHUONG set TuKhoaPhuong = " + " N'"
                                        + tukhoaphuong + "' where MaTuKhoaPhuong = " + matukhoaphuong;
                executeNonQuery(updatecommand);
                disconnect();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        public bool deleteTuKhoaPhuong(int id)
        {
            try
            {
                connect();
                string updatecommand = "DELETE FROM TUKHOAPHUONG WHERE MaTuKhoaPhuong = " + id;
                executeNonQuery(updatecommand);
                disconnect();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
        protected override object GetDataFromDataRow(DataTable dt, int i)
        {
            TuKhoaPhuong tkp = new TuKhoaPhuong();
            tkp.MaTuKhoaPhuong = (int)dt.Rows[i]["MaTuKhoaPhuong"];
            tkp.TuKhoaPhuong1 = dt.Rows[i]["TuKhoaPhuong"].ToString();
            tkp.MaPhuong = (int)dt.Rows[i]["MaPhuong"];
            return (object)tkp;
        }
    }
}