using System.Collections.Generic;
using CityTravelService.Models;
using System.Web.Http;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net;
using System;

namespace CityTravelService.Controllers
{
    public class TaiKhoanController : ApiController
    {
        #region GET
        // GET: api/TaiKhoan
        public IEnumerable<TaiKhoan> Get()
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();

            TaiKhoan[] tk = new TaiKhoan[tkO.getDsTaiKhoan().Count];
            tk = tkO.getDsTaiKhoan().ToArray();
            return tk;
        }

        // GET: api/TaiKhoan/5
        public IEnumerable<TaiKhoan> Get(string id)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();

            TaiKhoan[] tk = new TaiKhoan[tkO.getDsTaiKhoan(id).Count];
            tk = tkO.getDsTaiKhoan(id).ToArray();
            if (tk.Length == 0)
                throw new HttpResponseException(HttpStatusCode.NotFound);
            return tk;
        }
        #endregion

        #region POST
        // POST: api/TaiKhoan
        public HttpResponseMessage Post([FromBody]TaiKhoan tk)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            tkO.insertTaiKhoan(tk);
            var response = Request.CreateResponse<TaiKhoan>(HttpStatusCode.Created, tk);
            response.Headers.Location = new System.Uri(Request.RequestUri, "/api/TaiKhoan/" + tk.Email.ToString());
            return response;
        }
        #endregion

        #region PUT
        // PUT: api/TaiKhoan/5
        public HttpResponseMessage Put([FromBody]TaiKhoan tk)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            tkO.updateTaiKhoan(tk);
            var response = Request.CreateResponse<TaiKhoan>(HttpStatusCode.Created, tk);
            response.Headers.Location = new System.Uri(Request.RequestUri, "/api/DichVu/" + tk.Email.ToString());
            return response;
        }
        #endregion

        #region DELETE
        // DELETE: api/TaiKhoan/5
        public TaiKhoan Delete(string id)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            TaiKhoan[] tk = new TaiKhoan[tkO.getDsTaiKhoan(id).Count];
            tk = tkO.getDsTaiKhoan(id).ToArray();
            if (tk.Length == 0)
                throw new HttpResponseException(HttpStatusCode.NotFound);
            tkO.deleteTaiKhoan(id);
            return tk[0];
        }
        #endregion
    }
}
