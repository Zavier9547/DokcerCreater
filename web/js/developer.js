var dependenciesOj = $('#dependenciesList>tbody');

$(function() {});

$('#add').click(function() {
    var jsonM = {}
    jsonM.name = $('#name').val()
    jsonM.version = $('#version').val()
    jsonM.architecture = $('#architecture').val()

    $.ajax({
            url: 'http://localhost:80/dep',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonM)
        })
        .done(function(re) {
            if (re.status == "fail") {
                alert(re.info)
            } else {
                dependenciesOj.append('<tr><td></td><td>'+jsonM.name+'</td><td>'+jsonM.version+'</td><td>'+jsonM.architecture+'</td></tr>')
                $('#name').val('')
            }
        });
});

var tableOj = $('#read')
var jsonA = []
$('#conflict').click(function() {
    tableOj.find("tr").each(function() {
        var x = []
        $(this).find("td").each(function(index, el) {
            x[index] = $(el).text()
        });
        var y = {}
        y.name = x[1]
        y.version = x[2]
        y.architecture = x[3]
        jsonA.push(y)
    });
    var jsonM2 = {}
    jsonM2.name = $('#nameOfParent').val()
    jsonM2.version = $('#versionOfParent').val()
    $.ajax({
            url: 'http://localhost:80/images/id',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonM2)
        })
        .done(function(data) {
            $.ajax({
                    url: 'http://localhost:80/images/' + data.info,
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(jsonA)
                })
                .done(function(re) {
                    jsonA = []
                    if (re.status == "fail") {
                        alert(re.info)
                    } else {
                        alert("hehe,no problem")
                    }
                });
        });

});


$("#ImageList").bootstrapTable({
    url: "http://localhost:80/images",
    columns: [
        [ {
            title: '镜像名称',
            field: 'name',
            align: 'center',
            valign: 'middle'
        }, {
            title: '镜像版本',
            field: 'version',
            align: 'center',
            valign: 'middle'
        }, {
            title: '软件依赖',
            field: 'dependencies',
            align: 'center',
            valign: 'middle'
        }]
    ]
})