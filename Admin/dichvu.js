
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
        //document.getElementById('tID').disabled = true;
        document.getElementById('btnadd').disabled = true;
        document.getElementById('btnupdate').disabled = false;
          $http.get('http://citytravel-2.apphb.com/api/dichvu/'+id,id).then(function(response){
            console.log(response);
            $scope.text = response.data;
         });
    };
    
     $scope.updatedichvu = function(){
        document.getElementById('ctID').disabled = false;
        document.getElementById('btAdd').disabled = false;
        document.getElementById('btUpdate').disabled = true;
        console.log($scope.text);
        $http.put('http://citytravel-2.apphb.com/api/dichvu/' + $scope.text.MaDichVu, $scope.text).then(function(response){
           console.log(response);
            refresh();
        });
    };
    
    });