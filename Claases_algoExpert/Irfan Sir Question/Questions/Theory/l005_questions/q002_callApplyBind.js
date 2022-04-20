// Create 3 simple functions where call, bind and apply are used. 
// The intention of this exercise isto understand how they work and their differences.

// Constraints:
// 1.The candidate should be able to explain what call, bind and apply in JS are and itsdifferences.
// 2.Using live examples the candidate should be able to differentiate between them.
//==================================================================================================================

let person1 = {
    name: "Rajneesh Kumar",
    age: 24,

    showDetails: function () {
        console.log(`My name is ${this.name} and age is ${this.age}`);
    }
}

// call
let obj = {
    name: "Rajneesh Kumar",
    age: 24
}

function getInfo1(message) {
    return `My name is ${this.name} and age is ${this.age} and message is ${message}`;
}

console.log(getInfo1.call(obj, "hello"));


//apply
function getInfo2(...arr) {
    return `My name is ${this.name} and age is ${this.age} and lives is ${arr[0]} with in ${arr[1]}`;
}

console.log(getInfo2.apply(person1, ["India", "Delhi"]));


//bind
function getInfo2(...arr) {
    return `My name is ${this.name} and age is ${this.age} and lives is ${arr[0]} with in ${arr[1]}`;
}

let ans = getInfo2.bind(person1);  // this will return a bined function
console.log(ans("India", "Delhi"));
