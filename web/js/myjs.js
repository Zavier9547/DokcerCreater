var dependenciesOj = $('#dependenciesList>tbody');
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
    dependenciesOj.append('<tr><td></td><td>' + name + '</td><td>' + version + '</td></tr>');
    };
    nameOj.val('name');
    versionOj.val('default');

});

var formOj=$('form');
$('#submit1').click(function(){
	var nameOfImageOj=formOj.find('#nameOfImage');
	var versionOfImageOj=formOj.find('#versionOfImage');
	var nameOfParentOj=formOj.find('#nameOfParent');
	var versionOfParentOj=formOj.find('#versionOfParent');
	var cmdOj=formOj.find('#command');

	var jsonM = {};
	jsonM.nameOfImage=nameOfImageOj.val();
	jsonM.versionOfImage=versionOfImageOj.val();
	jsonM.nameOfParent=nameOfParentOj.val();
	jsonM.versionOfParent=versionOfParentOj.val();
	jsonM.dependencies={};
	dependenciesOj.each(function() {
		var tdData=[];
		$(this).find('td').each(function(i) {
			tdData[i]=$(this).text();
		});
		jsonM.dependencies[tdData[1]]=tdData[2];
		
	});
	jsonM.cmd=cmdOj.val();

	alert(JSON.stringify(jsonM,null, ' '));
	
});

$(function () {
	$('#well1').hide();
});

$('#img1').click(function() {
	$('#well1').show();
});

$('#test').click(function() {
	$.get('http://localhost', function(data) {
		alert(data);
	});
});