﻿using CityTravelService.Models;
using CityTravelService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace CityTravelService.Controllers
{
    public class DuLieuController : ApiController
    {
        DuLieuDAO Dulieu_DAO;
        // GET api/dulieu
        public IEnumerable<DuLieu> Get()
        {
            Dulieu_DAO = new DuLieuDAO();
            DuLieu[] list_dulieu = new DuLieu[Dulieu_DAO.get_Ds_DuLieu().Count];
            list_dulieu = Dulieu_DAO.get_Ds_DuLieu().ToArray();
            return list_dulieu;
        }

        // GET api/dulieu/5
        //Lấy thông tin dữ liệu có tên địa điểm, tên phường, tên quận huyện (kết các bảng lại với nhau)
        
        public IEnumerable<DuLieu> Get(int ma_dulieu)
        {
            Dulieu_DAO = new DuLieuDAO();
            DuLieu[] dulieu = new DuLieu[Dulieu_DAO.get_DuLieu(ma_dulieu).Count];
            dulieu = Dulieu_DAO.get_DuLieu(ma_dulieu).ToArray();
            if (dulieu.Length == 0)
                throw new HttpResponseException(HttpStatusCode.NotFound);
            return dulieu;
        }
        // POST api/dulieu
        public HttpResponseMessage Post([FromBody]DuLieu value)
        {
            Dulieu_DAO = new DuLieuDAO();
            bool ret = Dulieu_DAO.insert_DuLieu(value);
            var response = Request.CreateResponse<bool>(HttpStatusCode.Created, ret);
            return response;
        }

        // PUT api/dulieu/5
        public HttpResponseMessage Put(int id, [FromBody]string sonha)
        {
            Dulieu_DAO = new DuLieuDAO();
            bool ret = Dulieu_DAO.update_DuLieu(id, sonha);
            var response = Request.CreateResponse<bool>(HttpStatusCode.Created, ret);
            return response;
        }

        // DELETE api/dulieu/5
        public HttpResponseMessage Delete(int id)
        {
            Dulieu_DAO = new DuLieuDAO();
            bool ret = Dulieu_DAO.delete_DuLieu(id);
            var response = Request.CreateResponse<bool>(HttpStatusCode.Created, ret);
            return response;
        }


    }
}
