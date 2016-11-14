using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CityTravelService.Models;

namespace CityTravelService.Controllers
{
    public class DichVuController : ApiController
    {
        // GET: api/DichVu
        public IEnumerable<DichVu> Get()
        {
            DichVuDAO dvO = new DichVuDAO();

            DichVu[] dv = new DichVu[dvO.getDsDichVu().Count];
            dv = dvO.getDsDichVu().ToArray();
            return dv;
        }

        //// GET: api/DichVu/5
        //public DichVu Get(int id)
        //{
        //    DichVuDAO dvO = new DichVuDAO();

        //    DichVu dv = new DichVu();
        //    dv = dvO.getDichVu(id);
        //    return dv;
        //    //return "value";
        //}

        // POST: api/DichVu
        public void Post([FromBody]DichVu dv)
        {
            DichVuDAO dv0 = new DichVuDAO();
            dv0.insertDichVu(dv);
        }

        // PUT: api/DichVu/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/DichVu/5
        public void Delete(int id)
        {
            DichVuDAO dv0 = new DichVuDAO();
            dv0.deleteDichVu(id);
        }
    }
}
