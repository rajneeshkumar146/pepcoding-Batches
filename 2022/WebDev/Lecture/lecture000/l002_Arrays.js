// declare Array
let arr = [1, 2, 3, 4, 5, 6, 7, 89, "rahul", true, 12.45, '4'];
// console.log(arr);
console.log(arr.length);

// for (let i = 0; i < arr.length; i++)  // tradition loop
//     console.log("element at i", i , "is",arr[i]);

// for (let v in arr)   // for keys
//     console.log("element at i", v , "is",arr[v]);

// for (let v of arr)  // for values
//     console.log(v);

// push, unshift ===============================================
// arr.push("lastValue");
// arr.unshift("firstValue");
// console.log(arr);

// pop, shift ===============================================
// arr.pop();
// arr.shift();
// console.log(arr);

// indexof, contains() ===============================================

// slice(sidx, eidx), eidx is exclusive ========================================
// let partOfArray = arr.slice(3, 7);
// console.log(partOfArray);

// splice(sidx, NoOfele), for delete ===============================================
// arr.splice(0,4);
// console.log(arr);

//Filters==============================================================================
// 1. The filter() method creates a new array filled with elements that pass a test provided by a function.
// 2. The filter() method does not execute the function for empty elements.
// 3. The filter() method does not change the original array.

const filteredItem = arr.filter((item) => {
    return typeof (item) == 'number' && item % 2 == 0;
});

console.log(filteredItem);

console.log(a);
const a = 10;
