Array.prototype.myFun = function () {
    console.log("Hi i'm prototype");
    console.log(this);
}

// Array.prototype.myFun = () => {
//     console.log("Hi i'm prototype");
//     console.log(this);
// }

let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
// arr.myFun();

// cb is call back function
Array.prototype.myMap = function (cb) {
    let newArr = [];
    for (let i = 0; i < this.length; i++) {
        newArr.push(cb(this[i]));
    }
    return newArr;
}

Array.prototype.myReduce = function (cb, defaultValue) {
    let sum = defaultValue;
    for (let i = 0; i < this.length; i++)
        sum = cb(sum, this[i]);

    return sum;
}



function cb(val) {
    if (typeof val === 'number') return val * val;
    else return 0;
}
// console.log(arr.myMap(cb));

let ans = arr.myMap((val) => {
    if (typeof val === 'number') return val * val;
    else return 0;
});
console.log(ans);

ans = arr.myReduce((sum, val) => {
    return sum + val;
}, 0);
console.log(ans);