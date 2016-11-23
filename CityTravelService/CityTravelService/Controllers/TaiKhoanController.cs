<<<<<<< HEAD
﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CityTravelService.Models;
=======
﻿using System.Collections.Generic;
using CityTravelService.Models;
using System.Web.Http;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net;
using System;
>>>>>>> master
using System.Net.Mail;
using System.Configuration;

namespace CityTravelService.Controllers
{
<<<<<<< HEAD
    public class TaiKhoanController : ApiController
    {
     
        // GET: api/TaiKhoan
=======
    [RoutePrefix("api/TaiKhoan")]
    public class TaiKhoanController : ApiController
    {
        #region GET
        // GET: api/TaiKhoan
        [Route("")]
        [HttpGet]
>>>>>>> master
        public IEnumerable<TaiKhoan> Get()
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();

            TaiKhoan[] tk = new TaiKhoan[tkO.getDsTaiKhoan().Count];
            tk = tkO.getDsTaiKhoan().ToArray();
            return tk;
        }

        // GET: api/TaiKhoan/5
<<<<<<< HEAD
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
=======
        [Route("")]
        [HttpGet]
        public TaiKhoan Get(string email)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            TaiKhoan tk = new TaiKhoan();
            tk =tkO.getTaiKhoan(email);
            //if (tk.Length == 0)
            //    throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotFound));
            return tk;
        }

        // api đăng nhập
        // GET: 
        [Route("")]
        [HttpGet]
        public bool Get(string email, string password)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();

            TaiKhoan tk;
            tk = tkO.getTaiKhoan(email, password);
            if(tk.Email==null)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        #endregion

        #region POST
        // POST: api/TaiKhoan
        [Route("")]
        [HttpPost]
        public bool Post([FromBody]TaiKhoan tk)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            //tkO.insertTaiKhoan(tk);
            /*var response = Request.CreateResponse<TaiKhoan>(HttpStatusCode.Created, tk);
            response.Headers.Location = new System.Uri(Request.RequestUri, "/api/TaiKhoan/" + tk.Email.ToString());*/
            return tkO.insertTaiKhoan(tk);
        }
        #endregion

        #region PUT
        // PUT: api/TaiKhoan/5
        [Route("")]
        [HttpPut]
        public bool Put([FromBody]TaiKhoan tk)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            //tkO.updateTaiKhoan(tk);
            /*var response = Request.CreateResponse<TaiKhoan>(HttpStatusCode.Created, tk);
            response.Headers.Location = new System.Uri(Request.RequestUri, "/api/TaiKhoan/" + tk.Email.ToString());*/
            return tkO.updateTaiKhoan(tk);
        }
        #endregion

        #region DELETE
        // DELETE: api/TaiKhoan/5
        [Route("")]
        [HttpDelete]
        public bool Delete(string id)
        {
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            TaiKhoan tk = new TaiKhoan();
            tk = tkO.getTaiKhoan(id);
            if (tk == null)
                return false;
            /*throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotFound));*/
            return tkO.deleteTaiKhoan(id); //False: Khi no la khoa ngoại.
        }
        #endregion
        #region
        //FORGETPASSWORD:api/TaiKhoan/Forget
        [Route("Forget")]
        [HttpPut]
        public bool ForegetPassword(string email)
        {
            string temp = CreatePassword();
            MailMessage mailMessag = new MailMessage(ConfigurationManager.AppSettings.Get("Email"), email);
            mailMessag.Subject = "Gửi lại mật khẩu";
            mailMessag.Body = "Mật khẩu mới của bạn là: " + temp;
            SmtpClient client = new SmtpClient();
            client.Send(mailMessag);
            TaiKhoanDAO tkO = new TaiKhoanDAO();
            TaiKhoan tk = new TaiKhoan();
            tk = tkO.getTaiKhoan(email);
            if (tk==null)
                return false;
            tkO.updatePassword(temp, email);
            return true;
        }
        public string CreatePassword()
        {
            string _allowedChars = "ABCDEFGHIJKMNOPQRSTUVWXYZ0123456789";

            Random randNum = new Random();

            char[] chars = new char[6];

            int allowedCharCount = _allowedChars.Length;

            for (int i = 0; i < 6; i++)
            {
                chars[i] = _allowedChars[(int)((_allowedChars.Length) * randNum.NextDouble())];
                if (chars[i] == '0' || chars[i] == '1' || chars[i] == '2' || chars[i] == '3' || chars[i] == '4'
              || chars[i] == '5' || chars[i] == '6' || chars[i] == '7' || chars[i] == '8' || chars[i] == '9')
                {
                    _allowedChars = "ABCDEFGHIJKMNOPQRSTUVWXYZ";
                }
            }
            return new string(chars);
        }

        #endregion
    }
}
>>>>>>> master
