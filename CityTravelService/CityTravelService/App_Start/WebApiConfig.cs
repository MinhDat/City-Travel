using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using CityTravelService.Models;
using System.Configuration;

namespace CityTravelService
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services
            DataProvider.ConnectionString = ConfigurationManager.ConnectionStrings["connect"].ToString();
            // Web API routes
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

        }
    }
}
