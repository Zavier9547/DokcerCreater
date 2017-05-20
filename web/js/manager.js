$("#DepList").bootstrapTable({
    url: "http://localhost:80/dep",
    columns: [
        [ {
            title: '依赖名称',
            field: 'name',
            align: 'center',
            valign: 'middle'
        }, {
            title: '依赖版本',
            field: 'version',
            align: 'center',
            valign: 'middle'
        }, {
            title: '依赖架构',
            field: 'architecture',
            align: 'center',
            valign: 'middle'
        }, {            
            title: '父依赖',
            field: 'dependencies',
            align: 'center',
            valign: 'middle'
        }, {            
            title: '操作',
            field: 'operate',
            align: 'center',
            valign: 'middle',
            formatter: operateFormatter
        }]
    ]
})

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
            title: '镜像根级依赖',
            field: 'dependencies',
            align: 'center',
            valign: 'middle'
        }, {            
            title: '操作',
            field: 'operate',
            align: 'center',
            valign: 'middle',
            formatter: operateFormatter
        }]
    ]
})

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="btn btn-success  btn-sm" style="margin-right:15px;">编辑</button>',
        '<button type="button" class="btn btn-default  btn-sm" style="margin-right:15px;">删除</button>',
    ].join('');
}
