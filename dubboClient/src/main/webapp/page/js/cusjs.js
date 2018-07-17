jQuery.fn.extend({
    /***
     * @desc 获取一个元素的多少级父元素对象
     * @param obj 获取对象
     * @wxlevel  获取第几个层级
     * @return
     * */
    lotParent:function(obj,wxlevel){
    var  _this = obj;
    var parentObj = null;
    wxlevel = wxlevel==null?0:wxlevel;
    try {
        for (var i = 0; i < wxlevel; i++) {
            if (i == 0) {
                parentObj = _this.parent();
            } else {
                parentObj = $(parentObj).parent();
            }
        }
    }catch(err){
        return null;
    }
    return parentObj;
    },

   /***
    * @desc 判断一个json对象[1,2,...],是否含有该number,或者字符串
    * @param json对象
    * @param json数组
    * @return true or false
    * */
    isContanins:function(json,obj){
            //判断json 对象是否存在和正常判断对象
            var bool = false;
            if(json==null || json.length<1 || json=="undefined"){
                 return bool;
            }
            $.each(json,function(i){
                   //先转化类型
                   if(typeof obj == "string" && json[i].toString() == obj){
                                bool = true;
                   }
                   if( typeof obj == "number" && parseInt(json[i])==obj){
                                bool= true;
                   }
            })
            return bool;
    },
    /****
     * @desc 重写数组的删除元素的方法
     * @aurhor wsbg
     * @param array 或者json串数组
     * @param obj 被删除元素
     * @param len 删除长度 default 0
     *
     * */
     removerArr:function(arr,obj,len){
          if(arr==null || arr == "undefined"||arr.length <1 || obj==null){
               return null;
           }
           len = (len==null || len =="undefined")?0:1;
           var ins = -1;
           var vobj = null;
           for(var i = 0 ;i < arr.length ;i++){
                 vobj = arr[i];
                 if(vobj == obj || (vobj == parseInt(obj)) ){
                      ins = i;
                      break;
                 }
           }
           if(ins>-1){
                //数组删除
               arr.splice(ins,len);
               //json删除
               //arr.remove(ins);
           }
           return arr;
     }
})
