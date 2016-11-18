using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CityTravelService.Entity;
using CityTravelService.Models;

namespace CityTravelService.Controllers
{
    public class DiaDiemController : ApiController
    {
        // POST: api/DiaDiem
        //Them dia diem
        public void Post([FromBody]TENDIADIEM tenDD)
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            ddO.insertDiaDiem(tenDD);
        }

        // PUT: api/DIADIEM/{id}?value={value}
        //chinh sua ten dia diem
        public void Put(int id, [FromBody]string value)
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            ddO.updateDiaDiem(id, value);
        }


        // GET: api/DiaDiem
        public IEnumerable<TENDIADIEM> Get()
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            TENDIADIEM[] result = new TENDIADIEM[ddO.getAllDiaDiem().Count];
            result = ddO.getAllDiaDiem().ToArray();
            return result;
        }

        //GET: api/DiaDiem/5
        public void Delete(int id, [FromBody]string tendiadiem)
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            ddO.DeleteDiaDiem(id, tendiadiem);
        }
    }
}
