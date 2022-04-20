var a = 10;
var a = "Irfan";
var a ;
console.log(a);
var a = 30;

let b = 10;
b = 30;
console.log(b);

const val = 30;
// const ======================================================================
// 1. The const keyword was introduced in ES6 (2015).
// 2. Variables defined with const cannot be Redeclared.
// 3. Variables defined with const cannot be Reassigned.
// 4. Variables defined with const have Block Scope.
// 5. It defines a constant reference to a value.
// 6. As a general rule, always declare a variable with const unless you know that the value will change.
//             Use const when you declare:
//                 a. A new Array
//                 b. A new Object
//                 c. A new Function
//                 d. A new RegExp
// 7. Variables defined with var are hoisted to the top and can be initialized at any time.
//    Meaning: You can use the variable before it is declared:
// 8. Variables defined with const are also hoisted to the top, but not initialized.
//    Meaning: Using a const variable before it is declared will result in a ReferenceError:

function fun() {
    console.log("hi");
    return 1000;
}



const c = [1, 2, 3, 4, 5, 6, "Rajneesh", fun];
// c.push(30);
// c.shift();
// c.unshift(10);
// c[2] = 20;
// console.log(c[7]());

const obj = {
    name: "Rajneesh",
    age: 24,
}