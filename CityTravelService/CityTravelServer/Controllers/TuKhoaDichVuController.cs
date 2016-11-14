using CityTravelServer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace CityTravelServer.Controllers
{
    public class TuKhoaDichVuController : ApiController
    {
        // GET api/tukhoadichvu
        public IEnumerable<TuKhoaDichVu> Get()
        {
            TuKhoaDichVuDAO tkdvO = new TuKhoaDichVuDAO();

            TuKhoaDichVu[] tkdv = new TuKhoaDichVu[tkdvO.getDsTuKhoaDichVu().Count];
            tkdv = tkdvO.getDsTuKhoaDichVu().ToArray();
            return tkdv;
        }

        // GET api/tukhoadichvu/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/tukhoadichvu
        public void Post([FromBody]TuKhoaDichVu tkdv)
        {
            TuKhoaDichVuDAO tkdv0 = new TuKhoaDichVuDAO();
            tkdv0.insertTuKhoaDichVu(tkdv);
        }

        // PUT api/tukhoadichvu/5
        public void Put(int id, [FromBody]string value)
        {

        }

        // DELETE api/tukhoadichvu/5
        public void Delete(int id)
        {
            TuKhoaDichVuDAO tkdv0 = new TuKhoaDichVuDAO();
            tkdv0.deleteTuKhoaDichVu(id);
        }
    }
}
