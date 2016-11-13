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
    public class DiaDiemDAO : DataProvider
    {

        public void insertDiaDiem(TENDIADIEM tenDD)
        {
            connect();
            string insertCommand = "INSERT INTO TENDIADIEM VALUES("+ " N'" +
                tenDD.TenDiaDiem1 + "')";
            executeNonQuery(insertCommand);
            disconnect();
        }

        public void updateDiaDiem(int matenDiaDiem, string TenDiaDiemMoi)
        {
            connect();
            string updatecommand = "update TENDIADIEM set TenDiaDiem = " + " N'" 
                                    + TenDiaDiemMoi + "' where MaTenDiaDiem = " + matenDiaDiem;
            executeNonQuery(updatecommand);
            disconnect();
        }
    }
}