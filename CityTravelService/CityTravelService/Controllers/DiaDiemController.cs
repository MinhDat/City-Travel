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
        public void Post([FromBody]TENDIADIEM tenDD)
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            ddO.insertDiaDiem(tenDD);
        }

        // PUT: api/DIADIEM/{id}?value={value}
        public void Put(int id, string value)
        {
            DiaDiemDAO ddO = new DiaDiemDAO();
            ddO.updateDiaDiem(id, value);
        }
    }
}
