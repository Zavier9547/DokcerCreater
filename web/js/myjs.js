var dependenciesOj = $('#dependenciesList>tbody');
var dependenciesAlert = $('#alert1');

$('#add').click(function() {
    var jsonM = {}
    jsonM.groupId = $('#groupId').val()
    jsonM.artifactId = $('#artifactId').val()
    jsonM.version = $('#version').val()

    $.ajax({
            url: 'http://localhost:80/dep',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonM)
        })
        .done(function(re) {
            if (re.status == "fail") {
                alert("该依赖不存在，请确认你的输入。")
            } else {
                var jsonM2 = {}
                jsonM2.name = $('#nameOfParent').val()
                jsonM2.version = $('#versionOfParent').val()

                $.ajax({
                        url: 'http://localhost:80/images',
                        type: 'POST',
                        dataType: 'json',
                        contentType: 'application/json',
                        data: JSON.stringify(jsonM2)
                    })
                    .done(function(data) {
                        $.ajax({
                        	url: 'http://localhost:80/images/'+data.info,
                        	type: 'POST',
                        	dataType: 'json',
                        	contentType: 'application/json',
                        	data: JSON.stringify(jsonM)
                        })
                        .done(function(t) {
                        	if (t.status=="crashed"){
                        		alert("该依赖和基础镜像存在依赖冲突！");
                        	}else{
                        		console.log(t.status)
                        		dependenciesOj.append('<tr><td></td><td>'+jsonM.groupId+'</td><td>'+jsonM.artifactId+'</td><td>'+jsonM.version+'</td></tr>')
        
                        	}
                        })
                    })

            }
        });


});


$(function() {});

var formOj2 = $('#form2')
var depOj2 = $('#dep')
$('#getTable').click(function() {
    var nameOfImageOj = formOj2.find('#nameOfImage')
    var versionOfImageOj = formOj2.find('#versionOfImage')
    var jsonM = {}
    jsonM.name = nameOfImageOj.val()
    jsonM.version = versionOfImageOj.val()
    $.ajax({
            url: 'http://localhost:80/images',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonM)
        })
        .done(function(data) {
            $.get('http://localhost:80/images/' + data.info + '/table', function(re) {
                depOj2.empty();
                depOj2.append('<thead><tr><th></th><th>依赖名称</th></tr></thead>')
                depOj2.append('<tbody>')
                re.forEach(function(value, index, array) {
                    depOj2.append('<tr><td></td><td>' + value.name + '</td></tr>')
                });
                depOj2.append('</tbody>')
            });
        })


})
