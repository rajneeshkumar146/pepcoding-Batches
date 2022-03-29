// print
// console.log("hello world");
// console.log("hello world", "I'm Rajneesh");

// variables -> var, let, const

// 1. If you re-declare a JavaScript variable declared with var, it will not lose its value.
// console.log(carName);
// var carName = "volvo";
// console.log(carName);
// var carName = "BMW";
// console.log(carName);
// var carName;
// console.log(carName);

// 2. You cannot re-declare a variable declared with let or const.
// console.log(carName);
// let carName = "volvo";
// console.log(carName);
// let carName;  // not allowed

// let a;
// a = 10; console.log(typeof (a));
// a = 10.1; console.log(typeof (a));
// a = "hello i'm String"; console.log(typeof (a));
// a = null; console.log(typeof (a));
// a = true; console.log(typeof (a));

// Functions =================================================================

// Non-premitive -> array, function, object

// function isEven(val) {
//     console.log(val % 2 == 0);
//     // return val % 2 == 0;
// }

// let ans = isEven(11);
// console.log(ans);

function isPrime(n) {
    for (let i = 2; i * i <= n; i++) {
        if (n % i == 0)
            return false;
    }
    return true;
}

function printAllPrime(a, b) {
    let ans = "";
    while (a <= b) {
        if (isPrime(a))
            ans += a + " ";
        a++;
    }
    console.log(ans);
}

function printAllPrime2(a, b) {
    while (a <= b) {
        if (isPrime(a))
            process.stdout.write(a + " ");
        a++;
    }
}

// printAllPrime2(1, 1000);

function decimalToBinary(n) {
    if (!typeof (n) == 'number' || isNaN(n)) {
        console.log("Not a Number");
        return;
    }
    if (!Number.isInteger(n)) {
        console.log("Number is float");
        return;
    }

    let pow = 1, ans = 0;
    while (n != 0) {
        let rem = n % 2;
        n = parseInt(n / 2);
        ans = ans + rem * pow;
        pow *= 10;
    }
    return ans;
}

let ans = decimalToBinary(132);
console.log(ans);

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
