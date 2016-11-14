using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CityTravelService.Models;
using System.Net.Mail;
using System.Configuration;

namespace CityTravelService.Controllers
{
    public class TaiKhoanController : ApiController
    {
     
        // GET: api/TaiKhoan
        public IEnumerable<TaiKhoan> Get()
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();

            TaiKhoan[] tk = new TaiKhoan[tkO.getDsTaiKhoan().Count];
            tk = tkO.getDsTaiKhoan().ToArray();
            return tk;
        }

        // GET: api/TaiKhoan/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/TaiKhoan
        public void Post([FromBody]TaiKhoan tk)
        { 
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            tkO.insertTaiKhoan(tk);
        }

        // PUT: api/TaiKhoan/5
        public void Put([FromBody]TaiKhoan tk)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            tkO.updateTaiKhoan(tk);
        }

        // DELETE: api/TaiKhoan/5
        public void Delete(string id)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            tkO.deleteTaiKhoan(id);
        }
    }
}
