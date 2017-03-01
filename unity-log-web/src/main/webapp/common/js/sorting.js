var sort = {
    debug:function(str){
        if(window.console && window.console.log){
            console.log(str);
        }
    },
    swap:function(arr,index1,index2){//数组数据交换
        var temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    },
    bubbleSort:function(arr){ //冒泡排序
        this.debug("冒泡排序before:"+arr);
        var len = arr.length;
        for(var outer = 0;outer<len-1;outer++){//比较的轮数
            for(var inner = 0;inner<len-outer-1;inner++){//每轮比较的次数
                if(arr[inner] > arr[inner+1] ){
                    this.swap(arr,inner,inner+1)
                }
            }
            this.debug("第"+(outer+1)+"轮后:"+arr);
        }
        this.debug("冒泡排序after:"+arr);
    },
    selectionSort:function(arr){//选择排序
        this.debug("选择排序before:"+arr);
        var min,len = arr.length;
        for (var outer = 0; outer < len -1; outer++) {
            min = outer;
            // 比较最小项目和第outer项之后的剩余数组项, 以寻找更小项
            for (var inner = outer+1; inner < len; inner++) {
                if (arr[inner] <arr[min]) {
                    min = inner;
                    this.debug("min--"+min);
                }
            }
            this.swap(arr,outer, min);
            this.debug("第"+(outer+1)+"轮后:"+arr);
        }
        this.debug("选择排序after:"+arr);
    },
    insertionSort:function(arr){//插入排序
        this.debug("插入排序before:"+arr);
        var temp,inner,len = arr.length;
        for (var outer = 1; outer < len ; outer++) {
            temp = arr[outer];
            inner = outer;
            while(inner>0 && arr[inner-1] >= temp){
                arr[inner] = arr[inner-1];
                --inner;
            }
            arr[inner] = temp;
        }
        this.debug("插入排序after:"+arr);
    },
    shellSort:function(arr){//希尔排序
        this.debug("希尔排序before:"+arr);
        var gaps = [5,3,1],
            temp;
        for(var g = 0;g <gaps.length; g++){
            for(var i = gaps[g];i<arr.length;i++){
                temp = arr[i];
                for(var j = i;j >= gaps[g] && arr[j-gaps[g]] > temp;j -= gaps[g]){
                    arr[j] = arr[j-gaps[g]];
                }
                arr[j] = temp;
            }
        }
        this.debug("希尔排序after:"+arr);
    },
    shellSortDynamic:function(arr){//动态计算间隔序列的希尔排序
        this.debug("动态计算间隔序列的希尔排序before:"+arr);
        var N = arr.length,
            h = 1;
        while(h < N/3){
            h = 3*h +1;
        }
        while(h >= 1){
            for(var i = h;i < N;i++){
                for(var j = i;j >= h && arr[j] < arr[j-h];j -= h){
                    this.swap(arr,j,j-h);
                }
            }
            h = (h-1)/3;
        }
        this.debug("动态计算间隔序列的希尔排序after:"+arr);
    },
    mergeSort:function(arr){//归并排序
        this.debug("归并排序before:"+arr);
        var len = arr.length,
            step = 1,
            left,
            right;
        var mergeArray = function(arr,startLeft,stopLeft,startRight,stopRight){
            var rightArr = new Array(stopRight - startRight +1),
                leftArr =  new Array(stopLeft - startLeft +1),
                k = startRight,
                m = 0,
                n = 0;
            for(var i = 0;i < (rightArr.length - 1); ++i){
                rightArr[i] = arr[k];
                ++k;
            }
            k = startLeft;
            for(var i = 0;i < (leftArr.length - 1); ++i){
                leftArr[i] = arr[k];
                ++k;
            }
            rightArr[rightArr.length-1] = Infinity;  //哨兵值
            leftArr[leftArr.length-1] = Infinity;    //哨兵值
            for(var k = startLeft;k < stopRight; ++k){
                if(leftArr[m] <=rightArr[n]){
                    arr[k] = leftArr[m];
                    m++;
                }else{
                    arr[k] = rightArr[n];
                    n++
                }
            }
        }
        if(len < 2){
            return;
        }
        while(step < len){
            left = 0;
            right = step;
            while(right + step <= len){
                mergeArray(arr,left,left+step,right,right+step);
                left = right + step;
                right = left + step;
            }
            if(right < len){
                mergeArray(arr,left,left+step,right,len);
            }
            step *= 2;
        }
        this.debug("归并排序after:"+arr);
    },
    quickSort:function(arr){//快速排序
        this.debug("快速排序before:"+arr);
        var _quickSort = function(arr){
            var len = arr.length,
                lesser = [],
                greater = [],
                pivot,
                meCall = arguments.callee;
            if(len == 0){
                return [];
            }
            pivot = arr[0];
            for(var i = 1;i < len; i++){
                if(arr[i] < pivot ){
                    lesser.push(arr[i])
                }else{
                    greater.push(arr[i])
                }
            }
            return meCall(lesser).concat(pivot,meCall(greater));
        }
        this.debug("快速排序after:"+_quickSort(arr));
        return _quickSort(arr);
    }
}

var search = {
    linearSearch:function(arr,data){//线性查找
        for(var i = 0;i<arr.length;i++){
            if(arr[i] == data){
                return i;
            }
        }
        return -1;
    },
    binarySearch:function(arr,data){//二分查找 适用于已排好序的线性结构
        var lowerBound = 0,
            upperBound = arr.length - 1,
            mid;
        while(lowerBound <= upperBound){
            mid = Math.floor((lowerBound+upperBound)/2);
            if(arr[mid] < data){
                lowerBound = mid + 1;
            }else if(arr[mid] > data){
                upperBound = mid - 1;
            }else{
                return mid;
            }
            return -1;
        }
    }
}

//定义一个比较器
function compare(propertyName) {
    return function (object1, object2) {
        var value1 = object1[propertyName];
        var value2 = object2[propertyName];
        if (value2 < value1) {
            return -1;
        }
        else if (value2 > value1) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
