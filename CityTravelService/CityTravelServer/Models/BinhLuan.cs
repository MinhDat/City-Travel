using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CityTravelServer.Models
{
    public class BinhLuan
    {
        public String MaBinhLuan { get; set; }
        public string MaTaiKhoan { get; set; }
        public string NoiDung { get; set; }
        public DateTime ThoiGian { get; set; }
    }
}