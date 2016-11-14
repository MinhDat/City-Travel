using CityTravelServer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace CityTravelServer.Controllers
{
    public class BinhLuanController : ApiController
    {
        // GET: api/BinhLuan
        public IEnumerable<BinhLuan> Get()
        {
            BinhLuanDAO dvO = new BinhLuanDAO();

            BinhLuan[] dv = new BinhLuan[dvO.getDsBinhLuan().Count];
            dv = dvO.getDsBinhLuan().ToArray();
            return dv;
        }

        // GET: api/DichVu/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/DichVu
        public void Post([FromBody]BinhLuan bl)
        {
            BinhLuanDAO blO = new BinhLuanDAO();
            blO.insertBinhLuan(bl);
        }

        // DELETE: api/DichVu/5
        public void Delete(string id)
        {
            BinhLuanDAO blO = new BinhLuanDAO();
            blO.deleteBinhLuan(id);
        }
    }
}
