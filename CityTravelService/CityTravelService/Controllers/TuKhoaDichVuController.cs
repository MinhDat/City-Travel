﻿using CityTravelServer.Models;
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
        public IEnumerable<TuKhoaDichVu> Get(int id)
        {
            TuKhoaDichVuDAO dvO = new TuKhoaDichVuDAO();

            TuKhoaDichVu[] dv = new TuKhoaDichVu[dvO.getDsTuKhoaDichVu(id).Count];
            dv = dvO.getDsTuKhoaDichVu(id).ToArray();
            if (dv.Length == 0)
                throw new HttpResponseException(HttpStatusCode.NotFound);
            return dv;
        }

        // POST api/tukhoadichvu
        public void Post([FromBody]TuKhoaDichVu tkdv)
        {
            TuKhoaDichVuDAO tkdv0 = new TuKhoaDichVuDAO();
            tkdv0.insertTuKhoaDichVu(tkdv);
        }

        // PUT api/tukhoadichvu/5
        public void Put([FromBody]TuKhoaDichVu tkdv)
        {
            TuKhoaDichVuDAO tkdv0 = new TuKhoaDichVuDAO();
            tkdv0.updateTuKhoaDichVu(tkdv);
        }

        // DELETE api/tukhoadichvu/5
        public void Delete(int id)
        {
            TuKhoaDichVuDAO tkdv0 = new TuKhoaDichVuDAO();
            tkdv0.deleteTuKhoaDichVu(id);
        }
    }
}