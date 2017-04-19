var dependencies = $('#dependenciesList>tbody');
var dependenciesAlert = $('#alert1');

$(function () {
	dependenciesAlert.hide();
});

$('#addDependences').click(function() {
	dependenciesAlert.hide();
    var nameOj = $('#nameOfDependences');
    var versionOj = $('#versionOfDependences');
    var name = nameOj.val();
    var version = versionOj.val();
    if (version === ''){
    	version='default';
    }
    if (name==='name' || name === ''){
    	dependenciesAlert.html('<strong>The name of dependency must not be null.</strong>');
    	dependenciesAlert.show();
    }else{
    dependencies.append('<tr><td></td><td>' + name + '</td><td>' + version + '</td></tr>');
    };
    nameOj.val('name');
    versionOj.val('default');

});

