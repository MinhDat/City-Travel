(function () {
    'use strict';
var app=angular.module('app',['ui.router','ngCookies']);
app.factory("UserInfo", [
            function(){
            var info = {
                user: {
                    Address: '',
                    Birth: '',
                    Email: '',
                    FirtName: '',
                    IdUser: '',
                    LastName:'',
                    PassWord:'',
                    Phone:'',
                    Picture:'',
                    Role:'',
                    Sex:''
                }            
            };
            return info;
        }]);
app.controller('LoginController',['$scope','$http','$location','$log','$state','UserInfo',function($scope,$http,$location,$log,$state,UserInfo)
        {
          $scope.SignIn=function(event){
            $scope.dataLoading=true;
             $http({
                        method: 'GET',
                        url: 'http://citytravel-2.apphb.com/api/TaiKhoan',
                        params: {
                            email:$scope.email,
                            password:$scope.password,
                            provider:'Local'
                          }
                    }).then(function successCallback(response) {
                        if(response.data)
                        {
                            UserInfo.user.Address=response.data.Address;
                            UserInfo.user.Birth=moment(response.data.Birth).format('DD/MM/YYYY');
                            UserInfo.user.Email=response.data.Email;
                            UserInfo.user.FirtName=response.data.FirtName;
                            UserInfo.user.IdUser=response.data.IdUser;
                            UserInfo.user.LastName=response.data.LastName;
                            UserInfo.user.PassWord=response.data.PassWord;
                            UserInfo.user.Phone=response.data.Phone;
                            UserInfo.user.Picture=response.data.Picture;
                            UserInfo.user.Role=response.data.Role;
                            UserInfo.user.Sex=response.data.Sex;
                            console.log(response);
                            if(UserInfo.user.IdUser==null)
                            {
                               $scope.dataLoading=false;
                                $scope.error="Login Fail";
                            }
                            else
                            {
                              if(UserInfo.user.Role=="Admin")
                              {
                             $scope.message="Login Success";
                              $scope.dataLoading=false;
                              $state.go("/dichvu"); 
                              }  
                              else
                              {
                                 $scope.dataLoading=false;
                                $scope.error="Not authorize";
                              }
                            }
                        }
                      
                    }, function errorCallback(response) {
                        
                    });
                  } 
        }]);
app.controller('dichvuController',['$scope','$http','$location','$log','$state','UserInfo',function($scope,$http,$location,$log,$state,UserInfo){
    var refresh=function(){
        $http({
                    method: 'GET',
                    url: 'http://citytravel-2.apphb.com/api/dichvu'
                }).then(function successCallback(response) {
                    $scope.dichvus=response.data;
                }, function errorCallback(response) {
                });
             };

refresh(); 
$scope.adddichvu = function(){
console.log("hello");
  console.log($scope.text);
  document.getElementById('btnupdate').disabled = true;
  if ($scope.text == "")
      {
          confirm("Bạn chưa nhập hoặc nhập thiếu dữ liệu");
      }
  else
      {
          $http.post('http://citytravel-2.apphb.com/api/dichvu',$scope.text).then(function(response){
             console.log(response);
             refresh();
          });
      }
};

    
     $scope.deletedichvu = function(id){
        console.log(id);
        $http.delete('http://citytravel-2.apphb.com/api/dichvu?id='+id,id).then(function(response){
            refresh();
        });
    };
    
    $scope.editdichvu = function(id) {
        console.log(id);
        document.getElementById('tID').disabled = true;
        document.getElementById('btnadd').disabled = true;
        document.getElementById('btnupdate').disabled = false;
          $http.get('http://citytravel-2.apphb.com/api/dichvu?id='+id,id).then(function(response){
            console.log(response);
            $scope.text = response.data[0];
         });
    };
    
     $scope.updatedichvu = function(){
        document.getElementById('tID').disabled = false;
        document.getElementById('btnadd').disabled = false;
        document.getElementById('btnupdate').disabled = false;
        console.log($scope.text);
        $http.put('http://citytravel-2.apphb.com/api/dichvu?id=' + $scope.text.ID, $scope.text).then(function(response){
           console.log(response);
            refresh();
        });
    };
    }]);


app.controller('tendiadiemController',['$scope','$http','$location','$log','$state','UserInfo',function($scope,$http,$location,$log,$state,UserInfo){
    var refresh=function(){
        $http({
                    method: 'GET',
                    url: 'http://citytravel-2.apphb.com/api/tendiadiem'
                }).then(function successCallback(response) {
                    $scope.tendiadiems=response.data;
                }, function errorCallback(response) {
                });
             };

refresh(); 
$scope.addtendiadiem = function(){
console.log("hello");
  console.log($scope.text);
  document.getElementById('btnupdate').disabled = true;
  if ($scope.text == "")
      {
          confirm("Bạn chưa nhập hoặc nhập thiếu dữ liệu");
      }
  else
      {
          $http.post('http://citytravel-2.apphb.com/api/tendiadiem',$scope.text).then(function(response){
             console.log(response);
             refresh();
          });
      }
};

    
     $scope.deletetendiadiem = function(id){
        console.log(id);
        $http.delete('http://citytravel-2.apphb.com/api/tendiadiem/'+id,id).then(function(response){
            refresh();
        });
    };
    
    $scope.edittendiadiem = function(id) {
        console.log(id);
        document.getElementById('tID').disabled = true;
        document.getElementById('btnadd').disabled = true;
        document.getElementById('btnupdate').disabled = false;
          $http.get('http://citytravel-2.apphb.com/api/tendiadiem?ma_ten_diadiem='+id,id).then(function(response){
            console.log(response);
            $scope.text = response.data;
         });
    };
    
     $scope.updatetendiadiem = function(){
        document.getElementById('tID').disabled = false;
        document.getElementById('btnadd').disabled = false;
        document.getElementById('btnupdate').disabled = false;
        console.log($scope.text);
        $http.put('http://citytravel-2.apphb.com/api/tendiadiem/' + $scope.text.MaTenDiaDiem, $scope.text).then(function(response){
           console.log(response);
            refresh();
        });
    };
    }]);


app.controller('taikhoanController',['$scope','$http','$location','$log','$state','UserInfo',function($scope,$http,$location,$log,$state,UserInfo){
   var refresh=function(){
         $http.get("http://citytravel-2.apphb.com/api/taikhoan")
  .then(function(response) {
    console.log(response.data);
      $scope.taikhoans = response.data;
  });
};

refresh();

  $scope.deletetaikhoan = function(id){
        console.log(id);
        $http.delete('http://citytravel-2.apphb.com/api/taikhoan?id='+id,id).then(function(response){
            refresh();
        });
    };
    }]);
app.controller('binhluanController',['$scope','$http','$location','$log','$state','UserInfo',function($scope,$http,$location,$log,$state,UserInfo){
   var refresh=function(){
         $http.get("http://citytravel-2.apphb.com/api/BinhLuanAdmin")
  .then(function(response) {
    console.log(response.data);
      $scope.binhluans = response.data;
  });
};
refresh();

$scope.approvedcomment = function(mabinhluan){
  console.log(mabinhluan);
   $http.put('http://citytravel-2.apphb.com/api/binhluan?id=' + mabinhluan,mabinhluan).then(function(response){
           console.log(response);
            refresh();
        });
}

  $scope.deletecomment = function(id){
        console.log(id);
        $http.delete('http://citytravel-2.apphb.com/api/binhluan?id='+id,id).then(function(response){
            refresh();
        });
    };
    }]);


app.config(['$stateProvider','$urlRouterProvider','$locationProvider',
        function ($stateProvider, $urlRouterProvider,$locationProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider.state("/", {
                    url: '/',
                    templateUrl: "/ProjectMain/login/login.view.html",
                    controller: "LoginController"
                }).state("/dichvu", {
                    url: '/dichvu',
                    templateUrl: "/ProjectMain/dichvu/dichvu.html",
                    controller: "dichvuController"
                }).state("/home", {
                    url: '/home',
                    templateUrl: "/ProjectMain/dichvu/home.html"
                }).state("/binhluan", {
                    url: '/binhluan',
                    templateUrl: "/ProjectMain/dichvu/binhluan.html",
                    controller: "binhluanController"
                }).state("/tendiadiem", {
                    url: '/tendiadiem',
                    templateUrl: "/ProjectMain/dichvu/diadiem.html",
                    controller: "tendiadiemController"
                }).state("/taikhoan", {
                    url: '/taikhoan',
                    templateUrl: "/ProjectMain/dichvu/taikhoan.html",
                    controller: "taikhoanController"
                });
    }]);
   
})();