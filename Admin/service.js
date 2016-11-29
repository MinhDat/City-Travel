var app=angular.module("loginapp", []);

app.service("myService", function ($http) {  
  
 this.UserLogin = function (User) {  
        var response = $http({  
            method: "get",  
            url: "http://citytravel-2.apphb.com/api/taikhoan",  
            data: JSON.stringify(User),  
            dataType: "json"  
        });  
        return response;  
    } 
});  