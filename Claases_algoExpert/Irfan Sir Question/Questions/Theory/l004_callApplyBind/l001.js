// call
// let obj = {
//     name: "Rajneesh Kumar",
//     age: 24
// }

// function getInfo(message) {
//     return `My name is ${this.name} and age is ${this.age} and message is ${message}`;
// }

// console.log(getInfo.call(obj, "hello"));


let person1 = {
    name: "Rajneesh Kumar",
    age: 24,

    showDetails: function () {
        console.log(`My name is ${this.name} and age is ${this.age}`);
    }
}

let person2 = {
    name: "Irfan",
    age: 25,

    showDetails: function () {
        console.log(`My name is ${this.name} and age is ${this.age}`);
    }
}

// person1.showDetails.call(person2);

//Apply
function getInfo(...arr) {
    return `My name is ${this.name} and age is ${this.age} and lives is ${arr[0]} with in ${arr[1]}`;
}

let ans = getInfo.bind(person1);
console.log(ans("India", "Delhi"));




