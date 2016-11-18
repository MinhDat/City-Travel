using CityTravelService.Entity;
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
        DULIEU_DAO Dulieu_DAO;
        CHITIET_DULIEU ChiTiet_DuLieu;
        // GET api/dulieu
        public IEnumerable<DULIEU> Get()
        {
            Dulieu_DAO = new DULIEU_DAO();
            DULIEU[] list_dulieu = new DULIEU[Dulieu_DAO.get_Ds_DuLieu().Count];
            list_dulieu = Dulieu_DAO.get_Ds_DuLieu().ToArray();
            return list_dulieu;
        }

        // GET api/dulieu/5
        //Lấy thông tin dữ liệu có tên địa điểm, tên phường, tên quận huyện (kết các bảng lại với nhau)
        public CHITIET_DULIEU Get(int ma_dulieu)  
        {
            ChiTiet_DuLieu = new CHITIET_DULIEU();
            CHITIET_DULIEU chitiet = ChiTiet_DuLieu.get_DuLieu_ChiTiet(ma_dulieu);
            return chitiet;
        }

        public DULIEU Get1(int ma_dulieu)
        {
            Dulieu_DAO = new DULIEU_DAO();
            DULIEU dulieu = Dulieu_DAO.get_DuLieu(ma_dulieu);
            return dulieu;
        }
        // POST api/dulieu
        public void Post([FromBody]DULIEU value)
        {
            Dulieu_DAO = new DULIEU_DAO();
            Dulieu_DAO.insert_DuLieu(value);
        }

        // PUT api/dulieu/5
        public void Put(int id, [FromBody]string sonha)
        {
            Dulieu_DAO = new DULIEU_DAO();
            Dulieu_DAO.update_DuLieu(id, sonha);
        }

        // DELETE api/dulieu/5
        public void Delete(int id)
        {
            Dulieu_DAO = new DULIEU_DAO();
            Dulieu_DAO.delete_DuLieu(id);
        }


    }
}
