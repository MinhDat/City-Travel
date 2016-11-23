var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider
  .when("/index", {
    templateUrl : "../index.handlebars"
  })  
  .when("/menu1", {
    templateUrl : "../service.handlebars"
  })
  .when("/menu2", {
    templateUrl : "../places.handlebars"
  })
  .when("/menu3", {
    templateUrl : "../user.handlebars"
  })
 
});