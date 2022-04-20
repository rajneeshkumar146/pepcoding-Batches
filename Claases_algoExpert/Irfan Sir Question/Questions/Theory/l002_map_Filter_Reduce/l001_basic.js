const myArr = [1, 2, 3, 4, 5, 6,7,8,9,10]

// map
const newArr = myArr.map((val) => {
    if (typeof val === 'number') return val * val;
});
console.log(newArr);

// filter
// const newArr = myArr.filter((val) => {
//     if (typeof val !== 'number') return val;
// });
// console.log(newArr);

// reduce
const ans = myArr.reduce((sum, val) => {
    return sum + val;
}, 0);
console.log(ans);