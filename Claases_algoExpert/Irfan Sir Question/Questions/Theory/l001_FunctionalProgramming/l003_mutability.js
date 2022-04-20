const person1 = {
    name: "roger",
    age: 30
};

// const person2 = Object.assign({}, person1);
const person2 = { ...person1 }; // deep copy

person2.name = "steve";
console.log(person1);
console.log(person2);