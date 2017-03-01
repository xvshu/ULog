/**
 * /**
 * 验证名称是否重复
 * @param actionName
 * @param name  字段名成
 * @param message 提示信息
 * @param formId 表单id
 */
function ajaxCheckName(actionName,data, message, formId) {
    if(typeof(formId) == 'undefined') {
        formId = 'addForm';
    }
    $.ajax({
        url: actionName,
        type:"POST",
        dataType:"json",
        data: data,
        cache:false,
        success:function(msg){
            var state = parseInt(msg);
            if(state == 0){
                jQuery("#" + formId).submit();
                $('#addwin').dialog('close');
            }else if(state == 1){
                alert( message + "名称与已有的重复,请修改!");
            } else if(state == 2) {
                alert("所选任务类型和接收端名称已生成一份订阅关系，请重新选择!");
            }
        },error:function(XMLHttpRequest, textStatus, errorThrown){
            alert("error:="+ errorThrown.toString()+",XMLHttpRequest="+XMLHttpRequest+",textStatus="+textStatus);
        }
    });
}

/**
 * 清空表单内容
 * @param formId
 */
function clearFormValue(value,formId){
    try {
        jQuery("input[name*='" + value + "']", jQuery('#' + formId)).val('');
    } catch(e) {    }
}
/**
 * 返回所选项的名字和id
 * @param name
 * @param id
 */
function choseOneValue(name, id) {
    alert(name + ":" + id);
    try {
        window.returnValue = name + "|" + id;
        window.close();
    } catch(e) {
        alert(e.description);
    }
}
/**
 * 换页
 * @param page
 * @param formId
 */
function goNextPage(page,formId) {
    var sInput = "<input type=\"hidden\" id=\"page\" name=\"page\" value=\"" + page + "\"/>";
    if(typeof(formId) == "undefined") {
        formId = "listForm";
    }
    if(jQuery('#page')[0]){
        jQuery("#page").val(page);
    } else{
        jQuery("#" + formId).append(jQuery(sInput));
    }
    jQuery("#" + formId).submit();
}
/**
 * 添加新item
 * @param url
 */
function addItem(url) {
    window.location = url;
}
/**
 * 返回各状态任务数页面
 * @param url
 */
function goBackTasckCount(url){
    window.location = url;
}
/**
 * 验证名称长度
 * @param str
 */
function getLens(str) {
    var p1 = new RegExp('%u..', 'g')
    var p2 = new RegExp('%.', 'g')
    return escape(str).replace(p1, '').replace(p2, '').length
}
/**
 * 验证中文
 * @param name
 */
function checkChinese(name){
    return (/[\u4E00-\u9FA5]/g.test(name));
}
/**
 * 验证正整数
 * @param value
 */
function checkNum(value){
    var re = /^(\+|-)?\d+$/;
//    alert(re.test(value));
    return re.test(value);
}
/**
 * 关闭弹出窗口
 */
function closeWin() {
    window.opener = null;
    //window.opener=top;
    window.open("","_self");
    window.close();
}

function formatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();

    var h = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d) +' '+(h<10?('0'+h):h)+":"+(mm<10?('0'+mm):mm)+":"+(s<10?('0'+s):s);
}

/**
 * 更换json对象中，key的值--许恕-2015年9月29日
 *  说明：key变化，value值不变化，比如将 {name：xvshu}改为{username：xvshu}
 * @param json
 * @param oddkey 旧key值
 * @param newkey 新key值
 */
function del(json,oddkey,newkey){
    var val=json[oddkey];
    //delete json[oddkey];
    json[newkey]=val;
}

/**
 * easyui将数据库中时间戳转为日期格式--许恕--2015年9月29日
 * @param nS 数据库中的时间戳 例：1293072805
 * @returns {string} 普通日期格式 例：2010-12-23
 */
function getLocalTime(val,row,index) {
    if(val==null){
        return "";
    }else {
        return formatter(new Date(parseInt(val)));
    }
}

function getLocalTimes(val) {
    if(val==null){
        return "";
    }else {
        return formatter(new Date(parseInt(val)));
    }
}

/**
 * easyui将数据库中的大写字符转换为小写
 * * @param nS 例：SAY
 * @returns {string}  例：say
 */
function getLowercaseletters(val,row,index) {
    if(val==null){
        return "";
    }else {
        return val.toLowerCase();
    }
}


function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function() {
        return this.elements.length;
    };
    //判断MAP是否为空
    this.isEmpty = function() {
        return (this.elements.length < 1);
    };
    //删除MAP所有元素
    this.clear = function() {
        this.elements = new Array();
    };
    //向MAP中增加元素（key, value)
    this.put = function(_key, _value) {
        this.elements.push( {
            key : _key,
            value : _value
        });
    };
    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    };
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };
    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };
    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };
}