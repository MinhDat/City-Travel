var app=angular.module("loginapp", []);

    app.controller('logincontroller',function($scope,$http,$log,myService){
    	$scope.dangnhap=function(){
var User = {  
            UserName: $scope.Email,  
            Password: $scope.Password  
        };  
        $("#divLoading").show();  
        $http.get("http://citytravel-2.apphb.com/api/taikhoan")
  .then(function(response) {
       var getData = response.data;
       getData.then(function (msg){  
            if (msg.data == "0") {  
                $("#divLoading").hide();  
                $("#alertModal").modal('show');  
                $scope.msg = "Password Incorrect !";  
            }  
            else if (msg.data == "-1") {  
                $("#divLoading").hide();  
                $("#alertModal").modal('show');  
                $scope.msg = "Username Incorrect !"; 
            }  
            else {  
                uID = msg.data;  
                $("#divLoading").hide();  
                window.location.href = "/Home/Index";  
            }  
        });  
        debugger;  
              
  });
        
    }  
  
function clearFields() {  
        $scope.Email = '';        
        $scope.Password = '';  
    }  


$scope.alertmsg = function () {  
        $("#alertModal").modal('hide');  
    };
});
