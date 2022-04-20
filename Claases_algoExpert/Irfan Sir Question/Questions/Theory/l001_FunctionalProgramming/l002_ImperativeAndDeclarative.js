// Imperative Way of coding write and every steps 

// const x = 5;
// const xSquared = x * x;
// let isEven = xSquared % 2 === 0 ? true : false;

//decarative way of code just give result

// const isSquareEven = (x) => (x * x % 2 == 0 ? true : false);
// console.log(isSquareEven(23));

// pure Function with sideEffect means it is using some other resource outside function

function addNum(a, b) {
    console.log(a + b);  // console.log is extrnal resource 
}

function addNums(a, b) {
    return a + b;
}
