// function isEven(val) {
//     return val % 2 == 0;
// }
// console.log(isEven(20));

//=================================================================
// real();
// function real(){
//     console.log("Hi i'm real 1");
// }

// function real(){
//     console.log("Hi i'm real 2");
// }

// function real(){
//     console.log("Hi i'm real 3");
// }

//=================================================================

// var varName1 = 10;
// function fn() {
//     console.log("line 1: " + varName1);
//     var varName2 = 20;
//     console.log("line 2: " + varName2);
//     function fn2() {
//         console.log("line 3: " + varName1);
//     }
//     fn2();
// }
// fn();

// method : lexical scope
// process to find variable is known as scope-chaining.


// console.log("line 1", varName);
// var varName = 10;
// console.log("line 2", varName);

// function a() {
//     console.log("line 3", varName);
// }

// function fn() {
//     console.log("line 4", varName);
//     var varName = 20;
//     function b() {
//         console.log("line 5", varName);
//     }
//     b();
//     a();
// }

// fn();


//===================================================================
// console.log(isEven);
// var isEven = function (val) {
//     return val % 2 == 0;
// }
// console.log(isEven);


// var real = function () {
//     console.log("Hi i'm real 1");
// }
// real();

// var real = function () {
//     console.log("Hi i'm real 2");
// }
// real();

// var real = function () {
//     console.log("Hi i'm real 3");
// }
// real();

// IIFE -> Immediately Invoked Functions Expression
// (function () {
//     console.log("hi!!!");
// })();

// // Arrow Functions-> syntax, react
// let fun1 = (num) => {
//     return num * num;
// };

// let fun2 = (num) => num * num;

// console.log(fun1(3));

// function sayHello(args) {
//     console.log("hello", args);
//     if (typeof args === 'function') console.log(args());

//     return args;
// }

// function smaller() {
//     console.log("hello i'm smaller");
//     return [1, 2, 3, "hi"];
// }

// let fn = sayHello(smaller);
// console.log(fn);


//============================================================

const person1 = {
    name: "Rajneesh",
    age: 24,
    fun: function () {
        console.log(hi);
        return 30;
    },
    arr: [12, 3, 4, 45]
};

let shallowCopy = person1;
shallowCopy.name = "Irfan";
console.log(person1);

// const deepCopy = Object({}, person1);   // deep copy
const deepCopy = { ...person1 };
deepCopy.name = "Roger";
console.log(deepCopy);