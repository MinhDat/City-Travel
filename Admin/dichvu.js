
var app=angular.module("Appdichvu", []);

    app.controller('dichvuController',function($scope,$http,$log){
var refresh=function(){
         $http.get("http://citytravel-2.apphb.com/api/dichvu")
  .then(function(response) {
      $scope.dichvus = response.data;
              
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
        $http.delete('http://citytravel-2.apphb.com/api/dichvu/'+id,id).then(function(response){
            refresh();
        });
    };
    
    $scope.editdichvu = function(id) {
        console.log(id);
        document.getElementById('tID').disabled = true;
        document.getElementById('btnadd').disabled = true;
        document.getElementById('btnupdate').disabled = false;
          $http.get('http://citytravel-2.apphb.com/api/dichvu/'+id,id).then(function(response){
            console.log(response);
            $scope.text = response.data[0];
         });
    };
    
     $scope.updatedichvu = function(){
        document.getElementById('tID').disabled = false;
        document.getElementById('btnadd').disabled = false;
        document.getElementById('btnupdate').disabled = false;
        console.log($scope.text);
        $http.put('http://citytravel-2.apphb.com/api/dichvu/' + $scope.text.ID, $scope.text).then(function(response){
           console.log(response);
            refresh();
        });
    };
    });


    app.controller('taikhoanController',function($scope,$http,$log){
var refresh=function(){
         $http.get("http://citytravel-2.apphb.com/api/taikhoan")
  .then(function(response) {
    console.log(response.data);
      $scope.taikhoans = response.data;
  });
};

refresh();

  $scope.deletetaikhoan = function(email){
        console.log(email);
        $http.delete('http://citytravel-2.apphb.com/api/taikhoan/'+email,email).then(function(response){
            refresh();
        });
    };
  });


app.controller('binhluanController',function($scope,$http,$log){
var refresh=function(){
         $http.get("http://citytravel-2.apphb.com/api/binhluan")
  .then(function(response) {
    console.log(response.data);
      $scope.binhluans = response.data;
  });
};
refresh();

$scope.Cancelcomment = function(mabinhluan,trangthai){
  console.log(mabinhluan);
  console.log(trangthai);
  $scope.binhluans.trangthai=0;
   $http.put('http://citytravel-2.apphb.com/api/binhluan/' + mabinhluan,$scope.binhluans).then(function(response){
           console.log(response);
            refresh();
        });
}

});










