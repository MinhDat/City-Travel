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
        public string CreateCode()
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
        [AllowAnonymous]
        [Route("ForgetPassword")]
        [HttpGet]
        public bool Main(string email)
        {          
            MailMessage mailMessag = new MailMessage(ConfigurationManager.AppSettings.Get("Email"), email);
            mailMessag.Subject = "Gửi lại mật khẩu";
            mailMessag.Body = "Mã khẩu của bạn là: " + CreateCode();
            SmtpClient client = new SmtpClient();
            client.Send(mailMessag);
            return true;
        }
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
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/TaiKhoan/5
        public void Delete(int id)
        {
        }
    }
}
